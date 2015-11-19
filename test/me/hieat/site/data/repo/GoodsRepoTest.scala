package me.hieat.site.data.repo

import me.hieat.site.common.Utils
import me.hieat.site.data.DbWordPSpec
import me.hieat.site.data.domain.GoodsId
import me.hieat.site.data.model.Goods
import play.api.libs.json.JsObject

/**
 * GoodsRepoTest
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-10-08.
 */
class GoodsRepoTest extends DbWordPSpec {
  val goodsRepo = new GoodsRepo(schema)

  "GoodsRepoTest" should {
    "insert" in {
      val goods = Goods(GoodsId(""), "回锅肉套饭", 15, List("/assets/vendor/img/goodses/001.jpg"), JsObject(Nil), Utils.now())
      goodsRepo.insert(goods).futureValue.name shouldBe goods.name
    }

    "getGoodsList" in {
      goodsRepo.getGoodsList(None, None).futureValue.content should not be empty
    }
  }

}
