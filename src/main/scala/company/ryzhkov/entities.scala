package company.ryzhkov

case class CoffeeId(value: Long) extends AnyVal

case class Coffee(id: Option[CoffeeId], country: Country, name: String, price: BigDecimal, isPremium: Boolean)

case class CountryId(value: Long) extends AnyVal

case class Country(id: Option[CountryId], name: String)

case class CoffeeInfo(name: String, country: String, priceBeforeDiscount: BigDecimal, priceAfterDiscount: BigDecimal)

case class UserId(value: Long) extends AnyVal

case class User(id: UserId, username: String, email: String)
