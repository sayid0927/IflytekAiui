package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.Context;

import com.blankj.utilcode.utils.ConstUtils;
import com.blankj.utilcode.utils.TimeUtils;
import com.orhanobut.logger.Logger;
import com.zhengpu.iflytekaiui.R;
import com.zhengpu.iflytekaiui.base.AppController;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;
import com.zhengpu.iflytekaiui.utils.PreferUtil;

import java.util.Date;

/**
 * sayid ....
 * Created by wengmf on 2017/12/26.
 */

public class R4Action {

    private Context context;
    private long spaceTime;
    private int spaceCount;
    private String request;

    public R4Action(Context context,String request) {
        this.context = context;
        this.request =request;
        this.spaceTime = PreferUtil.getInstance().getR4SpaceTime(); // 上次的时间戳
        this.spaceCount = PreferUtil.getInstance().getR4SpaceCount();
    }

    public void start() {
        // 与之前的时间戳对比如果小于5分钟
        if (TimeUtils.getTimeSpanByNow(spaceTime, ConstUtils.TimeUnit.MIN) < 5) {
            switch (spaceCount) {
                case 0:
                    SpeechRecognizerService.startSpeech(AppController.R4_0, context.getResources().getString(R.string.r4_0_text), request);

                    break;
                case 1:
                    SpeechRecognizerService.startSpeech(AppController.R4_1, context.getResources().getString(R.string.r4_1_text), request);
                    break;
                case 2:
                    SpeechRecognizerService.startSpeech(AppController.R4_2, context.getResources().getString(R.string.r4_2_text),request);
                    break;
                default:
                    SpeechRecognizerService.startSpeech(AppController.R4_0, context.getResources().getString(R.string.r4_0_text), request);
            }
            spaceCount++;
            if (spaceCount == 3) spaceCount = 0;
            PreferUtil.getInstance().setR4SpaceCount(spaceCount);
        } else {
            SpeechRecognizerService.startSpeech(AppController.R4_0, context.getResources().getString(R.string.r4_0_text), request);
            PreferUtil.getInstance().setR4SpaceTime(TimeUtils.getNowTimeMills());
            PreferUtil.getInstance().setR4SpaceCount(1);
        }
    }
}
