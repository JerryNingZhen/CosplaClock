package com.itheima.cosplatime;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;
import android.widget.Toast;

/**
 * ClassName:LockView <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Date: 2016年8月29日 下午6:18:33 <br/>
 * 
 * @author 宁震
 * @version
 */
public class LockView extends View {

    private Bitmap bitmap;
    private Paint paint;
    private int max;
    private  OnLockListener mListener ;
    private Scroller mScroller;
    private int screenWidth;
    public LockView(Context context) {
        this(context, null);
    }

    public LockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.switch_button);
        paint = new Paint();
        mScroller = new Scroller(getContext());
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measuredHeight = bitmap.getHeight();
        screenWidth = widthMeasureSpec;
        setMeasuredDimension(widthMeasureSpec, measuredHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawBitmap(bitmap, 0, 0, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float x2 = event.getX();
                if (x2>bitmap.getWidth()) {
                    return false;
                }
                float x1 = bitmap.getWidth()/2.0f;
                float dx =   x2 -  x1;
                if (dx<0) {
                    dx = 0;
                }else if (dx>max) {
                    dx = max;
                }
                scrollTo((int) -dx, 0);
                break;
            case MotionEvent.ACTION_MOVE:
                float x4 = event.getX();
                float x3 = bitmap.getWidth()/2.0f;
                float dx1 =   x4 -  x3;
                if (dx1<0) {
                    dx1 = 0;
                }else if (dx1>max) {
                    dx1 = max;
                }
                scrollTo((int) -dx1, 0);
                break;
            case MotionEvent.ACTION_UP:
                float x5 = event.getX();
                float x6 = bitmap.getWidth()/2.0f;
                float dx3 =   x5 -  x6;
                if (dx3<max) {
                    int startX = getScrollX();
                    int startY = 0;
                    int dx0 = 0-startX;
                    int dy0 = 0;
                    //返回起始位置
//                    mScroller.START
//                    scrollTo(0, 0);
                    //平缓的返回到起点
                    mScroller.startScroll(startX, startY, dx0, dy0, 300);
                    invalidate();
                }else  {
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            SystemClock.sleep(200);
                            if (mListener!=null) {
                                mListener.onUnLock();
                            }
                        }
                    }.start();
                   
                }
                break;
        }

        return true;
    }
    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), 0);
            invalidate();
        }
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        max = getWidth() -bitmap.getWidth();
    }
    public void setOnLockListener(OnLockListener listener ) {
        mListener = listener;
    }
    public interface OnLockListener {
        void onUnLock();
    }

}
