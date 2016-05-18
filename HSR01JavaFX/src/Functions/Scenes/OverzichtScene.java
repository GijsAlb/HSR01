package Functions.Scenes;

import Config.config;
import Functions.Database.DatabaseTableView;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class OverzichtScene {
    
    public static Scene getScene(Stage stage) {
        TabPane tabPane = new TabPane();
        
        //Tabje met alle pakketten erop
        Tab pakketTab = new Tab();
        pakketTab.setText("Pakketten");
        pakketTab.setContent(DatabaseTableView.fetchData("SELECT idpakket AS Pakketid, "
                                                              + "barcode AS Barcode, "
                                                              + "(CASE locatie "
                                                                  + "WHEN 0 THEN 'Niet gekoppeld' "
                                                                  + "WHEN 1 THEN 'Opgehaald' "
                                                                  + "WHEN 2 THEN 'Onderweg' "
                                                                  + "WHEN 3 THEN 'Niet gekoppeld' "
                                                                  + "WHEN 4 THEN 'Afgeleverd' "
                                                                  + "ELSE 'Locatie onbekend' "
                                                              + "END) AS Locatie, "
                                                              + "lengte as Lengte, "
                                                              + "breedte AS Breedte, "
                                                              + "hoogte AS Hoogte, "
                                                              + "gewicht AS Gewicht "
                                                        + "FROM pakket;"));
        
        //Tabje met alle backoffice accounts
        Tab accountTab = new Tab();
        accountTab.setText("Accounts");
        StackPane stackpane = new StackPane();
        stackpane.getChildren().add(DatabaseTableView.fetchData("SELECT accountid, gebruikersnaam, wachtwoord_tijdelijk, wachtwoord FROM backoffice_account;"));
        accountTab.setContent(stackpane);
        
        //Tabs toevoegen aan de TabPane
        tabPane.getTabs().addAll(pakketTab, accountTab);
        //Tabjes kunnen niet meer gesloten worden
        for(Tab tab : tabPane.getTabs()) {
            tab.setClosable(false);
        }
        
        Scene overzichtScene = new Scene(tabPane, 1280, 720);
        overzichtScene.getStylesheets().add("file:src/CSS/JMetroDarkTheme.css");
//        overzichtScene.getStylesheets().add(config.CSS);
        overzichtScene.setRoot(tabPane);
        return overzichtScene;
    }
}
