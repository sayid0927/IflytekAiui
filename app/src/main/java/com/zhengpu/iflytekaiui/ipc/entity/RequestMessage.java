package com.zhengpu.iflytekaiui.ipc.entity;

public class RequestMessage {

    private String message;

    private  String service;

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
