package com.example.integra_kids_mobile.games.views.jogo_memoria;

import android.os.Bundle;
import android.widget.GridLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.integra_kids_mobile.R;
import com.example.integra_kids_mobile.games.components.KeyView;
import com.example.integra_kids_mobile.games.components.Timer;
import com.example.integra_kids_mobile.games.InfoJogos;

import java.util.ArrayList;
import java.util.List;

public class JogoMemoria extends AppCompatActivity {
    private final long id = 1;
    private final InfoJogos infoJogos = new InfoJogos(this.id, 0); // hardcoded
    private Timer timer;
    private List<KeyView> keyViewList = new ArrayList<>();
    private int placedCards = 0;
    private int selectedCards = 0;
    private int[] selectedCardIdxs = new int[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.jogo_cores);

        final ConstraintLayout constraintLayout = findViewById(R.id.timer);
        this.timer = new Timer(this);
        constraintLayout.addView(this.timer);

        final GridLayout gridLayout = findViewById(R.id.memoria_grid);

        for (int i = 0; i < 6; i++) {
            keyViewList.add(new KeyView(this));
            KeyView keyView = keyViewList.get(i);

            keyView.setOnClickListener(v -> {
                infoJogos.setTentativas(infoJogos.getTentativas() + 1);
                if (true) {
                    infoJogos.setAcertos(infoJogos.getAcertos() + 1);
                    placedCards++;
                    if (placedCards == keyViewList.toArray().length) {
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
