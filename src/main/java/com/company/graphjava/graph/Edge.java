package com.company.graphjava.graph;

import com.company.graphjava.Main;

public class Edge {
    private final int index;
    private final double weight;

    public int getIndex() {
        return index;
    }

    public double getWeight() {
        return weight;
    }

    public Edge(Graph graph, int index, double weight) {
        if (index < 0 || index >= graph.getRows() * graph.getColumns())
            throw new IllegalArgumentException();
        this.index = index;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Edge))
            return false;

        Edge edge = (Edge) o;
        if (this.weight == edge.weight && this.index == edge.index)
            return true;
        return false;
    }

    @Override
    public String toString() {
        return this.index + " :" + this.weight;
    }
}
