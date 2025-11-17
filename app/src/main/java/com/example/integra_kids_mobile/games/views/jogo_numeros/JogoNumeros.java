package com.example.integra_kids_mobile.games.views.jogo_numeros;

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

public class JogoNumeros extends AppCompatActivity {
    private final long id = 2;
    private final InfoJogos infoJogos = new InfoJogos(this.id, 0); // hardcoded
    private Timer timer;
    private List<KeyView> keyViewList = new ArrayList<>();
    private int placedNumberBoxes = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.jogo_cores);

        // Adiciona timer
        ConstraintLayout constraintLayout = findViewById(R.id.timer);
        timer = new Timer(this);
        constraintLayout.addView(timer);

        GridLayout gridLayout = findViewById(R.id.numeros_grid);

        // Cria 9 KeyViews para os números de 0 a 8
        for (int i = 0; i < 9; i++) {
            KeyView keyView = new KeyView(this);
            keyViewList.add(keyView);

            keyView.setOnClickListener(v -> {
                infoJogos.setTentativas(infoJogos.getTentativas() + 1);

                // Aqui você pode colocar sua lógica de acerto/erro
                infoJogos.setAcertos(infoJogos.getAcertos() + 1);
                placedNumberBoxes++;

                if (placedNumberBoxes == keyViewList.size()) {
                    infoJogos.terminarJogo();
                    finish();
                }
            });

            // Adiciona a KeyView na GridLayout
            gridLayout.addView(keyView);
        }

        infoJogos.comecarJogo();
    }
}
