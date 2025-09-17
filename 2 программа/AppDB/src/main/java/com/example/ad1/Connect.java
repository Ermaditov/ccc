package com.example.ad1;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.*;

public class Connect {
    public static Connection ConnectDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://192.168.1.141:3306/isp16", "isp16", "isp16");
            JOptionPane.showMessageDialog(null, "Соединение установлено.");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    public static ObservableList<BAZA> getBAZAkpk() {
        Connection connTABL = ConnectDb();
        ObservableList<BAZA> list = FXCollections.observableArrayList();
        try {
            assert connTABL != null;
            PreparedStatement ps1 = connTABL.prepareStatement("select * from BazaATS");
            ResultSet rs1 = ps1.executeQuery();

            while (rs1.next()) {
                list.add(new BAZA(Integer.parseInt(rs1.getString("id")), rs1.getString("tip"), rs1.getString("proizvoditil"), rs1.getString("model"), rs1.getString("nomer")));
            }
        } catch (Exception ignored) {
        }
        return list;
    }


}
