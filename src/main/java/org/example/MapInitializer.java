import java.util.*;
public class MapInitializer {
    public static BusNetworkMap initializeMap(){
        BusNetworkMap map = new BusNetworkMap();
        List<BusRoute> routes  = DBManager.fetchBusRoutes();
        for (BusRoute route : routes){
            map.addRoute(route.getBusName(),route.getBusStops());
        }
        return map;
    }

}
