package com.zhengpu.iflytekaiui.iflytekbean;

import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2018/5/10.
 */

public class StockBean {


    /**
     * data : {"result":[{"closingPrice":"30536.14","currentPrice":"30746.68","detail":"","highPrice":"30851.30","lowPrice":"30712.93","mbmChartUrl":"","name":"恒生指数","openingPrice":"30716.97","riseRate":"0.69%","riseValue":"210.54","source":"iflytek","stockCode":"hkHSI","updateDateTime":"2018-05-10 14:01:00","url":""}]}
     * rc : 0
     * semantic : [{"intent":"QUERY","slots":[{"name":"code","value":"HSI"},{"name":"market","value":"hk"},{"name":"name","value":"恒生指数"}]}]
     * service : stock
     * state : {"fg::stock::default::default":{"state":"default"}}
     * text : 今天的港股大盘
     * uuid : atn00a3723f@ch6a6d0e4f2c6d6f1d01
     * used_state : {"state_key":"fg::stock::default::default","state":"default"}
     * answer : {"text":"恒生指数现在为30746.68点"}
     * dialog_stat : dataInvalid
     * save_history : true
     * sid : atn00a3723f@ch6a6d0e4f2c6d6f1d01
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

        //  "text": "恒生指数现在为30746.68点"

        private  String text;

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
             * closingPrice : 30536.14
             * currentPrice : 30746.68
             * detail :
             * highPrice : 30851.30
             * lowPrice : 30712.93
             * mbmChartUrl :
             * name : 恒生指数
             * openingPrice : 30716.97
             * riseRate : 0.69%
             * riseValue : 210.54
             * source : iflytek
             * stockCode : hkHSI
             * updateDateTime : 2018-05-10 14:01:00
             * url :
             */

            private String closingPrice;
            private String currentPrice;
            private String detail;
            private String highPrice;
            private String lowPrice;
            private String mbmChartUrl;
            private String name;
            private String openingPrice;
            private String riseRate;
            private String riseValue;
            private String source;
            private String stockCode;
            private String updateDateTime;
            private String url;

            public String getClosingPrice() {
                return closingPrice;
            }

            public void setClosingPrice(String closingPrice) {
                this.closingPrice = closingPrice;
            }

            public String getCurrentPrice() {
                return currentPrice;
            }

            public void setCurrentPrice(String currentPrice) {
                this.currentPrice = currentPrice;
            }

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public String getHighPrice() {
                return highPrice;
            }

            public void setHighPrice(String highPrice) {
                this.highPrice = highPrice;
            }

            public String getLowPrice() {
                return lowPrice;
            }

            public void setLowPrice(String lowPrice) {
                this.lowPrice = lowPrice;
            }

            public String getMbmChartUrl() {
                return mbmChartUrl;
            }

            public void setMbmChartUrl(String mbmChartUrl) {
                this.mbmChartUrl = mbmChartUrl;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getOpeningPrice() {
                return openingPrice;
            }

            public void setOpeningPrice(String openingPrice) {
                this.openingPrice = openingPrice;
            }

            public String getRiseRate() {
                return riseRate;
            }

            public void setRiseRate(String riseRate) {
                this.riseRate = riseRate;
            }

            public String getRiseValue() {
                return riseValue;
            }

            public void setRiseValue(String riseValue) {
                this.riseValue = riseValue;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getStockCode() {
                return stockCode;
            }

            public void setStockCode(String stockCode) {
                this.stockCode = stockCode;
            }

            public String getUpdateDateTime() {
                return updateDateTime;
            }

            public void setUpdateDateTime(String updateDateTime) {
                this.updateDateTime = updateDateTime;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }

}
