package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.Context;

import com.zhengpu.iflytekaiui.iflytekbean.StoryBean;
import com.zhengpu.iflytekaiui.iflytekbean.TelephoneBean;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;
import com.zhengpu.iflytekaiui.utils.PreferUtil;

/**
 * sayid ....
 * Created by wengmf on 2018/1/29.
 */

public class TelephoneAction {

    private Context context;
    private String service;
    private TelephoneBean telephoneBean;
    private  String request;

    public TelephoneAction(String service, TelephoneBean telephoneBean, String request, Context context) {

        this.context = context;
        this.service = service;
        this.telephoneBean = telephoneBean;
        this.request =request ;

    }


    public void start() {


    }

}
