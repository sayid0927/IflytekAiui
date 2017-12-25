package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.Context;

import com.zhengpu.iflytekaiui.iflytekbean.NewsBean;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;
import com.zhengpu.iflytekaiui.utils.PreferUtil;

/**
 * sayid ....
 * Created by wengmf on 2017/12/25.
 */

public class NewsAction {


    private Context context;
    private String service;
    private NewsBean newsBean;

    public NewsAction(String service, NewsBean newsBean, Context context) {
        this.context = context;
        this.newsBean = newsBean;
        this.service = service;
    }

    public void start() {
        if (newsBean != null && newsBean.getData() != null && newsBean.getData().getResult().size() != 0 && newsBean.getData().getResult().get(0).getUrl() != null) {
            PreferUtil.getInstance().setPlayMusicUrl(newsBean.getData().getResult().get(0).getUrl());
            SpeechRecognizerService.startSpeech(service, newsBean.getAnswer().getText());
        }
    }
}
