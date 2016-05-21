package Applicatie;

import Verouderd.DatabaseArrayListHashMap;
import Functions.Hasher;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginScene extends Scene {

    //Attributes
    private ArrayList<LinkedHashMap<String, String>> gebruikersnamenEnWachtwoorden;

    //Constructors
    public LoginScene(Stage stage, GridPane root, double width, double height) {
        super(root, width, height);

        gebruikersnamenEnWachtwoorden = new ArrayList<>(DatabaseArrayListHashMap.fetchData("SELECT gebruikersnaam, wachtwoord FROM backoffice_account;", "gebruikersnaam", "wachtwoord"));

        root.setAlignment(Pos.TOP_CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitel = new Text("Welkom!");
        scenetitel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        root.add(scenetitel, 0, 0, 2, 1);

        //Nodes aanmaken
        Label LGebruikersnaam = new Label("Gebruikersnaam:");
        TextField TFGebruikersnaam = new TextField();
        Label LWachtwoord = new Label("Wachtwoord:");
        PasswordField PFWachtwoord = new PasswordField();
        Button BInloggen = new Button("Inloggen");
        Button BAfsluiten = new Button("Afsluiten");
        final Text TMelding = new Text();

        //Nodes toevoegen aan de GridPane
        root.add(LGebruikersnaam, 0, 1);
        root.add(TFGebruikersnaam, 1, 1);
        root.add(LWachtwoord, 0, 2);
        root.add(PFWachtwoord, 1, 2);
        HBox HBInloggen = new HBox(10);
        HBInloggen.setAlignment(Pos.BOTTOM_RIGHT);
        HBInloggen.getChildren().add(BInloggen);
        BInloggen.setDefaultButton(true);
        root.add(HBInloggen, 1, 4);
        HBox HBAfsluiten = new HBox(10);
        HBAfsluiten.setAlignment(Pos.BOTTOM_LEFT);
        HBAfsluiten.getChildren().add(BAfsluiten);
        BAfsluiten.setCancelButton(true);
        root.add(HBAfsluiten, 0, 4);
        HBox HBMelding = new HBox(10);
        HBMelding.setAlignment(Pos.CENTER_RIGHT);
        HBMelding.getChildren().add(TMelding);
        root.add(HBMelding, 1, 6);

        //Handling voor inlogknop
        BInloggen.setOnAction((ActionEvent e) -> {
            String checkGebruikersnaam, checkWachtwoord;
            checkGebruikersnaam = TFGebruikersnaam.getText();
            checkWachtwoord = Hasher.hash("SHA-512", PFWachtwoord.getText());
            boolean doorgaan = true;
            forloop:
            for (LinkedHashMap<String, String> lhm : gebruikersnamenEnWachtwoorden) {
                Iterator it = lhm.entrySet().iterator();
                while (it.hasNext() && doorgaan) {
                    Map.Entry pair = (Map.Entry) it.next();
                    if (checkGebruikersnaam.equals(pair.getKey()) && checkWachtwoord.equals(pair.getValue())) {
                        doorgaan = false;
                        break forloop;
                    }
                }
            }
            if (doorgaan) {
                TMelding.setFill(Color.FIREBRICK);
                TMelding.setText("Gebruikersnaam en/of \nwachtwoord is onjuist. \nProbeer het opnieuw.");
            } else {
                TMelding.setFill(Color.DARKGREEN);
                TMelding.setText("Gebruikersnaam en \nwachtwoord zijn juist");
                //Haalt resolutie op, en zet width en height om naar 67% (ongeveer 1080x720 bij een resolutie van 1920x1080) zodat het scherm op andere resoluties niet te groot wordt
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                double breedte = screenSize.getWidth() * 0.66667;
                double hoogte = screenSize.getHeight() * 0.66667;
                TabPane overzichtTabPane = new TabPane();
                stage.setScene(new OverzichtScene(stage, overzichtTabPane, breedte, hoogte));
                stage.setResizable(true);
                stage.centerOnScreen();
//                    stage.setFullScreen(true);
            }
            TFGebruikersnaam.setText("");
            PFWachtwoord.setText("");
        });
        
        //Handling voor afsluitknop
        BAfsluiten.setOnAction((ActionEvent e) -> {
            Platform.exit();
        });

        getStylesheets().add("file:src/CSS/JMetroLightTheme.css");
    }

}
