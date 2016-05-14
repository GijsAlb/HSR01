package Applicatie;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;

public class FactuursysteemGridPane extends GridPane {
    //Attributes
    private TableView tableview;
    
    //Constructors
    public FactuursysteemGridPane() {
        setAlignment(Pos.TOP_CENTER);
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(25, 25, 25, 25));
        
        
    }
}