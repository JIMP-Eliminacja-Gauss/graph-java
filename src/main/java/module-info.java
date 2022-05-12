module com.company.graphjava {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.company.graphjava to javafx.fxml;
    exports com.company.graphjava;
}