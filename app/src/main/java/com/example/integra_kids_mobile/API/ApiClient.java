package com.example.integra_kids_mobile.API;

import android.content.Context;
import android.content.SharedPreferences;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApiClient {

    private static final OkHttpClient client = new OkHttpClient();
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private static final String BASE_URL = Api.BASE_URL;

    // =======================================================
    // MONTA URL COMPLETA
    // =======================================================
    private static String fullUrl(String endpoint) {
        if (endpoint.startsWith("http")) return endpoint;
        return BASE_URL + endpoint;
    }

    // =======================================================
    // PEGAR TOKEN CERTO
    // =======================================================
    public static String getToken(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE);
        return prefs.getString("user_token", null); // 🔥 Corrigido
    }

    // =======================================================
    // GET
    // =======================================================
    public static Response get(Context context, String endpoint) throws Exception {
        Request request = new Request.Builder()
                .url(fullUrl(endpoint))
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + getToken(context))
                .build();

        return client.newCall(request).execute();
    }

    // =======================================================
    // POST
    // =======================================================
    public static Response post(Context context, String endpoint, String jsonBody) throws Exception {
        RequestBody body = RequestBody.create(jsonBody, JSON);

        Request request = new Request.Builder()
                .url(fullUrl(endpoint))
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + getToken(context))
                .build();

        return client.newCall(request).execute();
    }

    public static Response postNoAuth(String url, String jsonBody) throws Exception {
        RequestBody body = RequestBody.create(jsonBody, JSON);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build(); // sem Authorization

        return client.newCall(request).execute();
    }


    // =======================================================
    // PUT
    // =======================================================
    public static Response put(Context context, String endpoint, String jsonBody) throws Exception {
        RequestBody body = RequestBody.create(jsonBody, JSON);

        Request request = new Request.Builder()
                .url(fullUrl(endpoint))
                .put(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + getToken(context))
                .build();

        return client.newCall(request).execute();
    }

    // =======================================================
    // PATCH
    // =======================================================
    public static Response patch(Context context, String endpoint, String jsonBody) throws Exception {
        RequestBody body = RequestBody.create(jsonBody, JSON);

        Request request = new Request.Builder()
                .url(fullUrl(endpoint))
                .patch(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", getToken(context))
                .build();

        return client.newCall(request).execute();
    }

    // =======================================================
    // DELETE
    // =======================================================
    public static Response delete(Context context, String endpoint) throws Exception {
        Request request = new Request.Builder()
                .url(fullUrl(endpoint))
                .delete()
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + getToken(context))
                .build();

        return client.newCall(request).execute();
    }
}
