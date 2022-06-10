package com.company.graphjava.controller;

import com.company.graphjava.MyExceptions;
import com.company.graphjava.graph.Algorithm;
import com.company.graphjava.graph.Files;
import com.company.graphjava.graph.Generator;
import com.company.graphjava.graph.Graph;
import com.company.graphjava.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.scene.control.Button;

import java.io.File;
import java.io.IOException;


public class MainWindowController {

    @FXML
    private Button exitButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button loadButton;
    @FXML
    private Button generateButton;
    @FXML
    private Button connectivityButton;
    @FXML
    private Button clearButton;
    @FXML
    private Canvas canvas;
    private GUIMonitor gui;
    private Integer lastVertexClicked;
    private Integer sourceVertex;

    public void onCanvasMouseClicked(MouseEvent event) {
        if (gui == null)
            return;
        Integer vertex = gui.findVertex(event.getX(), event.getY());
        if (vertex == null)
            return;
        if (lastVertexClicked != null && (int)vertex == (int)lastVertexClicked && sourceVertex == null) {
            sourceVertex = vertex;
            if (Algorithm.dijkstra((int)sourceVertex) == null)
                sourceVertex = null;
        }
        lastVertexClicked = vertex;
        if (sourceVertex != null && (int) sourceVertex != (int) lastVertexClicked) {
            gui.drawShortestPath((int) sourceVertex, (int) lastVertexClicked);
        }
        System.out.println();
        System.out.println("source vertex = " + sourceVertex + "     lastVertexClicked = " + lastVertexClicked);
    }
    public void onExitButtonClicked() {
        Platform.exit();
    }

    public void onSettingsButtonClicked() throws IOException {
        new SettingsWindowController().switchToSettingsScene();
    }

    public void onSaveButtonClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save the generated graph");
        File graphFile = fileChooser.showSaveDialog(Main.getMainWindow().getMainStage());
        if (graphFile == null)
            return;
        try {
            Files.fileCreate((graphFile.getPath()).toString());
        } catch (IOException e) {
            showErrorDialog("Cannot write graph to this file");
        } catch (NullPointerException e) {
            showErrorDialog("Generate or load graph before saving");
        }
    }

    public void onLoadButtonClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load a graph from file");
        File graphFile = fileChooser.showOpenDialog(Main.getMainWindow().getMainStage());
        if (graphFile == null)
            return;
        try {
            Files.fileRead((graphFile.getPath()).toString());
        } catch (IOException e) {
            showErrorDialog("Cannot open chosen file");
        } catch (NumberFormatException e) {
            showErrorDialog("Bad file format");
        } catch (MyExceptions.FileFormatException e) {
            showErrorDialog("Bad file format, graph should be grid graph");
        }
    }

    public void onGenerateButtonClicked() {
        sourceVertex = null;
        if (Main.getSettings().getRows() == null) {
            showErrorDialog("Graph parameters not specified! Open up settings windows and fill in the blanks.");
            return;
        }

        Graph graph = new Graph();
        Generator.generateGridGraph(graph);
        Main.setGraph(graph);
        System.out.println("Wygenerowano graf");

        GraphicsContext grc = canvas.getGraphicsContext2D();
        gui = new GUIMonitor(grc, 600, 600);
        gui.drawGraph();
    }

    public void onConnectivityButtonClicked() {
      //  Algorithm.bfs(Main.getGraph());

    }

    public void onClearButtonClicked() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void showErrorDialog( String text ) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        //alert.setTitle("Błąd");
        //alert.setHeaderText("No parameters passed");
        alert.setContentText(text);

        alert.showAndWait();
    }
}