package com.example.programi2;

public class asetget {
    int id,страна_id;
    String название,город;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getНазвание() {
        return название;
    }

    public void setНазвание(String название) {
        this.название = название;
    }

    public String getГород() {
        return город;
    }

    public void setГород(String город) {
        this.город = город;
    }

    public int getСтрана_id() {
        return страна_id;
    }

    public void setСтрана_id(int страна_id) {
        this.страна_id = страна_id;
    }

    public asetget(int id, String название, String город, int страна_id) {
        this.id = id;
        this.название = название;
        this.город = город;
        this.страна_id = страна_id;
    }
}

