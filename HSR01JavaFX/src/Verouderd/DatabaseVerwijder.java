package Verouderd;

import java.sql.*;
import Config.config;

public class DatabaseVerwijder {
    //Zet de kolom 'verwijderd' op true, op basis van de meegegeven tabel, primarykey en waarde
    public static void verwijder(String tabel, String primarykey, String whereVeld) {
        Connection conn;
        try {
            //MySQL driver aanroepen
            Class.forName(config.DRIVER).newInstance();
            //Connectie wordt gemaakt
            conn = DriverManager.getConnection(config.URL, config.USERNAME, config.PASSWORD);
            try {
                //Statement maken met de connectie
                Statement st = conn.createStatement();
                
                StringBuilder sb = new StringBuilder();
                sb.append("UPDATE ");
                sb.append(tabel);
                sb.append(" SET ");
                sb.append(primarykey);
                sb.append(" = ");
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