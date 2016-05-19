package Gijs;

import Config.config;
import Functions.Database.DatabaseKolommenObservableList;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

/**
 *
 * @author Gijs
 */
public class ZoekFunctie {

    private Image zoekIcon;
    private TextField zoekVeld;
    private Button zoekKnop;
    private ComboBox cat;
    private ObservableList ol;
    public Connection conn;
    private TableView tableViewZoek;
    private ObservableList data = FXCollections.observableArrayList();
 

    public ZoekFunctie(String query) {
        zoekIcon = new Image(getClass().getResourceAsStream("vergrootglas.jpg"));

        zoekVeld = new TextField();

        ol = DatabaseKolommenObservableList.fetchData(query);

        cat = new ComboBox(ol);

        zoekKnop = new Button();
        zoekKnop.setGraphic(new ImageView(zoekIcon));
        
        zoekKnop.setOnAction((ActionEvent e) -> {
            String zoekopdr = "" + zoekVeld.getText();
            String kolom = cat.getSelectionModel().getSelectedItem().toString();
            
            try {
                //MySQL driver aanroepen
                Class.forName(config.DRIVER).newInstance();
                //Connectie wordt gemaakt
                conn = DriverManager.getConnection(config.URL, config.USERNAME, config.PASSWORD);
                //Query wordt uitgevoerd en in een ResultSet gezet
                java.sql.ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM pakket WHERE " + kolom + " LIKE " + zoekopdr);
                //voegt dynamisch kolommen toe aan de hand van het aantal kolommen in de query
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    final int j = i;
                    TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                    //geeft juiste waarde (aan de hand van db) aan de tabelkolom
                    col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                        public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                            return new SimpleStringProperty(param.getValue().get(j).toString());
                        }
                    });
                    
                    tableViewZoek.getColumns().addAll(col);
                    
                }
                
                //voegt rijen toe en zet deze in de observerablearraylist zodat de data automatisch binnen de tabel ingevoegd wordt
                while (rs.next()) {
                    //Iterate Row
                    ObservableList<String> row = FXCollections.observableArrayList();
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        //zorgt dat de data onder de juiste kolom wordt geplaatst
                        row.add(rs.getString(i));
                    }
                    
                    data.add(row);
                    
                }
                
                //voegt alle data toe aan tabel
                tableViewZoek.setItems(data);
                
                //stackPane.getChildren.remove(tableview);
                //stackPane.getChildren.add(tableViewZoek);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                System.err.println(ex.getMessage());
                
            }
        });

    }
}
