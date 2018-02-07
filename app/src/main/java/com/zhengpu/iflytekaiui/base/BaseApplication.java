package com.zhengpu.iflytekaiui.base;

import android.app.Application;

import com.blankj.utilcode.utils.ThreadPoolUtils;
import com.blankj.utilcode.utils.Utils;
import com.bugtags.library.Bugtags;
import com.umeng.commonsdk.UMConfigure;
import com.zhengpu.iflytekaiui.utils.PreferUtil;
import com.zhengpu.iflytekaiui.utils.UmengUtil;

import xiaofei.library.hermeseventbus.HermesEventBus;

import static com.bugtags.library.Bugtags.BTGInvocationEventNone;


public class BaseApplication extends Application {


    public static BaseApplication baseApplication;

    /**
     * 主线程池
     */

    public  static ThreadPoolUtils MAIN_EXECUTOR =   new ThreadPoolUtils(ThreadPoolUtils.Type.FixedThread,5);


    @Override
    public void onCreate() {
        super.onCreate();

        HermesEventBus.getDefault().init(this);
        Bugtags.start("a2c7a5fe7d501248b07b48400f6f5210", this, BTGInvocationEventNone);
        Utils.init(this);
        UmengUtil.UmengUtilInit(this);
        PreferUtil.getInstance().init(this);
        UMConfigure.init(this, "5a7a9eb28f4a9d2a5900017c", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "");

    }

    public static BaseApplication getBaseApplication() {
        return baseApplication;
    }

}
