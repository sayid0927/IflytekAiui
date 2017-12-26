package com.zhengpu.iflytekaiui.iflytekaction;


import com.zhengpu.iflytekaiui.iflytekbean.BaseBean;
import com.zhengpu.iflytekaiui.iflytekutils.WordsToVoice;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;

/**
 * sayid ....
 * Created by wengmf on 2017/11/23.
 */

public class CalcAction {

    private String text;
    private  String service;
    private String  request;
    public CalcAction(String service, String text, String  request) {
        this.text = text;
        this.service = service;
        this.request = request;
    }

    public void start() {
        SpeechRecognizerService.startSpeech(service,text,request);
    }
}
