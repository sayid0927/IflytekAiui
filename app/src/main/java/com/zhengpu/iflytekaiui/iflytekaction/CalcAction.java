package com.zhengpu.iflytekaiui.iflytekaction;


import com.zhengpu.iflytekaiui.iflytekutils.WordsToVoice;

/**
 * sayid ....
 * Created by wengmf on 2017/11/23.
 */

public class CalcAction {

    private String text;
    private  String service;
    public CalcAction( String service,String text) {
        this.text = text;
    }

    public void start() {
        WordsToVoice.startSynthesizer(service,text);
    }

}
