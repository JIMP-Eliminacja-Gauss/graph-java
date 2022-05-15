package com.company.graphjava;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindow extends Application {
    private final int WIN_WIDTH = 750;
    private final int WIN_HEIGHT = 630;
    private Stage mainStage;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("graph.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WIN_WIDTH, WIN_HEIGHT);
        stage.setTitle("Graph");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        mainStage = stage;
    }

    public Stage getMainStage() {
        return mainStage;
    }

    public void run() {
        launch();
    }
}
