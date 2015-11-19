package me.hieat.site.common

import me.hieat.site.common.exception.HiInternalServerException

/**
 * 安全相关
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-10-01.
 */
object SecurityUtils {
  private val des = new TripleDesUtils(Settings.security.key)

  def encrypt(clearText: String): String = {
    try {
      des.encrypt(clearText)
    } catch {
      case e: Exception =>
        throw HiInternalServerException(e.getLocalizedMessage, cause = e)
    }
  }

  def decrypt(encryptedText: String): String = {
    try {
      des.decrypt(encryptedText)
    } catch {
      case e: Exception =>
        throw HiInternalServerException(e.getLocalizedMessage, cause = e)
    }
  }
}
