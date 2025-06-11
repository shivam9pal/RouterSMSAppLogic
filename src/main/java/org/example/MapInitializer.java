import java.util.*;
public class MapInitializer {
    public static BusNetworkMap initializeMap(){
        BusNetworkMap map = new BusNetworkMap();
        List<BusRoute> routes  = DBManager.fetchBusRoutes();
        for (BusRoute route : routes){
            map.addRoute(rooute.getBusName(),route.getBusStops());
        }
        return map;
    }

}
