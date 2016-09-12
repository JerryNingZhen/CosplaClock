
package com.itheima.cosplatime.utils;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.Service;
import android.content.Context;

/**
 * ClassName:ServiceUtils <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Date: 2016年8月12日 上午1:07:23 <br/>
 * 
 * @author 宁震
 * @version
 */
public class ServiceUtils {

    public static boolean isServiceRuing(Context context,Class<? extends Service> clazz ) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningServiceInfo> list = am.getRunningServices(Integer.MAX_VALUE);
        boolean flag = false;
        for (RunningServiceInfo runningServiceInfo : list) {
            String className = runningServiceInfo.service.getClassName();
            //System.out.println("RunningServiceInfo:"+className);
            if (className.equals(clazz.getName())) {
                flag = true;
            }
        }
        return flag;
    }
}
