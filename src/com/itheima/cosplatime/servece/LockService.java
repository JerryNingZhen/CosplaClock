  
package com.itheima.cosplatime.servece;


import com.itheima.cosplatime.MainActivity;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

/** 
 * ClassName:LockService <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Date:     2016年9月9日 上午8:15:41 <br/> 
 * @author   宁震 
 * @version       
 */
public class LockService extends Service {

    private MyReceiver receiver;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        receiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        // 锁屏事件
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.setPriority(Integer.MAX_VALUE);
        registerReceiver(receiver, filter);
    }
    class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // 
            if (Intent.ACTION_SCREEN_OFF.equals(action)||Intent.ACTION_SCREEN_ON.equals(action)) {
                Intent lockscreen = new Intent(LockService.this, MainActivity.class);
                lockscreen.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(lockscreen);
            } 
        }
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

}
  