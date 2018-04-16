import mill._, mill.scalalib._, mill.scalalib.publish._, mill.scalajslib._
import mill.scalanativelib.ScalaNativeModule

import ammonite.ops.pwd

trait SbtNativeModule extends ScalaNativeModule { outer =>
  override def sources = T.sources(
    millSourcePath / 'src / 'main / 'scala,
    millSourcePath / 'src / 'main / 'java
  )
  override def resources = T.sources{ millSourcePath / 'src / 'main / 'resources }
  trait Tests extends super.Tests {
    override def millSourcePath = outer.millSourcePath
    override def sources = T.sources(
      millSourcePath / 'src / 'test / 'scala,
      millSourcePath / 'src / 'test / 'java
    )
    override def resources = T.sources{ millSourcePath / 'src / 'test / 'resources }
  }
}



trait NativeExample extends SbtNativeModule { outer =>
  def millSourcePath = pwd
  def scalaVersion = "2.11.12"
  def scalaNativeVersion = "0.3.7" 

  def ivyDeps = Agg(
    ivy"com.lihaoyi::scalatags::0.6.7"
  )

  object test extends Tests {
    def scalaNativeVersion = outer.scalaNativeVersion
    def testFrameworks = Seq("utest.runner.Framework", "org.scalatest.tools.Framework")
    //def testFrameworks = Seq("utest.runner.Framework")
    //def testFrameworks = Seq("org.scalatest.tools.Framework")
    def ivyDeps = Agg(
      ivy"com.lihaoyi::utest::0.6.4",
      ivy"org.scalatest::scalatest::3.2.0-SNAP10"
    )
  }
}

object nativeexample extends NativeExample

object nativeexampleopt extends NativeExample {
  def releaseMode = true
}
