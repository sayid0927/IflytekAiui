package com.zhengpu.iflytekaiui.base;

import android.app.Application;

import com.blankj.utilcode.utils.Utils;
import com.bugtags.library.Bugtags;
import com.zhengpu.iflytekaiui.utils.PreferUtil;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import xiaofei.library.hermeseventbus.HermesEventBus;

import static com.bugtags.library.Bugtags.BTGInvocationEventNone;


public class BaseApplication extends Application {


    /**
     * 主线程池
     */
    public static Executor MAIN_EXECUTOR = Executors.newFixedThreadPool(5);


    @Override
    public void onCreate() {
        super.onCreate();
        Bugtags.start("a2c7a5fe7d501248b07b48400f6f5210", this, BTGInvocationEventNone);
        HermesEventBus.getDefault().init(this);
        Utils.init(this);
        PreferUtil.getInstance().init(this);
    }
}
