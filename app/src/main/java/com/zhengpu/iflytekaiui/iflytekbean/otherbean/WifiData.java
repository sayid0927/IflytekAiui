package com.zhengpu.iflytekaiui.iflytekbean.otherbean;

import com.google.gson.Gson;

import java.io.Serializable;

public class WifiData implements Serializable {



    /***
     *Wifi密码
     */
    private String password;

    /***
     *WifiSsid
     */
    private String ssid;

    private String  msg;


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /***
     *
     */
    private int code;
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public static String toJsonStr(WifiData wifiInfo) {
        return new Gson().toJson(wifiInfo);
    }


    public static WifiData toObject(String jsonStr) {
        return new Gson().fromJson(jsonStr, WifiData.class);
    }

    @Override
    public String toString() {
        return "WifiInfo:{" +
                "ssid='" + ssid + '\'' +
                ", password=" + password +
                ", code=" + code +
                ", msg=" + msg +
                '}';
    }
}
