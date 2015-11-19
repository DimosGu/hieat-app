package me.hieat.site.data.domain

import me.hieat.site.common.Utils
import play.api.libs.json._

/**
 * 商品ID
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-09-30.
 */
class GoodsId(val value: String) extends AnyVal {
  override def toString: String = value

  def isEmpty = (value eq null) || value.isEmpty
}

object GoodsId {
  def apply(): GoodsId = new GoodsId(Utils.oid())

  def apply(value: String): GoodsId = new GoodsId(value)

  implicit val __formats = new Format[GoodsId] {
    override def reads(json: JsValue): JsResult[GoodsId] = JsSuccess(GoodsId(json.as[String]))

    override def writes(o: GoodsId): JsValue = JsString(o.value)
  }
}
