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

        // Lista de exemplo
        listaJogadores = new ArrayList<>();
        listaJogadores.add(new Jogador(1,"Luna", R.drawable.player_icon1));
        listaJogadores.add(new Jogador(2,"Pedro", R.drawable.player_icon2));
        listaJogadores.add(new Jogador(3,"Mia", R.drawable.player_icon3));
        listaJogadores.add(new Jogador(4,"Leo", R.drawable.player_icon4));
        listaJogadores.add(new Jogador(5,"Junin", R.drawable.player_icon11));
        listaJogadores.add(new Jogador(6,"Kleiton", R.drawable.player_icon6));
        listaJogadores.add(new Jogador(7,"Jubileu", R.drawable.player_icon7));

        jogadorAdapter = new JogadorAdapter(listaJogadores, this);

        // 🔹 GridLayout com 2 colunas
        GridLayoutManager glm = new GridLayoutManager(this, 2);

        // 🔹 Centraliza o último item quando o total for ímpar
        glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int total = jogadorAdapter.getItemCount();

                if (total % 2 == 1 && position == total - 1) {
                    return 2; // ocupa as duas colunas → centraliza
                }
                return 1;
            }
        });

        recyclerJogadores.setLayoutManager(glm);
        recyclerJogadores.setAdapter(jogadorAdapter);
    }
}
