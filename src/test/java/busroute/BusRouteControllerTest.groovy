package busroute

import busroute.businessExceptions.FileFormatException
import busroute.businessExceptions.FileReadException
import busroute.controller.BusRouteController
import busroute.service.RouteService
import groovy.json.JsonSlurper
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.http.HttpStatus.OK
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

/**
 * Created by Martyna on 2016-12-07.
 */
class BusRouteControllerTest extends Specification {

    def routeService = Mock(RouteService)

    def busRouteController = new BusRouteController(routeService: routeService)

    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(busRouteController).build()

    def setup() {
        busRouteController.routeService = routeService
    }

    def "should return valid JSON"() {
        given:
        routeService.isDirectRoute(dep_sid, arr_sid) >> direct_bus_route

        when:
        def response = mockMvc.perform(get("/api/direct?dep_sid=" + dep_sid + "&arr_sid=" + arr_sid)).andReturn().response
        def content = new JsonSlurper().parseText(response.contentAsString)

        then:
        response.status == OK.value()
        response.contentType == 'application/json;charset=UTF-8'
        content.dep_sid == dep_sid
        content.arr_sid == arr_sid
        content.direct_bus_route == direct_bus_route

        where:
        dep_sid | arr_sid | direct_bus_route
        3       | 9       | false
        1       | 2       | true
    }

    def "should return message"() {
        given:
        routeService.isDirectRoute(dep_sid, arr_sid) >> { throw exception }

        when:
        def response = mockMvc.perform(get("/api/direct?dep_sid=" + dep_sid + "&arr_sid=" + arr_sid)).andReturn().response

        then:
        response.status == OK.value()
        response.contentType == 'text/plain;charset=ISO-8859-1'
        response.contentAsString == message

        where:
        dep_sid | arr_sid | exception                 | message
        3       | 9       | new FileFormatException() | "File has wrong format!"
        1       | 2       | new FileReadException()   | "Can't read file!"
    }
}
