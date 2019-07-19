package flight

import store.{Sale, Store}

class Flight(id: String, override val capacity: Map[Seat, Int]) extends Store[Seat, Person] {
  private var seated = List[Sale[Seat, Person]]()

  def sell(seat: Seat, passenger: Person): Boolean = ???

  override def currentSales: List[Sale[Seat, Person]] = seated

  override def toString: String = s"Flight-$id"
}

object Flight {
  val availableFlights = List(
    new Flight("f1", Map(SingleSeat -> 8)),
    new Flight("f2", Map(SingleSeat -> 5, LuxurySeat -> 2, new SpecialSeat(150) -> 2))
  )
}


