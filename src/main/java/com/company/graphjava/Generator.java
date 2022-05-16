package com.company.graphjava;

import java.util.Random;

public class Generator {

    private static boolean makeConnection(double probability) {
        Random random = new Random();
        int border = (int) (probability * Integer.MAX_VALUE);
        int value = random.nextInt();

        return border >= value;
    }

    private static void addPair(Graph graph, int vertexIndex, int neighbourIndex, double weight) {
        graph.addToAdjacencyList(vertexIndex, new Edge(graph, neighbourIndex, weight));
        graph.addToAdjacencyList(neighbourIndex, new Edge(graph, vertexIndex, weight));
    }

    public void generateGridGraph(Graph graph) {
        Random random = new Random();
        double weight;
        int vertexIndex;
        for (int i = 0; i < graph.getRows(); i++) {
            for (int j = 0; j < graph.getColumns(); j++) {

                if (j < graph.getColumns() - 1 && Generator.makeConnection(graph.getProbability())) {
                    weight = random.nextDouble() * (graph.getToY() - graph.getFromX()) + graph.getFromX();
                    vertexIndex = i * graph.getColumns() + j;
                    Generator.addPair(graph, vertexIndex, vertexIndex + 1, weight);
                }

                if (i < graph.getRows() - 1 && Generator.makeConnection(graph.getProbability())) {
                    weight = random.nextDouble() * (graph.getToY() - graph.getFromX()) + graph.getFromX();
                    vertexIndex = i * graph.getColumns() + j;
                    Generator.addPair(graph, vertexIndex, vertexIndex + graph.getColumns(), weight);
                }
            }
        }
    }
}
