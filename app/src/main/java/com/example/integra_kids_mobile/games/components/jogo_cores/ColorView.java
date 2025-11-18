package com.example.integra_kids_mobile.games.components.jogo_cores;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Size;

import com.example.integra_kids_mobile.games.components.KeyView;
import com.example.integra_kids_mobile.games.components.KeyViewStateEnum;

public class ColorView extends KeyView {
    @Size(2)
    private String[] colors;

    @Size(2)
    private int currentColor = KeyViewStateEnum.NORMAL;

    public ColorView(Context context) {
        super(context);
    }

    @Size(2)
    public String[] getColors() {
        return colors;
    }

    public void setColors(@Size(2) String[] colors) {
        this.colors = colors;
    }

    public void setNormalColor() {
        this.currentColor = KeyViewStateEnum.NORMAL;
        this.setBackgroundColorWithAnimation(Color.parseColor(this.colors[this.currentColor]));
    }

    public void setFocusedColor() {
        this.currentColor = KeyViewStateEnum.FOCUSED;
        this.setBackgroundColorWithAnimation(Color.parseColor(this.colors[this.currentColor]));
    }

    public void toggleColor() {
        this.currentColor = (this.currentColor + 1) % 2;
        this.setBackgroundColorWithAnimation(Color.parseColor(this.colors[this.currentColor]));
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        int startX = 0, startY = 0;

        this.drawable.setShape(GradientDrawable.OVAL);
        this.drawable.setBounds(startX, startY, startX + this.width,  startY + this.height);

        this.drawable.setColor(this.backgroundColor);
        this.drawable.setStroke(this.borderSize, this.borderColor);

        drawable.draw(canvas);
    }
}
