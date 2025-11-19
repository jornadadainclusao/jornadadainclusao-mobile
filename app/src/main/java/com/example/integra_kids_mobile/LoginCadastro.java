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

import org.json.JSONObject;

public class LoginCadastro extends AppCompatActivity {

    private ImageView imageLogo;
    private Button btnCadLog1, btnCadLog2, btnEsqueciSenha;
    private LinearLayout layoutRegister, layoutLogin;
    private TextInputEditText inputLoginEmail, inputLoginSenha;
    private TextInputEditText inputRegNome, inputRegEmail, inputRegSenha, inputRegSenhaConf;
    private boolean isRegisterMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Se já estiver logado, vai direto para o MenuPrincipal
        LoginAuth.checkLoginRedirect(this);

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login_cadastro);

        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        int themeMode = prefs.getInt("themeMode", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        AppCompatDelegate.setDefaultNightMode(themeMode);

        imageLogo = findViewById(R.id.imageLogo);
        btnCadLog1 = findViewById(R.id.btnCadLog1);
        btnCadLog2 = findViewById(R.id.btnCadLog2);
        layoutLogin = findViewById(R.id.layoutLogin);
        layoutRegister = findViewById(R.id.layoutRegister);

        // LOGIN INPUTS
        inputLoginEmail = findViewById(R.id.inputLoginEmail);
        inputLoginSenha = findViewById(R.id.inputLoginSenha);

        // REGISTER INPUTS
        inputRegNome = findViewById(R.id.inputRegNome);
        inputRegEmail = findViewById(R.id.inputRegEmail);
        inputRegSenha = findViewById(R.id.inputRegSenha);
        inputRegSenhaConf = findViewById(R.id.inputRegSenhaConf);

        btnCadLog2.setOnClickListener(v -> {
            if(layoutLogin.getVisibility() == View.GONE){
                layoutLogin.setVisibility(View.VISIBLE);
                layoutRegister.setVisibility(View.GONE);
                btnCadLog2.setText("Faça seu cadastro");
                btnCadLog1.setText("Logar");
                isRegisterMode = false;
            }
            else{
                layoutRegister.setVisibility(View.VISIBLE);
                layoutLogin.setVisibility(View.GONE);
                btnCadLog2.setText("Já tem conta? Faça login");
                btnCadLog1.setText("Cadastrar");
                isRegisterMode = true;
            }
        });


        btnCadLog1.setOnClickListener(v -> {

            String btnText = btnCadLog1.getText().toString();
            String cadEmail = inputRegEmail.getText().toString().trim();
            String nome  = inputRegNome.getText().toString().trim();
            String cadSenha = inputRegSenha.getText().toString().trim();
            String cadSenhaConf = inputRegSenhaConf.getText().toString().trim();

            String logEmail = inputLoginEmail.getText().toString().trim();
            String logSenha = inputLoginSenha.getText().toString().trim();

            // ============================================================
            //                CADASTRAR
            // ============================================================
            if (isRegisterMode) {

                if (TextUtils.isEmpty(cadEmail) || TextUtils.isEmpty(nome) || TextUtils.isEmpty(cadSenha)) {
                    Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!cadSenha.equals(cadSenhaConf)) {
                    Toast.makeText(this, "A confirmação de senha não coincide!", Toast.LENGTH_SHORT).show();
                    return;
                }

                new Thread(() -> {
                    try {
                        JSONObject resp = UsuarioService.cadastrar(this, nome, cadEmail, cadSenha);

                        runOnUiThread(() -> {
                            Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();

                            // voltar para o login
                            layoutLogin.setVisibility(View.VISIBLE);
                            layoutRegister.setVisibility(View.GONE);
                            btnCadLog2.setText("Faça seu cadastro");
                            btnCadLog1.setText("Logar");
                            isRegisterMode = false;
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
            //                LOGIN (agora usando LoginAuth)
            // ============================================================
            if (TextUtils.isEmpty(logEmail) || TextUtils.isEmpty(logSenha)) {
                Toast.makeText(this, "Preencha email e senha!", Toast.LENGTH_SHORT).show();
                return;
            }

            new Thread(() -> {
                boolean success = LoginAuth.login(this, logEmail, logSenha);

                runOnUiThread(() -> {
                    if (success) {
                        Intent i = new Intent(LoginCadastro.this, MenuPrincipal.class);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(this, "Email ou senha incorretos!", Toast.LENGTH_SHORT).show();
                    }
                });

            }).start();
        });

    }
}