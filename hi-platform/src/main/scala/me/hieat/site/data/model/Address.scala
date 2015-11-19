package me.hieat.site.data.model

import java.time.LocalDateTime

import me.hieat.site.data.domain.AddressId
import me.hieat.site.data.driver.MyDriver.api._

/**
 * 送货地址
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-09-28.
 */
case class Address(id: AddressId,
                   title: String,
                   province: String,
                   city: String,
                   county: String,
                   address: String,
                   createdAd: LocalDateTime)

class TableAddress(tag: Tag) extends Table[Address](tag, "t_address") {
  val id = column[AddressId]("id", O.PrimaryKey)
  val title = column[String]("title")
  val province = column[String]("province")
  val city = column[String]("city")
  val county = column[String]("county")
  val address = column[String]("address")
  val createdAt = column[LocalDateTime]("createdAt")

  def * = (id, title, province, city, county, address, createdAt) <>(Address.tupled, Address.unapply)
}
