package me.hieat.site.common

import org.scalatest.{Matchers, WordSpec}

/**
 * SecurityUtilsTest
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-10-01.
 */
class SecurityUtilsTest extends WordSpec with Matchers {
  val clearText = 234L + HiConstant.OWNER_TOKEN_DELIMITER +
    (Utils.currentTimeSeconds + Settings.cookieSettings.maxAge) + HiConstant.OWNER_TOKEN_DELIMITER +
    "127.0.0.1"

  "SecurityUtilsTest" should {
    "encrypt" in {
      val encryptText = SecurityUtils.encrypt(clearText)
      println(encryptText)

      val recoverText = SecurityUtils.decrypt(encryptText)
      println(recoverText)

      recoverText shouldBe clearText
    }
  }

}
