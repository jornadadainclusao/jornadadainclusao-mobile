package com.example.integra_kids_mobile.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.integra_kids_mobile.API.UsuarioService;
import com.example.integra_kids_mobile.R;
import com.example.integra_kids_mobile.common.ReturnButton;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

public class PerfilEditCad extends AppCompatActivity {

    private TextInputEditText inputNome, inputEmail, inputSenha, inputSenhaConf;
    private Button btnAltUser, btnDeleteUser;

    private long userId;
    private String token;

    private static final String PREF_NAME = "AuthPrefs";
    private static final String KEY_ID = "user_id";
    private static final String KEY_NOME = "user_nome";
    private static final String KEY_EMAIL = "user_email";
    private static final String KEY_TOKEN = "user_token";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.perfil_edit_cad);

        ReturnButton.configurar(this);

        inputNome = findViewById(R.id.inputEditCadUserName);
        inputEmail = findViewById(R.id.inputEditCadUserEmail);
        inputSenha = findViewById(R.id.inputEditCadUserSenha);
        inputSenhaConf = findViewById(R.id.inputEditCadUserSenhaConf);

        loadUserData();

        btnAltUser = findViewById(R.id.btnAltUser);
        btnDeleteUser = findViewById(R.id.btnDeleteUser);

        // ALTERAR DADOS
        btnAltUser.setOnClickListener(v -> {
            String novoNome = inputNome.getText().toString().trim();
            String novoEmail = inputEmail.getText().toString().trim();
            String novaSenha = inputSenha.getText().toString().trim();
            String confSenha = inputSenhaConf.getText().toString().trim();

            if (!novaSenha.equals(confSenha)) {
                Toast.makeText(this, "As senhas não coincidem!", Toast.LENGTH_SHORT).show();
                return;
            }

            new AlertDialog.Builder(this)
                    .setTitle("Confirmação")
                    .setMessage("Deseja realmente alterar os dados?")
                    .setPositiveButton("Sim", (dialog, which) -> atualizarUsuario(novoNome, novoEmail, novaSenha))
                    .setNegativeButton("Não", null)
                    .show();
        });

        // DELETAR CONTA
        btnDeleteUser.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Confirmação")
                    .setMessage("Deseja realmente deletar sua conta?")
                    .setPositiveButton("Sim", (dialog, which) -> deletarUsuario())
                    .setNegativeButton("Não", null)
                    .show();
        });
    }

    private void loadUserData() {
        SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        userId = prefs.getLong(KEY_ID, 0);
        token = prefs.getString(KEY_TOKEN, "");

        inputNome.setText(prefs.getString(KEY_NOME, ""));
        inputEmail.setText(prefs.getString(KEY_EMAIL, ""));

        Log.d("DEBUG_USER", "ID: " + userId + " | Token: " + token);
    }

    private void saveUserDataLocal(JSONObject json) {
        try {
            SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            if (json.has("id")) editor.putLong(KEY_ID, json.getLong("id"));
            if (json.has("nome")) editor.putString(KEY_NOME, json.getString("nome"));
            if (json.has("usuario")) editor.putString(KEY_EMAIL, json.getString("usuario"));
            if (json.has("token")) editor.putString(KEY_TOKEN, json.getString("token"));

            editor.apply();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------------------
    //     PATCH /usuarios/{id}
    // ---------------------------
    private void atualizarUsuario(String nome, String email, String senha) {
        new Thread(() -> {
            try {
                JSONObject dados = new JSONObject();
                dados.put("id", userId); // 🔹 MUITO IMPORTANTE
                if (!nome.isEmpty()) dados.put("nome", nome);
                if (!email.isEmpty()) dados.put("usuario", email);
                if (!senha.isEmpty()) dados.put("senha", senha);

                String jsonBody = dados.toString();

                // Chamada PATCH correta com a versão atual do service
                JSONObject resp = UsuarioService.atualizarParcial(
                        this,   // Context
                        jsonBody
                );

                Log.d("DEBUG_PATCH_RESP", resp.toString());
                saveUserDataLocal(resp);

                runOnUiThread(() -> {
                    Toast.makeText(this, "Dados atualizados com sucesso!", Toast.LENGTH_SHORT).show();
                    loadUserData();
                });

            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() ->
                        Toast.makeText(this, "Erro ao atualizar dados.", Toast.LENGTH_SHORT).show()
                );
            }
        }).start();
    }



    // ---------------------------
    //   DELETE /usuarios/{id}
    // ---------------------------
    private void deletarUsuario() {
        new Thread(() -> {
            try {
                boolean sucesso = UsuarioService.deletar(this, userId);

                runOnUiThread(() -> {
                    if (sucesso) {
                        Toast.makeText(this, "Conta deletada com sucesso!", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(this, "Usuário não encontrado ou erro ao deletar.", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() ->
                        Toast.makeText(this, "Erro ao deletar conta.", Toast.LENGTH_SHORT).show()
                );
            }
        }).start();
    }
}