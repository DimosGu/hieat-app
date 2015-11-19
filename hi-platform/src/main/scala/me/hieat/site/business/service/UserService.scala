package me.hieat.site.business.service

import javax.inject.{Inject, Singleton}

import me.hieat.site.data.domain.{AccountId, SignAccount}
import me.hieat.site.data.model.User
import me.hieat.site.data.repo.{Schema, UserRepo}
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.Future

/**
 * UserService
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-09-30.
 */
@Singleton
class UserService @Inject()(schema: Schema) {
  def existsByPhone(phone: String) = {
    userRepo.existsByPhone(phone)
  }

  def findOneById(accountId: AccountId) = {
    userRepo.findOneById(accountId)
  }

  def signUpByPhone(payload: SignAccount): Future[AccountId] = {
    // TODO 校验码验证
    userRepo.signUpByPhone(payload.account, payload.password)
  }

  def signInByPhone(payload: SignAccount): Future[Option[User]] = {
    userRepo.signInByPhone(payload.account, payload.password)
  }

  val userRepo = new UserRepo(schema)
}
