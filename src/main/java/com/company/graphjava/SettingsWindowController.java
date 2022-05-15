package com.company.graphjava;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.stage.Modality.APPLICATION_MODAL;

public class SettingsWindowController {

    public Button applyButton;
    public Button cancelButton;
    public Button okButton;
    public Button currentSettingsButton;
    public Button clearButton;

    public TextField rowsTextField;
    public TextField columnsTextField;
    public TextField probabilityTextField;
    public TextField edgeWeightRangeFromTextField;
    public TextField edgeWeightRangeToTextField;

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

        }
        changesApplied = true;
    }

    public void onCancelButtonClicked(ActionEvent event) {
        Main.settings.settingsStage.close();
    }

    public void onOkButtonClicked(ActionEvent event) {
        if (!changesApplied)
            onApplyButtonClicked(null);

        if (rows != null && columns != null && probability != null && edgeWeightRangeFrom != null && edgeWeightRangeTo != null) {
            Main.settings.rows = rows;
            Main.settings.columns = columns;
            Main.settings.probability = probability;
            Main.settings.edgeWeightRangeFrom = edgeWeightRangeFrom;
            Main.settings.edgeWeightRangeTo = edgeWeightRangeTo;
            Main.settings.settingsStage.close();
        } else {
            showErrorDialog("all parameters should be given");
        }

        changesApplied = false;
    }

    public void onClearButtonClicked(ActionEvent event) {
        rowsTextField.clear();
        columnsTextField.clear();
        probabilityTextField.clear();
        edgeWeightRangeFromTextField.clear();
        edgeWeightRangeToTextField.clear();
    }

    public void onCurrentSettingsButtonClicked(ActionEvent event) {
        if (Main.settings.rows != null)
            rowsTextField.setText(String.valueOf(Main.settings.rows));
        if (Main.settings.columns != null)
            columnsTextField.setText(String.valueOf(Main.settings.columns));
        if (Main.settings.probability != null)
            probabilityTextField.setText(String.valueOf(Main.settings.probability));
        if (Main.settings.edgeWeightRangeFrom != null)
            edgeWeightRangeFromTextField.setText(String.valueOf(Main.settings.edgeWeightRangeFrom));
        if (Main.settings.edgeWeightRangeTo != null)
            edgeWeightRangeToTextField.setText(String.valueOf(Main.settings.edgeWeightRangeTo));
    }

    private Integer readIntFromTextField (TextField tf) throws NumberFormatException {
        Integer number;
        try {
            number = Integer.parseInt(tf.getText());
        } catch (NumberFormatException e) {
            tf.clear();
            return null;
        }
        if (number <= 0) {
            tf.clear();
            return null;
        }
        return number;
    }

    private Double readDoubleFromTextField (TextField tf) throws NumberFormatException {
        Double number;
        try {
            number = Double.parseDouble(tf.getText());
        } catch (NumberFormatException e) {
            tf.clear();
            return null;
        }

        if (tf == probabilityTextField && number > 1) {
            tf.clear();
            return null;
        }

        if (number < 0) {
            tf.clear();
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
}
