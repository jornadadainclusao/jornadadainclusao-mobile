package com.example.integra_kids_mobile.games.components;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;

import android.os.Handler;
import android.widget.FrameLayout;

public class Timer extends androidx.appcompat.widget.AppCompatTextView {

    private final Handler handler = new Handler();
    private int time = 180_000;

    private long startedAt = 0;  // marca o início real

    public Timer(Context context) {
        super(context);
        init();
    }

    public void startTimer() {
        startedAt = System.currentTimeMillis();
        handler.post(updateTimer);
    }

    public void stopTimer() {
        handler.removeCallbacks(updateTimer);
    }

    public int getTime() {
        long now = System.currentTimeMillis();
        return (int) ((now - startedAt) / 1000);  // segundos reais
    }

    // ---------- RESTO DO SEU CÓDIGO IGUAL ----------
    private void init() {
        this.setTextColor(Color.WHITE);
        this.setTextSize(18);
        this.setGravity(Gravity.CENTER);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.OVAL);
        drawable.setColor(Color.parseColor("#EB4A4A"));
        this.setBackground(drawable);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(100, 100);
        params.gravity = Gravity.TOP | Gravity.END;
        params.setMargins(0, 50, 10, 0);
        this.setLayoutParams(params);

        setTimerText(formatTimerString());
    }

    private String formatTimerString() {
        int minutes = (time / 1000) / 60;
        int seconds = (time / 1000) % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    private void setTimerText(String s) {
        this.setText(s);
    }

    private final Runnable updateTimer = new Runnable() {
        public void run() {
            if (time <= 0) {
                return;
            }

            time -= 1000;
            setTimerText(formatTimerString());
            handler.postDelayed(this, 1000);
        }
    };
}
