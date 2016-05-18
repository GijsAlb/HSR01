package Applicatie;

import Config.config;
import Functions.Scenes.LoginScene;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Applicatie extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Pakketmonitor");
        stage.getIcons().add(new Image(config.ICON));
        
        stage.setScene(LoginScene.getScene(stage));
        
        stage.setResizable(false);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}