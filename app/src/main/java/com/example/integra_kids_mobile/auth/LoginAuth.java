package com.example.integra_kids_mobile.auth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.integra_kids_mobile.LoginCadastro;
import com.example.integra_kids_mobile.MenuPrincipal;
import com.example.integra_kids_mobile.API.UsuarioService;

import org.json.JSONObject;

public class LoginAuth {

    private static final String PREF_NAME = "AuthPrefs";
    private static final String KEY_ID = "user_id";
    private static final String KEY_NOME = "user_nome";
    private static final String KEY_EMAIL = "user_email";
    private static final String KEY_TOKEN = "user_token";

    // ---------------------------------------------------------
    //                     LOGIN
    // ---------------------------------------------------------
    public static boolean login(Context context, String email, String senha) {
        try {
            JSONObject resp = UsuarioService.logar(email, senha);

            if (resp.has("token")) {
                saveUserData(context, resp);
                return true;
            }

            return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ---------------------------------------------------------
    //           SALVAR DADOS DO USUÁRIO LOGADO
    // ---------------------------------------------------------
    private static void saveUserData(Context context, JSONObject json) throws Exception {

        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putLong(KEY_ID, json.getLong("id"));
        editor.putString(KEY_NOME, json.getString("nome"));
        editor.putString(KEY_EMAIL, json.getString("email"));
        editor.putString(KEY_TOKEN, json.getString("token"));

        editor.apply();
    }

    // ---------------------------------------------------------
    //                LOGOUT – LIMPA MEMÓRIA
    // ---------------------------------------------------------
    public static void logout(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        prefs.edit().clear().apply();

        Intent i = new Intent(context, LoginCadastro.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(i);
    }

    // ---------------------------------------------------------
    //                 VERIFICAR SE ESTÁ LOGADO
    // ---------------------------------------------------------
    public static boolean isLogged(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return prefs.contains(KEY_TOKEN);
    }

    // ---------------------------------------------------------
    //         REDIRECIONAR PARA MENU SE JÁ ESTIVER LOGADO
    // ---------------------------------------------------------
    public static void checkLoginRedirect(Context context) {
        if (isLogged(context)) {
            Intent i = new Intent(context, MenuPrincipal.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(i);
        }
    }

    // ---------------------------------------------------------
    //                  MÉTODOS DE ACESSO RÁPIDO
    // ---------------------------------------------------------
    public static long getUserId(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).getLong(KEY_ID, -1);
    }

    public static String getUserNome(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).getString(KEY_NOME, null);
    }

    public static String getUserEmail(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).getString(KEY_EMAIL, null);
    }

    public static String getToken(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).getString(KEY_TOKEN, null);
    }
}
