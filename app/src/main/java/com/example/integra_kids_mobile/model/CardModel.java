package com.example.integra_kids_mobile.model;

public class CardModel {
    public String name;
    public int imageRes;
    public boolean isMatched = false;

    public CardModel(String name, int imageRes) {
        this.name = name;
        this.imageRes = imageRes;
    }
}
