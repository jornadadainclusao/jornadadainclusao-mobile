package com.example.integra_kids_mobile.games.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Timer extends ConstraintLayout {
    private GradientDrawable drawable = new GradientDrawable();
    private TextView timeView = null;
    private int backgroundColor = Color.parseColor("#EB4A4A");
    private final Handler handler = new Handler();
    private int time = 180_000;
    private int minutes = 0;
    private int seconds = 0;
    private String fmt;

    public Timer(Context context) {
        super(context);
        timeView = new TextView(context);
        init();
    }

    public Timer(Context context, AttributeSet attrs) {
        super(context, attrs);
        timeView = new TextView(context, attrs);
        init();
    }

    public Timer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        timeView = new TextView(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.addView(timeView);
        this.drawable.setShape(GradientDrawable.OVAL);
        this.drawable.setColor(this.backgroundColor);
        int startX = getPaddingRight() - 100, startY = getPaddingTop() - 100;
        this.drawable.setBounds(startX, startY, startX + 100, startY + 100);
    }

    protected void onDraw(@NonNull Canvas canvas) {
        init();
        drawable.draw(canvas);
    }

    private Runnable update = new Runnable() {
        public void run() {
            if (time >= 0) {
                time -= 1000;
                seconds = (time / 1000) / 60;
                minutes = (time / 1000) % 60;
                fmt = String.format("%d:%d", minutes, seconds);
                timeView.setText(fmt);
            } else {
                return;
            }
            handler.postDelayed(this, 1000);
        }
    };

}
