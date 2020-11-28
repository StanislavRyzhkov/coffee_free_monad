package company.ryzhkov

import cats.free.Free

sealed trait CoffeeRepository[A]
case object GetAll extends CoffeeRepository[List[Coffee]]
case class GetByName(name: String) extends CoffeeRepository[Coffee]
case class Save(coffee: Coffee) extends CoffeeRepository[Unit]

object CoffeRepository {
  def getAll(): Free[CoffeeRepository, List[Coffee]] = Free.liftF(GetAll)

  def getByName(name: String): Free[CoffeeRepository, Coffee] = Free.liftF(GetByName(name))

  def save(coffee: Coffee): Free[CoffeeRepository, Unit] = Free.liftF(Save(coffee))
}
