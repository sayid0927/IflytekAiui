package com.zhengpu.iflytekaiui.iflytekbean;

import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2017/12/1.
 */

public class FlightBean extends BaseBean {


    /**
     * data : {"result":[{"aPort":"长乐国际机场","airline":"厦门航空有限公司","arriveCity":"福州","arriveTime":"2017-12-21 11:55:00","arriveTimeStamp":"1513828500","cabinInfo":"经济舱","dPort":"宝安国际机场","departCity":"深圳","endtime_for_voice":"明天11:55:00","endtimestamp":1513828500,"flight":"MF8077","price":"1070","quantity":"10","rate":"0.99","standardPrice":"1080","starttime_for_voice":"明天10:20:00","starttimestamp":1513822800,"takeOffTime":"2017-12-21 10:20:00","takeOffTimeStamp":"1513822800"},{"aPort":"长乐国际机场","airline":"厦门航空有限公司","arriveCity":"福州","arriveTime":"2017-12-21 11:55:00","arriveTimeStamp":"1513828500","cabinInfo":"经济舱","dPort":"宝安国际机场","departCity":"深圳","endtime_for_voice":"明天11:55:00","endtimestamp":1513828500,"flight":"MF8077","price":"1080","quantity":"10","rate":"1","standardPrice":"1080","starttime_for_voice":"明天10:20:00","starttimestamp":1513822800,"takeOffTime":"2017-12-21 10:20:00","takeOffTimeStamp":"1513822800"},{"aPort":"长乐国际机场","airline":"厦门航空有限公司","arriveCity":"福州","arriveTime":"2017-12-21 23:45:00","arriveTimeStamp":"1513871100","cabinInfo":"经济舱","dPort":"宝安国际机场","departCity":"深圳","endtime_for_voice":"明天23:45:00","endtimestamp":1513871100,"flight":"MF8368","price":"1070","quantity":"10","rate":"0.99","standardPrice":"1080","starttime_for_voice":"明天22:20:00","starttimestamp":1513866000,"takeOffTime":"2017-12-21 22:20:00","takeOffTimeStamp":"1513866000"},{"aPort":"长乐国际机场","airline":"厦门航空有限公司","arriveCity":"福州","arriveTime":"2017-12-21 23:45:00","arriveTimeStamp":"1513871100","cabinInfo":"经济舱","dPort":"宝安国际机场","departCity":"深圳","endtime_for_voice":"明天23:45:00","endtimestamp":1513871100,"flight":"MF8368","price":"1080","quantity":"10","rate":"1","standardPrice":"1080","starttime_for_voice":"明天22:20:00","starttimestamp":1513866000,"takeOffTime":"2017-12-21 22:20:00","takeOffTimeStamp":"1513866000"},{"aPort":"长乐国际机场","airline":"中国东方航空股份有限公司","arriveCity":"福州","arriveTime":"2017-12-21 23:45:00","arriveTimeStamp":"1513871100","cabinInfo":"经济舱","dPort":"宝安国际机场","departCity":"深圳","endtime_for_voice":"明天23:45:00","endtimestamp":1513871100,"flight":"MU4112","price":"1080","quantity":"10","rate":"1","standardPrice":"0","starttime_for_voice":"明天22:20:00","starttimestamp":1513866000,"takeOffTime":"2017-12-21 22:20:00","takeOffTimeStamp":"1513866000"}]}
     * rc : 0
     * semantic : [{"intent":"QUERY","slots":[{"name":"endLoc.city","value":"福州市","normValue":"福州市"},{"name":"endLoc.cityAddr","value":"福州","normValue":"福州"},{"name":"endLoc.type","value":"LOC_BASIC","normValue":"LOC_BASIC"},{"name":"startDate","value":"明天","normValue":"{\"datetime\":\"2017-12-21\",\"suggestDatetime\":\"2017-12-21\"}"},{"name":"startLoc.city","value":"深圳市","normValue":"深圳市"},{"name":"startLoc.cityAddr","value":"深圳","normValue":"深圳"},{"name":"startLoc.type","value":"LOC_BASIC","normValue":"LOC_BASIC"}]}]
     * service : flight
     * state : {"fg::flight::default::default":{"state":"default"}}
     * text : 明天
     * uuid : atn001c0fd0@ch0d760d9546bf6f2a01
     * answer : {"text":"[n2]为您找到5个班次，推荐明天10:20:00从深圳出发，明天11:55:00到达福州的[n1][h1]MF8077的经济舱[h0][n0]，价格是[h2]1070元[h0]，航空公司为厦门航空有限公司"}
     * dialog_stat : DataValid
     * save_history : true
     * sid : atn001c0fd0@ch0d760d9546bf6f2a01
     */

    private DataBean data;
    private int rc;
    private String service;
    private String text;
    private String uuid;
    private PoetryBean.AnswerBean answer;
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

    public PoetryBean.AnswerBean getAnswer() {
        return answer;
    }

    public void setAnswer(PoetryBean.AnswerBean answer) {
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

    public static class DataBean {
        private List<ResultBean> result;

        public List<ResultBean> getResult() {
            return result;
        }

        public void setResult(List<ResultBean> result) {
            this.result = result;
        }

        public static class ResultBean {
            /**
             * aPort : 长乐国际机场
             * airline : 厦门航空有限公司
             * arriveCity : 福州
             * arriveTime : 2017-12-21 11:55:00
             * arriveTimeStamp : 1513828500
             * cabinInfo : 经济舱
             * dPort : 宝安国际机场
             * departCity : 深圳
             * endtime_for_voice : 明天11:55:00
             * endtimestamp : 1513828500
             * flight : MF8077
             * price : 1070
             * quantity : 10
             * rate : 0.99
             * standardPrice : 1080
             * starttime_for_voice : 明天10:20:00
             * starttimestamp : 1513822800
             * takeOffTime : 2017-12-21 10:20:00
             * takeOffTimeStamp : 1513822800
             */

            private String aPort;
            private String airline;
            private String arriveCity;
            private String arriveTime;
            private String arriveTimeStamp;
            private String cabinInfo;
            private String dPort;
            private String departCity;
            private String endtime_for_voice;
            private int endtimestamp;
            private String flight;
            private String price;
            private String quantity;
            private String rate;
            private String standardPrice;
            private String starttime_for_voice;
            private int starttimestamp;
            private String takeOffTime;
            private String takeOffTimeStamp;

            public String getAPort() {
                return aPort;
            }

            public void setAPort(String aPort) {
                this.aPort = aPort;
            }

            public String getAirline() {
                return airline;
            }

            public void setAirline(String airline) {
                this.airline = airline;
            }

            public String getArriveCity() {
                return arriveCity;
            }

            public void setArriveCity(String arriveCity) {
                this.arriveCity = arriveCity;
            }

            public String getArriveTime() {
                return arriveTime;
            }

            public void setArriveTime(String arriveTime) {
                this.arriveTime = arriveTime;
            }

            public String getArriveTimeStamp() {
                return arriveTimeStamp;
            }

            public void setArriveTimeStamp(String arriveTimeStamp) {
                this.arriveTimeStamp = arriveTimeStamp;
            }

            public String getCabinInfo() {
                return cabinInfo;
            }

            public void setCabinInfo(String cabinInfo) {
                this.cabinInfo = cabinInfo;
            }

            public String getDPort() {
                return dPort;
            }

            public void setDPort(String dPort) {
                this.dPort = dPort;
            }

            public String getDepartCity() {
                return departCity;
            }

            public void setDepartCity(String departCity) {
                this.departCity = departCity;
            }

            public String getEndtime_for_voice() {
                return endtime_for_voice;
            }

            public void setEndtime_for_voice(String endtime_for_voice) {
                this.endtime_for_voice = endtime_for_voice;
            }

            public int getEndtimestamp() {
                return endtimestamp;
            }

            public void setEndtimestamp(int endtimestamp) {
                this.endtimestamp = endtimestamp;
            }

            public String getFlight() {
                return flight;
            }

            public void setFlight(String flight) {
                this.flight = flight;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getQuantity() {
                return quantity;
            }

            public void setQuantity(String quantity) {
                this.quantity = quantity;
            }

            public String getRate() {
                return rate;
            }

            public void setRate(String rate) {
                this.rate = rate;
            }

            public String getStandardPrice() {
                return standardPrice;
            }

            public void setStandardPrice(String standardPrice) {
                this.standardPrice = standardPrice;
            }

            public String getStarttime_for_voice() {
                return starttime_for_voice;
            }

            public void setStarttime_for_voice(String starttime_for_voice) {
                this.starttime_for_voice = starttime_for_voice;
            }

            public int getStarttimestamp() {
                return starttimestamp;
            }

            public void setStarttimestamp(int starttimestamp) {
                this.starttimestamp = starttimestamp;
            }

            public String getTakeOffTime() {
                return takeOffTime;
            }

            public void setTakeOffTime(String takeOffTime) {
                this.takeOffTime = takeOffTime;
            }

            public String getTakeOffTimeStamp() {
                return takeOffTimeStamp;
            }

            public void setTakeOffTimeStamp(String takeOffTimeStamp) {
                this.takeOffTimeStamp = takeOffTimeStamp;
            }
        }
    }
}
