package Lab2



sealed trait List[A]
case class Cons[A](head: A, tail: List[A]) extends List[A]//содержит голову и хвост
case class Nil[A]() extends List[A]//пустой

/** Напишите свои решения в виде функций. */
object RecursiveData {

  // a) Реализуйте функцию, определяющую является ли пустым `List[Int]`.
  def listIsEmpty(list: List[Int]): Boolean = list == Nil()


  // используйте функцию из пункта (a) здесь, не изменяйте сигнатуру
  def testListIntEmpty(list: List[Int]): Boolean = listIsEmpty(list)

  // b) Реализуйте функцию, которая получает head `List[Int]`или возвращает -1 в случае если он пустой.
  def listIsHead(list: List[Int]): Int = list match {
    case list: Cons[Int] => list.head
    case _ => -1
  }


  // используйте функцию из пункта (б) здесь, не изменяйте сигнатуру
  def testListIntHead(list: List[Int]): Int = listIsHead(list)

  // c) Можно ли изменить `List[A]` так чтобы гарантировать что он не является пустым?
  sealed trait ListNotEmpty[A]
  case class ConsNotEmpty[A](head: A, tail: ListNotEmpty[A]) extends ListNotEmpty[A]
  case class End[A](head: A) extends ListNotEmpty[A]

  /* d) Реализуйте универсальное дерево (Tree) которое хранит значения в виде листьев и состоит из:
   *      node - левое и правое дерево (Tree)
   *      leaf - переменная типа A
   */
  sealed trait Tree[A]
  case class Node[A](left: Tree[A], right: Tree[A]) extends Tree[A]
  case class Leaf[A](head: A) extends Tree[A]




  def main(args: Array[String]): Unit = {
    var list1: List[Int] = Nil()
    var list2: List[Int] = Cons(4, Cons(1, Nil()))

    println("Tree")
    println(testListIntEmpty(list1))
    println(testListIntEmpty(list2))
  }
}
