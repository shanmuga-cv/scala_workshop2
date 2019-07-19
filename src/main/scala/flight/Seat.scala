package flight

sealed class Seat(val seatType: String, val maxWeight: Int) {
  override def toString = f"${this.getClass.getName}<seatType=$seatType, maxWeight=$maxWeight>"

  override def equals(other: Any): Boolean = other match {
    case other: Seat => this.seatType == other.seatType && this.maxWeight == other.maxWeight
    case _ => false
  }
}

object SingleSeat extends Seat("Single", 80)
object LuxurySeat extends Seat("Lux", 120)
class SpecialSeat(maxWeight: Int) extends Seat("Spl", maxWeight)
