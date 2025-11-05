package com.example.integra_kids_mobile;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

public class UsuarioConfig extends Fragment {

    private RadioGroup radioGroup;
    private RadioButton radioSystem, radioLight, radioDark;

    public UsuarioConfig() {
        // Construtor vazio obrigatório
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Infla o layout do fragment
        return inflater.inflate(R.layout.usuario_config, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Referências para os elementos
        radioGroup = view.findViewById(R.id.radioTheme);
        radioSystem = view.findViewById(R.id.radioButton);
        radioLight = view.findViewById(R.id.radioButton2);
        radioDark = view.findViewById(R.id.radioButton3);

        // Recupera a preferência salva
        SharedPreferences prefs = requireContext().getSharedPreferences("AppPrefs", requireContext().MODE_PRIVATE);
        int themeMode = prefs.getInt("themeMode", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);

        // Aplica o tema atual
        AppCompatDelegate.setDefaultNightMode(themeMode);

        // Marca o botão correspondente
        switch (themeMode) {
            case AppCompatDelegate.MODE_NIGHT_YES:
                radioDark.setChecked(true);
                break;
            case AppCompatDelegate.MODE_NIGHT_NO:
                radioLight.setChecked(true);
                break;
            default:
                radioSystem.setChecked(true);
        }

        // Listener de mudança
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            int selectedMode = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;

            if (checkedId == R.id.radioButton2)
                selectedMode = AppCompatDelegate.MODE_NIGHT_NO;
            else if (checkedId == R.id.radioButton3)
                selectedMode = AppCompatDelegate.MODE_NIGHT_YES;

            // Aplica o novo tema
            AppCompatDelegate.setDefaultNightMode(selectedMode);

            // Salva preferência
            prefs.edit().putInt("themeMode", selectedMode).apply();
        });
    }
}
