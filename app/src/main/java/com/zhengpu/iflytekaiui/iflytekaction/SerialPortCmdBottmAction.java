package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.blankj.utilcode.utils.FileUtils;
import com.orhanobut.logger.Logger;
import com.zhengpu.iflytekaiui.base.AppController;
import com.zhengpu.iflytekaiui.iflytekbean.otherbean.PortData;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;
import com.zhengpu.iflytekaiui.utils.PreferUtil;

import java.io.PrintWriter;
import java.util.List;

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
            if (bit[7] == 0 && bit[6] == 0) {
                // 未充电
                service = "未充电";
            } else if(bit[7] == 1 && bit[6] == 1){
                // 未充电
                service = "未充电";
            } else if (bit[7] == 1 && bit[6] == 0) {
                //bit0 == 1  冲电中
                service = "充电中";
            } else if (bit[7] == 0 && bit[6] == 1) {
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

//        com.orhanobut.logger.Logger.e("冲电  service>>   "+ service);
//        com.orhanobut.logger.Logger.e("冲电  电量  >>   "+ String.valueOf(high4bitsVal) + "    >>"+message );

        if (PreferUtil.getInstance().getElectricity() == 1) {
            PreferUtil.getInstance().setElectricity(0);
            String lile;
            if (high4bitsVal <= 1) {
                lile = "多多电量不够了，多多得补充电量了";
            } else {
                lile = "当前剩余电量" + message;
            }
            SpeechRecognizerService.startSpeech(AppController.OPENAPPTEST_ROBOTCOMMAND, lile, lile);
        }

        SpeechRecognizerService.sendHermesMessage(service,message);

        PortData   portData = new PortData();
        portData.setBatteryState(service);
        portData.setBatteryLevel(message);
        savePortFileData(portData);

    }

    /**
     * 将byte转换为一个长度为8的byte数组，数组每个值代表bit
     */

    private static byte[] getBooleanArray(byte b) {
        byte[] array = new byte[8];
        for (int i = 7; i >= 0; i--) {
            array[i] = (byte) (b & 1);
            b = (byte) (b >> 1);
        }
        return array;
    }


    private  void savePortFileData(PortData portData){
        String foldName = "ZPUSER";
        String fileName = "portData";
        String filePath = Environment.getExternalStorageDirectory()+"/"+
                foldName +"/"+fileName;
        boolean  createSuccess = FileUtils.createFileByDeleteOldFile(filePath);
        if(createSuccess){
           FileUtils.writeFileFromString(filePath,  PortData.toJsonStr(portData), false);
        }
    }
}
