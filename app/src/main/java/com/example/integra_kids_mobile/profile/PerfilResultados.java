package com.example.integra_kids_mobile.profile;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.integra_kids_mobile.R;
import com.example.integra_kids_mobile.adapter.HistoricoAdapter;
import com.example.integra_kids_mobile.common.ReturnButton;
import com.example.integra_kids_mobile.model.Partida;

import java.util.ArrayList;
import java.util.List;

public class PerfilResultados extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.perfil_resultados);

        ReturnButton.configurar(this);

        RecyclerView recyclerHistorico = findViewById(R.id.recyclerHistorico);
        recyclerHistorico.setLayoutManager(new LinearLayoutManager(this));

        List<Partida> lista = new ArrayList<>();
        lista.add(new Partida("Jogo das Cores", "09/11/2025"));
        lista.add(new Partida("Jogo da Memória", "10/11/2025"));
        lista.add(new Partida("Jogo das Cores", "09/11/2025"));
        lista.add(new Partida("Jogo das Cores", "09/11/2025"));
        lista.add(new Partida("Jogo das Cores", "09/11/2025"));
        lista.add(new Partida("Jogo das Cores", "09/11/2025"));
        lista.add(new Partida("Jogo das Cores", "09/11/2025"));
        lista.add(new Partida("Jogo das Cores", "09/11/2025"));
        lista.add(new Partida("Jogo das Cores", "09/11/2025"));


        HistoricoAdapter adapter = new HistoricoAdapter(lista, partida -> {
            // Ação ao clicar em deletar
            Toast.makeText(this,
                    "Partida deletada: " + partida.getNomeJogo(), Toast.LENGTH_SHORT).show();
        });

        recyclerHistorico.setAdapter(adapter);

    }
}