package me.hieat.site.http

import javax.inject.{Inject, Provider}

import me.hieat.site.common.exception._
import me.hieat.site.data.MyJsonImplicits._
import play.api.http.DefaultHttpErrorHandler
import play.api.libs.json.Json
import play.api.mvc.{RequestHeader, Result, Results}
import play.api.routing.Router
import play.api.{Configuration, Environment, OptionalSourceMapper, UsefulException}

import scala.concurrent.Future

/**
 * ErrorHandler
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-10-02.
 */
class ErrorHandler @Inject()(env: Environment,
                             config: Configuration,
                             sourceMapper: OptionalSourceMapper,
                             router: Provider[Router]
                              ) extends DefaultHttpErrorHandler(env, config, sourceMapper, router) {

  override def onProdServerError(request: RequestHeader, exception: UsefulException) = {
    Future.successful(
      Results.InternalServerError("A server error occurred: " + exception.getMessage)
    )
  }

  override def onForbidden(request: RequestHeader, message: String) = {
    Future.successful(
      Results.Forbidden("You're not allowed to access this resource.")
    )
  }

  override def onServerError(request: RequestHeader, exception: Throwable): Future[Result] = {
    exception match {
      case e: HiBadRequestException =>
        Future.successful(Results.BadRequest(Json.toJson(e)))
      case e: HiUnauthorizedException =>
        Future.successful(Results.Unauthorized(Json.toJson(e)))
      case e: HiForbiddenException =>
        Future.successful(Results.Forbidden(Json.toJson(e)))
      case e: HiNotFoundException =>
        Future.successful(Results.NotFound(Json.toJson(e)))
      case e: HiConflictException =>
        Future.successful(Results.Conflict(Json.toJson(e)))
      case e: HiException => // HiInternalServerException
        Future.successful(Results.InternalServerError(Json.toJson(e)))
      case _ =>
        super.onServerError(request, exception)
    }
  }

  override def onClientError(request: RequestHeader, statusCode: Int, message: String): Future[Result] = {
    super.onClientError(request, statusCode, message)
  }
}
