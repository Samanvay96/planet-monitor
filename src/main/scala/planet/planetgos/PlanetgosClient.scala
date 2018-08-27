package planet.planetgos

import org.json4s.jackson.JsonMethods.parse
import org.json4s.{DefaultFormats, JValue}

import scalaj.http.{Http, HttpRequest}

class PlanetgosClient(planetgosBaseUrl: String, apiToken: String,  http: (String) => HttpRequest = Http.apply) {
  def query(endpoint: String, queries: Map[String, String]): JValue = {
    var request = http(s"$planetgosBaseUrl/$endpoint")
      .header("Accept", "application/json")
      .param("token", apiToken)
      .param("format", "json")

    for (key <- queries.keySet) {
      request = request.param(key, queries.getOrElse(key, ""))
    }

    implicit val formats = DefaultFormats
    parse(request.asString.body)
  }
}
