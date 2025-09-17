package com.example.programi2;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javax.swing.*;

import static com.example.programi2.Conect.getasetgetskpk;
import static com.example.programi2.Conect.getgetsetskpk;

public class Aroport {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<asetget, String> cgr;

    @FXML
    private TableColumn<asetget, String> cnz;

    @FXML
    private TableColumn<asetget, Integer> cst;

    @FXML
    private TableColumn<asetget, Integer> idii;

    @FXML
    private Button men;

    @FXML
    private TableView<asetget> tbli;

    @FXML
    private TextField tcti;

    @FXML
    private TextField tgr;

    @FXML
    private TextField tidd;
    @FXML
    private TextField tnz;

int index=-1;
    ObservableList<asetget> listBAP; //Название массива
    Connection conn1 = null; //Конектор равен нулю
    PreparedStatement pst1 = null;
    @FXML
    void dobvleniee(ActionEvent event) {
        conn1 = Conect.ConnectDb();

        String sql = "insert into Аэропорты (аэропорт_id, название, город, страна_id) values(?,?,?,?)";

        try {
            assert conn1 != null;
            pst1 = conn1.prepareStatement(sql);
            pst1.setString(1, tidd.getText());
            pst1.setString(2, tnz.getText());
            pst1.setString(3, tgr.getText());
            pst1.setString(4, tcti.getText());
            pst1.execute();

            JOptionPane.showMessageDialog(null, "Добавлено!");
            UpdateTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void UpdateTable() {
            idii.setCellValueFactory(new PropertyValueFactory<asetget, Integer>("id"));
           cnz.setCellValueFactory(new PropertyValueFactory<asetget, String>("название"));
          cgr.setCellValueFactory(new PropertyValueFactory<asetget, String>("город"));
        cst.setCellValueFactory(new PropertyValueFactory<asetget, Integer>("страна_id"));
         listBAP = getasetgetskpk();
        tbli.setItems(listBAP);

            }
    @FXML
    void izmeneniee(ActionEvent event) {
        try {
            conn1 = Conect.ConnectDb();
            String value1 = tidd.getText();
            String value2 = tnz.getText();
            String value3 = tgr.getText();
            String value4 = tcti.getText();
            String sql = "UPDATE Аэропорты SET аэропорт_id= '" + value1 + "',название= '" + value2 + "',город= '" + value3 + "',страна_id= '" + value4 + "'where аэропорт_id='" + value1 + "' ";

            pst1 = conn1.prepareStatement(sql);
            pst1.execute();
            JOptionPane.showMessageDialog(null, "Изменено");
            UpdateTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    @FXML
    void menee(ActionEvent event) {

    }

    @FXML
    void obnovleniee(ActionEvent event) {
        idii.setCellValueFactory(new PropertyValueFactory<asetget, Integer>("id"));
        cnz.setCellValueFactory(new PropertyValueFactory<asetget, String>("название"));
        cgr.setCellValueFactory(new PropertyValueFactory<asetget, String>("город"));
        cst.setCellValueFactory(new PropertyValueFactory<asetget, Integer>("страна_id"));
        listBAP = getasetgetskpk();
        tbli.setItems(listBAP);

        JOptionPane.showMessageDialog(null, "Обновление");
    }




    @FXML
    void ydaleniee(ActionEvent event) {
        conn1 = Conect.ConnectDb();

        String sql = "Delete from Аэропорты where аэропорт_id = ?";
        try {

            assert conn1 != null;
            pst1 = conn1.prepareStatement(sql);
            pst1.setString(1, tidd.getText());
            pst1.execute();
            JOptionPane.showMessageDialog(null, "Удалён");
            UpdateTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    void initialize() {
        assert cgr != null : "fx:id=\"cgr\" was not injected: check your FXML file 'Aroport.fxml'.";
        assert cnz != null : "fx:id=\"cnz\" was not injected: check your FXML file 'Aroport.fxml'.";
        assert cst != null : "fx:id=\"cst\" was not injected: check your FXML file 'Aroport.fxml'.";
        assert idii != null : "fx:id=\"idii\" was not injected: check your FXML file 'Aroport.fxml'.";
        assert men != null : "fx:id=\"men\" was not injected: check your FXML file 'Aroport.fxml'.";
        assert tbli != null : "fx:id=\"tbli\" was not injected: check your FXML file 'Aroport.fxml'.";
        assert tcti != null : "fx:id=\"tcti\" was not injected: check your FXML file 'Aroport.fxml'.";
        assert tgr != null : "fx:id=\"tgr\" was not injected: check your FXML file 'Aroport.fxml'.";
        assert tidd != null : "fx:id=\"tidd\" was not injected: check your FXML file 'Aroport.fxml'.";
        assert tnz != null : "fx:id=\"tnz\" was not injected: check your FXML file 'Aroport.fxml'.";

    }

}
