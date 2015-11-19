package me.hieat.site.config

import com.typesafe.scalalogging.LazyLogging
import play.api.ApplicationLoader.Context
import play.api.Configuration
import play.api.inject.guice.{GuiceApplicationBuilder, GuiceApplicationLoader}
import play.api.libs.json.Json

/**
 * HieatApplicationLoader
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-10-07.
 */
class HieatApplicationLoader extends GuiceApplicationLoader with LazyLogging {
  override protected def builder(context: Context): GuiceApplicationBuilder = {
    val extra = generateExtraConfiguration(context)

    //    logger.debug("extra: " + extra.getString("hieat.assets.js.app"))
    //    logger.info(context.environment.toString)

    initialBuilder
      .in(context.environment)
      .loadConfig(extra ++ context.initialConfiguration)
      .overrides(overrides(context): _*)
  }

  private def generateExtraConfiguration(context: Context) = {
    val inAssetsStat = context.environment.classLoader.getResourceAsStream("assets-stats.json")
    val assetsStatJson = Json.parse(inAssetsStat)
    inAssetsStat.close()

    val app = assetsStatJson.\("app").as[String]
    val sign = assetsStatJson.\("sign").as[String]
    val common = assetsStatJson.\("common").as[String]

    Configuration("hieat.assets.js.app" -> app,
      "hieat.assets.js.sign" -> sign,
      "hieat.assets.js.common" -> common)
  }

}
