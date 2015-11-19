package me.hieat.site.common

import com.typesafe.config.{Config, ConfigFactory}
import scala.collection.JavaConverters._

/**
 * Settings
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-09-30.
 */
object Settings {
  val security = {
    val conf = ConfigFactory.load().getConfig("hieat.security")
    SecuritySetting(getString(conf, "key").get)
  }

  val cookieSettings = {
    val conf = ConfigFactory.load().getConfig("hieat.cookie")
    CookieSetting(getString(conf, "domain"),
      getString(conf, "path").getOrElse("/"),
      getBoolean(conf, "httpOnly").getOrElse(false),
      getInt(conf, "maxAge").getOrElse(1800))
  }

  val filterAfterToken = {
    val conf = ConfigFactory.load().getConfig("hieat.filter-after-token")
    FilterAfterToken(getBoolean(conf, "match-all").getOrElse(false),
      getStringList(conf, "paths"),
      getStringList(conf, "exclude-paths"))
  }

  private def getStringList(conf: Config, name: String): Seq[String] = try {
    conf.getStringList(name).asScala
  } catch {
    case _: Exception =>
      Nil
  }

  private def getString(conf: Config, name: String) = try {
    Some(conf.getString(name))
  } catch {
    case _: Exception =>
      None
  }

  private def getBoolean(conf: Config, name: String) = try {
    Some(conf.getBoolean(name))
  } catch {
    case _: Exception =>
      None
  }

  private def getInt(conf: Config, name: String) = try {
    Some(conf.getInt(name))
  } catch {
    case _: Exception =>
      None
  }
}

case class CookieSetting(domain: Option[String], path: String, httpOnly: Boolean, maxAge: Int)

case class SecuritySetting(key: String)

case class FilterAfterToken(matchAll: Boolean, paths: Seq[String], excludePaths: Seq[String])
