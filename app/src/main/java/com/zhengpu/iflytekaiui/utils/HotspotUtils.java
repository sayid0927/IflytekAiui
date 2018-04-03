package com.zhengpu.iflytekaiui.utils;

import android.Manifest;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;

import com.blankj.utilcode.utils.LogUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.orhanobut.logger.Logger;
import com.zhengpu.iflytekaiui.base.BaseApplication;
import com.zhengpu.iflytekaiui.broadcastReceiver.WifiChangeBroadcastReceiver;
import com.zhengpu.iflytekaiui.broadcastReceiver.WifiChangeListener;
import com.zhengpu.iflytekaiui.iflytekbean.otherbean.HotspotRequest;
import com.zhengpu.iflytekaiui.iflytekbean.otherbean.WifiData;
import com.zhengpu.iflytekaiui.iflytekutils.JsonParser;
import com.zhengpu.iflytekaiui.iflytekutils.VoiceToWords;
import com.zhengpu.iflytekaiui.wifitools.ApMgr;
import com.zhengpu.iflytekaiui.wifitools.Consts;
import com.zhengpu.iflytekaiui.wifitools.HotSpotBroadcaseReceiver;
import com.zhengpu.iflytekaiui.wifitools.WifiMgr;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * sayid ....
 * Created by wengmf on 2018/1/26.
 */

public class HotspotUtils implements WifiChangeListener {


    private static String WIFISSID;
    private static String PWD;
    private final WifiMgr wifiMgr;
    private Context context;
    private HotsposListener hotsposListener;
    private WifiChangeBroadcastReceiver wifiChangeBroadcastReceiver;
    private static HotspotUtils hotspotUtils ;


    /**
     * Udp Socket
     */
    private DatagramSocket mDatagramSocket;

    /**
     * 便携热点状态接收器
     */
    private HotSpotBroadcaseReceiver mHotSpotBroadcaseReceiver;
    /**
     * 是否初始化成功
     */
    private boolean mIsInitialized;

    /**
     * 获取权限是否成功
     */
    private boolean mIsPermissionGranted;

    /**
     * 创建便携热点所需权限
     */
    private static final String[] PERMISSION_CREATE_HOTSPOT = new String[]{
            Manifest.permission.WRITE_SETTINGS,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE};

    /**
     * 创建便携热点权限请求码
     */
    private static final int PERMISSION_REQ_CREATE_HOTSPOT = 3021;


    public static synchronized  HotspotUtils getHotspotUtils(Context context){
        if (hotspotUtils == null)
            hotspotUtils = new HotspotUtils(context);
        return hotspotUtils;
    }

    public HotspotUtils(Context context) {
        this.context = context;

        wifiMgr = new WifiMgr(context);
        WIFISSID = wifiMgr.getConnectedSSID();
        WIFISSID = "ZP1";
        PWD = "zeunpro123";
//        WifiInfo wifiInfo=  wifiMgr.getCurrentWifiInfo();

        //  申请权限
        requestPermission(PERMISSION_CREATE_HOTSPOT, PERMISSION_REQ_CREATE_HOTSPOT);
        //  创建热点
        openHotspot();


        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.wifi.STATE_CHANGE");
        filter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        wifiChangeBroadcastReceiver = new WifiChangeBroadcastReceiver();
        //注册广播接收
        context.registerReceiver(wifiChangeBroadcastReceiver, filter);
        wifiChangeBroadcastReceiver.setWifiChangeListener(this);


    }

    public void setHotsposListener(HotsposListener hotsposListener) {
        this.hotsposListener = hotsposListener;
    }

    private void openHotspot() {

        String ssid = "zeunproztx";
        String password = "zeunpro123";

        //是否有权限
        if (mIsPermissionGranted) {
            //开启热点前，先关闭WiFi，如有其他热点已开启，先关闭
            ApMgr.closeWifi(context);
            if (ApMgr.isApOn(context)) {
                ApMgr.closeAp(context);
            }
            //注册便携热点状态接收器
            registerHotSpotReceiver();
            //开启热点
            boolean isSuccess = ApMgr.openAp(context, ssid, password);
            if (!isSuccess) {
                //    创建热点失败
                if (hotsposListener != null)
                    hotsposListener.openHotspotError();
//            ToastUtils.showLongToast("创建热点失败");
            } else {
                //创建热点成功
                if (hotsposListener != null)
                    hotsposListener.openHotspotSuccess();
//            ToastUtils.showLongToast("创建热点成功");
            }
        }
    }

    /**
     * 注册便携热点状态接收器
     */
    private void registerHotSpotReceiver() {
        if (mHotSpotBroadcaseReceiver == null) {
            mHotSpotBroadcaseReceiver = new HotSpotBroadcaseReceiver() {
                @Override
                public void onHotSpotEnabled() {
                    //热点成功开启
                    if (!mIsInitialized) {
                        mIsInitialized = true;
//                    成功开启热点
                        //等待接收端连接
                        Runnable mUdpServerRunnable = receiveInitSuccessOrderRunnable();
                        BaseApplication.MAIN_EXECUTOR.execute(mUdpServerRunnable);
                    }
                }
            };
        }
        IntentFilter filter = new IntentFilter(HotSpotBroadcaseReceiver.ACTION_HOTSPOT_STATE_CHANGED);
        context.registerReceiver(mHotSpotBroadcaseReceiver, filter);

    }

    /**
     * 等待接收端发送初始化完成指令线程
     */

    private Runnable receiveInitSuccessOrderRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    //开始接收接收端发来的指令
                    receiveInitSuccessOrder(Consts.DEFAULT_SERVER_UDP_PORT);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private void requestPermission(String[] permissions, int requestCode) {
        int mRequestCode = requestCode;
        if (checkPermissions(permissions)) {
            //获取创建便携热点权限成功
            mIsPermissionGranted = requestCode == PERMISSION_REQ_CREATE_HOTSPOT;
        }
    }

    /**
     * 检查所需的权限是否都已授权
     */
    private boolean checkPermissions(String[] permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 等待接收端发送初始化完成指令，向其发送文件列表
     */
    private void receiveInitSuccessOrder(int serverPort) throws Exception {

        //确保WiFi连接后获取正确IP地址
        int tryCount = 0;
        String localIpAddress = ApMgr.getHotspotLocalIpAddress(context);
        while (localIpAddress.equals(Consts.DEFAULT_UNKNOW_IP) && tryCount < Consts.DEFAULT_TRY_COUNT) {
            Thread.sleep(1000);
            localIpAddress = ApMgr.getHotspotLocalIpAddress(context);
            tryCount++;
        }

         /*
          这里使用UDP发送和接收指令 *
          Udp Socket
          */
        mDatagramSocket = new DatagramSocket(serverPort);
        while (true) {
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            mDatagramSocket.receive(receivePacket);
            String response = new String(receivePacket.getData(), "utf-8");
            response = response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1);
            Logger.e("开启热点  接收到的消息 -------->>>" + response);
            final HotspotRequest hotspotRequest = JsonParser.parseHotspotRequest(response);
            switch (hotspotRequest.getMsg()) {
                case "connect_success":       // 对方连接热点成功
                    InetAddress inetAddress = receivePacket.getAddress();
                    int port = receivePacket.getPort();
                    //通过UDP发送文件列表给接收端
                    sendFileInfoListToFileReceiverWithUdp(inetAddress, port);
                    break;

                case "received_psw":     // 对方收到WIFI信息成功
                    //  关闭热点
                    if (ApMgr.isApOn(context)) {
                        ApMgr.closeAp(context);
                    }
                    //  打开Wifi
                    wifiMgr.openWifi();
                    PreferUtil.getInstance().setConnectWifi(true);
                    PreferUtil.getInstance().setConnectWifiSSID(WIFISSID);
                    break;
            }
        }
    }

    /**
     * 通过UDP发送文件列表给接收端
     */

    private void sendFileInfoListToFileReceiverWithUdp(InetAddress ipAddress, int serverPort) {

        WifiData wifiInfo = new WifiData();
        wifiInfo.setMsg("wifi_psw");
        wifiInfo.setPassword(PWD);
        wifiInfo.setSsid(WIFISSID);
        wifiInfo.setCode(1001);

        String jsonStr = WifiData.toJsonStr(wifiInfo);
        DatagramPacket sendFileInfoPacket = new DatagramPacket(jsonStr.getBytes(), jsonStr.getBytes().length, ipAddress, serverPort);
        try {
            //发送文件列表
            mDatagramSocket.send(sendFileInfoPacket);
            LogUtils.i("发送消息 --------->>>" + jsonStr + "=== Success!");
        } catch (IOException e) {
            e.printStackTrace();
            LogUtils.i("发送消息 --------->>>" + jsonStr + "=== 失败！");
        }
    }

    @Override
    public void WifiChangeSuccess() {
        // 关闭热点重连Wifi
        if (hotsposListener != null)
            hotsposListener.connectWifiSuccess();
    }
}
