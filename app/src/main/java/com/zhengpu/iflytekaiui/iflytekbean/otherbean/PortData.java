package com.zhengpu.iflytekaiui.iflytekbean.otherbean;

import com.google.gson.Gson;

import java.io.Serializable;

public class PortData implements Serializable {

    private String batteryState;
    private String  batteryLevel;

    public String getBatteryState() {
        return batteryState;
    }

    public void setBatteryState(String batteryState) {
        this.batteryState = batteryState;
    }

    public String getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(String batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public static String toJsonStr(PortData wifiInfo) {
        return new Gson().toJson(wifiInfo);
    }

    public static PortData toObject(String jsonStr) {
        return new Gson().fromJson(jsonStr, PortData.class);
    }

    @Override
    public String toString() {
        return "PortData{" +
                "batteryState='" + batteryState + '\'' +
                ", batteryLevel='" + batteryLevel + '\'' +
                '}';
    }

}
