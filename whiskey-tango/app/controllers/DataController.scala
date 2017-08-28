package controllers

import javax.inject._
import scala.util.Properties
import play.api._
import play.api.mvc._
import play.api.libs.json._

import com.typesafe.config.ConfigFactory

import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

@Singleton
class DataController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  val master: String = Properties.envOrElse("SPARK_MASTER", ConfigFactory.load().getString("spark.master"))

  val conf = new SparkConf()
    .setJars(Array("target/scala-2.11/whiskey-tango_2.11-1.0-SNAPSHOT.jar"))
    .setAppName("City Aggregator")
    .setMaster(master)
  val sc = new SparkContext(conf)

  def cities(from: String, until: String, city: String) = Action { implicit request: Request[AnyContent] =>
    val format = new java.text.SimpleDateFormat("yyyy-MM-dd")
    val m = Map("from" -> from, "until" -> until, "city" -> city)
    val textFile = sc.textFile("../input.csv")
    val data = textFile
      .map(_.split(";"))
      .map(s => (s(0), (s(3), s(9))))
      .reduceByKey((i, j) => i)
      .map(_._2)
      .filter(p => format.parse(p._1) after format.parse(m("from")))
      .filter(p => format.parse(p._1) before format.parse(m("until")))
      .filter(_._2 matches m("city"))
      .map(_ -> 1)
      .reduceByKey(_+_)
      .collect()
    Ok(Json.toJson(data))
  }

}
