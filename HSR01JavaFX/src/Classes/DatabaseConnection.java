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
        HashMap<Integer, HashMap<String, String>> dbInfoHashMap = new HashMap<>();
        Connection conn;
        try {
            Class.forName(config.DRIVER).newInstance();
            conn = DriverManager.getConnection(config.URL, config.USERNAME, config.PASSWORD);
            
            try {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);
                Integer i = 0;
                while (rs.next()) {
                    HashMap<String, String> tempHashMap = new HashMap<>();
                    tempHashMap.put("idpakket", rs.getString("idpakket"));
                    tempHashMap.put("lengte", rs.getString("lengte"));
                    tempHashMap.put("breedte", rs.getString("breedte"));
                    tempHashMap.put("hoogte", rs.getString("hoogte"));
                    tempHashMap.put("gewicht", rs.getString("gewicht"));
                    Map tempMap = null;
                    tempHashMap.putAll(tempMap);
                    dbInfoHashMap.put(i, new HashMap<>(tempMap));
                    tempHashMap.clear();
                    tempMap.clear();
                    i++;
                }
                return dbInfoHashMap;
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        } catch (IllegalAccessException ex) {
            System.err.println(ex.getMessage());
        } catch (InstantiationException ex) {
            System.err.println(ex.getMessage());
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }
}
