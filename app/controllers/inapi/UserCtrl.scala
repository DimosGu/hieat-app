package controllers.inapi

import java.util.UUID
import javax.inject.{Inject, Singleton}

import me.hieat.site.business.service.{CaptchaService, RedisService, SmsService, UserService}
import me.hieat.site.common.exception.{HiForbiddenException, HiNotFoundException}
import me.hieat.site.common.{HiConstant, Settings, Utils}
import me.hieat.site.data.domain.{AccountToken, SignAccount}
import me.hieat.site.http.WebUtils
import play.api.libs.json.Json
import play.api.mvc.Action
import utils.{MyController, TokenAction}

import scala.concurrent.Future

/**
 * User Api Controller
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-09-30.
 */
@Singleton
class UserCtrl @Inject()(userService: UserService,
                         captchaService: CaptchaService,
                         smsService: SmsService,
                         redisService: RedisService) extends MyController {
  def signInByPhone = Action.async(parse.json.map(_.as[SignAccount])) { request =>
    val result = for {
      key <- request.session.get(HiConstant.CAPTCHA_KEY)
      token <- redisService.getCaptcha(key) if token == request.body.code
    } yield {
        userService.signInByPhone(request.body).map {
          case Some(u) =>
            // XXX 设置Session
            val maxAge = Settings.cookieSettings.maxAge
            val accountToken = AccountToken(
              u.id,
              Utils.currentTimeSeconds + maxAge,
              request.remoteAddress)
            val cookie = WebUtils.createCookieByToken(accountToken)

            Ok(Json.toJson(u)).withCookies(cookie).withNewSession

          case None =>
            NotFound(Json.toJson(HiNotFoundException("账号不存在")))
        }
      }
    result getOrElse Future.successful(Forbidden(Json.toJson(HiForbiddenException("验证码无效"))))
  }

  def signUpByPhone = Action.async(parse.json.map(_.as[SignAccount])) { request =>
    userService.signUpByPhone(request.body).map { oid =>
      Created(Json.obj("id" -> oid))
    }
  }

  def signOut = TokenAction { request =>
    val cookie = WebUtils.discardCookie(HiConstant.OWNER_TOKEN_COOKIE_NAME)
    Ok.withCookies(cookie).withNewSession
  }

  def createCaptcha = Action {
    val (token, bytes) = captchaService.createCaptcha()
    val key = UUID.randomUUID().toString
    redisService.saveCaptcha(key, token)
    Ok(bytes)
      .withSession(HiConstant.CAPTCHA_KEY -> key)
      .withHeaders(
        "Content-Type" -> "image/png",
        "Cache-Control" -> "no-cache, no-store",
        "Pragma" -> "no-cache")
  }

  def createSmsValidCode = Action.async(parse.json) { request =>
    // TODO 校验手机号是否已经注册
    val phone = request.body.\("account").as[String]
    val code = request.body.\("code").as[String]
    userService.existsByPhone(phone).map {
      case 0 =>
        val result = for {
          key <- request.session.get(HiConstant.CAPTCHA_KEY)
          token <- redisService.getCaptcha(key) if token == code
        } yield {
            val validCode = smsService.createValidCode(phone)
            // TODO 实际商品中应把validCode通过短信网关发送给用户
            Ok
          }
        result getOrElse Forbidden(Json.toJson(HiForbiddenException("验证码无效")))

      case _ =>
        Forbidden(Json.toJson(HiForbiddenException(s"$phone 已注册")))
    }
  }

  def getUser = TokenAction.async { request =>
    val token = request.token
    userService.findOneById(token.accountId).map {
      case Some(user) =>
        Ok(Json.toJson(user))
      case None =>
        NotFound(Json.toJson(HiNotFoundException(token.accountId + " not exists")))
    }
  }
}
