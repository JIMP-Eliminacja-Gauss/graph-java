package com.company.graphjava.graph;

import com.company.graphjava.Main;

import java.util.Iterator;
import java.util.PriorityQueue;

public class Algorithm {
    private static int [] previousVertex;
    private static double [] shortestPath;

    public static double getShortestPath(int vertexIndex) {
        return shortestPath[vertexIndex];
    }

    public static void dijkstra(int sourceVertex) {
        dijkstraInit(sourceVertex);
        PriorityQueue<Neighbour> pQueue = new PriorityQueue<>(new NeighbourComparator());
        Neighbour [] adjacencyList = Main.getGraph().getAdjacencyList();
        int rows = Main.getGraph().getRows();
        int columns = Main.getGraph().getColumns();
        if (rows*columns == 1) {
            return;
        }
        int [] visited = new int [rows*columns];

        visited[sourceVertex] = 1;
        pQueue.add(adjacencyList[sourceVertex]);

        while (!pQueue.isEmpty()) {
            Neighbour vertex = pQueue.poll();
            Iterator<Edge> neighbours = vertex.iterator();
            while(neighbours.hasNext()) {
                Edge edge = neighbours.next();
                int edgeVertexIndex = edge.getIndex();
                if (visited[edgeVertexIndex] != 1) {
                    pQueue.add(adjacencyList[edgeVertexIndex]);
                    visited[edgeVertexIndex] = 1;
                }
                relax(vertex.getVertexIndex(), edgeVertexIndex, edge.getWeight());
            }
        }

        for (int i = 0; i < rows*columns; i++) {
            System.out.println(i + "  NAJKROTSZA SCIEZKA   " + shortestPath[i] + "\tPOPRZEDNI WIERZCHOLEK   " + previousVertex[i]);
        }

    }

    private static void dijkstraInit(int sourceVertex) {
        int rows = Main.getGraph().getRows();
        int columns = Main.getGraph().getColumns();
        previousVertex = new int [rows*columns];
        shortestPath = new double [rows*columns];
        for (int i = 0; i < rows*columns; i++) {
            previousVertex[i] = 0;
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
