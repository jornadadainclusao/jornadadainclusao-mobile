package com.example.integra_kids_mobile.games.components;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;

// Extende-se a View apenas para colocar a figura no Layout. O que importa pra sua forma mesmo é o
// GradientDrawable
public class KeyView extends View {
    protected GradientDrawable drawable = new GradientDrawable();
    protected char content = '\0';
    protected int width = 150;
    protected int height = 150;
    protected int fontSize = (int) (this.width * 0.75);
    protected int backgroundColor = Color.RED; // Cor padrão bem óbvia caso algo inesperado ocorra
    protected int borderSize = 8;
    protected int borderColor = Color.BLACK;
    protected boolean isPlaced = false;

    public KeyView(Context context) {
        super(context);
        init();
    }

    public KeyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public KeyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    protected void init() {
        drawable.setColor(this.backgroundColor);
        drawable.setStroke(this.borderSize, this.borderColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(this.width, this.height);
    }

    public char getContent() {
        return this.content;
    }

    public void setContent(char content) {
        this.content = content;
        requestLayout();
        invalidate();
    }

    // Não sobrescreva o método base
    public int getKeyWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
        requestLayout();
        invalidate();
    }

    // Não sobrescreva o método base
    public int getKeyHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
        requestLayout();
        invalidate();
    }

    public int getFontSize() {
        return this.fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
        requestLayout();
        invalidate();
    }

    public int getBackgroundColor() {
        return this.backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        this.drawable.setColor(this.backgroundColor);
        requestLayout();
        invalidate();
    }

    public void setBackgroundColorWithAnimation(int backgroundColor) {
        ObjectAnimator animation = ObjectAnimator.ofArgb(this, "backgroundColor", backgroundColor);
        animation.setDuration(150);
        animation.start();
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

    public void setBorderColorWithAnimation(int borderColor) {
        ObjectAnimator animation = ObjectAnimator.ofArgb(this, "borderColor", borderColor);
        animation.setDuration(150);
        animation.start();
    }

    public boolean isPlaced() {
        return this.isPlaced;
    }

    public void setPlaced(boolean b) {
        this.isPlaced = b;
    }
}
