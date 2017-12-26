package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.Context;

import com.zhengpu.iflytekaiui.R;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;

/**
 * sayid ....
 * Created by wengmf on 2017/12/26.
 */

public class R4Action {


    private  String service;
    private String  request;
    private Context context;
    public R4Action(String service , String  request, Context context) {
        this.service = service;
        this.request = request;
        this.context =context;
    }

    public void start() {
        SpeechRecognizerService.startSpeech(service,context.getResources().getString(R.string.r4_text),request);
    }

}
