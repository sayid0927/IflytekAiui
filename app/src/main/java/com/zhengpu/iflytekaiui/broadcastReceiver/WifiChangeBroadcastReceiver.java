package com.zhengpu.iflytekaiui.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import com.zhengpu.iflytekaiui.utils.PreferUtil;
import com.zhengpu.iflytekaiui.wifitools.WifiMgr;

/**
 * sayid ....
 * Created by wengmf on 2018/1/29.
 */

public class WifiChangeBroadcastReceiver extends BroadcastReceiver {
    private Context mContext;
    private  WifiChangeListener wifiChangeListener;

    public  void  setWifiChangeListener(WifiChangeListener wifiChangeListener){
        this.wifiChangeListener = wifiChangeListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext=context;
        getWifiInfo();
    }

    private void getWifiInfo() {
        WifiManager wifiManager = (WifiManager) mContext.getSystemService(mContext.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();

        if (wifiInfo.getBSSID() != null) {
           if(PreferUtil.getInstance().getConnectWifi()){
               //wifi名称
               WifiMgr wifiMgr = new WifiMgr(mContext);
               if(wifiMgr.getConnectedSSID().equals(PreferUtil.getInstance().getConnectWifiSSID())){
                   if(wifiChangeListener!=null) {

                       wifiChangeListener.WifiChangeSuccess();
                       PreferUtil.getInstance().setConnectWifi(false);

                   }
               }
           }
        }
    }
}