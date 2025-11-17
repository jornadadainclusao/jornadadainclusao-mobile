package com.example.integra_kids_mobile.profile;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.integra_kids_mobile.API.DependenteService;
import com.example.integra_kids_mobile.R;
import com.example.integra_kids_mobile.auth.LoginAuth;
import com.example.integra_kids_mobile.common.ReturnButton;
import com.example.integra_kids_mobile.utils.AvatarMapper;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

import java.util.Calendar;

public class PerfilCadKid extends AppCompatActivity {

    private int selectedAvatarDrawable = -1; // drawable do avatar selecionado

    // Array com TODOS os drawables dos avatars (ordem dos botões do XML)
    private final int[] avatarDrawables = {
            R.drawable.player_icon1,
            R.drawable.player_icon2,
            R.drawable.player_icon3,
            R.drawable.player_icon4,
            R.drawable.player_icon5,
            R.drawable.player_icon6,
            R.drawable.player_icon7,
            R.drawable.player_icon8,
            R.drawable.player_icon9,
            R.drawable.player_icon10,
            R.drawable.player_icon11,
            R.drawable.player_icon12
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_cad_kid);
        ReturnButton.configurar(this);

        TextInputEditText inpNome = findViewById(R.id.cadKidInputNome);
        TextInputEditText inpData = findViewById(R.id.cadKidInputNascimento);

        // ======================
        //        DATE PICKER
        // ======================
        inpData.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePicker = new DatePickerDialog(
                    PerfilCadKid.this,
                    (view1, y, m, d) -> inpData.setText(String.format("%02d/%02d/%04d", d, m + 1, y)),
                    year, month, day
            );

            // faixa permitida 3–10 anos
            Calendar maxDate = Calendar.getInstance();
            maxDate.add(Calendar.YEAR, -3);

            Calendar minDate = Calendar.getInstance();
            minDate.add(Calendar.YEAR, -10);

            datePicker.getDatePicker().setMinDate(minDate.getTimeInMillis());
            datePicker.getDatePicker().setMaxDate(maxDate.getTimeInMillis());
            datePicker.show();
        });

        // ======================
        //      GRID AVATARES
        // ======================
        GridLayout gridAvatares = findViewById(R.id.gridAvatares);

        for (int i = 0; i < gridAvatares.getChildCount(); i++) {

            View child = gridAvatares.getChildAt(i);

            if (child instanceof ImageButton) {

                ImageButton avatarButton = (ImageButton) child;

                // Salva dinamicamente o drawable correspondente baseado na posição
                avatarButton.setTag(avatarDrawables[i]);

                int index = i;

                avatarButton.setOnClickListener(v -> {

                    // limpa seleção antiga
                    for (int j = 0; j < gridAvatares.getChildCount(); j++) {
                        View other = gridAvatares.getChildAt(j);
                        if (other instanceof ImageButton) {
                            other.setBackground(null);
                        }
                    }

                    // marca selecionado
                    avatarButton.setBackgroundResource(R.drawable.avatar_border);

                    // obtém o drawable
                    selectedAvatarDrawable = avatarDrawables[index];
                });
            }
        }

        // ======================
        //      BOTÃO CADASTRAR
        // ======================
        findViewById(R.id.cadKidBtnCadastrar).setOnClickListener(v -> {

            String nome = inpNome.getText().toString().trim();
            String data = inpData.getText().toString().trim();

            if (nome.isEmpty() || data.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (selectedAvatarDrawable == -1) {
                Toast.makeText(this, "Selecione um avatar!", Toast.LENGTH_SHORT).show();
                return;
            }

            int idade = calcularIdade(data);

            // ==========================
            //       PEGAR SEXO
            // ==========================
            String sexo = "N"; // padrão
            RadioGroup grupoSexo = findViewById(R.id.cadKidRadioGroupGenero);
            int selectedId = grupoSexo.getCheckedRadioButtonId();

            if (selectedId == R.id.cadKidInputGeneroMasc) {
                sexo = "M";
            } else if (selectedId == R.id.cadKidInputGeneroFem) {
                sexo = "F";
            }

            // ==========================
            // AVATAR → URL
            // ==========================
            String avatarUrl = AvatarMapper.getAvatarUrlFromResource(selectedAvatarDrawable);

            // ID do usuário logado
            int usuarioId = LoginAuth.getUserId(this);

            // Enviar para o backend
            criarDependente(nome, idade, sexo, avatarUrl, usuarioId);

        });
    }

    // ======================
    //     CALCULAR IDADE
    // ======================
    private int calcularIdade(String dataNascimento) {
        try {
            String[] parts = dataNascimento.split("/");
            int dia = Integer.parseInt(parts[0]);
            int mes = Integer.parseInt(parts[1]);
            int ano = Integer.parseInt(parts[2]);

            Calendar hoje = Calendar.getInstance();
            Calendar nasc = Calendar.getInstance();
            nasc.set(ano, mes - 1, dia);

            int idade = hoje.get(Calendar.YEAR) - nasc.get(Calendar.YEAR);

            if (hoje.get(Calendar.MONTH) < nasc.get(Calendar.MONTH) ||
                    (hoje.get(Calendar.MONTH) == nasc.get(Calendar.MONTH) &&
                            hoje.get(Calendar.DAY_OF_MONTH) < nasc.get(Calendar.DAY_OF_MONTH))) {
                idade--;
            }

            return idade;
        } catch (Exception e) {
            return 0;
        }
    }

    // ======================
    //   CHAMAR cadastrar(...)
    // ======================
    private void criarDependente(String nome, int idade, String sexo, String avatarUrl, int usuarioId) {

        new Thread(() -> {
            try {
                JSONObject resp = DependenteService.cadastrar(
                        this, nome, idade, sexo, avatarUrl, usuarioId
                );

                runOnUiThread(() -> {
                    Toast.makeText(this, "Dependente criado!", Toast.LENGTH_SHORT).show();
                    finish();
                });

            } catch (Exception e) {
                runOnUiThread(() ->
                        Toast.makeText(this, "Erro ao cadastrar: " + e.getMessage(), Toast.LENGTH_LONG).show()
                );
            }
        }).start();
    }
}
