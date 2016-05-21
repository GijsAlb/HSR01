package Applicatie;

import Functions.Database;
import Functions.QueryParser;
import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class ExtendedTab extends Tab {

    //Attributes
    private String selectieId;
    private String selectieQuery;
    private final String origineleQuery;
    private TableView tableView;

    //Constructors
    public ExtendedTab(String titel, String query, String kolommenquery, String tabel, String primarykey) {
        origineleQuery = query;
        
        setText(titel);

        BorderPane borderPane = new BorderPane();

        //Toolbar voor boven de pakkettentabel
        ToolBar toolBar = new ToolBar();
        toolBar.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        toolBar.setMinHeight(50);

        //Buttons, TextFields en ComboBoxes worden gemaakt voor in de ToolBar
        Button BToevoegen = new Button();
        Button BVerwijderen = new Button();
        Button BHerladen = new Button();
        Button BZoeken = new Button();
        BZoeken.setDefaultButton(true);
        ComboBox CBZoeken = new ComboBox(Database.getKolommen(kolommenquery));
        CBZoeken.setId("zoeken-combo-box");
        CBZoeken.setPromptText("Categorie");
        CBZoeken.setPrefWidth(200);
        TextField TFZoeken = new TextField();
        TFZoeken.setId("zoeken-text-field");
        TFZoeken.setPromptText("Zoeken");

        //Icoontjes worden toegevoegd aan de Buttons
        ImageView icoonToevoegen = new ImageView("file:src/Images/AddIconWhite24.png");
        BToevoegen.setGraphic(icoonToevoegen);
        ImageView icoonVerwijderen = new ImageView("file:src/Images/DeleteIconWhite24.png");
        BVerwijderen.setGraphic(icoonVerwijderen);
        ImageView icoonHerladen = new ImageView("file:src/Images/RefreshIconWhite24.png");
        BHerladen.setGraphic(icoonHerladen);
        ImageView icoonZoeken = new ImageView("file:src/Images/SearchIconWhite24.png");
        BZoeken.setGraphic(icoonZoeken);

        //Alle items worden toegevoegd aan de ToolBar
        toolBar.getItems().addAll(
                BToevoegen,
                BVerwijderen,
                BHerladen,
                BZoeken,
                CBZoeken,
                TFZoeken
        );

        //ToolBar wordt toegevoegd aan de BorderPane
        borderPane.setTop(toolBar);

        //Zet de pakkettentabel in een StackPane en zet die in het midden van de BorderPane
        tableView = Database.getTableView(query);
        selectieQuery = query;
        StackPane pakketStackPane = new StackPane();
        pakketStackPane.getChildren().add(tableView);
        borderPane.setCenter(pakketStackPane);
        setContent(borderPane);

        //Functionaliteit wordt toegevoegd aan de Buttons
        BVerwijderen.setOnAction((ActionEvent event) -> {
            int index = tableView.getSelectionModel().getSelectedIndex();
            Database.delete(tabel, primarykey, selectieId);
            tableView.setItems(Database.getData(selectieQuery));
            tableView.getSelectionModel().select(index);
        });
        BHerladen.setOnAction((ActionEvent event) -> {
            tableView.setItems(Database.getData(query));
            selectieQuery = origineleQuery;
        });
        BZoeken.setOnAction((ActionEvent event) -> {
            if (!CBZoeken.getSelectionModel().isEmpty() && !(TFZoeken.getText().trim().isEmpty())) {
                String categorie = CBZoeken.getSelectionModel().getSelectedItem().toString();
                String zoekopdracht = "" + TFZoeken.getText();
                String zoekQuery = QueryParser.setCategorieZoekopdracht(query, categorie, zoekopdracht);
                tableView.setItems(Database.getData(zoekQuery));
                selectieQuery = zoekQuery;
            }
        });

        //Selectielistener voor pakkettabel
        tableView.getSelectionModel().selectedItemProperty().addListener((ObservableValue observableValue, Object oldValue, Object newValue) -> {
            //Kijkt welke rij is geselecteerd en haalt hier de 1e kolom (in principe de primary key) uit en slaat deze op in het attribuut selectieId
            if (tableView.getSelectionModel().getSelectedItem() != null) {
                ObservableList itemslist = tableView.getSelectionModel().getSelectedItems();
                for (Object rij : itemslist) {
                    ObservableList observableRij = (ObservableList) rij;
                    selectieId = (String) observableRij.get(0);
                }
            }
        });
        
        //Zorgt ervoor dat het tabje niet gesloten kan worden
        setClosable(false);
        
        //KeyListener zodat kan worden gezocht met enter
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();

                if (keyCode == KeyEvent.VK_ENTER) {
                    if (!CBZoeken.getSelectionModel().isEmpty() && !(TFZoeken.getText().trim().isEmpty())) {
                        String categorie = CBZoeken.getSelectionModel().getSelectedItem().toString();
                        String zoekopdracht = "" + TFZoeken.getText();
                        String zoekQuery = QueryParser.setCategorieZoekopdracht(query, categorie, zoekopdracht);
                        tableView.setItems(Database.getData(zoekQuery));
                        selectieQuery = zoekQuery;
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

    }
}
