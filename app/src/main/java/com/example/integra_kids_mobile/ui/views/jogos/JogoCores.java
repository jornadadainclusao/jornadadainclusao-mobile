package com.example.integra_kids_mobile.ui.views.jogos;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.integra_kids_mobile.R;
import com.example.integra_kids_mobile.ui.components.jogos.KeyView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// NOTE (renato): EU ODEIO ANDROID STUDIO

public class JogoCores extends AppCompatActivity {
    final private String[][] cores = {
            {"branco", "#E9E9E9", "#B8B6B6"},
            {"rosa", "#FA70E4", "#CC5CBA"},
            {"verde", "#8FF43F", "#78BD42"},
            {"laranja", "#FAA94B", "#BF8A4D"},
            {"amarelo", "#FFE96A", "#BFB15C"},
            {"vermelho", "#EB4A4A", "#B84444"}
    };
    int[] ids = {
            0, 1, 2, 3, 4, 5
    };
    List<KeyView> squares = new ArrayList<>();
    int currentlySelected = -1;

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

        GridLayout gridLayout = findViewById(R.id.cores_grid);

        for (int i = 0; i < this.cores.length; i++) {
            this.squares.add(i, new KeyView(this));
            KeyView square = this.squares.get(i);

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            int margin = (int) (square.getKeyWidth() * 0.20);
            params.setMargins(margin, margin, margin, margin);

            square.setId(i);
            square.setKeyBackgroundColor(Color.parseColor(this.cores[i][1]));
            square.setStateListAnimator(null);
            square.setLayoutParams(params);

            final int idx = ids[i];
            slots[idx].setOnClickListener(v -> {
                if (this.currentlySelected == -1) {
                    return;
                } else if (this.currentlySelected != idx) {
                    return;
                } else {
                    ConstraintLayout l = (ConstraintLayout) v;
                    GridLayout parent = findViewById(R.id.cores_grid);

                    KeyView s = squares.get(this.currentlySelected);
                    parent.removeView(s);
                    l.addView(s);
                    s.setFocusable(false);
                }
            });

            // NOTE (renato): Mude a cor caso o botão seja selecionado
            square.setOnClickListener(v -> {
                KeyView currentSquare, previousSquare;
                // NOTE (renato): lol, state management... Puta hack, mas basicamente só tô pegando
                // a cor anterior e revertendo-a ao seu estado original (caso não seja a primeira)
                if (this.currentlySelected == -1) {
                    this.currentlySelected = v.getId();
                    currentSquare = squares.get(this.currentlySelected);
                    currentSquare.setKeyBackgroundColor(Color.parseColor(cores[this.currentlySelected][1]));
                    return;
                }

                int previousSelected = this.currentlySelected;
                previousSquare = squares.get(previousSelected);
                this.currentlySelected = v.getId();
                currentSquare = squares.get(this.currentlySelected);

                previousSquare.setKeyBackgroundColor(Color.parseColor(cores[previousSelected][1]));
                currentSquare.setKeyBackgroundColor(Color.parseColor(cores[this.currentlySelected][2]));
            });

            gridLayout.addView(square);
        }
    }
}
