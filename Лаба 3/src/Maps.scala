

/** Напишите вашу реализацию в тестовые функции.
  * 
  * https://docs.scala-lang.org/overviews/collections/maps.html
  */
object Maps {


  case class User(name: String, age: Int)

  def main(arg:Array[String]): Unit ={
    println("USERS:")
    val users:Seq[User] = Seq(User("Bob",12),User("Jane",15),User("Liz", 10),User("Ann",13))
    println(testGroupUsers(users))
    println(averageAge(testGroupUsers(users)))
    println("ADAMS:")
    val adams = Map("A"->User("Adam",35),"B"->User("Adam",40),"C"->User("Ann",38),"D"->User("Ally",30),"E"->User("Adam",20))
    println(testNumberFrodos(adams))
    println(testUnderaged(adams))

  }

  /* a) В данной Seq[User] сгруппируйте пользователей по имени (`groupBy`) и вычислите средний возраст: `name -> averageAge`
   *    Вы можете реализовать ваше решение в теле тестовой функции. Не изменяйте сигнатуру.
   */
  def testGroupUsers(users: Seq[User]): Map[String, Int] = users.map(User=>(User.name,User.age)).toMap//оьращаем в карту
  def averageAge (users: Map[String, Int]) : Int = users.values.sum/users.size//получаем среднее значение

  /* b) Дана `Map[String, User]` состоящая из имен пользователей `User`, сколько имен пользователей, содержащихся в Map, содержат подстроку "Adam"?
   *    Вы можете реализовать ваше решение в теле тестовой функции. Не изменяйте сигнатуру.
   */
  def testNumberFrodos(map: Map[String, User]): Int = map.count(_._2.name.contains("Adam"))

  /* c) Удалите всех пользователей возраст которых менее 35 лет.
   *    Вы можете реализовать ваше решение в теле тестовой функции. Не изменяйте сигнатуру.
   */
  def testUnderaged(map: Map[String, User]): Map[String, User] = map.filter(_._2.age>=35)//без age не работает
}
