package com.example.integra_kids_mobile.API;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.Response;

public class GameService {

    private static final String BASE_JOGO = "/jogo";
    private static final String BASE_INFO = "/infoJogos";


    // ==========================================================
    //                     JOGO CONTROLLER
    // ==========================================================

    // 🔹 GET /jogo
    public static JSONArray getJogos(Context context) throws Exception {
        Response response = ApiClient.get(context, BASE_JOGO);
        String resp = response.body().string();
        return new JSONArray(resp);
    }

    // 🔹 GET /jogo/{id}
    public static JSONObject getJogoById(Context context, long id) throws Exception {
        Response response = ApiClient.get(context, BASE_JOGO + "/" + id);
        String resp = response.body().string();
        return new JSONObject(resp);
    }


    // ==========================================================
    //                 INFO JOGOS CONTROLLER
    // ==========================================================

    // 🔹 GET /infoJogos
    public static JSONArray getInfos(Context context) throws Exception {
        Response response = ApiClient.get(context, BASE_INFO);
        String resp = response.body().string();
        return new JSONArray(resp);
    }

    // 🔹 GET /infoJogos/{id}
    public static JSONObject getInfoById(Context context, long id) throws Exception {
        Response response = ApiClient.get(context, BASE_INFO + "/" + id);
        String resp = response.body().string();
        return new JSONObject(resp);
    }

    // 🔹 GET /infoJogos/dependente/{id}
    public static JSONArray getInfosByDependente(Context context, long depId) throws Exception {
        Response response = ApiClient.get(context, BASE_INFO + "/dependente/" + depId);
        String resp = response.body().string();
        return new JSONArray(resp);
    }


    // ==========================================================
    //                        POST
    // ==========================================================

    // 🔹 POST /infoJogos/cadastrar
    public static JSONObject cadastrarInfo(
            Context context,
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

        Response response = ApiClient.post(context, BASE_INFO + "/cadastrar", body.toString());
        return new JSONObject(response.body().string());
    }


    // ==========================================================
    //                        PUT
    // ==========================================================

    // 🔹 PUT /infoJogos/atualizar
    public static JSONObject atualizarInfo(
            Context context,
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

        Response response = ApiClient.put(context, BASE_INFO + "/atualizar", body.toString());
        return new JSONObject(response.body().string());
    }


    // ==========================================================
    //                       DELETE
    // ==========================================================

    // 🔹 DELETE /infoJogos/{id}
    public static boolean deletarInfo(Context context, long id) throws Exception {
        Response response = ApiClient.delete(context, BASE_INFO + "/" + id);
        return response.isSuccessful();
    }
}
