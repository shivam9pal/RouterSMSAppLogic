package org.example;

public class Main{
    public static void main(String[] args){
        BusNetworkMap map =new MapInitializer.initializeMap();
        while (true){
            List<Message> messages = MessageProcessor.fetchUnprocessedMessages();
            for(Message msg:messages){
                String route =MessageProcessor.processMessage(map,msg);
                ReplySender.sendReply(msg,route);
            }
            try{
                Thread.sleep(500);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}