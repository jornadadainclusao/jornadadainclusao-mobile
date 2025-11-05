package com.example.integra_kids_mobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.integra_kids_mobile.ui.views.LoginScreen;
import com.example.integra_kids_mobile.ui.views.MainScreen;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // TODO: Turn the string into a res
        SharedPreferences sharedPreferences = this.getSharedPreferences("preferences", Context.MODE_PRIVATE);
        final long sevenDaysInMillis = 1000 * 60 * 60 * 24 * 7;
        final long rightNow = Calendar.getInstance().getTimeInMillis();
        final long previousLogin = sharedPreferences.getLong("previousLogin", rightNow);

        // Sete dias após o último login realizado pelo usuário
        Intent intent;
        if (sevenDaysInMillis + previousLogin < rightNow) {
            intent = new Intent(this, LoginScreen.class);
        } else {
            intent = new Intent(this, MainScreen.class);
        }
        startActivity(intent);
        finish();
    }
}
