package com.zhengpu.watch.base;

import android.annotation.TargetApi;
import android.app.Application;
import android.os.Build;

import com.blankj.utilcode.utils.ThreadPoolUtils;
import com.blankj.utilcode.utils.Utils;
import com.bugtags.library.Bugtags;
//import com.commonsdk.application.BaseApplication;
//import com.commonsdk.zeunpro.appupdatecheck.AppUpdateCheck;
//import com.commonsdk.zeunpro.data.ZpData;
import com.zhengpu.watch.component.AppComponent;
import com.zhengpu.watch.component.DaggerAppComponent;
import com.zhengpu.watch.module.ApiModule;
import com.zhengpu.watch.module.AppModule;
import com.zhengpu.watch.utils.AppUtils;
import com.zhengpu.watch.utils.PreferUtil;

import xiaofei.library.hermeseventbus.HermesEventBus;

import static com.bugtags.library.Bugtags.BTGInvocationEventNone;

@SuppressWarnings("unused")

public class MyApplication extends Application {

    public static MyApplication baseApplication;

    private static AppComponent appComponent;

    public  static ThreadPoolUtils MAIN_EXECUTOR =   new ThreadPoolUtils(ThreadPoolUtils.Type.FixedThread,5);


    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
        //将我们自己的MyApplication中的所有逻辑放在这里，例如初始化一些第三方

        initCompoent();
        HermesEventBus.getDefault().connectApp(this, "com.zhengpu.iflytekaiui");
        PreferUtil.getInstance().init(this);
        AppUtils.init(this);
        Utils.init(this);
        Bugtags.start("d9e1bb99a794bb5d45206e48b8e9c8a3", this, BTGInvocationEventNone);
//    AppUpdateCheck updateCheck = new AppUpdateCheck(getApplicationContext(), ZpData.IP + AppUpdateCheck.APPUPDATE_URL);
//     updateCheck.checkAppUpdate(AppUpdateCheck.ALWAYS);

    }


    private void initCompoent() {
        appComponent = DaggerAppComponent.builder()
                .apiModule(new ApiModule())
                .appModule(new AppModule(this))
                .build();
    }


    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)


    /**
     * 获取SampleApplication实例
     * @return
     */

    public static MyApplication getBaseApplication() {
        return baseApplication;
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

}
