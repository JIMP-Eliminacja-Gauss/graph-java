package com.company.graphjava;

import com.company.graphjava.graph.Graph;

public class Main {
    private static MainWindow mainWindow;
    private static Settings settings;
    private static Graph graph;

    public static Graph getGraph() {
        return graph;
    }
    public static void setGraph(Graph graph) {
        if (graph == null)
            throw new NullPointerException("Graph is null!");
        Main.graph = graph;
    }
    public static MainWindow getMainWindow() {
        return mainWindow;
    }

    public static Settings getSettings() {
        return settings;
    }

    public static void main(String[] args) {
        mainWindow = new MainWindow();
        settings = new Settings();
        mainWindow.run();
    }
}