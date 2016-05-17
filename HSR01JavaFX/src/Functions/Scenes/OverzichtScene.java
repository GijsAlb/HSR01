package Functions.Scenes;

import Config.config;
import Functions.Database.DatabaseTableView;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class OverzichtScene {
    
    public static Scene getScene(Stage stage) {
        TabPane tabPane = new TabPane();
        
        //Tabje met alle pakketten erop
        Tab pakketTab = new Tab();
        pakketTab.setText("Pakketten");
        pakketTab.setContent(DatabaseTableView.fetchData("SELECT idpakket, barcode, locatie, lengte, breedte, hoogte, gewicht FROM pakket;"));
        
        //Tabje met alle backoffice accounts
        Tab accountTab = new Tab();
        accountTab.setText("Accounts");
        accountTab.setContent(DatabaseTableView.fetchData("SELECT accountid, gebruikersnaam, wachtwoord_tijdelijk, wachtwoord FROM backoffice_account;"));
        
        //Tabs toevoegen aan de TabPane
        tabPane.getTabs().addAll(pakketTab, accountTab);
        //Tabjes kunnen niet meer gesloten worden
        for(Tab tab : tabPane.getTabs()) {
            tab.setClosable(false);
        }
        
        Scene overzichtScene = new Scene(tabPane, 1280, 720);
//        overzichtScene.getStylesheets().add("file:src/CSS/JMetroDarkTheme.css");
        overzichtScene.getStylesheets().add(config.CSS);
        overzichtScene.setRoot(tabPane);
        return overzichtScene;
    }
}
