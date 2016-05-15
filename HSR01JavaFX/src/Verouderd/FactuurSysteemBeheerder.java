package Verouderd;

import Config.config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * 
 * @author Gijs Alberts
 */

public class FactuurSysteemBeheerder extends Application{

    //tabel en data attributen
    private ObservableList<ObservableList<String>> data;
    private TableView tableview;
    
    //main
    public static void main(String[] args) {
        launch(args);
    }
    
    public void buildData(){
          Connection conn ;
          data = FXCollections.observableArrayList();
          try{
            //mysql driver aanroepen  
            Class.forName(config.DRIVER).newInstance();
            //Connectie wordt gemaakt
            conn = DriverManager.getConnection(config.URL, config.USERNAME, config.PASSWORD);
            //sql query
            String SQL = "SELECT * from pakket";
            //resultset met opgehaalde gegevens uit db
            ResultSet rs = conn.createStatement().executeQuery(SQL);

            //voegt dynamisch kolommen toe aan de hand van het aantal kolommen in de query
            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                final int j = i;                
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                //geeft juiste waarde (aan de hand van db) aan de tabelkolom
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                        return new SimpleStringProperty(param.getValue().get(j).toString());                        
                    }                    
                });

                tableview.getColumns().addAll(col); 
              
            }

            //voegt rijen toe en zet deze in de observerablearraylist zodat de data automatisch binnen de tabel ingevoegd wordt
            while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //zorgt dat de data onder de juiste kolom wordt geplaatst
                    row.add(rs.getString(i));
                }
            
                data.add(row);

            }

            //voegt alle data toe aan tabel
            tableview.setItems(data);
          }catch(Exception e){
              System.out.println("Kan geen database verbinding maken.");             
          }
      }


      @Override
      public void start(Stage stage) throws Exception {
        //TableView
        tableview = new TableView();
        buildData();

        //Main Scene
        Scene scene = new Scene(tableview);        

        stage.setScene(scene);
        stage.show();
      }
}