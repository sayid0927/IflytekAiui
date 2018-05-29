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
    private long infraredTime;
    private String[] strData;

    public SerialPortCmdBackgroundAction(Context context, byte[] bytes, String[] strData) {
        this.context = context;
        this.bytes = bytes;
        this.strData = strData;
        this.TouchHandTime = PreferUtil.getInstance().getTouchHandTime(); // 上次的时间戳
        this.infraredTime = PreferUtil.getInstance().getInfraredTime();
        this.TouchHandCount = PreferUtil.getInstance().getTouchHandCount();

    }

    public void start() {
//        Logger.e(" 红外 >>>    " + strData[5]);

        if(PreferUtil.getInstance().getbodyCheckMode()==0){
            bodyinfrareds(bytes[5]);
        }else {
            bodyinfrared(bytes[5]);
        }

//        if (PreferUtil.getInstance().getbodyCheck()) {
//            bodyinfrared(bytes[5]);
//        }
//        else  if(){
//            bodyinfrareds(bytes[5]);
//        }

        bodyTouch(bytes[3]);
    }

    private void bodyTouch(byte data) {
        // 摸手
        if (ValueUtil.isBitnTrue(data, 0) || ValueUtil.isBitnTrue(data, 1) || ValueUtil.isBitnTrue(data, 2) || ValueUtil.isBitnTrue(data, 3)) {
            if (!SpeechRecognizerService.isIsSpeech() && SpeechRecognizerService.microPhone == 0 && !SpeechRecognizerService.isKuGuoMuiscPlay() && !SpeechRecognizerService.FaceServiceState && !SpeechRecognizerService.isDance) {
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
                    PreferUtil.getInstance().setTouchHandTime(TimeUtils.getNowTimeMills());
                    PreferUtil.getInstance().setTouchHandCount(1);
                    SpeechRecognizerService.startSpeech(AppController.TouchRightHand, context.getResources().getString(R.string.Touch_Hand_text_0), context.getResources().getString(R.string.Touch_Hand_text_0));
                }
            }
        }
    }

    private void bodyinfrareds(byte data) {
//        Logger.e(" 红外 >>>    " + strData[5]);
        if (strData[5].equals("01") && PreferUtil.getInstance().getisbodyInfrared()) {
//            Logger.e(" 红外  触发 >>>    " );
            PreferUtil.getInstance().setisbodyInfrared(false);
            SpeechRecognizerService.stratInfrared(context);
            SpeechRecognizerService.pirV = 1;
            PreferUtil.getInstance().setInfraredTime(TimeUtils.getNowTimeMills());
        }
    }

    private void bodyinfrared(byte data) {
//        Logger.e(" 红外 >>>    " + strData[5]);
        if (strData[5].equals("01")) {
            if (TimeUtils.getTimeSpanByNow(infraredTime, ConstUtils.TimeUnit.MIN) > 1) {
//                Logger.e(" 红外  触发 >>>    " );
                SpeechRecognizerService.stratInfrared(context);
                SpeechRecognizerService.pirV = 1;
                PreferUtil.getInstance().setInfraredTime(TimeUtils.getNowTimeMills());

            } else {
                if (SpeechRecognizerService.isPir) {
                    SpeechRecognizerService.isPir = false;
                    SpeechRecognizerService.stratInfrared(context);
                    PreferUtil.getInstance().setInfraredTime(TimeUtils.getNowTimeMills());
                }
            }
        } else {
            SpeechRecognizerService.pirV = 0;
        }
    }
}
