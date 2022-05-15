package com.company.graphjava;

public class Main {
    public static MainWindow mainWindow;
    public static Settings settings;

    public static void main(String[] args) {
        mainWindow = new MainWindow();
        settings = new Settings();
        mainWindow.run();
    }
}