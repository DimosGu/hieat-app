package me.hieat.site.data.repo

import me.hieat.site.common.{Utils, HiConstant}
import me.hieat.site.data.domain.{GoodsId, PageGoods}
import me.hieat.site.data.model.Goods

import scala.concurrent.{Future, ExecutionContext}
import me.hieat.site.data.driver.MyDriver.api._

/**
 * 商品
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-10-06.
 */
class GoodsRepo(schema: Schema)(implicit ec: ExecutionContext) {

  import schema._

  def insert(payload: Goods): Future[Goods] = {
    val goods = if (payload.id.isEmpty) payload.copy(id = GoodsId()) else payload
    db.run((tGoods += goods).transactionally).map(_ => goods)
  }

  def getGoodsList(page: Option[Int], _limit: Option[Int]): Future[PageGoods] = {
    val limit = _limit.getOrElse(HiConstant.DEFAULT_LIMIT)
    val offset = Utils.pageOffset(page.getOrElse(HiConstant.FIRST_PAGE), limit)

    val qItems = tGoods.sortBy(_.id.desc).take(limit).drop(offset)

    val q = for {
      totalItems <- goodsCount.result
      items <- qItems.result
    } yield (totalItems, items)
    db.run(q).map { case (totalItems, content) =>
      // TODO
      val totalPage = Utils.totalPage(totalItems, limit)
      PageGoods(content, page.getOrElse(HiConstant.FIRST_PAGE), limit, totalItems, totalPage)
    }
  }

  private def goodsCount = tGoods.length
}
