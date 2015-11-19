package me.hieat.site.http.filters

import com.typesafe.scalalogging.StrictLogging
import me.hieat.site.common.exception.HiUnauthorizedException
import me.hieat.site.common.{HiConstant, SecurityUtils, Settings, Utils}
import me.hieat.site.data.domain.AccountToken
import me.hieat.site.http.WebUtils
import play.api.mvc.{Filter, RequestHeader, Result}

import scala.concurrent.Future

/**
 * Owner Token Filter
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-10-02.
 */
class TokenFilter extends Filter with StrictLogging {

  import play.api.libs.concurrent.Execution.Implicits.defaultContext

  val matchAll = Settings.filterAfterToken.matchAll
  val paths = Settings.filterAfterToken.paths
  val excludePaths = Settings.filterAfterToken.excludePaths

  override def apply(r: (RequestHeader) => Future[Result])(rh: RequestHeader): Future[Result] = {
    r(rh).map { result =>
      rh.path match {
        case path if !excludePaths.contains(path) && paths.exists(apiPath => path.startsWith(apiPath)) =>
          // XXX 重设AccountToken到cookie
          rh.cookies.get(HiConstant.OWNER_TOKEN_COOKIE_NAME) match {
            case Some(cookie) =>
//              logger.trace("old cookie: " + cookie)
              val decryptedValue = SecurityUtils.decrypt(cookie.value)
              val curToken = AccountToken(decryptedValue)
              if (curToken.hasExpired) {
                throw HiUnauthorizedException("session timeout")
              }

              val newToken = curToken.copy(timestamp = Utils.currentTimeSeconds + Settings.cookieSettings.maxAge)
              val newCookie = WebUtils.createCookieByToken(newToken)
//              logger.trace("new cookie: " + newCookie)
              result.withCookies(newCookie)

            case None =>
              result
          }

        case _ =>
          result
      }
    }
  }
}
