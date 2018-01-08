package com.zhengpu.iflytekaiui.iflytekaction;


import android.content.Context;

import com.zhengpu.iflytekaiui.iflytekbean.BaseBean;
import com.zhengpu.iflytekaiui.iflytekbean.CalcBean;
import com.zhengpu.iflytekaiui.iflytekutils.WordsToVoice;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;

/**
 * sayid ....
 * Created by wengmf on 2017/11/23.
 */

public class CalcAction {

    private String text;
    private String service;
    private String request;
    private CalcBean calcBean;
    private Context context;

    public CalcAction(String service, CalcBean calcBean, String request, Context context) {

        this.calcBean = calcBean;
        this.service = service;
        this.request = request;
        this.context = context;

    }

    public void start() {

        if (calcBean != null && calcBean.getAnswer() != null && calcBean.getAnswer().getText() != null) {
            String operation = calcBean.getOperation();
            switch (operation) {
                case "ANSWER":
                    SpeechRecognizerService.startSpeech(service, calcBean.getAnswer().getText(), request);
                    break;
            }
        }else {
            R4Action r4Action = new R4Action(context,request);
            r4Action.start();
        }
    }
}
