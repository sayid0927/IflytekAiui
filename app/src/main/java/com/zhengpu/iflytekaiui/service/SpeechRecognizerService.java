package com.zhengpu.iflytekaiui.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;

import com.blankj.utilcode.utils.Utils;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
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

import static com.zhengpu.iflytekaiui.utils.DeviceUtils.isAccessibilitySettingsOn;


/**
 * ....
 * Created by wengmf on 2017/11/21
 */

public class SpeechRecognizerService extends Service implements IGetVoiceToWord, WakeUpListener, IGetWordToVoice, KuGuoMuiscPlayListener {

    private IflytekWakeUp iflytekWakeUp;
    private static VoiceToWords voiceToWords;
    private static WordsToVoice wordsToVoice;
    private KuGuoMuiscPlayThread kuGuoMuiscPlayThread;
    private String message;

    @Override
    public void onCreate() {
        super.onCreate();


        if (!isAccessibilitySettingsOn(this)) {
           Intent intent=  new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        HermesEventBus.getDefault().register(SpeechRecognizerService.this);
        Utils.init(this);
        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=5a3ca6a9," + SpeechConstant.FORCE_LOGIN + "=true");// 传递科大讯飞appid
        PreferUtil.getInstance().init(this);

        //初始化讯飞语音识别
        voiceToWords = VoiceToWords.getInstance(this);
        voiceToWords.setmIGetVoiceToWord(this);
        voiceToWords.setkuGuoMuiscPlayListener(this);
        wordsToVoice = WordsToVoice.getInstance(this);
        wordsToVoice.setiGetWordToVoice(this);

        iflytekWakeUp = new IflytekWakeUp(this, new MyWakeuperListener(this, this));
        iflytekWakeUp.startWakeuper();
        kuGuoMuiscPlayThread = KuGuoMuiscPlayThread.getInstance(this);

        startSpeech(AppController.LAUNCHER_TEXT, getResources().getString(R.string.launcher_text), getResources().getString(R.string.launcher_text));

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    //获取其他进程的消息 让机器人播报其他线程消息内容
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getChildAppEvent(RequestMessage requestMessage) {
        String reqService =  requestMessage.getService();
        String reqMessage = requestMessage.getMessage();
        startSpeech(reqService,reqMessage, reqMessage);
    }

    /**
     * 语音转文本回调
     */

    @Override
    public void getResult(String service, BaseBean result) {
    }

    /**
     * 用户说话声音太小回调
     */
    @Override
    public void showLowVoice(String result) {

     startSpeech(AppController.SHOWLOWVOICE_TEXT,getResources().getString(R.string.showLowVoice_text),getResources().getString(R.string.showLowVoice_text));

    }

    /**
     * 用户说话结束回调
     */
    @Override
    public void SpeechOver() {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setService(AppController.SPEECH_OVER);
        sendMessage.setMessage("用户说话结束");
        HermesEventBus.getDefault().post(sendMessage);
    }
    /**
     * 用户开始说话回调
     */
    @Override
    public void SpeechStart() {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setService(AppController.SPEECH_START);
        sendMessage.setMessage("用户开始说话");
        HermesEventBus.getDefault().post(sendMessage);
    }
    /**
     * 用户说话错误回调
     */
    @Override
    public void SpeechError(String error) {

    }

    /**
     * 机器人唤醒成功
     */
    @Override
    public void OnWakeUpSuccess() {
        if(kuGuoMuiscPlayThread.isPlay())
            kuGuoMuiscPlayThread.pause();
        startSpeech(AppController.WAKEUP_TEXT,getResources().getString(R.string.wakeup_text),getResources().getString(R.string.wakeup_text));
    }

    /**
     * 机器人唤醒失败
     */
    @Override
    public void OnWakeUpError() {

    }

    /***
     *
     * 机器人语音播报结束回调
     */
    @Override
    public void SpeechEnd(String service) {
        switch (service) {
            case AppController.SHOWLOWVOICE_TEXT:
                voiceToWords.mIatDestroy();
                break;
            case AppController.MUSICX:
                voiceToWords.mIatDestroy();
                KuGuoMuiscPlayThread.getInstance(this).playUrl(PreferUtil.getInstance().getPlayMusicUrl());
                break;
            case AppController.NEWS:
                voiceToWords.mIatDestroy();
                KuGuoMuiscPlayThread.getInstance(this).playUrl(PreferUtil.getInstance().getPlayMusicUrl());
                break;
            case AppController.STORY:
                voiceToWords.mIatDestroy();
                KuGuoMuiscPlayThread.getInstance(this).playUrl(PreferUtil.getInstance().getPlayStoryUrl());
                break;
            default:
                voiceToWords.startRecognizer();
                break;
        }

    }
    /***
     *
     * 机器人语音播报错误回调
     */
    @Override
    public void SpeechError() {

    }

    /***
     *   设置机器人语音播报内容
     * @param service    语义场景
     * @param text         播报内容
     * @param request    发送给其他线程的内容
     */
    public static void startSpeech(String service, String text, String request) {

        voiceToWords.mIatDestroy();
        wordsToVoice.startSynthesizer(service, text);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setService(service);
        sendMessage.setMessage(request);
        HermesEventBus.getDefault().post(sendMessage);

    }

    @Override
    public void KuGuoMuiscPlayPause() {
        setKuGuoMuiscPlayStart(0);
    }

    @Override
    public void KuGuoMuiscPlayReplay() {
        setKuGuoMuiscPlayStart(1);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    // 播放音乐暂停、继续和停止
    private void setKuGuoMuiscPlayStart(int Type) {
        if (Type == 0) {     //暂停
            if (kuGuoMuiscPlayThread.isPlay())
                kuGuoMuiscPlayThread.pause();
        } else {                 // 继续
            kuGuoMuiscPlayThread.start();
        }
    }
}
