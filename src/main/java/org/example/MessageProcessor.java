public class MessageProcessor {
    public static List<Message> fetchUnprocessedMessages(){
        return DBManager.fetchMessages();
    }

    public static String processMessage(BusNetworkMap map, Message msg){
        return routeFinder.findPath(map,msg.getSoirce(),msg.getDestination());
    }
}
