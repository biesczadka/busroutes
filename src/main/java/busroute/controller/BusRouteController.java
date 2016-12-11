package busroute.controller;

import busroute.businessExceptions.BusinessException;
import busroute.model.DirectRouteDTO;
import busroute.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BusRouteController {

    @Autowired
    RouteService routeService;

    @SuppressWarnings("unused")
    @RequestMapping(method = RequestMethod.GET, value = {"/api/direct"})
    public DirectRouteDTO isDirectRoute(@RequestParam(value = "dep_sid") Integer dep_sid, @RequestParam(value = "arr_sid") Integer arr_sid) {
        boolean isRoute = routeService.isDirectRoute(dep_sid, arr_sid);

        return new DirectRouteDTO(dep_sid, arr_sid, isRoute);
    }

    @SuppressWarnings("unused")
    @ExceptionHandler(value = BusinessException.class)
    public String beHandler(BusinessException e) {
        return e.getMessage();
    }

    @SuppressWarnings("unused")
    @ExceptionHandler(value = NumberFormatException.class)
    public String nfeHandler(NumberFormatException e) {
        return "Parameters must be integers!";
    }
}
