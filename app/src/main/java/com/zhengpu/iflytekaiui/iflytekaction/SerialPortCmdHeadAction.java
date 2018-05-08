package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.Context;

import com.blankj.utilcode.utils.ConstUtils;
import com.blankj.utilcode.utils.TimeUtils;
import com.orhanobut.logger.Logger;
import com.zhengpu.iflytekaiui.R;
import com.zhengpu.iflytekaiui.base.AppController;
import com.zhengpu.iflytekaiui.base.BaseApplication;
import com.zhengpu.iflytekaiui.ipc.entity.DanceMessage;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;
import com.zhengpu.iflytekaiui.utils.PreferUtil;
import com.zhengpu.iflytekaiui.utils.UDPReceiveUtils;
import com.zhengpu.iflytekaiui.utils.ValueUtil;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import xiaofei.library.hermeseventbus.HermesEventBus;

import static com.zhengpu.iflytekaiui.service.SpeechRecognizerService.stopDanceAction;

/**
 * Created by Administrator on 2017/12/27 0027.
 */

public class SerialPortCmdHeadAction {

    private Context context;
    private byte[] bytes;
    private String[] strData;
    private long TouchHeadTime;
    private int TouchHeadCount;
    private  int count=0;

    private ScheduledFuture scheduledFuture;

    public SerialPortCmdHeadAction(Context context, byte[] bytes, String[] strData) {
        this.context = context;
        this.bytes = bytes;
        this.strData = strData;
        this.TouchHeadTime = PreferUtil.getInstance().getTouchHeadTime(); // 上次的时间戳
        this.TouchHeadCount = PreferUtil.getInstance().getTouchHeadCount();
    }

    public void start() {
        if (bytes.length > 17) {
            Logger.e("唤醒位 》》》  " + strData[17]);
            if (ValueUtil.isBitnTrue(bytes[17], 0)) {
                SpeechRecognizerService.stratWakeUp(context);
                Logger.e("唤醒成功>>>>>>>>>>>>>>>>>    ");
                scheduledFuture = BaseApplication.MAIN_EXECUTOR.scheduleWithFixedDelay(senByte(), 0, 3, TimeUnit.MILLISECONDS);
            }
        }
        touTouch(bytes[2]);
    }

    private Runnable senByte() {
        return new Runnable() {
            @Override
            public void run() {
                if(count <4){
                    SpeechRecognizerService.sendSerialMessageBytes(new byte[]{0x5A, 0x50, 0x05, 0x02, 0x07, 0x01, 0x00,
                            0x00, 0x0A, 0x0D, 0x0A});
                    count++;
                }else {
                    scheduledFuture.cancel(false);
                }
            }
        };
    }

    private void touTouch(byte data) {

        if (ValueUtil.isBitnTrue(data, 0) || ValueUtil.isBitnTrue(data, 1) || ValueUtil.isBitnTrue(data, 2)) {
            if (!SpeechRecognizerService.isIsSpeech() && SpeechRecognizerService.microPhone == 0 && !SpeechRecognizerService.isKuGuoMuiscPlay() && !SpeechRecognizerService.FaceServiceState && !SpeechRecognizerService.isDance ) {
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
                    PreferUtil.getInstance().setTouchHeadTime(TimeUtils.getNowTimeMills());
                    PreferUtil.getInstance().setTouchHeadCount(1);
                    SpeechRecognizerService.startSpeech(AppController.TouchHead, context.getResources().getString(R.string.Touch_Head_text_0), context.getResources().getString(R.string.Touch_Head_text_0));
                }
            }
            if(SpeechRecognizerService.isDance){
                 stopDanceAction();
            }
        }
    }
}
