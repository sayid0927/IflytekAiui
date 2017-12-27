package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import com.zhengpu.iflytekaiui.MainActivity;
import com.zhengpu.iflytekaiui.base.AppController;
import com.zhengpu.iflytekaiui.base.BaseApplication;
import com.zhengpu.iflytekaiui.dialog.CommonDialog;
import com.zhengpu.iflytekaiui.iflytekbean.VideoBean;
import com.zhengpu.iflytekaiui.iflytekutils.WordsToVoice;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;
import com.zhengpu.iflytekaiui.utils.PreferUtil;

import java.util.List;

import static com.zhengpu.iflytekaiui.utils.DeviceUtils.isAppInstalled;

/**
 * Created by Administrator on 2017/12/27 0027.
 */

public class ShipingAction {

    private VideoBean videoBean;
    private String service;
    private String request;
    private Context context;

    public ShipingAction(String service, VideoBean videoBean, Context context, String request) {
        this.videoBean = videoBean;
        this.service = service;
        this.request = request;
        this.context = context;
    }

    public void start() {

        String name = videoBean.getSemantic().get(0).getSlots().get(0).getName();
        if (name.equals("TVSeries") || name.equals("TVShow") || name.equals("video") || name.equals("film")) {
            String videoName = videoBean.getSemantic().get(0).getSlots().get(0).getValue();
            PreferUtil.getInstance().setPlayVideoName(videoName);
            if (isAppInstalled(context, "com.qiyi.video")) {
                //  打开应用
                OpenAppAction openAppAction = new OpenAppAction("爱奇艺", context);
                openAppAction.start();
                SpeechRecognizerService.startSpeech(service, "为你打开爱奇艺搜索" + videoName, request);

            } else {
//            没有安装爱奇艺APP  打开浏览器 下载APP
                SpeechRecognizerService.startSpeech(service, "你还没安装爱奇艺APP， 是否去下载该程序", request);
                String keywords = "安卓爱奇艺App";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.baidu.com/s?wd=" + keywords + "&tn=SE_PSStatistics_p1d9m0nf"));
                context.startActivity(intent);

//                final CommonDialog dialog = new CommonDialog(BaseApplication.getBaseApplication(), "你还没爱奇艺APP， 是否去下载该程序");
//                dialog.show();
//                dialog.onButOKListener(new CommonDialog.onButOKListener() {
//                    @Override
//                    public void onButOKListener() {
//                        dialog.dismiss();
//                        String keywords = "安卓爱奇艺App";
//                        Intent intent = new Intent(Intent.ACTION_VIEW);
//                        intent.setData(Uri.parse("https://www.baidu.com/s?wd=" + keywords + "&tn=SE_PSStatistics_p1d9m0nf"));
//                        context.startActivity(intent);
//                    }
//                });
//                dialog.onButCancellListener(new CommonDialog.onButCancelListener() {
//                    @Override
//                    public void onButCancelListener() {
//                        dialog.dismiss();
//                    }
//                });
            }
        }
//        SpeechRecognizerService.startSpeech(service, text,request);
    }
}
