package me.hieat.site.business.service

import javax.inject.{Inject, Singleton}

import me.hieat.site.data.domain.PageGoods
import me.hieat.site.data.repo.{GoodsRepo, Schema}
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.Future

/**
 * 商品服务
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-10-06.
 */
@Singleton
class GoodsService @Inject()(schema: Schema) {
  def getGoodsList(page: Option[Int], limit: Option[Int]): Future[PageGoods] = {
    goodsRepo.getGoodsList(page, limit)
  }

  val goodsRepo = new GoodsRepo(schema)
}
