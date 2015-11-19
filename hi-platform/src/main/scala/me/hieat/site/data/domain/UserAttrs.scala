package me.hieat.site.data.domain

import me.hieat.site.common.enums.Gender
import play.api.libs.json.Json

/**
 * 用户属性
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-09-30.
 */
case class UserAttrs(phone: Option[String] = None,
                     email: Option[String] = None,
                     nick: Option[String] = None,
                     age: Option[Int] = None,
                     gender: Option[Gender.Value] = None)

object UserAttrs {
  implicit val __formats = Json.format[UserAttrs]
}