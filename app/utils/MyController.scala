package utils

import me.hieat.site.data.MyJsonImplicits
import play.api.libs.concurrent.Execution
import play.api.mvc.Controller

/**
 * MyController
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-09-30.
 */
trait MyController extends Controller with MyJsonImplicits {
  implicit def defaultContext = Execution.Implicits.defaultContext
}
