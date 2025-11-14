package com.example.integra_kids_mobile.ui.views.jogos.jogoCores;

import android.graphics.Color;
import android.view.View;

import com.example.integra_kids_mobile.ui.components.jogos.KeyView;

public class ColorBox {
    private long id;
    // Usar como se fosse um enum (Java enums suck)
    public final int NORMAL = 0, DARK = 1;
    private int currentColor = NORMAL;
    private String[] colors;
    private View container;
    private KeyView keyView;

    ColorBox(long id, String[] colors, View container, KeyView keyView) {
        this.id = id;
        this.colors = colors;
        this.container = container;
        this.keyView = keyView;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNormalColor() {
        this.currentColor = NORMAL;
        this.keyView.setKeyBackgroundColor(Color.parseColor(this.colors[this.currentColor]));
    }

    public void setDarkColor() {
        this.currentColor = DARK;
        this.keyView.setKeyBackgroundColor(Color.parseColor(this.colors[this.currentColor]));
    }

    public void toggleColor() {
        this.currentColor = (this.currentColor + 1) % 2;
        this.keyView.setKeyBackgroundColor(Color.parseColor(this.colors[this.currentColor]));
    }

    public String[] getColors() {
        return colors;
    }

    public void setColors(String[] colors) {
        this.colors = colors;
    }

    public View getContainer() {
        return container;
    }

    public void setContainer(View container) {
        this.container = container;
    }

    public KeyView getKeyView() {
        return keyView;
    }

    public void setKeyView(KeyView keyView) {
        this.keyView = keyView;
    }

    static class Builder {
        private long id = -1;
        private String[] colors;
        private View container;
        private KeyView keyView;

        public Builder() {
        }

        public Builder withId(long id) {
            this.id = id;
            return this;
        }

        public Builder withColors(String[] colors) {
            this.colors = colors;
            return this;
        }

        public Builder withContainer(View container) {
            this.container = container;
            return this;
        }

        public Builder withKeyView(KeyView keyView) {
            this.keyView = keyView;
            return this;
        }

        public ColorBox build() {
            return new ColorBox(this.id, this.colors, this.container, this.keyView);
        }
    }
}
