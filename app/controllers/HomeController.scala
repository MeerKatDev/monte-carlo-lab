package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import utils._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject() extends Controller {

  def index = Action {
    val grid = new Grid(20)
    for(i <- 0 to 10) grid.createWalkMonitorSeed
    Ok(views.html.index(grid.toString))
  }

}
