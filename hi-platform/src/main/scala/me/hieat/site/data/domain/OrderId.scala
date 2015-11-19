package me.hieat.site.data.domain

import play.api.libs.json._

/**
 * 订单编号
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-09-30.
 */
class OrderId(val value: String) extends AnyVal{
  override def toString: String = value
}

object OrderId {
  def apply(value: String) = new OrderId(value)

  implicit val __formats = new Format[OrderId] {
    override def reads(json: JsValue): JsResult[OrderId] = JsSuccess(OrderId(json.as[String]))

    override def writes(o: OrderId): JsValue = JsString(o.value)
  }
}
