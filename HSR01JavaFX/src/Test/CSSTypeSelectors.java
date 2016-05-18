package Test;

import Applicatie.*;
import Config.config;
import Functions.Scenes.LoginScene;
import javafx.application.Application;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CSSTypeSelectors extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Pakketmonitor");
        stage.getIcons().add(new Image(config.ICON));
        
        GridPane grid = new GridPane();
        System.out.println(grid.getTypeSelector());
        ToggleButton toggle = new ToggleButton("toggle");
        System.out.println(toggle.getTypeSelector());
        
        stage.setScene(LoginScene.getScene(stage));
        
        stage.setResizable(false);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}