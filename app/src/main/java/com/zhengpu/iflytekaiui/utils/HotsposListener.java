package com.zhengpu.iflytekaiui.utils;

/**
 * sayid ....
 * Created by wengmf on 2018/1/29.
 */

public interface HotsposListener {


    /***
     * 创建 热点 成功
     */
    void  openHotspotSuccess();

    /***
     * 创建 热点失败
     */
    void  openHotspotError();

    /***
     * 接收UDP消息
     */

   void UDPReceiveSuccess(String content);


    /***
     *   关闭热点重连Wifi 成功
     */
    void connectWifiSuccess();


    /***
     *   关闭热点重连Wifi 失败
     */
    void connectWifiError();

}
