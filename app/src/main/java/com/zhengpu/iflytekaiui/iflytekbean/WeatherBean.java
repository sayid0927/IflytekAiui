package com.zhengpu.iflytekaiui.iflytekbean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/23 0023.
 */

public class WeatherBean extends BaseBean {


    /**
     * data : {"result":[{"airData":85,"airQuality":"良","city":"上海","date":"2017-11-23","dateLong":1511366400,
     * "exp":{"ct":{"expName":"穿衣指数","level":"较冷","prompt":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"}},
     * "humidity":"44%","lastUpdateTime":"2017-11-23 18:00","pm25":"58","temp":8,"tempRange":"5℃","weather":"晴","weatherType":0,"wind":"西风微风","windLevel":0},
     * {"city":"上海","date":"2017-11-24","dateLong":1511452800,"lastUpdateTime":"2017-11-23 18:00","tempRange":"7℃ ~ 14℃","weather":"晴转多云","weatherType":0,"wind":"西风微风","windLevel":0},
     * {"city":"上海","date":"2017-11-25","dateLong":1511539200,"lastUpdateTime":"2017-11-23 18:00","tempRange":"8℃ ~ 15℃","weather":"多云转阴","weatherType":1,"wind":"西南风微风","windLevel":0},
     * {"city":"上海","date":"2017-11-26","dateLong":1511625600,"lastUpdateTime":"2017-11-23 18:00","tempRange":"7℃ ~ 17℃","weather":"多云转晴","weatherType":1,"wind":"北风微风","windLevel":0},
     * {"city":"上海","date":"2017-11-27","dateLong":1511712000,"lastUpdateTime":"2017-11-23 18:00","tempRange":"8℃ ~ 16℃","weather":"晴转多云","weatherType":0,"wind":"东南风微风","windLevel":0},
     * {"city":"上海","date":"2017-11-28","dateLong":1511798400,"lastUpdateTime":"2017-11-23 18:00","tempRange":"11℃ ~ 17℃","weather":"小雨转多云","weatherType":7,"wind":"西北风微风","windLevel":0},
     * {"city":"上海","date":"2017-11-29","dateLong":1511884800,"lastUpdateTime":"2017-11-23 18:00","tempRange":"10℃ ~ 15℃","weather":"小雨转大雨","weatherType":7,"wind":"东北风3-4级","windLevel":1}]}
     * rc : 0
     * semantic : [{"intent":"QUERY","slots":[{"name":"location.city","value":"上海市","normValue":"上海市"},{"name":"location.cityAddr","value":"上海","normValue":"上海"},{"name":"location.type","value":"LOC_BASIC","normValue":"LOC_BASIC"},{"name":"queryType","value":"内容"},{"name":"subfocus","value":"天气状态"}]}]
     * service : weather
     * state : {"fg::weather::default::default":{"state":"default"}}
     * text : 上海天气怎么样
     * uuid : atn00e99e8a@ch74900d723ae96f2601
     * used_state : {"state_key":"fg::weather::default::default","state":"default"}
     * answer : {"text":"\"上海\"今天\"晴\"，\"5℃\"，\"西风微风\""}
     * dialog_stat : DataValid
     * save_history : true
     * sid : atn00e99e8a@ch74900d723ae96f2601
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public AnswerBean getAnswer() {
        return answer;
    }

    public void setAnswer(AnswerBean answer) {
        this.answer = answer;
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
             * airData : 85
             * airQuality : 良
             * city : 上海
             * date : 2017-11-23
             * dateLong : 1511366400
             * exp : {"ct":{"expName":"穿衣指数","level":"较冷","prompt":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"}}
             * humidity : 44%
             * lastUpdateTime : 2017-11-23 18:00
             * pm25 : 58
             * temp : 8
             * tempRange : 5℃
             * weather : 晴
             * weatherType : 0
             * wind : 西风微风
             * windLevel : 0
             */

            private int airData;
            private String airQuality;
            private String city;
            private String date;
            private int dateLong;
            private ExpBean exp;
            private String humidity;
            private String lastUpdateTime;
            private String pm25;
            private int temp;
            private String tempRange;
            private String weather;
            private int weatherType;
            private String wind;
            private int windLevel;

            public int getAirData() {
                return airData;
            }

            public void setAirData(int airData) {
                this.airData = airData;
            }

            public String getAirQuality() {
                return airQuality;
            }

            public void setAirQuality(String airQuality) {
                this.airQuality = airQuality;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public int getDateLong() {
                return dateLong;
            }

            public void setDateLong(int dateLong) {
                this.dateLong = dateLong;
            }

            public ExpBean getExp() {
                return exp;
            }

            public void setExp(ExpBean exp) {
                this.exp = exp;
            }

            public String getHumidity() {
                return humidity;
            }

            public void setHumidity(String humidity) {
                this.humidity = humidity;
            }

            public String getLastUpdateTime() {
                return lastUpdateTime;
            }

            public void setLastUpdateTime(String lastUpdateTime) {
                this.lastUpdateTime = lastUpdateTime;
            }

            public String getPm25() {
                return pm25;
            }

            public void setPm25(String pm25) {
                this.pm25 = pm25;
            }

            public int getTemp() {
                return temp;
            }

            public void setTemp(int temp) {
                this.temp = temp;
            }

            public String getTempRange() {
                return tempRange;
            }

            public void setTempRange(String tempRange) {
                this.tempRange = tempRange;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public int getWeatherType() {
                return weatherType;
            }

            public void setWeatherType(int weatherType) {
                this.weatherType = weatherType;
            }

            public String getWind() {
                return wind;
            }

            public void setWind(String wind) {
                this.wind = wind;
            }

            public int getWindLevel() {
                return windLevel;
            }

            public void setWindLevel(int windLevel) {
                this.windLevel = windLevel;
            }

            public static class ExpBean {
                /**
                 * ct : {"expName":"穿衣指数","level":"较冷","prompt":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"}
                 */

                private CtBean ct;

                public CtBean getCt() {
                    return ct;
                }

                public void setCt(CtBean ct) {
                    this.ct = ct;
                }

                public static class CtBean {
                    /**
                     * expName : 穿衣指数
                     * level : 较冷
                     * prompt : 建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。
                     */

                    private String expName;
                    private String level;
                    private String prompt;

                    public String getExpName() {
                        return expName;
                    }

                    public void setExpName(String expName) {
                        this.expName = expName;
                    }

                    public String getLevel() {
                        return level;
                    }

                    public void setLevel(String level) {
                        this.level = level;
                    }

                    public String getPrompt() {
                        return prompt;
                    }

                    public void setPrompt(String prompt) {
                        this.prompt = prompt;
                    }
                }
            }
        }
    }
}
