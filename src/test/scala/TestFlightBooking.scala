import Main.{findAndBook, findSeat, findSeatInFlight}
import flight.{Flight, LuxurySeat, Person, SingleSeat, SpecialSeat}
import org.scalatest.FunSuite
import store.Sale


class TestFlightBooking extends FunSuite {
  test("test findSeatInFlight") {
    assert(findSeatInFlight(new Flight("a", Map(SingleSeat -> 8)), Person("a", 80)) contains SingleSeat)
    assert(findSeatInFlight(new Flight("a", Map(SingleSeat -> 8)), Person("a", 120)).isEmpty)
    assert(findSeatInFlight(new Flight("a", Map(SingleSeat -> 8, LuxurySeat -> 1)), Person("a", 120)) contains LuxurySeat)
  }

  test("test findSeat") {
    val flights = List(
      new Flight("a", Map(SingleSeat -> 8)),
      new Flight("a", Map(SingleSeat -> 5, LuxurySeat -> 2, new SpecialSeat(150) -> 2))
    )
    assert(findSeat(flights, Person("a", 45)) == Some(flights(0), SingleSeat))
    assert(findSeat(flights, Person("a", 115)) == Some(flights(1), LuxurySeat))
    assert(findSeat(flights, Person("a", 150)) == Some(flights(1), new SpecialSeat(150)))
    assert(findSeat(flights, Person("a", 151)).isEmpty)
  }

  test("test seating") {
    val flights = List(
      new Flight("a", Map(SingleSeat -> 8)),
      new Flight("a", Map(SingleSeat -> 5, LuxurySeat -> 2, new SpecialSeat(150) -> 2))
    )

    val expectedRemainingPeople = List(
      Person("Brian", 145),
      Person("Daniel", 149),
      Person("Gerald", 146),
      Person("Harley", 130),
      Person("Judy", 104),
      Person("Karen", 67),
      Person("Kathy", 114),
      Person("Lisa", 64)
    )

    val remainingPeople = Person.people.filter(p => !findAndBook(flights, p))
    assert(remainingPeople == expectedRemainingPeople)
    val seating0 = List(
      Sale("Single-1", SingleSeat, Person("Bruce", 56)),
      Sale("Single-2", SingleSeat, Person("Barry", 55)),
      Sale("Single-3", SingleSeat, Person("Ana", 74)),
      Sale("Single-4", SingleSeat, Person("Amanda", 72)),
      Sale("Single-5", SingleSeat, Person("Albert", 45)),
      Sale("Single-6", SingleSeat, Person("Addison", 51)),
      Sale("Single-7", SingleSeat, Person("Adam", 63)),
      Sale("Single-8", SingleSeat, Person("Abraham", 62))
    )
    assert(flights(0).currentSales == seating0)

    val seating1 = List(
      Sale("Single-1",  SingleSeat, Person("Jessica", 53)),
      Sale("Single-2",  SingleSeat, Person("Jenifer", 70)),
      Sale("Single-3",  SingleSeat, Person("Emily", 71)),
      Sale("Lux-1",  LuxurySeat, Person("Dona", 94)),
      Sale("Single-4",  SingleSeat, Person("David", 58)),
      Sale("Single-5",  SingleSeat, Person("Carl", 75)),
      Sale("Spl-1",  new SpecialSeat(150), new Person("Blake", 126)),
      Sale("Lux-2",  LuxurySeat, Person("Beth", 82)),
      Sale("Spl-2",  new SpecialSeat(150), new Person("Alice", 145))
    )
    assert(flights(1).currentSales == seating1)
  }
}