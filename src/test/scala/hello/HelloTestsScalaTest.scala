package hello
import org.scalatest._

class HelloTestsScalaTest extends FunSuite {
  val txt = Hello.page.render

  test("head") {
    println("running test: head")
    val head = txt.take(txt.indexOf("<body"))
    assert(head.contains("console"))
    assert(head.contains("log(1)"))
  }

  test("rest") {
    println("running test: rest")
    assert(txt.contains("Paragraph"))
  } 
  
  test("title") {
    println("running test: title")
    assert(txt.indexOf(Hello.titleString) >= 0)
    //assert(false)
  }
}
