package com.example.integra_kids_mobile.API;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import okhttp3.Response;

public class UsuarioService {

    private static final String BASE_URL = "https://backend-9qjw.onrender.com";

    // -----------------------
    // LOGIN → envia usuario + senha
    // -----------------------
    public static JSONObject logar(String usuario, String senha) throws Exception {
        String url = BASE_URL + "/usuarios/logar";

        JSONObject json = new JSONObject();
        json.put("usuario", usuario); // ⚠️ o backend espera "usuario"
        json.put("senha", senha);

        // 🔹 LOG do JSON que será enviado
        android.util.Log.d("LOGIN_DEBUG", "Enviando login: " + json.toString());

        Response resp = ApiClient.postNoAuth(url, json.toString());
        String respBody = resp.body().string();

        // 🔹 LOG da resposta recebida
        android.util.Log.d("LOGIN_DEBUG", "Resposta do backend: " + respBody);

        return new JSONObject(respBody);
    }


    // -----------------------
    // CADASTRO
    // -----------------------
    public static JSONObject cadastrar(Context context, String nome, String usuario, String senha) throws Exception {
        String url = BASE_URL + "/usuarios/cadastrar";

        JSONObject json = new JSONObject();
        json.put("nome", nome);
        json.put("usuario", usuario);
        json.put("senha", senha);

        Response resp = ApiClient.postNoAuth(url, json.toString());

        String bodyString = resp.body() != null ? resp.body().string() : "";

        Log.d("DEBUG_CAD_RESP", "Código: " + resp.code());
        Log.d("DEBUG_CAD_RESP", "Body: " + bodyString);

        if (resp.isSuccessful()) {
            return new JSONObject(bodyString);
        } else {
            throw new Exception("Erro ao cadastrar: código " + resp.code() + " | Body: " + bodyString);
        }

    }

    // -----------------------
    // PATCH /usuarios/{id}
    // -----------------------
// PATCH /usuarios/atualizar-parcial
    public static JSONObject atualizarParcial(Context context, String jsonBody) throws Exception {
        String endpoint = "/usuarios/atualizar-parcial"; // sem ID na URL
        Response resp = ApiClient.patch(context, endpoint, jsonBody);
        return new JSONObject(resp.body().string());
    }


    // -----------------------
    // DELETE /usuarios/{id}
    // -----------------------
    public static boolean deletar(Context context, long id) throws Exception {
        String url = BASE_URL + "/usuarios/" + id;
        String token = ApiClient.getToken(context);

        Log.d("DEBUG_DELETE_REQ", "URL: " + url);
        Log.d("DEBUG_DELETE_REQ", "Token: " + token);

        Response resp = ApiClient.delete(context, url);

        // Log da resposta
        Log.d("DEBUG_DELETE_RESP", "Código: " + resp.code());
        if (resp.body() != null) {
            Log.d("DEBUG_DELETE_RESP", "Body: " + resp.body().string());
        } else {
            Log.d("DEBUG_DELETE_RESP", "Body: null (sem conteúdo)");
        }

        // Aceita qualquer 2xx como sucesso
        return resp.code() >= 200 && resp.code() < 300;
    }



}
