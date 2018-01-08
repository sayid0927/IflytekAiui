package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.zhengpu.iflytekaiui.iflytekbean.BaikeBean;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;

/**
 * Created by Administrator on 2017/12/24 0024.
 */

public class BaikeAction {

    private String service;
    private  String request;
    private BaikeBean baikeBean;
    private Context context;

    public BaikeAction(String service,BaikeBean baikeBean,String request,Context context) {
        this.baikeBean = baikeBean;
        this.service = service;
        this.request = request;
        this.context = context;
    }

    public void start() {
        if (baikeBean != null && baikeBean.getAnswer()!=null && baikeBean.getAnswer().getText() != null) {
            SpeechRecognizerService.startSpeech(service, baikeBean.getAnswer().getText() ,request);
        }else {
            R4Action r4Action = new R4Action(context,request);
            r4Action.start();
        }
    }
}
