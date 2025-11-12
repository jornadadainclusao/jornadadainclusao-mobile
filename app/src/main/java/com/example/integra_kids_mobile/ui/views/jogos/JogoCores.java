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
    // Tons para cores
    // Java enums suck
    private final int NORMAL = 0, DARKER = 1;

    private final List<JogoCoresData> data = new ArrayList<>();
    private int previouslySelectedIdx = -1, currentlySelectedIdx = -1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.jogo_cores);

        String[][] colors = {
                {"#E9E9E9", "#B8B6B6"}, // branco
                {"#FA70E4", "#CC5CBA"}, // rosa
                {"#8FF43F", "#78BD42"}, // verde
                {"#FAA94B", "#BF8A4D"}, // laranja
                {"#FFE96A", "#BFB15C"}, // amarelo
                {"#EB4A4A", "#B84444"}  // vermelho
        };
        ConstraintLayout[] slots = {
                findViewById(R.id.coelho_slot),
                findViewById(R.id.borboleta_slot),
                findViewById(R.id.sapo_slot),
                findViewById(R.id.leao_slot),
                findViewById(R.id.peixe_slot),
                findViewById(R.id.joaninha_slot),
        };

        final GridLayout gridLayout = findViewById(R.id.cores_grid);

        for (int i = 0; i < colors.length; i++) {
            // Crie a cor
            final JogoCoresData currentData = new JogoCoresData.Builder()
                    .withId(i)
                    .withColors(colors[i])
                    .withContainer(slots[i])
                    .withKeyView(new KeyView(this))
                    .build();

            data.add(currentData);
            final KeyView circle = currentData.circle;

            // Coloque a cor no grid
            final GridLayout.LayoutParams gridParams = new GridLayout.LayoutParams();
            gridParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            gridParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            final int margin = (int) (circle.getKeyWidth() * 0.20);
            gridParams.setMargins(margin, margin, margin, margin);

            circle.setId(i);
            circle.setKeyBackgroundColor(Color.parseColor(currentData.colors[NORMAL]));
            circle.setStateListAnimator(null);
            circle.setLayoutParams(gridParams);
            gridLayout.addView(circle);

            // Não pode passar variáveis para lambdas
            final long idx = currentData.id;
            final View slot = currentData.container;
            slot.setOnClickListener(v -> {
                KeyView c = data.get(this.currentlySelectedIdx).circle;

                if (this.currentlySelectedIdx == idx && ! c.isPlaced()) {
                    final JogoCoresData _currentData = data.get(this.currentlySelectedIdx);

                    ConstraintLayout l = (ConstraintLayout) v;
                    GridLayout parent = findViewById(R.id.cores_grid);
                    String color = _currentData.colors[NORMAL];

                    parent.removeView(c);
                    l.addView(c);
                    c.setFocusable(false);
                    c.setPlaced(true);
                    c.setKeyBackgroundColor(Color.parseColor(color));
                }
            });

            // NOTE (renato): Escureça o círculo caso selecionado
            circle.setOnClickListener(v -> {
                final KeyView c = (KeyView) v;
                if (c.isPlaced()) {
                    return;
                }
                this.previouslySelectedIdx = this.currentlySelectedIdx;
                this.currentlySelectedIdx = c.getId();

                final JogoCoresData _currentData = data.get(this.currentlySelectedIdx);
                final KeyView currentCircle = _currentData.circle;
                if (this.previouslySelectedIdx == -1) {
                    currentCircle.setKeyBackgroundColor(Color.parseColor(_currentData.colors[NORMAL]));
                    return;
                }

                final JogoCoresData previousData = data.get(this.previouslySelectedIdx);
                final KeyView previousSquare = data.get(this.previouslySelectedIdx).circle;
                previousSquare.setKeyBackgroundColor(Color.parseColor(previousData.colors[NORMAL]));
                currentCircle.setKeyBackgroundColor(Color.parseColor(_currentData.colors[DARKER]));
            });
        }
    }
}
