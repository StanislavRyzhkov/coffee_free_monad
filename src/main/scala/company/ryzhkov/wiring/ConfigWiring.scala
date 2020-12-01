package company.ryzhkov.wiring

import scala.concurrent.ExecutionContext.global

import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import slick.jdbc.PostgresProfile.api._

trait ConfigWiring {
  lazy val rootConfig: Config = ConfigFactory.load()

  lazy implicit val db = Database.forConfig("mydb")

  lazy implicit val ec = global

}

object ConfigWiring extends ConfigWiring
