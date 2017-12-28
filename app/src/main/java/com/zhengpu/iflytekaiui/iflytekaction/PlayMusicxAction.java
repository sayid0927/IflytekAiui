package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.blankj.utilcode.utils.EncodeUtils;
import com.zhengpu.iflytekaiui.R;
import com.zhengpu.iflytekaiui.base.AppController;
import com.zhengpu.iflytekaiui.iflytekbean.AllAudioSongBean;
import com.zhengpu.iflytekaiui.iflytekbean.MusicXBean;
import com.zhengpu.iflytekaiui.iflytekbean.otherbean.IfMusicBean;
import com.zhengpu.iflytekaiui.iflytekbean.otherbean.IfMusicResBean;
import com.zhengpu.iflytekaiui.iflytekutils.JsonParser;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;
import com.zhengpu.iflytekaiui.thread.KuGuoMuiscPlayListener;
import com.zhengpu.iflytekaiui.utils.PreferUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.zhengpu.iflytekaiui.utils.DeviceUtils.isAppInstalled;
import static com.zhengpu.iflytekaiui.utils.DeviceUtils.scanAllAudioFiles;
import static com.zhengpu.iflytekaiui.utils.DeviceUtils.setCurrentVolume;

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
                                R4Action r4Action = new R4Action(context);
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
                            PreferUtil.getInstance().setPlayMusicName(artist+song);
                            if (isAppInstalled(context, "com.kugou.android")) {
                                //  打开应用
                                OpenAppAction openAppAction = new OpenAppAction("酷狗音乐", context);
                                openAppAction.start();
                                AppController.KuGuoplayClickabl = true;
                                AppController.abj = true;
                                AppController.b2w = true;
                                SpeechRecognizerService.startSpeech(service, "为你打开酷狗音乐播放" + artist+song, strRequest);
                                break;
                            } else {
//                             没有安装爱奇艺APP  打开浏览器 下载APP
                                SpeechRecognizerService.startSpeech(service, "你还没安装酷狗音乐APP， 是否去下载该程序", strRequest);
                                String keywords = "安卓酷狗音乐App";
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                String url ="https://m.baidu.com/from=844b/s?word="+ EncodeUtils.urlEncode(keywords);
                                intent.setData(Uri.parse(url));
                                context.startActivity(intent);
                                break;
                        }

//                        String url = "http://aiui.xfyun.cn/taste/getAnswer?text=" + EncodeUtils.urlEncode(musicXBean.getText()) + "&uid=1513933005954&appid=all&category=musicX&timestamp=1513933075498";
//                        OkHttpClient okHttpClient = new OkHttpClient();
//                        Request request = new Request.Builder()
//                                .url(url)
//                                .build();
//                        Call call = okHttpClient.newCall(request);
//                        call.enqueue(new Callback() {
//                            @Override
//                            public void onFailure(Call call, IOException e) {
//                                e.printStackTrace();
//                                SpeechRecognizerService.startSpeech(service, context.getResources().getString(R.string.error_network_text), strRequest);
//                            }
//
//                            @Override
//                            public void onResponse(Call call, Response response) throws IOException {
//                                String res = response.body().string();
//                                IfMusicBean ifMusicBean = JsonParser.parseResultIfMusicBean(res);
//                                if (ifMusicBean != null && ifMusicBean.getData() != null && ifMusicBean.getData().getRes() != null) {
//                                    IfMusicResBean ifMusicResBean = JsonParser.parseResultIfMusicResBean(ifMusicBean.getData().getRes());
//                                    if (ifMusicResBean != null && ifMusicResBean.getData() != null && ifMusicResBean.getData().getResult() != null && ifMusicResBean.getData().getResult().size() != 0) {
//                                        List<IfMusicResBean.DataBean.ResultBean> resultBean = ifMusicResBean.getData().getResult();
//
//                                        if (ifMusicResBean != null && ifMusicResBean.getData() != null && ifMusicResBean.getData().getResult() != null && ifMusicResBean.getData().getResult().get(0).getAudiopath() != null) {
//                                            PreferUtil.getInstance().setPlayMusicUrl(ifMusicResBean.getData().getResult().get(0).getAudiopath());
//                                            SpeechRecognizerService.startSpeech(service, ifMusicResBean.getAnswer().getText(), strRequest);
//                                        }
//                                    }
//                                }
//                            }
//                        });
                    }
                default:
                    R4Action r4Action = new R4Action(context);
                    r4Action.start();
            }
        } else {
            R4Action r4Action = new R4Action(context);
            r4Action.start();
        }
    }
}
