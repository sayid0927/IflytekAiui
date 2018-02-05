package com.zhengpu.iflytekaiui.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Handler;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * Created by zhangtx on 2018/1/26.
 */

public class UDPSendUtils  {
    private static UDPSendUtils mobileSocketClient = null;
    private static String BROADCAST_IP = "239.0.0.1";
    private static int BROADCAST_PORT = 9898;
    private WifiManager.MulticastLock multicastLock;
    private MulticastSocket multicastSocket;
    private InetAddress inetAddress;
    private  String content;

    private UDPSendUtils() {
        try {
            //初始化组播
            inetAddress = InetAddress.getByName(BROADCAST_IP);
            multicastSocket = new MulticastSocket(BROADCAST_PORT);
            multicastSocket.setTimeToLive(1);
            multicastSocket.joinGroup(inetAddress);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized UDPSendUtils getInstance( ) {
        if (mobileSocketClient == null) {
            mobileSocketClient = new UDPSendUtils();
        }
        return mobileSocketClient;
    }

    public void sendMessage(String content) {
                byte[] data = content.getBytes();
                //构造要发送的数据
                DatagramPacket dataPacket = new DatagramPacket(data, data.length, inetAddress, BROADCAST_PORT);
                try {
                    if (dataPacket != null)
                        multicastSocket.send(dataPacket);
                } catch (IOException e) {
                    e.printStackTrace();
        }
    }
}
