package com.example.integra_kids_mobile.ui.views.jogos.jogoVogais;

import android.graphics.Color;
import android.os.Bundle;
import android.transition.ChangeTransform;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.integra_kids_mobile.R;
import com.example.integra_kids_mobile.ui.components.jogos.KeyView;
import com.example.integra_kids_mobile.ui.components.jogos.KeyViewStateEnum;
import com.example.integra_kids_mobile.ui.components.jogos.Timer;
import com.example.integra_kids_mobile.ui.components.jogos.jogo_cores.ColorView;
import com.example.integra_kids_mobile.ui.views.jogos.InfoJogos;

import java.util.ArrayList;
import java.util.List;

public class jogoVogais extends AppCompatActivity {
    private final long id = 3;
    private final InfoJogos infoJogos = new InfoJogos(this.id, 0); // hardcoded
    private Timer timer;
    private List<KeyView> keyViewList = new ArrayList<>();
    private int placedLetterBoxes = 0;
    private int selectedLetterBoxIdx = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.jogo_cores);

        final ConstraintLayout constraintLayout = findViewById(R.id.timer);
        this.timer = new Timer(this);
        constraintLayout.addView(this.timer);

        final GridLayout gridLayout = findViewById(R.id.vogais_grid);

        for (int i = 'a'; i < 'z'; i++) {
            keyViewList.add(new KeyView(this));
            KeyView keyView = keyViewList.get(i);

            keyView.setOnClickListener(v -> {
                infoJogos.setTentativas(infoJogos.getTentativas() + 1);
                if (true) {
                    infoJogos.setAcertos(infoJogos.getAcertos() + 1);
                    placedLetterBoxes++;
                    if (placedLetterBoxes == keyViewList.toArray().length) {
                        infoJogos.terminarJogo();
                        finish();
                    }
                } else {
                    infoJogos.setErros(infoJogos.getErros() + 1);
                }
            });
        }

        infoJogos.comecarJogo();
    }
}
