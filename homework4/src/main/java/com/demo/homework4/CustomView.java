package com.demo.homework4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.util.Random;

public class CustomView extends View {

    private Paint paint = new Paint();
    private final int[] colors = new int[5];

    static private float radius;
    static private int sector;
    final static private float sectorSweepAngle = 90;
    final static private float startAngelTopLeft = 180;
    final static private float startAngelBottomRight = 0;
    final static private float startAngelTopRight = 270;
    final static private float startAngelBottomLeft = 90;

    private int centerX;
    private int centerY;

    private int sectorLeft;
    private int sectorRight;
    private int sectorTop;
    private int sectorBottom;

    private ClickCustomListener clickCustomListener;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initColors();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initColors() {
        for (int i = 0; i < colors.length; i++) {
            colors[i] = randomColor();
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            Log.d("Нажаты координаты", +x + ";" + y);
            handleClick(x, y);
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int viewWidth = MeasureSpec.getSize(widthMeasureSpec);
        int viewHeight = MeasureSpec.getSize(heightMeasureSpec);

        centerX = viewWidth / 2;
        centerY = viewHeight / 2;

        radius = (float) (viewWidth * 0.16);
        sector = (int) (viewWidth * 0.4);

        sectorLeft = centerX - sector;
        sectorRight = centerX + sector;
        sectorTop = centerY - sector;
        sectorBottom = centerY + sector;
    }

    protected int randomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256),
     rnd.nextInt(256), rnd.nextInt(256));
    }

    private void handleClick(float x, float y) {
        if (isCenterCircleClicked(x, y)) {
            initColors();
            invalidate();
        }else {
           int arcPosition = getArcPosition(x, y);
           if (arcPosition != -1){
               if (clickCustomListener != null){
                   clickCustomListener.onClicked(new ClickedData(x, y,colors[arcPosition]));
               }
               int randomColor = randomColor();
               colors[arcPosition] = randomColor;
               invalidate();
           }
        }
    }
    private boolean isCenterCircleClicked(float x, float y){
        return  Math.sqrt((x - centerX) * (x - centerX) + (y - centerY) * (y - centerY)) < radius;
    }
    private int getArcPosition(float x, float y){
        boolean insideBigCircle = Math.sqrt((x - centerX) * (x - centerX) + (y - centerY) * (y - centerY)) < sector;
        if(insideBigCircle){
            if(x < centerX && y < centerY){
                return 0;
            }else if (x > centerX && y < centerY){
                return 1;
            }else if(x > centerX && y > centerY){
                return 2;
            }else if(x < centerX && y > centerY){
                return 3;
            }
        }
        return -1;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {

            paint.setColor(colors[0]);
            canvas.drawArc(sectorLeft, sectorTop, sectorRight, sectorBottom, startAngelTopLeft, sectorSweepAngle, true, paint);
            paint.setColor(colors[1]);
            canvas.drawArc(sectorLeft, sectorTop, sectorRight, sectorBottom, startAngelTopRight, sectorSweepAngle, true, paint);

            paint.setColor(colors[2]);
            canvas.drawArc(sectorLeft, sectorTop, sectorRight, sectorBottom, startAngelBottomRight, sectorSweepAngle, true, paint);
            paint.setColor(colors[3]);
            canvas.drawArc(sectorLeft, sectorTop, sectorRight, sectorBottom, startAngelBottomLeft, sectorSweepAngle, true, paint);

            paint.setColor(colors[4]);
            canvas.drawCircle(centerX, centerY, radius, paint);

            super.onDraw(canvas);
    }
    public void setClickCustomListener(ClickCustomListener clickCustomListener){
        this.clickCustomListener = clickCustomListener;

    }
}



