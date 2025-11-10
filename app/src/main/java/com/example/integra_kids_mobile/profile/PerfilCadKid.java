package com.example.integra_kids_mobile.profile;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.integra_kids_mobile.R;
import com.example.integra_kids_mobile.common.ReturnButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;

public class PerfilCadKid extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.perfil_cad_kid);

        ReturnButton.configurar(this);

        TextInputEditText editTextDate = findViewById(R.id.editTextDate);

        editTextDate.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();

            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePicker = new DatePickerDialog(
                    PerfilCadKid.this, // 👈 referência da Activity
                    (view1, yearSelected, monthSelected, daySelected) -> {
                        String date = String.format("%02d/%02d/%04d", daySelected, monthSelected + 1, yearSelected);
                        editTextDate.setText(date);
                    },
                    year, month, day
            );

            // Limita entre 3 e 10 anos atrás
            Calendar maxDate = Calendar.getInstance();
            maxDate.add(Calendar.YEAR, -3);

            Calendar minDate = Calendar.getInstance();
            minDate.add(Calendar.YEAR, -10);

            datePicker.getDatePicker().setMinDate(minDate.getTimeInMillis());
            datePicker.getDatePicker().setMaxDate(maxDate.getTimeInMillis());

            datePicker.show();
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