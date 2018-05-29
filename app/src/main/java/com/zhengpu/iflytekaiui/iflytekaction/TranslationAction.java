package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.Context;

import com.zhengpu.iflytekaiui.iflytekbean.StockBean;
import com.zhengpu.iflytekaiui.iflytekbean.TranslationBean;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;

/**
 * sayid ....
 * Created by wengmf on 2018/5/10.
 */

public class TranslationAction {

    private Context context;
    private String service;
    private String strRequest;
    private TranslationBean translationBean;

    public TranslationAction(String service, TranslationBean translationBean, String strRequest, Context context) {

        this.translationBean = translationBean;
        this.service = service;
        this.context = context;
        this.strRequest = strRequest;

    }

    public void start() {
        if(translationBean!=null && translationBean.getAnswer().getText()!=null){
            SpeechRecognizerService.startSpeech(service,translationBean.getAnswer().getText(),strRequest);
        }else {
            R4Action r4Action = new R4Action(context, strRequest);
            r4Action.start();
        }
    }
}
