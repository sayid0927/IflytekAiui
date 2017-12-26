package com.zhengpu.iflytekaiui.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.blankj.utilcode.utils.Utils;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.orhanobut.logger.Logger;
import com.zhengpu.iflytekaiui.R;
import com.zhengpu.iflytekaiui.base.AppController;
import com.zhengpu.iflytekaiui.iflytekbean.BaseBean;
import com.zhengpu.iflytekaiui.iflytekutils.IGetVoiceToWord;
import com.zhengpu.iflytekaiui.iflytekutils.IGetWordToVoice;
import com.zhengpu.iflytekaiui.iflytekutils.IflytekWakeUp;
import com.zhengpu.iflytekaiui.iflytekutils.MyWakeuperListener;
import com.zhengpu.iflytekaiui.iflytekutils.VoiceToWords;
import com.zhengpu.iflytekaiui.iflytekutils.WakeUpListener;
import com.zhengpu.iflytekaiui.iflytekutils.WordsToVoice;
import com.zhengpu.iflytekaiui.ipc.entity.RequestMessage;
import com.zhengpu.iflytekaiui.ipc.entity.SendMessage;
import com.zhengpu.iflytekaiui.thread.KuGuoMuiscPlayListener;
import com.zhengpu.iflytekaiui.thread.KuGuoMuiscPlayThread;
import com.zhengpu.iflytekaiui.utils.PreferUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import xiaofei.library.hermeseventbus.HermesEventBus;


/**
 * ....
 * Created by wengmf on 2017/11/21
 */

public class SpeechRecognizerService extends Service implements IGetVoiceToWord, WakeUpListener, IGetWordToVoice, KuGuoMuiscPlayListener {

    private IflytekWakeUp iflytekWakeUp;
    private VoiceToWords voiceToWords;
    private static WordsToVoice wordsToVoice;
    private KuGuoMuiscPlayThread kuGuoMuiscPlayThread;
    private String message;

    @Override
    public void onCreate() {
        super.onCreate();

//        if (!isStartAccessibilityService(getApplicationContext())) {
//           Intent intent=  new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//        }

        HermesEventBus.getDefault().register(SpeechRecognizerService.this);
        Utils.init(this);
        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=5a3ca6a9," + SpeechConstant.FORCE_LOGIN + "=true");// 传递科大讯飞appid
        PreferUtil.getInstance().init(this);

        //初始化讯飞语音识别
        voiceToWords = VoiceToWords.getInstance(this);
        voiceToWords.setmIGetVoiceToWord(this);
        wordsToVoice = WordsToVoice.getInstance(this);
        wordsToVoice.setiGetWordToVoice(this);
        iflytekWakeUp = new IflytekWakeUp(this, new MyWakeuperListener(this, this));
        iflytekWakeUp.startWakeuper();
        kuGuoMuiscPlayThread = KuGuoMuiscPlayThread.getInstance(this);
        kuGuoMuiscPlayThread.setKuGuoMuiscPlayListener(this);

        startSpeech(AppController.LAUNCHER_TEXT, getResources().getString(R.string.launcher_text), getResources().getString(R.string.launcher_text));

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMainAppEvent(RequestMessage requestMessage) {
        String reqService =  requestMessage.getService();
        String reqMessage = requestMessage.getMessage();
        startSpeech(reqService,reqMessage, reqMessage);

    }

    /**
     * 语音转文本回调
     */
    @Override
    public void getResult(String service, BaseBean result) {
        Logger.e(result.toString());
        switch (service) {
            case AppController.R4:    //  听不懂说什么
                wordsToVoice.startSynthesizer(AppController.R4, getResources().getString(R.string.r4_text));
                break;
            case AppController.MUSICX:
                voiceToWords.mIatDestroy();
                KuGuoMuiscPlayThread.getInstance(this).playUrl(PreferUtil.getInstance().getPlayMusicUrl());
                break;
        }
        voiceToWords.startRecognizer();
    }

    /**
     * 说话声音太小回调
     */
    @Override
    public void showLowVoice(String result) {
        wordsToVoice.startSynthesizer(AppController.SHOWLOWVOICE_TEXT, getResources().getString(R.string.showLowVoice_text));
        voiceToWords.mIatDestroy();
    }


    /**
     * 结束说话回调
     */
    @Override
    public void SpeechOver() {

    }

    @Override
    public void SpeechStart() {

    }

    @Override
    public void SpeechError(String error) {

    }

    /**
     * 唤醒成功
     */
    @Override
    public void OnWakeUpSuccess() {
        wordsToVoice.startSynthesizer("ddd", "是的主人");
        voiceToWords.startRecognizer();
    }

    /**
     * 唤醒失败
     */
    @Override
    public void OnWakeUpError() {

    }

    @Override
    public void SpeechEnd(String service) {
        Logger.e(service);
        if (service.equals(AppController.SHOWLOWVOICE_TEXT)) {
            voiceToWords.mIatDestroy();
        } else
            voiceToWords.startRecognizer();
    }

    @Override
    public void SpeechError() {

    }

    public static void startSpeech(String service, String text, String request) {
        wordsToVoice.startSynthesizer(service, text);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setService(service);
        sendMessage.setMessage(request);
        HermesEventBus.getDefault().post(sendMessage);

    }

    @Override
    public void KuGuoMuiscPlayPause() {

    }

    @Override
    public void KuGuoMuiscPlayStop() {

    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
