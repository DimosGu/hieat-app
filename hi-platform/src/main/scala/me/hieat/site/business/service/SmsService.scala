package me.hieat.site.business.service

import javax.inject.Singleton

import scala.util.Random

/**
 * 短信服务
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-10-01.
 */
@Singleton
class SmsService {

  def createValidCode(phone: String) = {
    (0 until 6).map(_ => Random.nextInt(10)).mkString
  }

}
