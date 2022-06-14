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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
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
    @FXML
    private Text messagePanel;

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
            showSuccessMessage("Chose vertex #" + sourceVertex + " as source vertex");
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
        boolean thrownException = false;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save the generated graph");
        File graphFile = fileChooser.showSaveDialog(Main.getMainWindow().getMainStage());
        if (graphFile == null)
            return;
        try {
            Files.fileCreate((graphFile.getPath()).toString());
        } catch (IOException e) {
            showErrorDialog("Cannot write graph to this file");
            thrownException = true;
        } catch (NullPointerException e) {
            showErrorDialog("Generate or load graph before saving");
            thrownException = true;
        }

        if (thrownException)
            showErrorMessage("Graph hasn't been saved");
        else
            showSuccessMessage("Graph has been successfully saved to file");
    }

    public void onLoadButtonClicked() {
        sourceVertex = null;
        boolean thrownException = false;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load a graph from file");
        File graphFile = fileChooser.showOpenDialog(Main.getMainWindow().getMainStage());
        if (graphFile == null)
            return;
        try {
            Files.fileRead((graphFile.getPath()).toString());
        } catch (IOException e) {
            showErrorDialog("Cannot open chosen file");
            thrownException = true;
        } catch (NumberFormatException e) {
            showErrorDialog("Bad file format");
            thrownException = true;
        } catch (MyExceptions.FileFormatException e) {
            showErrorDialog("Bad file format, graph should be grid graph");
            thrownException = true;
        }

        if (thrownException)
            showErrorMessage("Cannot load graph from file");
        else
            showSuccessMessage("Graph has been successfully loaded. Choose the source vertex by double clicking on it");

        GraphicsContext grc = canvas.getGraphicsContext2D();
        if (gui == null)
            gui = new GUIMonitor(grc, 600, 600);

        Thread t1 = new Thread(gui);
        t1.start();




    }

    public void onGenerateButtonClicked() throws InterruptedException {
        sourceVertex = null;
        if (Main.getSettings().getRows() == null) {
            showErrorDialog("Graph parameters not specified! Open up settings windows and fill in the blanks.");
            return;
        }

        Graph graph = new Graph();
        Thread t1 = new Thread(new Generator(graph));
        t1.start();
        Main.setGraph(graph);
        System.out.println("Wygenerowano graf");
        t1.join();

        GraphicsContext grc = canvas.getGraphicsContext2D();
        if (gui == null)
            gui = new GUIMonitor(grc, 600, 600);

        // draws Graph
        Thread t2 = new Thread(gui);
        t2.start();

        int col = Main.getGraph().getColumns();
        int row = Main.getGraph().getRows();
        showSuccessMessage(row + " x " + col + " graph has been generated. Choose the source vertex by double clicking on it");
    }

    public void onConnectivityButtonClicked() {
      boolean connected =  Algorithm.bfs(Main.getGraph());
      if (connected) {
          showSuccessMessage("Graph is connected");
      } else
          showErrorMessage("Graph is not connected");


    }

    public void onClearButtonClicked() throws InterruptedException {
        Thread thread = new Thread(gui);
        thread.start();
        thread.join();
        sourceVertex = null;
        showSuccessMessage("Graph has been successfully cleared. Choose the source vertex by double clicking on it");
    }

    private void showErrorDialog( String text ) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        //alert.setTitle("Błąd");
        //alert.setHeaderText("No parameters passed");
        alert.setContentText(text);

        alert.showAndWait();
    }

    private void showSuccessMessage(String s) {
        messagePanel.setText(s);
        messagePanel.setFill(Color.GREEN);
        messagePanel.setVisible(true);
    }

    private void showErrorMessage(String s) {
        messagePanel.setText(s);
        messagePanel.setFill(Color.RED);
        messagePanel.setVisible(true);
    }
}