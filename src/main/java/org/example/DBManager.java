
public class DBManager {
    public static final String URL ="";
    public static final String USER ="admin";
    public static final String PASS =""
    
    
    public static List<BusRoute> fetchBusRoutes(){
        List<BusRoute> routes = new ArrayList<>();
        try
            (Connection conn = DriverManager.getConnection(URL,USER,PASS);
            Statement st = conn.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT route_code ,route_details FROM busroutes");){
            while(rs.next()){
                String bus=rs.getString("route_code");
                List<String> stops=Array.asList(rs.getString(route_details).split("-"));
                route.add(new BusRoute(bus,stops));
            }}
            catch(SQLException e){
                e.printStackTrace();
            }
            return routes;
        }

    public static List<Message> fetchMessages(){
            List<Message> list = new ArrayList<>();
            try
                (Connection conn = DriverManager.getConnection(URL,USER,PASS);
            Statement st = conn.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT id, source, destination.sender_mob FROM messages WHERE is_processed=0");){
            while(rs.next()){
                list.add(new Message(
                    rs.getInt("id");
                    rs.getString("source");
                    rs.getString("destination");
                    rs.getString("sender_mob");
                    ));
                }
            }
            catch(SQLException e){
                e.printStackTrace();
                }
            
            return list;
    }

    public static void insertReply(String sendermob, String reply){
        try
            (Connection conn = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement ps=conn.prepareStatement("INSERT INTO reply (sender_mob,reply) VALUES (?,?)");){
            ps.setString(1,senderMob);
            ps.setString(2,reply);
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void markMessageProcessed(int id){
        try(Connection conn=DriverManager.getConnection(URL,USER,PASS);
        PreparedStatement ps = conn.preparedStatement("UPDATE messages SET is_processed = 1 WHERE id =?");){
            ps.setInt(1,id);
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}


