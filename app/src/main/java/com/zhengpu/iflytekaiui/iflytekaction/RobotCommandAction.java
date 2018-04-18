package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.Context;

import com.zhengpu.iflytekaiui.base.AppController;
import com.zhengpu.iflytekaiui.iflytekbean.RobotCommandBean;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;

/**
 * sayid ....
 * Created by wengmf on 2018/1/2.
 */

public class RobotCommandAction {


    private Context context;
    private String service;
    private RobotCommandBean robotCommandBean;
    private String request;


    public RobotCommandAction(String service,RobotCommandBean robotCommandBean, String request ,Context context) {

        this.context = context;
        this.service = service;
        this.robotCommandBean = robotCommandBean;
        this.request = request;

    }

    public void start() {

        if(robotCommandBean!= null&& robotCommandBean.getSemantic()!=null&& robotCommandBean.getSemantic().size()!=0
            && robotCommandBean.getSemantic().get(0).getSlots()!=null && robotCommandBean.getSemantic().get(0).getSlots().get(0).getValue()!=null){

            String  value = robotCommandBean.getSemantic().get(0).getSlots().get(0).getNormValue();
            switch (value){
                case AppController.WalkForward:   //前进
                    SpeechRecognizerService.sendSerialMessageBytes(AppController.byteWalkForward);
                    SpeechRecognizerService.startSpeech(service, "嘟嘟嘟，多多出发啦" , request);
                    break;
                case AppController.WalkBack:   //后退
                    SpeechRecognizerService.sendSerialMessageBytes(AppController.byteWalkBack);
                    SpeechRecognizerService.startSpeech(service, "注意，多多倒车啦" , request);
                    break;
                case AppController.WalkLeft:   //左转
                    SpeechRecognizerService.sendSerialMessageBytes(AppController.byteWalkLeft);
                    SpeechRecognizerService.startSpeech(service, "向左扭扭", request);
                    break;
                case AppController.WalkRight:   //右转
                    SpeechRecognizerService.sendSerialMessageBytes(AppController.byteWalkRight);
                    SpeechRecognizerService.startSpeech(service, "向右扭扭" , request);
                    break;
//                case AppController.WalkForwardLeft:   //前进左转
//                    SpeechRecognizerService.sendSerialMessageBytes(AppController.byteWalkForwardLeft);
//                    SpeechRecognizerService.startSpeech(service, "正在" + value, request);
//                    break;
//                case AppController.WalkForwardRight:   //前进右转
//                    SpeechRecognizerService.sendSerialMessageBytes(AppController.byteWalkForwardRight);
//                    SpeechRecognizerService.startSpeech(service, "正在" + value, request);
//                    break;
//                case AppController.WalkBackLeft:   //后退左转
//                    SpeechRecognizerService.sendSerialMessageBytes(AppController.byteWalkBackLeft);
//                    SpeechRecognizerService.startSpeech(service, "正在" + value, request);
//                    break;
//                case AppController.WalkBackRight:   //后退右转
//                    SpeechRecognizerService.sendSerialMessageBytes(AppController.byteWalkBackRight);
//                    SpeechRecognizerService.startSpeech(service, "正在" + value, request);
//                    break;
//                case AppController.WalkSituLeft:   //原地左转
//                    SpeechRecognizerService.sendSerialMessageBytes(AppController.byteWalkSituLeft);
//                    SpeechRecognizerService.startSpeech(service, "正在" + value, request);
//                    break;
//                case AppController.WalkSituRight:   //原地右转
//                    SpeechRecognizerService.sendSerialMessageBytes(AppController.byteWalkSituRight);
//                    SpeechRecognizerService.startSpeech(service, "正在" + value, request);
//                    break;
//                case AppController.HandUp:   //抬手
//                    SpeechRecognizerService.sendSerialMessageBytes(AppController.byteHandUp);
//                    SpeechRecognizerService.startSpeech(service, "正在" + value, request);
//                    break;
//                case AppController.HandReset:   //手复位
//                    SpeechRecognizerService.sendSerialMessageBytes(AppController.byteHandReset);
//                    SpeechRecognizerService.startSpeech(service, "正在" + value, request);
//                    break;
//                case AppController.HeadUp:   //抬头
//                    SpeechRecognizerService.sendSerialMessageBytes(AppController.byteHeadUp);
//                    SpeechRecognizerService.startSpeech(service, "正在" + value, request);
//                    break;
//                case AppController.HeadReset:   //头复位
//                    SpeechRecognizerService.sendSerialMessageBytes(AppController.byteHeadReset);
//                    SpeechRecognizerService.startSpeech(service, "正在" + value, request);
//                    break;
//                case AppController.HeadDown:   //低头
//                    SpeechRecognizerService.sendSerialMessageBytes(AppController.byteHeadDown);
//                    SpeechRecognizerService.startSpeech(service, "正在" + value, request);
//                    break;
//                case AppController.HeadLeft:   //头左转
//                    SpeechRecognizerService.sendSerialMessageBytes(AppController.byteHeadLeft);
//                    SpeechRecognizerService.startSpeech(service, "正在" + value, request);
//                    break;
//                case AppController.HeadRight:   //头右转
//                    SpeechRecognizerService.sendSerialMessageBytes(AppController.byteHeadRight);
//                    SpeechRecognizerService.startSpeech(service, "正在" + value, request);
//                    break;
//                case AppController.MotorStop:   //机器人停止
//                    SpeechRecognizerService.sendSerialMessageBytes(AppController.byteMotorStop);
//                    SpeechRecognizerService.startSpeech(service, "正在" + value, request);
//                    break;
//                case AppController.AutoCharge:   //充电
//                    SpeechRecognizerService.sendSerialMessageBytes(AppController.byteAutoCharge);
//                    SpeechRecognizerService.startSpeech(service, "正在" + value, request);
//                    break;
//                case AppController.AutoUpdata:   //升级
//                    SpeechRecognizerService.sendSerialMessageBytes(AppController.byteAutoUpdata);
//                    SpeechRecognizerService.startSpeech(service, "正在" + value, request);
//                    break;
//                case AppController.AutoReset:   //复位
//                    SpeechRecognizerService.sendSerialMessageBytes(AppController.byteAutoReset);
//                    SpeechRecognizerService.startSpeech(service, "正在" + value, request);
//                    break;
                default:
                    R4Action r4Action = new R4Action(context,request);
                    r4Action.start();
                    break;

            }
        }else {
          R4Action r4Action = new R4Action(context,request);
          r4Action.start();
        }
    }
}
