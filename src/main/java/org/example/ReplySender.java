public class ReplySender {
    public static void sendReply(Message msg,String reply){
        DBManager.insertReply(msg.getSenderMob(),reply);
        DBManager.markMessageProcessed(msg.getId());
    }
    
}
