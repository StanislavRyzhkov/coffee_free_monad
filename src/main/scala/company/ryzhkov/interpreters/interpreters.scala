package company.ryzhkov.interpreters

import scala.concurrent.Future

import cats.~>
import company.ryzhkov.CoffeeRepository
import company.ryzhkov.CoffeeService._
import company.ryzhkov.GetByName
import company.ryzhkov.Info
import company.ryzhkov.Logger
import company.ryzhkov.SendMessage
import company.ryzhkov.Sender
import company.ryzhkov.wiring.DaoWiring

trait Context {
  val coffeInterpreter  = new CoffeeRepositorySlickInterpreter {}
  val senderInterpreter = new SenderInterpreter {}
  val loggerIntertreter = new LoggerInterpreter {}

  val interpreter: Eff ~> Future = coffeInterpreter.or(loggerIntertreter)

  val interpreter2: Eff2 ~> Future = senderInterpreter.or(interpreter)

}

object Context extends Context

trait CoffeeRepositorySlickInterpreter extends (CoffeeRepository ~> Future) with DaoWiring {
  def apply[A](cr: CoffeeRepository[A]): Future[A] = {
    cr match {

      case GetByName(name) =>
        coffeeDao.getByName(name)
    }
  }
}

trait SenderInterpreter extends (Sender ~> Future) {
  def apply[A](s: Sender[A]): Future[A] =
    s match {
      case SendMessage(msg) => Future.successful(println(s"msg sent"))
    }
}

trait LoggerInterpreter extends (Logger ~> Future) {
  def apply[A](log: Logger[A]): Future[A] =
    log match {
      case Info(msg) => Future.successful(println(msg))
    }
}
