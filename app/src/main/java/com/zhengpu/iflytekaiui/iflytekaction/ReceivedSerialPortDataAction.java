package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.Context;
import android.util.Log;

import com.blankj.utilcode.utils.ConstUtils;
import com.blankj.utilcode.utils.TimeUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.orhanobut.logger.Logger;
import com.zhengpu.iflytekaiui.R;
import com.zhengpu.iflytekaiui.base.AppController;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;
import com.zhengpu.iflytekaiui.utils.PreferUtil;

/**
 * sayid ....
 * Created by wengmf on 2018/1/4.
 */

public class ReceivedSerialPortDataAction {

    private Context context;
    private String[] bytes;
    private byte[] originalBytes;
    private long TouchHeadTime;
    private int TouchHeadCount;
    private long TouchFaceTime;
    private int TouchFaceCount;
    private long TouchHandTime;
    private int TouchHandCount;


    public ReceivedSerialPortDataAction(byte[] originalBytes, String[] bytes, Context context) {

        this.context = context;
        this.bytes = bytes;
        this.originalBytes = originalBytes;

        this.TouchHeadTime = PreferUtil.getInstance().getTouchHeadTime(); // 上次的时间戳
        this.TouchHeadCount = PreferUtil.getInstance().getTouchHeadCount();

        this.TouchFaceTime = PreferUtil.getInstance().getTouchFaceTime(); // 上次的时间戳
        this.TouchFaceCount = PreferUtil.getInstance().getTouchFaceCount();

        this.TouchHandTime = PreferUtil.getInstance().getTouchHandTime(); // 上次的时间戳
        this.TouchHandCount = PreferUtil.getInstance().getTouchHandCount();

    }

    public void start() {
        if (bytes != null) {
            if (bytes[0].equals("5A") && bytes[1].equals("50")) {
                switch (bytes[4]) {
                    case "82":
                        Logger.e(" 查询  >>>   ");
                        break;
                    case "01":     //设置    PAD发起，控制设备动作或设置参数。

                        break;
                    case "02":   //查询    对实时性不作要求的，通过心跳报上来。PAD下发查询命令，立马回。

                        break;
                    case "03":    //心跳   心跳为设备发起，时间间隔默认为10s,(PAD可以根据设置命令来改变心跳时间间隔)。

                        byte b = Byte.valueOf(bytes[3]);
//                        int b1=b<<4&0xf0; //左移4位和 11110000与运算 低位变高位
//                        int b2=b>>>4&0x0f;//右移4位和 00001111与运算 高位变低位

                        int b1 = b >> 4 & 0x0f; //左移4位和 11110000与运算 低位变高位
                        int b2 = b << 4 & 0xf0;//右移4位和 00001111与运算 高位变低位

                        int c = b1 + b2; //高位低位相加得到高地位互换
                        Logger.e("高四位与低四位交换   >>>   " + String.valueOf(c));

                        break;
                    case "04":  //主动上报   如体感触摸，温度超过上限，有火警，盗警，烟雾报警等。

                        ParserByte();

                        break;

                    case "05":  //升级    数据域为XMODEM协议所发内容。

                        break;

                }
            }
        }
    }

    private void ParserByte() {

        String hongwai = function1(originalBytes[7]);
        if (hongwai != null && !hongwai.equals("null") && hongwai.length() >= 2) {
            int i = Integer.parseInt(hongwai.substring(hongwai.length() - 2));
            Logger.e("pir  " + String.valueOf(i));
            switch (i) {
                case 00:  //  前后没人
                    break;
                case 10:   //前面有人
                    SpeechRecognizerService.stratFaceservice(context);
                    break;
                case 01:   //后面有人
                    break;
                case 11:  //  前后有人
                    SpeechRecognizerService.stratFaceservice(context);
                    break;
            }
        }
        if (bytes[9].equals("00")||bytes[9].equals("07")) {  //头顶

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
        } else if (bytes[9].equals("01") || bytes[9].equals("02")) {  //脸

            if (TimeUtils.getTimeSpanByNow(TouchFaceTime, ConstUtils.TimeUnit.MIN) < 5) {
                switch (TouchFaceCount) {
                    case 0:
                        SpeechRecognizerService.startSpeech(AppController.TouchRightFace, context.getResources().getString(R.string.Touch_Face_text_0), context.getResources().getString(R.string.Touch_Face_text_0));
                        break;
                    case 1:
                        SpeechRecognizerService.startSpeech(AppController.TouchRightFace, context.getResources().getString(R.string.Touch_Face_text_1), context.getResources().getString(R.string.Touch_Face_text_1));
                        break;
                    case 2:
                        SpeechRecognizerService.startSpeech(AppController.TouchRightFace, context.getResources().getString(R.string.Touch_Face_text_2), context.getResources().getString(R.string.Touch_Face_text_2));
                        break;
                    default:
                        SpeechRecognizerService.startSpeech(AppController.TouchRightFace, context.getResources().getString(R.string.Touch_Face_text_0), context.getResources().getString(R.string.Touch_Face_text_0));
                }
                TouchFaceCount++;
                if (TouchFaceCount == 3) TouchFaceCount = 0;
                PreferUtil.getInstance().setTouchFaceCount(TouchFaceCount);
            } else {
                SpeechRecognizerService.startSpeech(AppController.TouchRightFace, context.getResources().getString(R.string.Touch_Face_text_0), context.getResources().getString(R.string.Touch_Face_text_0));
                PreferUtil.getInstance().setTouchFaceTime(TimeUtils.getNowTimeMills());
                PreferUtil.getInstance().setTouchFaceCount(1);
            }

        } else if (bytes[9].equals("03") || bytes[9].equals("04")) {  //手

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
        } else if (bytes[9].equals("05")) {   //前胸
            SpeechRecognizerService.startSpeech(AppController.TouchFront, context.getResources().getString(R.string.Touch_Front_text), context.getResources().getString(R.string.Touch_Front_text));
        } else if (bytes[9].equals("06")) {   //后背
            SpeechRecognizerService.startSpeech(AppController.TouchBack, context.getResources().getString(R.string.Touch_Back_text), context.getResources().getString(R.string.Touch_Back_text));
        }
    }

    private static String function1(int n) {
        return Integer.toBinaryString(n);
    }

}
