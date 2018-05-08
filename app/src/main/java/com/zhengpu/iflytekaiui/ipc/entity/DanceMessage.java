package com.zhengpu.iflytekaiui.ipc.entity;

/**
 * sayid ....
 * Created by wengmf on 2017/12/26.
 */

public class DanceMessage {
    private String message;
    private String service;
    private String pkg;

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
