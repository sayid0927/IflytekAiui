package com.zhengpu.iflytekaiui.SerialPort;

/**
 * sayid ....
 * Created by wengmf on 2018/1/3.
 */

public interface OpenSerialPortListener {


    /**
     * 串口打开成功
     */
    void onOPenSerialSuccess();

    /**
     * 串口打开失败
     */
    void onOPenSerialFail();

    /**
     * 发送串口消息成功
     */
    void onDataSentSuccess();

    /**
     * 发送串口消息失败
     */
    void onDataSentFail();


    /**
     * 接收串口消息成功
     */
    void onDataReceivedSuccess(byte[] bytes);


}
