package busroute.service;

import busroute.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Martyna on 2016-12-07.
 */
@Service
public class RouteService {

    @Autowired
    RouteRepository routeRepository;

    public boolean isDirectRoute(int start, int dest){
    return routeRepository.getRoutes().stream().anyMatch(route -> route.isRoute(start, dest));
    }
}
