package com.example.programi2;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;

public class Registrasia {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button avt;

    @FXML
    private Button avtor;

    @FXML
    private TextField login;

    @FXML
    private TextField parol;

    @FXML
    private Button prog;

    @FXML
    private Button reg;

    @FXML
    private CheckBox sogal;

    @FXML
    private Button vix;

    Connection conn1 = null; //Конектор равен нулю
    PreparedStatement pst1 = null;

    @FXML
    void avtorizasia(ActionEvent event) throws IOException {

        FXMLLoader fmxlLoader = new FXMLLoader(HelloApplication.class.getResource("Register.fxml"));
        Stage stage1 = (Stage) avt.getScene().getWindow();
        stage1.close();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("avtorizasia.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 603, 403);
        Stage stage2 = new Stage();
        stage2.setTitle("Окно авторизации");
        stage2.setScene(scene);
        stage2.show();
    }

    @FXML
    void exit(ActionEvent event) {
 System.exit(0);
    }

    @FXML
    void oavtore(ActionEvent event) {

        JOptionPane.showMessageDialog(null, "Автор: Ермаков Сергей\n Группа: ИСП-31");
    }

    @FXML
    void oprograme(ActionEvent event) {
        JOptionPane.showMessageDialog(null, "Программа написана для базы данных Аэропорт\n Заказчик: ГПОУ КПК");
    }

    @FXML
    void registrasia(ActionEvent event) {
            String Loga = login.getText().toString();
            String Parola = parol.getText().toString();
            conn1 = Conect.ConnectDb();
            String sql = "INSERT INTO Polzovatel (Login,Porol)values(?,?)";
            try {
                assert conn1 != null;
                pst1 = conn1.prepareStatement(sql);
                pst1.setString(1, Loga);
                pst1.setString(2, Parola);
                pst1.execute();

                JOptionPane.showMessageDialog(null, "Пользователь зарегистрирован!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        {
            System.out.print("??");
        }
    }


    @FXML
    void initialize() {
        assert avt != null : "fx:id=\"avt\" was not injected: check your FXML file 'registrasia.fxml'.";
        assert avtor != null : "fx:id=\"avtor\" was not injected: check your FXML file 'registrasia.fxml'.";
        assert login != null : "fx:id=\"login\" was not injected: check your FXML file 'registrasia.fxml'.";
        assert parol != null : "fx:id=\"parol\" was not injected: check your FXML file 'registrasia.fxml'.";
        assert prog != null : "fx:id=\"prog\" was not injected: check your FXML file 'registrasia.fxml'.";
        assert reg != null : "fx:id=\"reg\" was not injected: check your FXML file 'registrasia.fxml'.";
        assert sogal != null : "fx:id=\"sogal\" was not injected: check your FXML file 'registrasia.fxml'.";
        assert vix != null : "fx:id=\"vix\" was not injected: check your FXML file 'registrasia.fxml'.";

    }

}
