package com.company.graphjava;


public class Graph {
    private int rows;
    private int columns;
    private double fromX;
    private double fromY;
    private double probability;
    private Vertex [] adjacencyList;

    public Graph(int rows, int columns, Double fromX, Double fromY, double probability) {
        if (rows < 0 || columns < 0 || probability < 0 || probability > 1 ||
                (fromX != null && fromX < 0) || (fromY != null && fromY < 0))
            throw new IllegalArgumentException();

        if (! ((fromX == null && fromY == null) || (fromX != null && fromY != null)) )
            throw new IllegalArgumentException("FromX and FromY should either both be specified or none.");

        this.rows = rows;
        this.columns = columns;
        this.fromX = fromX == null ? 1 : fromX;
        this.fromY = fromY == null ? 10 : fromY;
        this.probability = probability;
        adjacencyList = new Vertex[rows * columns];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public double getFromX() {
        return fromX;
    }

    public double getFromY() {
        return fromY;
    }

    public double getProbability() {
        return probability;
    }

    public void setRows(int rows) {
        if (rows < 0)
            throw new IllegalArgumentException();
        this.rows = rows;
    }

    public void setColumns(int columns) {
        if (columns < 0)
            throw new IllegalArgumentException();
        this.columns = columns;
    }

    public void setFromX(double fromX) {
        if (fromX < 0 || fromX > fromY)
            throw new IllegalArgumentException();
        this.fromX = fromX;
    }

    public void setFromY(double fromY) {
        if (fromY < 0 || fromY < fromX)
            throw new IllegalArgumentException();
        this.fromY = fromY;
    }

    public void setProbability(double probability) {
        if (probability < 0 || probability > 1)
            throw new IllegalArgumentException();
        this.probability = probability;
    }
}