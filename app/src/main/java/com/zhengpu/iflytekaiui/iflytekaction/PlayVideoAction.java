package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;


import com.zhengpu.iflytekaiui.base.AppController;
import com.zhengpu.iflytekaiui.iflytekutils.WordsToVoice;
import com.zhengpu.iflytekaiui.utils.PreferUtil;

import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2017/11/29.
 */

public class PlayVideoAction {


    private Context context;
    private String videoName;
    private String appName;

    public PlayVideoAction(String videoName, String appName, Context context) {
        this.videoName = videoName;
        this.appName = appName;
        this.context = context;
        AppController.AiQiPlayClickabl =true;

    }


    public void start() {

        if (appName != null && videoName != null && context != null) {

            PreferUtil.getInstance().setPlayVideoName(videoName);
            startAppName();
        }
    }

    private void startAppName() {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> installAppList = pm.queryIntentActivities(intent, 0);
        for (ResolveInfo info : installAppList) {
            String name = info.loadLabel(pm).toString();
            if (name.equalsIgnoreCase(appName)) {
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
                return;
            }
        }
        WordsToVoice.startSynthesizer(AppController.OPENAPPTEST_APP,"没有找到优酷视频播放" + videoName + "哦");
    }


}
