package Gijs;

import Config.config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javafx.collections.ObservableList;

/**
 *
 * @author Gijs
 */
public class ResultSet {
   
    public static ObservableList getObservableList(String query) {
       Connection conn;
       ObservableList lijst = null ;
        try {
            //MySQL driver aanroepen  
            Class.forName(config.DRIVER).newInstance();
            //Connectie wordt gemaakt
            conn = DriverManager.getConnection(config.URL, config.USERNAME, config.PASSWORD);
            //Query wordt uitgevoerd en in een ResultSet gezet
            java.sql.ResultSet rs = conn.createStatement().executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
        
            // The column count starts from 1
            for (int i = 1; i <= columnCount; i++) {
                lijst.addAll(rsmd.getColumnName(i));
            }
            return lijst;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }
}
