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

import java.util.logging.Logger;

import xiaofei.library.hermeseventbus.HermesEventBus;

/**
 * Created by Administrator on 2017/12/27 0027.
 */

public class SerialPortCmdBottmAction {

    private Context context;
    private byte[] bytes;
    private String[] strData;

    public SerialPortCmdBottmAction(Context context, byte[] bytes, String[] strData) {
        this.context = context;
        this.bytes = bytes;
        this.strData = strData;
    }

    public void start() {

//   未充电:bit0=0,bit1=0
//   充电中:bit0=1,bit1=0
//   充满:bit0=0,bit1=1

        String service = "";
        String message = "";
        int high4bitsVal = 0;

        if (bytes.length >= 12) {
            byte[] bit = getBooleanArray(bytes[12]);
            if (bit[7] ==0 && bit[6]==0) {
                // 未充电
                service = "未充电";
            } else if (bit[7] ==1 && bit[6]==0) {
                //bit0 == 1  冲电中
                service = "充电中";
            } else if (bit[7] ==0 && bit[6]==1) {
                //bit1 == 1  // 充电完成
                service = "充电完成";
            }

            high4bitsVal = (bytes[12] & 0xF0) >> 4;
            switch (high4bitsVal) {
                case 0:
                    message = "0%";
                    break;
                case 1:
                    message = "20%";
                    break;
                case 2:
                    message = "40%";
                    break;
                case 3:
                    message = "60%";
                    break;
                case 4:
                    message = "80%";
                    break;
                case 5:
                    message = "100%";
                    break;
            }
        }

        com.orhanobut.logger.Logger.e("冲电  service>>   "+ service);
        com.orhanobut.logger.Logger.e("冲电  电量  >>   "+ String.valueOf(high4bitsVal) + "    >>"+message );

//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setService(service);
//        sendMessage.setMessage(message);
//        HermesEventBus.getDefault().post(sendMessage);

        SpeechRecognizerService.sendHermesMessage(service,message);

    }

    private static String function1(int n) {
        return Integer.toBinaryString(n);
    }

    /**
     * 将byte转换为一个长度为8的byte数组，数组每个值代表bit
     */

    public static byte[] getBooleanArray(byte b) {
        byte[] array = new byte[8];
        for (int i = 7; i >= 0; i--) {
            array[i] = (byte) (b & 1);
            b = (byte) (b >> 1);
        }
        return array;
    }

}
