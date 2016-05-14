package Applicatie;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class OverzichtGridPane extends GridPane {
    //Constructors
    public OverzichtGridPane() {
        setAlignment(Pos.TOP_CENTER);
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(25, 25, 25, 25));
        
        getScene().
        Text scenetitel = new Text("Welkom bij het tweede scherm!");
        scenetitel.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        add(scenetitel, 0, 0, 2, 1);
    }
}
