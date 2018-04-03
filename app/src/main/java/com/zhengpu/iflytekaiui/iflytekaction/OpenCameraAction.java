package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import com.zhengpu.iflytekaiui.base.AppController;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;

import java.util.List;


public class OpenCameraAction {

    private Context context;
    private String mAppName;

    public OpenCameraAction(String appname, Context context) {
        this.mAppName = appname;
        this.context = context;
    }

    public void start() {

        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("com.android.camera2","com.android.camera.CameraActivity");
        intent.setComponent(componentName);
        intent.setAction("android.intent.action.TAKE_PICTURE");
//        intent.setAction("android.intent.action.VIDEO_RECORD");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context. startActivity(intent);

    }
}
