package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.Context;

import com.zhengpu.iflytekaiui.iflytekbean.RobotCommandBean;
import com.zhengpu.iflytekaiui.utils.PreferUtil;

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

            String  value = robotCommandBean.getSemantic().get(0).getSlots().get(0).getValue();
            switch (value){
                case  "":




                    break;

                default:

                    R4Action r4Action = new R4Action(context);
                    r4Action.start();
                    break;

            }
        }else {
          R4Action r4Action = new R4Action(context);
          r4Action.start();
        }
    }













}
