package me.hieat.site.data.domain

import play.api.libs.json._

/**
 * 送货地址
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-09-28.
 */
class AddressId(val value: String) extends AnyVal {
  override def toString: String = value
}

object AddressId {
  def apply(value: String) = new AddressId(value)

  implicit val __formats = new Format[AddressId] {
    override def writes(o: AddressId): JsValue = JsString(o.value)

    override def reads(json: JsValue): JsResult[AddressId] = JsSuccess(new AddressId(json.as[String]))
  }
}
