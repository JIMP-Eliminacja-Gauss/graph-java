package com.company.graphjava.controller;

import com.company.graphjava.Main;
import com.company.graphjava.graph.Algorithm;
import com.company.graphjava.graph.Edge;
import com.company.graphjava.graph.Graph;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Iterator;



public class GUIMonitor {
    private final GraphicsContext grc;
    private final int canvasWidth;
    private final int canvasHeight;
    private double squareSide;
    private double diameter;

    public double getSquareSide() { return squareSide; }
    public double getDiameter() { return diameter; }


    public GUIMonitor(GraphicsContext grc, int canvasWidth, int canvasHeight) {
        this.grc = grc;
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
    }

    private double calculateColorHue(double weight, double min, double max) {
        double dx = max - min;
        double hueDx = Color.GREEN.getHue() - Color.RED.getHue();
        return (max - weight)/dx * hueDx + Color.RED.getHue();
    }

    private void drawVertices(int rows, int columns, double squareSide, double diameter) {
        grc.setFill(Color.rgb(255,255,255));
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grc.fillOval(j * squareSide + 0.125 * squareSide, i * squareSide + 0.125 * squareSide, diameter, diameter);
            }
        }
    }

    private void drawEdges(int rows, int columns, double squareSide) {
        double maxEdgeValue;
        double minEdgeValue;

        if (Main.getGraph().isFromFile()) {
            minEdgeValue = Main.getGraph().minEdgeValue();
            maxEdgeValue = Main.getGraph().maxEdgeValue();
        } else {
            minEdgeValue = Main.getGraph().getFromX();
            maxEdgeValue = Main.getGraph().getToY();
        }


        grc.setLineWidth(0.25 * squareSide);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Edge verticalEdge = null;
                Edge horizontalEdge = null;
                int currVertexIndex = i * columns + j;
                Iterator<Edge> neighbours = Main.getGraph().getNeighboursIterator(currVertexIndex);
                while (neighbours.hasNext()) {
                    Edge edge = neighbours.next();
                    if (edge.getIndex() == currVertexIndex + 1)
                        horizontalEdge = edge;
                    if (edge.getIndex() == currVertexIndex + columns)
                        verticalEdge = edge;

                }
                if (i < rows - 1 && verticalEdge != null) {
                    grc.setStroke(Color.hsb(calculateColorHue(verticalEdge.getWeight(), minEdgeValue, maxEdgeValue),
                            1,1,1));

                    grc.strokeLine(j * squareSide + squareSide / 2, i * squareSide + squareSide/ 2,
                            j * squareSide + squareSide / 2, (i+1) * squareSide + squareSide/2);
                }

                if (j < columns - 1 && horizontalEdge != null) {
                    grc.setStroke(Color.hsb(calculateColorHue(horizontalEdge.getWeight(), minEdgeValue, maxEdgeValue),
                            1,1,1));
                    grc.strokeLine(j * squareSide + squareSide/2, i * squareSide + squareSide/2,
                            (j+1) * squareSide + squareSide/2, i * squareSide + squareSide/2);
                }
            }
        }
    }

    public Integer findVertex(double x, double y) {
        int column = (int) (x / squareSide);
        int row = (int) (y / squareSide);
        int vertexIndex = row*(Main.getGraph().getColumns()) + column;

        if (vertexIndex >= Main.getGraph().getRows() * Main.getGraph().getColumns())
            return null;

        double vertexCentreX = column*squareSide + squareSide/2;
        double vertexCentreY = row*squareSide + squareSide/2;
        double distanceFromVertexCentre = Math.sqrt( (vertexCentreX - x)*(vertexCentreX - x) + (vertexCentreY - y)*(vertexCentreY - y) );

        if (distanceFromVertexCentre <= diameter/2){
            return vertexIndex;
        }

        return null;
    }

    public void drawShortestPath (int sourceVertex, int vertex) {
        if (Algorithm.getShortestPath(vertex) == Double.POSITIVE_INFINITY)
            return;

        int columns = Main.getGraph().getColumns();
        int rows = Main.getGraph().getRows();

        while(true){
            int previousVertex = Algorithm.getPreviousVertex(vertex);
            int row =  previousVertex/columns;
            int column = previousVertex - row*columns;
            double previousVertexCentreX = column*squareSide + squareSide/2;
            double previousVertexCentreY = row*squareSide + squareSide/2;

            row = vertex/columns;
            column = vertex - row*columns;
            double vertexCentreX = column*squareSide + squareSide/2;
            double vertexCentreY = row*squareSide + squareSide/2;

            grc.setLineWidth(0.2 * squareSide);
            grc.setStroke(Color.rgb(1,1,1));
            grc.strokeLine(vertexCentreX, vertexCentreY,
                    previousVertexCentreX, previousVertexCentreY);

            vertex = previousVertex;
            if(previousVertex == sourceVertex)
                break;
        }

    }



    public synchronized void drawGraph() {
        int rows = Main.getGraph().getRows();
        int columns = Main.getGraph().getColumns();

        int minSquareSide = 3;
        int maxSquareSide = 60;

        //double squareSide;
        //double diameter;

        /*
        Dzielimy plotno do rysowania na kwadraty i w kazdym srodku kradratu rysujemy kolo
        o srednicy 3/4 boku kwadratu.
         */

        // gdyby plotno nie bylo kwadratowe
        if (columns > rows)
            squareSide = canvasWidth/ (double) columns;
        else if (columns < rows)
            squareSide = canvasHeight/ (double) rows;
        else
            squareSide = Math.min(canvasHeight, canvasWidth)/ (double) rows;

        if (squareSide > maxSquareSide) squareSide = maxSquareSide;
        // squareSide < minSquareSide komunikat?

        diameter = (int) (0.75 * squareSide);

        // tlo

        Platform.runLater(() -> {
            grc.setFill(Color.NAVY);
            grc.fillRect(0, 0, canvasWidth, canvasHeight);
        });



        // krawedzie
        Platform.runLater(() -> drawEdges(rows, columns, squareSide));

        // rysowanie wierzcholkow
        Platform.runLater(() -> drawVertices(rows, columns, squareSide, diameter));
        System.out.println("DONE");
    }
}
