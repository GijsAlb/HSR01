package Functions;

import Config.config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class DatabaseTableView {

    //Attributes
    private ObservableList<ObservableList<String>> data;
    private TableView tableview;

    //Methods
    public TableView fetchData(String query) {
        data = FXCollections.observableArrayList();
        Connection conn;
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
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                //Geeft juiste waarde uit de database aan de tabelkolom
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tableview.getColumns().addAll(col);

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
            return tableview;
        } catch (Exception e) {
            System.out.println("Kan geen database verbinding maken.");
            return null;
        }
    }
}
