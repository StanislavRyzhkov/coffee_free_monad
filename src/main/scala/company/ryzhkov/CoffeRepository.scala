package company.ryzhkov

import cats.InjectK
import cats.free.Free

sealed trait CoffeeRepository[A]
case class GetByName(name: String) extends CoffeeRepository[Option[CoffeeInfo]]

class CoffeeRepositoryOps[F[_]](implicit I: InjectK[CoffeeRepository, F]) {
  def getByName(name: String): Free[F, Option[CoffeeInfo]] = Free.inject[CoffeeRepository, F](GetByName(name))
}

object CoffeeRepositoryOps {
  implicit def coffeeRepositoryOps[F[_]](implicit I: InjectK[CoffeeRepository, F]): CoffeeRepositoryOps[F] =
    new CoffeeRepositoryOps[F]
}
