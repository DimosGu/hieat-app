package me.hieat.site.data

import me.hieat.site.common.exception.HiException
import me.hieat.site.data.domain.SignAccount
import me.hieat.site.data.model._
import play.api.libs.json.{JsValue, Writes, Json}

/**
 * Json Implicits
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-09-30.
 */
trait MyJsonImplicits {
  implicit val __exceptionWrites = new Writes[HiException] {
    override def writes(o: HiException): JsValue = Json.obj("message" -> o.message, "code" -> o.code)
  }
  implicit val __signAccountFormats = Json.format[SignAccount]
  implicit val __userFormats = Json.format[User]
  implicit val __goodsFormats = Json.format[Goods]
}

object MyJsonImplicits extends MyJsonImplicits
