package com.zhengpu.iflytekaiui.iflytekbean;

import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2018/5/16.
 */

public class HotelSearchBean {


    /**
     * data : {"result":[{"address":"魏公村南区18号楼一单元201","area":"海淀区","averagePrice":"","center":"","city":"北京市","distance":"","facility":"","gps":"","img":"","location":"116.317029,39.953412","lowestPrice":"","name":"北京封阿姨家庭旅馆","phone":"13681265163","province":"北京市","radius":"3000","recommend":"0","reservation":"","score":"","source":"amap","star":"","tags":"","url":""},{"address":"北京北太平庄北医三院","area":"海淀区","averagePrice":"","center":"","city":"北京市","distance":"","facility":"","gps":"","img":"","location":"116.359204,39.981743","lowestPrice":"","name":"北京邮科社区赵家旅馆","phone":"","province":"北京市","radius":"3000","recommend":"0","reservation":"","score":"","source":"amap","star":"","tags":"","url":""},{"address":"红居街21号附近","area":"西城区","averagePrice":"","center":"","city":"北京市","distance":"","facility":"","gps":"","img":"","location":"116.336725,39.887858","lowestPrice":"","name":"盛源饭店客房","phone":"","province":"北京市","radius":"3000","recommend":"0","reservation":"","score":"","source":"amap","star":"","tags":"","url":""},{"address":"文慧园路4-401号附近","area":"海淀区","averagePrice":"","center":"","city":"北京市","distance":"","facility":"","gps":"","img":"","location":"116.365436,39.952384","lowestPrice":"","name":"禾名旅舍","phone":"","province":"北京市","radius":"3000","recommend":"0","reservation":"","score":"","source":"amap","star":"","tags":"","url":""},{"address":"五爱屯东街","area":"丰台区","averagePrice":"","center":"","city":"北京市","distance":"","facility":"","gps":"","img":"","location":"116.381570,39.800352","lowestPrice":"","name":"北京万和顺旅馆","phone":"010-67985308","province":"北京市","radius":"3000","recommend":"0","reservation":"","score":"","source":"amap","star":"","tags":"","url":""},{"address":"樊羊路67号附近","area":"丰台区","averagePrice":"","center":"","city":"北京市","distance":"","facility":"","gps":"","img":"","location":"116.312370,39.821933","lowestPrice":"","name":"亿园招待所","phone":"","province":"北京市","radius":"3000","recommend":"0","reservation":"","score":"","source":"amap","star":"","tags":"","url":""},{"address":"建国门内大街丙6号楼","area":"东城区","averagePrice":"","center":"","city":"北京市","distance":"","facility":"","gps":"","img":"","location":"116.430857,39.906402","lowestPrice":"","name":"北京海关招待所","phone":"010-65194433","province":"北京市","radius":"3000","recommend":"0","reservation":"","score":"","source":"amap","star":"","tags":"","url":""},{"address":"爱民四巷与西什库大街交叉口东50米","area":"西城区","averagePrice":"","center":"","city":"北京市","distance":"","facility":"","gps":"","img":"","location":"116.380064,39.930385","lowestPrice":"","name":"旅馆","phone":"","province":"北京市","radius":"3000","recommend":"0","reservation":"","score":"","source":"amap","star":"","tags":"","url":""},{"address":"华清嘉园东区1号楼","area":"海淀区","averagePrice":"","center":"","city":"北京市","distance":"","facility":"","gps":"","img":"http://store.is.autonavi.com/showpic/84e8bc9d10638bce96cac0c02a32428f","location":"116.336609,39.992228","lowestPrice":"","name":"北京清华缘青年旅舍(五道口地铁站店)","phone":"18801483577","province":"北京市","radius":"3000","recommend":"0","reservation":"","score":"","source":"amap","star":"","tags":"","url":""},{"address":"太平路48号院嘉溪江宾馆北门","area":"海淀区","averagePrice":"","center":"","city":"北京市","distance":"","facility":"","gps":"","img":"","location":"116.257824,39.899209","lowestPrice":"","name":"48号院招待所(太平路48号院东)","phone":"","province":"北京市","radius":"3000","recommend":"0","reservation":"","score":"","source":"amap","star":"","tags":"","url":""}]}
     * rc : 0
     * semantic : [{"intent":"QUERY","slots":[{"name":"location.city","value":"CURRENT_CITY","normValue":"CURRENT_CITY"},{"name":"location.poi","value":"CURRENT_POI","normValue":"CURRENT_POI"},{"name":"location.type","value":"LOC_POI","normValue":"LOC_POI"}]}]
     * service : hotelSearch
     * state : {"fg::hotelSearch::default::default":{"state":"default"}}
     * text : 附近的酒店
     * uuid : atn002a54ce@dx00070e572942a10e01
     * answer : {"text":"没有检测到gps信息哦，为您查到北京的酒店有北京封阿姨家庭旅馆、北京邮科社区赵家旅馆等"}
     * dialog_stat : dataInvalid
     * save_history : true
     * sid : atn002a54ce@dx00070e572942a10e01
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

    public  static  class AnswerBean {
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
             * address : 魏公村南区18号楼一单元201
             * area : 海淀区
             * averagePrice :
             * center :
             * city : 北京市
             * distance :
             * facility :
             * gps :
             * img :
             * location : 116.317029,39.953412
             * lowestPrice :
             * name : 北京封阿姨家庭旅馆
             * phone : 13681265163
             * province : 北京市
             * radius : 3000
             * recommend : 0
             * reservation :
             * score :
             * source : amap
             * star :
             * tags :
             * url :
             */

            private String address;
            private String area;
            private String averagePrice;
            private String center;
            private String city;
            private String distance;
            private String facility;
            private String gps;
            private String img;
            private String location;
            private String lowestPrice;
            private String name;
            private String phone;
            private String province;
            private String radius;
            private String recommend;
            private String reservation;
            private String score;
            private String source;
            private String star;
            private String tags;
            private String url;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getAveragePrice() {
                return averagePrice;
            }

            public void setAveragePrice(String averagePrice) {
                this.averagePrice = averagePrice;
            }

            public String getCenter() {
                return center;
            }

            public void setCenter(String center) {
                this.center = center;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public String getFacility() {
                return facility;
            }

            public void setFacility(String facility) {
                this.facility = facility;
            }

            public String getGps() {
                return gps;
            }

            public void setGps(String gps) {
                this.gps = gps;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getLowestPrice() {
                return lowestPrice;
            }

            public void setLowestPrice(String lowestPrice) {
                this.lowestPrice = lowestPrice;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getRadius() {
                return radius;
            }

            public void setRadius(String radius) {
                this.radius = radius;
            }

            public String getRecommend() {
                return recommend;
            }

            public void setRecommend(String recommend) {
                this.recommend = recommend;
            }

            public String getReservation() {
                return reservation;
            }

            public void setReservation(String reservation) {
                this.reservation = reservation;
            }

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getStar() {
                return star;
            }

            public void setStar(String star) {
                this.star = star;
            }

            public String getTags() {
                return tags;
            }

            public void setTags(String tags) {
                this.tags = tags;
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
