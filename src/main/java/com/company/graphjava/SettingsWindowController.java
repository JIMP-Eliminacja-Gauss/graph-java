package com.company.graphjava;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SettingsWindowController {

    // przyciski
    @FXML
    private Button applyButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button okButton;
    @FXML
    private Button currentSettingsButton;
    @FXML
    private Button clearButton;

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

    private Integer readIntFromTextField (TextField tf) throws NumberFormatException {
        int number;
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
        double number;
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
