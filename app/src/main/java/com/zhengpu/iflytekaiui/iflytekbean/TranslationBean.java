package com.zhengpu.iflytekaiui.iflytekbean;

import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2018/5/10.
 */

public class TranslationBean {


    /**
     * data : {"result":[{"oriLangCountry":"cn","original":"有志者事竟成","transLangCountry":"en","translated":"Where there is a will there is a way"}]}
     * rc : 0
     * semantic : [{"intent":"TRANSLATION","slots":[{"name":"content","value":"有志者事竟成"},{"name":"source","value":"cn"},{"name":"target","value":"en"}]}]
     * service : translation
     * state : {"fg::translation::default::default":{"state":"default"}}
     * text : 我想翻译有志者事竟成
     * uuid : atn00a44fd2@ch3e410e4f323e6f2001
     * answer : {"text":"\"有志者事竟成\"的英文是\"Where there is a will there is a way\""}
     * dialog_stat : dataInvalid
     * save_history : true
     * sid : atn00a44fd2@ch3e410e4f323e6f2001
     */

    private DataBean data;
    private int rc;
    private String service;
    private String text;
    private String uuid;
    private AnswerBean answer;
    private String dialog_stat;
    private boolean save_history;
    private String sid;

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

    public  static  class  AnswerBean{

        // text": "\"有志者事竟成\"的英文是\"Where there is a will there is a way\""
        private  String text ;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public static class DataBean {
        private java.util.List<ResultBean> result;

        public List<ResultBean> getResult() {
            return result;
        }

        public void setResult(List<ResultBean> result) {
            this.result = result;
        }

        public static class ResultBean {
            /**
             * oriLangCountry : cn
             * original : 有志者事竟成
             * transLangCountry : en
             * translated : Where there is a will there is a way
             */

            private String oriLangCountry;
            private String original;
            private String transLangCountry;
            private String translated;

            public String getOriLangCountry() {
                return oriLangCountry;
            }

            public void setOriLangCountry(String oriLangCountry) {
                this.oriLangCountry = oriLangCountry;
            }

            public String getOriginal() {
                return original;
            }

            public void setOriginal(String original) {
                this.original = original;
            }

            public String getTransLangCountry() {
                return transLangCountry;
            }

            public void setTransLangCountry(String transLangCountry) {
                this.transLangCountry = transLangCountry;
            }

            public String getTranslated() {
                return translated;
            }

            public void setTranslated(String translated) {
                this.translated = translated;
            }
        }
    }
}
