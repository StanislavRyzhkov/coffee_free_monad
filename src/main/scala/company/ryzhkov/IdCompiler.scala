package company.ryzhkov

import cats.Id
import cats.~>

object IdCompiler {
  val country = Country(
    id = Some(CountryId(1)),
    name = "USA"
  )

  var repository: Map[String, Coffee] = Map(
    "expresso" -> Coffee(Some(CoffeeId(1)), country, "expresso", 1.00, true)
  )

  def idCompiler: CoffeeRepository ~> Id =
    new (CoffeeRepository ~> Id) {
      def apply[A](coffeeRepository: CoffeeRepository[A]): Id[A] = {
        coffeeRepository match {
          case GetAll          =>
            repository.values.toList

          case GetByName(name) =>
            repository.get(name)

          case Save(coffee)    =>
            repository = repository + (coffee.name -> coffee)
            ()
        }
      }
    }
}
