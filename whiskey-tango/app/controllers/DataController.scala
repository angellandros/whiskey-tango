package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class DataController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def cities(from: String, until: String, city: String) = Action { implicit request: Request[AnyContent] =>
    val m = Map("from" -> from, "until" -> until, "city" -> city)
    Ok(Json.toJson(m))
  }
}