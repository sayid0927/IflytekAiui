package com.zhengpu.iflytekaiui.iflytekaction;

import android.app.Application;
import android.content.Context;

import com.iflytek.cloud.thirdparty.by;
import com.zhengpu.iflytekaiui.base.AppController;
import com.zhengpu.iflytekaiui.base.BaseApplication;
import com.zhengpu.iflytekaiui.iflytekbean.RobotCommandBean;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;
import com.zhengpu.iflytekaiui.utils.PreferUtil;

import java.util.concurrent.TimeUnit;

import static com.zhengpu.iflytekaiui.service.SpeechRecognizerService.sendSerialMessageBytes;

/**
 * sayid ....
 * Created by wengmf on 2018/1/2.
 */

public class RobotCommandAction {


    private Context context;
    private String service;
    private RobotCommandBean robotCommandBean;
    private String request;


    public RobotCommandAction(String service, RobotCommandBean robotCommandBean, String request, Context context) {

        this.context = context;
        this.service = service;
        this.robotCommandBean = robotCommandBean;
        this.request = request;

    }

    public void start() {

        if (robotCommandBean != null && robotCommandBean.getSemantic() != null && robotCommandBean.getSemantic().size() != 0
                && robotCommandBean.getSemantic().get(0).getSlots() != null && robotCommandBean.getSemantic().get(0).getSlots().get(0).getValue() != null) {

            if (robotCommandBean.getSemantic().get(0).getIntent().equals("command")) {
                String value = robotCommandBean.getSemantic().get(0).getSlots().get(0).getNormValue();
                switch (value) {
                    case AppController.WalkForward:   //前进
                        sendSerialMessageBytes(AppController.byteWalkBack);
                        SpeechRecognizerService.startSpeech(service, "嘟嘟嘟，多多出发啦", request);
                        BaseApplication.MAIN_EXECUTOR.schedule(new Runnable() {
                            @Override
                            public void run() {
                                sendSerialMessageBytes(new byte[]{0x5A,0x50,0x05,0x03,0x01,0x06,0x00,0x00,0x0A,0x0D,0x0A});
                            }
                        }, 2, TimeUnit.SECONDS);
                        break;
                    case AppController.WalkBack:   //后退
                        sendSerialMessageBytes(AppController.byteWalkForward);
                        SpeechRecognizerService.startSpeech(service, "注意，多多倒车啦", request);
                        BaseApplication.MAIN_EXECUTOR.schedule(new Runnable() {
                            @Override
                            public void run() {
                                sendSerialMessageBytes(new byte[]{0x5A,0x50,0x05,0x03,0x01,0x06,0x00,0x00,0x0A,0x0D,0x0A});
                            }
                        }, 2, TimeUnit.SECONDS);
                        break;
                    case AppController.WalkLeft:   //左转
                        sendSerialMessageBytes(AppController.byteWalkRight);
                        SpeechRecognizerService.startSpeech(service, "向左扭扭", request);
                        BaseApplication.MAIN_EXECUTOR.schedule(new Runnable() {
                            @Override
                            public void run() {
                                sendSerialMessageBytes(new byte[]{0x5A,0x50,0x05,0x03,0x01,0x06,0x00,0x00,0x0A,0x0D,0x0A});
                            }
                        }, 3, TimeUnit.SECONDS);
                        break;
                    case AppController.WalkRight:   //右转

                        sendSerialMessageBytes(AppController.byteWalkLeft);
                        SpeechRecognizerService.startSpeech(service, "向右扭扭", request);
                        BaseApplication.MAIN_EXECUTOR.schedule(new Runnable() {
                            @Override
                            public void run() {
                                sendSerialMessageBytes(new byte[]{0x5A,0x50,0x05,0x03,0x01,0x06,0x00,0x00,0x0A,0x0D,0x0A});
                            }
                        }, 3, TimeUnit.SECONDS);
                        break;
                    default:
                        R4Action r4Action = new R4Action(context, request);
                        r4Action.start();
                        break;
                }

            } else {
                if (robotCommandBean.getSemantic().get(0).getIntent().equals("Temperature")) {
                    PreferUtil.getInstance().setTemperature(1);
                    sendSerialMessageBytes(new byte[]{0x5A, 0x50, 0x05, 0x01, 0x02, 0x01, 0x00, 0x00, 0x04, 0x0D, 0x0A});
                }
            }
        } else {
            R4Action r4Action = new R4Action(context, request);
            r4Action.start();
        }
    }
}
