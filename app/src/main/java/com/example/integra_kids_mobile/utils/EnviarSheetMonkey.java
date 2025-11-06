package com.example.integra_kids_mobile.utils;

import android.content.Context;

import org.json.JSONObject;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class EnviarSheetMonkey {

    public interface Callback {
        void onSuccess();
        void onError();
    }

    public static void enviarDados(Context context, String nome, String email, String mensagem, Callback callback) {
        new Thread(() -> {
            try {
                URL url = new URL("https://api.sheetmonkey.io/form/nRyPAqMh8R3SKUjhnNGymn");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                conn.setDoOutput(true);

                JSONObject json = new JSONObject();
                json.put("Nome", nome);
                json.put("Email", email);
                json.put("Mensagem", mensagem);

                OutputStream os = conn.getOutputStream();
                os.write(json.toString().getBytes("UTF-8"));
                os.close();

                int responseCode = conn.getResponseCode();
                conn.disconnect();

                if (responseCode == 200) {
                    callback.onSuccess();
                } else {
                    callback.onError();
                }

            } catch (Exception e) {
                e.printStackTrace();
                callback.onError();
            }
        }).start();
    }
}
