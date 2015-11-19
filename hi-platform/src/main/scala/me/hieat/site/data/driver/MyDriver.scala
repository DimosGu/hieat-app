package me.hieat.site.data.driver

import com.github.tminglei.slickpg._
import com.github.tminglei.slickpg.utils.SimpleArrayUtils
import me.hieat.site.common.HiConstant
import me.hieat.site.common.enums.Gender
import me.hieat.site.data.domain._
import play.api.libs.json.{JsValue, Json}
import slick.driver.JdbcDriver

trait MyColumnTypes {
  this: JdbcDriver =>

  trait MyColumnsAPI {
    self: API =>
    implicit val __accountIdColumnType = MappedColumnType.base[AccountId, Long]({ o => o.value }, { l => AccountId(l) })
    implicit val __addressIdColumnType = MappedColumnType.base[AddressId, String]({ o => o.value }, { s => AddressId(s) })
    implicit val __orderIdColumnType = MappedColumnType.base[OrderId, String]({ o => o.value }, { s => OrderId(s) })
    implicit val __goodsIdColumnType = MappedColumnType.base[GoodsId, String]({ o => o.value }, { s => GoodsId(s) })

    implicit val __genderColumnType = MappedColumnType.base[Gender.Value, String]({ o => o.toString }, { s => Gender.withName(s) })
  }

}

trait MyDriver
  extends ExPostgresDriver
  with PgDate2Support
  with PgHStoreSupport
  with PgPlayJsonSupport
  with PgArraySupport
  //with PgRangeSupport
  //with PgSearchSupport
  //with PgPostGISSupport
  with MyColumnTypes {
  override val pgjson = "jsonb"
  override val api = MyAPI

  object MyAPI
    extends API
    with DateTimeImplicits
    with HStoreImplicits
    with JsonImplicits
    with ArrayImplicits
    //  with RangeImplicits
    //  with SearchImplicits
    //  with PostGISImplicits
    with MyColumnsAPI {
    implicit val strListTypeMapper = new SimpleArrayJdbcType[String]("text").to(_.toList)
    implicit val json4sJsonArrayTypeMapper =
      new AdvancedArrayJdbcType[JsValue](pgjson,
        (s) => SimpleArrayUtils.fromString[JsValue](Json.parse)(s).orNull,
        (v) => SimpleArrayUtils.mkString[JsValue](_.toString())(v)
      ).to(_.toList)

  }

  object SqlTypes {
    val OID = "char(" + HiConstant.OID_LENGTH + ")"
  }

}

object MyDriver extends MyDriver {
}
