package company.ryzhkov

import cats.Id
import cats.~>
import company.ryzhkov.CoffeeService.Eff
import company.ryzhkov.CoffeeService.Eff2

object Application extends App {

  val inter1: Eff ~> Id  = CoffeeRepositoryInterpreter or LoggerInterpreter
  val inter2: Eff2 ~> Id = SenderInterpreter or inter1

  val res = CoffeeService.getInfo("expresso").foldMap(inter2)

  println(res)
}
