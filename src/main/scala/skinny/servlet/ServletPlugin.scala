package skinny.servlet

import sbt._

object ServletPlugin extends AutoPlugin {

  val autoImport = ServletKeys

  import autoImport._

  lazy val container = Container("container")

  def servletSettings: Seq[Setting[_]] = servletSettings(DefaultConf)

  def servletSettings(conf: Configuration): Seq[Setting[_]] = {
    container.settings ++
      inConfig(conf)(WebappPlugin.webappSettings0) ++
      Seq(
        apps in container.Configuration := {
          Seq("/" -> (deployment in conf).value)
        }) ++ WarPlugin.globalWarSettings
  }

  def webappSettings: Seq[Setting[_]] = WebappPlugin.webappSettings

  def webappSettings(config: Configuration): Seq[Setting[_]] = WebappPlugin.webappSettings(config)

  def globalWarSettings: Seq[Setting[_]] = WarPlugin.globalWarSettings

  override def projectSettings = servletSettings
}
