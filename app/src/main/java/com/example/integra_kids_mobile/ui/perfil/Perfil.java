package com.example.integra_kids_mobile.ui.perfil;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.integra_kids_mobile.databinding.PerfilBinding;

import com.example.integra_kids_mobile.R;

import com.example.integra_kids_mobile.profile.PerfilEditCad;
import com.example.integra_kids_mobile.profile.PerfilCadKid;
import com.example.integra_kids_mobile.profile.PerfilEditKid;
import com.example.integra_kids_mobile.profile.PerfilResultados;
import com.example.integra_kids_mobile.profile.PerfilTrocarPlayer;
import com.example.integra_kids_mobile.LoginCadastro;

public class Perfil extends Fragment {

    Button btnProfile1, btnProfile2, btnProfile3, btnProfile4, btnProfile5, btnProfile6;
    private Class<?>[] profileRoutes = {
            PerfilEditCad.class,
            PerfilCadKid.class,
            PerfilEditKid.class,
            PerfilResultados.class,
            PerfilTrocarPlayer.class,
    };

private PerfilBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        PerfilViewModel perfilViewModel =
                new ViewModelProvider(this).get(PerfilViewModel.class);

    binding = PerfilBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

    btnProfile1 = root.findViewById(R.id.btnProfile1);
    btnProfile2 = root.findViewById(R.id.btnProfile2);
    btnProfile3 = root.findViewById(R.id.btnProfile3);
    btnProfile4 = root.findViewById(R.id.btnProfile4);
    btnProfile5 = root.findViewById(R.id.btnProfile5);
    btnProfile6 = root.findViewById(R.id.btnProfile6);

    btnProfile1.setOnClickListener(v -> callProfileScreen(0));
    btnProfile2.setOnClickListener(v -> callProfileScreen(1));
    btnProfile3.setOnClickListener(v -> callProfileScreen(2));
    btnProfile4.setOnClickListener(v -> callProfileScreen(3));
    btnProfile5.setOnClickListener(v -> callProfileScreen(4));
        btnProfile6.setOnClickListener(v -> {
            // Logout usando LoginAuth
            com.example.integra_kids_mobile.auth.LoginAuth.logout(requireContext());
        });


        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void callProfileScreen(int id){
        Intent intent = new Intent(requireContext(), profileRoutes[id]);
        startActivity(intent);
    }
}