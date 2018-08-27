package planet

import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.mockito.MockitoSugar
import planet.planetgos.{PlanetgosClient, PlanetgosWatcher}


class PlanetgosWatcherSpec extends FlatSpec with Matchers with MockitoSugar {

  "round function" should "round a number to 3 decimal places" in {
    // Mock a planetgos client and initialise a limit
    val planetgosClient = mock[PlanetgosClient]
    val limit = "10"

    // Initialise a planetgoswatcher module
    val testPlanetgosWatcher = new PlanetgosWatcher(planetgosClient, limit)

    // Test round function
    testPlanetgosWatcher.round(149.39203958) shouldEqual 149.392
  }

}
