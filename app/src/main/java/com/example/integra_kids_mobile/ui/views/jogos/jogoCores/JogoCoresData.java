package com.example.integra_kids_mobile.ui.views.jogos.jogoCores;

import android.view.View;

import com.example.integra_kids_mobile.ui.components.jogos.KeyView;

public class JogoCoresData {
    private long id;
    private String[] colors;
    private View container;
    private KeyView circle;

    JogoCoresData(long id, String[] colors, View container, KeyView circle) {
        this.id = id;
        this.colors = colors;
        this.container = container;
        this.circle = circle;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public KeyView getCircle() {
        return circle;
    }

    public void setCircle(KeyView circle) {
        this.circle = circle;
    }

    static class Builder {
        private long id = -1;
        private String[] colors;
        private View container;
        private KeyView circle;

        public Builder() {}

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

        public Builder withKeyView(KeyView circle) {
            this.circle = circle;
            return this;
        }

        public JogoCoresData build() {
            return new JogoCoresData(this.id, this.colors, this.container, this.circle);
        }
    }
}