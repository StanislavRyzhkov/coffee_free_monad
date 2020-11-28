package company.ryzhkov

import cats.free.Free

sealed trait CoffeeService[A]
case class GetPremiumInfo(name: String) extends CoffeeService[CoffeeInfo]

object CoffeeService {
  def getPremiumInfo(name: String): Free[CoffeeService, CoffeeInfo] = Free.liftF(GetPremiumInfo(name))
}
