package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;
import com.zhengpu.iflytekaiui.utils.PreferUtil;

/**
 * Created by Administrator on 2017/12/27 0027.
 */

public class SerialPortUtilsAction {


    private Context context;
    private byte[] bytes;
    private String[] strBytes;
    private SerialPortCmdBottmAction serialPortCmdBottmAction;

    public SerialPortUtilsAction(Context context, byte[] bytes, String[] strBytes) {
        this.context = context;
        this.bytes = bytes;
        this.strBytes = strBytes;

    }

    public void start() {
        if (bytes[0] == 0x5A & bytes[1] == 0x50) {
            if (bytes[7] == 0x00) {    //Status值  状态  0 成功

//                int len = Integer.valueOf(strBytes[2], 16);  // 长度， 从stod算到Statu.(5=<len<80)
//                int tmp = 0;
//                for (int i = 3; i < len + 3; i++) {
//                    int l = Integer.valueOf(strBytes[i], 16);
//                    tmp += l;
//                }
//                tmp = tmp & 0xff;

                byte[] dataByte = new byte[bytes.length];
                String[] strData = new String[bytes.length];
                for (int i = 0; i < bytes.length; i++) {
                    if ((i + 8) < bytes.length) {
                        dataByte[i] = bytes[i + 8];
                        strData[i] = strBytes[i + 8];
                    }
                }

                switch (bytes[4]) {      // cmd值  设置(0x01)  查询(0x02)  心跳(0x03) 主动上报(0x04)  升级(0x05)  测试(0x06)
                    case 0x01:
                        break;
                    case 0x02:
                        if (PreferUtil.getInstance().getTemperature() == 1) {
                            PreferUtil.getInstance().setTemperature(0);
                            SerialPortCmdTemperatureAction serialPortCmdTemperatureAction = new SerialPortCmdTemperatureAction(context, dataByte, strData);
                            serialPortCmdTemperatureAction.start();
                        } else {
                            serialPortCmdBottmAction = new SerialPortCmdBottmAction(context, dataByte, strData);
                            serialPortCmdBottmAction.start();
                        }
                        break;
                    case 0x03:
                        break;
                    case 0x04:
                        switch (bytes[3]) {
                            case 0x10:   // 上报背部板状态.
                                SerialPortCmdBackgroundAction serialPortCmdBackgroundAction = new SerialPortCmdBackgroundAction(context, dataByte, strData);
                                serialPortCmdBackgroundAction.start();
                                break;
                            case 0x20:   // 上报头部板状态
                                SerialPortCmdHeadAction serialPortCmdHeadAction = new SerialPortCmdHeadAction(context, dataByte, strData);
                                serialPortCmdHeadAction.start();
                                break;
                            case 0x30:  //  上报底板状态
                                serialPortCmdBottmAction = new SerialPortCmdBottmAction(context, dataByte, strData);
                                serialPortCmdBottmAction.start();
                                break;
                        }
                        break;
                }

                if (strBytes[6].equals("01")) {
                    SendByte();
                }
            }
        }
    }

    private void SendByte() {
        byte sum = 0;
        byte[] sendByte = new byte[11];
        sendByte[0] = 0x5A;
        sendByte[1] = 0x50;
        sendByte[2] = 0x05;
        switch (bytes[3]) {
            case 0x10:
                sendByte[3] = 0x01;
                break;
            case 0x20:
                sendByte[3] = 0x02;
                break;
            case 0x30:
                sendByte[3] = 0x03;
                break;
        }
        sendByte[4] = bytes[4];
        sendByte[5] = bytes[5];
        sendByte[6] = bytes[6];
        sendByte[7] = 0x00;

        for (int i = 3; i < 8; i++) {
            sum = (byte) (sum + sendByte[i]);
        }
        sendByte[8] = sum;
        sendByte[9] = 0x0D;
        sendByte[10] = 0x0A;
        SpeechRecognizerService.sendSerialMessageBytes(sendByte);
    }
}
