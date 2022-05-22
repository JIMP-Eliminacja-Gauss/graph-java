module com.company.graphjava {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.company.graphjava to javafx.fxml;
    exports com.company.graphjava;
    exports com.company.graphjava.controller;
    opens com.company.graphjava.controller to javafx.fxml;
    exports com.company.graphjava.graph;
    opens com.company.graphjava.graph to javafx.fxml;
}