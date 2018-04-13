package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.Context;

import com.blankj.utilcode.utils.ConstUtils;
import com.blankj.utilcode.utils.LogUtils;
import com.blankj.utilcode.utils.TimeUtils;
import com.zhengpu.iflytekaiui.R;
import com.zhengpu.iflytekaiui.base.AppController;
import com.zhengpu.iflytekaiui.iflytekutils.WordsToVoice;
import com.zhengpu.iflytekaiui.ipc.entity.SendMessage;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;
import com.zhengpu.iflytekaiui.utils.PreferUtil;
import com.zhengpu.iflytekaiui.utils.ValueUtil;

import xiaofei.library.hermeseventbus.HermesEventBus;

/**
 * Created by Administrator on 2017/12/27 0027.
 */

public class SerialPortCmdBottmAction {

    private Context context;
    private byte[] bytes;
    private String [] strData;
    public SerialPortCmdBottmAction(Context context, byte[] bytes,String [] strData) {
        this.context = context;
        this.bytes = bytes;
        this.strData =strData;
    }

    public void start() {

        String service = "";
        String message = "";
        int high4bitsVal=0;

        if (bytes.length >= 12) {
            if ((bytes[12]&0x01) > 0) {
                    //bit0 == 1  冲电中
              service = "state_Flushing";
            }
            if ((bytes[12]&0x02) > 0) {
                //bit1 == 1  // 冲完电
                service = "state_Finish";
            }
             high4bitsVal = (bytes[12]&0xF0) >> 4;
            switch (high4bitsVal){
                case 0:
                    message ="0%" ;
                    break;
                case 1:
                    message ="20%" ;
                    break;
                case 2:
                    message ="40%" ;
                    break;
                case 3:
                    message ="60%" ;
                    break;
                case 4:
                    message ="80%" ;
                    break;
                case 5:
                    message ="100%" ;
                    break;
            }
        }

        SendMessage sendMessage = new SendMessage();
        sendMessage.setService(service);
        sendMessage.setMessage(String.valueOf(high4bitsVal));
        HermesEventBus.getDefault().post(sendMessage);

    }

    private static String function1(int n) {
        return Integer.toBinaryString(n);
    }

}
