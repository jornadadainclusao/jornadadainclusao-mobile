package com.example.integra_kids_mobile.API;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class EmailSenhaService {

    private static final OkHttpClient client = new OkHttpClient();
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    // ---------------------------------------------------------
    //                  SENHA CONTROLLER
    // ---------------------------------------------------------

    // 🔹 POST /senha/recuperar/{email}
    public static boolean recuperarSenha(String email) throws Exception {
        String endpoint = "/senha/recuperar/" + email;

        Request request = new Request.Builder()
                .url(Api.BASE_URL + endpoint)
                .post(RequestBody.create("", JSON))
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.isSuccessful();
        }
    }

    // 🔹 PUT /senha/alterar
    public static boolean alterarSenha(String email, String novaSenha) throws Exception {

        JSONObject body = new JSONObject();
        body.put("email", email);
        body.put("senha", novaSenha);

        return put("/senha/alterar", body).isSuccessful();
    }


    // ---------------------------------------------------------
    //                  EMAIL CONTROLLER
    // ---------------------------------------------------------

    // 🔹 POST /email/enviar
    public static boolean enviarEmail(String email, String assunto, String mensagem) throws Exception {

        JSONObject body = new JSONObject();
        body.put("email", email);
        body.put("assunto", assunto);
        body.put("mensagem", mensagem);

        RequestBody reqBody = RequestBody.create(body.toString(), JSON);

        Request request = new Request.Builder()
                .url(Api.BASE_URL + "/email/enviar")
                .post(reqBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.isSuccessful();
        }
    }


    // ---------------------------------------------------------
    //              MÉTODOS PRIVADOS (PUT)
    // ---------------------------------------------------------

    private static Response put(String endpoint, JSONObject bodyJson) throws Exception {
        String url = Api.BASE_URL + endpoint;

        RequestBody body = RequestBody.create(bodyJson.toString(), JSON);
        Request request = new Request.Builder().url(url).put(body).build();

        return client.newCall(request).execute();
    }

}
