package pckg

import java.io.PrintWriter
import scala.sys.process._

object HelloWorldGenerator {
  def main(args: Array[String]): Unit = {
    new PrintWriter("src/main/scala/pckg/HelloWorld.scala") {
      write("""package pckg
              |
              |object HelloWorld {
              |  def main(args: Array[String]): Unit = {
              |    println("Hello world!")
              |  }
              |}""".stripMargin)
      close()
    }
    "scalac -d target/scala-2.12/classes src/main/scala/pckg/HelloWorld.scala".!!
    val clazz = Class.forName("pckg.HelloWorld")
    val method = clazz.getMethod("main", classOf[Array[String]])
    method.invoke(null, new Array[String](0))
  }
}