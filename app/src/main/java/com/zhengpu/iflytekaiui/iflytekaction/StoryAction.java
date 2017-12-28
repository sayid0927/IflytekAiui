package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.Context;

import com.zhengpu.iflytekaiui.iflytekbean.StoryBean;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;
import com.zhengpu.iflytekaiui.utils.PreferUtil;

/**
 * sayid ....
 * Created by wengmf on 2017/12/25.
 */

public class StoryAction {

    private Context context;
    private String service;
    private StoryBean storyBean;
    private  String request;

    public StoryAction(String service, StoryBean storyBean, String request, Context context) {
        this.context = context;
        this.service = service;
        this.storyBean = storyBean;
        this.request =request ;
    }

    public void start() {
        if (storyBean != null && storyBean.getData() != null &&
              storyBean.getData().getResult()!=null &&  storyBean.getData().getResult().size() != 0 &&
                storyBean.getData().getResult().get(0).getName() != null && storyBean.getData().getResult().get(0).getPlayUrl() != null) {
            PreferUtil.getInstance().setPlayStoryUrl(storyBean.getData().getResult().get(0).getPlayUrl());
            SpeechRecognizerService.startSpeech(service, "请欣赏" + storyBean.getData().getResult().get(0).getName(),request);
        }
    }
}
