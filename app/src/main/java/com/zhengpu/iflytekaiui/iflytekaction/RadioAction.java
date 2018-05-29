package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.Context;
import android.nfc.cardemulation.CardEmulation;

import com.zhengpu.iflytekaiui.R;
import com.zhengpu.iflytekaiui.iflytekbean.JokeBean;
import com.zhengpu.iflytekaiui.iflytekbean.RadioBean;
import com.zhengpu.iflytekaiui.iflytekbean.otherbean.TianJokeBean;
import com.zhengpu.iflytekaiui.iflytekutils.JsonParser;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;
import com.zhengpu.iflytekaiui.utils.PreferUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * sayid ....
 * Created by wengmf on 2018/5/10.
 */

public class RadioAction {


    private Context context;
    private String service;
    private String strRequest;
    private RadioBean radioBean;

    public RadioAction(String service, RadioBean radioBean, String strRequest, Context context) {

        this.radioBean = radioBean;
        this.service = service;
        this.context = context;
        this.strRequest = strRequest;

    }

    public void start() {

        if(radioBean!=null && radioBean.getAnswer().getText()!=null && radioBean.getData().getResult().size()!=0){
            PreferUtil.getInstance().setPlayRadioUrl(radioBean.getData().getResult().get(0).getUrl());
            SpeechRecognizerService.startSpeech(service,radioBean.getAnswer().getText(),strRequest);
        }else {
            R4Action r4Action = new R4Action(context, strRequest);
            r4Action.start();
        }
    }
}
