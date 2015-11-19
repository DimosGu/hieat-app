package me.hieat.site.data.model

import java.time.LocalDateTime

import me.hieat.site.data.domain.AccountId
import me.hieat.site.data.driver.MyDriver.api._
import play.api.libs.json.JsValue

/**
 * 用户表
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-09-27.
 */
case class User(id: AccountId,
                attrs: JsValue,
                createdAt: LocalDateTime) {

}

class TableUser(tag: Tag) extends Table[User](tag, "t_user") {
  val id = column[AccountId]("id", O.PrimaryKey)
  val attrs = column[JsValue]("attrs")
  val createdAt = column[LocalDateTime]("createdAt")

  def * = (id, attrs, createdAt) <>(User.tupled, User.unapply)
}
