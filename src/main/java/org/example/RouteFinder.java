import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;


public class RouteFinder {
    public static String findPath(BusNetworkMap map, String source, String destination){
        Queue<RouteNode> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(new RouteNode(source,new ArrayList<>(),null));

        while(!queue.isEmpty()){
            RouteNode current = queue.poll();
            if(current.stop.equals(destination)){
                return formatRoute(current.path);
            }

            for(Neighbour neighbour :map.getNeighbour(current.stop)){
                String key =neighbour.stop+":"+neighbour.bus;
                if(!visited.contains(key)){
                    visited.add(key);
                    List<RouteSegment> newPath = new ArrayList<>(current.path);
                    newPath.add(new RouteSegment(current.stop  ,neighbour.stop,neighbour.bus));
                    queue.add(new RouteNode(neighbour.stop,newPath,neighbour.bus));
                }
            }
        }
        return "No Route Found.";
    }

    private static String formatRoute(List<RouteSegment> path){
        if(path.isEmpty()) return "Source and destination are same.";

        StringBuilder sb = new StringBuilder();
        String currentBus = "";
        String from = "";

        fro(int i=0;i<path.size();i++){
            if(!seg.bus.equals(currentBus)){
                if(!currentBus.isEmpty()) sb.append("\n");
                sb.append(" take bus ").append(seg.bus).append(" from ").append(seg.from).append(" to ")
                currentBus=seg.bus;
                from=seg.from;
            }   
            (i==path.size() -1 || !path.get(i+1).bus.equals(currentBus)){
                sb.append(seg.to);
            }
         }
         return sb.toString();
    }
    
}
