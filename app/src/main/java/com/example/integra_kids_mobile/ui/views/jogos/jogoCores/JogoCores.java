package com.example.integra_kids_mobile.ui.views.jogos.jogoCores;

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
    private final long id = 4;
    private final List<ColorBox> data = new ArrayList<>();
    private int selectedColorBoxIdx = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.jogo_cores);

        String[][] colors = {
                { "#E9E9E9", "#B8B6B6" }, // branco
                { "#FA70E4", "#CC5CBA" }, // rosa
                { "#8FF43F", "#78BD42" }, // verde
                { "#FAA94B", "#BF8A4D" }, // laranja
                { "#FFE96A", "#BFB15C" }, // amarelo
                { "#EB4A4A", "#B84444" } // vermelho
        };
        ConstraintLayout[] containers = {
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
            final ColorBox currentData = new ColorBox.Builder()
                    .withId(i)
                    .withColors(colors[i])
                    .withContainer(containers[i])
                    .withKeyView(new KeyView(this))
                    .build();

            data.add(currentData);
            final KeyView keyView = currentData.getKeyView();

            // Coloque a cor no grid
            final GridLayout.LayoutParams gridParams = new GridLayout.LayoutParams();
            gridParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            gridParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            final int margin = (int) (keyView.getKeyWidth() * 0.20);
            gridParams.setMargins(margin, margin, margin, margin);

            keyView.setId(i);
            keyView.setKeyBackgroundColor(Color.parseColor(currentData.getColors()[currentData.NORMAL]));
            keyView.setStateListAnimator(null);
            keyView.setLayoutParams(gridParams);
            gridLayout.addView(keyView);

            final View container = currentData.getContainer();
            container.setOnClickListener(v -> {
                KeyView kv = data.get(this.selectedColorBoxIdx).getKeyView();

                if (this.selectedColorBoxIdx == currentData.getId() && !kv.isPlaced()) {
                    final ColorBox _currentData = data.get(this.selectedColorBoxIdx);

                    ConstraintLayout l = (ConstraintLayout) v;
                    GridLayout parent = findViewById(R.id.cores_grid);
                    String color = _currentData.getColors()[_currentData.NORMAL];

                    parent.removeView(kv);
                    l.addView(kv);
                    kv.setFocusable(false);
                    kv.setPlaced(true);
                    kv.setKeyBackgroundColor(Color.parseColor(color));
                }
            });

            // NOTE (renato): Escureça o círculo caso selecionado
            keyView.setOnClickListener(v -> {
                final KeyView kv = (KeyView) v;
                if (kv.isPlaced()) {
                    return;
                }

                if (this.selectedColorBoxIdx != -1) {
                    data.get(this.selectedColorBoxIdx).toggleColor();
                }
                this.selectedColorBoxIdx = kv.getId();
                data.get(this.selectedColorBoxIdx).toggleColor();
            });
        }

        // Pegar o id do dependente
        // InfoJogos info = new InfoJogos(id, /* dependente */);
    }
}
