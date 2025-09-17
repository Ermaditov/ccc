module com.example.programi2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.programi2 to javafx.fxml;
    exports com.example.programi2;
}