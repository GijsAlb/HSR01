package Applicatie;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Applicatie extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pakketmonitor");
        
        LoginGridPane grid = new LoginGridPane();
        
//        grid.setGridLinesVisible(true);
        Scene scene = new Scene(grid, 400, 250);
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