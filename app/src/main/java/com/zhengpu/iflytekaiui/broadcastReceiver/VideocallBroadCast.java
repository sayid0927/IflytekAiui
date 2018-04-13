package com.zhengpu.iflytekaiui.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.blankj.utilcode.utils.LogUtils;
import com.orhanobut.logger.Logger;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;
import com.zhengpu.iflytekaiui.utils.PreferUtil;

public class VideocallBroadCast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            int microphone = intent.getExtras().getInt("microphone");
            Logger.e("microphone >>>>  " + String.valueOf(microphone));
             //  结束视频通话  microphone = 0
            //   正在视频通话  microphone = 1
            SpeechRecognizerService.microPhone = microphone;

        }
    }
}
