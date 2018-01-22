package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.blankj.utilcode.utils.EncodeUtils;
import com.zhengpu.iflytekaiui.R;
import com.zhengpu.iflytekaiui.iflytekbean.AllAudioSongBean;
import com.zhengpu.iflytekaiui.iflytekbean.MusicXBean;
import com.zhengpu.iflytekaiui.iflytekbean.VideoBean;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;
import com.zhengpu.iflytekaiui.utils.CommDialog;
import com.zhengpu.iflytekaiui.utils.PreferUtil;

import java.util.List;

import static com.zhengpu.iflytekaiui.utils.DeviceUtils.isAppInstalled;
import static com.zhengpu.iflytekaiui.utils.DeviceUtils.scanAllAudioFiles;
import static com.zhengpu.iflytekaiui.utils.DeviceUtils.updatePlayStart;

/**
 * sayid ....
 * Created by wengmf on 2018/1/15.
 */

public class MoreMusicXAction {


    private Context context;
    private String service;
    private VideoBean.MoreResultsBean musicXBean;
    private String artist = "", song = "";
    private int PalyMode;
    private String strRequest;


    public MoreMusicXAction(String service, VideoBean.MoreResultsBean musicXBean, String strRequest, Context context) {
        this.service = service;
        this.musicXBean = musicXBean;
        this.context = context;
        this.strRequest = strRequest;
    }

    public void start() {
        if (musicXBean != null && musicXBean.getSemantic()!=null && musicXBean.getSemantic().get(0).getSlots()!=null &&
              musicXBean.getSemantic().get(0).getSlots()!=null  ) {
            PalyMode = -1;
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
                if (isAppInstalled(context, "cn.kuwo.kwmusichd")) {
                    //  打开应用
                    updatePlayStart(context, 7, artist +song);
                    updatePlayStart(context, 1, "true");
                    updatePlayStart(context, 3, "true");

                    OpenAppAction openAppAction = new OpenAppAction("酷我音乐HD", context);
                    openAppAction.start();
                    SpeechRecognizerService.startSpeech(service, "为你打开酷我音乐播放" + artist + song, strRequest);

                } else {
//               没有安装酷狗播放APP  打开浏览器 下载APP
                    SpeechRecognizerService.startSpeech(service, context.getResources().getString(R.string.kuguo_tip), strRequest);
                    showDialog();

                }
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
//                String keywords = "安卓酷狗音乐App";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                String url = "http://sj.qq.com/myapp/detail.htm?apkName=cn.kuwo.kwmusichd";
//                String url = "https://m.baidu.com/from=844b/s?word=" + EncodeUtils.urlEncode(keywords);
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
