package com.zhengpu.watch.iflytekbean;

/**
 * Created by Administrator on 2017/12/24 0024.
 */

public class CustomBaikeBean {


    /**
     * category : OPENAPPTEST.custom_baike
     * intentType : custom
     * query : 深圳到福州
     * query_ws : 深圳/NS_S// 到/P// 福州/NS_S//
     * rc : 0
     * nlis : true
     * service : OPENAPPTEST.custom_baike
     * uuid : atn0001c4f7@ch75750d9dcb446f2001
     * vendor : OPENAPPTEST
     * version : 3.0
     * semantic : [{"entrypoint":"ent","intent":"baike","score":0.9345765709877014,"slots":[{"begin":0,"end":2,"name":"ChinaCity","normValue":"深圳市","value":"深圳"}],"template":"{ChinaCity}"}]
     * state : {"fg::flight::default::default":{"state":"default"}}
     * sid : iata49512ff@gz02270d9dcb40507500
     * text : 深圳到福州
     */

    private String category;
    private String intentType;
    private String query;
    private String query_ws;
    private int rc;
    private String nlis;
    private String service;
    private String uuid;
    private String vendor;
    private String version;
    private String sid;
    private String text;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIntentType() {
        return intentType;
    }

    public void setIntentType(String intentType) {
        this.intentType = intentType;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQuery_ws() {
        return query_ws;
    }

    public void setQuery_ws(String query_ws) {
        this.query_ws = query_ws;
    }

    public int getRc() {
        return rc;
    }

    public void setRc(int rc) {
        this.rc = rc;
    }

    public String getNlis() {
        return nlis;
    }

    public void setNlis(String nlis) {
        this.nlis = nlis;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
