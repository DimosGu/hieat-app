package me.hieat.site.data.domain

import play.api.libs.json._

import scala.language.implicitConversions

/**
 * 用户相关ID
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-09-27.
 */
class AccountId(val value: Long) extends AnyVal {
  override def toString: String = String.valueOf(value)
}

object AccountId {
  def create() = new AccountId(0L)

  def apply(value: Long) = new AccountId(value)

  implicit val __formats = new Format[AccountId] {
    override def writes(o: AccountId): JsValue = JsNumber(o.value)

    override def reads(json: JsValue): JsResult[AccountId] = JsSuccess(new AccountId(json.as[Long]))
  }

  implicit def long2OwnerId(l: Long): AccountId = AccountId(l)

}
