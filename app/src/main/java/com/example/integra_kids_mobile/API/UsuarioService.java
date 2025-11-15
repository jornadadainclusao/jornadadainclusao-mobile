package com.example.integra_kids_mobile.API;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UsuarioService {

    private static final OkHttpClient client = new OkHttpClient();
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    // ---------------------------------------------------------
    //                  USUARIO CONTROLLER
    // ---------------------------------------------------------

    // 🔹 GET /usuarios — lista todos os usuários
    public static JSONArray getUsuarios() throws Exception {
        String resp = ApiClient.get("/usuarios");
        return new JSONArray(resp);
    }

    // 🔹 GET /usuarios/{id}
    public static JSONObject getUsuarioById(long id) throws Exception {
        String resp = ApiClient.get("/usuarios/" + id);
        return new JSONObject(resp);
    }

    // 🔹 POST /usuarios/cadastrar
    public static JSONObject cadastrarUsuario(String nome, String email, String senha) throws Exception {

        JSONObject body = new JSONObject();
        body.put("nome", nome);
        body.put("email", email);
        body.put("senha", senha);

        return post("/usuarios/cadastrar", body);
    }

    // 🔹 POST /usuarios/logar — login do usuário
    public static JSONObject logar(String email, String senha) throws Exception {

        JSONObject body = new JSONObject();
        body.put("email", email);
        body.put("senha", senha);

        return post("/usuarios/logar", body);
    }

    // 🔹 PUT /usuarios/atualizar
    public static JSONObject atualizarUsuario(long id, String nome, String email, String senha) throws Exception {

        JSONObject body = new JSONObject();
        body.put("id", id);
        body.put("nome", nome);
        body.put("email", email);
        body.put("senha", senha);

        return put("/usuarios/atualizar", body);
    }

    // 🔹 DELETE /usuarios/{id}
    public static boolean deletarUsuario(long id) throws Exception {
        return delete("/usuarios/" + id);
    }


    // ---------------------------------------------------------
    //              PRIVATE HTTP HELPERS
    // ---------------------------------------------------------

    private static JSONObject post(String endpoint, JSONObject bodyJson) throws Exception {
        String url = Api.BASE_URL + endpoint;

        RequestBody body = RequestBody.create(bodyJson.toString(), JSON);
        Request request = new Request.Builder().url(url).post(body).build();

        try (Response response = client.newCall(request).execute()) {
            return new JSONObject(response.body().string());
        }
    }

    private static JSONObject put(String endpoint, JSONObject bodyJson) throws Exception {
        String url = Api.BASE_URL + endpoint;

        RequestBody body = RequestBody.create(bodyJson.toString(), JSON);
        Request request = new Request.Builder().url(url).put(body).build();

        try (Response response = client.newCall(request).execute()) {
            return new JSONObject(response.body().string());
        }
    }

    private static boolean delete(String endpoint) throws Exception {
        String url = Api.BASE_URL + endpoint;

        Request request = new Request.Builder().url(url).delete().build();

        try (Response response = client.newCall(request).execute()) {
            return response.isSuccessful();
        }
    }
}
