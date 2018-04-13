package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.Context;

import com.blankj.utilcode.utils.ConstUtils;
import com.blankj.utilcode.utils.TimeUtils;
import com.orhanobut.logger.Logger;
import com.zhengpu.iflytekaiui.R;
import com.zhengpu.iflytekaiui.base.AppController;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;
import com.zhengpu.iflytekaiui.utils.PreferUtil;
import com.zhengpu.iflytekaiui.utils.ValueUtil;

/**
 * Created by Administrator on 2017/12/27 0027.
 */

public class SerialPortCmdBackgroundAction {

    private Context context;
    private byte[] bytes;
    private long TouchHandTime;
    private int TouchHandCount;
    private String[] strData;

    public SerialPortCmdBackgroundAction(Context context, byte[] bytes, String[] strData) {
        this.context = context;
        this.bytes = bytes;
        this.strData = strData;
        this.TouchHandTime = PreferUtil.getInstance().getTouchHandTime(); // 上次的时间戳
        this.TouchHandCount = PreferUtil.getInstance().getTouchHandCount();
    }

    public void start() {

//        bodyinfrared(bytes[5]);
        bodyTouch(bytes[3]);

    }

    private void bodyTouch(byte data) {
        if (SpeechRecognizerService.Service != null && !SpeechRecognizerService.Service.equals(AppController.TouchRightHand) && !SpeechRecognizerService.Service.equals(AppController.TouchHead)) {
            if (ValueUtil.isBitnTrue(data, 0) || ValueUtil.isBitnTrue(data, 1) || ValueUtil.isBitnTrue(data, 2) || ValueUtil.isBitnTrue(data, 3)) {
                // 摸手
                if (SpeechRecognizerService.touch_V != 1) {
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
                    SpeechRecognizerService.touch_V = 1;
                    SpeechRecognizerService.Service =AppController.TouchRightHand;
                }
            }else {
                SpeechRecognizerService.touch_V=0;
            }
        }
    }

    private void bodyinfrared(byte data) {

        if (ValueUtil.isBitnTrue(data, 0) || ValueUtil.isBitnTrue(data, 1)) {
            Logger.e(String.valueOf(SpeechRecognizerService.pirV));
            Logger.e(strData[5]);
            if (SpeechRecognizerService.pirV != 1) {
                Logger.e("人体红外触发>>>>>>>>>>>>>>>>>>>>>>>>>>>>   ");
                SpeechRecognizerService.stratWakeUp(context);
                SpeechRecognizerService.pirV = 1;
            }
        } else {
            SpeechRecognizerService.pirV = 0;
        }
    }

}
