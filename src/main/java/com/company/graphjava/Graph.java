package com.company.graphjava;


public class Graph {
    private int rows;
    private int columns;
    private double fromX;
    private double toY;
    private double probability;
    private final Vertex [] adjacencyList;

    public Graph() {
        if (Main.getSettings().getRows() < 0 && Main.getSettings().getColumns() < 0)
            throw new IllegalArgumentException("Rows and columns must be positive integers");

        if (Main.getSettings().getProbability() < 0 || Main.getSettings().getProbability() > 1)
            throw new IllegalArgumentException("Probability must be chosen from range <0; 1>");

        if (Main.getSettings().getEdgeWeightRangeFrom() < 0 || Main.getSettings().getEdgeWeightRangeTo() < 0 ||
            Main.getSettings().getEdgeWeightRangeFrom() > Main.getSettings().getEdgeWeightRangeTo())
            throw new IllegalArgumentException("Edge weights cannot be negative and fromX <= toX");

        this.rows = Main.getSettings().getRows();
        this.columns = Main.getSettings().getColumns();
        this.fromX = Main.getSettings().getEdgeWeightRangeFrom();
        this.toY = Main.getSettings().getEdgeWeightRangeTo();
        this.probability = Main.getSettings().getProbability();
        adjacencyList = new Vertex[this.rows * this.columns];
    }

    public Graph(int rows, int columns) {
        if (rows < 0 || columns < 0)
            throw new IllegalArgumentException();

        this.rows = rows;
        this.columns = columns;
        adjacencyList = new Vertex[rows * columns];
    }

    public Graph(int rows, int columns, double fromX, double toY, double probability) {
        if (rows < 0 || columns < 0 || probability < 0 || probability > 1)
            throw new IllegalArgumentException();

        this.rows = rows;
        this.columns = columns;
        this.fromX = fromX;
        this.toY = toY;
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

    public double getToY() {
        return toY;
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
        if (fromX < 0 || fromX > toY)
            throw new IllegalArgumentException();
        this.fromX = fromX;
    }

    public void setToY(double toY) {
        if (toY < 0 || toY < fromX)
            throw new IllegalArgumentException();
        this.toY = toY;
    }

    public void setProbability(double probability) {
        if (probability < 0 || probability > 1)
            throw new IllegalArgumentException();
        this.probability = probability;
    }

    public void addToAdjacencyList(int vertexIndex, Edge edge) {
        try {
            if (adjacencyList[vertexIndex] == null)
                adjacencyList[vertexIndex] = new Vertex();

            adjacencyList[vertexIndex].addEdge(edge);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}