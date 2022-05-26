package com.company.graphjava.graph;

import java.util.ArrayList;
import java.util.Iterator;

public class Neighbour implements Iterable<Edge> {
    private ArrayList<Edge> neighbours = new ArrayList<Edge>();
    private int vertexIndex;

    public int getSize() {
        return this.neighbours.size();
    }
    public int getVertexIndex() {return vertexIndex; }
    public void setVertexIndex(int vertexIndex) {
        this.vertexIndex = vertexIndex;
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
