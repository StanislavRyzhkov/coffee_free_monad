package company.ryzhkov.wiring

import company.ryzhkov.dao.CoffeeDao

trait DaoWiring extends ConfigWiring {
  implicit lazy val coffeeDao = new CoffeeDao
}

object DaoWiring extends DaoWiring
