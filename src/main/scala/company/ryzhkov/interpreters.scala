package company.ryzhkov

import cats.Id
import cats.~>

object CoffeeRepositoryInterpreter extends (CoffeeRepository ~> Id) {
  val country = Country(
    id = Some(CountryId(1)),
    name = "USA"
  )

  var repository: Map[String, Coffee] = Map(
    "expresso" -> Coffee(Some(CoffeeId(1)), country, "expresso", 1.00, true)
  )

  def apply[A](cr: CoffeeRepository[A]): cats.Id[A] = {
    cr match {
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

object LoggerInterpreter extends (Logger ~> Id) {
  def apply[A](log: Logger[A]): cats.Id[A] =
    log match {
      case Info(msg) => println(msg)
    }
}

object SenderInterpreter extends (Sender ~> Id) {
  def apply[A](s: Sender[A]): cats.Id[A] =
    s match {
      case SendMessage(msg) => println(s"CATCH: $msg")
    }
}
