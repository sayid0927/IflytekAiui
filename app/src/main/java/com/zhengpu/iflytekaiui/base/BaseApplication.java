package com.zhengpu.iflytekaiui.base;

import android.app.Application;

import xiaofei.library.hermeseventbus.HermesEventBus;


public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        HermesEventBus.getDefault().init(this);
    }
}
