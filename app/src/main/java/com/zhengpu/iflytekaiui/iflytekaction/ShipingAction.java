package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import com.zhengpu.iflytekaiui.MainActivity;
import com.zhengpu.iflytekaiui.iflytekbean.VideoBean;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;

import java.util.List;

import static com.zhengpu.iflytekaiui.utils.DeviceUtils.isAppInstalled;

/**
 * Created by Administrator on 2017/12/27 0027.
 */

public class ShipingAction {

    private VideoBean videoBean;
    private String service;
    private  String request;
    private Context context;

    public ShipingAction(String service, VideoBean videoBean , Context context ,String request) {
         this.videoBean = videoBean;
        this.service = service;
        this.request = request;
        this.context = context;
    }

    public void start() {


//
//        String name = videoBean.getSemantic().get(0).getSlots().get(0).getName();
//        if (name.equals("TVSeries") || name.equals("TVShow") || name.equals("video") || name.equals("film")) {
//            String videoName =videoBean.getSemantic().get(0).getSlots().get(0).getValue();
//            if (isAppInstalled(context, "com.qiyi.video")) {
//                //  打开应用
//                Intent intent = new Intent();
//                intent.setAction("android.intent.action.MAIN");
//                intent.addCategory("android.intent.category.LAUNCHER");
//                PackageManager pm = context.getPackageManager();
//                List<ResolveInfo> installAppList = pm.queryIntentActivities(intent, 0);
//                for (ResolveInfo info : installAppList) {
//                    String name = info.loadLabel(pm).toString();
//                    if (name.equalsIgnoreCase(appName)) {
//                        String pkgname = info.activityInfo.packageName;
//                        if ("com.android.contacts".equalsIgnoreCase(pkgname)) {
//                            Uri uri = Uri.parse("content://contacts/people");
//                            Intent i = new Intent("android.intent.action.VIEW", uri);
//                            context.startActivity(i);
//                        } else {
//                            intent = pm.getLaunchIntentForPackage(pkgname);
//                            intent.addCategory("android.intent.category.LAUNCHER");
//                            context.startActivity(intent);
//                        }
//                        return;
//                    }
//                }
//                WordsToVoice.startSynthesizer(AppController.OPENAPPTEST_APP,"没有找到优酷视频播放" + videoName + "哦");
//            } else {
////                        Logger.e("没有安装爱奇艺APP");
//
//                final CommonDialog dialog = new CommonDialog(MainActivity.this, "你还没爱奇艺APP， 是否去下载该程序");
//                dialog.show();
//
//                dialog.onButOKListener(new CommonDialog.onButOKListener() {
//                    @Override
//                    public void onButOKListener() {
//                        dialog.dismiss();
//                        String keywords = "安卓爱奇艺App";
//                        Intent intent = new Intent(Intent.ACTION_VIEW);
//                        intent.setData(Uri.parse("https://www.baidu.com/s?wd=" + keywords + "&tn=SE_PSStatistics_p1d9m0nf"));
//                        startActivity(intent);
//                    }
//                });
//                dialog.onButCancellListener(new CommonDialog.onButCancelListener() {
//                    @Override
//                    public void onButCancelListener() {
//                        dialog.dismiss();
//                    }
//                });
//            }
//        }















//        SpeechRecognizerService.startSpeech(service, text,request);
    }
}
