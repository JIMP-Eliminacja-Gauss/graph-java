package com.company.graphjava;

import java.util.ArrayList;

public class Vertex {
    private ArrayList<Edge> edges = new ArrayList<Edge>();

    public int getSize() {
        return this.edges.size();
    }

    public ArrayList<Edge> getEdges() {
        return this.edges;
    }

    public void addEdge(Edge edge) {
        if (!edges.contains(edge))
            edges.add(edge);
    }

}
