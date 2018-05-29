package com.zhengpu.iflytekaiui.thread;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;

/**
 * sayid ....
 * Created by wengmf on 2017/12/13.
 */

public class KuGuoMuiscPlayThread implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

    static MediaPlayer mediaPlayer;
    private Context context;
    static KuGuoMuiscPlayThread kuGuoMuiscPlayThread;
    private String musicUrl;
    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {
        public void run() {
            try {
                mediaPlayer.reset();
                mediaPlayer.setDataSource(musicUrl); // 设置数据源
                mediaPlayer.prepare();                           // prepare自动播放

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public static synchronized KuGuoMuiscPlayThread getInstance(Context context) {
        if (kuGuoMuiscPlayThread == null)
            kuGuoMuiscPlayThread = new KuGuoMuiscPlayThread(context);
        return kuGuoMuiscPlayThread;
    }

    public KuGuoMuiscPlayThread(Context context) {
        this.context = context;
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);// 设置媒体流类型
        mediaPlayer.setOnBufferingUpdateListener(this);
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnCompletionListener(this);
    }


    public void playUrl(final String url) {
        this.musicUrl=url;
        mHandler.post(mRunnable);
    }

    public void start() {
        mediaPlayer.start();
    }

    public boolean isPlay() {
        return mediaPlayer != null && mediaPlayer.isPlaying();
    }

    // 停止
    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    // 暂停
    public void pause() {
        mediaPlayer.pause();
    }
    // 线程销毁
    public  void remove(){
        mHandler.removeCallbacks(mRunnable);
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }
}
