package store

case class Sale[T, U](saleId: String, item: T, customer: U)

trait Store[T, U] {
  def capacity: Map[T, Int]
  def currentSales: List[Sale[T, U]]
  def sell(item: T, customer: U): Boolean

  def remaining(): Map[T, Int] = {
    val seatedCount = currentSales.groupBy(_.item).mapValues(_.length)
    for {
      (k, v) <- capacity
    } yield (k, v - seatedCount.getOrElse(k, 0))
  }
}
