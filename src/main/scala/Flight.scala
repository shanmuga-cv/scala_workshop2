class Flight(id: String, private val pool: Map[Seat, Int]) {
  private var seated = List[Seating]()

  def remaining(): Map[Seat, Int] = ???

  def addBooking(seat: Seat, passenger: Person): Boolean = ???

  def seating: List[Seating] = seated

  override def toString: String = s"Flight-$id"
}

object Flight {
  val availableFlights = List(
    new Flight("f1", Map(SingleSeat -> 8)),
    new Flight("f2", Map(SingleSeat -> 5, LuxurySeat -> 2, new SpecialSeat(150) -> 2))
  )
}


case class Seating(seatNumber: String, seatType: Seat, passenger: Person)
