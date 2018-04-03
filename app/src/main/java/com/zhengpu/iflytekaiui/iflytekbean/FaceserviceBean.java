package com.zhengpu.iflytekaiui.iflytekbean;

import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2018/3/16.
 */

public class FaceserviceBean  extends BaseBean{


    /**
     * category : OPENAPPTEST.Faceservice
     * intentType : custom
     * rc : 0
     * service : OPENAPPTEST.Faceservice
     * uuid : atn000e9f0f@ch1aa50e06bb496f1d01
     * vendor : OPENAPPTEST
     * version : 1.0
     * semantic : [{"entrypoint":"ent","intent":"stratFaceservice","score":1,"slots":[]}]
     * state : null
     * sid : atn000e9f0f@ch1aa50e06bb496f1d01
     * text : 多多你好
     */

    private String category;
    private String intentType;
    private int rc;
    private String service;
    private String uuid;
    private String vendor;
    private String version;
    private Object state;
    private String sid;
    private String text;
    private List<SemanticBean> semantic;

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

    public int getRc() {
        return rc;
    }

    public void setRc(int rc) {
        this.rc = rc;
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

    public Object getState() {
        return state;
    }

    public void setState(Object state) {
        this.state = state;
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

    public List<SemanticBean> getSemantic() {
        return semantic;
    }

    public void setSemantic(List<SemanticBean> semantic) {
        this.semantic = semantic;
    }

    public static class SemanticBean {
        /**
         * entrypoint : ent
         * intent : stratFaceservice
         * score : 1
         * slots : []
         */

        private String entrypoint;
        private String intent;
        private int score;
        private List<?> slots;

        public String getEntrypoint() {
            return entrypoint;
        }

        public void setEntrypoint(String entrypoint) {
            this.entrypoint = entrypoint;
        }

        public String getIntent() {
            return intent;
        }

        public void setIntent(String intent) {
            this.intent = intent;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public List<?> getSlots() {
            return slots;
        }

        public void setSlots(List<?> slots) {
            this.slots = slots;
        }
    }
}
