package com.example.integra_kids_mobile.games.views.jogo_numeros;

import android.view.View;

import com.example.integra_kids_mobile.games.components.jogo_cores.ColorView;
import com.example.integra_kids_mobile.games.components.jogo_numeros.NumeroView;

public class NumeroViewState {
    private View container;
    private char currentNumber = '0';
    private NumeroView[] numeroView = new NumeroView[10];

    private int size = 0;

    NumeroViewState(View container) {
        this.container = container;
    }

    public View getContainer() {
        return container;
    }

    public void setContainer(View container) {
        this.container = container;
    }

    public char getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(char currentNumber) {
        this.currentNumber = currentNumber;
    }

    public NumeroView getNumeroView(int index) {
        return numeroView[index];
    }

    public void addNumeroView(NumeroView numeroView) {
        this.numeroView[this.size] = numeroView;
        this.size += 1;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
