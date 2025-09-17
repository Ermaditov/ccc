package com.example.programi2;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;

import static com.example.programi2.Conect.getgetokpk;
import static com.example.programi2.Conect.getgetsetskpk;

public class Aviacomp {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<geto, Integer> ccr;

    @FXML
    private TableColumn<geto, Integer> cid;

    @FXML
    private TableColumn<geto, String> cnaz;

    @FXML
    private Button mn;
    @FXML
    private TextField pisc;


    @FXML
    private TableView<geto> tb;

    @FXML
    private TextField tcti;

    @FXML
    private TextField tid;

    @FXML
    private TextField tnaz;
    int index = -1;
    ObservableList<geto> listBAP; //Название массива
    Connection conn1 = null; //Конектор равен нулю
    PreparedStatement pst1 = null;

    @FXML
    void dobavka(ActionEvent event) {
        conn1 = Conect.ConnectDb();

        String sql = "insert into Авиакомпании (авиакомпания_id, название,страна_id) values(?,?,?)";

        try {
            assert conn1 != null;
            pst1 = conn1.prepareStatement(sql);
            pst1.setString(1, tid.getText());
            pst1.setString(2, tnaz.getText());
            pst1.setString(3, tcti.getText());
            pst1.execute();

            JOptionPane.showMessageDialog(null, "Добавлено!");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
    private void UpdateTable() {
        cid.setCellValueFactory(new PropertyValueFactory<geto, Integer>("авиакомпания_id"));
        cnaz.setCellValueFactory(new PropertyValueFactory<geto, String>("название"));
        ccr.setCellValueFactory(new PropertyValueFactory<geto, Integer>("срана_id"));
        listBAP = getgetokpk();
        tb.setItems(listBAP);

        JOptionPane.showMessageDialog(null, "Обновление");
    }
    @FXML
    void search_user() { //Поисковик.
        cid.setCellValueFactory(new PropertyValueFactory<geto, Integer>("авиакомпания_id"));
        cnaz.setCellValueFactory(new PropertyValueFactory<geto, String>("билет_id"));
        ccr.setCellValueFactory(new PropertyValueFactory<geto, Integer>("страна_id"));


        listBAP = getgetokpk();
        tb.setItems(listBAP);

        FilteredList<geto> filteredData = new FilteredList<>(listBAP, b -> true);
        pisc.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(person.getId()).contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(person.getНазвание()).contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(person.getСтрана_id()).contains(lowerCaseFilter)) {
                    return true;
                }else
                    return false;
            });
        });
        SortedList<geto> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tb.comparatorProperty());
        tb.setItems(sortedData);
    }



    @FXML
    void izmena(ActionEvent event) {
        try {
            conn1 = Conect.ConnectDb();
            String value1 = tid.getText();
            String value2 = tnaz.getText();
            String value3 = tcti.getText();
            String sql = "UPDATE Авиакомпании SET авиакомпания_id= '" + value1 + "',название= '" + value2 + "',страна_id= '" + value3 + "' where авиакомпания_id='" + value1 + "' ";

            pst1 = conn1.prepareStatement(sql);
            pst1.execute();
            JOptionPane.showMessageDialog(null, "Изменено");

            UpdateTable();
            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    void menu(ActionEvent event) {

    }

    @FXML
    void obnova(ActionEvent event) {
        cid.setCellValueFactory(new PropertyValueFactory<geto, Integer>("авиакомпания_id"));
        cnaz.setCellValueFactory(new PropertyValueFactory<geto, String>("название"));
        ccr.setCellValueFactory(new PropertyValueFactory<geto, Integer>("срана_id"));
        listBAP = getgetokpk();
        tb.setItems(listBAP);

        JOptionPane.showMessageDialog(null, "Обновление");
    }



    @FXML
    void steret(ActionEvent event) {
        conn1 = Conect.ConnectDb();

        String sql = "Delete from Авиакомпания where авикомпания_id = ?";
        try {

            assert conn1 != null;
            pst1 = conn1.prepareStatement(sql);
            pst1.setString(1, tid.getText());
            pst1.execute();
            JOptionPane.showMessageDialog(null, "Удалён");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    @FXML
    void initialize() {
        assert ccr != null : "fx:id=\"ccr\" was not injected: check your FXML file 'aviacomp.fxml'.";
        assert cid != null : "fx:id=\"cid\" was not injected: check your FXML file 'aviacomp.fxml'.";
        assert cnaz != null : "fx:id=\"cnaz\" was not injected: check your FXML file 'aviacomp.fxml'.";
        assert mn != null : "fx:id=\"mn\" was not injected: check your FXML file 'aviacomp.fxml'.";
        assert tb != null : "fx:id=\"tb\" was not injected: check your FXML file 'aviacomp.fxml'.";
        assert tcti != null : "fx:id=\"tcti\" was not injected: check your FXML file 'aviacomp.fxml'.";
        assert tid != null : "fx:id=\"tid\" was not injected: check your FXML file 'aviacomp.fxml'.";
        assert tnaz != null : "fx:id=\"tnaz\" was not injected: check your FXML file 'aviacomp.fxml'.";

    }

}
