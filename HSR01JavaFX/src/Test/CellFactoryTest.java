import Config.config;
import Functions.Database.DatabaseKolommenObservableList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Callback;

public class CellFactoryTest extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("CellFactoryTest");
        Group root = new Group();
        Scene scene = new Scene(root, 400, 300, Color.WHITE);

        GridPane gridpane = new GridPane();

        ComboBox<String> cmb = new ComboBox(DatabaseKolommenObservableList.fetchData(config.PAKKETKOLOMMENQUERY));
        ObservableList labels = FXCollections.observableArrayList();
        labels.addAll("1e label", "2e label", "3e label");
        
        ListCell listCell = new ListCell();
        ListView listView = new ListView(labels);
        listCell.updateListView(listView);
        cmb.setButtonCell(listCell);
        
        gridpane.add(cmb, 2, 0);
        
        root.getChildren().add(gridpane);   
        primaryStage.setScene(scene);

        primaryStage.show();
    }

}