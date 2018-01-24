package com.zhengpu.iflytekaiui.utils;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.media.AudioManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.TextUtils;

import com.zhengpu.iflytekaiui.contentprovider.PlayController;
import com.zhengpu.iflytekaiui.contentprovider.PlayProvider;
import com.zhengpu.iflytekaiui.iflytekbean.AllAudioSongBean;
import com.zhengpu.iflytekaiui.service.MyAccessibilityService;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static android.content.Context.AUDIO_SERVICE;

/**
 * Created by zs on 2016/7/7.
 */
public class DeviceUtils {
    /*
     * 获取应用名
     */
    public static String getVersionName(Context context) {
        String versionName = null;
        try {
            //获取包管理者
            PackageManager pm = context.getPackageManager();
            //获取packageInfo
            PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);
            //获取versionName
            versionName = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return versionName;
    }

    /*
     * 获取应用版本
     */
    public static int getVersionCode(Context context) {

        int versionCode = 0;
        try {
            //获取包管理者
            PackageManager pm = context.getPackageManager();
            //获取packageInfo
            PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);
            //获取versionCode
            versionCode = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 判断AccessibilityService服务是否已经启动
     */
    public static boolean isAccessibilitySettingsOn(Context mContext) {
        int accessibilityEnabled = 0;
        final String service = "com.zhengpu.iflytekaiui" + "/" + MyAccessibilityService.class.getCanonicalName();
        boolean accessibilityFound = false;
        try {
            accessibilityEnabled = Settings.Secure.getInt(
                    mContext.getApplicationContext().getContentResolver(),
                    android.provider.Settings.Secure.ACCESSIBILITY_ENABLED);
        } catch (Settings.SettingNotFoundException e) {

        }
        TextUtils.SimpleStringSplitter mStringColonSplitter = new TextUtils.SimpleStringSplitter(':');

        if (accessibilityEnabled == 1) {
            String settingValue = Settings.Secure.getString(
                    mContext.getApplicationContext().getContentResolver(),
                    Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            if (settingValue != null) {
                TextUtils.SimpleStringSplitter splitter = mStringColonSplitter;
                splitter.setString(settingValue);
                while (splitter.hasNext()) {
                    String accessabilityService = splitter.next();
                    if (accessabilityService.equalsIgnoreCase(service)) {
                        return true;
                    }
                }
            }
        } else {

        }
        return accessibilityFound;
    }

    /***
     * 判断是否安装了某个APP
     */
    public static boolean isAppInstalled(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        List<String> pName = new ArrayList<String>();
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                pName.add(pn);
            }
        }
        return pName.contains(packageName);
    }

    /***
     * 获取手机中所有音乐文件
     */
    public static List<AllAudioSongBean> scanAllAudioFiles(Context context) {
        //生成动态集合，用于存储数据
        List<AllAudioSongBean> allAudioSongBeanList = new ArrayList<>();
        //查询媒体数据库
        Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        //遍历媒体数据库
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                //歌曲编号
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
                //歌曲名
                String tilte = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
                //歌曲的专辑名：MediaStore.Audio.Media.ALBUM
                String album = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
                //歌曲的歌手名： MediaStore.Audio.Media.ARTIST
                String author = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                //歌曲文件的路径 ：MediaStore.Audio.Media.DATA
                String url = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                //歌曲的总播放时长 ：MediaStore.Audio.Media.DURATION
                int duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
                //歌曲文件的大小 ：MediaStore.Audio.Media.SIZE
                Long size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));
                if (size > 1024 * 800) {//如果文件大小大于800K，
                    AllAudioSongBean allAudioSongBean = new AllAudioSongBean();
                    allAudioSongBean.setMusicId(id);
                    allAudioSongBean.setMusicTitle(tilte);
                    allAudioSongBean.setMusicFileUrl(url);
                    allAudioSongBean.setMusic_file_name(tilte);
                    allAudioSongBean.setMusic_author(author);
                    allAudioSongBean.setMusic_url(url);
                    allAudioSongBean.setMusic_duration(duration);
                    allAudioSongBeanList.add(allAudioSongBean);
                }
                cursor.moveToNext();
            }
        }
        return allAudioSongBeanList;
    }

    /***
     * 控制系统音量大小
     * @param Type =0 ; 减少音量
     * @param context
     */

    public static void setCurrentVolume(int Type, Context context) {
        //初始化音频管理器
        AudioManager mAudioManager = (AudioManager) context.getSystemService(AUDIO_SERVICE);
        //获取系统最大音量
        int maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        // 获取设备当前音量
        int currentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        if (Type == 0) {
            //减少音量
            mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);//调低声音
        } else {
            mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);  //调高声音
        }
    }


    /***
     * 判断是不是MP3格式
     */
    public static boolean isMp3Url(String url) {
        Pattern exp = Pattern.compile("^https://[\\w-\\.]+(?:/|(?:/[\\w\\.\\-]+)*(?:/[\\w\\.\\-]+\\.mp3))?$", Pattern.CASE_INSENSITIVE);
        return exp.matcher(url).matches();
    }

    public static PlayController getPlayStart(Context context, int id) {

        PlayController playController = new PlayController();
        Uri playUri = PlayProvider.PLAY_CONTENT_URI;
        Cursor playCursor = context.getContentResolver().query(playUri, new String[]{"_id", "type", "isPlay"}, null, null, null);

        if (playCursor != null) {
            while (playCursor.moveToNext()) {
                if (playCursor.getInt(0) == id) {
                    playController.Id = playCursor.getInt(0);
                    playController.type = playCursor.getString(1);
                    playController.isPlay = playCursor.getString(2);
                }
            }
            playCursor.close();
        }
        return playController;
    }

    public static void updatePlayStart(Context context, int id, String falg) {
        Uri bookUri = PlayProvider.PLAY_CONTENT_URI;
        ContentValues values = new ContentValues();
        values.put("isPlay", falg);
        try {
            int c = context.getContentResolver().update(bookUri, values, "_id=?", new String[]{String.valueOf(id)});
        } catch (Exception e) {
            e.toString();
        }
    }

    public static Intent IntentcreateExplicitFromImplicitIntent(Context context, Intent implicitIntent) {
        PackageManager pm =context.getPackageManager();
        List<ResolveInfo> resolveInfo =pm.queryIntentServices(implicitIntent, 0);
        if (resolveInfo == null ||resolveInfo.size() != 1) {
            return null;
        }
        ResolveInfo serviceInfo =resolveInfo.get(0);
        String packageName =serviceInfo.serviceInfo.packageName;
        String className =serviceInfo.serviceInfo.name;
        ComponentName component = new ComponentName(packageName, className);
        Intent explicitIntent = new Intent(implicitIntent);
        explicitIntent.setComponent(component);
        return explicitIntent;
    }




}


