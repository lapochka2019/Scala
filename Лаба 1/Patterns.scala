package exercise1

/** Напишите решение в виде функции. 
  * 
  * Синтаксис:
  *   val a: Int = ???
  * 
  *   a match {
  *     case 0 => true
  *     case _ => false
  *   }
  */
object PatternMatching {

  sealed trait Hand
  case object Rock    extends Hand
  case object Paper   extends Hand
  case object Scissor extends Hand

  sealed trait Result
  case object Win  extends Result
  case object Lose extends Result
  case object Draw extends Result

  sealed trait Food
  case object Meat       extends Food
  case object Vegetables extends Food
  case object Plants     extends Food

  trait Animal {

    val name: String
    var food: Food
  }
  case class Mammal(name: String, var food: Food, weight: Int) extends Animal
  case class Fish(name: String, var food: Food)                extends Animal
  case class Bird(name: String, var food: Food)                extends Animal



  /* a) Напишите функцию, которая ставит в соответствие числу строку слудеющим образом:
   * Если:
   *     1 => "it is one"
   *     2 => "it is two"
   *     3 => "it is three"
   *     иначе => "what's that"
   */
  def A (n:Int): String = n match {
    case 1 => "it is one"
    case 2 => "it is two"
    case 3 => "it is three"
    case _ => "what's that"
  }

  // примените вашу функцию из пункта (a) здесь, не изменяя сигнатуру
  def testIntToString(value: Int): String = A(value)

  /* b) Напишите функцию которая возвращает true если переменная `value` принимает значение:
   *     "max" или "Max
   *     "moritz" или "Moritz"
   */
def B (s: String):Boolean = s match {
  case "max"|"Max"|"moritz"|"Moritz"=>true
  case _=> false
}


  // примените функции из пункта (b) здесь, не изменяя сигнатуру
  def testIsMaxAndMoritz(value: String): Boolean = B(value)

  // c) Напишите функцию проверки является ли `value` четным 
def C (n:Int):Boolean = n match {
  case n => n%2==0
  case _=> false
}


  // примените функции из пункта (c) здесь, не изменяя сигнатуру
  def testIsEven(value: Int): Boolean = C(value)


  
  /* d) Напишите функцию, моделирующую игру в Камень ножницы бумага 
   *     1. камень побеждает ножницы
   *     2. ножницы побеждают бумагу
   *     3. бумага побеждает камень
   *    Выиграет ли игрок `a`?
   *///Scissor, Paper, Rock
def D (a: Hand, b: Hand): Result = (a,b) match {
  case (Rock,Scissor)|(Scissor,Paper)|(Paper,Rock)=>Win
  case (Rock,Rock)|(Scissor,Scissor)|(Paper,Paper)=>Draw
  case _=>Lose
}

  // примените вашу функцию из пункта (d) здесь, не изменяя сигнатуру
  def testWinsA(a: Hand, b: Hand): Result = D(a,b)



  // Примечание: используйте определение Animals

  // e) Верните вес (weight: Int) объекта Mammal, иначе верните -1.
def e (animal: Animal):Int = animal match {
  case animal: Mammal => animal.weight
  case _=> -1
}


  // примените функцию из пункта (e) здесь, не изменяйте сигнатуру
  def testExtractMammalWeight(animal: Animal): Int = e(animal)


  // f) Измените поле еда объектов классов Fish и Birds на Plants, класс Mammal оставьте неизмененным.
def f ( animal: Animal):Animal = animal match {
  case animal: Mammal=>animal
  case _=> animal.food = Plants
    animal
}


  // примените функцию из пункта (f) здесь, не изменяйте сигнатуру
  def testUpdateFood(animal: Animal): Animal = f(animal)

  def main(args: Array[String]): Unit = {
    println("A")
    println(A(1))

    println("B")
    println(B("nas"))
    println(B("max"))

    println("C")
    println(C(4))

    println("D")
    println(D(Scissor,Rock))

    println("e")
    val mammal = new Mammal("M", Meat, 50)
    val fish = new Fish("F", Vegetables)
    println(e(mammal))
    println(e(fish))

    println("f")
    println(f(mammal).food)
    println(f(fish).food)

  }
}
