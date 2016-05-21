package Test;

import Config.config;
import Verouderd.DatabaseTableView;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TableViewTest extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("TableViewTest");
        StackPane testPane = new StackPane();
        Scene testScene = new Scene(testPane, 500, 400);
        
        TableView testTableView = DatabaseTableView.fetchData(config.PAKKETQUERY);
        
        ObservableList itemslist = testTableView.getSelectionModel().getSelectedItems();
        for(Object waarde : itemslist) {
            System.out.println(waarde);
        }
        
        testTableView.getSelectionModel().select(5);
        System.out.println(testTableView.getSelectionModel().getSelectedIndex());
        testTableView.getSelectionModel().select(1);
        System.out.println(testTableView.getSelectionModel().getSelectedIndex());
        
        
//        ObservableList observableList = testTableView.getItems();
//        for(Object rij : observableList) {
//            ObservableList rij2 = (ObservableList) rij;
//            for(Object waarde : rij2) {
//                System.out.println(waarde);
//            }
//            System.out.println();
//        }
        
        testPane.getChildren().add(testTableView);
        
        stage.setScene(testScene);
        stage.show();
    }
}
