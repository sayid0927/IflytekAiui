package com.zhengpu.iflytekaiui.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.orhanobut.logger.Logger;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;
import com.zhengpu.iflytekaiui.utils.PreferUtil;

import static com.zhengpu.iflytekaiui.service.SpeechRecognizerService.sendSerialMessageBytes;
import static com.zhengpu.iflytekaiui.service.SpeechRecognizerService.startSpeech;

public class HumanCheckBroadCast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        if (action.equals("com.zp.action.bodyset")) {

            boolean bodyCheck  = intent.getBooleanExtra("body_check",false);
            boolean  faceCheck = intent.getBooleanExtra("face_check",false);
            int bodyCheckMode = intent.getIntExtra("body_check_mode",0);
            boolean  infraredAvoid = intent.getBooleanExtra("infrared_avoid",true);
            boolean ultrasonicAvoid = intent.getBooleanExtra("ultrasonic_avoid",true);

            Logger.e("HumanCheckBroadCast   bodyCheck >>>>  " + bodyCheck);
            Logger.e("HumanCheckBroadCast   face_check >>>>  " + faceCheck);
            Logger.e("HumanCheckBroadCast   bodyCheckMode >>>>  " + bodyCheckMode);
            Logger.e("HumanCheckBroadCast   infraredAvoid >>>>  " + infraredAvoid);
            Logger.e("HumanCheckBroadCast   ultrasonicAvoid >>>>  " + ultrasonicAvoid);

            PreferUtil.getInstance().setfaceCheck(faceCheck);
            PreferUtil.getInstance().setbodyCheck(bodyCheck);
            PreferUtil.getInstance().setbodyCheckMode(bodyCheckMode);

            if(bodyCheckMode==0 && !PreferUtil.getInstance().getisbodyInfrared()){
                PreferUtil.getInstance().setisbodyInfrared(false);
            }

//            背部板:
//            取消手红外避障(5A 50 05 01 06 00 00 00 07 0D 0A)
//            启动手红外避障(5A 50 05 01 06 01 00 00 08 0D 0A)
//            底部板:
//            取消超声波避障(5A 50 05 03 06 00 00 00 09 0D 0A)
//            启用超声波避障(5A 50 05 03 06 01 00 00 0A 0D 0A)

            if(infraredAvoid){
                sendSerialMessageBytes(new byte[]{0x5A, 0x50, 0x05, 0x01, 0x06, 0x01, 0x00, 0x00, 0x08, 0x0D, 0x0A});
            }else {
                sendSerialMessageBytes(new byte[]{0x5A, 0x50, 0x05, 0x01, 0x06, 0x00, 0x00, 0x00, 0x07, 0x0D, 0x0A});
            }
            if(ultrasonicAvoid){
                sendSerialMessageBytes(new byte[]{0x5A, 0x50, 0x05, 0x03, 0x06, 0x01, 0x00, 0x00, 0x0A, 0x0D, 0x0A});
            }else {
                sendSerialMessageBytes(new byte[]{0x5A, 0x50, 0x05, 0x03, 0x06, 0x00, 0x00, 0x00, 0x09, 0x0D, 0x0A});
            }
        }
    }


}
