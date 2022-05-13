package com.company.graphjava;

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
        if (index < 0 || index >= graph.getRows() * graph.getColumns() || weight < 0)
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
