package Test;

import java.util.LinkedHashMap;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ComboBoxTest extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("ComboBoxTest");
        GridPane grid = new GridPane();
        Scene testScene = new Scene(grid, 500, 400);
        
        ObservableList<LinkedHashMap<String, String>> lijst = FXCollections.observableArrayList();
        
        LinkedHashMap<String, String> lhm = new LinkedHashMap<>();
        lhm.put("Pakketid", "idpakket");
        lhm.put("Barcode", "barcode");
        lhm.put("Lengte", "lengte");
        lhm.put("Breedte", "breedte");
        lhm.put("Hoogte", "hoogte");
        lhm.put("Gewicht", "gewicht");
        
        lijst.addAll(lhm);
        
        ComboBox<LinkedHashMap<String, String>> comboBox = new ComboBox<>(lijst);
        
        grid.add(comboBox, 0, 0);
        
        stage.setScene(testScene);
        stage.show();
    }
}
