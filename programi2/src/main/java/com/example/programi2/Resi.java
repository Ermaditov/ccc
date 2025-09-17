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
import javafx.scene.input.MouseEvent;

import javax.swing.*;

import static com.example.programi2.Conect.getgetsetskpk;

public class Resi {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button dob;

    @FXML
    private TableColumn<getset, Date> dtbr;

    @FXML
    private TableColumn<getset, Integer> idbil;

    @FXML
    private TableColumn<getset, Integer> idi;

    @FXML
    private Button izm;

    @FXML
    private Button nzd;

    @FXML
    private Button obn;

    @FXML
    private TextField pois;

    @FXML
    private TableColumn<getset, String> sts;

    @FXML
    private TableView<getset> tabl;

    @FXML
    private TextField tbl;

    @FXML
    private TextField tbr;

    @FXML
    private TextField tdt;

    @FXML
    private TextField tst;


    @FXML
    private Button yd;
    int index = -1;
    ObservableList<getset> listBAP; //Название массива
    Connection conn1 = null; //Конектор равен нулю
    PreparedStatement pst1 = null;

    @FXML
    void dobovlenia(ActionEvent event) {

        conn1 = Conect.ConnectDb();

        String sql = "insert into Бронирование (бронирование_id, билет_id, дата_бронирования, статус) values(?,?,?,?)";

        try {
            assert conn1 != null;
            pst1 = conn1.prepareStatement(sql);
            pst1.setString(1, tbr.getText());
            pst1.setString(2, tbl.getText());
            pst1.setString(3, tdt.getText());
            pst1.setString(4, tst.getText());
            pst1.execute();

            JOptionPane.showMessageDialog(null, "Добавлено!");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    void search_user() { //Поисковик.
        idi.setCellValueFactory(new PropertyValueFactory<getset, Integer>("бронирование_id"));
        idbil.setCellValueFactory(new PropertyValueFactory<getset, Integer>("билет_id"));
        dtbr.setCellValueFactory(new PropertyValueFactory<getset, Date>("дата_бронирования"));
        sts.setCellValueFactory(new PropertyValueFactory<getset, String>("статус"));

        listBAP = getgetsetskpk();
        tabl.setItems(listBAP);

        FilteredList<getset> filteredData = new FilteredList<>(listBAP, b -> true);
        pois.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(person.getБронирование_id()).contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(person.getБилет_id()).contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(person.getДата_бронирования()).contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(person.getСтатус()).contains(lowerCaseFilter))
                    return true;

                else
                    return false;
            });
        });
        SortedList<getset> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tabl.comparatorProperty());
        tabl.setItems(sortedData);
    }


    private void UpdateTable() {
        idi.setCellValueFactory(new PropertyValueFactory<getset, Integer>("бронирование_id"));
        idbil.setCellValueFactory(new PropertyValueFactory<getset, Integer>("билет_id"));
        dtbr.setCellValueFactory(new PropertyValueFactory<getset, Date>("дата_бронирования"));
        sts.setCellValueFactory(new PropertyValueFactory<getset, String>("статус"));
        listBAP = getgetsetskpk();
        tabl.setItems(listBAP);

        JOptionPane.showMessageDialog(null, "Обновление");
    }

    @FXML
    void izmenenia(ActionEvent event) {
        try {
            conn1 = Conect.ConnectDb();
            String value1 = tbr.getText();
            String value2 = tbl.getText();
            String value3 = tdt.getText();
            String value4 = tst.getText();
            String sql = "UPDATE Бронирование SET бронирование_id= '" + value1 + "',билет_id= '" + value2 + "',дата_бронирования= '" + value3 + "',статус= '" + value4 + "'where бронирование_id='" + value1 + "' ";

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
    void nazad(ActionEvent event) {

    }
    @FXML
    void getSelected(MouseEvent event) {
       }

    @FXML
    void obnovlenie(ActionEvent event) {
        idi.setCellValueFactory(new PropertyValueFactory<getset, Integer>("бронирование_id"));
        idbil.setCellValueFactory(new PropertyValueFactory<getset, Integer>("билет_id"));
        dtbr.setCellValueFactory(new PropertyValueFactory<getset, Date>("дата_бронирования"));
        sts.setCellValueFactory(new PropertyValueFactory<getset, String>("статус"));
        listBAP = getgetsetskpk();
        tabl.setItems(listBAP);

        JOptionPane.showMessageDialog(null, "Обновление");
    }


    



    @FXML
    void ydalenia(ActionEvent event) {
        conn1 = Conect.ConnectDb();

        String sql = "Delete from Бронирование where бронирование_id = ?";
        try {

            assert conn1 != null;
            pst1 = conn1.prepareStatement(sql);
            pst1.setString(1, tbr.getText());
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

    }
}


