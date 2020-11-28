package company.ryzhkov

import cats.free.Free
import company.ryzhkov.CoffeRepository._

object CoffeeService {
  val discount = 0.25

  def getInfo(name: String): Free[CoffeeRepository, Option[CoffeeInfo]] =
    for {
      coffeeOpt   <- getByName(name)
      coffeInfoOpt = coffeeOpt.map(toCoffeeInfo)
    } yield coffeInfoOpt

  def toCoffeeInfo(coffee: Coffee): CoffeeInfo = {
    val Coffee(_, country, name, price, _) = coffee

    CoffeeInfo(
      name = name,
      country = country.name,
      priceBeforeDiscount = price,
      priceAfterDiscount = price * (1 - discount)
    )
  }
}
