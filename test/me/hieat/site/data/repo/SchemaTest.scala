package me.hieat.site.data.repo

import me.hieat.site.data.DbWordPSpec
import me.hieat.site.data.driver.MyDriver.api._

/**
 * SchemaTest
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-09-30.
 */
class SchemaTest extends DbWordPSpec {
  "SchemaTest" should {
    "create statements" in {
      schema.schemas.createStatements.foreach(println)
    }

    "create ddls" in {
      schema.db.run(schema.schemas.create.transactionally)
    }
  }
}
