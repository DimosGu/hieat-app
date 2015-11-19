package me.hieat.site.data.model

import java.time.LocalDateTime

import me.hieat.site.data.domain.{GoodsId, OrderId}
import me.hieat.site.data.driver.MyDriver.api._
import play.api.libs.json.JsValue

/**
 * 订单明细，每个商品一条明细
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-09-28.
 */
case class OrderItem(id: String,
                     orderId: OrderId,
                     goodsId: GoodsId,
                     attrs: JsValue,
                     createdAt: LocalDateTime)

class TableOrderItem(tag: Tag) extends Table[OrderItem](tag, "t_order_item") {
  val id = column[String]("id", O.PrimaryKey)
  val orderId = column[OrderId]("orderId")
  val goodsId = column[GoodsId]("goodsId")
  val attrs = column[JsValue]("attrs")
  val createdAt = column[LocalDateTime]("createdAt")

  def __fkOrder = foreignKey(tableName + "_fk_order", orderId, TableQuery[TableOrder])(_.id)

  def __fkGoods = foreignKey(tableName + "_fk_goods", goodsId, TableQuery[TableGoods])(_.id)

  def * = (id, orderId, goodsId, attrs, createdAt) <>(OrderItem.tupled, OrderItem.unapply)
}
