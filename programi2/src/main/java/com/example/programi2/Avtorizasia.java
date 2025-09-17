package com.example.programi2;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;

public class Avtorizasia {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button avis;

    @FXML
    private Button exi;

    @FXML
    private TextField ligin;

    @FXML
    private TextField parli;

    @FXML
    private Button regs;
    Connection conn1 = null; //Конектор равен нулю
    ResultSet rs = null;
    PreparedStatement pst1 = null;
    @FXML
    void Avtorizi(ActionEvent event) {
        conn1 = Conect.ConnectDb();
        String sql = "Select * from Polzovatel where Login = ? and  Porol= ?";
            try {
                pst1 = conn1.prepareStatement(sql);
                pst1.setString(1, parli.getText());
                pst1.setString(2, ligin.getText());
                rs = pst1.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Данные введены верно.");
                    FXMLLoader fmxlLoader = new FXMLLoader(HelloApplication.class.getResource("avtorizasia.fxml"));
                    Stage stage1 = (Stage) avis.getScene().getWindow();
                    stage1.close();

                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("resi.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 605, 406);
                    Stage stage2 = new Stage();
                    stage2.setTitle("Главное меню");
                    stage2.setScene(scene);
                    stage2.show();

                } else JOptionPane.showMessageDialog(null, "Данные введены неверно.");

                FXMLLoader fmxlLoader = new FXMLLoader(HelloApplication.class.getResource("avtorizasia.fxml"));
                Stage stage1 = (Stage) avis.getScene().getWindow();
                stage1.close();

                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("registrasia.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 800, 600);
                Stage stage2 = new Stage();
                stage2.setTitle("Главное меню");
                stage2.setScene(scene);
                stage2.show();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);

            }
        }
    @FXML
    void Registrisi(ActionEvent event) {

    }

    @FXML
    void vixod(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert avis != null : "fx:id=\"avis\" was not injected: check your FXML file 'avtorizasia.fxml'.";
        assert exi != null : "fx:id=\"exi\" was not injected: check your FXML file 'avtorizasia.fxml'.";
        assert ligin != null : "fx:id=\"ligin\" was not injected: check your FXML file 'avtorizasia.fxml'.";
        assert parli != null : "fx:id=\"parli\" was not injected: check your FXML file 'avtorizasia.fxml'.";
        assert regs != null : "fx:id=\"regs\" was not injected: check your FXML file 'avtorizasia.fxml'.";

    }

}
