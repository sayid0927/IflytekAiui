package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.Context;

import com.zhengpu.iflytekaiui.iflytekbean.FaceserviceBean;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;

/**
 * sayid ....
 * Created by wengmf on 2018/3/16.
 */

public class FaceserviceAction  {


    private FaceserviceBean faceserviceBean ;
    private String service;
    private Context context;
    private String  request;

    public FaceserviceAction(String service, FaceserviceBean faceserviceBean, String request, Context context) {
        this.faceserviceBean = faceserviceBean;
        this.service = service;
        this.context = context;
        this.request = request;
    }

    public void start() {
        SpeechRecognizerService.stratFaceservice(context);
    }
}
