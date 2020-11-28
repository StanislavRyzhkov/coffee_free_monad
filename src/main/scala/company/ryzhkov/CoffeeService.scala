package company.ryzhkov

import cats.data.EitherK
import cats.free.Free

object CoffeeService {
  type Eff[A]  = EitherK[CoffeeRepository, Logger, A]
  type Eff2[A] = EitherK[Sender, Eff, A]

  val discount = 0.25

  def getInfo(
    name:       String
  )(implicit C: CoffeeRepositoryOps[Eff2], L: LoggerOps[Eff2], S: SenderOps[Eff2]): Free[Eff2, CoffeeInfo] = {

    import C._, L._, S._

    for {
      coffeeOpt <- getByName(name)
      coffeInfo  = coffeeOpt.map(toCoffeeInfo).getOrElse(throw new RuntimeException("Not found"))
      _         <- sendMessage(s"$coffeInfo was selected")
      _         <- info("Operation finished")
    } yield coffeInfo
  }

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
