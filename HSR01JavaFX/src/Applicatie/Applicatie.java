package Applicatie;

import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Applicatie extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Pakketmonitor");
        
        stage.setScene(new LoginScene(stage, new GridPane(), 400, 250));
        
//        stage.setResizable(false);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}