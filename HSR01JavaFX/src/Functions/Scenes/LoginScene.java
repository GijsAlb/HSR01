package Functions.Scenes;

import Config.config;
import Functions.Database.DatabaseArrayListHashMap;
import Functions.Hasher;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginScene {

    public static Scene getScene(Stage stage) {
        GridPane grid = new GridPane();

        ArrayList<LinkedHashMap<String, String>> gebruikersnamenEnWachtwoorden;
        gebruikersnamenEnWachtwoorden = new ArrayList<>(DatabaseArrayListHashMap.fetchData("SELECT gebruikersnaam, wachtwoord FROM backoffice_account;", "gebruikersnaam", "wachtwoord"));

        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitel = new Text("Welkom!");
        scenetitel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitel, 0, 0, 2, 1);

        //Nodes aanmaken
        Label LGebruikersnaam = new Label("Gebruikersnaam:");
        TextField TFGebruikersnaam = new TextField();
        Label LWachtwoord = new Label("Wachtwoord:");
        PasswordField PFWachtwoord = new PasswordField();
        Button BInloggen = new Button("Inloggen");
        Button BAfsluiten = new Button("Afsluiten");
        final Text TMelding = new Text();

        //Nodes toevoegen aan de GridPane
        grid.add(LGebruikersnaam, 0, 1);
        grid.add(TFGebruikersnaam, 1, 1);
        grid.add(LWachtwoord, 0, 2);
        grid.add(PFWachtwoord, 1, 2);
        HBox HBInloggen = new HBox(10);
        HBInloggen.setAlignment(Pos.BOTTOM_RIGHT);
        HBInloggen.getChildren().add(BInloggen);
//        BInloggen.setDefaultButton(true);
        grid.add(HBInloggen, 1, 4);
        HBox HBAfsluiten = new HBox(10);
        HBAfsluiten.setAlignment(Pos.BOTTOM_LEFT);
        HBAfsluiten.getChildren().add(BAfsluiten);
//        BAfsluiten.setCancelButton(true);
        grid.add(HBAfsluiten, 0, 4);
        HBox HBMelding = new HBox(10);
        HBMelding.setAlignment(Pos.CENTER_RIGHT);
        HBMelding.getChildren().add(TMelding);
        grid.add(HBMelding, 1, 6);
        
        //Handling voor BInloggen
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
//                    System.out.println(pair.getKey() + " " + pair.getValue());
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
                stage.setScene(OverzichtScene.getScene(stage));
                stage.setResizable(true);
                stage.centerOnScreen();
//                    stage.setFullScreen(true);
            }
            TFGebruikersnaam.setText("");
            PFWachtwoord.setText("");
        });
        BAfsluiten.setOnAction((ActionEvent e) -> {
            Platform.exit();
        });

        Scene loginScene = new Scene(grid, 400, 250);
        loginScene.getStylesheets().add("file:src/CSS/JMetroLightTheme.css");
        loginScene.setRoot(grid);
        return loginScene;
    }
}