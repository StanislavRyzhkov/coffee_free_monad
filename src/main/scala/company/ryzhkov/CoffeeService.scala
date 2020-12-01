package company.ryzhkov

import cats.data.EitherK
import cats.free.Free

object CoffeeService {
  type Eff[A]  = EitherK[CoffeeRepository, Logger, A]
  type Eff2[A] = EitherK[Sender, Eff, A]

  val discount = 0.25

  def getInfo(
    name: String
  )(implicit
    C:    CoffeeRepositoryOps[Eff2],
    L:    LoggerOps[Eff2],
    S:    SenderOps[Eff2]
  ): Free[Eff2, CoffeeInfoWithDiscount] = {

    import C._, L._, S._

    for {
      coffeeInfoOpt        <- getByName(name)
      coffeInfoWithDiscount =
        coffeeInfoOpt.map(toCoffeeInfoWithDiscount).getOrElse(throw new RuntimeException("Not found"))
      _                    <- sendMessage(s"$coffeInfoWithDiscount was selected")
      _                    <- info("Operation finished")
    } yield coffeInfoWithDiscount
  }

  def toCoffeeInfoWithDiscount(coffee: CoffeeInfo): CoffeeInfoWithDiscount = {
    val CoffeeInfo(name, country, priceBeforeDiscount) = coffee

    CoffeeInfoWithDiscount(
      name = name,
      country = country,
      priceBeforeDiscount = priceBeforeDiscount,
      priceAfterDiscount = priceBeforeDiscount * (1 - discount)
    )
  }
}
