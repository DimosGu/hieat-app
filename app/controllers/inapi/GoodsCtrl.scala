package controllers.inapi

import javax.inject.{Inject, Singleton}

import me.hieat.site.business.service.GoodsService
import play.api.libs.json.Json
import play.api.mvc.Action
import utils.MyController

/**
 * 商品
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-10-06.
 */
@Singleton
class GoodsCtrl @Inject()(goodsService: GoodsService) extends MyController {

  def getGoodsList = Action.async { request =>
    val page = request.getQueryString("page").map(_.toInt)
    val limit = request.getQueryString("limit").map(_.toInt)
    goodsService.getGoodsList(page, limit).map { result =>
      Ok(Json.toJson(result))
    }
  }

}
