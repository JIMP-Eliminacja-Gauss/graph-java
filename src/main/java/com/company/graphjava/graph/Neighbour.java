package com.company.graphjava.graph;

import java.util.ArrayList;
import java.util.Iterator;

public class Neighbour implements Iterable<Edge> {
    private ArrayList<Edge> neighbours = new ArrayList<Edge>();

    public int getSize() {
        return this.neighbours.size();
    }

    @Override
    public Iterator<Edge> iterator() {
        return this.neighbours.iterator();
    }

    public void addEdge(Edge edge) {
        if (!neighbours.contains(edge))
            neighbours.add(edge);
    }

}
