package com.example.integra_kids_mobile.games.views.jogo_cores;

import android.view.View;

import com.example.integra_kids_mobile.games.components.jogo_cores.ColorView;

public class ColorViewState {
    private long id;
    private View container;
    private ColorView colorView;

    ColorViewState(long id, View container, ColorView keyView) {
        this.id = id;
        this.container = container;
        this.colorView = keyView;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public View getContainer() {
        return container;
    }

    public void setContainer(View container) {
        this.container = container;
    }

    public ColorView getColorView() {
        return colorView;
    }

    public void setColorView(ColorView colorView) {
        this.colorView = colorView;
    }

    static class Builder {
        private long id = -1;
        private View container;
        private ColorView colorView;

        public Builder() {
        }

        public Builder withId(long id) {
            this.id = id;
            return this;
        }

        public Builder withContainer(View container) {
            this.container = container;
            return this;
        }

        public Builder withColorView(ColorView colorView) {
            this.colorView = colorView;
            return this;
        }

        public ColorViewState build() {
            return new ColorViewState(this.id, this.container, this.colorView);
        }
    }
}
