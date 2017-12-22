package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;


import com.zhengpu.iflytekaiui.base.AppController;
import com.zhengpu.iflytekaiui.iflytekutils.WordsToVoice;

import java.util.List;


public class OpenAppAction {

    private Context context;
    private String mAppName;

    public OpenAppAction(String appname, Context context) {
        this.mAppName = appname;
        this.context = context;
    }

    public void start() {
        if ((mAppName != null) && (mAppName.length() != 0)) {
            getAppByName();
        }
    }

    private void getAppByName() {

        Intent intent = new Intent();
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> installAppList = pm.queryIntentActivities(intent, 0);
        for (ResolveInfo info : installAppList) {
            String name = info.loadLabel(pm).toString();
            if (name.equalsIgnoreCase(mAppName)) {
                String pkgname = info.activityInfo.packageName;
                if ("com.android.contacts".equalsIgnoreCase(pkgname)) {
                    Uri uri = Uri.parse("content://contacts/people");
                    Intent i = new Intent("android.intent.action.VIEW", uri);
                    context.startActivity(i);
                } else {
                    intent = pm.getLaunchIntentForPackage(pkgname);
                    intent.addCategory("android.intent.category.LAUNCHER");
                    context.startActivity(intent);
                }
                WordsToVoice.startSynthesizer(AppController.OPENAPPTEST_APP,"正在打开" + mAppName + "...");
                return;
            }
        }
        WordsToVoice.startSynthesizer(AppController.OPENAPPTEST_APP,"没有找到你所说的应用哦");
    }
}
