package com.example.integra_kids_mobile.API;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.Response;

public class DependenteService {

    private static final String BASE = "/dependente";

    // ==========================================================
    //                         GET
    // ==========================================================

    // 🔹 Buscar todos os dependentes
    public static JSONArray getTodos(Context context) throws Exception {
        Response response = ApiClient.get(context, BASE);
        String resp = response.body().string();
        return new JSONArray(resp);
    }

    // 🔹 Buscar dependente por ID
    public static JSONObject getById(Context context, long id) throws Exception {
        Response response = ApiClient.get(context, BASE + "/" + id);
        String resp = response.body().string();
        return new JSONObject(resp);
    }

    // 🔹 Histórico de jogos do dependente
    public static JSONArray getInfoJogos(Context context, long id) throws Exception {
        Response response = ApiClient.get(context, BASE + "/infoJogosByDependente/" + id);
        String resp = response.body().string();
        return new JSONArray(resp);
    }

    // 🔹 Buscar dependentes de um usuário
    public static JSONArray getDependentesDoUsuario(Context context, long usuarioId) throws Exception {
        Response response = ApiClient.get(context, BASE + "/getDependenteByIdUsuario/" + usuarioId);
        String resp = response.body().string();
        return new JSONArray(resp);
    }


    // ==========================================================
    //                         POST
    // ==========================================================

    // 🔹 Cadastrar dependente
    public static JSONObject cadastrar(Context context, String nome, int idade, String sexo, String avatar, long usuarioId) throws Exception {

        JSONObject dep = new JSONObject();
        dep.put("nome", nome);
        dep.put("idade", idade);
        dep.put("sexo", sexo);
        dep.put("foto", avatar);

        JSONObject usuario = new JSONObject();
        usuario.put("id", usuarioId);
        dep.put("usuario_id_fk", usuario);

        Log.d("API_DEBUG", "Enviando JSON: " + dep.toString());

        Response response = ApiClient.post(context, BASE, dep.toString());
        String resp = response.body().string();
        return new JSONObject(resp);
    }


    // ==========================================================
    //                         PUT
    // ==========================================================

    // 🔹 Atualização completa
    public static JSONObject atualizar(Context context, long id, String nome, int idade, String sexo, String avatar, long usuarioId) throws Exception {

        JSONObject dep = new JSONObject();
        dep.put("id", id);
        dep.put("nome", nome);
        dep.put("idade", idade);
        dep.put("sexo", sexo);
        dep.put("foto", avatar);

        JSONObject usuario = new JSONObject();
        usuario.put("id", usuarioId);
        dep.put("usuario_id_fk", usuario);

        Response response = ApiClient.put(context, BASE, dep.toString());
        String resp = response.body().string();
        return new JSONObject(resp);
    }


    // ==========================================================
    //                         PATCH
    // ==========================================================

    // 🔹 Atualização parcial
    public static JSONObject atualizarParcial(Context context, long id, JSONObject dto) throws Exception {

        Response response = ApiClient.patch(context, BASE + "/" + id, dto.toString());
        String resp = response.body().string();
        return new JSONObject(resp);
    }


    // ==========================================================
    //                         DELETE
    // ==========================================================

    // 🔹 Deletar dependente
    public static boolean deletar(Context context, long id) throws Exception {
        Response response = ApiClient.delete(context, BASE + "/" + id);
        return response.isSuccessful();
    }
}
