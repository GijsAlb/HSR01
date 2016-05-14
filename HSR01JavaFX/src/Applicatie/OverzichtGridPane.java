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
        
        Text overzichtTitel = new Text("Welkom bij het tweede scherm!");
        overzichtTitel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 5));
        add(overzichtTitel, 0, 0);
    }
}