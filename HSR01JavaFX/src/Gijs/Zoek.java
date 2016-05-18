package Gijs;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Gijs
 */
public class Zoek {
    private TextField zoekVeld;
    private Button zoekKnop;
    public Zoek() {
        Image vergrootGlas = new Image(getClass().getResourceAsStream("vergrootglas.jpg"));
        zoekVeld = new TextField();
        zoekKnop = new Button();
        zoekKnop.setGraphic(new ImageView(vergrootGlas));
    }
}
