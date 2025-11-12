package com.example.integra_kids_mobile.model;

public class Jogador {
    private int id;
    private String nome;
    private int imagemResId;

    public Jogador(int id, String nome, int imagemResId) {
        this.id = id;
        this.nome = nome;
        this.imagemResId = imagemResId;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getImagemResId() {
        return imagemResId;
    }
}
