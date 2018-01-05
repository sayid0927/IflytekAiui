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

    private static final String TOUCH_FACE_TIME  = "touch_face_time";
    private static final String TOUCH_FACE_COUNT = "touch_face_count";

    private static final String TOUCH_HAND_TIME  = "touch_hand_time";
    private static final String TOUCH_HAND_COUNT = "touch_hand_count";

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

    public void setSpeechParams(int flag) {
        putInt(SPEECH_PARAMS, flag);
    }

    public int getSpeechParams() {
        return getInt(SPEECH_PARAMS, 0);
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
