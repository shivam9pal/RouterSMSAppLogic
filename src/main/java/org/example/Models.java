
    class BusRoute{
        private String busName ;
        private List<String> stops;

        public BusRoute(String busName,List<String> stops){
            this.busName=busName;
            this.stops=stops;
        }
        public String getBusName(){
            return busName;
        }
        public List<String> getStops{
            return stops;
        }
    
    }

    class Message{
        private int id;
        private String source,destination,senderMob;

        public Message(int id ,String source,String destination,String senderMob){
            this.id=id;
            this.source=source;
            this.destination=destination;
            this.senderMob=senderMob;
        }
        public int getId(){
            return id;
        }
        public String getSource(){
            return source;
        }
        public String getDestination(){
            return destination;
        }
        public String getSenderMob(){
            return senderMob;
        }
    }

    class RouteNode{
        String stop;
        List<RouteSegment> path;
        String bus;

        public RouteNode(String stop, List<RouteSegment> path ,String bus){

            this.stop =stop;
            this.path =path;
            this.bus  =bus;

        }
    }

    class RouteSegment{
        String from;
        String to;
        String bus;

        public RouteSegement(String from, String to ,String bus){
            this.from=from;
            this.to=to;
            this.bus=bus;
        }
    }


    

