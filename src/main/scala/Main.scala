// to cover collections, option, inheritance

import scala.util.Try

object Main {
  def findSeatInFlight(flight: Flight, passenger: Person): Option[Seat] = ???

  def findSeat(flights: List[Flight], passenger: Person): Option[(Flight, Seat)] = ???

  def findAndBook(flights: List[Flight], passenger: Person): Boolean = ???

  def main(args: Array[String]): Unit = {
    val remainingPeople = Person.people.filter(p => !findAndBook(Flight.availableFlights, p))
    println("booking not found for ")
    remainingPeople foreach println
    println
    println

    Flight.availableFlights.foreach(f => {
      println("bookings for flingt " + f)
      f.seating foreach println
      println
      println
    })
  }
}
