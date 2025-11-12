package com.example.integra_kids_mobile.profile;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.integra_kids_mobile.R;
import com.example.integra_kids_mobile.adapter.JogadorAdapter;
import com.example.integra_kids_mobile.common.ReturnButton;
import com.example.integra_kids_mobile.model.Jogador;

import java.util.ArrayList;
import java.util.List;

public class PerfilTrocarPlayer extends AppCompatActivity {

    private RecyclerView recyclerJogadores;
    private JogadorAdapter jogadorAdapter;
    private List<Jogador> listaJogadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.perfil_trocar_player);

        ReturnButton.configurar(this);

        recyclerJogadores = findViewById(R.id.recyclerJogadores);

        // 🔹 Define layout em grade com 2 colunas
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerJogadores.setLayoutManager(gridLayoutManager);

        // Lista de exemplo
        listaJogadores = new ArrayList<>();
        listaJogadores.add(new Jogador(1,"Luna", R.drawable.player_icon1));
        listaJogadores.add(new Jogador(2,"Pedro", R.drawable.player_icon2));
        listaJogadores.add(new Jogador(3,"Mia", R.drawable.player_icon3));
        listaJogadores.add(new Jogador(4,"Leo", R.drawable.player_icon4));
        listaJogadores.add(new Jogador(5,"Junin", R.drawable.player_icon11));
        listaJogadores.add(new Jogador(6,"Kleiton", R.drawable.player_icon6));

        jogadorAdapter = new JogadorAdapter(listaJogadores, this);
        recyclerJogadores.setAdapter(jogadorAdapter);
    }

}


