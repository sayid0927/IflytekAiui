package com.zhengpu.iflytekaiui.iflytekbean;

/**
 * Created by Administrator on 2017/11/23 0023.
 */

public class DatetimeBean extends BaseBean {


    /**
     * answer : {"text":"今天是2017年11月23日 丁酉年十月初六 星期四","type":"T"}
     * match_info : {"type":"gparser_path","value":"-----"}
     * operation : ANSWER
     * rc : 0
     * service : datetime
     * state : null
     * text : 今天星期几
     * uuid : atn00e23eeb@chb04f0d7236086f2a01
     * sid : atn00e23eeb@chb04f0d7236086f2a01
     */

    private AnswerBean answer;
    private MatchInfoBean match_info;
    private String operation;
    private int rc;
    private String service;
    private Object state;
    private String text;
    private String uuid;
    private String sid;


    public AnswerBean getAnswer() {
        return answer;
    }

    public void setAnswer(AnswerBean answer) {
        this.answer = answer;
    }

    public MatchInfoBean getMatch_info() {
        return match_info;
    }

    public void setMatch_info(MatchInfoBean match_info) {
        this.match_info = match_info;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
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

    public Object getState() {
        return state;
    }

    public void setState(Object state) {
        this.state = state;
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

    public static class AnswerBean {
        /**
         * text : 今天是2017年11月23日 丁酉年十月初六 星期四
         * type : T
         */

        private String text;
        private String type;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class MatchInfoBean {
        /**
         * type : gparser_path
         * value : -----
         */

        private String type;
        private String value;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
