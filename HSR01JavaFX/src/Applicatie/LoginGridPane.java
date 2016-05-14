package Applicatie;

import Functions.DatabaseArrayListHashMap;
import Functions.Hasher;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

public class LoginGridPane extends GridPane {
    //Attributes
    private String checkGebruikersnaam, checkWachtwoord;
    private ArrayList<LinkedHashMap<String, String>> gebruikersnamenEnWachtwoorden;
    
    //Constructors
    public LoginGridPane() {
        DatabaseArrayListHashMap dba = new DatabaseArrayListHashMap();
        gebruikersnamenEnWachtwoorden = new ArrayList<>(dba.fetchData("SELECT gebruikersnaam, wachtwoord FROM backoffice_account;", "gebruikersnaam", "wachtwoord"));
        
        setAlignment(Pos.TOP_CENTER);
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(25, 25, 25, 25));
        
        Text scenetitel = new Text("Welkom!");
        scenetitel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        add(scenetitel, 0, 0, 2, 1);
        
        //Nodes aanmaken
        Label LGebruikersnaam = new Label("Gebruikersnaam:");
        TextField TFGebruikersnaam = new TextField();
        Label LWachtwoord = new Label("Wachtwoord:");
        PasswordField PFWachtwoord = new PasswordField();
        Button BInloggen = new Button("Inloggen");
        final Text TMelding = new Text();
        
        //Nodes toevoegen aan de GridPane
        add(LGebruikersnaam, 0, 1);
        add(TFGebruikersnaam, 1, 1);
        add(LWachtwoord, 0, 2);
        add(PFWachtwoord, 1, 2);
        HBox HBInloggen = new HBox(10);
        HBInloggen.setAlignment(Pos.BOTTOM_RIGHT);
        HBInloggen.getChildren().add(BInloggen);
        add(HBInloggen, 1, 4);
        HBox HBMelding = new HBox(10);
        HBMelding.setAlignment(Pos.CENTER_RIGHT);
        HBMelding.getChildren().add(TMelding);
        add(HBMelding, 1, 6);
        
        //Handling voor BTInloggen
        BInloggen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                checkGebruikersnaam = TFGebruikersnaam.getText();
                Hasher hasher = new Hasher();
                checkWachtwoord = hasher.hash("SHA-512", PFWachtwoord.getText());
                boolean doorgaan = true;
                forloop:
                for (LinkedHashMap<String, String> lhm : gebruikersnamenEnWachtwoorden) {
                    Iterator it = lhm.entrySet().iterator();
                    while (it.hasNext() && doorgaan) {
                        Map.Entry pair = (Map.Entry) it.next();
                        System.out.println(pair.getKey() + " " + pair.getValue());
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
                    OverzichtGridPane overzichtGrid = new OverzichtGridPane();
                    getScene().setRoot(overzichtGrid);
                }
                TFGebruikersnaam.setText("");
                PFWachtwoord.setText("");
            }
        });
        
    }
    
}