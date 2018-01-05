package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.Context;

import com.blankj.utilcode.utils.ConstUtils;
import com.blankj.utilcode.utils.TimeUtils;
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
    private byte[] bytes;
    private long TouchHeadTime;
    private int TouchHeadCount;
    private long TouchFaceTime;
    private int TouchFaceCount;
    private long TouchHandTime;
    private int TouchHandCount;

    public ReceivedSerialPortDataAction(byte[] bytes, Context context) {

        this.context = context;
        this.bytes = bytes;

        this.TouchHeadTime = PreferUtil.getInstance().getTouchHeadTime(); // 上次的时间戳
        this.TouchHeadCount = PreferUtil.getInstance().getTouchHeadCount();

        this.TouchFaceTime = PreferUtil.getInstance().getTouchFaceTime(); // 上次的时间戳
        this.TouchFaceCount = PreferUtil.getInstance().getTouchFaceCount();

        this.TouchHandTime = PreferUtil.getInstance().getTouchHandTime(); // 上次的时间戳
        this.TouchHandCount = PreferUtil.getInstance().getTouchHandCount();

    }

    public void start() {
        if (bytes != null && bytes.length < 9) {
            if (bytes[0] == 0x5A && bytes[1] == 0x50) {
                switch (bytes[4]) {
                    case 0x01:     //设置    PAD发起，控制设备动作或设置参数。

                        break;
                    case 0x02:   //查询    对实时性不作要求的，通过心跳报上来。PAD下发查询命令，立马回。

                        break;
                    case 0x03:    //心跳   心跳为设备发起，时间间隔默认为10s,(PAD可以根据设置命令来改变心跳时间间隔)。

                        break;
                    case 0x04:  //主动上报   如体感触摸，温度超过上限，有火警，盗警，烟雾报警等。

                        ParserByte();

                        break;

                    case 0x05:  //升级    数据域为XMODEM协议所发内容。

                        break;

                }
            }
        }
    }

    private void ParserByte() {

        if (bytes[6] == 0x01) {  //头顶

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


        } else if (bytes[6] == 0x02 || bytes[6] == 0x03) {  //脸

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

        } else if (bytes[6] == 0x04 || bytes[6] == 0x05) {  //手

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
        } else if (bytes[6] == 0x06) {   //前胸
            SpeechRecognizerService.startSpeech(AppController.TouchFront, context.getResources().getString(R.string.Touch_Front_text), context.getResources().getString(R.string.Touch_Front_text));
        } else if (bytes[6] == 0x07) {   //后背
            SpeechRecognizerService.startSpeech(AppController.TouchBack, context.getResources().getString(R.string.Touch_Back_text), context.getResources().getString(R.string.Touch_Back_text));
        }

//        switch (bytes[6]){
//            case 0x01:  //头顶
//
//                if (TimeUtils.getTimeSpanByNow(TouchHeadTime, ConstUtils.TimeUnit.MIN) < 5) {
//                    switch (TouchHeadCount) {
//                        case 0:
//                            SpeechRecognizerService.startSpeech(AppController.TouchHead,context.getResources().getString(R.string.Touch_Head_text_0),context.getResources().getString(R.string.Touch_Head_text_0));
//                            break;
//                        case 1:
//                            SpeechRecognizerService.startSpeech(AppController.TouchHead,context.getResources().getString(R.string.Touch_Head_text_1),context.getResources().getString(R.string.Touch_Head_text_1));
//                            break;
//                        case 2:
//                            SpeechRecognizerService.startSpeech(AppController.TouchHead,context.getResources().getString(R.string.Touch_Head_text_2),context.getResources().getString(R.string.Touch_Head_text_2));
//                            break;
//                        default:
//                            SpeechRecognizerService.startSpeech(AppController.TouchHead,context.getResources().getString(R.string.Touch_Head_text_0),context.getResources().getString(R.string.Touch_Head_text_0));
//                    }
//                    TouchHeadCount++;
//                    if (TouchHeadCount == 3) TouchHeadCount = 0;
//                    PreferUtil.getInstance().setTouchHeadCount(TouchHeadCount);
//                } else {
//                    SpeechRecognizerService.startSpeech(AppController.TouchHead,context.getResources().getString(R.string.Touch_Head_text_0),context.getResources().getString(R.string.Touch_Head_text_0));
//                    PreferUtil.getInstance().setTouchHeadTime(TimeUtils.getNowTimeMills());
//                    PreferUtil.getInstance().setTouchHeadCount(1);
//                }
//
//                break;
//            case 0x02 :  //左脸
//                SpeechRecognizerService.startSpeech(AppController.TouchLeftFace,context.getResources().getString(R.string.Touch_Face_text_0),context.getResources().getString(R.string.Touch_Face_text_0));
//                break;
//            case 0x03:  //右脸
//                SpeechRecognizerService.startSpeech(AppController.TouchRightFace,context.getResources().getString(R.string.Touch_Face_text_0),context.getResources().getString(R.string.Touch_Face_text_0));
//                break;
//            case 0x04:  // 左手
//                SpeechRecognizerService.startSpeech(AppController.TouchLeftHand,context.getResources().getString(R.string.Touch_Hand_text_0),context.getResources().getString(R.string.Touch_Hand_text_0));
//                break;
//            case 0x05:  // 右手
//                SpeechRecognizerService.startSpeech(AppController.TouchRightHand,context.getResources().getString(R.string.Touch_Hand_text_0),context.getResources().getString(R.string.Touch_Hand_text_0));
//                break;
//            case 0x06:  //前胸
//                SpeechRecognizerService.startSpeech(AppController.TouchFront,context.getResources().getString(R.string.Touch_Front_text),context.getResources().getString(R.string.Touch_Front_text));
//                break;
//            case 0x07:  //后背
//                SpeechRecognizerService.startSpeech(AppController.TouchBack,context.getResources().getString(R.string.Touch_Back_text),context.getResources().getString(R.string.Touch_Back_text));
//                break;
//        }
    }
}
