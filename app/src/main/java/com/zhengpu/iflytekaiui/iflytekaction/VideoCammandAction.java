package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.zhengpu.iflytekaiui.iflytekbean.VideoCammandBean;

import java.util.List;

import static com.zhengpu.iflytekaiui.service.SpeechRecognizerService.SendShootAction;

/**
 * sayid ....
 * Created by wengmf on 2018/3/29.
 */

public class VideoCammandAction {


    private Context context;
    private VideoCammandBean videoCammandBean;

    public VideoCammandAction(VideoCammandBean videoCammandBean, Context context) {
        this.videoCammandBean = videoCammandBean;
        this.context = context;
    }

    public void start() {

        if(videoCammandBean!=null && videoCammandBean.getSemantic()!=null && videoCammandBean.getSemantic().size()!=0){

          String inte=     videoCammandBean.getSemantic().get(0).getIntent();
          switch (inte){
              case  "Video_Action_Stop":
                  SendShootAction("VIDEO_RECORDING_STATE","PAUSE_RECORDING");
                  break;
              case  "Video_Action_Start":
                  SendShootAction("VIDEO_RECORDING_STATE","START_RECORDING");
                  break;
              case  "Video_Action_Restart":
                  SendShootAction("VIDEO_RECORDING_STATE","RESUME_RECORDING");
                  break;
              case  "Video_Action_End":
                  SendShootAction("VIDEO_RECORDING_STATE","END_OF_RECORDING");
                  break;

          }
        }
    }
}
