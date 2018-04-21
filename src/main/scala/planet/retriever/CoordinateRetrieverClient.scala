package planet.retriever

import org.json4s.{DefaultFormats, _}
import org.json4s.jackson.JsonMethods.{parse, _}

import scalaj.http.Http

class CoordinateRetrieverClient(url: String, apiToken: String) {
  def getObjects: Array[MonitoredObject] = {
    val request = Http(s"$url/coordinates")
      .options(HttpOptions.allowUnsafeSSL)
      .header("Accept", "application/json")
      .timeout(60000, 60000)
      .param("api_token", apiToken)

    implicit val formats = DefaultFormats
    parse(request.asString.body).extract[Array[MonitoredObject]]
  }

}
