
package com.itheima.cosplatime.app;

import com.itheima.cosplatime.servece.LockService;
import com.itheima.cosplatime.utils.ServiceUtils;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

/**
 * ClassName:MyApplication <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Date: 2016年9月9日 上午8:20:38 <br/>
 * 
 * @author 宁震
 * @version
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Intent service = new Intent(this,LockService.class);
        //注册服务
        startService(service);
        boolean serviceRuing = ServiceUtils.isServiceRuing(this, LockService.class);
        String tag = "MyApplication";
        Log.e(tag , "LockService服务是否运行"+serviceRuing);
    }
}
