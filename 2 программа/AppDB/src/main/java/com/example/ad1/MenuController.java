package com.example.ad1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Exit;

    @FXML
    private Button PloshKryg;

    @FXML
    private Button PloshPryam;

    @FXML
    private Button PloshTge;

    @FXML
    void Exit1(ActionEvent event) {

    }

    @FXML
    void PloshKryg1(ActionEvent event) {

    }

    @FXML
    void PloshPryam1(ActionEvent event) {

    }

    @FXML
    void PloshTge1(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert Exit != null : "fx:id=\"Exit\" was not injected: check your FXML file 'Menu.fxml'.";
        assert PloshKryg != null : "fx:id=\"PloshKryg\" was not injected: check your FXML file 'Menu.fxml'.";
        assert PloshPryam != null : "fx:id=\"PloshPryam\" was not injected: check your FXML file 'Menu.fxml'.";
        assert PloshTge != null : "fx:id=\"PloshTge\" was not injected: check your FXML file 'Menu.fxml'.";

    }

}



