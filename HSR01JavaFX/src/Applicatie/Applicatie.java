package Applicatie;

import javafx.application.Application;
import javafx.stage.Stage;

public class Applicatie extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Pakketmonitor");

        stage.setScene(SceneFunction.loginScene(stage));

//        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
