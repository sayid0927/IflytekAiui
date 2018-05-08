package com.zhengpu.iflytekaiui.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.orhanobut.logger.Logger;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;
import com.zhengpu.iflytekaiui.utils.PreferUtil;

import static com.zhengpu.iflytekaiui.service.SpeechRecognizerService.startSpeech;

public class HumanCheckBroadCast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        if (action.equals("com.zp.action.bodyset")) {

            boolean bodyCheck  = intent.getBooleanExtra("body_check",false);
            boolean  faceCheck = intent.getBooleanExtra("face_check",false);

            Logger.e("HumanCheckBroadCast   bodyCheck >>>>  " + bodyCheck);
            Logger.e("HumanCheckBroadCast   face_check >>>>  " + faceCheck);

            PreferUtil.getInstance().setfaceCheck(faceCheck);
            PreferUtil.getInstance().setbodyCheck(bodyCheck);

//            SpeechRecognizerService.bodyCheck=bodyCheck;
//            SpeechRecognizerService.faceCheck = face_check;

        }
    }
}
