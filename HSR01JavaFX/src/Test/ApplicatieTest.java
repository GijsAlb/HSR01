package Test;

import Login.*;
import Functions.DatabaseEenVeld;
import Functions.DatabaseArrayListHashMap;
import Functions.Hasher;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

/**
 *
 * @author Bram
 */
public class ApplicatieTest extends Application {

    //Attributes
    private String checkGebruikersnaam, checkWachtwoord;
    private ArrayList<LinkedHashMap<String, String>> gebruikersnamenEnWachtwoorden;

    //Constructors
    public ApplicatieTest() {
        DatabaseArrayListHashMap dba = new DatabaseArrayListHashMap();
        gebruikersnamenEnWachtwoorden = new ArrayList<>(dba.fetchData("SELECT gebruikersnaam, wachtwoord FROM backoffice_account;", "gebruikersnaam", "wachtwoord"));
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pakketmonitor");

        GridPane grid = new GridPane();

        grid.setAlignment(Pos.CENTER);
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
        final Text TMelding = new Text();

        //Nodes toevoegen aan de GridPane
        grid.add(LGebruikersnaam, 0, 1);
        grid.add(TFGebruikersnaam, 1, 1);
        grid.add(LWachtwoord, 0, 2);
        grid.add(PFWachtwoord, 1, 2);
        HBox HBInloggen = new HBox(10);
        HBInloggen.setAlignment(Pos.BOTTOM_RIGHT);
        HBInloggen.getChildren().add(BInloggen);
        grid.add(HBInloggen, 1, 4);
        HBox HBMelding = new HBox(10);
        HBMelding.setAlignment(Pos.CENTER_RIGHT);
        HBMelding.getChildren().add(TMelding);
        grid.add(HBMelding, 1, 6);

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
                    it.remove();
                }
                if (doorgaan) {
                    TMelding.setFill(Color.FIREBRICK);
                    TMelding.setText("Gebruikersnaam en/of \nwachtwoord is onjuist. \nProbeer het opnieuw.");
                } else {
                    TMelding.setFill(Color.DARKGREEN);
                    TMelding.setText("Gebruikersnaam en wachtwoord zijn juist");
                }
                TFGebruikersnaam.setText("");
                PFWachtwoord.setText("");
            }
        });

        grid.setGridLinesVisible(true);
        Scene scene = new Scene(grid, 400, 275);
//        scene.getStylesheets().add(getClass().getClassLoader().getResource("login.css").toExternalForm());
        primaryStage.setScene(scene);
//        primaryStage.setResizable(false);

        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
