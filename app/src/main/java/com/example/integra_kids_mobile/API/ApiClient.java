package com.example.integra_kids_mobile.API;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiClient {
    private static final OkHttpClient client = new OkHttpClient();

    public static String get(String endpoint) throws Exception {
        String url = Api.BASE_URL + endpoint;
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
