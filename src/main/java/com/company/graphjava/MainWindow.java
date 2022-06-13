package com.company.graphjava;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainWindow extends Application {
    private Stage mainStage;

    @Override
    public void start(Stage stage) throws IOException {
        int WIN_WIDTH = 750;
        int WIN_HEIGHT = 630;

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("graph.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WIN_WIDTH, WIN_HEIGHT);
        stage.setTitle("Graph");
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("graphIcon.jpg"))));
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
