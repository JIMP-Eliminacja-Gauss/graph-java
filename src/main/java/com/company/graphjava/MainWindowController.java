package com.company.graphjava;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.scene.control.Button;

import java.io.File;
import java.io.IOException;

import static javafx.stage.Modality.APPLICATION_MODAL;


public class MainWindowController {
    public Button exitButton;
    public Button settingsButton;
    public Button saveButton;
    public Button loadButton;
    public Button generateButton;

    public void onExitButtonClicked() {
        Platform.exit();
    }

    public void onSettingsButtonClicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("settings.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600,150);
        Stage stage = new Stage();
        stage.setTitle("Settings");
        stage.setScene(scene);
        stage.setResizable(false);

        // sprawia że nie można wykonywać żadnych akcji przed zamknięciem się okna ustawień
        stage.initModality(APPLICATION_MODAL);
        Main.getSettings.settingsStage = stage;
        stage.show();
    }

    public void onSaveButtonClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save the generated graph");
        File graphFile = fileChooser.showSaveDialog(Main.mainWindow.getMainStage());
    }

    public void onLoadButtonClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load a graph from file");
        File graphFile = fileChooser.showOpenDialog(Main.mainWindow.getMainStage());
    }

    public void onGenerateButtonClicked() {
        // TODO: jezeli ustawienia sie zmienily, dodac indykator
        if (Main.getSettings().rows == null)
            return;

        Graph graph = new Graph();
        Generator generator = new Generator();
        generator.generateGridGraph(graph);
        System.out.println("Wygenerowano graf");
    }
}