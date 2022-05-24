package com.company.graphjava.graph;

import com.company.graphjava.Main;
import com.company.graphjava.MyExceptions;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

public class Files {


    public static void fileRead(String path) throws IOException, NumberFormatException, MyExceptions.FileFormatException{
        BufferedReader file = new BufferedReader(new FileReader(path));
        String line = file.readLine();
        String[] graphSize = line.split("\\s+");
        int rows = Integer.parseInt(graphSize[0]);
        int columns = Integer.parseInt(graphSize[1]);

        Graph graph = new Graph(rows,columns);
        Main.setGraph(graph);

        int currentVertexIndex = 0;
        while ((line = file.readLine()) != null) {
            line = line.replace(':', ' ');
            String [] edges = line.split("\\s+");
            for (int i = 0; i < edges.length; i += 2) {
                int edgeVertexIndex = Integer.parseInt(edges[i]);
                double edgeWeight = Double.parseDouble(edges[i+1]);
                if (edgeVertexIndex < 0 || edgeVertexIndex > (rows*columns-1)) {
                    throw new MyExceptions.FileFormatException("Vertex index should be >= 0 and < rows*columns");
                }
                if (edgeVertexIndex != currentVertexIndex+1 && edgeVertexIndex != currentVertexIndex-1 && edgeVertexIndex != currentVertexIndex+columns && edgeVertexIndex != currentVertexIndex-columns) {
                    throw new MyExceptions.FileFormatException("Graph should be grid graph");
                }
                Main.getGraph().addToAdjacencyList(currentVertexIndex, new Edge(Main.getGraph(), edgeVertexIndex, edgeWeight));
            }
            System.out.println(line);
            currentVertexIndex++;

        }
    }

    public static void fileCreate(String path) throws IOException, NullPointerException{
        int rows = Main.getGraph().getRows();
        int columns = Main.getGraph().getColumns();

        BufferedWriter file = new BufferedWriter(new FileWriter(path));
        file.write(rows + " " + columns);
        file.newLine();

        if (rows * columns != 1) {
            for (int i = 0; i < rows * columns; i++) {
                Iterator<Edge> iterator = Main.getGraph().getNeigboursIterator(i);
                for (Iterator<Edge> it = iterator; it.hasNext(); ) {
                    Edge edge = it.next();
                    file.write(edge.getIndex() + " :" + edge.getWeight() + "     ");
                }
                file.newLine();
            }
        }
        file.close();
    }


}
