package Lab2
/** Option представляет собой контейнер, который хранит какое-то значение 
  * или не хранит ничего совсем, указывает, вернула ли операция результат или нет. 
  * Это часто используется при поиске значений или когда операции могут потерпеть неудачу, 
  * и вам не важна причина.
 
  * Комбинаторы называются так потому, что они созданы, чтобы объединять результаты. 
  * Результат одной функции часто используется в качестве входных данных для другой.

  * Наиболее распространенным способом, является использование их со стандартными структурами данных.
  * Функциональные комбинаторы `map` и` flatMap` являются контекстно-зависимыми. 
  * map - применяет функцию к каждому элементу из списка, возвращается список с тем же числом элементов.
  * flatMap берет функцию, которая работает с вложенными списками и объединяет результаты.
*/
sealed trait Option[A] {

  def map[B](f: A => B): Option[B]

  /**
  Возвращает Некоторое значение, содержащее результат применения f
  к значению этого параметра, если этот параметр непустой.
  В противном случае не возвращайте Ничего.
   */
  def flatMap[B](f: A => Option[B]): Option[B]

  /**
   * Возвращает результат применения f к значению этого параметра,
   * если этот параметр непустой.
   * Возвращает значение Нет, если этот параметр пуст.
   */
}
case class Some[A](a: A) extends Option[A] {

  def map[B](f: A => B): Option[B] = Some(f(a))
  def flatMap[B](f: A => Option[B]): Option[B] = f(a)
}
case class None[A]()     extends Option[A] {

  def map[B](f: A => B): Option[B] = None()
  def flatMap[B](f: A => Option[B]): Option[B] = None()
}

/** Напишите ваши решения в тестовых функциях.  */
object Compositions {

  // a) Используйте данные функции. Вы можете реализовать свое решение прямо в тестовой функции.
  // Нельзя менять сигнатуры 

  def testCompose[A, B, C, D](f: A => B)
                             (g: B => C)
                             (h: C => D): A => D = h.compose(g).compose(f)

  // b) Напишите функции с использованием `map` и `flatMap`. Вы можете реализовать свое решение прямо в тестовой функции.
  // Нельзя менять сигнатуры 

  def testMapFlatMap[A, B, C, D](f: A => Option[B])
                                (g: B => Option[C])
                                (h: C => D): Option[A] => Option[D] = _ flatMap f flatMap g map h
  /**
   flatmap. Берёт слева нашу переменную. Она у нас имеет тип Option
   Сам тип Option либо хранит переменную в виде типа Some, либо хранит в себе None
   flatMap смотрит налево, если слева находится None() - возвращает тоже None(). Если слева есть переменная в типе Some
   тогда он берёт эту переменную  и делает с ней функцию, которая указана справа.
   Возвращает, соответственно, тоже Some.
   map. Работает аналогично, но не может вернуть нам None(). map вернёт Some(None())
   */

  // c) Напишите функцию используя for. Вы можете реализовать свое решение прямо в тестовой функции.
  // Нельзя менять сигнатуры 

  def testForComprehension[A, B, C, D](f: A => Option[B])
                                      (g: B => Option[C])
                                      (h: C => D): Option[A] => Option[D] = {
    for {
      a <- _
      b <- f(a)
      c <- g(b)
    } yield h(c)
  }
    /**
     * for yield - конструкция в Scale
     * внутри for - список
     * yield берёт из этого списка что мы укажем.
     * for не цикл, а список для формирования формул. В нашем случае формула h(g(f(_)))
  */

  def A(x: Char): Int = x + 5
  def B(x: Int): Double = x * 2 + 1
  def C(x: Double): Int = (x / 2).toInt

  def AA(x: Char): Option[Int] = Some(x + 5)
  def BB(x: Int): Option[Double] = Some(x * 2 + 1)
  def CC(x: Double): Int = (x / 2).toInt


  def main(args: Array[String]): Unit = {

    println("testCompose")
    println(testCompose(A)(B)(C)(' ')) // ascii of [space] is 32

    println("testMapFlatMap")
    println(testMapFlatMap(AA)(BB)(CC)(Some(' ')))
    println(testMapFlatMap(AA)((x: Int) => None())(CC)(Some(' ')))

    println("testForComprehension")
    println(testForComprehension(AA)(BB)(CC)(Some(' ')))
    println(testForComprehension(AA)(BB)((x: Double) => None())(Some(' ')))
  }
}
