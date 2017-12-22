package com.zhengpu.iflytekaiui.iflytekbean;

import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2017/12/14.
 */

public class CmdBean {


    /**
     * man_intv : TOP1
     * rc : 0
     * semantic : [{"intent":"INSTRUCTION","slots":[{"name":"insType","value":"volume_minus"}]}]
     * service : cmd
     * state : {"fg::cmd::default::default":{"state":"default"}}
     * text : 音量小点
     * uuid : atn001d453a@ch5db30d8d6b476f2a01
     * sid : atn001d453a@ch5db30d8d6b476f2a01
     */

    private String man_intv;
    private int rc;
    private String service;
    private String text;
    private String uuid;
    private String sid;
    private List<SemanticBean> semantic;


    public String getMan_intv() {
        return man_intv;
    }

    public void setMan_intv(String man_intv) {
        this.man_intv = man_intv;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public List<SemanticBean> getSemantic() {
        return semantic;
    }

    public void setSemantic(List<SemanticBean> semantic) {
        this.semantic = semantic;
    }

    public static class SemanticBean {

        private String intent;
        private List<SlotsBean> slots;


        public String getIntent() {
            return intent;
        }

        public void setIntent(String intent) {
            this.intent = intent;
        }

        public List<SlotsBean> getSlots() {
            return slots;
        }

        public void setSlots(List<SlotsBean> slots) {
            this.slots = slots;
        }

        public static class SlotsBean {
            /**
             * semantic : [{"intent":"name","slots":[{"name":"song","value":"我的地盘"}]}]
             */
            private String name;
            private String value;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }
}
