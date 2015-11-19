package me.hieat.site.common.enums

import play.api.libs.json._

/**
 * 用户性别
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-09-27.
 */
object Gender extends Enumeration {
  val Male = Value("M")
  val Female = Value("F")

  implicit val __formats = new Format[Gender.Value] {
    override def writes(o: Gender.Value): JsValue = JsString(o.toString)

    override def reads(json: JsValue): JsResult[Gender.Value] = JsSuccess(Gender.withName(json.as[String]))
  }
}
