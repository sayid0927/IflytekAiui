package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.Context;

import com.zhengpu.iflytekaiui.iflytekbean.CalcBean;
import com.zhengpu.iflytekaiui.iflytekbean.PoetryBean;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;

/**
 * sayid ....
 * Created by wengmf on 2017/12/28.
 */

public class PoetryAction {

    private String text;
    private String service;
    private String request;
    private PoetryBean poetryBean;
    private Context context;

    public PoetryAction(String service, PoetryBean poetryBean, String request, Context context) {

        this.poetryBean = poetryBean;
        this.service = service;
        this.request = request;
        this.context = context;

    }

    public void start() {

//        if (poetryBean != null && poetryBean.getData() != null &&
//              poetryBean.getData().getResult()!=null &&  poetryBean.getData().getResult().size() != 0 &&
//                poetryBean.getData().getResult().get(0).getTitle() != null &&
//                poetryBean.getData().getResult().get(0).getShowContent() != null) {

        if (poetryBean != null && poetryBean.getAnswer() != null && poetryBean.getAnswer().getText() != null) {
            SpeechRecognizerService.startSpeech(service, poetryBean.getAnswer().getText(), request);
        } else {
            R4Action r4Action = new R4Action(context,request);
            r4Action.start();
        }
    }
}
