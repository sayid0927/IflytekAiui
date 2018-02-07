package com.zhengpu.iflytekaiui.utils;

import android.content.Context;

import com.blankj.utilcode.utils.AppUtils;
import com.blankj.utilcode.utils.NetworkUtils;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/11/6 0006.
 */

public class UmengUtil {

    private static UmengUtil umengUtil;
    private static Context context;
    private static   HashMap<String, String> maps;

    private UmengUtil(Context context) {

        this.context = context;
        maps = new HashMap<>();
        maps.put("App版本号", AppUtils.getAppVersionName(context));
        maps.put("App版本码", String.valueOf(AppUtils.getAppVersionCode(context)));
        maps.put("App名称", String.valueOf(AppUtils.getAppName(context)));
        maps.put("当前网络类型", String.valueOf(NetworkUtils.getNetworkType()));
        maps.put("设备型号", com.blankj.utilcode.utils.DeviceUtils.getModel());
        maps.put("设备系统版本号", String.valueOf(com.blankj.utilcode.utils.DeviceUtils.getSDKVersion()));

    }

    public static synchronized void UmengUtilInit(Context context) {

        if (umengUtil == null) {
            umengUtil = new UmengUtil(context);
        }
    }

    public static void onEvent(String EventsName) {
        MobclickAgent.onEvent(context, EventsName, maps);
    }
    public static void onEvent(Context context, String EventsName) {
        MobclickAgent.onEvent(context, EventsName, maps);
    }
}
