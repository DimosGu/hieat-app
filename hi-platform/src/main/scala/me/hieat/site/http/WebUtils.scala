package me.hieat.site.http

import me.hieat.site.common.{HiConstant, SecurityUtils, Settings}
import me.hieat.site.data.domain.AccountToken
import play.api.mvc.{Cookie, DiscardingCookie, Request}

/**
 * Web Utils
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-10-02.
 */
object WebUtils {
  val cookieSettings = Settings.cookieSettings

  def getOwnerToken[A](implicit request: Request[A]) = {
    val utStr = request.cookies.get(HiConstant.OWNER_TOKEN_COOKIE_NAME).map(_.value) orElse
      request.headers.get(HiConstant.OWNER_TOKEN_COOKIE_NAME)

    utStr.map { v =>
      val s = SecurityUtils.decrypt(v)
      AccountToken(s)
    }
  }

  def discardCookie(name: String) = {
    DiscardingCookie(name, cookieSettings.path, cookieSettings.domain).toCookie
  }

  def createCookie(name: String, value: String, maxAge: Option[Int] = None) = {
    Cookie(name, value, maxAge orElse Some(cookieSettings.maxAge), cookieSettings.path, cookieSettings.domain)
  }

  def createCookieByToken(token: AccountToken) = {
    createCookie(HiConstant.OWNER_TOKEN_COOKIE_NAME, SecurityUtils.encrypt(token.toString), None)
  }

}
