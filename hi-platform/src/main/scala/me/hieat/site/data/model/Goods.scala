package me.hieat.site.data.model

import java.time.LocalDateTime

import me.hieat.site.data.domain.GoodsId
import me.hieat.site.data.driver.MyDriver.api._
import play.api.libs.json.JsValue

/**
 * 商品
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-09-28.
 */
case class Goods(id: GoodsId,
                 name: String,
                 price: BigDecimal,
                 images: List[String],
                 attrs: JsValue,
                 createdAt: LocalDateTime)

class TableGoods(tag: Tag) extends Table[Goods](tag, "t_goods") {
  val id = column[GoodsId]("id", O.PrimaryKey)
  val name = column[String]("name")
  val price = column[BigDecimal]("price")
  val images = column[List[String]]("images")
  val attrs = column[JsValue]("attrs")
  val createdAt = column[LocalDateTime]("createdAt")

  def * = (id, name, price, images, attrs, createdAt) <>(Goods.tupled, Goods.unapply)
}

