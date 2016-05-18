package Functions.Scenes;

import Config.config;
import Functions.Database.DatabaseKolommenObservableList;
import Functions.Database.DatabaseTableView;
import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class OverzichtScene {
    
    public static Scene getScene(Stage stage) {
        TabPane tabPane = new TabPane();
        
        //Tabje met overzicht pakketten
        Tab pakketTab = new Tab();
        pakketTab.setText("Pakketten");
        
        BorderPane pakketBorderPane = new BorderPane();
        
        //Toolbar voor boven de pakkettentabel
        ToolBar pakketToolBar = new ToolBar();
        pakketToolBar.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        
        //Buttons, TextFields en ComboBoxes worden gemaakt voor in de ToolBar
        Button pakketToevoegen = new Button();
        Button pakketHerladen = new Button();
        Button pakketZoeken = new Button();
        ComboBox pakketZoekenCategorie = new ComboBox(DatabaseKolommenObservableList.fetchData(config.PAKKETKOLOMMENQUERY));
        pakketZoekenCategorie.setPromptText("Categorie");
        TextField pakketZoekenVeld = new TextField();
        pakketZoekenVeld.setPromptText("Zoeken");
        
        //Icoontjes worden toegevoegd aan de Buttons
        ImageView icoonToevoegen = new ImageView("file:src/Images/AddIconWhite24.png");
        pakketToevoegen.setGraphic(icoonToevoegen);
        ImageView icoonHerladen = new ImageView("file:src/Images/RefreshIconWhite24.png");
        pakketHerladen.setGraphic(icoonHerladen);
        ImageView icoonZoeken = new ImageView("file:src/Images/SearchIconWhite24.png");
        pakketZoeken.setGraphic(icoonZoeken);
        
        //Alle items worden toegevoegd aan de ToolBar
        pakketToolBar.getItems().addAll(
                pakketToevoegen,
                pakketHerladen,
                pakketZoeken,
                pakketZoekenCategorie,
                pakketZoekenVeld
        );
        
        //ToolBar wordt toegevoegd aan de BorderPane
        pakketBorderPane.setTop(pakketToolBar);
        
        //Zet de pakkettentabel in een StackPane en zet die in het midden van de BorderPane
        StackPane pakketStackPane = new StackPane();
        pakketStackPane.getChildren().add(DatabaseTableView.fetchData(config.PAKKETQUERY));
        pakketBorderPane.setCenter(pakketStackPane);
        pakketTab.setContent(pakketBorderPane);
        
        //Tabje met alle backoffice accounts
        Tab accountTab = new Tab();
        accountTab.setText("Accounts");
        StackPane stackpane = new StackPane();
        stackpane.getChildren().add(DatabaseTableView.fetchData("SELECT accountid, gebruikersnaam, wachtwoord_tijdelijk, wachtwoord FROM backoffice_account;"));
        accountTab.setContent(stackpane);
        
        //Tabs toevoegen aan de TabPane
        tabPane.getTabs().addAll(pakketTab, accountTab);
        
        //Tabs kunnen niet meer gesloten worden
        for(Tab tab : tabPane.getTabs()) {
            tab.setClosable(false);
        }
        
        //Haalt resolutie op, en zet width en height om naar 67% (ongeveer 1080x720 bij een resolutie van 1920x1080) zodat het scherm op andere resoluties niet te groot wordt
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth() * 0.66667;
        double height = screenSize.getHeight() * 0.66667;
        Scene overzichtScene = new Scene(tabPane, width, height);
        System.out.println(overzichtScene.getWidth());
        System.out.println(overzichtScene.getHeight());
        overzichtScene.getStylesheets().add(config.CSS);
        overzichtScene.setRoot(tabPane);
        
        return overzichtScene;
    }
}