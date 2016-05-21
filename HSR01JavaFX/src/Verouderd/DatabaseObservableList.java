package Verouderd;

import Config.config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DatabaseObservableList {
    public static ObservableList fetchData(String query) {
        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
        Connection conn;
        try {
            //MySQL driver aanroepen  
            Class.forName(config.DRIVER).newInstance();
            //Connectie wordt gemaakt
            conn = DriverManager.getConnection(config.URL, config.USERNAME, config.PASSWORD);
            //Query wordt uitgevoerd en in een ResultSet gezet
            ResultSet rs = conn.createStatement().executeQuery(query);

            //Voegt rijen toe en zet deze in de ObserverableArrayList zodat de data automatisch binnen de tabel ingevoegd wordt
            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //zorgt dat de data onder de juiste kolom wordt geplaatst
                    row.add(rs.getString(i));
                }

                data.add(row);
            }
            
            return data;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }
}
