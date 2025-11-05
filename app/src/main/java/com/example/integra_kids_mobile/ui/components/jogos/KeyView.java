package com.example.integra_kids_mobile.ui.components.jogos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

public class KeyView extends View {
    final private Paint paint = new Paint();
    private char keyContent = '\0';
    private int keyWidth = 150;
    private int keyHeight = 150;
    private int keyFontSize = (int) (this.keyWidth * 0.75);
    private int keyBackgroundColor = Color.RED;

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

    private void init() {
        paint.setColor(this.keyBackgroundColor);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int viewWidth = getWidth();
        int viewHeight = getHeight();

        int startX = 0;
        int startY = 0;

        canvas.drawOval(startX, startY, startX + this.keyWidth, startY + this.keyHeight, this.paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size = Math.max(this.keyWidth, this.keyHeight);
        setMeasuredDimension(size, size);
    }

    public char getKeyContent() {
        return this.keyContent;
    }

    public void setKeyContent(char keyContent) {
        this.keyContent = keyContent;
        requestLayout();
        invalidate();
    }

    public int getKeyWidth() {
        return this.keyWidth;
    }

    public void setKeyWidth(int keyWidth) {
        this.keyWidth = keyWidth;
        requestLayout();
        invalidate();
    }

    public int getKeyHeight() {
        return this.keyHeight;
    }

    public void setKeyHeight(int keyHeight) {
        this.keyHeight = keyHeight;
        requestLayout();
        invalidate();
    }

    public int getKeyFontSize() {
        return this.keyFontSize;
    }

    public void setKeyFontSize(int keyFontSize) {
        this.keyFontSize = keyFontSize;
        requestLayout();
        invalidate();
    }

    public int getKeyBackgroundColor() {
        return this.keyBackgroundColor;
    }

    public void setKeyBackgroundColor(int keyBackgroundColor) {
        this.keyBackgroundColor = keyBackgroundColor;
        paint.setColor(this.keyBackgroundColor);
        requestLayout();
        invalidate();
    }
}
