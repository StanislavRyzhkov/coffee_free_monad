package company.ryzhkov

case class CoffeeRecord(id: Long = 0, countryId: Long, name: String, price: BigDecimal, isPremium: Boolean)

case class CountryRecord(id: Long = 0, name: String)

case class CoffeeInfo(name: String, country: String, priceBeforeDiscount: BigDecimal)

case class CoffeeInfoWithDiscount(
  name:                String,
  country:             String,
  priceBeforeDiscount: BigDecimal,
  priceAfterDiscount:  BigDecimal
)

case class User(id: Long, username: String, email: String)
