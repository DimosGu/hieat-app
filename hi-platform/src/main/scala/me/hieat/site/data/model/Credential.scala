package me.hieat.site.data.model

import me.hieat.site.data.domain.AccountId
import me.hieat.site.data.driver.MyDriver.api._

/**
 * 用户认证信息
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-09-28.
 */
case class Credential(id: AccountId,
                      phone: Option[String],
                      email: Option[String],
                      salt: Array[Byte],
                      password: Array[Byte])

class TableCredential(tag: Tag) extends Table[Credential](tag, "t_credential") {
  val id = column[AccountId]("id", O.PrimaryKey, O.AutoInc)
  val phone = column[Option[String]]("phone")
  val email = column[Option[String]]("email")
  val salt = column[Array[Byte]]("salt")
  val password = column[Array[Byte]]("password")

  def __idxPhone = index(tableName + "_idx_phone", phone, true)

  def __idxEmail = index(tableName + "_idx_email", email, true)

  def * = (id, phone, email, salt, password) <>(Credential.tupled, Credential.unapply)
}
