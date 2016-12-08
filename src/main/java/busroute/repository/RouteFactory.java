package busroute.repository;

import busroute.model.Route;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Martyna on 2016-12-07.
 */
 class RouteFactory {

    public static Route createRoute(String line){
        List<String> items = new ArrayList<String>(Arrays.asList(line.split(" ")));
        int routeId = Integer.valueOf(items.get(0));
        items.remove(0);
        List<Integer> stations = items.stream().map(s -> Integer.valueOf(s)).collect(Collectors.toList());
        return new Route(routeId, stations);
    }
}
