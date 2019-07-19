package flight

import scala.io.Source

case class Person(name: String, weight: Int)
object Person{
  def people: List[Person] = {
    Source.fromFile("people.csv")
      .getLines()
      .map(_.split(","))
      .map(x => new Person(x(0), x(1).toInt))
      .toList
  }
}
