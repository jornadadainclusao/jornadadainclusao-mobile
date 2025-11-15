package com.example.integra_kids_mobile.API;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DependenteService {

    private static final OkHttpClient client = new OkHttpClient();
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    // ---------------------------------------------------------
    //              MÉTODOS SIMPLIFICADOS
    // ---------------------------------------------------------

    // 🔹 1. Buscar todos os dependentes
    public static JSONArray getTodos() throws Exception {
        return new JSONArray(ApiClient.get("/dependente"));
    }

    // 🔹 2. Buscar por ID
    public static JSONObject getById(long id) throws Exception {
        return new JSONObject(ApiClient.get("/dependente/" + id));
    }

    // 🔹 3. Histórico de jogos
    public static JSONArray getInfoJogos(long id) throws Exception {
        return new JSONArray(ApiClient.get("/dependente/infoJogosByDependente/" + id));
    }

    // 🔹 4. Buscar dependentes por usuário
    public static JSONArray getDependentesDoUsuario(long usuarioId) throws Exception {
        return new JSONArray(ApiClient.get("/dependente/getDependenteByIdUsuario/" + usuarioId));
    }

    // 🔹 5. Cadastrar dependente — só valores
    public static JSONObject cadastrar(String nome, int idade, String sexo, String avatar, long usuarioId) throws Exception {

        JSONObject dep = new JSONObject();
        dep.put("nome", nome);
        dep.put("idade", idade);
        dep.put("sexo", sexo);
        dep.put("avatar", avatar);

        JSONObject usuario = new JSONObject();
        usuario.put("id", usuarioId);
        dep.put("usuario_id_fk", usuario);

        return post("/dependente/cadastrar", dep);
    }

    // 🔹 6. Atualizar dependente — só valores
    public static JSONObject atualizar(long id, String nome, int idade, String sexo, String avatar, long usuarioId) throws Exception {

        JSONObject dep = new JSONObject();
        dep.put("id", id);
        dep.put("nome", nome);
        dep.put("idade", idade);
        dep.put("sexo", sexo);
        dep.put("avatar", avatar);

        JSONObject usuario = new JSONObject();
        usuario.put("id", usuarioId);
        dep.put("usuario_id_fk", usuario);

        return put("/dependente/atualizar", dep);
    }

    // 🔹 7. Deletar dependente — só valor
    public static boolean deletar(long id) throws Exception {
        return delete("/dependente/" + id);
    }


    // ---------------------------------------------------------
    //              MÉTODOS PRIVADOS DE SUPORTE
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
