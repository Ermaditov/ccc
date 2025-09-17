package com.example.ad1;

public class BAZA {
    int id;
    String tip, proizvoditil, model, nomer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getProizvoditil() {
        return proizvoditil;
    }

    public void setProizvoditil(String proizvoditil) {
        this.proizvoditil = proizvoditil;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNomer() {
        return nomer;
    }

    public void setNomer(String nomer) {
        this.nomer = nomer;
    }

    public BAZA(int id, String tip, String proizvoditil, String model, String nomer) {
        this.id = id;
        this.tip = tip;
        this.proizvoditil = proizvoditil;
        this.model = model;
        this.nomer = nomer;
    }
}
