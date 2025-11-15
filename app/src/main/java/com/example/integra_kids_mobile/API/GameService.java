package com.example.integra_kids_mobile.API;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GameService {

    private static final OkHttpClient client = new OkHttpClient();
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    // ---------------------------------------------------------
    //                  JOGO CONTROLLER
    // ---------------------------------------------------------

    // 🔹 GET /jogo — lista todos os jogos
    public static JSONArray getJogos() throws Exception {
        String resp = ApiClient.get("/jogo");
        return new JSONArray(resp);
    }

    // 🔹 GET /jogo/{id}
    public static JSONObject getJogoById(long id) throws Exception {
        String resp = ApiClient.get("/jogo/" + id);
        return new JSONObject(resp);
    }

    // ---------------------------------------------------------
    //              INFO JOGOS CONTROLLER
    // ---------------------------------------------------------

    // 🔹 GET /infoJogos — lista geral de partidas
    public static JSONArray getInfos() throws Exception {
        String resp = ApiClient.get("/infoJogos");
        return new JSONArray(resp);
    }

    // 🔹 GET /infoJogos/{id}
    public static JSONObject getInfoById(long id) throws Exception {
        String resp = ApiClient.get("/infoJogos/" + id);
        return new JSONObject(resp);
    }

    // 🔹 GET /infoJogos/dependente/{id}
    public static JSONArray getInfosByDependente(long depId) throws Exception {
        String resp = ApiClient.get("/infoJogos/dependente/" + depId);
        return new JSONArray(resp);
    }

    // 🔹 POST /infoJogos/cadastrar (somente valores)
    public static JSONObject cadastrarInfo(
            long dependenteId,
            long jogoId,
            int acertos,
            int erros,
            int tempo
    ) throws Exception {

        JSONObject body = new JSONObject();

        JSONObject dep = new JSONObject();
        dep.put("id", dependenteId);

        JSONObject jogo = new JSONObject();
        jogo.put("id", jogoId);

        body.put("dependente", dep);
        body.put("jogo", jogo);
        body.put("acertos", acertos);
        body.put("erros", erros);
        body.put("tempo", tempo);

        return post("/infoJogos/cadastrar", body);
    }

    // 🔹 PUT /infoJogos/atualizar
    public static JSONObject atualizarInfo(
            long id,
            long dependenteId,
            long jogoId,
            int acertos,
            int erros,
            int tempo
    ) throws Exception {

        JSONObject body = new JSONObject();

        body.put("id", id);

        JSONObject dep = new JSONObject();
        dep.put("id", dependenteId);

        JSONObject jogo = new JSONObject();
        jogo.put("id", jogoId);

        body.put("dependente", dep);
        body.put("jogo", jogo);
        body.put("acertos", acertos);
        body.put("erros", erros);
        body.put("tempo", tempo);

        return put("/infoJogos/atualizar", body);
    }

    // 🔹 DELETE /infoJogos/{id}
    public static boolean deletarInfo(long id) throws Exception {
        return delete("/infoJogos/" + id);
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
