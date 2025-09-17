package com.example.ad1;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

import static com.example.ad1.Connect.ConnectDb;
import static com.example.ad1.Connect.getBAZAkpk;

//Выше приведен список всех импортируемых баз для корректной работы приложения.
 
public class BazaController { //Сам код котролера начинается от сюда.

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Vihod_id;

    //Ниже предоставлен список всех ID компонентов для обращения к ним и работы с ними.

    @FXML
    private TableColumn<BAZA, String> col_email;

    @FXML
    private TableColumn<BAZA, Integer> col_id;

    @FXML
    private TableColumn<BAZA, String> col_password;

    @FXML
    private TableColumn<BAZA, String> col_type;

    @FXML
    private TableColumn<BAZA, String> col_username;

    @FXML
    private TextField filterField;

    @FXML
    private TableView<BAZA> table_users;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_password;

    @FXML
    private TextField txt_type;

    @FXML
    private TextField txt_username;

    int index =-1; //Задача переменной

    ObservableList<BAZA> listBAPA; //Название массива
    Connection conn1 = null; //Конектор равен нулю
    PreparedStatement pst1= null;

    @FXML
    void Add_users(ActionEvent event) { //Кнопка добавления информации в таблицу.
        conn1 = Connect.ConnectDb();

        String sql = "insert into BazaATS (tip, proizvoditil, model, nomer) values(?,?,?,?)";

        try {
            assert conn1 != null;
            pst1 = conn1.prepareStatement(sql);
            pst1.setString(1, txt_username.getText());
            pst1.setString(2, txt_password.getText());
            pst1.setString(3, txt_email.getText());
            pst1.setString(4, txt_type.getText());
            pst1.execute();

            JOptionPane.showMessageDialog(null, "Добавлено!");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    void Delete(ActionEvent event) { //Кнопка удаления, работает она по айди.
        conn1 = ConnectDb();

        String sql = "Delete from BazaATS where id = ?";
        try {

            assert conn1 != null;
            pst1 = conn1.prepareStatement(sql);
            pst1.setString(1, txt_id.getText());
            pst1.execute();
            JOptionPane.showMessageDialog(null, "Удалён");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    void Edit(ActionEvent event) { //Кнопка обновления.
        try {
            conn1 = Connect.ConnectDb();

            String value1 = txt_id.getText();
            String value2 = txt_username.getText();
            String value3 = txt_password.getText();
            String value4 = txt_email.getText();
            String value5 = txt_type.getText();

            String sql = "update BazaATS set id = '" + value1 + "',tip = '" + value2 + "',proizvoditil = '" + value3 + "', model = '" + value4 + "', nomer = '" + value5 + "' where id = '" + value1 + "' ";

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
    void search_user() { //Поисковик.
        col_id.setCellValueFactory(new PropertyValueFactory<BAZA,Integer>("id"));
        col_username.setCellValueFactory(new PropertyValueFactory<BAZA,String>("tip"));
        col_password.setCellValueFactory(new PropertyValueFactory<BAZA,String>("proizvoditil"));
        col_email.setCellValueFactory(new PropertyValueFactory<BAZA,String>("model"));
        col_type.setCellValueFactory(new PropertyValueFactory<BAZA,String>("nomer"));

        listBAPA = getBAZAkpk();
        table_users.setItems(listBAPA);

        FilteredList<BAZA> filteredData = new FilteredList<>(listBAPA, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) ->  {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getTip().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (person.getProizvoditil().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (person.getNomer().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                else if (String.valueOf(person.getModel()).contains(lowerCaseFilter))
                    return true;

                else
                    return false;
            } );
        } );
        SortedList<BAZA> sortedData  = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_users.comparatorProperty());
        table_users.setItems(sortedData);
    }

    @FXML
    void UpdateTable() { //Обновление таблица для корректного отображения данных.
        col_id.setCellValueFactory(new PropertyValueFactory<BAZA, Integer>("id"));
        col_username.setCellValueFactory(new PropertyValueFactory<BAZA, String>("tip"));
        col_password.setCellValueFactory(new PropertyValueFactory<BAZA, String>("proizvoditil"));
        col_email.setCellValueFactory(new PropertyValueFactory<BAZA, String>("model"));
        col_type.setCellValueFactory(new PropertyValueFactory<BAZA, String>("nomer"));

        listBAPA = getBAZAkpk();
        table_users.setItems(listBAPA);

        JOptionPane.showMessageDialog(null, "Обновление");
    }

    @FXML
    void Vihod(ActionEvent event) {

    }

    @FXML
    void getSelected(MouseEvent event) {
        index = table_users.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }
        txt_id.setText(col_id.getCellData(index).toString());
        txt_username.setText(col_username.getCellData(index).toString());
        txt_password.setText(col_password.getCellData(index).toString());
        txt_email.setText(col_email.getCellData(index).toString());
        txt_type.setText(col_type.getCellData(index).toString());

    }

    @FXML
    void initialize() {
        UpdateTable();
        search_user();
    }
}


