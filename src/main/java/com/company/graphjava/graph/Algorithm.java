package com.company.graphjava.graph;


import com.company.graphjava.Main;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.PriorityQueue;

public class Algorithm {
    private static int [] previousVertex;
    private static double [] shortestPath;

    public static double getShortestPath(int vertexIndex) {
        return shortestPath[vertexIndex];
    }
    public static int getPreviousVertex(int vertexIndex) {
        return previousVertex[vertexIndex];
    }

    public static Integer dijkstra(int sourceVertex) {
        dijkstraInit(sourceVertex);
        PriorityQueue<Neighbour> pQueue = new PriorityQueue<>(new NeighbourComparator());
        Neighbour [] adjacencyList = Main.getGraph().getAdjacencyList();
        int rows = Main.getGraph().getRows();
        int columns = Main.getGraph().getColumns();
        if (rows*columns == 1) {
            return null;
        }
        int [] visited = new int [rows*columns];

        visited[sourceVertex] = 1;
        if(adjacencyList[sourceVertex] == null)
            return null;
        pQueue.add(adjacencyList[sourceVertex]);

        while (!pQueue.isEmpty()) {
            Neighbour vertex = pQueue.poll();
            Iterator<Edge> neighbours = vertex.iterator();
            while(neighbours.hasNext()) {
                Edge edge = neighbours.next();
                int edgeVertexIndex = edge.getIndex();
                double edgeWeight = edge.getWeight();
                if (edgeWeight < 0)
                    return 1;
                if (visited[edgeVertexIndex] != 1) {
                    pQueue.add(adjacencyList[edgeVertexIndex]);
                    visited[edgeVertexIndex] = 1;
                }
                relax(vertex.getVertexIndex(), edgeVertexIndex, edge.getWeight());
            }
        }
        return 0;
    }

    private static void dijkstraInit(int sourceVertex) {
        int rows = Main.getGraph().getRows();
        int columns = Main.getGraph().getColumns();
        previousVertex = new int [rows*columns];
        shortestPath = new double [rows*columns];
        for (int i = 0; i < rows*columns; i++) {
            previousVertex[i] = sourceVertex;
            shortestPath[i] = Double.POSITIVE_INFINITY;
        }
        shortestPath[sourceVertex] = 0;

    }

    private static void relax(int vertexIndex, int edgeVertexIndex, double edgeWeight) {
        if (shortestPath[vertexIndex] + edgeWeight < shortestPath[edgeVertexIndex]) {
            shortestPath[edgeVertexIndex] = shortestPath[vertexIndex] + edgeWeight;
            previousVertex[edgeVertexIndex] = vertexIndex;
        }
    }
      
    public static boolean bfs(Graph graph) {
        Queue<Integer> FIFO = new LinkedList<>();
        boolean [] visited = new boolean[graph.getRows() * graph.getColumns()];
        int vertexIndex;

        FIFO.add(0);
        visited[0] = true;
        while (FIFO.size() > 0) {
            vertexIndex = FIFO.remove();

            for (Iterator<Edge> i = graph.getNeighboursIterator(vertexIndex); i.hasNext(); ) {
                int index = i.next().getIndex();
                if (!visited[index]) {
                    visited[index] = true;
                    FIFO.add(index);
                }
            }
        }

        for (boolean b : visited)
            if (!b)
                return false;

        return true;
    }
}
