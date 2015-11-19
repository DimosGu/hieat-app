package utils

import me.hieat.site.common.exception.HiUnauthorizedException
import me.hieat.site.data.domain.AccountToken
import me.hieat.site.http.WebUtils
import play.api.mvc.{ActionBuilder, ActionTransformer, Request, WrappedRequest}

import scala.concurrent.Future

/**
 * TokenAction
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-10-01.
 */
object TokenAction extends ActionBuilder[TokenRequest] with ActionTransformer[Request, TokenRequest] {
  override protected def transform[A](request: Request[A]): Future[TokenRequest[A]] =
    WebUtils.getOwnerToken(request) match {
      case Some(token) =>
        if (token.hasExpired) {
          throw HiUnauthorizedException("Unauthorized: expired")
        }

        Future.successful(new TokenRequest(token, request))

      case None =>
        throw HiUnauthorizedException("unauthorized")
    }
}

class TokenRequest[A](val token: AccountToken, request: Request[A]) extends WrappedRequest[A](request) {
  override def toString(): String = getClass.getSimpleName + "(" + token + ", " + request + ")"
}
