package com.zhengpu.iflytekaiui.iflytekbean.otherbean;

/**
 * sayid ....
 * Created by wengmf on 2018/1/27.
 */

public class HotspotRequest {

    private  int code;
    private String ssid;
    private  int order;
    private  int status;
    private  String msg;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
