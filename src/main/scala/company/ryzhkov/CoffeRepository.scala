package company.ryzhkov

import cats.InjectK
import cats.free.Free

sealed trait CoffeeRepository[A]
case object GetAll extends CoffeeRepository[List[Coffee]]
case class GetByName(name: String) extends CoffeeRepository[Option[Coffee]]
case class Save(coffee: Coffee) extends CoffeeRepository[Unit]

class CoffeeRepositoryOps[F[_]](implicit I: InjectK[CoffeeRepository, F]) {
  def getAll: Free[F, List[Coffee]] = Free.inject[CoffeeRepository, F](GetAll)

  def getByName(name: String): Free[F, Option[Coffee]] = Free.inject[CoffeeRepository, F](GetByName(name))

  def save(coffee: Coffee): Free[F, Unit] = Free.inject[CoffeeRepository, F](Save(coffee))
}

object CoffeeRepositoryOps {
  implicit def coffeeRepositoryOps[F[_]](implicit I: InjectK[CoffeeRepository, F]): CoffeeRepositoryOps[F] =
    new CoffeeRepositoryOps[F]
}
