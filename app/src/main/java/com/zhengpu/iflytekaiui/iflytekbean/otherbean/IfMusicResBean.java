package com.zhengpu.iflytekaiui.iflytekbean.otherbean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/24 0024.
 */

public class IfMusicResBean {


    /**
     * data : {"inherit":0,"isCached":0,"priority":0,"result":[{"albumname":"刘德华 Unforgettable Concert 2010","itemid":"45571977","movienames":"","neatsongname":["掌声响起"],"pictures":[{"level":2,"path":"http://vbox.hfdn.openstorage.cn/ctipicture/2/20160303/28606fc27d27aeb92245b10445da05d9/9b63ba4b27901d779cc08c91dbffaff1_z.jpg","size":"389x389"},{"level":1,"path":"http://vbox.hfdn.openstorage.cn/ctipicture/2/20160303/28606fc27d27aeb92245b10445da05d9/9b63ba4b27901d779cc08c91dbffaff1_small.jpg","size":"300x300"}],"programname":"","publishtime":1303228800,"singeraliasnames":[""],"singerids":["25000"],"singernames":["刘德华"],"songname":"掌声响起","source":"iflytek","tagnames":["国语","流行","伤感","80后","经典","怀旧",""]},{"albumname":"Vision Tour 2004 演唱会","itemid":"45106598","movienames":"","neatsongname":["暗里着迷"],"pictures":[{"level":2,"path":"http://vbox.hfdn.openstorage.cn/ctipicture/1/20160304/286874d6e6037084987d410b7764809e/765cf368f30074da3934b3282f733e59_z.jpg","size":"500x500"},{"level":1,"path":"http://vbox.hfdn.openstorage.cn/ctipicture/1/20160304/286874d6e6037084987d410b7764809e/765cf368f30074da3934b3282f733e59_small.jpg","size":"300x300"}],"programname":"","publishtime":1101830400,"singeraliasnames":[""],"singerids":["25000"],"singernames":["刘德华"],"songname":"暗里着迷","source":"iflytek","tagnames":["粤语","流行","怀旧","80后","经典","民谣","感动","摇滚","旅行","性感",""]},{"albumname":"刘德华中国巡回演唱会·上海","itemid":"46178815","movienames":"","neatsongname":["心肝宝贝"],"pictures":[{"level":2,"path":"http://vbox.hfdn.openstorage.cn/ctipicture/1/20160305/8d248cd62fca72ab45471fe95a16cc71/3c969d6dbe5133722430880d8dd47a67_z.jpg","size":"500x500"},{"level":1,"path":"http://vbox.hfdn.openstorage.cn/ctipicture/1/20160305/8d248cd62fca72ab45471fe95a16cc71/3c969d6dbe5133722430880d8dd47a67_small.jpg","size":"300x300"}],"programname":"","publishtime":1222272000,"singeraliasnames":[""],"singerids":["25000"],"singernames":["刘德华"],"songname":"心肝宝贝","source":"iflytek","tagnames":["思念","孤独","流行",""]},{"albumname":"刘德华中国巡回演唱会·上海","itemid":"45953598","movienames":"","neatsongname":["来生缘"],"pictures":[{"level":2,"path":"http://vbox.hfdn.openstorage.cn/ctipicture/1/20160305/8d248cd62fca72ab45471fe95a16cc71/3c969d6dbe5133722430880d8dd47a67_z.jpg","size":"500x500"},{"level":1,"path":"http://vbox.hfdn.openstorage.cn/ctipicture/1/20160305/8d248cd62fca72ab45471fe95a16cc71/3c969d6dbe5133722430880d8dd47a67_small.jpg","size":"300x300"}],"programname":"","publishtime":1222272000,"singeraliasnames":["华仔","华哥"],"singerids":["25000"],"singernames":["刘德华"],"songname":"来生缘","source":"iflytek","tagnames":["华语","流行",""]},{"albumname":"爱如此神奇","itemid":"44929367","movienames":"","neatsongname":["中国人"],"pictures":[{"level":2,"path":"http://vbox.hfdn.openstorage.cn/ctipicture/1/20160306/573990fbddcb312b81f2a11a01f575c5/b331e8e4ab24e84e5e635c74b47b00a0_z.jpg","size":"500x500"},{"level":1,"path":"http://vbox.hfdn.openstorage.cn/ctipicture/1/20160306/573990fbddcb312b81f2a11a01f575c5/b331e8e4ab24e84e5e635c74b47b00a0_small.jpg","size":"300x300"}],"programname":"","publishtime":820425600,"singeraliasnames":[""],"singerids":["25000"],"singernames":["刘德华"],"songname":"中国人","source":"iflytek","tagnames":["国语","流行","80后","经典","夜晚","放松","综艺热歌",""]}],"sem_score":{"artist":{"lcs":1,"pos":"ps","txt":"刘德华"},"top":0}}
     * rc : 0
     * semantic : [{"intent":"PLAY","slots":[{"name":"artist","value":"刘德华"}]}]
     * service : musicX
     * uuid : atn00019d34@ch17500da03dc96f2001
     * text : 播放刘德华的歌
     * state : {"fg::musicX::default::playing":{"artist":"1","state":"playing"}}
     * used_state : {"artist":"1","state":"playing","state_key":"fg::musicX::default::playing"}
     * answer : {"text":"请欣赏刘德华的歌曲掌声响起"}
     * dialog_stat : DataValid
     * save_history : true
     * sid : atn00019d34@ch17500da03dc96f2001
     */

    private DataBean data;
    private int rc;
    private String service;
    private String uuid;
    private String text;
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
        /**
         * inherit : 0
         * isCached : 0
         * priority : 0
         * result : [{"albumname":"刘德华 Unforgettable Concert 2010","itemid":"45571977","movienames":"","neatsongname":["掌声响起"],"pictures":[{"level":2,"path":"http://vbox.hfdn.openstorage.cn/ctipicture/2/20160303/28606fc27d27aeb92245b10445da05d9/9b63ba4b27901d779cc08c91dbffaff1_z.jpg","size":"389x389"},{"level":1,"path":"http://vbox.hfdn.openstorage.cn/ctipicture/2/20160303/28606fc27d27aeb92245b10445da05d9/9b63ba4b27901d779cc08c91dbffaff1_small.jpg","size":"300x300"}],"programname":"","publishtime":1303228800,"singeraliasnames":[""],"singerids":["25000"],"singernames":["刘德华"],"songname":"掌声响起","source":"iflytek","tagnames":["国语","流行","伤感","80后","经典","怀旧",""]},{"albumname":"Vision Tour 2004 演唱会","itemid":"45106598","movienames":"","neatsongname":["暗里着迷"],"pictures":[{"level":2,"path":"http://vbox.hfdn.openstorage.cn/ctipicture/1/20160304/286874d6e6037084987d410b7764809e/765cf368f30074da3934b3282f733e59_z.jpg","size":"500x500"},{"level":1,"path":"http://vbox.hfdn.openstorage.cn/ctipicture/1/20160304/286874d6e6037084987d410b7764809e/765cf368f30074da3934b3282f733e59_small.jpg","size":"300x300"}],"programname":"","publishtime":1101830400,"singeraliasnames":[""],"singerids":["25000"],"singernames":["刘德华"],"songname":"暗里着迷","source":"iflytek","tagnames":["粤语","流行","怀旧","80后","经典","民谣","感动","摇滚","旅行","性感",""]},{"albumname":"刘德华中国巡回演唱会·上海","itemid":"46178815","movienames":"","neatsongname":["心肝宝贝"],"pictures":[{"level":2,"path":"http://vbox.hfdn.openstorage.cn/ctipicture/1/20160305/8d248cd62fca72ab45471fe95a16cc71/3c969d6dbe5133722430880d8dd47a67_z.jpg","size":"500x500"},{"level":1,"path":"http://vbox.hfdn.openstorage.cn/ctipicture/1/20160305/8d248cd62fca72ab45471fe95a16cc71/3c969d6dbe5133722430880d8dd47a67_small.jpg","size":"300x300"}],"programname":"","publishtime":1222272000,"singeraliasnames":[""],"singerids":["25000"],"singernames":["刘德华"],"songname":"心肝宝贝","source":"iflytek","tagnames":["思念","孤独","流行",""]},{"albumname":"刘德华中国巡回演唱会·上海","itemid":"45953598","movienames":"","neatsongname":["来生缘"],"pictures":[{"level":2,"path":"http://vbox.hfdn.openstorage.cn/ctipicture/1/20160305/8d248cd62fca72ab45471fe95a16cc71/3c969d6dbe5133722430880d8dd47a67_z.jpg","size":"500x500"},{"level":1,"path":"http://vbox.hfdn.openstorage.cn/ctipicture/1/20160305/8d248cd62fca72ab45471fe95a16cc71/3c969d6dbe5133722430880d8dd47a67_small.jpg","size":"300x300"}],"programname":"","publishtime":1222272000,"singeraliasnames":["华仔","华哥"],"singerids":["25000"],"singernames":["刘德华"],"songname":"来生缘","source":"iflytek","tagnames":["华语","流行",""]},{"albumname":"爱如此神奇","itemid":"44929367","movienames":"","neatsongname":["中国人"],"pictures":[{"level":2,"path":"http://vbox.hfdn.openstorage.cn/ctipicture/1/20160306/573990fbddcb312b81f2a11a01f575c5/b331e8e4ab24e84e5e635c74b47b00a0_z.jpg","size":"500x500"},{"level":1,"path":"http://vbox.hfdn.openstorage.cn/ctipicture/1/20160306/573990fbddcb312b81f2a11a01f575c5/b331e8e4ab24e84e5e635c74b47b00a0_small.jpg","size":"300x300"}],"programname":"","publishtime":820425600,"singeraliasnames":[""],"singerids":["25000"],"singernames":["刘德华"],"songname":"中国人","source":"iflytek","tagnames":["国语","流行","80后","经典","夜晚","放松","综艺热歌",""]}]
         * sem_score : {"artist":{"lcs":1,"pos":"ps","txt":"刘德华"},"top":0}
         */

        private int inherit;
        private int isCached;
        private int priority;
        private SemScoreBean sem_score;
        private List<ResultBean> result;

        public int getInherit() {
            return inherit;
        }

        public void setInherit(int inherit) {
            this.inherit = inherit;
        }

        public int getIsCached() {
            return isCached;
        }

        public void setIsCached(int isCached) {
            this.isCached = isCached;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public SemScoreBean getSem_score() {
            return sem_score;
        }

        public void setSem_score(SemScoreBean sem_score) {
            this.sem_score = sem_score;
        }

        public List<ResultBean> getResult() {
            return result;
        }

        public void setResult(List<ResultBean> result) {
            this.result = result;
        }

        public static class SemScoreBean {
            /**
             * artist : {"lcs":1,"pos":"ps","txt":"刘德华"}
             * top : 0
             */

            private ArtistBean artist;
            private int top;

            public ArtistBean getArtist() {
                return artist;
            }

            public void setArtist(ArtistBean artist) {
                this.artist = artist;
            }

            public int getTop() {
                return top;
            }

            public void setTop(int top) {
                this.top = top;
            }

            public static class ArtistBean {
                /**
                 * lcs : 1
                 * pos : ps
                 * txt : 刘德华
                 */

                private int lcs;
                private String pos;
                private String txt;

                public int getLcs() {
                    return lcs;
                }

                public void setLcs(int lcs) {
                    this.lcs = lcs;
                }

                public String getPos() {
                    return pos;
                }

                public void setPos(String pos) {
                    this.pos = pos;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }
        }

        public static class ResultBean {
            /**
             * albumname : 刘德华 Unforgettable Concert 2010
             * itemid : 45571977
             * movienames :
             * neatsongname : ["掌声响起"]
             * pictures : [{"level":2,"path":"http://vbox.hfdn.openstorage.cn/ctipicture/2/20160303/28606fc27d27aeb92245b10445da05d9/9b63ba4b27901d779cc08c91dbffaff1_z.jpg","size":"389x389"},{"level":1,"path":"http://vbox.hfdn.openstorage.cn/ctipicture/2/20160303/28606fc27d27aeb92245b10445da05d9/9b63ba4b27901d779cc08c91dbffaff1_small.jpg","size":"300x300"}]
             * programname :
             * publishtime : 1303228800
             * singeraliasnames : [""]
             * singerids : ["25000"]
             * singernames : ["刘德华"]
             * songname : 掌声响起
             * source : iflytek
             * tagnames : ["国语","流行","伤感","80后","经典","怀旧",""]
             */

            private String albumname;
            private String itemid;
            private String movienames;
            private String programname;
            private int publishtime;
            private String songname;
            private String source;
            private List<String> neatsongname;
            private List<PicturesBean> pictures;
            private List<String> singeraliasnames;
            private List<String> singerids;
            private List<String> singernames;
            private List<String> tagnames;

            public String getAlbumname() {
                return albumname;
            }

            public void setAlbumname(String albumname) {
                this.albumname = albumname;
            }

            public String getItemid() {
                return itemid;
            }

            public void setItemid(String itemid) {
                this.itemid = itemid;
            }

            public String getMovienames() {
                return movienames;
            }

            public void setMovienames(String movienames) {
                this.movienames = movienames;
            }

            public String getProgramname() {
                return programname;
            }

            public void setProgramname(String programname) {
                this.programname = programname;
            }

            public int getPublishtime() {
                return publishtime;
            }

            public void setPublishtime(int publishtime) {
                this.publishtime = publishtime;
            }

            public String getSongname() {
                return songname;
            }

            public void setSongname(String songname) {
                this.songname = songname;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public List<String> getNeatsongname() {
                return neatsongname;
            }

            public void setNeatsongname(List<String> neatsongname) {
                this.neatsongname = neatsongname;
            }

            public List<PicturesBean> getPictures() {
                return pictures;
            }

            public void setPictures(List<PicturesBean> pictures) {
                this.pictures = pictures;
            }

            public List<String> getSingeraliasnames() {
                return singeraliasnames;
            }

            public void setSingeraliasnames(List<String> singeraliasnames) {
                this.singeraliasnames = singeraliasnames;
            }

            public List<String> getSingerids() {
                return singerids;
            }

            public void setSingerids(List<String> singerids) {
                this.singerids = singerids;
            }

            public List<String> getSingernames() {
                return singernames;
            }

            public void setSingernames(List<String> singernames) {
                this.singernames = singernames;
            }

            public List<String> getTagnames() {
                return tagnames;
            }

            public void setTagnames(List<String> tagnames) {
                this.tagnames = tagnames;
            }

            public static class PicturesBean {
                /**
                 * level : 2
                 * path : http://vbox.hfdn.openstorage.cn/ctipicture/2/20160303/28606fc27d27aeb92245b10445da05d9/9b63ba4b27901d779cc08c91dbffaff1_z.jpg
                 * size : 389x389
                 */

                private int level;
                private String path;
                private String size;

                public int getLevel() {
                    return level;
                }

                public void setLevel(int level) {
                    this.level = level;
                }

                public String getPath() {
                    return path;
                }

                public void setPath(String path) {
                    this.path = path;
                }

                public String getSize() {
                    return size;
                }

                public void setSize(String size) {
                    this.size = size;
                }
            }
        }
    }
}
