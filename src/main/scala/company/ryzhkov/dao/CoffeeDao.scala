package company.ryzhkov.dao

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

import company.ryzhkov.CoffeeInfo
import company.ryzhkov.model.DBModels.models
import slick.jdbc.PostgresProfile.api._

class CoffeeDao(implicit ex: ExecutionContext, db: Database) {

  def getByName(name: String): Future[Option[CoffeeInfo]] = {
    val res = (for {
      coffee  <- models.coffees.filter(_.name === name)
      country <- models.countries if (coffee.countryId === country.id)
    } yield (coffee.name, country.name, coffee.price)).result.headOption

    db.run(res).map(_.map(CoffeeInfo.tupled))
  }
}
