package com.zhengpu.iflytekaiui.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.blankj.utilcode.utils.Utils;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.orhanobut.logger.Logger;
import com.unicool.aidlcallback.server.ITaskBinder;
import com.unicool.aidlcallback.server.ITaskCallback;
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
import com.zhengpu.iflytekaiui.thread.KuGuoMuiscPlayListener;
import com.zhengpu.iflytekaiui.thread.KuGuoMuiscPlayThread;
import com.zhengpu.iflytekaiui.utils.PreferUtil;

import java.util.Random;


/**
 * ....
 * Created by wengmf on 2017/11/21
 */

public class SpeechRecognizerService extends Service implements IGetVoiceToWord, WakeUpListener, IGetWordToVoice, KuGuoMuiscPlayListener {

    private IflytekWakeUp iflytekWakeUp;
    private VoiceToWords voiceToWords;
    private static WordsToVoice wordsToVoice;
    private KuGuoMuiscPlayThread kuGuoMuiscPlayThread;

    @Override
    public void onCreate() {
        super.onCreate();

        Logger.e("onCreate" );

//        if (!isStartAccessibilityService(getApplicationContext())) {
//           Intent intent=  new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//        }

        Utils.init(this);
        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=5a3ca6a9,"+ SpeechConstant.FORCE_LOGIN +"=true");// 传递科大讯飞appid
        PreferUtil.getInstance().init(this);
      //初始化讯飞语音识别

        voiceToWords = VoiceToWords.getInstance(this);
        voiceToWords.setmIGetVoiceToWord(this);
        wordsToVoice = WordsToVoice.getInstance(this);
        wordsToVoice.setiGetWordToVoice(this);
        iflytekWakeUp = new IflytekWakeUp(this, new MyWakeuperListener(this, this));
        wordsToVoice.startSynthesizer(AppController.LAUNCHER_TEXT, getResources().getString(R.string.launcher_text));
        iflytekWakeUp.startWakeuper();
        kuGuoMuiscPlayThread = KuGuoMuiscPlayThread.getInstance(this);
        kuGuoMuiscPlayThread.setKuGuoMuiscPlayListener(this);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logger.e("MyService onStartCommand: startId=" + startId);
        return super.onStartCommand(intent, flags, startId);
    }


    /**
     * 语音转文本回调
     */
    @Override
    public void getResult(String service, BaseBean result) {
        Logger.e(result.toString());
        switch (service){
            case  AppController.R4:    //  听不懂说什么
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
        if(service.equals(AppController.SHOWLOWVOICE_TEXT)){
            voiceToWords.mIatDestroy();
        }else
        voiceToWords.startRecognizer();
    }

    @Override
    public void SpeechError() {

    }

    public static void  startSpeech(String  service,String text ){
        wordsToVoice.startSynthesizer(service, text);
    }

    @Override
    public void KuGuoMuiscPlayPause() {

    }

    @Override
    public void KuGuoMuiscPlayStop() {

    }

    final RemoteCallbackList<ITaskCallback> mRcbList = new RemoteCallbackList<>();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("TAG", "MyService onBind: ");
//        callbackClient(-1);
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("TAG", "MyService onUnbind: ");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.e("TAG", "MyService onRebind: ");
    }


    @Override
    public void onDestroy() {
        Log.e("TAG", "MyService onDestroy: ");
        mRcbList.kill();
    }

    private final ITaskBinder.Stub mBinder = new ITaskBinder.Stub() {
        @Override
        public boolean isTaskRunning() {
            Log.e("TAG", "ITaskBinder isTaskRunning: ");
            return false;
        }

        @Override
        public void stopRunningTask() {
            Log.e("TAG", "ITaskBinder stopRunningTask: ");
            stopSelf(); //对应startId，默认值-1
        }

        @Override
        public void registerCallback(ITaskCallback cb) {
            Log.e("TAG", "ITaskBinder registerCallback: " + (cb != null));
            if (cb == null) return;
            mRcbList.register(cb);

            // TODO: 2017/5/5 先注册，后使用
            callbackClient(new Random().nextInt());
        }

        @Override
        public void unregisterCallback(ITaskCallback cb) {
            Log.e("TAG", "ITaskBinder unregisterCallback: " + (cb != null));
            if (cb != null) {
                mRcbList.unregister(cb);
            }
        }
    };


    private void callbackClient(int val) {
        final int N = mRcbList.beginBroadcast();
        Log.e("TAG", "RemoteCallbackList N: " + N);
        for (int i = 0; i < N; i++) {
            try {
                mRcbList.getBroadcastItem(i).actionPerformed(val);
            } catch (RemoteException e) {
                // The RemoteCallbackList will take care of removing
                // the dead object for us.
                Log.e("TAG", "RemoteCallbackList i: " + i);
                Log.e("TAG", "RemoteCallbackList RemoteException: " + e.getMessage());
            }
        }
        mRcbList.finishBroadcast();
    }



}
