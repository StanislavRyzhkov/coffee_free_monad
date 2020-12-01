package company.ryzhkov

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

import cats.implicits._
import company.ryzhkov.interpreters.Context

object Controller extends Context {

  def start: Future[CoffeeInfoWithDiscount] = {
    CoffeeService.getInfo("Americano").foldMap(interpreter2)
  }

}
