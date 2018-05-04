/**
 * Copyright(C)2012-2013 深圳市掌星立意科技有限公司版权所有
 * 创 建 人:Jacky
 * 修 改 人:
 * 创 建日期:2013-7-25
 * 描    述:xml储存数据
 * 版 本 号:
 */
package com.zhengpu.iflytekaiui.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.blankj.utilcode.utils.TimeUtils;


public final class PreferUtil {

    public static PreferUtil INSTANCE;
    private static SharedPreferences mPrefer;
    private static final String APP_NAME = "om.zhengpu.zhengpuandroid";

    private static final String PLAY_MUSIC_URL = "play_music_name";
    private static final String PLAY_MUSIC_NAME = "play_music_name";
    private static final String PLAY_VIDEO_NAME = "play_video_name";
    private static final String PLAY_STORY_URL = "play_story_url";
    private static final String R4_SPACE_TIME = "r4_space_time";
    private static final String R4_SPACE_COUNT = "r4_space_count";
    private static final String SPEECH_PARAMS = "speech_params";

    private static final String TOUCH_HEAD_TIME = "touch_head_time";
    private static final String TOUCH_HEAD_COUNT = "touch_head_count";

    private static final String TOUCH_FACE_TIME = "touch_face_time";
    private static final String TOUCH_FACE_COUNT = "touch_face_count";

    private static final String TOUCH_HAND_TIME = "touch_hand_time";
    private static final String TOUCH_HAND_COUNT = "touch_hand_count";
    private static final String INFRARED_TIME = "infrared_time";



    private static final String SHOW_LOW_VOICE_TIME = "show_low_voice_time";
    private static final String SHOW_LOW_VOICE_COUNT = "show_low_voice_count";

    private static final String CONNECT_WIFI = "connect_wifi";
    private static final String CONNECT_WIFI_SSID = "connect_wifi_ssid";

    private static final String EMOJI_CONNECT_STATE= "emoji_connect_state";

    private static final String SPEECH_SPEED= "speech_speed";

    private static final String SPEECH_PITCH= "speech_pitch";

    private static final String ELECTRICITY= "electricity";

    private static final String TEMPERATURE= "temperature";

    private static final String HUMIDITY= "humidity";






    public  void  setEmojiConnectState(boolean flag){
        putBoolean(EMOJI_CONNECT_STATE,flag);
    }

    public  boolean getEmojiConnectState(){
        return  getBoolean(EMOJI_CONNECT_STATE,true);
    }

    public  String getConnectWifiSSID(){
        return  getString(CONNECT_WIFI_SSID,"");
    }

    public void setConnectWifiSSID(String flag){
        putString(CONNECT_WIFI_SSID, flag);
    }

    public  boolean getConnectWifi(){
        return  getBoolean(CONNECT_WIFI,false);
    }

    public void setConnectWifi(boolean flag){
        putBoolean(CONNECT_WIFI, flag);
    }

    public String getPlayMusicName() {
        return getString(PLAY_MUSIC_NAME, "");
    }

    public void setPlayMusicName(String flag) {
        putString(PLAY_MUSIC_NAME, flag);
    }

    public String getPlayMusicUrl() {
        return getString(PLAY_MUSIC_URL, "");
    }

    public void setPlayMusicUrl(String flag) {
        putString(PLAY_MUSIC_URL, flag);
    }

    public void setPlayVideoName(String flag) {
        putString(PLAY_VIDEO_NAME, flag);
    }

    public String getPlayVideoName() {
        return getString(PLAY_VIDEO_NAME, "");
    }

    public void setPlayStoryUrl(String flag) {
        putString(PLAY_STORY_URL, flag);
    }

    public String getPlayStoryUrl() {
        return getString(PLAY_STORY_URL, "");
    }

    public void setR4SpaceTime(long flag) {
        putLong(R4_SPACE_TIME, flag);
    }

    public long getR4SpaceTime() {
        return getLong(R4_SPACE_TIME, TimeUtils.getNowTimeMills());
    }

    public void setTouchHeadTime(long flag) {
        putLong(TOUCH_HEAD_TIME, flag);
    }

    public long getTouchHeadTime() {
        return getLong(TOUCH_HEAD_TIME, TimeUtils.getNowTimeMills());
    }

    public void setTouchHeadCount(int flag) {
        putInt(TOUCH_HEAD_COUNT, flag);
    }

    public int getTouchHeadCount() {
        return getInt(TOUCH_HEAD_COUNT, 0);
    }

    public void setTouchFaceTime(long flag) {
        putLong(TOUCH_FACE_TIME, flag);
    }

    public long getTouchFaceTime() {
        return getLong(TOUCH_FACE_TIME, TimeUtils.getNowTimeMills());
    }

    public void setTouchFaceCount(int flag) {
        putInt(TOUCH_FACE_COUNT, flag);
    }

    public int getTouchFaceCount() {
        return getInt(TOUCH_FACE_COUNT, 0);
    }

    public void setTouchHandTime(long flag) {
        putLong(TOUCH_HAND_TIME, flag);
    }

    public long getInfraredTime() {
        return getLong(INFRARED_TIME, TimeUtils.getNowTimeMills());
    }


    public void setInfraredTime(long flag) {
        putLong(INFRARED_TIME, flag);
    }

    public long getTouchHandTime() {
        return getLong(TOUCH_HAND_TIME, TimeUtils.getNowTimeMills());
    }









    public void setTouchHandCount(int flag) {
        putInt(TOUCH_HAND_COUNT, flag);
    }

    public int getTouchHandCount() {
        return getInt(TOUCH_HAND_COUNT, 0);
    }

    public void setR4SpaceCount(int flag) {
        putInt(R4_SPACE_COUNT, flag);
    }

    public int getR4SpaceCount() {
        return getInt(R4_SPACE_COUNT, 0);
    }

    public void setShowLowVoiceTime(Long flag) {
        putLong(SHOW_LOW_VOICE_TIME, flag);
    }

    public long getShowLowVoiceTime() {
        return getLong(SHOW_LOW_VOICE_TIME, TimeUtils.getNowTimeMills());
    }


    public void setShowLowVoiceCount(int flag) {
        putInt(SHOW_LOW_VOICE_COUNT, flag);
    }

    public int getShowLowVoiceCount() {
        return getInt(SHOW_LOW_VOICE_COUNT, 0);
    }

    public void setSpeechParams(int flag) {
        putInt(SPEECH_PARAMS, flag);
    }

    public int getSpeechParams() {
        return getInt(SPEECH_PARAMS, 0);
    }




    public void setSpeechSpeed(int flag) {
        putInt(SPEECH_SPEED, flag);
    }

    public int getSpeechSpeed() {
        return getInt(SPEECH_SPEED, 0);
    }



    public void setSpeechPitch(int flag) {
        putInt(SPEECH_PITCH, flag);
    }

    public int getSpeechPictch() {
        return getInt(SPEECH_PITCH, 0);
    }

    public void setTemperature(int flag) {
        putInt(TEMPERATURE, flag);
    }

    public int getTemperature() {
        return getInt(TEMPERATURE, 0);
    }

    public void setHumidity(int flag) {
        putInt(HUMIDITY, flag);
    }

    public int getHumidity() {
        return getInt(HUMIDITY, 0);
    }



    public void setbatteryState(String flag) {
        putString(HUMIDITY, flag);
    }

    public String getbatteryState() {
        return getString(HUMIDITY,"");
    }




    public void setElectricity(int flag) {
        putInt(ELECTRICITY, flag);
    }

    public int getElectricity() {
        return getInt(ELECTRICITY, 0);
    }



    private PreferUtil() {
    }

    public static PreferUtil getInstance() {
        if (INSTANCE == null) {
            return new PreferUtil();
        }
        return INSTANCE;
    }

    public void init(Context ctx) {
        mPrefer = ctx.getSharedPreferences(APP_NAME, Context.MODE_PRIVATE
                | Context.MODE_PRIVATE);
        mPrefer.edit().commit();
    }


    public String getString(String key, String defValue) {
        return mPrefer.getString(key, defValue);
    }

    public int getInt(String key, int defValue) {
        return mPrefer.getInt(key, defValue);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return mPrefer.getBoolean(key, defValue);
    }

    public void putString(String key, String value) {
        mPrefer.edit().putString(key, value).commit();
    }

    public void putInt(String key, int value) {
        mPrefer.edit().putInt(key, value).commit();
    }

    public void putBoolean(String key, boolean value) {
        mPrefer.edit().putBoolean(key, value).commit();
    }

    public void putLong(String key, long value) {
        mPrefer.edit().putLong(key, value).commit();
    }

    public long getLong(String key, long defValue) {
        return mPrefer.getLong(key, defValue);
    }

    public void removeKey(String key) {
        mPrefer.edit().remove(key).commit();
    }


}
