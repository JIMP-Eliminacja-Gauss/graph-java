package com.company.graphjava.controller;

import com.company.graphjava.Main;
import com.company.graphjava.graph.Edge;
import com.company.graphjava.graph.Graph;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Iterator;

public class GUIMonitor {
    private final GraphicsContext grc;
    private final int canvasWidth;
    private final int canvasHeight;

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

        if (Main.getSettings().getEdgeWeightRangeFrom() == null) {
            minEdgeValue = Main.getGraph().getFromX();
            maxEdgeValue = Main.getGraph().getToY();
        } else {
            minEdgeValue = Main.getGraph().minEdgeValue();
            maxEdgeValue = Main.getGraph().maxEdgeValue();
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
                    else if (edge.getIndex() == currVertexIndex + columns)
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

    public void drawGraph() {
        int rows = Main.getSettings().getRows();
        int columns = Main.getSettings().getColumns();

        int minSquareSide = 3;
        int maxSquareSide = 60;

        double squareSide;
        double diameter;

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
        //grc.setFill(Color.CORNSILK);
        grc.setFill(Color.NAVY);
        grc.fillRect(0, 0, canvasWidth, canvasHeight);
        System.out.println("Squareside = " + squareSide);
        System.out.println("Max = " + Main.getGraph().maxEdgeValue());
        System.out.println("Min = " + Main.getGraph().minEdgeValue());
        System.out.printf("wiersze = %d, kolumny = %d", Main.getGraph().getRows(), Main.getGraph().getColumns());

        // krawedzie
        drawEdges(rows, columns, squareSide);

        // rysowanie wierzcholkow
        drawVertices(rows, columns, squareSide, diameter);
    }
}
