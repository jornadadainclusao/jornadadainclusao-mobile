package com.example.integra_kids_mobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.integra_kids_mobile.API.UsuarioService;
import com.example.integra_kids_mobile.auth.LoginAuth;
import com.google.android.material.textfield.TextInputEditText;

public class LoginCadastro extends AppCompatActivity {

    private ImageView imageLogo;
    private Button btnCadLog1, btnCadLog2;
    private LinearLayout layoutRegister, layoutLogin;
    private TextInputEditText inputLoginEmail, inputLoginSenha;
    private TextInputEditText inputRegNome, inputRegEmail, inputRegSenha, inputRegSenhaConf;
    private CheckBox checkBoxLembrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login_cadastro);

        // Tema
        SharedPreferences themePrefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        int themeMode = themePrefs.getInt("themeMode", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        AppCompatDelegate.setDefaultNightMode(themeMode);

        // Verifica se deve manter login
        SharedPreferences authPrefs = getSharedPreferences("AuthPrefs", MODE_PRIVATE);
        boolean manterLogin = authPrefs.getBoolean("manter_login", false);
        String token = authPrefs.getString("user_token", "");

        if (manterLogin && token != null && !token.isEmpty()) {
            startActivity(new Intent(this, MenuPrincipal.class));
            finish();
            return;
        }

        // Views
        imageLogo = findViewById(R.id.imageLogo);
        btnCadLog1 = findViewById(R.id.btnCadLog1);
        btnCadLog2 = findViewById(R.id.btnCadLog2);
        layoutLogin = findViewById(R.id.layoutLogin);
        layoutRegister = findViewById(R.id.layoutRegister);

        // LOGIN INPUTS
        inputLoginEmail = findViewById(R.id.inputLoginEmail);
        inputLoginSenha = findViewById(R.id.inputLoginSenha);
        checkBoxLembrar = findViewById(R.id.checkBoxLembrar);

        // REGISTER INPUTS
        inputRegNome = findViewById(R.id.inputRegNome);
        inputRegEmail = findViewById(R.id.inputRegEmail);
        inputRegSenha = findViewById(R.id.inputRegSenha);
        inputRegSenhaConf = findViewById(R.id.inputRegSenhaConf);

        // Alternar login ↔ cadastro
        btnCadLog2.setOnClickListener(v -> {
            if (layoutLogin.getVisibility() == View.GONE) {
                layoutLogin.setVisibility(View.VISIBLE);
                layoutRegister.setVisibility(View.GONE);
                btnCadLog2.setText("Faça seu cadastro");
                btnCadLog1.setText("Logar");
            } else {
                layoutRegister.setVisibility(View.VISIBLE);
                layoutLogin.setVisibility(View.GONE);
                btnCadLog2.setText("Já tem conta? Faça login");
                btnCadLog1.setText("Cadastrar");
            }
        });

        // BOTÃO LOGAR / CADASTRAR
        btnCadLog1.setOnClickListener(v -> handleAuthAction());
    }

    // ============================================================
    //                     FUNÇÃO PRINCIPAL
    // ============================================================
    private void handleAuthAction() {

        String action = btnCadLog1.getText().toString();

        // ===========================
        //        DADOS LOGIN
        // ===========================
        String logEmail = inputLoginEmail.getText().toString().trim();
        String logSenha = inputLoginSenha.getText().toString().trim();

        // ===========================
        //        DADOS CADASTRO
        // ===========================
        String nome = inputRegNome.getText().toString().trim();
        String cadEmail = inputRegEmail.getText().toString().trim();
        String cadSenha = inputRegSenha.getText().toString().trim();
        String cadSenhaConf = inputRegSenhaConf.getText().toString().trim();

        // ============================================================
        //                        CADASTRAR
        // ============================================================
        if (action.equals("Cadastrar")) {

            if (TextUtils.isEmpty(nome) || TextUtils.isEmpty(cadEmail) ||
                    TextUtils.isEmpty(cadSenha) || TextUtils.isEmpty(cadSenhaConf)) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!cadSenha.equals(cadSenhaConf)) {
                Toast.makeText(this, "As senhas não coincidem!", Toast.LENGTH_SHORT).show();
                return;
            }

            new Thread(() -> {
                try {
                    UsuarioService.cadastrar(this, nome, cadEmail, cadSenha);

                    runOnUiThread(() -> {
                        Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                        voltarParaLogin();
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                    runOnUiThread(() ->
                            Toast.makeText(this, "Erro ao cadastrar!", Toast.LENGTH_SHORT).show()
                    );
                }
            }).start();

            return;
        }

        // ============================================================
        //                            LOGIN
        // ============================================================
        if (TextUtils.isEmpty(logEmail) || TextUtils.isEmpty(logSenha)) {
            Toast.makeText(this, "Preencha email e senha!", Toast.LENGTH_SHORT).show();
            return;
        }

        new Thread(() -> {
            boolean sucesso = LoginAuth.login(this, logEmail, logSenha);

            runOnUiThread(() -> {
                if (sucesso) {

                    // Salva o "lembrar login"
                    SharedPreferences prefs = getSharedPreferences("AuthPrefs", MODE_PRIVATE);
                    prefs.edit().putBoolean("manter_login", checkBoxLembrar.isChecked()).apply();

                    startActivity(new Intent(this, MenuPrincipal.class));
                    finish();
                } else {
                    Toast.makeText(this, "Email ou senha incorretos!", Toast.LENGTH_SHORT).show();
                }
            });
        }).start();
    }

    private void voltarParaLogin() {
        layoutLogin.setVisibility(View.VISIBLE);
        layoutRegister.setVisibility(View.GONE);
        btnCadLog2.setText("Faça seu cadastro");
        btnCadLog1.setText("Logar");
    }
}
