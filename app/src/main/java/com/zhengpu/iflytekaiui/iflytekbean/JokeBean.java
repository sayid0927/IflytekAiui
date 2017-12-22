package com.zhengpu.iflytekaiui.iflytekbean;

import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2017/11/29.
 */

public class JokeBean extends BaseBean {


    /**
     * data : {"result":[{"album":"微博冷笑话","albumUrl":"http://www.ximalaya.com/1000558/album/2677704","author":"笑话大王","category":"娱乐","content":"","id":77,"mp3Url":"http://audio.xmcdn.com/group3/M06/D3/90/wKgDsVLf3M_jWEolAAXfITEFyUk555.mp3","mp4Url":"http://audio.xmcdn.com/group8/M03/2D/7B/wKgDYFWKmxnQz2Z0AAJDYzaKDcI619.m4a","status":"","subCategory":"内涵段子","tag":"冷笑话;微博;笑话大王","title":"黄金搭档"},{"album":"微博冷笑话","albumUrl":"http://www.ximalaya.com/1000558/album/2677704","author":"笑话大王","category":"娱乐","content":"","id":97,"mp3Url":"http://audio.xmcdn.com/group3/M06/D2/99/wKgDslLfsp3Cm5k5AAOZ59SqIlY934.mp3","mp4Url":"http://audio.xmcdn.com/group16/M00/43/86/wKgDalWlBJSxj41UAAFmk_pJbnY189.m4a","status":"","subCategory":"内涵段子","tag":"冷笑话;微博;笑话大王","title":"老外买鸡"},{"album":"微博冷笑话","albumUrl":"http://www.ximalaya.com/1000558/album/2677704","author":"笑话大王","category":"娱乐","content":"","id":96,"mp3Url":"http://audio.xmcdn.com/group3/M06/D2/9A/wKgDslLfsp-RosyEAAX_yFPOl0s729.mp3","mp4Url":"http://audio.xmcdn.com/group15/M02/40/FD/wKgDaFWhT7bBepZcAAJPiDP-r6U709.m4a","status":"","subCategory":"内涵段子","tag":"冷笑话;微博;笑话大王","title":"老张的推荐信"},{"album":"嘻哈宝典","albumUrl":"http://www.ximalaya.com/7132999/album/240721","author":"DJ阿木_lO","category":"娱乐","content":"","id":2,"mp3Url":"http://audio.xmcdn.com/group3/M07/20/7A/wKgDslMKrU-yACc4AAQV9EKuP2Q548.mp3","mp4Url":"http://audio.xmcdn.com/group10/M02/09/F2/wKgDZ1VfdHaz6HpRAAGY0rVPfm4859.m4a","status":"","subCategory":"","tag":"娱乐;搞笑;笑话;综艺节目;脱口秀","title":"魔法公主"},{"album":"微博冷笑话","albumUrl":"http://www.ximalaya.com/1000558/album/2677704","author":"笑话大王","category":"娱乐","content":"","id":81,"mp3Url":"http://audio.xmcdn.com/group3/M06/D3/3F/wKgDslLf3M2Rj_tyAAXS4tKqpxk445.mp3","mp4Url":"http://audio.xmcdn.com/group14/M07/36/62/wKgDY1WVm0PykzBJAAI--V6Tltc559.m4a","status":"","subCategory":"内涵段子","tag":"冷笑话;微博;笑话大王","title":"青花大海碗"},{"album":"微博冷笑话","albumUrl":"http://www.ximalaya.com/1000558/album/2677704","author":"笑话大王","category":"娱乐","content":"","id":88,"mp3Url":"http://audio.xmcdn.com/group3/M06/D3/5A/wKgDsVLfzsKgTzFRAAW7NpVPX4s642.mp3","mp4Url":"http://audio.xmcdn.com/group12/M0B/26/22/wKgDXFWCTaiCwEhTAAI1CbDBmlI758.m4a","status":"","subCategory":"内涵段子","tag":"冷笑话;微博;笑话大王","title":"谁坑谁"},{"album":"微博冷笑话","albumUrl":"http://www.ximalaya.com/1000558/album/2677704","author":"笑话大王","category":"娱乐","content":"","id":98,"mp3Url":"http://audio.xmcdn.com/group3/M06/D2/AB/wKgDsVLfppmDPQvgAAUIbwuElbI827.mp3","mp4Url":"http://audio.xmcdn.com/group10/M0B/4B/51/wKgDZ1WvQE2S2scwAAHxsHW-9fo426.m4a","status":"","subCategory":"内涵段子","tag":"冷笑话;微博;笑话大王","title":"汤没法喝了"},{"album":"微博冷笑话","albumUrl":"http://www.ximalaya.com/1000558/album/2677704","author":"笑话大王","category":"娱乐","content":"","id":86,"mp3Url":"http://audio.xmcdn.com/group3/M06/D3/5A/wKgDsVLfzsCjUtmzAAedqeq2Zh4981.mp3","mp4Url":"http://audio.xmcdn.com/group15/M02/40/FB/wKgDZVWhT9vyvzJlAALtaviiVY8264.m4a","status":"","subCategory":"内涵段子","tag":"冷笑话;微博;笑话大王","title":"都是艺术家"},{"album":"嘻哈宝典","albumUrl":"http://www.ximalaya.com/7132999/album/240721","author":"DJ阿木_lO","category":"娱乐","content":"","id":10,"mp3Url":"http://audio.xmcdn.com/group3/M07/20/7B/wKgDslMKrXXANySXAALGcpgFd4E959.mp3","mp4Url":"http://audio.xmcdn.com/group9/M08/4D/A2/wKgDYlWx9F6ii0OrAAEXfd8Ixrs384.m4a","status":"","subCategory":"","tag":"娱乐;搞笑;笑话;综艺节目;脱口秀","title":"分手"},{"album":"嘻哈宝典","albumUrl":"http://www.ximalaya.com/7132999/album/240721","author":"DJ阿木_lO","category":"娱乐","content":"","id":12,"mp3Url":"http://audio.xmcdn.com/group3/M07/20/CD/wKgDsVMKrXyz38faAAODBBkWWnc365.mp3","mp4Url":"http://audio.xmcdn.com/group14/M03/36/CF/wKgDZFWWHOWjCClTAAFf3UaRoKI804.m4a","status":"","subCategory":"","tag":"娱乐;搞笑;笑话;综艺节目;脱口秀","title":"西红柿炒鸡蛋"}]}
     * rc : 0
     * semantic : [{"intent":"QUERY","slots":[]}]
     * service : joke
     * state : {"fg::joke::default::default":{"state":"default"}}
     * text : 我想听笑话
     * uuid : atn0047b490@ch1fca0d7969446f2a01
     * answer : {"text":"请听笑话,黄金搭档."}
     * dialog_stat : dataInvalid
     * save_history : true
     * sid : atn0047b490@ch1fca0d7969446f2a01
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

    public static class AnswerBean {

        private String text;


        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

    }



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
             * album : 微博冷笑话
             * albumUrl : http://www.ximalaya.com/1000558/album/2677704
             * author : 笑话大王
             * category : 娱乐
             * content :
             * id : 77
             * mp3Url : http://audio.xmcdn.com/group3/M06/D3/90/wKgDsVLf3M_jWEolAAXfITEFyUk555.mp3
             * mp4Url : http://audio.xmcdn.com/group8/M03/2D/7B/wKgDYFWKmxnQz2Z0AAJDYzaKDcI619.m4a
             * status :
             * subCategory : 内涵段子
             * tag : 冷笑话;微博;笑话大王
             * title : 黄金搭档
             */

            private String album;
            private String albumUrl;
            private String author;
            private String category;
            private String content;
            private int id;
            private String mp3Url;
            private String mp4Url;
            private String status;
            private String subCategory;
            private String tag;
            private String title;

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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
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
        }
    }
}
