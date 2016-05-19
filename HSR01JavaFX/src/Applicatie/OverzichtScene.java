package Applicatie;

import Config.config;
import Functions.Database.DatabaseKolommenObservableList;
import Functions.Database.DatabaseTableView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class OverzichtScene extends Scene {

    public OverzichtScene(Stage stage, TabPane root, double width, double height) {
        super(root, width, height);
        
        //Tabje met overzicht pakketten
        Tab pakketTab = new Tab();
        pakketTab.setText("Pakketten");

        BorderPane pakketBorderPane = new BorderPane();

        //Toolbar voor boven de pakkettentabel
        ToolBar pakketToolBar = new ToolBar();
        pakketToolBar.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        //Buttons, TextFields en ComboBoxes worden gemaakt voor in de ToolBar
        Button pakketToevoegen = new Button();
        Button pakketVerwijderen = new Button();
        Button pakketHerladen = new Button();
        Button pakketZoeken = new Button();
        ComboBox pakketZoekenCategorie = new ComboBox(DatabaseKolommenObservableList.fetchData(config.PAKKETKOLOMMENQUERY));
        pakketZoekenCategorie.setPromptText("Categorie");
        TextField pakketZoekenVeld = new TextField();
        pakketZoekenVeld.setPromptText("Zoeken");

        //Icoontjes worden toegevoegd aan de Buttons
        ImageView icoonToevoegen = new ImageView("file:src/Images/AddIconWhite24.png");
        pakketToevoegen.setGraphic(icoonToevoegen);
        ImageView icoonVerwijderen = new ImageView("file:src/Images/DeleteIconWhite24.png");
        pakketVerwijderen.setGraphic(icoonVerwijderen);
        ImageView icoonHerladen = new ImageView("file:src/Images/RefreshIconWhite24.png");
        pakketHerladen.setGraphic(icoonHerladen);
        ImageView icoonZoeken = new ImageView("file:src/Images/SearchIconWhite24.png");
        pakketZoeken.setGraphic(icoonZoeken);

        //Alle items worden toegevoegd aan de ToolBar
        pakketToolBar.getItems().addAll(
                pakketToevoegen,
                pakketVerwijderen,
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
        TableView accountTabel = DatabaseTableView.fetchData("SELECT gebruikersnaam, wachtwoord FROM backoffice_account;");
        Object kolom;
        Object waarde;
        
        //TEST
        accountTabel.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                //Check whether item is selected and set value of selected item to Label
                if (accountTabel.getSelectionModel().getSelectedItem() != null) {
                    TableView.TableViewSelectionModel selectionModel = accountTabel.getSelectionModel();
                    ObservableList selectedCells = selectionModel.getSelectedCells();
                    TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                    Object waarde = tablePosition.getTableColumn().getCellData(newValue);
                    Object kolom = tablePosition.getTableColumn().getText();
                    System.out.println("Kolom: " + kolom + ", waarde: " + waarde);
                }
            }
        });
        //TEST
        
        stackpane.getChildren().add(accountTabel);
        accountTab.setContent(stackpane);

        //Tabs toevoegen aan de TabPane
        root.getTabs().addAll(pakketTab, accountTab);

        //Tabs kunnen niet meer gesloten worden
        for (Tab tab : root.getTabs()) {
            tab.setClosable(false);
        }
        
        //Stylesheet wordt toegevoegd
        getStylesheets().add("file:src/CSS/JMetroLightTheme.css");
    }
    
}
