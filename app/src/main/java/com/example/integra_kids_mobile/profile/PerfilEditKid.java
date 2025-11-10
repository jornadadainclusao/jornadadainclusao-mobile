package com.example.integra_kids_mobile.profile;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.integra_kids_mobile.R;
import com.example.integra_kids_mobile.common.ReturnButton;

import java.util.concurrent.atomic.AtomicInteger;

public class PerfilEditKid extends AppCompatActivity {

    Button btnSelectedPlayer;
    LinearLayout layoutEditKid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.perfil_edit_kid);

        ReturnButton.configurar(this);

        btnSelectedPlayer = findViewById(R.id.btnSelectedPlayer);
        layoutEditKid = findViewById(R.id.layoutEditKid);

        layoutEditKid.setVisibility(GONE);

        btnSelectedPlayer.setOnClickListener(v -> {
            layoutEditKid.setVisibility(VISIBLE);
            btnSelectedPlayer.setText("Mudar criança");
        });

        GridLayout gridAvatares = findViewById(R.id.gridAvatares);
        AtomicInteger selectedAvatarId = new AtomicInteger(-1);

        for (int i = 0; i < gridAvatares.getChildCount(); i++) {
            View child = gridAvatares.getChildAt(i);
            if (child instanceof ImageButton) {
                ImageButton avatar = (ImageButton) child;

                avatar.setOnClickListener(v -> {
                    // remove borda de todos
                    for (int j = 0; j < gridAvatares.getChildCount(); j++) {
                        View other = gridAvatares.getChildAt(j);
                        if (other instanceof ImageButton) {
                            other.setBackground(null);
                        }
                    }

                    // adiciona a borda circular no selecionado
                    avatar.setBackgroundResource(R.drawable.avatar_border);
                    selectedAvatarId.set(avatar.getId()); // salva qual foi escolhido
                });
            }
        }
    }
}