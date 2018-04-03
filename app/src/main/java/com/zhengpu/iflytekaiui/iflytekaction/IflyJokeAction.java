package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.Context;

import com.zhengpu.iflytekaiui.R;
import com.zhengpu.iflytekaiui.iflytekbean.IflyJokeBean;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;

/**
 * sayid ....
 * Created by wengmf on 2017/11/29.
 */

public class IflyJokeAction {


    private Context context;
    private String service;
    private String strRequest;
    private IflyJokeBean jokeBean;

    public IflyJokeAction(String service, IflyJokeBean jokeBean, String strRequest, Context context) {

        this.jokeBean = jokeBean;
        this.service = service;
        this.context = context;
        this.strRequest = strRequest;

    }

    public void start() {

        if(jokeBean!=null){
            if(jokeBean.getData().getResult()!=null&& jokeBean.getData().getResult().size()!=0){
                if(jokeBean.getData().getResult().get(0).getContent()!=null) ;
                SpeechRecognizerService.startSpeech(service, jokeBean.getData().getResult().get(0).getContent(), strRequest);
            }
        }
    }
}
