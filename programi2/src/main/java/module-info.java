module com.example.programi2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires jdk.jdi;


    opens com.example.programi2 to javafx.fxml;
    exports com.example.programi2;
}