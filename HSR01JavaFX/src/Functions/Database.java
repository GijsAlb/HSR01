package Functions;

import Config.config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class Database {
    
    //Geeft een lijst terug met kolommen op basis van de meegegeven query
    public static ObservableList<String> getKolommen(String query) {
        Connection conn;
        ObservableList<String> lijst = FXCollections.observableArrayList();
        try {
            //MySQL driver aanroepen  
            Class.forName(config.DRIVER).newInstance();
            //Connectie wordt gemaakt
            conn = DriverManager.getConnection(config.URL, config.USERNAME, config.PASSWORD);
            //Query wordt uitgevoerd en in een ResultSet gezet
            ResultSet rs = conn.createStatement().executeQuery(query);
            //Metadata van de resultset wordt opgeslagen
            ResultSetMetaData rsmd = rs.getMetaData();
            int aantalKolommen = rsmd.getColumnCount();

            //Loopt door alle kolommen uit de metadata heen en voegt ze toe aan de lijst
            for (int i = 1; i <= aantalKolommen; i++) {
                lijst.add(rsmd.getColumnLabel(i));
            }
            return lijst;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }
    
    //Voert een query uit, zet de twee meegegeven velden in een LinkedHashMap en zet deze LinkedHashMaps vervolgens in een overkoepelende ArrayList, die wordt gereturnd
    public static ArrayList<LinkedHashMap<String, String>> getArrayListHashMap(String query, String veld1, String veld2) {
        Connection conn;
        ArrayList<LinkedHashMap<String, String>> data = new ArrayList<>();
        try {
            //MySQL driver wordt aangeroepen
            Class.forName(config.DRIVER).newInstance();
            //Connectie wordt gemaakt
            conn = DriverManager.getConnection(config.URL, config.USERNAME, config.PASSWORD);
            try {
                //Statement maken met de connectie
                Statement st = conn.createStatement();
                //Query wordt uitgevoerd en in een ResultSet gezet
                ResultSet rs = st.executeQuery(query);
                //Door de resultset heen loopen, veld 1 en 2 toevoegen aan een LinkedHashMap en deze toevoegen aan de overkoepelende ArrayList
                while (rs.next()) {
                    Map<String, String> tempMap = new LinkedHashMap<>();
                    tempMap.put(rs.getString(veld1),rs.getString(veld2));
                    data.add(new LinkedHashMap<>(tempMap));
                    tempMap.clear();
                }
                return data;
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }
    
    //Haalt data op uit de database en zet deze in een lijst, waarmee een TableView kan worden gevuld
    public static ObservableList getData(String query) {
        Connection conn;
        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
        try {
            //MySQL driver aanroepen  
            Class.forName(config.DRIVER).newInstance();
            //Connectie wordt gemaakt
            conn = DriverManager.getConnection(config.URL, config.USERNAME, config.PASSWORD);
            //Query wordt uitgevoerd en in een ResultSet gezet
            ResultSet rs = conn.createStatement().executeQuery(query);

            //Voegt rijen toe en zet deze in de ObserverableList zodat de data automatisch binnen de tabel ingevoegd wordt
            while (rs.next()) {
                //Maakt een rij aan en zet deze in de ObservableList
                ObservableList<String> rij = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    rij.add(rs.getString(i));
                }
                data.add(rij);
            }
            
            return data;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }
    
    //Haalt data en kolommen op uit de database en maakt hier een TableView mee
    public static TableView getTableView(String query) {
        Connection conn;
        TableView tableview = new TableView();
        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
        try {
            //MySQL driver aanroepen  
            Class.forName(config.DRIVER).newInstance();
            //Connectie wordt gemaakt
            conn = DriverManager.getConnection(config.URL, config.USERNAME, config.PASSWORD);
            //Query wordt uitgevoerd en in een ResultSet gezet
            ResultSet rs = conn.createStatement().executeQuery(query);

            //Voegt dynamisch kolommen toe aan de hand van het aantal kolommen in de query
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn kolom = new TableColumn(rs.getMetaData().getColumnLabel(i + 1));
                kolom.setSortable(false);
                
                //Geeft juiste waarde uit de database aan de tabelkolom
                kolom.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });


                tableview.getColumns().addAll(kolom);

            }

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

            //Voegt alle data toe aan tableview
            tableview.setItems(data);
            conn.close();
            return tableview;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }
    
    //Zet de kolom 'verwijderd' op true, op basis van de meegegeven tabel, primarykey en waarde
    public static void delete(String tabel, String primarykey, String whereVeld) {
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
                sb.append(" SET verwijderd = 1 WHERE ");
                sb.append(primarykey);
                sb.append(" = ");
                sb.append(whereVeld);
                sb.append(";");
                System.out.println(sb);
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
