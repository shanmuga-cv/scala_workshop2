package traits_demo
import scala.util.Random

trait Creature {
  /**
    * No implicit constructors as in c++
    * No explicit constructors as in java abstract classes super(arguments) as in
    *
    *     >>> you CAN NOT create instance of trait. <<<
    *
    * as opposite to Java interface you can actually implement some methods in traits
    *
    * trait may contains attributes
    *
    *
    * */

  private val maxOccupiedCells = 9    // Have to initialize private attribute
  protected val occupiedCells: Int    // But may left protected to be initialized by concrete implementation

  /**
    *   private[this] - available for this object
    *   private[Creature] - available for all objects of similar classes
    *
    * */

  var hitPoints: Int
  def lifeRemaining(): Int = hitPoints

  def move()
  def attack()
}

trait Pirate extends Creature {   // mix them in!
  def move()
  def attack(): Unit = println("Yohoho I am attacking")
}

trait Zombi extends Creature {
  def attack(): Unit = println("Roaaar...")
}

/**
  * multiple inheritance is possible as opposite to abstract
  *
  * Classical diamond problem: what will happen if we call attack method?
  *
  * [error] (Note: this can be resolved by declaring an override in class DeadPirate.)
  * [error] class DeadPirate(_hitPoints: Int) extends Pirate with Zombi  :(
  * */
class DeadPirate(var hitPoints: Int, val occupiedCells: Int)        // Have to implement abstract attribute !
  extends Pirate with Zombi {
  def move(): Unit = Random.nextInt(13)     // Have to implement abstract method    !

  override def attack(): Unit = super.attack()  // who is our super??? For this case I like explicit override classifer
}


object TraitsDemo extends App {
  val smth = new DeadPirate(13, 10)
  smth.attack()
}
