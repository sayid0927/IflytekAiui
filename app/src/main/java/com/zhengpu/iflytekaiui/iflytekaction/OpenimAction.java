package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.zhengpu.iflytekaiui.iflytekbean.DanceBean;
import com.zhengpu.iflytekaiui.iflytekbean.OpenimBean;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;

/**
 * sayid ....
 * Created by wengmf on 2018/4/17.
 */

public class OpenimAction {

    private OpenimBean openimBean;
    private String service;
    private Context context;
    private String request;

    public OpenimAction(String service, OpenimBean openimBean, String request, Context context) {

        this.openimBean = openimBean;
        this.service = service;
        this.context = context;
        this.request = request;

    }

    public void start() {

        if (openimBean != null && openimBean.getSemantic() != null && openimBean.getSemantic().size() != 0 &&
                openimBean.getSemantic().get(0).getSlots() != null && openimBean.getSemantic().get(0).getSlots().size() != 0) {
            try {
                Intent gotoIm = new Intent();
                gotoIm.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                gotoIm.setComponent(new ComponentName("com.zeunpro.im", "com.zeunpro.im.SplashActivity"));
                context.startActivity(gotoIm);
            }catch (Exception e){
                e.printStackTrace();
            }
        } else {
            R4Action r4Action = new R4Action(context, request);
            r4Action.start();
        }
    }
}
