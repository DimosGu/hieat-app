package me.hieat.site.common

import java.time.LocalDateTime

import org.bson.types.ObjectId

/**
 * 常用工具
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-09-30.
 */
object Utils {
  def oid(): String = ObjectId.get().toString

  def totalPage(totalItems: Int, limit: Int) = {
    require(limit > 0)
    (totalItems + limit) / limit
  }

  def pageOffset(page: Int, limit: Int) = {
    require(page > 0)
    require(limit > 0)
    (page - 1) / limit
  }

  def currentTimeSeconds = System.currentTimeMillis() / 1000

  def now() = LocalDateTime.now()
}
