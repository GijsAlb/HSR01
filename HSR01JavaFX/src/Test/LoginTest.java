package Test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginTest extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pakketmonitor");
        
        LoginGridPaneTest grid = new LoginGridPaneTest();
        
//        grid.setGridLinesVisible(true);
        Scene scene = new Scene(grid, 400, 275);
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
