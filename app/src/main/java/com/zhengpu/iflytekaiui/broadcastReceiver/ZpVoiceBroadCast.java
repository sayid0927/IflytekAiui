package com.zhengpu.iflytekaiui.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.blankj.utilcode.utils.LogUtils;
import com.zhengpu.iflytekaiui.utils.PreferUtil;

import static android.content.Context.MODE_PRIVATE;

public class ZpVoiceBroadCast extends BroadcastReceiver {
    public static final String VOICE_TYPE = "voice_type";  //声音角色   男声 女声 童声
    public static final String PREFS = "zpPrefs";
    public static final String HUNAM_CHECK = "hunam_check";
    public static final String COLLECTION_STRANGERS = "collection_strangers";
    public static final String VOICE_SPEED = "voice_speed";   //  语速
    public static final String VOICE_INTONATION = "voice_intonation";  // 语调
    public static final String ZP_ACTION_VOICE_SET = "zp_action_voice_set";
    public static final String VOICE_OPEN = "voice_open";     // 声音开关  如果关掉不再语音回复
    public static final String VOICE_NAME = "voice_name";  //唤醒词


    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            String voiceType = intent.getExtras().getString(VOICE_TYPE);
            String  voiceName= intent.getExtras().getString(VOICE_NAME);

            float voiceSpeed = intent.getExtras().getFloat(VOICE_SPEED);
            float voiceIntonation = intent.getExtras().getFloat(VOICE_INTONATION);
            boolean voiceOpen = intent.getExtras().getBoolean(VOICE_OPEN);

            LogUtils.e("ZpVoiceBroadCast",String.valueOf(voiceSpeed));
            LogUtils.e("ZpVoiceBroadCast",String.valueOf(voiceIntonation));
            LogUtils.e("ZpVoiceBroadCast",String.valueOf(voiceOpen));
            LogUtils.e("ZpVoiceBroadCast",String.valueOf(voiceType));
            LogUtils.e("ZpVoiceBroadCast",String.valueOf(voiceName));




            if(voiceType!=null){
                switch (voiceType){
                    case  "男声":
                        PreferUtil.getInstance().setSpeechParams(2);
                        break;
                    case "女声":
                        PreferUtil.getInstance().setSpeechParams(1);
                        break;
                    case "童声":
                        PreferUtil.getInstance().setSpeechParams(0);
                        break;
                }
            }
            if(voiceName!=null){

            }
            if(voiceSpeed!=0){
                PreferUtil.getInstance().setSpeechSpeed((int)voiceSpeed);
            }
            if(voiceIntonation!=0){
                PreferUtil.getInstance().setSpeechPitch((int)voiceIntonation);
            }

        }
    }
}
