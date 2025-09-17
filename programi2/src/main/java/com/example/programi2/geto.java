package com.example.programi2;

public class geto {
    int id,страна_id;
    String название;

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

    public int getСтрана_id() {
        return страна_id;
    }

    public void setСтрана_id(int страна_id) {
        this.страна_id = страна_id;
    }

    public geto(int id, String название, int страна_id) {
        this.id = id;
        this.название = название;
        this.страна_id = страна_id;
    }
}
