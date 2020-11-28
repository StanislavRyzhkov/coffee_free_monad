package company.ryzhkov

object Application extends App {
  val res = CoffeeService.getInfo("expresso").foldMap(IdCompiler.idCompiler)

  res match {
    case Some(value) => println(value)
    case _           => println("No coffee")
  }
}
