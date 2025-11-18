package com.example.integra_kids_mobile.games.components.jogo_numeros;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Size;

import com.example.integra_kids_mobile.games.components.KeyViewStateEnum;

public class NumeroView extends androidx.appcompat.widget.AppCompatTextView {
    private long id;
    protected String content;
    protected int width = 150, height = 150;
    @Size(2)
    protected String[] colors;
    protected int backgroundColor = Color.RED; // Cor padrão bem óbvia caso algo inesperado ocorra
    protected int borderSize = 8;
    protected int borderColor = Color.BLACK;
    protected boolean isPlaced = false;
    private GradientDrawable drawable = new GradientDrawable();
    private int currentColor = KeyViewStateEnum.NORMAL;

    public NumeroView(Context context) {
        super(context);
    }

    protected void init() {
        this.setTextColor(Color.WHITE);
        this.setTextSize(18);
        this.setGravity(Gravity.CENTER);
        this.setText(content);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setColor(Color.parseColor("#EB4A4A"));
        this.setBackground(drawable);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(150, 150);
        params.gravity = Gravity.TOP | Gravity.END;
        params.setMargins(0, 65, 65, 0);
        this.setLayoutParams(params);
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
        requestLayout();
        invalidate();
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        int startX = 0, startY = 0;

        init();

        drawable.draw(canvas);
    }

    // Não sobrescreva o método base
    public int getKeyWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    // Não sobrescreva o método base
    public int getKeyHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Size(2)
    public String[] getColors() {
        return colors;
    }

    public void setColors(@Size(2) String[] colors) {
        this.colors = colors;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        requestLayout();
        invalidate();
    }

    public void setBackgroundColorWithAnimation(int backgroundColor) {
        ObjectAnimator animation = ObjectAnimator.ofArgb(this, "backgroundColor", backgroundColor);
        animation.setDuration(150);
        animation.start();
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

    public int getBorderSize() {
        return borderSize;
    }

    public void setBorderSize(int borderSize) {
        this.borderSize = borderSize;
        this.drawable.setStroke(this.borderColor, this.borderSize);
        requestLayout();
        invalidate();
    }

    public int getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
        this.drawable.setStroke(this.borderColor, this.borderSize);
        requestLayout();
        invalidate();
    }


    public boolean isPlaced() {
        return isPlaced;
    }

    public void setPlaced(boolean placed) {
        isPlaced = placed;
    }

    public long _getId() {
        return id;
    }

    public void _setId(long id) {
        this.id = id;
    }
}
