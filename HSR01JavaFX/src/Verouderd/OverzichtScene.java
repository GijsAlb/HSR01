package Verouderd;

import Functions.DatabaseTableView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class OverzichtScene extends Scene {
    //Attributes
    
    
    //Constructors
    public OverzichtScene(Stage stage, Parent root, double width, double height) {
        super(root, width, height);
        
        TabPane tabPane = new TabPane();
        
        Tab pakketTab = new Tab();
        pakketTab.setText("Pakketten");
        pakketTab.setContent(DatabaseTableView.fetchData("SELECT * FROM pakket;"));
        
        //Tabs toevoegen aan de TabPane
        tabPane.getTabs().add(pakketTab);
        
        setRoot(tabPane);
    }
    
}
