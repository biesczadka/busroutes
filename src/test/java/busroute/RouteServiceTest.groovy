package busroute

import busroute.model.Route
import busroute.repository.RouteRepository
import busroute.service.RouteService

/**
 * Created by Martyna on 2016-12-07.
 */
class RouteServiceTest extends spock.lang.Specification {

    def routeRepository = Mock(RouteRepository)
    def routeService = new RouteService()

    def setup() {
        routeService.routeRepository = routeRepository
    }

    def "should find if direct path"() {
        given:
        routeRepository.getRoutes() >> [new Route(1, [0, 0, 1, 2, 3, 4]), new Route(2, [3, 1, 6, 5]), new Route(3, [0, 6, 4])]

        expect:
        routeService.isDirectRoute(start, dest) == ifDirectRouteExists

        where:
        start | dest | ifDirectRouteExists
        2     | 4    | true
        7     | 1    | false
    }
}
