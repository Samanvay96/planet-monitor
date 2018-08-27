package planet.planetgos

import com.typesafe.scalalogging.LazyLogging
import org.json4s.DefaultFormats
import org.json4s.JsonAST.JArray
import planet.models.monitored.CoordinateObject
import planet.planetgos.PlanetgosClient

class PlanetgosWatcher(planetgosClient: PlanetgosClient, limit: String) extends LazyLogging {
  implicit val formats = DefaultFormats

  def getGossip(coordinateObject: CoordinateObject): JArray = {

    logger.info("Made query")
    val query = makeQuery(coordinateObject)

    logger.info("Made request for gossip")
    val payload = planetgosClient.query("getGos", query)

    (payload \ "gossips").extract[JArray]

  }

  def makeQuery(coordinateObject: CoordinateObject): Map[String, String] = {
    Map(
      "planet" -> coordinateObject.`planet`,
      "coordinate" -> s"(${coordinateObject.`lat`},${coordinateObject.`long`})",
      "sort" -> "spicy",
      "limit" -> limit
    )
  }

  def makeCoordinate(coordinateObject: CoordinateObject): String = {
    s"(${round(coordinateObject.`lat`)},${round(coordinateObject.`long`)})"
  }

  def round(number: Double): Double = {
    (number * 1000).round / 1000.toDouble
  }

}
