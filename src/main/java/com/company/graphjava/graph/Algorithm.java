package com.company.graphjava.graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Algorithm {
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
