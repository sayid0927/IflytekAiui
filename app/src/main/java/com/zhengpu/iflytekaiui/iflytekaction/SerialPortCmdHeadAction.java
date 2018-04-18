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
        if (bytes.length > 17) {
            Logger.e("唤醒位 》》》  "+ strData[17]);
            if (ValueUtil.isBitnTrue(bytes[17], 0)) {
                SpeechRecognizerService.stratWakeUp(context);
                Logger.e("唤醒成功>>>>>>>>>>>>>>>>>    ");
            }
        }
        touTouch(bytes[2]);
    }

    private void touTouch(byte data) {
        if (ValueUtil.isBitnTrue(data, 0) || ValueUtil.isBitnTrue(data, 1) || ValueUtil.isBitnTrue(data, 2)) {
            if (!SpeechRecognizerService.isSpeech) {
                SpeechRecognizerService.isSpeech = true;
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
                    SpeechRecognizerService.isSpeech = true;
                    PreferUtil.getInstance().setTouchHeadTime(TimeUtils.getNowTimeMills());
                    PreferUtil.getInstance().setTouchHeadCount(1);
                    SpeechRecognizerService.startSpeech(AppController.TouchHead, context.getResources().getString(R.string.Touch_Head_text_0), context.getResources().getString(R.string.Touch_Head_text_0));
                }
            }
        }
    }
}
