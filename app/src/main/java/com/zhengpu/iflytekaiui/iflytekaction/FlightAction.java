package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.Context;

import com.zhengpu.iflytekaiui.iflytekbean.DatetimeBean;
import com.zhengpu.iflytekaiui.iflytekbean.FlightBean;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;

/**
 * sayid ....
 * Created by wengmf on 2017/12/28.
 */

public class FlightAction {


    private FlightBean flightBean ;
    private String service;
    private Context context;
    private String  request;


    public FlightAction(String service, FlightBean flightBean, String request, Context context) {
        this.flightBean = flightBean;
        this.service = service;
        this.context = context;
        this.request = request;
    }

    public void start() {
        if(flightBean!=null && flightBean.getAnswer()!=null && flightBean.getAnswer().getText()!=null){
            SpeechRecognizerService.startSpeech(service, flightBean.getAnswer().getText(), request);
        }else {
            R4Action r4Action = new R4Action(context);
            r4Action.start();
        }
    }




}
