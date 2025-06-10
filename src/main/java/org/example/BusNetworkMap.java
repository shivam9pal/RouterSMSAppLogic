package org.example;
public class BusNetworkMap {
    private final Map<String, List<String>> stopGraph = new HashMap<>();
    
    
    // function to add route 
    public void addRoute(String bus, List<String> stops){
        for(int i=0; i<stops.size()-1;i++){
            addEdge(stops.get(i),stops.get(i+1),bus);
            addEdge(stops.get(i+1),stops.get(i),bus);
        }
    }
    public void addEdge(String from,String to, String bus){
        stopGraph.computeIfAbsent(from,k -> new ArrayList<>()).add(new Neighbour(to,bus));
    }

    public List<Neighbour> getNeighbour(String stop){
        return stopGraph.getOrDefault(stop,new ArrayList<>());
    }
}

class Neighbour{
    String stop;
    String bus;

    public Neighbour(String stop,String bus){
        this.stop=stop;
        this.bus=bus;
    }
}
