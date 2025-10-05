package com.example.integra_kids_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ImageView imageLogo;
    private Button btnCadLog1, btnCadLog2;
    private LinearLayout layoutRegister, layoutLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        imageLogo = findViewById(R.id.imageLogo);
        btnCadLog1 = findViewById(R.id.btnCadLog1);
        btnCadLog2 = findViewById(R.id.btnCadLog2);
        layoutLogin = findViewById(R.id.layoutLogin);
        layoutRegister = findViewById(R.id.layoutRegister);

        btnCadLog2.setOnClickListener(v -> {
            if(layoutLogin.getVisibility() == View.GONE){
                layoutLogin.setVisibility(View.VISIBLE);
                layoutRegister.setVisibility(View.GONE);
                btnCadLog2.setText("Faça seu cadastro");
                btnCadLog1.setText("Logar");
            }
            else{
                layoutRegister.setVisibility(View.VISIBLE);
                layoutLogin.setVisibility(View.GONE);
                btnCadLog2.setText("Já tem conta? Faça login");
                btnCadLog1.setText("Cadastrar");
            }
        });
        btnCadLog1.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity2.class);
            startActivity(intent);
            finish();
        });

    }
}