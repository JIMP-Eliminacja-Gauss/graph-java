package com.company.graphjava;

import javafx.stage.Stage;

public class Settings {
    private Integer rows;
    private Integer columns;
    private Double probability;
    private Double edgeWeightRangeFrom;
    private Double edgeWeightRangeTo;
    private Stage settingsStage;

    public void setRows(Integer rows) {
        try {
            if (rows > 0)
                this.rows = rows;
            else
                throw new IllegalArgumentException("Rows variable must be positive!");

        } catch (NullPointerException e) {
            System.out.println("Rows variable cannot be null!");
        }
    }

    public void setColumns(Integer columns) {
        try {
            if (columns > 0)
                this.columns = columns;
            else
                throw new IllegalArgumentException("Columns variable must be positive!");

        } catch (NullPointerException e) {
            System.out.println("Columns variable cannot be null!");
        }
    }

    public void setProbability(Double probability) {
        try {
            if (probability >= 0 && probability <= 1)
                this.probability = probability;
            else
                throw new IllegalArgumentException("Probability variable must be in <0; 1> range!");

        } catch (NullPointerException e) {
            System.out.println("Probability variable cannot be null!");
        }
    }

    public void setEdgeWeightRangeFrom(Double edgeWeightRangeFrom) {
        try {
            if (edgeWeightRangeFrom >= 0)
                this.edgeWeightRangeFrom = edgeWeightRangeFrom;
            else
                throw new IllegalArgumentException("FromX variable must be non-negative!");

        } catch (NullPointerException e) {
            System.out.println("FromX variable cannot be null!");
        }
    }

    public void setEdgeWeightRangeTo(Double edgeWeightRangeTo) {
        try {
            if (edgeWeightRangeTo >= 0)
                this.edgeWeightRangeTo = edgeWeightRangeTo;
            else
                throw new IllegalArgumentException("ToY variable must be non-negative!");

        } catch (NullPointerException e) {
            System.out.println("ToY variable cannot be null!");
        }
    }

    public void setSettingsStage(Stage settingsStage) {
        try {
            this.settingsStage = settingsStage;
        } catch (NullPointerException e) {
            System.out.println("Settings stage is NULL");
        }
    }

    public Integer getRows() {
        return rows;
    }

    public Integer getColumns() {
        return columns;
    }

    public Double getProbability() {
        return probability;
    }

    public Double getEdgeWeightRangeFrom() {
        return edgeWeightRangeFrom;
    }

    public Double getEdgeWeightRangeTo() {
        return edgeWeightRangeTo;
    }

    public Stage getSettingsStage() {
        return settingsStage;
    }
}
