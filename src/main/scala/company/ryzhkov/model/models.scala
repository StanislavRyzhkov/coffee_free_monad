package company.ryzhkov.model

import company.ryzhkov.CoffeeRecord
import company.ryzhkov.CountryRecord
import slick.jdbc.PostgresProfile.api._

object DBModels {
  val models = new CoffeeModel with CountryModel {}
}

trait CoffeeModel {
  class CoffeeTable(tag: Tag) extends Table[CoffeeRecord](tag, "coffees") {
    def id        = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def countryId = column[Long]("country_id")
    def name      = column[String]("name")
    def price     = column[BigDecimal]("price")
    def isPremium = column[Boolean]("is_premium")
    def *         = (id, countryId, name, price, isPremium) <> (CoffeeRecord.tupled, CoffeeRecord.unapply _)
  }

  val coffees = TableQuery[CoffeeTable]
}

trait CountryModel {
  class CountryTable(tag: Tag) extends Table[CountryRecord](tag, "countries") {
    def id   = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def *    = (id, name) <> (CountryRecord.tupled, CountryRecord.unapply _)
  }

  val countries = TableQuery[CountryTable]
}
