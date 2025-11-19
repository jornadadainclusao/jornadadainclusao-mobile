package com.example.integra_kids_mobile.games.views.jogo_memoria;

import android.content.Context;
import androidx.appcompat.widget.AppCompatImageView;
import com.example.integra_kids_mobile.R;

public class KeyView extends AppCompatImageView {

    private boolean revealed = false;
    private int drawableRes;
    private static final int BACK_RES = R.drawable.rosa;

    public KeyView(Context context) {
        super(context);
        hide();
    }

    public void setDrawable(int drawableRes) {
        this.drawableRes = drawableRes;
    }

    public void reveal() {
        if (!revealed && drawableRes != 0) {
            setImageResource(drawableRes);
            revealed = true;
        }
    }

    public void hide() {
        setImageResource(BACK_RES);
        revealed = false;
    }

    public boolean isRevealed() {
        return revealed;
    }
}
