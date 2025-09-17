package com.example.ad1;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.*;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> Dolg;

    @FXML
    private Button Exit;

    @FXML
    private Button ObAvtore;

    @FXML
    private CheckBox PasswordOnOf;

    @FXML
    private Button Register;

    @FXML
    private TextField VvodSecSlov;


    @FXML
    private Button VipVxod;

    @FXML
    private TextField VvodLog;

    @FXML
    private TextField pass;

    @FXML
    private PasswordField VvodPass;

    Connection conn1 = null; //Конектор равен нулю
    ResultSet rs = null;
    PreparedStatement pst1 = null;

    @FXML
    void Exit1(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void ObAvtore1(ActionEvent event) {
        Dialog dialog = new Dialog();
        dialog.setTitle("Об авторе.");
        dialog.setHeaderText(null);
        dialog.setContentText("Программу создал Пашков А.Е.,\nГруппа: ИСП-31\nВсе права защищены.");
        ButtonType closeButtonType = new ButtonType("Закрыть сообщение", ButtonType.CANCEL.getButtonData());
        dialog.getDialogPane().getButtonTypes().add(closeButtonType);
        dialog.show();
    }

    @FXML
    void Register1(ActionEvent event) throws IOException {
        FXMLLoader fmxlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fmxl"));
        Stage stage1 = (Stage) Register.getScene().getWindow();
        stage1.close();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Register.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 566, 440);
        Stage stage2 = new Stage();
        stage2.setTitle("Окно регистрации");
        stage2.setScene(scene);
        stage2.show();
    }

    @FXML
    void OknoDolg1(ActionEvent event) {
        String StringOnDolg = Dolg.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    void VipVxod1(ActionEvent event) {

        conn1 = Connect.ConnectDb();
        String sql = "Select * from UsersATS where Dolgnost_user = ? and Login_user = ? and Pass_user = ? and Pass2_user = ?";
        try {
            pst1 = conn1.prepareStatement(sql);
            pst1.setString(1, Dolg.getSelectionModel().getSelectedItem().toString());
            pst1.setString(2, VvodLog.getText());
            pst1.setString(3, VvodPass.getText());
            pst1.setString(4, VvodSecSlov.getText());

            rs = pst1.executeQuery();

            if (rs.next()) {

                JOptionPane.showMessageDialog(null, "Данные введены верно.");

                if (Objects.equals(Dolg, "Администратор")) {

                    FXMLLoader fmxlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
                    Stage stage1 = (Stage) VipVxod.getScene().getWindow();
                    stage1.close();

                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("TablichaTovars.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 605, 406);
                    Stage stage2 = new Stage();
                    stage2.setTitle("Главное меню");
                    stage2.setScene(scene);
                    stage2.show();
                }

                if (Objects.equals(Dolg, "Менеджер")) {

                    FXMLLoader fmxlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
                    Stage stage1 = (Stage) VipVxod.getScene().getWindow();
                    stage1.close();

                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("TablichaTovars.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 605, 406);
                    Stage stage2 = new Stage();
                    stage2.setTitle("Главное меню");
                    stage2.setScene(scene);
                    stage2.show();
                }

                if (Objects.equals(Dolg, "Пользователь")) {

                    FXMLLoader fmxlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
                    Stage stage1 = (Stage) VipVxod.getScene().getWindow();
                    stage1.close();

                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("TablichaTovars.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 605, 406);
                    Stage stage2 = new Stage();
                    stage2.setTitle("Главное меню");
                    stage2.setScene(scene);
                    stage2.show();
                }
            }
            else JOptionPane.showMessageDialog(null, "Данные введены неверно.");

            FXMLLoader fmxlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Stage stage1 = (Stage) VipVxod.getScene().getWindow();
            stage1.close();

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("TablichaTovars.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            Stage stage2 = new Stage();
            stage2.setTitle("Главное меню");
            stage2.setScene(scene);
            stage2.show();
        }  catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    void PasswordOnOf1(ActionEvent event) {
        if (PasswordOnOf.isSelected()) {
            pass.setText(VvodPass.getText());
            VvodPass.setVisible(false);
            pass.setVisible(true);
        }
        else {
            VvodPass.setText(pass.getText());
            pass.setVisible(false);
            VvodPass.setVisible(true);
        }
    }

    @FXML
    void initialize() {
        ObservableList<String> list = FXCollections.observableArrayList("Пользователь", "Администратор", "Менеджер");
        Dolg.setItems(list);
        assert Dolg != null : "fx:id=\"Dolshjnost\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert Exit != null : "fx:id=\"Exit\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert ObAvtore != null : "fx:id=\"ObAvtore\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert PasswordOnOf != null : "fx:id=\"PasswordOnOf\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert Register != null : "fx:id=\"Register\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert VipVxod != null : "fx:id=\"VipVxod\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert VvodLog != null : "fx:id=\"VvodLog\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert VvodPass != null : "fx:id=\"VvodPass\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert VvodSecSlov != null : "fx:id=\"VvodSecSlov\" was not injected: check your FXML file 'hello-view.fxml'.";
    }
}



