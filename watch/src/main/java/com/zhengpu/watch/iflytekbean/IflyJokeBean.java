package com.zhengpu.watch.iflytekbean;

import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2018/3/29.
 */

public class IflyJokeBean {


    /**
     * data : {"result":[{"album":"","albumUrl":"","author":"","category":"校园笑话","content":"为了杜绝学生翻墙，某校园的铁栅栏都是带尖头的。上面的警示标语如下：严禁翻墙，男生不小心就会翻成女生，女生不小心就会翻成女人。","id":31225,"mp3Url":"","mp4Url":"","status":1,"subCategory":"","tag":"","title":"杜绝学生翻墙","type":"0"},{"album":"","albumUrl":"","author":"","category":"校园笑话","content":"同桌感冒流鼻涕，但他忘记带手帕了，就不断把鼻涕用力吸入鼻子里。在黑板上写字的语文老师突然转过身来大嚷：够了！给我停止！吵死了！全班一片安静。老师又说：到底是谁上课时偷吃面条还这么大声？","id":31223,"mp3Url":"","mp4Url":"","status":1,"subCategory":"","tag":"","title":"上课偷吃面条","type":"0"},{"album":"","albumUrl":"","author":"","category":"校园笑话","content":"美术老师问学生：你为什么交白卷？学生说：我画了啊！我画的是牛吃草。老师问：那草呢？他说：被牛吃掉了。老师问：那牛呢？他说：吃完草走了。","id":31262,"mp3Url":"","mp4Url":"","status":1,"subCategory":"","tag":"","title":"画","type":"0"},{"album":"","albumUrl":"","author":"","category":"校园笑话","content":"一位男教师对两个吵闹不休的女学生说：两个女人的声音，犹如一千只鸭子的叫声。一会儿，教师的妻子来看望他。其中一个女学生赶来报告。老师，门外有五百只鸭子来看您。","id":31182,"mp3Url":"","mp4Url":"","status":1,"subCategory":"","tag":"","title":"鸭子来看望您","type":"0"},{"album":"","albumUrl":"","author":"","category":"校园笑话","content":"上了大学以后，大家7个人住在一个宿舍里。一天，一个人在那里庆幸说：咱们宿舍没有睡觉打呼噜的。另一个说：你怎么知道的？那人说：这些日子我晚上起来，没有听到打呼噜的声音。另一个人紧接着说：你醒了当然听不到了。大家都捧腹大笑。","id":31246,"mp3Url":"","mp4Url":"","status":1,"subCategory":"","tag":"","title":"关于睡觉打呼噜","type":"0"},{"album":"","albumUrl":"","author":"","category":"校园笑话","content":"初中时，一个同学叫李猜，一次上英语课英语老师问他叫什么名字，他说李猜，老师又问了一遍，他如是回答，英语老师大怒，狂吼一声：我不猜。","id":31187,"mp3Url":"","mp4Url":"","status":1,"subCategory":"","tag":"","title":"我不猜","type":"0"},{"album":"","albumUrl":"","author":"","category":"校园笑话","content":"某中学校长在一次大会上面对全体同学说：同学们，本校为应社会之发展，经济之蓬勃，特做了大小规模的变动，三年一小变，五年一大变。一同学忍不住说：这也太能憋了吧。","id":31231,"mp3Url":"","mp4Url":"","status":1,"subCategory":"","tag":"","title":"太能憋了","type":"0"},{"album":"","albumUrl":"","author":"","category":"校园笑话","content":"有个大学生，马上大四毕业了，依然没有工作，没有女友。于是，他去算命。你啊，将一直穷困潦倒，直到四十岁学生听了眼睛一亮，心想有转机，于是问：然后呢？然后你就习惯这样的生活了","id":31213,"mp3Url":"","mp4Url":"","status":1,"subCategory":"","tag":"","title":"然后呢？","type":"0"},{"album":"","albumUrl":"","author":"","category":"校园笑话","content":"一日，班长通知星期六要举行开学典礼．完了我同桌猛摇我手臂说：快，告诉我，星期六是礼拜几","id":31173,"mp3Url":"","mp4Url":"","status":1,"subCategory":"","tag":"","title":"迷迷糊糊","type":"0"},{"album":"","albumUrl":"","author":"","category":"校园笑话","content":"同桌有点呆呆的，英语刚把数字1到10教完之后，那天突然小心翼翼的问了我个问题：英语1念one，那11是不是念oneone啊？111是不是念oneoneone呢？英语老师幽幽的来了句：你也可以这样念，你属狗的吧。","id":31248,"mp3Url":"","mp4Url":"","status":1,"subCategory":"","tag":"","title":"你属狗的吧","type":"0"}]}
     * rc : 0
     * semantic : [{"intent":"QUERY","slots":[]}]
     * service : joke
     * state : {"fg::joke::default::default":{"state":"default"}}
     * text : 笑话
     * uuid : atn008c1d6a@ch50e60e17a2356f2001
     * used_state : {"state_key":"fg::joke::default::default","state":"default"}
     * answer : {"text":"请听笑话杜绝学生翻墙"}
     * dialog_stat : dataInvalid
     * save_history : true
     * sid : atn008c1d6a@ch50e60e17a2356f2001
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
             * album :
             * albumUrl :
             * author :
             * category : 校园笑话
             * content : 为了杜绝学生翻墙，某校园的铁栅栏都是带尖头的。上面的警示标语如下：严禁翻墙，男生不小心就会翻成女生，女生不小心就会翻成女人。
             * id : 31225
             * mp3Url :
             * mp4Url :
             * status : 1
             * subCategory :
             * tag :
             * title : 杜绝学生翻墙
             * type : 0
             */

            private String album;
            private String albumUrl;
            private String author;
            private String category;
            private String content;
            private int id;
            private String mp3Url;
            private String mp4Url;
            private int status;
            private String subCategory;
            private String tag;
            private String title;
            private String type;

            public String getAlbum() {
                return album;
            }

            public void setAlbum(String album) {
                this.album = album;
            }

            public String getAlbumUrl() {
                return albumUrl;
            }

            public void setAlbumUrl(String albumUrl) {
                this.albumUrl = albumUrl;
            }

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

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getMp3Url() {
                return mp3Url;
            }

            public void setMp3Url(String mp3Url) {
                this.mp3Url = mp3Url;
            }

            public String getMp4Url() {
                return mp4Url;
            }

            public void setMp4Url(String mp4Url) {
                this.mp4Url = mp4Url;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getSubCategory() {
                return subCategory;
            }

            public void setSubCategory(String subCategory) {
                this.subCategory = subCategory;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
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
        }
    }
}
