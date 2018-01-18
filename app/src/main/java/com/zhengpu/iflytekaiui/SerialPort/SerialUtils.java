package com.zhengpu.iflytekaiui.SerialPort;

import android.content.Context;

import com.kongqw.serialportlibrary.Device;
import com.kongqw.serialportlibrary.SerialPortFinder;
import com.kongqw.serialportlibrary.SerialPortManager;
import com.kongqw.serialportlibrary.listener.OnOpenSerialPortListener;
import com.kongqw.serialportlibrary.listener.OnSerialPortDataListener;
import com.orhanobut.logger.Logger;
import com.zhengpu.iflytekaiui.utils.ValueUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * sayid ....
 * Created by wengmf on 2018/1/3.
 */

public class SerialUtils implements OnOpenSerialPortListener {

    private  Context context;
    public static SerialUtils serialUtils;

    private SerialPortManager mSerialPortManager;
    private OpenSerialPortListener serialPortListener ;
    private  Device device;


    public SerialUtils(Context context) {
        this.context = context;

        mSerialPortManager = new SerialPortManager();
        SerialPortFinder serialPortFinder = new SerialPortFinder();
        ArrayList<Device> devices = serialPortFinder.getDevices();

        for(int i =0;i<devices.size();i++){
           if(devices.get(i).getFile().getAbsolutePath().equals("/dev/ttyMT2")){
             device = devices.get(i);
             break;
           }
        }

       if(device!=null){
           // 打开串口
           boolean openSerialPort = mSerialPortManager.setOnOpenSerialPortListener(this)
                   .setOnSerialPortDataListener(new OnSerialPortDataListener() {
                       @Override
                       public void onDataReceived(byte[] bytes) {
//                           Logger.e("onDataReceived  [ byte[] ]: " + Arrays.toString(bytes));
//                           Logger.e( "onDataReceived [ String ]: " + new String(bytes));



                           String value = ValueUtil.getInstance().bytesToHexStr(bytes);
                           Logger.e("接收成功 >>>   " + value);

                           //  接收成功
                           if(serialPortListener!=null )
                               serialPortListener.onDataReceivedSuccess(bytes);

                       }
                       @Override
                       public void onDataSent(byte[] bytes) {
//                           Logger.e("onDataSent [ byte[] ]: " + Arrays.toString(bytes));
//                           Logger.e("onDataSent [ String ]: " + new String(bytes));


                           String value = ValueUtil.getInstance().bytesToHexStr(bytes);
                           Logger.e("发送成功 >>>   " + value);
                           // 发送成功
                           if(serialPortListener!=null )
                               serialPortListener.onDataSentSuccess();

                       }
                   })
                   .openSerialPort(device.getFile(), 115200);
       }
    }

    public  void setSerialPortListener(OpenSerialPortListener serialPortListener) {
        this.serialPortListener = serialPortListener;
    }


    public static synchronized SerialUtils getInstance(Context context) {
        if (serialUtils == null)
            serialUtils = new SerialUtils(context);
        return serialUtils;
    }

    public  void sendContentBytes(byte[] bytes){
        boolean sendBytes = mSerialPortManager.sendBytes(bytes);
        //发送消息失败
        if(!sendBytes){
            if(serialPortListener!=null )
                serialPortListener.onDataSentFail();
        }
    }

    /**
     * 串口打开成功
     *
     */
    @Override
    public void onSuccess(File file) {
        if(serialPortListener!=null )
            serialPortListener.onOPenSerialSuccess();
    }

    /**
     * 串口打开失败
     */
    @Override
    public void onFail(File file, Status status) {
        if(serialPortListener!=null )
            serialPortListener.onOPenSerialFail();
    }
}
