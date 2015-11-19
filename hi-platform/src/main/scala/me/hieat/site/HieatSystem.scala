package me.hieat.site

import javax.inject.{Inject, Singleton}

import com.typesafe.scalalogging.StrictLogging
import me.hieat.site.data.repo.Schema
import play.api.inject.ApplicationLifecycle

import scala.concurrent.Future

/**
 * System Manager
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-10-02.
 */
@Singleton
class HieatSystem @Inject()(lifecycle: ApplicationLifecycle,
                            schema: Schema) extends StrictLogging {

  logger.info("Hieat System startup")

  lifecycle.addStopHook(() => Future.successful {
    logger.info("close db ....")
    schema.db.close()
  })
}
