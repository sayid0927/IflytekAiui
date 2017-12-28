package com.zhengpu.iflytekaiui.base;

import android.app.Application;

import com.bugtags.library.Bugtags;

import xiaofei.library.hermeseventbus.HermesEventBus;

import static com.bugtags.library.Bugtags.BTGInvocationEventNone;


public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Bugtags.start("beb9b4f14e72470fe0ad088b715ec421", this, BTGInvocationEventNone);
        HermesEventBus.getDefault().init(this);
    }
}
