package com.company.graphjava;

import java.util.ArrayList;
import java.util.Iterator;

public class Vertex implements Iterable<Edge> {
    private ArrayList<Edge> edges = new ArrayList<Edge>();

    public int getSize() {
        return this.edges.size();
    }

    @Override
    public Iterator<Edge> iterator() {
        return this.edges.iterator();
    }

    public void addEdge(Edge edge) {
        if (!edges.contains(edge))
            edges.add(edge);
    }

}
