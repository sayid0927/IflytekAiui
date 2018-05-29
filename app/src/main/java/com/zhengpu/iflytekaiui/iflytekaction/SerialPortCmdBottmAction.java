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
import com.zhengpu.iflytekaiui.utils.ValueUtil;

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
        String service = "";
        String message = "";
        int levi = 0;
        if (bytes.length >= 9) {
            switch (strData[8]) {
                case "01":
                    service = "充电中";
                    break;
                case "10":
                    service = "充电完成";
                    break;
                default:
                    service = "未充电";
                    break;
            }
            levi = ValueUtil.getInstance().hexToDecimal(strData[9]);
            message = String.valueOf(levi + "%");
//            com.orhanobut.logger.Logger.e("冲电  service>>   " + service);
//            com.orhanobut.logger.Logger.e("冲电  电量  >>   " + message);
        }
        if (PreferUtil.getInstance().getElectricity() == 1) {
            PreferUtil.getInstance().setElectricity(0);
            String lile;
            if (levi <= 20) {
                lile = "多多电量不够了，多多得补充电量了";
            } else {
                lile = "当前剩余电量" + message;
            }
            SpeechRecognizerService.startSpeech(AppController.OPENAPPTEST_ROBOTCOMMAND, lile, lile);
        }
        SpeechRecognizerService.sendHermesMessage(service, message);
        PortData portData = new PortData();
        portData.setBatteryState(service);
        portData.setBatteryLevel(message);
        savePortFileData(portData);

    }

//    public static int byteToInt(byte b) {
//        //Java 总是把 byte 当做有符处理；我们可以通过将其和 0xFF 进行二进制与得到它的无符值
//        return b & 0xFF;
//    }

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


    private void savePortFileData(PortData portData) {
        String foldName = "ZPUSER";
        String fileName = "portData";
        String filePath = Environment.getExternalStorageDirectory() + "/" +
                foldName + "/" + fileName;
        boolean createSuccess = FileUtils.createFileByDeleteOldFile(filePath);
        if (createSuccess) {
            FileUtils.writeFileFromString(filePath, PortData.toJsonStr(portData), false);
        }
    }
}
