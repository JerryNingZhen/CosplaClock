package com.itheima.cosplatime.view;

import java.util.Calendar;

import com.itheima.cosplatime.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.view.View;

/**
 * ClassName:ClockView <br/>
 * Function: 自定义时钟 <br/>
 * Date: 2016年9月7日 下午6:35:09 <br/>
 * 
 * @author 宁震
 * @version
 */
public class ClockView extends View {

    private Paint mPaint;
    private Paint mDigitsPaint;
    private Bitmap Logo;
    private Paint mLogoPaint;
    private Paint mPaintBackground;

    public ClockView(Context context) {
        this(context, null);
    }

    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setStyle(Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);
        // 刻度画笔
        mDigitsPaint = new Paint();
        mDigitsPaint.setTextSize(40);
        mDigitsPaint.setTextAlign(Align.CENTER);
        mDigitsPaint.setColor(Color.BLACK);
        Logo = BitmapFactory.decodeResource(getResources(), R.drawable.logo);

        // 画logo paint
        mLogoPaint = new Paint();
        // 画背景
        
    }

    private void initDrawBackground() {
        mPaintBackground = new Paint();
        int[] centerColor = {Color.BLACK, Color.LTGRAY, Color.WHITE, Color.LTGRAY};
        float[] edgeColor = {0,0.5f,0.7f,1.0f};
        TileMode tileMode = TileMode.CLAMP;
        Shader shader = new RadialGradient(mCx, mCy, mRadius, centerColor, edgeColor, tileMode);
        mPaintBackground.setShader(shader);
    }

    private int mCx;
    private int mCy;
    private int mRadius;
    private int mPadding = 5;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mCx = w / 2;
        mCy = h / 2;
        mRadius = w / 2 - mPadding;
        initDrawBackground();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画背景
        drawBackGround(canvas);
        // 画圆环
        canvas.drawCircle(mCx, mCy, mRadius, mPaint);
        // 画刻度
        drawTimeLine(canvas);
        // 画数字
        drawNumber(canvas);
        // 画logo
        drawLogo(canvas);
        // 画指针
        drawPointer(canvas);

    }

    private void drawBackGround(Canvas canvas) {
           //画背景
        canvas.drawCircle(mCx, mCy, mRadius, mPaintBackground);
    }

    private void drawPointer(Canvas canvas) {
        // 画秒针
        drawSecondPoint(canvas);
        // 画分针
        drawMinutePoint(canvas);
        // 画时针
        drawHourPoint(canvas);
    }
    private int hourLine = 100;
    private int minuteLine = 40;
    private int secondLine = -20;
    private int hourWidth = 20;
    private int minuteWidth = 16;
    private int secondWidth = 12;
    private int mHour;
    private int mMinute;
    private int mSecond;
    private float mHourDegress;
    private int mMinuteDegress;
    private int mSecondDegress;
    private void drawHourPoint(Canvas canvas) {
        Path path = new Path();
        float x1 = mCx, y1 = mPadding + mLongLineLength + mDigitsPaint.getTextSize() + 10+ hourLine;
        float x2 = mCx + hourWidth,y2 = mCy+20;
        float x3 = mCx,y3 = mCy ;
        float x4 = mCx-hourWidth,y4 = mCy+20;
        path.moveTo(x1, y1);
        path.lineTo(x2, y2);
        path.lineTo(x3, y3);
        path.lineTo(x4, y4);
        path.close();
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.GRAY);
        canvas.rotate(mHourDegress, mCx, mCy);
        canvas.drawPath(path, paint);
        canvas.rotate(-mHourDegress, mCx, mCy);
        
    }

    private void drawMinutePoint(Canvas canvas) {
        Path path = new Path();
        float x1 = mCx, y1 = mPadding + mLongLineLength + mDigitsPaint.getTextSize() + 10+ minuteLine;
        float x2 = mCx + minuteWidth,y2 = mCy+20;
        float x3 = mCx,y3 = mCy ;
        float x4 = mCx-minuteWidth,y4 =  mCy+20;
        path.moveTo(x1, y1);
        path.lineTo(x2, y2);
        path.lineTo(x3, y3);
        path.lineTo(x4, y4);
        path.close();
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        canvas.rotate(mMinuteDegress, mCx, mCy);
        canvas.drawPath(path, paint);
        canvas.rotate(-mMinuteDegress, mCx, mCy);
    }

    private void drawSecondPoint(Canvas canvas) {
        Path path = new Path();
        float x1 = mCx, y1 = mPadding + mLongLineLength + mDigitsPaint.getTextSize() + 10+ secondLine;
        float x2 = mCx + secondWidth,y2 = mCy+20;
        float x3 = mCx,y3 = mCy ;
        float x4 = mCx-secondWidth,y4 =  mCy+20;
        path.moveTo(x1, y1);
        path.lineTo(x2, y2);
        path.lineTo(x3, y3);
        path.lineTo(x4, y4);
        path.close();
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        canvas.rotate(mSecondDegress, mCx, mCy);
        canvas.drawPath(path, paint);
        canvas.rotate(-mSecondDegress, mCx, mCy);
        
    }

    private void drawLogo(Canvas canvas) {
        float left = mCx - Logo.getWidth() / 2;
        float top = mPadding + mLongLineLength + mDigitsPaint.getTextSize() + 60;
        canvas.drawBitmap(Logo, left, top, mLogoPaint);
    }

    private void drawNumber(Canvas canvas) {
        for (int i = 1; i <= 12; i++) {
            float x = mCx;
            float y = mPadding + mLongLineLength + mDigitsPaint.getTextSize() + 10;
            canvas.rotate(30, mCx, mCy);
            float px = mCx;
            float py = mPadding + mLongLineLength + mDigitsPaint.getTextSize() / 2 + 10;
            canvas.rotate(-30 * i, px, py);
            canvas.drawText(String.valueOf(i), x, y, mDigitsPaint);
            canvas.rotate(30 * i, px, py);
        }
    }

    private float mLongLineLength = 25f;
    private float mShortLineLength = 15f;

    private void drawTimeLine(Canvas canvas) {
        for (int i = 0; i < 60; i++) {
            float startX = mCx;
            float startY = mPadding;
            float stopX = startX;
            float stopY = 0;
            if (i % 5 == 0) {
                stopY = stopY + mLongLineLength;
            } else {
                stopY = stopY + mShortLineLength;
            }
            canvas.drawLine(startX, startY, stopX, stopY, mPaint);
            canvas.rotate(6, mCx, mCy);
        }
    }
    @Override
    protected void onAttachedToWindow() {
        postDelayed(mTicker, 1000);
    }
    private Runnable mTicker  =  new Runnable() {
        public void run() {
            startTick();
            postDelayed(mTicker, 1000);
        }
    };
   
    private void startTick() {
        Calendar mcCalendar = Calendar.getInstance();
        mcCalendar.setTimeInMillis(System.currentTimeMillis());
        mHour = mcCalendar.get(Calendar.HOUR);
        mMinute = mcCalendar.get(Calendar.MINUTE);
        mSecond = mcCalendar.get(Calendar.SECOND);
        mSecondDegress = mSecond*6;
        mMinuteDegress = mMinute*6;
        mHourDegress = mHour*30+(mMinute/60.0f)*30;
        invalidate();
    }
    /**
     * 控件从window移除时的调用
     */
  /*  protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(mTicker);
    };*/


}
