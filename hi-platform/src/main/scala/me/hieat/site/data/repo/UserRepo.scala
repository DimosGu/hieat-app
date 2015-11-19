package me.hieat.site.data.repo

import me.hieat.site.common.exception.HiUnauthorizedException
import me.hieat.site.common.{HiConstant, PasswordUtils, Utils}
import me.hieat.site.data.domain.{AccountId, UserAttrs}
import me.hieat.site.data.driver.MyDriver.api._
import me.hieat.site.data.model.{Credential, User}
import play.api.libs.json.Json

import scala.concurrent.{ExecutionContext, Future}

/**
 * User Repo
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-09-30.
 */
class UserRepo(schema: Schema)(implicit ec: ExecutionContext) {

  import schema._

  def existsByPhone(phone: String): Future[Int] = {
    db.run(tCredential.filter(_.phone === phone).length.result)
  }

  def findOneById(accountId: AccountId): Future[Option[User]] = {
    val action = tUser.filter(_.id === accountId).result
    db.run(action).map(_.headOption)
  }

  def signInByPhone(phone: String, password: String): Future[Option[User]] = {
    val q = for {
      c <- tCredential.filter(_.phone === phone)
      u <- tUser.filter(t => t.id === c.id)
    } yield (c, u)
    db.run(q.result).map(_.headOption).map {
      case Some((c, u)) =>
        if (PasswordUtils.matchPassword(c.salt, c.password, password.getBytes(HiConstant.CHARSET)))
          Some(u)
        else
          throw HiUnauthorizedException(s"$phone unauthorized")

      case None =>
        None
    }
  }

  def signUpByPhone(phone: String, pwd: String): Future[AccountId] = {
    val (salt, password) = PasswordUtils.generatePassword(pwd)
    val credential = Credential(0L, Some(phone), None, salt, password)

    val action = for {
      accountId <- tCredential returning tCredential.map(_.id) += credential
      _ <- tUser += User(accountId, Json.toJson(UserAttrs(phone = Some(phone))), Utils.now())
    } yield accountId

    db.run(action.transactionally)
  }

}
