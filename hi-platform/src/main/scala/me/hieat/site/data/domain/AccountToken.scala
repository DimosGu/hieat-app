package me.hieat.site.data.domain

import me.hieat.site.common.{HiConstant, Settings, Utils}
import org.apache.commons.lang3.StringUtils

/**
 * OwnerToken
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-10-01.
 */
case class AccountToken(accountId: AccountId, timestamp: Long, ip: String) {
  assert(timestamp > 0L)
  assert(StringUtils.isNoneBlank(ip))

  override def toString: String = {
    accountId.value + HiConstant.OWNER_TOKEN_DELIMITER +
      timestamp + HiConstant.OWNER_TOKEN_DELIMITER +
      ip + HiConstant.OWNER_TOKEN_DELIMITER
  }

  def hasExpired = (timestamp + Settings.cookieSettings.maxAge) < Utils.currentTimeSeconds

  def isValid = !hasExpired
}

object AccountToken {
  def apply(s: String): AccountToken = {
    val Array(accountId, timestamp, ip) = s.split(HiConstant.OWNER_TOKEN_DELIMITER)
    AccountToken(accountId.toLong, timestamp.toLong, ip)
  }
}