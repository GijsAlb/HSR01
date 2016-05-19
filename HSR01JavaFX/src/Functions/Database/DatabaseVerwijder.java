package Functions.Database;

import java.sql.*;
import Config.config;

public class DatabaseVerwijder {
    //Voert een query uit, zet het meegegeven veld in een ArrayList, die wordt gereturnd
    public static void verwijder(String query, String whereVeld) {
        Connection conn;
        try {
            //MySQL driver aanroepen
            Class.forName(config.DRIVER).newInstance();
            //Connectie wordt gemaakt
            conn = DriverManager.getConnection(config.URL, config.USERNAME, config.PASSWORD);
            try {
                //Statement maken met de connectie
                Statement st = conn.createStatement();
                
                StringBuilder sb = new StringBuilder(query);
                sb.append(whereVeld);
                sb.append(";");
                String updateQuery = sb.toString();
                
                //Update wordt uitgevoerd
                st.executeUpdate(updateQuery);
                st.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}