package com.example.integra_kids_mobile.model;

public class Partida {
    private String nomeJogo;
    private String data;

    public Partida(String nomeJogo, String data) {
        this.nomeJogo = nomeJogo;
        this.data = data;
    }

    public String getNomeJogo() { return nomeJogo; }
    public String getData() { return data; }
}
