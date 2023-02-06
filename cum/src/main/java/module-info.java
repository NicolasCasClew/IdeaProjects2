module com.example.cum {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;


    opens com.example.cum to javafx.fxml;
    exports com.example.cum;
}