package com.zhengpu.watch.serivce;

import android.app.ActivityManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.zhengpu.watch.base.MyApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * sayid ....
 * Created by wengmf on 2018/2/1.
 */

public class StartCommandService extends Service {
    private ActivityManager mActivityManager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;

    }


    @Override
    public void onCreate() {

        MyApplication.MAIN_EXECUTOR.scheduleWithFixedDelay(StartService(), 1, 2, TimeUnit.SECONDS);
        mActivityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);

    }


    private Runnable StartService() {
        return new Runnable() {
            @Override
            public void run() {
                List<ActivityManager.RunningServiceInfo> mServiceList = mActivityManager.getRunningServices(100);
                if (!ServiceIsStart(mServiceList, "com.zhengpu.iflytekaiui.service.SpeechRecognizerService")) {
                    try {
                        Intent intent = new Intent();
                        ComponentName componentName = new ComponentName("com.zhengpu.iflytekaiui",
                                "com.zhengpu.iflytekaiui.service.SpeechRecognizerService");
                        intent.setComponent(componentName);
                        startService(intent);
                    }catch (Exception e){
                        e.toString();
                    }
                }else {
                }
            }
        };
    }


    //通过Service的类名来判断是否启动某个服务  
    private boolean ServiceIsStart(List<ActivityManager.RunningServiceInfo> mServiceList, String className) {
        for (int i = 0; i < mServiceList.size(); i++) {
            if (className.equals(mServiceList.get(i).service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    //获取所有启动的服务的类名
    private List<String> getServiceClassName(List<ActivityManager.RunningServiceInfo> mServiceList) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < mServiceList.size(); i++) {
            res.add(mServiceList.get(i).service.getClassName());
        }
        return res;
    }
}
