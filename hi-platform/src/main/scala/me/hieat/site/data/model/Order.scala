package me.hieat.site.data.model

import java.time.LocalDateTime

import me.hieat.site.data.domain.{AddressId, OrderId}
import me.hieat.site.data.driver.MyDriver.api._
import play.api.libs.json.JsValue

/**
 * 订单
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-09-28.
 */
case class Order(id: OrderId,
                 attrs: JsValue,
                 addressId: Option[AddressId],
                 createdAt: LocalDateTime)

class TableOrder(tag: Tag) extends Table[Order](tag, "t_order") {
  val id = column[OrderId]("id", O.PrimaryKey)
  val attrs = column[JsValue]("attrs")
  val addressId = column[Option[AddressId]]("addressId")
  val createdAt = column[LocalDateTime]("createdAt")

  def __fkAddress = foreignKey(tableName + "_fk_address", addressId, TableQuery[TableAddress])(_.id.?)

  def * = (id, attrs, addressId, createdAt) <>(Order.tupled, Order.unapply)
}
