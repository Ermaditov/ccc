package com.example.programi2;

import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.util.Date;

public class getset {
    int бронирование_id,билет_id;
    Date дата_бронирования;
    String статус;

    public int getБронирование_id() {
        return бронирование_id;
    }

    public void setБронирование_id(int бронирование_id) {
        this.бронирование_id = бронирование_id;
    }

    public int getБилет_id() {
        return билет_id;
    }

    public void setБилет_id(int билет_id) {
        this.билет_id = билет_id;
    }

    public Date getДата_бронирования() {
        return дата_бронирования;
    }

    public void setДата_бронирования(Date дата_бронирования) {
        this.дата_бронирования = дата_бронирования;
    }

    public String getСтатус() {
        return статус;
    }

    public void setСтатус(String статус) {
        this.статус = статус;
    }

    public getset(int бронирование_id, int билет_id, Date дата_бронирования, String статус) {
        this.бронирование_id = бронирование_id;
        this.билет_id = билет_id;
        this.дата_бронирования = дата_бронирования;
        this.статус = статус;
    }
}
