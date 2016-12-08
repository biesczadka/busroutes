package busroute.model;

import java.util.List;

/**
 * Created by Martyna on 2016-12-07.
 */
public class Route {

    private int routeId;
    private List<Integer> stations;

    public Route(int routeId, List<Integer> stations) {
        this.routeId = routeId;
        this.stations = stations;
    }

    public boolean isRoute(int start, int dest) {

        int startIndex = stations.indexOf(start);
        int destIndex = stations.lastIndexOf(dest);
        if (startIndex < destIndex && startIndex >= 0 && destIndex >= 0) return true;
        return false;
    }
}
