package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import static com.zhengpu.iflytekaiui.service.SpeechRecognizerService.SendShootAction;


public class ShootAction {

    private Context context;
    private String mAppName;

    public ShootAction(String appname, Context context) {
        this.mAppName = appname;
        this.context = context;
    }

    public void start() {
        SendShootAction("ShootAction","true");
    }
}
