package com.example.integra_kids_mobile.common;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.integra_kids_mobile.R;

public class ReturnButton {
    public static void configurar(Activity activity) {
        Button btnReturn = activity.findViewById(R.id.btnReturn);

        if (btnReturn != null) {
            btnReturn.setOnClickListener(v -> {
                Log.d("ReturnButton", "Botão de retorno clicado!");
                activity.onBackPressed();
            });
        }
    }

    public static void configurar(View rootView) {
        Button btnReturn = rootView.findViewById(R.id.btnReturn);

        if (btnReturn != null) {
            btnReturn.setOnClickListener(v -> {
                if (rootView.getContext() instanceof Activity) {
                    ((Activity) rootView.getContext()).onBackPressed();
                }
            });
        }
    }
}
