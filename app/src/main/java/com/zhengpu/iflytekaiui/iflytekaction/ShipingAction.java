package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.blankj.utilcode.utils.EncodeUtils;
import com.zhengpu.iflytekaiui.base.AppController;
import com.zhengpu.iflytekaiui.iflytekbean.VideoBean;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;
import com.zhengpu.iflytekaiui.utils.PreferUtil;

import static com.zhengpu.iflytekaiui.utils.DeviceUtils.isAppInstalled;
import static com.zhengpu.iflytekaiui.utils.DeviceUtils.updatePlayStart;

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

        if (videoBean != null && videoBean.getSemantic() != null && videoBean.getSemantic().size() != 0 &&
                videoBean.getSemantic().get(0).getSlots() != null && videoBean.getSemantic().get(0).getSlots().get(0).getName() != null) {

            String name = videoBean.getSemantic().get(0).getSlots().get(0).getName();
            if (name.equals("TVSeries") || name.equals("TVShow") || name.equals("video") || name.equals("film")) {
                String videoName = videoBean.getSemantic().get(0).getSlots().get(0).getValue();
                PreferUtil.getInstance().setPlayVideoName(videoName);
                if (isAppInstalled(context, "com.qiyi.video")) {
                    //  打开应用

                    updatePlayStart(context,4,"true");

                    OpenAppAction openAppAction = new OpenAppAction("爱奇艺", context);
                    openAppAction.start();

                    SpeechRecognizerService.startSpeech(service, "为你打开爱奇艺搜索" + videoName, request);

                } else {
//                没有安装爱奇艺APP  打开浏览器 下载APP
                    SpeechRecognizerService.startSpeech(service, "你还没安装爱奇艺APP， 是否去下载该程序", request);
                    String keywords = "安卓爱奇艺App";
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    String url ="https://m.baidu.com/from=844b/s?word="+ EncodeUtils.urlEncode(keywords);
                    intent.setData(Uri.parse(url));
                    context.startActivity(intent);
                }
            }
        } else {
            R4Action r4Action = new R4Action(context,request);
            r4Action.start();
        }
    }
}
