module com.example.ad1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens com.example.ad1 to javafx.fxml;
    exports com.example.ad1;
}