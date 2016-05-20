package Applicatie;

import Config.config;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class OverzichtScene extends Scene {
    //Constructors
    public OverzichtScene(Stage stage, TabPane root, double width, double height) {
        super(root, width, height);
        
        //Tabje met overzicht pakketten
        ExtendedTab pakketTab = new ExtendedTab("Pakketten", config.PAKKETQUERY, config.PAKKETKOLOMMENQUERY, config.PAKKETDELETE);
        
        //Tabje met overzicht treinkoeriers
        ExtendedTab treinkoerierTab = new ExtendedTab("Treinkoeriers", config.TREINKOERIERQUERY, config.TREINKOERIERKOLOMMENQUERY, config.TREINKOERIERDELETE);

        //Tabs toevoegen aan de TabPane
        root.getTabs().addAll(pakketTab, treinkoerierTab);

        //Stylesheet wordt toegevoegd
        getStylesheets().add("file:src/CSS/JMetroLightTheme.css");
    }

}