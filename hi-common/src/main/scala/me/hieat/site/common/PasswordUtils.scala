package me.hieat.site.common

import org.apache.commons.codec.digest.DigestUtils

import scala.util.Random

/**
 * PasswordUtils
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-09-30.
 */
object PasswordUtils {
  def generatePassword(pwd: String) = {
    val salt = Array.ofDim[Byte](12)
    Random.nextBytes(salt)
    val password = DigestUtils.sha256(salt ++ pwd.getBytes(HiConstant.CHARSET))
    (salt, password)
  }

  def matchPassword(salt: Array[Byte], password: Array[Byte], pwd: Array[Byte]): Boolean = {
    java.util.Arrays.equals(password, DigestUtils.sha256(salt ++ pwd))
  }
}
