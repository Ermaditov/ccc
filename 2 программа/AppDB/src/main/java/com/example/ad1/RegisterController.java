package com.example.ad1;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.swing.*;

public class RegisterController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker DataRosh;

    @FXML
    private Button Exit;

    @FXML
    private ComboBox<String> OknoDolgnosty;

    @FXML
    private RadioButton PolGirl;

    @FXML
    private RadioButton PolMysh;

    @FXML
    private Button Register;

    @FXML
    private CheckBox Sogl;

    @FXML
    private TextField VvodEmailID;

    @FXML
    private TextField VvodLogID;

    @FXML
    private TextField VvodName;

    @FXML
    private TextField VvodPass;

    @FXML
    private TextField VvodVoz;

    @FXML
    private TextField VvodSecSlov;


    ObservableList<BAZA> listBAPA; //Название массива
    Connection conn1 = null; //Конектор равен нулю
    PreparedStatement pst1 = null;

    @FXML
    void Exit1(ActionEvent event) throws IOException {
        FXMLLoader fmxlLoader = new FXMLLoader(HelloApplication.class.getResource("Register.fxml"));
        Stage stage1 = (Stage) Exit.getScene().getWindow();
        stage1.close();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 603, 403);
        Stage stage2 = new Stage();
        stage2.setTitle("Окно регистрации");
        stage2.setScene(scene);
        stage2.show();

    }

    @FXML
    void OknoDolgnosty1(ActionEvent event) {
        String StringOnDolg = OknoDolgnosty.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    void Register1(ActionEvent event) {
        if (Sogl.isSelected()) {

            //текст
            String username = VvodName.getText().toString();
            String username2 = VvodLogID.getText().toString();
            String email = VvodEmailID.getText().toString();
            String pass1 = VvodPass.getText().toString();
            String pass2 = VvodSecSlov.getText().toString();

            //число
            int vozrost = Integer.parseInt(VvodVoz.getText().toString());

            //должность
            String Dolgnost = OknoDolgnosty.getSelectionModel().getSelectedItem().toString();

            //дата
            java.util.Date date =
                    Date.from(DataRosh.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            java.sql.Date Datauser = new java.sql.Date(date.getTime());

            //выбор
            ToggleGroup groppol = new ToggleGroup();
            PolGirl.setToggleGroup(groppol);
            PolMysh.setToggleGroup(groppol);

            RadioButton selon = (RadioButton) groppol.getSelectedToggle();
            String Pol = selon.getText().toString();

            //галочка
            String Soglass = "";
            if (Sogl.isSelected()) Soglass += "Да";
            conn1 = Connect.ConnectDb();
            String sql = "insert into UsersATS (User_name, Login_user, Pass_user, Email_user, Pass2_user, Dolgnost_user, Vozrost_user, Data_user, Pol, Soglass)values(?,?,?,?,?,?,?,?,?,?)";
            try {
                assert conn1 != null;
                pst1 = conn1.prepareStatement(sql);
                pst1.setString(1, username);
                pst1.setString(2, username2);
                pst1.setString(3, pass1);
                pst1.setString(4, email);
                pst1.setString(5, pass2);

                pst1.setString(6, Dolgnost);
                pst1.setInt(7, vozrost);

                pst1.setDate(8, Datauser);
                pst1.setString(9, Pol);
                pst1.setString(10, Soglass);
                pst1.execute();

                JOptionPane.showMessageDialog(null, "Пользователь зарегистрирован!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        } else {
            System.out.print("???");
        }
    }
    @FXML
    void initialize() {
        ObservableList<String> list = FXCollections.observableArrayList("Пользователь", "Администратор", "Менеджер");
        OknoDolgnosty.setItems(list);
    }
}



