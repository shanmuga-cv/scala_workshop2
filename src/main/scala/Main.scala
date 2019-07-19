import flight.{Flight, Person, Seat}

import scala.util.Try

object Main {
  def findSeatInFlight(flight: Flight, passenger: Person): Option[Seat] = {
    val eligibleSeats = flight.remaining()
      .withFilter(_._1.maxWeight >= passenger.weight)
      .withFilter(_._2 > 0)
      .map(_._1)
    Try {
      eligibleSeats.minBy(_.maxWeight)
    }.toOption
  }

  def findSeat(flights: List[Flight], passenger: Person): Option[(Flight, Seat)] = {
    val eligibleFlighSeats = flights.flatMap(f => findSeatInFlight(f, passenger).map((f, _)))
    Try {
      eligibleFlighSeats.minBy(_._2.maxWeight)
    }.toOption
  }

  def findAndBook(flights: List[Flight], passenger: Person): Boolean = {
    val seatFound = findSeat(flights, passenger)
    seatFound match {
      case Some((flight: Flight, seat: Seat)) => {
        flight.sell(seat, passenger)
        true
      }
      case None => false
    }
  }

  def main(args: Array[String]): Unit = {
    val remainingPeople = Person.people.filter(p => !findAndBook(Flight.availableFlights, p))
    println("booking not found for ")
    remainingPeople foreach println
    println
    println

    Flight.availableFlights.foreach(f => {
      println("bookings for flingt " + f)
      f.currentSales foreach println
      println
      println
    })
  }
}
