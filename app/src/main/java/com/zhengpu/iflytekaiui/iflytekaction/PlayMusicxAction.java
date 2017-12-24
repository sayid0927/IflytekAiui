package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;


import com.blankj.utilcode.utils.EncodeUtils;
import com.zhengpu.iflytekaiui.R;
import com.zhengpu.iflytekaiui.base.AppController;
import com.zhengpu.iflytekaiui.iflytekbean.otherbean.IfCustomBaikeBean;
import com.zhengpu.iflytekaiui.iflytekbean.otherbean.IfMusicBean;
import com.zhengpu.iflytekaiui.iflytekbean.otherbean.IfMusicResBean;
import com.zhengpu.iflytekaiui.iflytekutils.JsonParser;
import com.zhengpu.iflytekaiui.iflytekutils.WordsToVoice;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;
import com.zhengpu.iflytekaiui.utils.PreferUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * sayid ....
 * Created by wengmf on 2017/11/24.
 */

public class PlayMusicxAction {


    private Context context;
    private String service;
    private String value;

    public PlayMusicxAction(String service, String value, Context context) {
        this.service = service;
        this.value = value;
        this.context = context;
    }

    public void start() {

        String url = "http://aiui.xfyun.cn/taste/getAnswer?text=" + EncodeUtils.urlEncode(value) + "&uid=1513933005954&appid=all&category=musicX&timestamp=1513933075498";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                SpeechRecognizerService.startSpeech(service, context.getResources().getString(R.string.error_network_text));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                IfMusicBean ifMusicBean = JsonParser.parseResultIfMusicBean(res);
                if (ifMusicBean != null && ifMusicBean.getData() != null && ifMusicBean.getData().getRes() != null) {
                    IfMusicResBean ifMusicResBean = JsonParser.parseResultIfMusicResBean(ifMusicBean.getData().getRes());
                    if (ifMusicResBean != null && ifMusicResBean.getData() != null && ifMusicResBean.getData().getResult() != null && ifMusicResBean.getData().getResult().get(0).getAudiopath() != null) {
                        PreferUtil.getInstance().setPlayMusicUrl(ifMusicResBean.getData().getResult().get(0).getAudiopath());
                        SpeechRecognizerService.startSpeech(service, ifMusicResBean.getAnswer().getText());
                    }
                }
            }
        });
    }

//    private void startAppName() {
//        Intent intent = new Intent();
//        intent.setAction("android.intent.action.MAIN");
//        intent.addCategory("android.intent.category.LAUNCHER");
//        PackageManager pm = context.getPackageManager();
//        List<ResolveInfo> installAppList = pm.queryIntentActivities(intent, 0);
//        for (ResolveInfo info : installAppList) {
//            String name = info.loadLabel(pm).toString();
//            if (name.equalsIgnoreCase(appName)) {
//                String pkgname = info.activityInfo.packageName;
//                if ("com.android.contacts".equalsIgnoreCase(pkgname)) {
//                    Uri uri = Uri.parse("content://contacts/people");
//                    Intent i = new Intent("android.intent.action.VIEW", uri);
//                    context.startActivity(i);
//                    WordsToVoice.startSynthesizer(AppController.OPENAPPTEST_APP,text);
//                } else {
//                    intent = pm.getLaunchIntentForPackage(pkgname);
//                    intent.addCategory("android.intent.category.LAUNCHER");
//                    context.startActivity(intent);
//                    WordsToVoice.startSynthesizer(AppController.OPENAPPTEST_APP,text);
//                }
//                return;
//            }
//        }
//        WordsToVoice.startSynthesizer(AppController.OPENAPPTEST_APP,"没有找到酷狗音乐播放" + songName + "哦");
//    }
}
