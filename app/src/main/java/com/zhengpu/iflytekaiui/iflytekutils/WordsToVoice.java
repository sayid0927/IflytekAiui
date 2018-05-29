package com.zhengpu.iflytekaiui.iflytekutils;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechEvent;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.SynthesizerListener;
import com.iflytek.cloud.util.ResourceUtil;
import com.orhanobut.logger.Logger;
import com.zhengpu.iflytekaiui.utils.PreferUtil;


/**
 * sayid ....
 * Created by wengmf on 2017/11/22.
 * <p>
 * 语音合成，文字转语音
 */

public class WordsToVoice {

    private static String TAG = "WordsToVoice类";
    // 语音合成对象
    public static SpeechSynthesizer mTts;
    private static Context context;
    private static IGetWordToVoice iGetWordToVoice;

    // 函数调用返回值,表示返回结果，失败或成功
    public static int ret = 0;

    public static WordsToVoice wordsToVoice;
    private static String serviceData;

    /**
     * 构造方法。
     */
    public WordsToVoice(Context context) {
        this.context = context;
        mTts = SpeechSynthesizer.createSynthesizer(context, mTtsInitListener);
        //设置参数
//        setParams();
    }

    public void setiGetWordToVoice(IGetWordToVoice iGetWordToVoice) {
        this.iGetWordToVoice = iGetWordToVoice;
    }

    public static synchronized WordsToVoice getInstance(Context context) {
        if (wordsToVoice == null)
            wordsToVoice = new WordsToVoice(context);
        return wordsToVoice;
    }

    /**
     * 开始语音合成
     */
    public static void startSynthesizer(String service, String words) {
        setParams();
        ret = mTts.startSpeaking(words, mTtsListener);
        serviceData = service;
        if (ret != ErrorCode.SUCCESS) {
            // showTip("合成失败,错误码：" + ret);
            Logger.e("合成失败,错误码：" + ret);
        } else {
//            Logger.e("听写成功");
        }
    }

    /**
     * 参数设置
     */
    private static void setParams() {
        //SpeechConstant.SAMPLE_RATE, "16000" 默认的识别采样率支持16000Hz和8000Hz
        // 清空参数
        mTts.setParameter(SpeechConstant.PARAMS, null);
        // 引擎类型
        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
        // 设置在线合成发音人

        // 需下载使用对应的离线合成SDK
//    mTts.setParameter(ResourceUtil.TTS_RES_PATH, getResourcePath());

        mTts.setParameter(SpeechConstant.VOICE_NAME, "nannan");
        mTts.setParameter(SpeechConstant.SPEED, "58");
        mTts.setParameter(SpeechConstant.PITCH, "62");


//        switch (PreferUtil.getInstance().getSpeechParams()) {
//            case 0:    //卡通
//                // 设置在线合成发音人
//                mTts.setParameter(SpeechConstant.VOICE_NAME, "nannan");
//                if (PreferUtil.getInstance().getSpeechSpeed() == 0)
//                    //设置合成语速
//                    mTts.setParameter(SpeechConstant.SPEED, "58");
//                else {
//                    mTts.setParameter(SpeechConstant.SPEED, String.valueOf(PreferUtil.getInstance().getSpeechSpeed()));
//                }
//                //设置合成音调
//                if (PreferUtil.getInstance().getSpeechPictch() == 0)
//                    mTts.setParameter(SpeechConstant.PITCH, "62");
//                else {
//                    mTts.setParameter(SpeechConstant.PITCH, String.valueOf(PreferUtil.getInstance().getSpeechPictch()));
//                }
//                break;
//            case 1:   // 女声音
//                mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");
//                if (PreferUtil.getInstance().getSpeechSpeed() == 0)
//                    //设置合成语速
//                    mTts.setParameter(SpeechConstant.SPEED, "50");
//                else {
//                    mTts.setParameter(SpeechConstant.SPEED, String.valueOf(PreferUtil.getInstance().getSpeechSpeed()));
//                }
//                if (PreferUtil.getInstance().getSpeechPictch() == 0)
//                    mTts.setParameter(SpeechConstant.PITCH, "40");
//                else {
//                    mTts.setParameter(SpeechConstant.PITCH, String.valueOf(PreferUtil.getInstance().getSpeechPictch()));
//                }
//                break;
//            case 2:   //  男声音
//                mTts.setParameter(SpeechConstant.VOICE_NAME, "vixf");
//                if (PreferUtil.getInstance().getSpeechSpeed() == 0)
//                    //设置合成语速
//                    mTts.setParameter(SpeechConstant.SPEED, "52");
//                else {
//                    mTts.setParameter(SpeechConstant.SPEED, String.valueOf(PreferUtil.getInstance().getSpeechSpeed()));
//                }
//                if (PreferUtil.getInstance().getSpeechPictch() == 0)
//                    mTts.setParameter(SpeechConstant.PITCH, "62");
//                else {
//                    mTts.setParameter(SpeechConstant.PITCH, String.valueOf(PreferUtil.getInstance().getSpeechPictch()));
//                }
//                break;
//        }
//        mTts.setParameter(SpeechConstant.SPEED, "50");
//        mTts.setParameter(SpeechConstant.PITCH, "40");

        //设置合成音量
        mTts.setParameter(SpeechConstant.VOLUME, "75");
        //设置播放器音频流类型,参考系统AudioManager.STREAM_MUSIC
        mTts.setParameter(SpeechConstant.STREAM_TYPE, "3");
        // 设置播放合成音频打断音乐播放，默认为true
        mTts.setParameter(SpeechConstant.KEY_REQUEST_FOCUS, "true");
        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        // 注：AUDIO_FORMAT参数语记需要更新版本才能生效
        mTts.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");
        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, context.getFilesDir().getAbsolutePath() + "/tts.wav");

    }

    //判断是否正在播放

    public boolean isTtsSpeaking() {
        return mTts.isSpeaking();
    }

    /**
     * 初始化监听。
     */
    private InitListener mTtsInitListener = new InitListener() {
        @Override
        public void onInit(int code) {
            Log.d(TAG, "InitListener init() code = " + code);
            if (code != ErrorCode.SUCCESS) {
                // showTip("初始化失败,错误码：" + code);
            } else {
                // 初始化成功，之后可以调用startSpeaking方法
                // 注：有的开发者在onCreate方法中创建完合成对象之后马上就调用startSpeaking进行合成，
                // 正确的做法是将onCreate中的startSpeaking调用移至这里
            }
        }
    };

//    private void showTip(final String str) {
//        //mToast.setText(str);
//       // mToast.show();
//    }


    public void mTtsStop() {
        if (mTts != null && mTts.isSpeaking()) {
            mTts.stopSpeaking();
        }
    }

    public void mTtsDestroy() {
        // 退出时释放连接
        if (mTts != null) {
            if (mTts.isSpeaking()) {
                mTts.stopSpeaking();
            }
            mTts.destroy();
        }
    }

    public boolean   isSpeaking(){
        return  mTts.isSpeaking();
    }


    /**
     * 合成回调监听。
     */
    public static SynthesizerListener mTtsListener = new SynthesizerListener() {

        @Override
        public void onSpeakBegin() {
//            Logger.e("语音合成回调监听-----------" + "开始播放");
        }

        @Override
        public void onSpeakPaused() {
//            Logger.e("语音合成回调监听-----------" + "暂停播放");
        }

        @Override
        public void onSpeakResumed() {
//            Logger.e("语音合成回调监听-----------" + "继续播放");
        }

        @Override
        public void onBufferProgress(int percent, int beginPos, int endPos, String info) {
            // 合成进度
//            Logger.e("合成进度-----------" + String.valueOf(percent));
        }

        @Override
        public void onSpeakProgress(int percent, int beginPos, int endPos) {
            // 播放进度
        }

        @Override
        public void onCompleted(SpeechError error) {
            if (error == null) {
//                Logger.e("语音合成回调监听-----------"+"播放完成");
                iGetWordToVoice.SpeechEnd(serviceData);
            } else if (error != null) {
                //showTip(error.getPlainDescription(true));
                Logger.e("语音合成回调监听-------错误----" + error.getPlainDescription(true));
                iGetWordToVoice.SpeechError(error);
            }
        }

        @Override
        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
            // 以下代码用于获取与云端的会话id，当业务出错时将会话id提供给技术支持人员，可用于查询会话日志，定位出错原因
            // 若使用本地能力，会话id为null
            if (SpeechEvent.EVENT_SESSION_ID == eventType) {
                String sid = obj.getString(SpeechEvent.KEY_EVENT_SESSION_ID);
                Log.d(TAG, "session id =" + sid);
            }
        }
    };


    // 获取发音人资源路径
    private static String getResourcePath() {
        StringBuffer tempBuffer = new StringBuffer();
        // 合成通用资源
        tempBuffer.append(ResourceUtil.generateResourcePath(context, ResourceUtil.RESOURCE_TYPE.assets, "tts/common.jet"));
        tempBuffer.append(";");
        // 发音人资源
        tempBuffer.append(ResourceUtil.generateResourcePath(context, ResourceUtil.RESOURCE_TYPE.assets, "tts/xiaoyan.jet"));
        return tempBuffer.toString();
    }
}
