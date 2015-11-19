package me.hieat.site.common

import java.nio.charset.Charset

import org.bson.types.ObjectId

/**
 * 全局常量
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-09-28.
 */
object HiConstant {
  val FIRST_PAGE = 1
  val DEFAULT_LIMIT = 12
  val OWNER_SESSION_PREFIX = "o-s-p:"
  val OWNER_TOKEN_DELIMITER = ";"
  val OWNER_TOKEN_COOKIE_NAME = "h-o-t"
  val CHARSET = Charset.forName("UTF-8")
  val CAPTCHA_KEY = "CAPTCHA_KEY"
  val OID_LENGTH = ObjectId.get().toString.length
}
