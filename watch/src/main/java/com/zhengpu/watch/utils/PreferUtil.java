/**
 * Copyright(C)2012-2013 深圳市掌星立意科技有限公司版权所有
 * 创 建 人:Jacky
 * 修 改 人:
 * 创 建日期:2013-7-25
 * 描    述:xml储存数据
 * 版 本 号:
 */
package com.zhengpu.watch.utils;

import android.content.Context;
import android.content.SharedPreferences;


public final class PreferUtil {

    public static PreferUtil INSTANCE;
    private static SharedPreferences mPrefer;
    private static final String APP_NAME = "com.zeunpro.watch";

    private static final String PLAY_MUSIC_NAME = "play_music_name";
    private static final String PLAY_VIDEO_NAME = "play_video_name";

    private static final String PLAY_STORY_URL = "play_story_url";
    private static final String PLAY_STORY_LYRICS = "play_story_lyrics";


    public String getPlayMusicName() {
        return getString(PLAY_MUSIC_NAME, "");
    }

    public void setPlayMusicName(String flag) {
        putString(PLAY_MUSIC_NAME, flag);
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


    public void setPlayStoryLyrics(String flag) {
        putString(PLAY_STORY_LYRICS, flag);
    }


    public String getPlayStoryLyrics() {
        return getString(PLAY_STORY_LYRICS, "");
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
