package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;

/**
 * Created by Administrator on 2017/12/24 0024.
 */

public class BaikeAction {

    private String text;
    private String service;
    private  String request;

    public BaikeAction(String service, String text ,String request) {
        this.text = text;
        this.service = service;
        this.request = request;
    }

    public void start() {
        SpeechRecognizerService.startSpeech(service, text,request);
    }
}
