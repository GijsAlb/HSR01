package Applicatie;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Applicatie extends Application {
    
    @Override
    public void start(Stage stage) {
        stage.setTitle("TZT Monitor");
        stage.getIcons().add(new Image("file:src/Images/appIconLocomotief.png"));
        
        GridPane inlogGrid = new GridPane();
        stage.setScene(new LoginScene(stage, inlogGrid, 400, 250));
        
        stage.setResizable(false);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}