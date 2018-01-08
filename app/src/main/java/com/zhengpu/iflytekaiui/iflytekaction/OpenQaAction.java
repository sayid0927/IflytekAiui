package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.Context;

import com.zhengpu.iflytekaiui.iflytekbean.CalcBean;
import com.zhengpu.iflytekaiui.iflytekbean.OpenQABean;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;

/**
 * sayid ....
 * Created by wengmf on 2017/12/28.
 */

public class OpenQaAction {


    private String text;
    private String service;
    private String request;
    private OpenQABean openQABean;
    private Context context;

    public OpenQaAction(String service, OpenQABean openQABean, String request, Context context) {

        this.openQABean = openQABean;
        this.service = service;
        this.request = request;
        this.context = context;

    }

    public void start() {
        if (openQABean != null && openQABean.getAnswer() != null) {
            SpeechRecognizerService.startSpeech(service, openQABean.getAnswer().getText(), request);
        }else {
            R4Action r4Action = new R4Action(context,request);
            r4Action.start();
        }
    }
}
