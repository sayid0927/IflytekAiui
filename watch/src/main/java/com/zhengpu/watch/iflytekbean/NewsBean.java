package com.zhengpu.watch.iflytekbean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zhengpu.watch.base.AppContract;

import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2017/11/24.
 */

public class NewsBean  implements MultiItemEntity {


    /**
     * data : {"result":[{"category":"经济","content":"","description":"","id":"","imgUrl":"https://image.leting.io/26faf12b-59d9-4a80-bed0-240d2ee448dc.jpg","keyWords":"外贸","name":"","publishDateTime":"2017-12-11 19:27:24","source":"","time":"","title":"[央视]商务部: 我国外贸回稳向好势头进一步巩固","type":"1","url":"https://audio.leting.io/7ef60961-d75a-4f07-8d9e-2e92a4bd946e.mp3"},{"category":"科技","content":"","description":"","id":"","imgUrl":"https://image.leting.io/a93e8e69-d132-4cc5-bc65-76aab8303801.jpg","keyWords":"联通，阿里","name":"","publishDateTime":"2017-12-11 17:43:17","source":"","time":"","title":"[一财科技]联通也要玩新零售? 合作阿里用AR扫码逛营业厅","type":"1","url":"https://audio.leting.io/a9c797cd-e0aa-4f18-82ac-21cb1c8373e2.mp3"},{"category":"社会","content":"","description":"","id":"","imgUrl":"https://image.leting.io/479efae3-3a1b-446e-84c1-6ed87296f0ff.jpg","keyWords":"企业，招聘","name":"","publishDateTime":"2017-12-11 17:38:58","source":"","time":"","title":"[央视]养猪企业高薪\"抢人\" 名校毕业生月薪超2万？","type":"1","url":"https://audio.leting.io/c9c1cfbc-6b05-4550-834c-79b1e1e4e30b.mp3"},{"category":"社会","content":"","description":"","id":"","imgUrl":"https://image.leting.io/6e8d0565-5066-4333-b058-cec461959965.jpg","keyWords":"山西","name":"","publishDateTime":"2017-12-11 17:43:22","source":"","time":"","title":"[辽宁卫视]男子骑行两万六千公里 半夜遇熊豹经历47次生死","type":"1","url":"https://audio.leting.io/18d1d13f-8d6e-4b97-8d91-d3495d31bbf9.mp3"},{"category":"经济","content":" ","description":"","id":"","imgUrl":"https://image.leting.io/37922bb6-4845-4495-8988-c5049a2db93a.jpg","keyWords":"网综","name":"","publishDateTime":"2017-12-11 17:51:59","source":"","time":"","title":"[凤凰科技]《吐槽大会》第二季本周日回归 但网综的竞争越来越激烈了","type":"1","url":"https://audio.leting.io/e52111d5-7cfc-4688-8bc4-a2a3808c2ba2.mp3"},{"category":"社会","content":"  ","description":"","id":"","imgUrl":"https://image.leting.io/9e3f402a-bd0b-4330-8ad2-d7b938c87732.jpg","keyWords":"重庆","name":"","publishDateTime":"2017-12-11 17:52:04","source":"","time":"","title":"[新华网]重庆巫山\"强按贫困户头鞠躬道歉\"者系镇长 已被停职","type":"1","url":"https://audio.leting.io/59db2acc-50b1-4b51-8fba-d3d908f71ce3.mp3"},{"category":"娱乐","content":"","description":"","id":"","imgUrl":"https://image.leting.io/3ddaaebd-10b4-4441-9100-eea677ca6a72.jpg","keyWords":"小米","name":"","publishDateTime":"2017-12-11 17:38:52","source":"","time":"","title":"[雷科技]超划算! 小米双曲面旗舰大幅降价甩卖: 仅售2099元","type":"1","url":"https://audio.leting.io/d0549659-c566-4af0-863d-fe8eac602fa2.mp3"},{"category":"娱乐","content":" ","description":"","id":"","imgUrl":"https://image.leting.io/91ae5ac0-2ca7-4f80-8570-7997a57b9c9a.jpg","keyWords":"周杰伦","name":"","publishDateTime":"2017-12-11 17:51:23","source":"","time":"","title":"[新浪娱乐]周杰伦说老梗笑话展\"周式幽默\" 网友直呼冷爆了","type":"1","url":"https://audio.leting.io/537d9f93-0c65-4fbf-be13-102470d2c04b.mp3"},{"category":"国际","content":"","description":"","id":"","imgUrl":"https://image.leting.io/f15c3e33-b1fe-4368-8b8f-2915fc4a17c0.jpg","keyWords":"印度","name":"","publishDateTime":"2017-12-11 17:39:00","source":"","time":"","title":"[广东卫视]印度泰姬陵明年开始\"洗头\"","type":"1","url":"https://audio.leting.io/aa599d3c-3223-4268-acae-b13245fb9cb7.mp3"},{"category":"娱乐","content":" ","description":"","id":"","imgUrl":"https://image.leting.io/2af1c502-5532-468f-afa0-f0d2d7d83225.jpg","keyWords":"张庭","name":"","publishDateTime":"2017-12-11 17:51:20","source":"","time":"","title":"[腾讯娱乐]百亿阔太张庭晒照为女儿庆生 一家四口幸福惹人羡","type":"1","url":"https://audio.leting.io/320a2e80-2278-41a5-9b5e-4e24932c1292.mp3"}]}
     * rc : 0
     * semantic : [{"intent":"PLAY","slots":[{"name":"datetime","value":"今天","normValue":"{\"datetime\":\"2017-12-11\",\"suggestDatetime\":\"2017-12-11\"}"}]}]
     * service : news
     * state : {"fg::news::default::default":{"state":"default"}}
     * text : 今天新闻
     * uuid : atn0583dc04@ch2eca0d89e4766f2601
     * used_state : {"state_key":"fg::news::default::default","state":"default"}
     * answer : {"text":"开始为您播放"}
     * dialog_stat : dataInvalid
     * save_history : true
     * sid : atn0583dc04@ch2eca0d89e4766f2601
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

    @Override
    public int getItemType() {
        return AppContract.NewsBean;
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
             * category : 经济
             * content :
             * description :
             * id :
             * imgUrl : https://image.leting.io/26faf12b-59d9-4a80-bed0-240d2ee448dc.jpg
             * keyWords : 外贸
             * name :
             * publishDateTime : 2017-12-11 19:27:24
             * source :
             * time :
             * title : [央视]商务部: 我国外贸回稳向好势头进一步巩固
             * type : 1
             * url : https://audio.leting.io/7ef60961-d75a-4f07-8d9e-2e92a4bd946e.mp3
             */

            private String category;
            private String content;
            private String description;
            private String id;
            private String imgUrl;
            private String keyWords;
            private String name;
            private String publishDateTime;
            private String source;
            private String time;
            private String title;
            private String type;
            private String url;

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getKeyWords() {
                return keyWords;
            }

            public void setKeyWords(String keyWords) {
                this.keyWords = keyWords;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPublishDateTime() {
                return publishDateTime;
            }

            public void setPublishDateTime(String publishDateTime) {
                this.publishDateTime = publishDateTime;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
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
