package com.zhengpu.iflytekaiui.iflytekbean.otherbean;

import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2017/12/14.
 */

public class TianJokeBean {


    /**
     * code : 200
     * msg : success
     * newslist : [{"id":30657,"title":"上海大学","content":"上海大学是一所有几所大学合并组建的新校,几年前在全国的知名度不是很高。<br/>一位外地同学恰巧考入上海大学就读，一天他的一个朋友打电话来问他：<br/>\u201c兄弟，你考近了啥大学？\u201d<br/>\u201c上海大学。\u201d同学答。<br/>\u201c咳，废话少说；俺早就知道你考进的是上海的大学，俺只是想问你到底进了上海哪所大学？\u201d<br/>\u201c。。。。。？！\u201d"}]
     */

    private int code;
    private String msg;
    private List<NewslistBean> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    public static class NewslistBean {
        /**
         * id : 30657
         * title : 上海大学
         * content : 上海大学是一所有几所大学合并组建的新校,几年前在全国的知名度不是很高。<br/>一位外地同学恰巧考入上海大学就读，一天他的一个朋友打电话来问他：<br/>“兄弟，你考近了啥大学？”<br/>“上海大学。”同学答。<br/>“咳，废话少说；俺早就知道你考进的是上海的大学，俺只是想问你到底进了上海哪所大学？”<br/>“。。。。。？！”
         */

        private int id;
        private String title;
        private String content;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
