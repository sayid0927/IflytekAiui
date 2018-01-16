package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.blankj.utilcode.utils.EncodeUtils;
import com.zhengpu.iflytekaiui.R;
import com.zhengpu.iflytekaiui.base.AppController;
import com.zhengpu.iflytekaiui.iflytekbean.VideoBean;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;
import com.zhengpu.iflytekaiui.utils.CommDialog;
import com.zhengpu.iflytekaiui.utils.PreferUtil;

import java.util.logging.Logger;

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

        if (videoBean != null && videoBean.getAnswer() != null && videoBean.getData() != null && videoBean.getData().getResult() != null
                && videoBean.getData().getResult().size() != 0) {
            String text = videoBean.getAnswer().getText();
            if (text.equals("请告诉我您想看的影视名。")) {
                SpeechRecognizerService.startSpeech(AppController.VIDEO_1, text, request);

            } else {


                if (isAppInstalled(context, "com.qiyi.video")) {
                    //  打开应用
                    String videoName = videoBean.getData().getResult().get(0).getName();
                    updatePlayStart(context,6,videoBean.getData().getResult().get(0).getName());
                    updatePlayStart(context, 4, "true");
                    updatePlayStart(context,5,"true");
                    OpenAppAction openAppAction = new OpenAppAction("爱奇艺", context);
                    openAppAction.start();
                    SpeechRecognizerService.startSpeech(service, "为你打开爱奇艺搜索" + videoName, request);

                } else {
//                没有安装爱奇艺APP  打开浏览器 下载APP
                    SpeechRecognizerService.startSpeech(service, context.getResources().getString(R.string.aiqiyi_tip), request);
                    showDialog();
                }
            }
        } else {
            R4Action r4Action = new R4Action(context, request);
            r4Action.start();
        }
    }


    public void showDialog() {
        final CommDialog commDialog = new CommDialog(context,context.getResources().getString(R.string.aiqiyi_tip));
        commDialog.setOnClickListener(new CommDialog.OnClickListener() {
            @Override
            public void onButtonOKClick() {
                String keywords = "安卓爱奇艺App";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                String url = "https://m.baidu.com/from=844b/s?word=" + EncodeUtils.urlEncode(keywords);
                intent.setData(Uri.parse(url));
                context.startActivity(intent);
                commDialog.dismiss();
            }
            @Override
            public void onButtonCanelClick() {
                commDialog.dismiss();
            }
        });
        commDialog.show();
    }
}
