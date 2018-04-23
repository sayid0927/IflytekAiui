package com.zhengpu.iflytekaiui.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.orhanobut.logger.Logger;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;

import static com.zhengpu.iflytekaiui.service.SpeechRecognizerService.startFaceserSpeech;

public class StratFaceserBroadCast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (/*intent != null*/action.equals("com.zeunpro.login.FaceRecognitionService")) {
            String request_text = intent.getStringExtra("request_text");
            String request_state = intent.getStringExtra("request_state");

            Logger.e("request >>>>  " + request_text);
            Logger.e("request >>>>  " + request_state);

            if(request_state.equals("AAAAA") && request_text!=null && !request_text.equals("")){
                startFaceserSpeech("StratFaceser", request_text, request_text);
            }
            if (!request_state.equals("")) {
                SpeechRecognizerService.FaceServiceState = request_state.equals("state_start");
            }
        }
    }
}
