package company.ryzhkov
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Failure
import scala.util.Success

object Application extends App {

  Controller.start.onComplete {
    case Success(value)     => println(value)
    case Failure(exception) => println(exception.getMessage())
  }
}
