package com.zhengpu.iflytekaiui.iflytekbean;

import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2018/1/29.
 */

public class TelephoneBean {


    /**
     * data : {"error":{"code":3306020,"message":"get personal_res_text fail"},"result":null}
     * rc : 3
     * semantic : [{"intent":"DIAL","slots":[{"name":"name","value":"张三"}]}]
     * service : telephone
     * uuid : atn003d4d78@ch13c70dca20726f1d01
     * text : 给张三打电话
     * state : {"fg::telephone::default::default":{"name":"1","operation":"1","state":"default"}}
     * used_state : {"name":"1","operation":"1","state":"default","state_key":"fg::telephone::default::default"}
     * answer : {"text":"没有为您找到张三.请确认要拨打的联系人"}
     * dialog_stat : dataInvalid
     * save_history : true
     * sid : atn003d4d78@ch13c70dca20726f1d01
     */


    private DataBean data;
    private int rc;
    private String service;
    private String uuid;
    private String text;
    private AnswerBean answer;
    private String dialog_stat;
    private boolean save_history;
    private String sid;
    private List<SemanticBean> semantic;


    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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

    public boolean isSave_history() {
        return save_history;
    }

    public void setSave_history(boolean save_history) {
        this.save_history = save_history;
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

    public static  class  SemanticBean{

        private  String intent;

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

        public static class  SlotsBean{
            private  String name;
            private  String value;

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

    public  static  class  AnswerBean{
        private  String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public static class DataBean {
        /**
         * error : {"code":3306020,"message":"get personal_res_text fail"}
         * result : null
         */

        private ErrorBean error;
        private Object result;

        public ErrorBean getError() {
            return error;
        }

        public void setError(ErrorBean error) {
            this.error = error;
        }

        public Object getResult() {
            return result;
        }

        public void setResult(Object result) {
            this.result = result;
        }

        public static class ErrorBean {
            /**
             * code : 3306020
             * message : get personal_res_text fail
             */

            private int code;
            private String message;

            public int getCode() {
                return code;
            }

            public void setCode(int code) {
                this.code = code;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }
        }
    }
}
