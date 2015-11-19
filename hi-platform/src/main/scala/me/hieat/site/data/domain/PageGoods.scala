package me.hieat.site.data.domain

import me.hieat.site.data.model.Goods
import play.api.libs.json.Json

/**
 * 商品分页
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-10-06.
 */
case class PageGoods(content: Seq[Goods], page: Int, limit: Int, totalItems: Long, totalPage: Int)

import me.hieat.site.data.MyJsonImplicits._

object PageGoods {
  implicit val __writes = Json.writes[PageGoods]
}
