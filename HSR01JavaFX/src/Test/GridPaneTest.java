package Test;

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

public class GridPaneTest extends GridPane {
    //Constructors
    public GridPaneTest() {
        setAlignment(Pos.TOP_CENTER);
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(25, 25, 25, 25));
        
        Text scenetitel = new Text("Welkom bij het tweede scherm!");
        scenetitel.setFont(Font.font("Arial", FontWeight.NORMAL, 30));
        add(scenetitel, 0, 0, 2, 1);
        
        //Nodes aanmaken
        Label LGebruikersnaam = new Label("Gebruikersnaam:");
        TextField TFGebruikersnaam = new TextField();
        Label LWachtwoord = new Label("Wachtwoord:");
        PasswordField PFWachtwoord = new PasswordField();
        Button BInloggen = new Button("Inloggen");
        final Text TMelding = new Text();
        
        //Nodes toevoegen aan de GridPane
        add(LGebruikersnaam, 0, 2);
        add(TFGebruikersnaam, 1, 2);
        add(LWachtwoord, 0, 1);
        add(PFWachtwoord, 1, 1);
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
                System.out.println("De knop van het tweede scherm is ingedrukt!");
            }
        });
        
    }
    
}
