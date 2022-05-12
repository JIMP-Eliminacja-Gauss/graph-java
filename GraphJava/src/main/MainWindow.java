package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainWindow extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("MainWindowInterface.fxml")));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Graph");
        stage.show();
    }

    public void run() {
        launch();
    }
}
