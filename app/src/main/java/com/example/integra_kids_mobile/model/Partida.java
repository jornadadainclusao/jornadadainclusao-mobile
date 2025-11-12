package com.example.integra_kids_mobile.model;

public class Partida {
    private String nomeJogo;
    private String data;
    private int acertos;
    private int erros;
    private int tempo; // tempo em segundos

    public Partida(String nomeJogo, String data, int acertos, int erros, int tempo) {
        this.nomeJogo = nomeJogo;
        this.data = data;
        this.acertos = acertos;
        this.erros = erros;
        this.tempo = tempo;
    }

    public String getNomeJogo() {
        return nomeJogo;
    }

    public String getData() {
        return data;
    }

    public int getAcertos() {
        return acertos;
    }

    public int getErros() {
        return erros;
    }

    public int getTempo() {
        return tempo;
    }

    public void setNomeJogo(String nomeJogo) {
        this.nomeJogo = nomeJogo;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setAcertos(int acertos) {
        this.acertos = acertos;
    }

    public void setErros(int erros) {
        this.erros = erros;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }
}
