package com.example.integra_kids_mobile.ui.views.jogos;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.integra_kids_mobile.R;
import com.example.integra_kids_mobile.ui.components.jogos.KeyView;

import java.util.ArrayList;
import java.util.List;

public class JogoCores extends AppCompatActivity {
    enum colorsEnum {
        NAME,
        NORMAL_TONE,
        DARKER_TONE,
    };
    final private String[][] colors = {
            {"branco", "#E9E9E9", "#B8B6B6"},
            {"rosa", "#FA70E4", "#CC5CBA"},
            {"verde", "#8FF43F", "#78BD42"},
            {"laranja", "#FAA94B", "#BF8A4D"},
            {"amarelo", "#FFE96A", "#BFB15C"},
            {"vermelho", "#EB4A4A", "#B84444"}
    };

    List<KeyView> squares = new ArrayList<>();
    int previouslySelected = -1, currentlySelected = -1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.jogo_cores);

        ConstraintLayout[] slots = {
                findViewById(R.id.coelho_slot),
                findViewById(R.id.borboleta_slot),
                findViewById(R.id.sapo_slot),
                findViewById(R.id.leao_slot),
                findViewById(R.id.peixe_slot),
                findViewById(R.id.joaninha_slot),
        };

        final GridLayout gridLayout = findViewById(R.id.cores_grid);

        for (int i = 0; i < this.colors.length; i++) {
            this.squares.add(i, new KeyView(this));
            final KeyView square = this.squares.get(i);

            final GridLayout.LayoutParams gridParams = new GridLayout.LayoutParams();
            gridParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            gridParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            final int margin = (int) (square.getKeyWidth() * 0.20);
            gridParams.setMargins(margin, margin, margin, margin);

            square.setId(i);
            square.setKeyBackgroundColor(Color.parseColor(this.colors[i][colorsEnum.NORMAL_TONE.ordinal()]));
            square.setStateListAnimator(null);
            square.setLayoutParams(gridParams);
            gridLayout.addView(square);

            // Não pode passar variáveis para lambdas
            final int idx = i;
            final View slot = slots[idx];
            slot.setOnClickListener(v -> {
                KeyView s = this.squares.get(this.currentlySelected);

                if (this.currentlySelected == idx && s.isFocusable()) {
                    ConstraintLayout l = (ConstraintLayout) v;
                    GridLayout parent = findViewById(R.id.cores_grid);

                    parent.removeView(s);
                    l.addView(s);
                    s.setFocusable(false);
                    s.setKeyBackgroundColor(Color.parseColor(this.colors[idx][colorsEnum.NORMAL_TONE.ordinal()]));
                }
            });

            // NOTE (renato): Escureça o botão caso selecionado
            square.setOnClickListener(v -> {
                this.previouslySelected = this.currentlySelected;
                this.currentlySelected = v.getId();
                final KeyView currentSquare = this.squares.get(this.currentlySelected);

                if (this.previouslySelected == -1) {
                    currentSquare.setKeyBackgroundColor(Color.parseColor(colors[this.currentlySelected][colorsEnum.NORMAL_TONE.ordinal()]));
                    return;
                }
                final KeyView previousSquare = this.squares.get(this.previouslySelected);

                // Caso escolha um círculo já settado
                if (previousSquare == currentSquare) {
                    return;
                }

                previousSquare.setKeyBackgroundColor(Color.parseColor(this.colors[this.previouslySelected][colorsEnum.NORMAL_TONE.ordinal()]));
                currentSquare.setKeyBackgroundColor(Color.parseColor(this.colors[this.currentlySelected][colorsEnum.DARKER_TONE.ordinal()]));
            });
        }
    }
}
