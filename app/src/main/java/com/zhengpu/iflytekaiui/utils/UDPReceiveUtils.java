package com.zhengpu.iflytekaiui.utils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * Created by zhangtx on 2018/1/26.
 */

public class UDPReceiveUtils extends Thread {

    private static final String TAG = UDPReceiveUtils.class.getSimpleName();
    private static int BROADCAST_PORT = 9898;
    private static final String BROADCAST_IP = "239.0.0.1";
    private MulticastSocket multicastSocket;
    private InetAddress inetAddress;
    private UDPReceiveListenter udpReceiveListenter;


    public UDPReceiveUtils() {
//        joinGroup();
        try {
            multicastSocket = new MulticastSocket(BROADCAST_PORT);
            inetAddress = InetAddress.getByName(BROADCAST_IP);
            multicastSocket.joinGroup(inetAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void  setUdpReceiveListenter(UDPReceiveListenter udpReceiveListenter ){
        this.udpReceiveListenter = udpReceiveListenter;
    }



//    private void joinGroup() {
//        // 多播配置
//        try {
//            multicastSocket = new MulticastSocket(BROADCAST_PORT);
//            inetAddress = InetAddress.getByName(BROADCAST_IP);
//            multicastSocket.joinGroup(inetAddress);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void run() {
        while (true) {

            byte buf[] = new byte[1024];
            DatagramPacket dp = new DatagramPacket(buf, buf.length, inetAddress, BROADCAST_PORT);
            try {
                multicastSocket.receive(dp);
                String content = new String(buf, 0, dp.getLength());

                if(udpReceiveListenter!=null)
                    udpReceiveListenter.UDPReceiveSuccess(content);

//                Message msg = new Message();
//                msg.what = RECEIVE_MSG;
//                msg.obj = content;
//                handler.sendMessage(msg);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
