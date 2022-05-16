package com.company.graphjava;

public class Main {
    static MainWindow mainWindow;
    static Settings getSettings;

    public static MainWindow getMainWindow() {
        return mainWindow;
    }

    public static Settings getSettings() {
        return getSettings;
    }

    public static void main(String[] args) {
        mainWindow = new MainWindow();
        getSettings = new Settings();
        mainWindow.run();
    }
}