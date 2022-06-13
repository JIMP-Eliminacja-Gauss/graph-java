package com.company.graphjava.controller;

import com.company.graphjava.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static javafx.stage.Modality.APPLICATION_MODAL;

public class SettingsWindowController {

    // przyciski
    @FXML
    private Button applyButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button okButton;

    // Pola tekstowe
    @FXML
    private TextField rowsTextField;
    @FXML
    private TextField columnsTextField;
    @FXML
    private TextField probabilityTextField;
    @FXML
    private TextField edgeWeightRangeFromTextField;
    @FXML
    private TextField edgeWeightRangeToTextField;


    private Integer rows;
    private Integer columns;
    private Double probability;
    private Double edgeWeightRangeFrom;
    private Double edgeWeightRangeTo;

    private boolean changesApplied = false;


    public void onApplyButtonClicked(ActionEvent event) {
        rows = readIntFromTextField(rowsTextField);
        columns = readIntFromTextField(columnsTextField);
        probability = readDoubleFromTextField(probabilityTextField);
        edgeWeightRangeFrom = readDoubleFromTextField(edgeWeightRangeFromTextField);
        edgeWeightRangeTo = readDoubleFromTextField(edgeWeightRangeToTextField);

        if (edgeWeightRangeFrom != null && edgeWeightRangeTo != null && edgeWeightRangeFrom > edgeWeightRangeTo) {
            edgeWeightRangeFromTextField.clear();
            edgeWeightRangeToTextField.clear();
            edgeWeightRangeTo = null;
            edgeWeightRangeFrom = null;

        }
        changesApplied = true;
    }

    public void onCancelButtonClicked(ActionEvent event) {
        Main.getSettings().getSettingsStage().close();
    }

    public void onOkButtonClicked(ActionEvent event) {
        if (!changesApplied)
            onApplyButtonClicked(null);

        if (rows != null && columns != null && probability != null && edgeWeightRangeFrom != null && edgeWeightRangeTo != null) {
            Main.getSettings().setRows(rows);
            Main.getSettings().setColumns(columns);
            Main.getSettings().setProbability(probability);
            Main.getSettings().setEdgeWeightRangeFrom(edgeWeightRangeFrom);
            Main.getSettings().setEdgeWeightRangeTo(edgeWeightRangeTo);
            Main.getSettings().getSettingsStage().close();
        } else {
            showErrorDialog("all parameters should be given");
        }

        changesApplied = false;
    }
/*
    public void onClearButtonClicked(ActionEvent event) {
        rowsTextField.clear();
        columnsTextField.clear();
        probabilityTextField.clear();
        edgeWeightRangeFromTextField.clear();
        edgeWeightRangeToTextField.clear();
    }

    public void onCurrentSettingsButtonClicked(ActionEvent event) {
        if (Main.getSettings().getRows() != null)
            rowsTextField.setText(String.valueOf(Main.getSettings().getRows()));
        if (Main.getSettings().getColumns() != null)
            columnsTextField.setText(String.valueOf(Main.getSettings().getColumns()));
        if (Main.getSettings().getProbability() != null)
            probabilityTextField.setText(String.valueOf(Main.getSettings().getProbability()));
        if (Main.getSettings().getEdgeWeightRangeFrom() != null)
            edgeWeightRangeFromTextField.setText(String.valueOf(Main.getSettings().getEdgeWeightRangeFrom()));
        if (Main.getSettings().getEdgeWeightRangeTo() != null)
            edgeWeightRangeToTextField.setText(String.valueOf(Main.getSettings().getEdgeWeightRangeTo()));
    }
*/
    private Integer readIntFromTextField (TextField tf) throws NumberFormatException {
        int number;
        tf.setStyle("-fx-border-color: transparent");
        try {
            number = Integer.parseInt(tf.getText());
        } catch (NumberFormatException e) {
            tf.clear();
            tf.setStyle("-fx-border-color: red");
            return null;
        }
        if (number <= 0) {
            tf.clear();
            tf.setStyle("-fx-border-color: red");
            return null;
        }
        return number;
    }

    private Double readDoubleFromTextField (TextField tf) throws NumberFormatException {
        double number;
        tf.setStyle("-fx-border-color: transparent");
        try {
            number = Double.parseDouble(tf.getText());
        } catch (NumberFormatException e) {
            tf.clear();
            tf.setStyle("-fx-border-color: red");
            return null;
        }

        if (tf == probabilityTextField && number > 1) {
            tf.clear();
            tf.setStyle("-fx-border-color: red");
            return null;
        }


        if (number < 0) {
            tf.clear();
            tf.setStyle("-fx-border-color: red");
            return null;
        }
        return number;
    }

    public void showErrorDialog( String text ) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        //alert.setTitle("Błąd");
        alert.setHeaderText(null);
        alert.setContentText(text);

        alert.showAndWait();
    }

    public void switchToSettingsScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("settings.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600,120);
        Stage stage = new Stage();
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("settingsIcon.jpg"))));


        stage.setTitle("Settings");
        stage.setScene(scene);
        stage.setResizable(false);


        SettingsWindowController controller = fxmlLoader.getController();

        // wyswietlanie aktualnych ustawien
        if (Main.getSettings().getRows() != null) {
            controller.rowsTextField.setText(Main.getSettings().getRows().toString());
            controller.columnsTextField.setText(Main.getSettings().getColumns().toString());
            controller.probabilityTextField.setText(Main.getSettings().getProbability().toString());
            controller.edgeWeightRangeFromTextField.setText(Main.getSettings().getEdgeWeightRangeFrom().toString());
            controller.edgeWeightRangeToTextField.setText(Main.getSettings().getEdgeWeightRangeTo().toString());
        }


        stage.initModality(APPLICATION_MODAL);
        Main.getSettings().setSettingsStage(stage);
        stage.show();
    }

    public void handleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            okButton.fire();
        else if (event.getCode() == KeyCode.ESCAPE)
            cancelButton.fire();
    }

}
