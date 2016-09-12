package com.itheima.cosplatime;

import java.text.SimpleDateFormat;

import com.itheima.cosplatime.LockView.OnLockListener;

import android.app.Activity;
import android.os.Bundle;
import android.os.storage.OnObbStateChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.Toast;

public class MainActivity extends Activity {
    private RelativeLayout mRelativeLayout;
    private Scroller mScroller;
    private LockView lockview;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置无标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); // 设置全屏
        setContentView(R.layout.activity_main);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
      initView();
      initEvent();
    }
    private void initEvent() {
        lockview.setOnLockListener(new OnLockListener() {
            
            @Override
            public void onUnLock() {
                finish();
            }
        });
    }
    private void initView() {
        mRelativeLayout = (RelativeLayout) findViewById(R.id.main);
        lockview = (LockView) findViewById(R.id.lockview);
    }
    @Override
    public void onBackPressed() {
        // do nothing
    }
    /*private void initEvent() {
        mRelativeLayout.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float downX = 0;
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downX = event.getX();
                        long nowLongTime = System.currentTimeMillis();
                        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
                        String format = sdf.format(nowLongTime);
//                        Toast.makeText(getApplicationContext(),format, 1).show();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Toast.makeText(getApplicationContext(), "是否move", 0).show();
                        float moveX = event.getX();
                        float diffX = downX - moveX;
                        float finalScrollX = v.getScrollX() + diffX;
                        if (finalScrollX < mRelativeLayout.getMeasuredWidth()) {
                            mRelativeLayout.scrollTo(mRelativeLayout.getMeasuredWidth(), 0);
                            return true;
                        } else if (finalScrollX > 0) {
                            v.scrollTo(0, 0);

                            return true;
                        }
                        v.scrollBy((int) diffX, 0);
                        downX = moveX;
                        break;
                    case MotionEvent.ACTION_UP:
                        Toast.makeText(getApplicationContext(), "是否up", 0).show();
                        float threshold = mRelativeLayout.getMeasuredWidth() / 3.0f;
                        if (v.getScrollX() < threshold) {
                            open(v);
                        } else {
                            close(v);
                        }
                        
                        break;
                }
//                finish();
                return false;
            }
        });
    }
  
   

    public void close(View v) {

        mScroller.startScroll(v.getScrollX(), 0, -v.getScrollX(), 0, 500);
        if (mScroller.computeScrollOffset()) {
            mRelativeLayout.scrollTo(mScroller.getCurrX(), 0);
            v.invalidate();
        }
        v.invalidate();
    }

    public void open(View v) {
        if (mScroller.computeScrollOffset()) {
            mRelativeLayout.scrollTo(mScroller.getCurrX(), 0);
            v.invalidate();
        }
        mScroller.startScroll(v.getScrollX(), 0,
                mRelativeLayout.getMeasuredWidth() - mRelativeLayout.getScrollX(), 0, 500);
        v.invalidate();
    }*/
}
