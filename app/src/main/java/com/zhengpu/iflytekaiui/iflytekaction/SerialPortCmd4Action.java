package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.Context;

import com.blankj.utilcode.utils.ConstUtils;
import com.blankj.utilcode.utils.TimeUtils;
import com.zhengpu.iflytekaiui.R;
import com.zhengpu.iflytekaiui.base.AppController;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;
import com.zhengpu.iflytekaiui.utils.PreferUtil;
import com.zhengpu.iflytekaiui.utils.ValueUtil;

/**
 * Created by Administrator on 2017/12/27 0027.
 */

public class SerialPortCmd4Action {

    private Context context;
    private byte[] bytes;
    private long TouchHeadTime;
    private int TouchHeadCount;
    private long TouchHandTime;
    private int TouchHandCount;

    public SerialPortCmd4Action(Context context, byte[] bytes) {
        this.context = context;
        this.bytes = bytes;
        this.TouchHeadTime = PreferUtil.getInstance().getTouchHeadTime(); // 上次的时间戳
        this.TouchHeadCount = PreferUtil.getInstance().getTouchHeadCount();
        this.TouchHandTime = PreferUtil.getInstance().getTouchHandTime(); // 上次的时间戳
        this.TouchHandCount = PreferUtil.getInstance().getTouchHandCount();
    }

    public void start() {

            touTouch(bytes[2]);
            bodyTouch(bytes[3]);
            bodyinfrared(bytes[5]);

    }

    private void touTouch(byte data) {
        if (ValueUtil.isBitnTrue(data, 0) || ValueUtil.isBitnTrue(data, 1) || ValueUtil.isBitnTrue(data, 2)) {
            // 摸头
            if (TimeUtils.getTimeSpanByNow(TouchHeadTime, ConstUtils.TimeUnit.MIN) < 5) {
                switch (TouchHeadCount) {
                    case 0:
                        SpeechRecognizerService.startSpeech(AppController.TouchHead, context.getResources().getString(R.string.Touch_Head_text_0), context.getResources().getString(R.string.Touch_Head_text_0));
                        break;
                    case 1:
                        SpeechRecognizerService.startSpeech(AppController.TouchHead, context.getResources().getString(R.string.Touch_Head_text_1), context.getResources().getString(R.string.Touch_Head_text_1));
                        break;
                    case 2:
                        SpeechRecognizerService.startSpeech(AppController.TouchHead, context.getResources().getString(R.string.Touch_Head_text_2), context.getResources().getString(R.string.Touch_Head_text_2));
                        break;
                    default:
                        SpeechRecognizerService.startSpeech(AppController.TouchHead, context.getResources().getString(R.string.Touch_Head_text_0), context.getResources().getString(R.string.Touch_Head_text_0));
                }
                TouchHeadCount++;
                if (TouchHeadCount == 3) TouchHeadCount = 0;
                PreferUtil.getInstance().setTouchHeadCount(TouchHeadCount);
            } else {
                SpeechRecognizerService.startSpeech(AppController.TouchHead, context.getResources().getString(R.string.Touch_Head_text_0), context.getResources().getString(R.string.Touch_Head_text_0));
                PreferUtil.getInstance().setTouchHeadTime(TimeUtils.getNowTimeMills());
                PreferUtil.getInstance().setTouchHeadCount(1);
            }
        }
    }

    private void bodyTouch(byte data) {
        if (ValueUtil.isBitnTrue(data, 0) || ValueUtil.isBitnTrue(data, 1) || ValueUtil.isBitnTrue(data, 2) || ValueUtil.isBitnTrue(data, 3)) {
            // 摸手
            if (TimeUtils.getTimeSpanByNow(TouchHandTime, ConstUtils.TimeUnit.MIN) < 5) {
                switch (TouchHandCount) {
                    case 0:
                        SpeechRecognizerService.startSpeech(AppController.TouchRightHand, context.getResources().getString(R.string.Touch_Hand_text_0), context.getResources().getString(R.string.Touch_Hand_text_0));
                        break;
                    case 1:
                        SpeechRecognizerService.startSpeech(AppController.TouchRightHand, context.getResources().getString(R.string.Touch_Hand_text_1), context.getResources().getString(R.string.Touch_Hand_text_1));
                        break;
                    case 2:
                        SpeechRecognizerService.startSpeech(AppController.TouchRightHand, context.getResources().getString(R.string.Touch_Hand_text_2), context.getResources().getString(R.string.Touch_Hand_text_2));
                        break;
                    default:
                        SpeechRecognizerService.startSpeech(AppController.TouchRightHand, context.getResources().getString(R.string.Touch_Hand_text_0), context.getResources().getString(R.string.Touch_Hand_text_0));
                }
                TouchHandCount++;
                if (TouchHandCount == 3) TouchHandCount = 0;
                PreferUtil.getInstance().setTouchHandCount(TouchHandCount);
            } else {
                SpeechRecognizerService.startSpeech(AppController.TouchRightHand, context.getResources().getString(R.string.Touch_Hand_text_0), context.getResources().getString(R.string.Touch_Hand_text_0));
                PreferUtil.getInstance().setTouchHandTime(TimeUtils.getNowTimeMills());
                PreferUtil.getInstance().setTouchHandCount(1);
            }
        }
    }

    private void bodyinfrared(byte data) {
        if (ValueUtil.isBitnTrue(data, 0)) {
            SpeechRecognizerService.stratFaceservice(context);
        }
    }
}
