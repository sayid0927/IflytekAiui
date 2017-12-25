package com.zhengpu.iflytekaiui.iflytekbean;

import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2017/11/29.
 */

public class StoryBean extends BaseBean {


    /**
     * data : {"result":[{"author":"","category":"女孩故事,经典童话","id":"","name":"冰雪女王（安徒生版）","playUrl":"http://cdn.open.idaddy.cn/apsmp3/f409/iflytek000000001/201712250000/0/ADQGNlA0DTw=-YS85L3diMzVuZ28xLmF1ZGlv.mp3","series":"冰雪女王（安徒生版）","status":"1"},{"author":"","category":"睡前故事,女孩故事,经典童话","id":"","name":"【领声·好声音】丑小鸭","playUrl":"http://cdn.open.idaddy.cn/apsmp3/f2c6/iflytek000000001/201712250000/0/ADAGNlAyDT0=-YTY0LzgvNGJlNTNmZS5hdWRpbw==.mp3","series":"【领声·好声音】丑小鸭","status":"1"},{"author":"","category":"睡前故事,女孩故事,经典童话","id":"","name":"海的女儿（上）","playUrl":"http://cdn.open.idaddy.cn/apsmp3/48c6/iflytek000000001/201712250000/0/ADIGNFAwDTw=-YTY0LzQvYzd4aHdlcmUuYXVkaW8=.mp3","series":"海的女儿（上）","status":"1"},{"author":"","category":"女孩故事,经典童话,生活成长","id":"","name":"拇指姑娘（卉卉阿姨）","playUrl":"http://cdn.open.idaddy.cn/apsmp3/5d51/iflytek000000001/201712250000/0_adidaddy/ADIGMVA2DTI=-ADcGPlAyDT1Rbg==-YTY0LzAvM29iNGg0ZWQuYXVkaW8=.mp3","series":"拇指姑娘（卉卉阿姨）","status":"1"},{"author":"","category":"睡前故事,女孩故事,经典童话","id":"","name":"海的女儿（下）","playUrl":"http://cdn.open.idaddy.cn/apsmp3/6630/iflytek000000001/201712250000/0/ADIGNFAzDTU=-YS82L3lydzM0emI4LmF1ZGlv.mp3","series":"海的女儿（下）","status":"1"}]}
     * rc : 0
     * semantic : [{"intent":"QUERY","slots":[{"name":"author","value":"安徒生"}]}]
     * service : story
     * state : {"fg::story::default::default":{"state":"default"}}
     * text : 我想听安徒生写的故事
     * uuid : atn003318cc@ch3ed30d9bb2cc6f2001
     * dialog_stat : dataInvalid
     * save_history : true
     * sid : atn003318cc@ch3ed30d9bb2cc6f2001
     */

    private DataBean data;
    private int rc;
    private String service;
    private String text;
    private String uuid;
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
        private java.util.List<ResultBean> result;

        public List<ResultBean> getResult() {
            return result;
        }

        public void setResult(List<ResultBean> result) {
            this.result = result;
        }

        public static class ResultBean {
            /**
             * author :
             * category : 女孩故事,经典童话
             * id :
             * name : 冰雪女王（安徒生版）
             * playUrl : http://cdn.open.idaddy.cn/apsmp3/f409/iflytek000000001/201712250000/0/ADQGNlA0DTw=-YS85L3diMzVuZ28xLmF1ZGlv.mp3
             * series : 冰雪女王（安徒生版）
             * status : 1
             */

            private String author;
            private String category;
            private String id;
            private String name;
            private String playUrl;
            private String series;
            private String status;

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPlayUrl() {
                return playUrl;
            }

            public void setPlayUrl(String playUrl) {
                this.playUrl = playUrl;
            }

            public String getSeries() {
                return series;
            }

            public void setSeries(String series) {
                this.series = series;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }
}
