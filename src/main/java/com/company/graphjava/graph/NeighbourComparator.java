package com.company.graphjava.graph;

import com.company.graphjava.Main;

import java.util.Comparator;

public class NeighbourComparator implements Comparator<Neighbour> {
    public int compare(Neighbour n1, Neighbour n2) {
        if (1/Algorithm.getShortestPath(n1.getVertexIndex()) < 1/Algorithm.getShortestPath(n2.getVertexIndex()))
            return 1;
        else if (1/Algorithm.getShortestPath(n1.getVertexIndex()) > 1/Algorithm.getShortestPath(n2.getVertexIndex()))
            return -1;
        return 0;
    }
}

