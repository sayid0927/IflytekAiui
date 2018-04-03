package com.zhengpu.app2;

import android.app.Application;

import com.blankj.utilcode.utils.Utils;
import com.umeng.commonsdk.UMConfigure;

import xiaofei.library.hermeseventbus.HermesEventBus;


public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Utils.init(this);
        UmengUtil.UmengUtilInit(this);
        UMConfigure.init(this, "5a73f4ccf43e480e110001a0", "100", UMConfigure.DEVICE_TYPE_PHONE, "");
        HermesEventBus.getDefault().connectApp(this, "com.zhengpu.iflytekaiui");


    }
}
