package com.zhengpu.app2;

import android.app.Application;

import xiaofei.library.hermeseventbus.HermesEventBus;


public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        HermesEventBus.getDefault().connectApp(this, "com.zhengpu.iflytekaiui");
    }
}
