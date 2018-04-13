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

public class SerialPortCmdHeadAction {

    private Context context;
    private byte[] bytes;
    private String[] strData;
    private long TouchHeadTime;
    private int TouchHeadCount;

    public SerialPortCmdHeadAction(Context context, byte[] bytes, String[] strData) {
        this.context = context;
        this.bytes = bytes;
        this.strData = strData;
        this.TouchHeadTime = PreferUtil.getInstance().getTouchHeadTime(); // 上次的时间戳
        this.TouchHeadCount = PreferUtil.getInstance().getTouchHeadCount();
    }

    public void start() {

        if (strData.length >= 17) {
            String wak = strData[17];
//            Logger.e("科大讯飞唤醒标志>>>  " + wak);
            if (wak.equals("01")) {
                SpeechRecognizerService.stratWakeUp(context);
            }
        }

        touTouch(bytes[2]);
    }

    private void touTouch(byte data) {
        Logger.e("摸头>>>     " + strData[2]);
        Logger.e("摸头>>>     " + SpeechRecognizerService.Service);

        if (!SpeechRecognizerService.Service.equals(AppController.TouchHead) && !SpeechRecognizerService.Service.equals(AppController.TouchRightHand)) {
            if (ValueUtil.isBitnTrue(data, 0) || ValueUtil.isBitnTrue(data, 1) || ValueUtil.isBitnTrue(data, 2)) {
                // 摸头
                if (SpeechRecognizerService.touch_V != 1) {
                    if (TimeUtils.getTimeSpanByNow(TouchHeadTime, ConstUtils.TimeUnit.MIN) < 5) {
                        switch (TouchHeadCount) {
                            case 0:
                                SpeechRecognizerService.startSpeech(AppController.TouchHead, context.getResources().getString(R.string.Touch_Head_text_0), context.getResources().getString(R.string.Touch_Head_text_0));
                                SpeechRecognizerService.touch_V = 1;
                                SpeechRecognizerService.Service = AppController.TouchHead;
                                break;
                            case 1:
                                SpeechRecognizerService.startSpeech(AppController.TouchHead, context.getResources().getString(R.string.Touch_Head_text_1), context.getResources().getString(R.string.Touch_Head_text_1));
                                SpeechRecognizerService.touch_V = 1;
                                SpeechRecognizerService.Service = AppController.TouchHead;
                                break;
                            case 2:
                                SpeechRecognizerService.startSpeech(AppController.TouchHead, context.getResources().getString(R.string.Touch_Head_text_2), context.getResources().getString(R.string.Touch_Head_text_2));
                                SpeechRecognizerService.touch_V = 1;
                                SpeechRecognizerService.Service = AppController.TouchHead;
                                break;
                            default:
                                SpeechRecognizerService.startSpeech(AppController.TouchHead, context.getResources().getString(R.string.Touch_Head_text_0), context.getResources().getString(R.string.Touch_Head_text_0));
                                SpeechRecognizerService.touch_V = 1;
                                SpeechRecognizerService.Service = AppController.TouchHead;
                        }
                        TouchHeadCount++;
                        if (TouchHeadCount == 3) TouchHeadCount = 0;
                        PreferUtil.getInstance().setTouchHeadCount(TouchHeadCount);
                    } else {
                        SpeechRecognizerService.startSpeech(AppController.TouchHead, context.getResources().getString(R.string.Touch_Head_text_0), context.getResources().getString(R.string.Touch_Head_text_0));
                        PreferUtil.getInstance().setTouchHeadTime(TimeUtils.getNowTimeMills());
                        PreferUtil.getInstance().setTouchHeadCount(1);
                        SpeechRecognizerService.touch_V = 1;
                        SpeechRecognizerService.Service = AppController.TouchHead;
                    }
                } else {
                    SpeechRecognizerService.touch_V = 0;
                    SpeechRecognizerService.Service = "AA";
                }
            } else {
                SpeechRecognizerService.touch_V = 0;
                SpeechRecognizerService.Service = "AA";
            }
        }
    }
}
