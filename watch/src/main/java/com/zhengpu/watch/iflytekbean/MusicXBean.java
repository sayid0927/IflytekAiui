package com.zhengpu.watch.iflytekbean;

import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2017/11/24.
 */

public class MusicXBean extends BaseBean {


    /**
     * save_history : true
     * rc : 0
     * semantic : [{"intent":"PLAY","slots":[{"name":"song","value":"我的地盘"}]}]
     * service : musicX
     * uuid : atn0000b054@ch20690d72cf246f2601
     * text : 播放我的地盘
     * state : {"fg::musicX::default::playing":{"song":"1","state":"playing"}}
     * used_state : {"song":"1","state":"playing","state_key":"fg::musicX::default::playing"}
     * answer : {"text":"请欣赏周杰伦的歌曲我的地盘"}
     * dialog_stat : DataValid
     * sid : atn0000b054@ch20690d72cf246f2601
     */

    private boolean save_history;
    private int rc;
    private String service;
    private String uuid;
    private String text;
    private AnswerBean answer;
    private String dialog_stat;
    private String sid;
    private List<SemanticBean> semantic;


    public boolean isSave_history() {
        return save_history;
    }

    public void setSave_history(boolean save_history) {
        this.save_history = save_history;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public AnswerBean getAnswer() {
        return answer;
    }

    public void setAnswer(AnswerBean answer) {
        this.answer = answer;
    }

    public String getDialog_stat() {
        return dialog_stat;
    }

    public void setDialog_stat(String dialog_stat) {
        this.dialog_stat = dialog_stat;
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

    public static class AnswerBean {


        private String text;


        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

    }

    public static class SemanticBean {
        /**
         * semantic : [{"intent":"PLAY","slots":[{"name":"song","value":"我的地盘"}]}]
         */

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
             * semantic : [{"intent":"PLAY","slots":[{"name":"song","value":"我的地盘"}]}]
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
