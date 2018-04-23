package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.zhengpu.iflytekaiui.iflytekbean.DanceBean;
import com.zhengpu.iflytekaiui.iflytekbean.DatetimeBean;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;

/**
 * sayid ....
 * Created by wengmf on 2018/4/17.
 */

public class DanceAction {

    private DanceBean danceBean ;
    private String service;
    private Context context;
    private String  request;

    public DanceAction(String service, DanceBean danceBean, String request, Context context) {
        this.danceBean = danceBean;
        this.service = service;
        this.context = context;
        this.request = request;
    }

    public void start() {

        if(danceBean!=null && danceBean.getSemantic()!=null && danceBean.getSemantic().size()!=0
             && danceBean.getSemantic().get(0).getSlots()!=null && danceBean.getSemantic().get(0).getSlots().size()!=0   ){

            try {

                Intent gotoDance = new Intent();
                gotoDance.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                gotoDance.setComponent(new ComponentName("com.zeunpro.dance","com.zeunpro.dance.ui.DanceAct"));
                context.startActivity(gotoDance);
                SpeechRecognizerService.startSpeech("DanceAction","多多开始摇摆了","多多开始摇摆了");
                SpeechRecognizerService.Destroy();

            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            R4Action r4Action = new R4Action(context, request);
            r4Action.start();
        }
    }
}
