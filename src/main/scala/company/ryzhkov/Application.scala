package company.ryzhkov

import java.time.LocalDateTime

import scala.collection.mutable.ListBuffer

object Application extends App {
  println("Hello")

  val now = LocalDateTime.now()

  val list = ListBuffer(1, 2, 3)

  println(list)
}
