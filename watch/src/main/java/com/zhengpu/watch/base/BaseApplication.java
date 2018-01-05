package com.zhengpu.watch.base;

import android.annotation.TargetApi;
import android.app.Application;
import android.os.Build;

import com.bugtags.library.Bugtags;
import com.zhengpu.watch.component.AppComponent;
import com.zhengpu.watch.component.DaggerAppComponent;
import com.zhengpu.watch.module.ApiModule;
import com.zhengpu.watch.module.AppModule;
import com.zhengpu.watch.utils.AppUtils;
import com.zhengpu.watch.utils.PreferUtil;

import static com.bugtags.library.Bugtags.BTGInvocationEventNone;

@SuppressWarnings("unused")

public class BaseApplication extends Application {

    public static BaseApplication baseApplication;

    private static AppComponent appComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
        //将我们自己的MyApplication中的所有逻辑放在这里，例如初始化一些第三方

        initCompoent();
        PreferUtil.getInstance().init(this);
        AppUtils.init(this);
        Bugtags.start("beb9b4f14e72470fe0ad088b715ec421", this, BTGInvocationEventNone);

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

    public static BaseApplication getBaseApplication() {
        return baseApplication;
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }


}
