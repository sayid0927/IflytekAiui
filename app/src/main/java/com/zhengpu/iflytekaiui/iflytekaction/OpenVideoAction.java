package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;


public class OpenVideoAction {

    private Context context;
    private String mAppName;

    public OpenVideoAction(String appname, Context context) {
        this.mAppName = appname;
        this.context = context;
    }

    public void start() {

        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("com.android.camera2","com.android.camera.CameraActivity");
        intent.setComponent(componentName);
//        intent.setAction("android.intent.action.TAKE_PICTURE");
        intent.setAction("android.intent.action.VIDEO_RECORD");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context. startActivity(intent);

    }
}
