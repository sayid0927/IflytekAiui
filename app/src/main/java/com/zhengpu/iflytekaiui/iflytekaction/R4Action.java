package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.Context;

import com.blankj.utilcode.utils.TimeUtils;
import com.zhengpu.iflytekaiui.R;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;
import com.zhengpu.iflytekaiui.utils.PreferUtil;

import java.util.Date;

/**
 * sayid ....
 * Created by wengmf on 2017/12/26.
 */

public class R4Action {


    private String service;
    private String request;
    private Context context;
    private long spaceTime;
    private int spaceCount;

    public R4Action(String service, String request, Context context) {

        this.service = service;
        this.request = request;
        this.context = context;
//        this.spaceTime = PreferUtil.getInstance().getR4SpaceTime();
//        this.spaceCount = PreferUtil.getInstance().getR4SpaceCount();

    }

    public void start() {
        long curTime = (new Date()).getTime();

//        TimeUtils.getIntervalTime();
        SpeechRecognizerService.startSpeech(service, context.getResources().getString(R.string.r4_text), request);
    }
}
