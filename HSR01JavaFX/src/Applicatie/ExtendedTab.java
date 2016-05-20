package Applicatie;

import Functions.Database.DatabaseKolommenObservableList;
import Functions.Database.DatabaseObservableList;
import Functions.Database.DatabaseTableView;
import Functions.Database.DatabaseVerwijder;
import Functions.QueryParser;
import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javafx.beans.value.ChangeListener;
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
    private TableView tabel;

    //Constructors
    public ExtendedTab(String titel, String query, String kolommenquery, String deleteQuery) {
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
        ComboBox CBZoeken = new ComboBox(DatabaseKolommenObservableList.fetchData(kolommenquery));
        CBZoeken.setId("zoeken-combo-box");
        CBZoeken.setPromptText("Categorie");
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
        tabel = DatabaseTableView.fetchData(query);
        StackPane pakketStackPane = new StackPane();
        pakketStackPane.getChildren().add(tabel);
        borderPane.setCenter(pakketStackPane);
        setContent(borderPane);

        //Functionaliteit wordt toegevoegd aan de Buttons
        BVerwijderen.setOnAction((ActionEvent event) -> {
            int index = tabel.getSelectionModel().getSelectedIndex();
            DatabaseVerwijder.verwijder(deleteQuery, selectieId);
            tabel.setItems(DatabaseObservableList.fetchData(query));
            tabel.getSelectionModel().select(index);
        });
        BHerladen.setOnAction((ActionEvent event) -> {
            tabel.setItems(DatabaseObservableList.fetchData(query));
        });
        BZoeken.setOnAction((ActionEvent event) -> {
            if (!CBZoeken.getSelectionModel().isEmpty() && !(TFZoeken.getText().trim().isEmpty())) {
                String categorie = CBZoeken.getSelectionModel().getSelectedItem().toString();
                String zoekopdracht = "" + TFZoeken.getText();
                String zoekQuery = QueryParser.setCategorieZoekopdracht(query, categorie, zoekopdracht);
                tabel.setItems(DatabaseObservableList.fetchData(zoekQuery));
            }
        });

        //Selectielistener voor pakkettabel
        tabel.getSelectionModel().selectedItemProperty().addListener((ObservableValue observableValue, Object oldValue, Object newValue) -> {
            //Kijkt welke rij is geselecteerd en haalt hier de 1e kolom (in principe de primary key) uit en slaat deze op in het attribuut selectieId
            if (tabel.getSelectionModel().getSelectedItem() != null) {
                ObservableList itemslist = tabel.getSelectionModel().getSelectedItems();
                for (Object rij : itemslist) {
                    ObservableList observableRij = (ObservableList) rij;
                    selectieId = (String) observableRij.get(0);
                    System.out.println(selectieId);
                }
            }
        });

        setClosable(false);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                char keyChar = e.getKeyChar();
                int keyCodeExtended = e.getExtendedKeyCode();

                System.out.println(keyCode);
                System.out.println(keyChar);
                System.out.println(keyCodeExtended);

                if (keyCode == KeyEvent.VK_ENTER) {
                    if (!CBZoeken.getSelectionModel().isEmpty() && !(TFZoeken.getText().trim().isEmpty())) {
                        String categorie = CBZoeken.getSelectionModel().getSelectedItem().toString();
                        String zoekopdracht = "" + TFZoeken.getText();
                        String zoekQuery = QueryParser.setCategorieZoekopdracht(query, categorie, zoekopdracht);
                        tabel.setItems(DatabaseObservableList.fetchData(zoekQuery));
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }
}
