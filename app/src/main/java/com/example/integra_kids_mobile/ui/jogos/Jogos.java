package com.example.integra_kids_mobile.ui.jogos;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.integra_kids_mobile.LoginCadastro;
import com.example.integra_kids_mobile.R;
import com.example.integra_kids_mobile.databinding.JogosBinding;
import com.example.integra_kids_mobile.games.JogoCores;
import com.example.integra_kids_mobile.games.JogoMemoria;
import com.example.integra_kids_mobile.games.JogoNumeros;
import com.example.integra_kids_mobile.games.JogoVogais;

public class Jogos extends Fragment {

    int[] gameDescribe = {
            R.string.game_describe_mem,
            R.string.game_describe_vog,
            R.string.game_describe_num,
            R.string.game_describe_cor,
    };
    View[] gameRoute = {}; // colocar as rotas dps aqui
    int[] viewImg = {
            R.drawable.memoria,
            R.drawable.vogais,
            R.drawable.numeros,
            R.drawable.cores};
    int[] colors = {
            R.color.red,
            R.color.blue,
            R.color.yellow,
            R.color.green
    };
    private Class<?>[] gameRoutes = {
            JogoMemoria.class,
            JogoVogais.class,
            JogoNumeros.class,
            JogoCores.class
    };
    int localGame;
    String viewGame;
    Button btnGame1, btnGame2, btnGame3, btnGame4, btnGameScreenReturn, btnPlay;
    LinearLayout layoutGameList, layoutGameFocus;
    ImageView imgGame;
    TextView textGameName, textGameDescribe;

    private JogosBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        JogosViewModel jogosViewModel =
                new ViewModelProvider(this).get(JogosViewModel.class);
    binding = JogosBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        btnGame1 = root.findViewById(R.id.btnGame1);
        btnGame2 = root.findViewById(R.id.btnGame2);
        btnGame3 = root.findViewById(R.id.btnGame3);
        btnGame4 = root.findViewById(R.id.btnGame4);

        btnGame1.setOnClickListener(v -> {openGameView(0,btnGame1.getText().toString());});
        btnGame2.setOnClickListener(v -> {openGameView(1,btnGame2.getText().toString());});
        btnGame3.setOnClickListener(v -> {openGameView(2,btnGame3.getText().toString());});
        btnGame4.setOnClickListener(v -> {openGameView(3,btnGame4.getText().toString());});

        btnPlay = root.findViewById(R.id.btnPlay); // sem uso até q os jogos existam

        btnGameScreenReturn = root.findViewById(R.id.btnGameScreenReturn);
        layoutGameFocus = root.findViewById(R.id.layoutGameFocus);
        layoutGameList = root.findViewById(R.id.layoutGameList);

        // inicialização
        layoutGameFocus.setVisibility(GONE);
        layoutGameList.setVisibility(VISIBLE);

        textGameName = root.findViewById(R.id.textGameName);
        textGameDescribe = root.findViewById(R.id.textGameDescribe);
        imgGame = root.findViewById(R.id.imgGame);

        // btn de retorno
        btnGameScreenReturn.setOnClickListener(v -> {
            layoutGameFocus.setVisibility(GONE);
            layoutGameList.setVisibility(VISIBLE);
        });

        btnPlay.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), gameRoutes[localGame]);
            startActivity(intent);
        });
        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void openGameView(int id, String name){
        layoutGameList.setVisibility(GONE);
        layoutGameFocus.setVisibility(VISIBLE);

        layoutGameFocus.setBackgroundResource(R.drawable.border);
        layoutGameFocus.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), colors[id]));

        textGameName.setText(name);
        textGameDescribe.setText(gameDescribe[id]);
        imgGame.setImageResource(viewImg[id]);
        // tem q adicionar as rotas dps aqui
        setGame(id);
    }

    public void setGame(int id){
        localGame = id;
    }
}