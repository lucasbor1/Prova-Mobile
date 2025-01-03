package com.example.prova;

public class Conselho {

    private int id;
    private String conselho;

    public Conselho(int id, String conselho) {
        this.id = id;
        this.conselho = conselho;
    }

    public int getId() {
        return id;
    }

    public String getConselho() {
        return conselho;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setConselho(String conselho) {
        this.conselho = conselho;
    }
}
