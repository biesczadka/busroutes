package busroute

import busroute.businessExceptions.FileFormatException
import busroute.businessExceptions.FileReadException
import busroute.model.Route
import busroute.repository.RouteRepository
import spock.lang.Specification

/**
 * Created by Martyna on 2016-12-07.
 */
class RouteRepositoryTest extends Specification {

    def routeRepository = new RouteRepository()

    def "should parse file"() {
        given:
        routeRepository.busRouteFilePath = "src\\test\\resources\\busRoutes.txt"

        expect:
        List<Route> routes = routeRepository.getRoutes()
        routes.size() == 3
        routes[0].routeId == 0
        routes[0].stations == [0, 1, 2, 3, 4]

        routes[1].routeId == 1
        routes[1].stations == [3, 1, 6, 5]

        routes[2].routeId == 2
        routes[2].stations == [0, 6, 4]

    }

    def "should throw file read exception"() {
        given:
        routeRepository.busRouteFilePath = "notExistFile.txt"

        when:
        routeRepository.getRoutes()

        then:
        final FileReadException exception = thrown()
        exception.message == "Can't read file!"
    }

    def "should throw file format exception"() {
        given:
        routeRepository.busRouteFilePath = fileName

        when:
        routeRepository.getRoutes()

        then:
        final FileFormatException exception = thrown()
        exception.message == "File has wrong format!"

        where:
        fileName                   | _
        "src\\test\\resources\\wrongFormatBusRoutes.txt" | _
        "src\\test\\resources\\notEnoughRoutes.txt"      | _
    }


}
