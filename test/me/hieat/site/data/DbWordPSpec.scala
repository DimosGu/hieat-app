package me.hieat.site.data

import java.util.concurrent.TimeoutException

import akka.actor.ActorSystem
import me.hieat.site.data.repo.Schema
import org.scalatest._
import org.scalatest.concurrent.ScalaFutures

import scala.concurrent.duration._

/**
 * 测试封装
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-09-30.
 */
abstract class DbWordPSpec
  extends WordSpec with Matchers with OptionValues with EitherValues with ScalaFutures
  with BeforeAndAfterAll {

  implicit val system = ActorSystem("hieat-test")

  implicit def execution = system.dispatcher

  val schema = new Schema()

  override protected def afterAll(): Unit = {
    schema.db.close()
    try {
      system.shutdown()
      system.awaitTermination(2.seconds)
    } catch {
      case e: TimeoutException =>
        e.printStackTrace()
        system.shutdown()
    }
  }
}
