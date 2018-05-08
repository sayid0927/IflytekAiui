package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.Context;

import com.zhengpu.iflytekaiui.iflytekbean.CustomBaikeBean;
import com.zhengpu.iflytekaiui.iflytekbean.DatetimeBean;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;

/**
 * sayid ....
 * Created by wengmf on 2017/12/28.
 */

public class DateTimeAction {


    private  DatetimeBean datetimeBean ;
    private String service;
    private Context context;
    private String  request;

    public DateTimeAction(String service, DatetimeBean datetimeBean, String request, Context context) {
        this.datetimeBean = datetimeBean;
        this.service = service;
        this.context = context;
        this.request = request;
    }

    public void start() {
        if(datetimeBean!=null && datetimeBean.getAnswer()!=null && datetimeBean.getAnswer().getText()!=null){
            SpeechRecognizerService.startSpeech(service, datetimeBean.getAnswer().getText(), request);
        }else {
            R4Action r4Action = new R4Action(context,request);
            r4Action.start();
        }
    }
    }
