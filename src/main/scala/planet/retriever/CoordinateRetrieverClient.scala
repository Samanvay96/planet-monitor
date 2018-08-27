package planet.retriever

import org.json4s.{DefaultFormats, _}
import org.json4s.jackson.JsonMethods.{parse, _}
import planet.models.monitored.CoordinateObject

import scalaj.http.{Http, HttpOptions}

class CoordinateRetrieverClient(url: String, apiToken: String) {
  def getObjects: Array[CoordinateObject] = {
    val request = Http(s"$url/coordinates")
      .options(HttpOptions.allowUnsafeSSL)
      .header("Accept", "application/json")
      .timeout(4000, 4000)
      .param("api_token", apiToken)

    implicit val formats = DefaultFormats
    parse(request.asString.body).extract[Array[CoordinateObject]]
  }

}
