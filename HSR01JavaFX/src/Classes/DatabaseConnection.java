package Classes;

import java.sql.*;
import Config.config;
import java.util.HashMap;
import java.util.Map;

public class DatabaseConnection {
    //Attributes
    private String query;
    
    //Constructors
    public DatabaseConnection() {
        query = "SELECT * FROM pakket;";
    }
    
    //Methods
    public HashMap connect() {
        //HashMap wordt gemaakt om uiteindelijk te returnen
        HashMap<Integer, HashMap<String, String>> dbInfoHashMap = new HashMap<>();
        Connection conn;
        try {
            Class.forName(config.DRIVER).newInstance();
            //Connectie wordt gemaakt
            conn = DriverManager.getConnection(config.URL, config.USERNAME, config.PASSWORD);
            try {
                //Statement maken met de connectie
                Statement st = conn.createStatement();
                //Query wordt uitgevoerd en in een ResultSet gezet
                ResultSet rs = st.executeQuery(query);
                //Int om de data in de Hashmaps te nummeren
                Integer i = 0;
                //Door de resultset heen loopen
                while (rs.next()) {
                    Map tempMap = new HashMap<>();
                    tempMap.put("idpakket", rs.getString("idpakket"));
                    tempMap.put("lengte", rs.getString("lengte"));
                    tempMap.put("breedte", rs.getString("breedte"));
                    tempMap.put("hoogte", rs.getString("hoogte"));
                    tempMap.put("gewicht", rs.getString("gewicht"));
                    dbInfoHashMap.put(i, new HashMap<>(tempMap));
                    tempMap.clear();
                    i++;
                }
                return dbInfoHashMap;
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }
}
