package company.ryzhkov

import cats.InjectK
import cats.data.EitherK
import cats.free.Free

object Application extends App {
  type CoffeeApp[A] = EitherK[CoffeeRepository, CoffeeService, A]

  class Repository[F[_]](implicit I: InjectK[CoffeeRepository, F]) {

    def getAll(): Free[F, List[Coffee]] = Free.inject[CoffeeRepository, F](GetAll)

    def getByName(name: String): Free[F, Coffee] = Free.inject[CoffeeRepository, F](GetByName(name))

    def save(coffee: Coffee): Free[F, Unit] = Free.inject[CoffeeRepository, F](Save(coffee))
  }

  object Repository {
    implicit def repository[F[_]](implicit I: InjectK[CoffeeRepository, F]): Repository[F] = new Repository[F]
  }

  class Service[F[_]](implicit I: InjectK[CoffeeService, F]) {
    def getPremiumInfo(name: String): Free[F, CoffeeInfo] = Free.inject[CoffeeService, F](GetPremiumInfo(name))
  }

  object Service {
    implicit def service[F[_]](implicit I: InjectK[CoffeeService, F]): Service[F] = new Service[F]
  }

  def program(implicit R: Repository[CoffeeApp], S: Service[CoffeeApp]) = ???

}
