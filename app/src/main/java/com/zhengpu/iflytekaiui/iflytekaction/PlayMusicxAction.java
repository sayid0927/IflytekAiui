package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.blankj.utilcode.utils.EncodeUtils;
import com.orhanobut.logger.Logger;
import com.zhengpu.iflytekaiui.R;
import com.zhengpu.iflytekaiui.base.AppController;
import com.zhengpu.iflytekaiui.iflytekbean.AllAudioSongBean;
import com.zhengpu.iflytekaiui.iflytekbean.MusicXBean;
import com.zhengpu.iflytekaiui.iflytekbean.otherbean.IfMusicBean;
import com.zhengpu.iflytekaiui.iflytekbean.otherbean.IfMusicResBean;
import com.zhengpu.iflytekaiui.iflytekutils.JsonParser;
import com.zhengpu.iflytekaiui.ipc.entity.SendMessage;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;
import com.zhengpu.iflytekaiui.thread.KuGuoMuiscPlayListener;
import com.zhengpu.iflytekaiui.utils.CommDialog;
import com.zhengpu.iflytekaiui.utils.PreferUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import xiaofei.library.hermeseventbus.HermesEventBus;

import static com.zhengpu.iflytekaiui.utils.DeviceUtils.isAppInstalled;
import static com.zhengpu.iflytekaiui.utils.DeviceUtils.scanAllAudioFiles;
import static com.zhengpu.iflytekaiui.utils.DeviceUtils.setCurrentVolume;
import static com.zhengpu.iflytekaiui.utils.DeviceUtils.updatePlayStart;


/**
 * sayid ....
 * Created by wengmf on 2017/11/24.
 */

public class PlayMusicxAction {

    private Context context;
    private String service;
    private MusicXBean musicXBean;
    private String artist = "", song = "";
    private int PalyMode;
    private String strRequest;
    public KuGuoMuiscPlayListener kuGuoMuiscPlayListener;

    public PlayMusicxAction(String service, MusicXBean musicXBean, String strRequest, Context context) {
        this.service = service;
        this.musicXBean = musicXBean;
        this.context = context;
        this.strRequest = strRequest;
    }

    public void setKuGuoMuiscPlayListener(KuGuoMuiscPlayListener kuGuoMuiscPlayListener) {
        this.kuGuoMuiscPlayListener = kuGuoMuiscPlayListener;
    }

    public void start() {
        if (musicXBean != null && musicXBean.getText() != null) {
            PalyMode = -1;
            switch (musicXBean.getSemantic().get(0).getIntent()) {
                case "INSTRUCTION": // 指令命令
                    if (musicXBean.getSemantic().get(0).getSlots().get(0).getName().equals("insType")) {
                        switch (musicXBean.getSemantic().get(0).getSlots().get(0).getValue()) {
                            case "volume_minus":
                                setCurrentVolume(0, context);  // 音量小
                                break;
                            case "volume_plus":
                                setCurrentVolume(1, context);  // 音量大
                                break;

//                            case "pause":  // 暂停
//                            kuGuoMuiscPlayListener.KuGuoMuiscPlayPause();
//                            setKuGuoMuiscPlayStart(0);
//                                break;
//                            case "replay": // 继续播放
//                            kuGuoMuiscPlayListener.KuGuoMuiscPlayReplay();
//                            setKuGuoMuiscPlayStart(1);
//                                break;

                            default:
                                R4Action r4Action = new R4Action(context, strRequest);
                                r4Action.start();
                        }
                    }
                    break;
                case "PLAY": // 播放歌曲命令
                    for (int i = 0; i < musicXBean.getSemantic().get(0).getSlots().size(); i++) {
                        String name = musicXBean.getSemantic().get(0).getSlots().get(i).getName();
                        if (name.equals("artist")) {
                            artist = musicXBean.getSemantic().get(0).getSlots().get(i).getValue();
                        } else if (name.equals("song")) {
                            song = musicXBean.getSemantic().get(0).getSlots().get(i).getValue();
                        }
                    }
                    //  本地播放
                    List<AllAudioSongBean> allAudioSongBeanList = scanAllAudioFiles(context);
                    for (int i = 0; i < allAudioSongBeanList.size(); i++) {
                        String Author = allAudioSongBeanList.get(i).getMusic_author();           //  歌手
                        String Music_Title = allAudioSongBeanList.get(i).getMusicTitle();       //歌名
                        if (artist != "" && song != "") {
                            if (artist.equals(Author) && song.equals(Music_Title)) {                    //如果歌手跟歌名一样
                                PreferUtil.getInstance().setPlayMusicUrl(allAudioSongBeanList.get(i).getMusicFileUrl());
                                SpeechRecognizerService.startSpeech(service, "请欣赏" + artist + "的" + song, strRequest);
                                PalyMode = 0;
                                break;
                            }
                        } else if (song != "") {
                            if (song.equals(Music_Title)) {
                                PreferUtil.getInstance().setPlayMusicUrl(allAudioSongBeanList.get(i).getMusicFileUrl());
                                SpeechRecognizerService.startSpeech(service, "请欣赏" + song, strRequest);
                                PalyMode = 0;
                                break;
                            }
                        }
                    }
                    //    酷狗播放
                    if (PalyMode != 0) {
                        if (isAppInstalled(context, "com.tencent.qqmusicpad")) {
                            //  打开应用
                            updatePlayStart(context, 7, artist +song);
                            updatePlayStart(context, 1, "true");
                            updatePlayStart(context, 8, "true");
                            updatePlayStart(context, 9, "true");
                            updatePlayStart(context, 11, "true");

                            OpenAppAction openAppAction = new OpenAppAction("QQ音乐HD", context);
                            openAppAction.start();
                            SpeechRecognizerService.startSpeech(service, "为你打开QQ音乐播放" + artist + song, strRequest);

                            break;
                        } else {
//                             没有安装爱奇艺APP  打开浏览器 下载APP
                            SpeechRecognizerService.startSpeech(service, context.getResources().getString(R.string.kuguo_tip), strRequest);
                            showDialog();
                            break;
                        }
                    }
                default:
                    R4Action r4Action = new R4Action(context, strRequest);
                    r4Action.start();
            }
        } else {
            R4Action r4Action = new R4Action(context, strRequest);
            r4Action.start();
        }
    }


    public void showDialog() {
        final CommDialog commDialog = new CommDialog(context, context.getResources().getString(R.string.kuguo_tip));
        commDialog.setOnClickListener(new CommDialog.OnClickListener() {
            @Override
            public void onButtonOKClick() {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                String url = "http://sj.qq.com/myapp/detail.htm?apkName=com.tencent.qqmusicpad";
                intent.setData(Uri.parse(url));
                context.startActivity(intent);
                commDialog.dismiss();
            }
            @Override
            public void onButtonCanelClick() {
                commDialog.dismiss();
            }
        });
        commDialog.show();
    }
}
