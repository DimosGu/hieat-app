package controllers

import javax.inject.Singleton

import play.api.mvc.Action
import utils.MyController

@Singleton
class Application extends MyController {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def sign(file: String) = Action {
    Ok(views.html.sign("hieat"))
  }

  def app(file: String) = Action { implicit request =>
    Ok(views.html.app("hieat"))
  }

}
