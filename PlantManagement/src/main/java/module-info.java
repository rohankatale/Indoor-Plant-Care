module org.example.plantmanagement {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.plantmanagement to javafx.fxml;
    exports org.example.plantmanagement;
}