package me.hieat.site.data.repo

import javax.inject.Singleton

import me.hieat.site.data.driver.MyDriver.api._
import me.hieat.site.data.model._

/**
 * 数据库schema
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-09-28.
 */

@Singleton
class Schema() {
  val db = Database.forConfig("hieat.db")

  def tCredential = TableQuery[TableCredential]

  def tUser = TableQuery[TableUser]

  def tAddress = TableQuery[TableAddress]

  def tGoods = TableQuery[TableGoods]

  def tOrder = TableQuery[TableOrder]

  def tOrderItem = TableQuery[TableOrderItem]

  def schemas =
    tCredential.schema ++
      tUser.schema ++
      tAddress.schema ++
      tGoods.schema ++
      tOrder.schema ++
      tOrderItem.schema
}
