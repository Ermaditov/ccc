package com.example.programi2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Conect {
    public static Connection ConnectDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://192.168.1.141:3306/isp6", "isp6", "isp6");
            JOptionPane.showMessageDialog(null, "Соединение установлено.");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public static ObservableList<getset> getgetsetskpk() {
        Connection connTABL = ConnectDb();
        ObservableList<getset> list = FXCollections.observableArrayList();
        try {
            assert connTABL != null;
            PreparedStatement ps1 = connTABL.prepareStatement("select * from Бронирование");
            ResultSet rs1 = ps1.executeQuery();

            while (rs1.next()) {
                while (rs1.next()) {
                    int бронирование_Id = Integer.parseInt(rs1.getString("бронирование_id"));
                    int билет_Id = Integer.parseInt(rs1.getString("билет_id"));
                    java.sql.Date дата_Бронирования = rs1.getDate("дата_бронирования");
                    String статус = rs1.getString("статус");

                    // Создание объекта getset и добавление его в список
                    list.add(new getset(бронирование_Id, билет_Id, дата_Бронирования, статус));
                }

            }
        } catch (Exception ignored) {
        }
        return list;
    }

    public static ObservableList<asetget> getasetgetskpk() {
        Connection connTABL = ConnectDb();
        ObservableList<asetget> list = FXCollections.observableArrayList();
        try {
            assert connTABL != null;
            PreparedStatement ps1 = connTABL.prepareStatement("select * from Аэропорты");
            ResultSet rs1 = ps1.executeQuery();

            while (rs1.next()) {
                while (rs1.next()) {
                    int аэропорт_id = Integer.parseInt(rs1.getString("аэропорт_id"));
                    String название = rs1.getString("название");
                    String город = rs1.getString("город");
                    int страна_id = Integer.parseInt(rs1.getString("страна_id"));

                    // Создание объекта getset и добавление его в список
                    list.add(new asetget(аэропорт_id, название, город, страна_id));
                }

            }
        } catch (Exception ignored) {
        }
        return list;

    }

    public static ObservableList<geto> getgetokpk() {
        Connection connTABL = ConnectDb();
        ObservableList<geto> list = FXCollections.observableArrayList();
        try {
            assert connTABL != null;
            PreparedStatement ps1 = connTABL.prepareStatement("select * from Авиакомпании");
            ResultSet rs1 = ps1.executeQuery();

            while (rs1.next()) {
                while (rs1.next()) {
                    int авиакомпания_id = Integer.parseInt(rs1.getString("авиакомпания_id"));
                    String название = rs1.getString("название");
                    int страна_id = Integer.parseInt(rs1.getString("страна_id"));

                    // Создание объекта getset и добавление его в список
                    list.add(new geto(авиакомпания_id, название, страна_id));
                }

            }
        } catch (Exception ignored) {
        }
        return list;

    }
}




