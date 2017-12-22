package com.zhengpu.iflytekaiui.iflytekbean;

/**
 * sayid ....
 * Created by wengmf on 2017/11/29.
 */

public class StoryBean extends BaseBean {


    /**
     * save_history : true
     * rc : 0
     * semantic : [{"intent":"RANDOM_QUERY","slots":[]}]
     * service : story
     * state : {"fg::story::default::default":{"state":"default"}}
     * text : 讲个故事
     * uuid : atn0047697e@ch20690d7965896f2601
     * used_state : {"state_key":"fg::story::default::default","state":"default"}
     * answer : {"text":"请欣赏鳄鱼爱上长颈鹿."}
     * dialog_stat : dataInvalid
     * sid : atn0047697e@ch20690d7965896f2601
     */

    private boolean save_history;
    private int rc;
    private String service;
    private String text;
    private String uuid;
    private AnswerBean answer;
    private String dialog_stat;
    private String sid;


    public static class AnswerBean {

        private String text;


        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

    }



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


}
