package me.hieat.site.business.service

import javax.inject.Singleton

import com.redis._

/**
 * Redis 访问
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-10-01.
 */
@Singleton
class RedisService() {
//  def setSession(sessionKey: String, value: String, duration: Long) = clients.withClient { r =>
//    r.psetex(sessionKey, duration, value)
//  }

//  def clearSession(sessionKey: String) = clients.withClient { r =>
//    r.del(sessionKey)
//  }

  def getCaptcha(key: String): Option[String] = clients.withClient { r =>
    r.get(key)
  }

  def saveCaptcha(key: String, token: String, duration: Long = 60000) = clients.withClient { r =>
    r.psetex(key, duration, token)
  }

  private val clients = new RedisClientPool("localhost", 6379)
}
