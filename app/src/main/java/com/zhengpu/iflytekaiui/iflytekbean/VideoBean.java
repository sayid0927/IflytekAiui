package com.zhengpu.iflytekaiui.iflytekbean;

import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2017/11/29.
 */

public class VideoBean {


    /**
     * data : {"result":[{"actor":[{"name":"达米安·勃纳尔","roleName":""},{"name":"克里斯蒂安·布耶特","roleName":""},{"name":"劳尔·卡拉米","roleName":""},{"name":"英迪亚·海尔","roleName":""},{"name":"拉斐尔·蒂里","roleName":""},{"name":"Basile Meilleurat","roleName":"Yoann"},{"name":"Sébastien Novac","roleName":"Kibor"},{"name":"茵蒂雅·海尔","roleName":"Marie 玛丽"},{"name":"Christian Bouillette","roleName":""},{"name":"Raphal Thierry","roleName":""},{"name":"Baptiste Roques","roleName":"Lucas"},{"name":"Adrien Marsal","roleName":"Enzo"},{"name":"Tangi Belbeoc'h","roleName":"Le clochard"},{"name":"Jakez André","roleName":""},{"name":"Mathieu Milella","roleName":"Gendarme 1"},{"name":"Charles Bénéat","roleName":""},{"name":"Mathieu Philibert","roleName":"Gendarme 2"},{"name":"Clet Beyer","roleName":""},{"name":"Erwan Chapel","roleName":""},{"name":"Johan Floch","roleName":""},{"name":"Grégory Hairon","roleName":""},{"name":"Joan Jacq","roleName":""},{"name":"Stéphane Leucart","roleName":""},{"name":"Thierry Machard","roleName":""},{"name":"Estelle Bourget","roleName":""},{"name":"Jean-Michel Gerbeau","roleName":""},{"name":"Stéphanie Finot","roleName":""},{"name":"Emmanuel Vergnault","roleName":""},{"name":"Fernando Jesus de Abreu","roleName":""},{"name":"Rui Miguel Dias Jorge","roleName":""},{"name":"Emmanuelle Fitamant","roleName":""},{"name":"David Turner","roleName":""},{"name":"Ilia Squiban","roleName":""},{"name":"Abel Bonazzi Saint-Blancat","roleName":""}],"aliasName":["Rester vertical,天天向上","垂直人生","保持勃起","Staying Vertical","Rester vertical"],"area":["法国"],"award":[{"ceremony":"第69届戛纳电影节","title":"主竞赛单元 金棕榈奖(提名)"},{"ceremony":"第69届戛纳电影节","title":"金棕榈奖(提名) ,同志棕榈奖(提名)"},{"ceremony":"戛纳电影节","title":"同志棕榈奖(提名) ,金棕榈奖(提名)"},{"ceremony":"法国凯撒奖","title":"凯撒奖-最佳新人男演员(提名)"}],"category":"电影","company":"Strand Releasing","director":["阿兰·吉罗迪"],"episode":[],"hot":"1961","img":"http://kchfpre.openspeech.cn/music_db_file/parastor/data/downdata/pictures/douban_video/26678594/b2228126b5d411646a9ef7ea2b145fa6.jpg","language":"法语","leadActor":[{"name":"达米安·勃纳尔","roleName":""},{"name":"克里斯蒂安·布耶特","roleName":""},{"name":"劳尔·卡拉米","roleName":""},{"name":"英迪亚·海尔","roleName":""},{"name":"拉斐尔·蒂里","roleName":""},{"name":"茵蒂雅·海尔","roleName":"Marie 玛丽"},{"name":"Christian Bouillette","roleName":""}],"name":"保持站立","publishTime":"2016-05-12","score":"6.1","screenWriter":["阿兰·吉罗迪"],"season":"","source":"iflytek","tags":["剧情","情色","喜剧"]},{"actor":[{"name":"汪涵","roleName":""},{"name":"欧弟","roleName":""},{"name":"田源","roleName":""},{"name":"钱枫","roleName":""},{"name":"金恩圣","roleName":""},{"name":"大张伟","roleName":""},{"name":"王一博","roleName":""},{"name":"矢野浩二","roleName":""},{"name":"俞灏明","roleName":""},{"name":"孟玥","roleName":""},{"name":"陈思","roleName":""},{"name":"黄婷婷","roleName":""},{"name":"龚诗淇","roleName":""},{"name":"陆婷","roleName":""},{"name":"曾艳芬","roleName":""},{"name":"何晓玉","roleName":""},{"name":"鞠婧祎","roleName":""},{"name":"唐安琪","roleName":""},{"name":"朱元冰","roleName":"朱元冰"},{"name":"程琳","roleName":""},{"name":"姚贝娜","roleName":""},{"name":"涂们","roleName":""}],"aliasName":["天天向上","Day Day Up"],"area":["中国大陆"],"award":[],"category":"电影","company":"","director":["毛豆豆"],"episode":[],"hot":"2","img":"http://kchfpre.openspeech.cn/music_db_file/parastor/data/downdata/pictures/mtime_video/183358/98c960455a053ce75c625a0caec5af69.jpg","language":"国语","leadActor":[{"name":"汪涵","roleName":""},{"name":"欧弟","roleName":""},{"name":"田源","roleName":""},{"name":"钱枫","roleName":""},{"name":"金恩圣","roleName":""}],"name":"天天向上","publishTime":"2008-08-04","score":"0.0","screenWriter":[],"season":"","source":"iflytek","tags":["电视剧","脱口秀","综艺"]}]}
     * save_history : true
     * rc : 0
     * semantic : [{"intent":"QUERY","slots":[{"name":"name","value":"天天向上"}]}]
     * service : video
     * state : {"fg::video::default::default":{"name":"1","reserved_intent":"1"}}
     * text : 播放天天向上
     * uuid : atn002c174c@ch03520db7b9fa6f1d01
     * answer : {"text":"不好意思，没有为您找到您想看的影片，为您推荐\"保持站立\""}
     * dialog_stat : dataInvalid
     * moreResults : [{"text":"播放天天向上","rc":3,"semantic":[{"intent":"PLAY","slots":[{"name":"song","value":"天天向上"}]}],"service":"musicX","state":{"fg::musicX::default::default":{"song":"1"},"fg::video::default::default":{"name":"1","reserved_intent":"1"}}}]
     * sid : atn002c174c@ch03520db7b9fa6f1d01
     */

    private DataBean data;
    private boolean save_history;
    private int rc;
    private String service;
    private String text;
    private String uuid;
    private AnswerBean answer;
    private String dialog_stat;
    private String sid;
    private List<SemanticBean> semantic;
    private List<MoreResultsBean> moreResults;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public boolean isSave_history() {
        return save_history;
    }

    public void setSave_history(boolean save_history) {
        this.save_history = save_history;
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

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public List<SemanticBean> getSemantic() {
        return semantic;
    }

    public void setSemantic(List<SemanticBean> semantic) {
        this.semantic = semantic;
    }

    public List<MoreResultsBean> getMoreResults() {
        return moreResults;
    }

    public void setMoreResults(List<MoreResultsBean> moreResults) {
        this.moreResults = moreResults;
    }

    public static class MoreResultsBean {
        /***
         * {"text":"播放天天向上","rc":3,"
         * semantic":[{"intent":"PLAY","slots":[{"name":"song","value":"天天向上"}]}],"service":"musicX",
         * "state":{"fg::musicX::default::default":{"song":"1"},"fg::video::default::default":{"name":"1","reserved_intent":"1"}}}
         */

        private String text;
        private List<SemanticBean> semantic;
        private  String service;



        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public List<SemanticBean> getSemantic() {
            return semantic;
        }

        public void setSemantic(List<SemanticBean> semantic) {
            this.semantic = semantic;
        }

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public static class SemanticBean {
            /**
             * semantic : [{"intent":"PLAY","slots":[{"name":"song","value":"我的地盘"}]}]
             */

            private String intent;
            private List<SlotsBean> slots;


            public String getIntent() {
                return intent;
            }

            public void setIntent(String intent) {
                this.intent = intent;
            }

            public List<SlotsBean> getSlots() {
                return slots;
            }

            public void setSlots(List<SlotsBean> slots) {
                this.slots = slots;
            }

            public static class SlotsBean {
                /**
                 * semantic : [{"intent":"PLAY","slots":[{"name":"song","value":"我的地盘"}]}]
                 */
                private String name;
                private String value;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }
        }
    }

    public static class SemanticBean {
        /***
         * intent":"QUERY","slots":[{"name":"name","value":"天天向上"}
         */
        private String intent;
        private List<slotsBean> slots;

        public String getIntent() {
            return intent;
        }

        public void setIntent(String intent) {
            this.intent = intent;
        }

        public List<slotsBean> getSlots() {
            return slots;
        }

        public void setSlots(List<slotsBean> slots) {
            this.slots = slots;
        }

        public static class slotsBean {

            private String name;
            private String value;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }

    public static class AnswerBean {
        /**
         * answer : {"text":"不好意思，没有为您找到您想看的影片，为您推荐\"保持站立\""}
         */

        private String text;
        private String type;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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
             * actor : [{"name":"达米安·勃纳尔","roleName":""},{"name":"克里斯蒂安·布耶特","roleName":""},{"name":"劳尔·卡拉米","roleName":""},{"name":"英迪亚·海尔","roleName":""},{"name":"拉斐尔·蒂里","roleName":""},{"name":"Basile Meilleurat","roleName":"Yoann"},{"name":"Sébastien Novac","roleName":"Kibor"},{"name":"茵蒂雅·海尔","roleName":"Marie 玛丽"},{"name":"Christian Bouillette","roleName":""},{"name":"Raphal Thierry","roleName":""},{"name":"Baptiste Roques","roleName":"Lucas"},{"name":"Adrien Marsal","roleName":"Enzo"},{"name":"Tangi Belbeoc'h","roleName":"Le clochard"},{"name":"Jakez André","roleName":""},{"name":"Mathieu Milella","roleName":"Gendarme 1"},{"name":"Charles Bénéat","roleName":""},{"name":"Mathieu Philibert","roleName":"Gendarme 2"},{"name":"Clet Beyer","roleName":""},{"name":"Erwan Chapel","roleName":""},{"name":"Johan Floch","roleName":""},{"name":"Grégory Hairon","roleName":""},{"name":"Joan Jacq","roleName":""},{"name":"Stéphane Leucart","roleName":""},{"name":"Thierry Machard","roleName":""},{"name":"Estelle Bourget","roleName":""},{"name":"Jean-Michel Gerbeau","roleName":""},{"name":"Stéphanie Finot","roleName":""},{"name":"Emmanuel Vergnault","roleName":""},{"name":"Fernando Jesus de Abreu","roleName":""},{"name":"Rui Miguel Dias Jorge","roleName":""},{"name":"Emmanuelle Fitamant","roleName":""},{"name":"David Turner","roleName":""},{"name":"Ilia Squiban","roleName":""},{"name":"Abel Bonazzi Saint-Blancat","roleName":""}]
             * aliasName : ["Rester vertical,天天向上","垂直人生","保持勃起","Staying Vertical","Rester vertical"]
             * area : ["法国"]
             * award : [{"ceremony":"第69届戛纳电影节","title":"主竞赛单元 金棕榈奖(提名)"},{"ceremony":"第69届戛纳电影节","title":"金棕榈奖(提名) ,同志棕榈奖(提名)"},{"ceremony":"戛纳电影节","title":"同志棕榈奖(提名) ,金棕榈奖(提名)"},{"ceremony":"法国凯撒奖","title":"凯撒奖-最佳新人男演员(提名)"}]
             * category : 电影
             * company : Strand Releasing
             * director : ["阿兰·吉罗迪"]
             * episode : []
             * hot : 1961
             * img : http://kchfpre.openspeech.cn/music_db_file/parastor/data/downdata/pictures/douban_video/26678594/b2228126b5d411646a9ef7ea2b145fa6.jpg
             * language : 法语
             * leadActor : [{"name":"达米安·勃纳尔","roleName":""},{"name":"克里斯蒂安·布耶特","roleName":""},{"name":"劳尔·卡拉米","roleName":""},{"name":"英迪亚·海尔","roleName":""},{"name":"拉斐尔·蒂里","roleName":""},{"name":"茵蒂雅·海尔","roleName":"Marie 玛丽"},{"name":"Christian Bouillette","roleName":""}]
             * name : 保持站立
             * publishTime : 2016-05-12
             * score : 6.1
             * screenWriter : ["阿兰·吉罗迪"]
             * season :
             * source : iflytek
             * tags : ["剧情","情色","喜剧"]
             */

            private String category;
            private String company;
            private String hot;
            private String img;
            private String language;
            private String name;
            private String publishTime;
            private String score;
            private String season;
            private String source;
            private List<ActorBean> actor;
            private List<String> aliasName;
            private List<String> area;
            private List<AwardBean> award;
            private List<String> director;
            private List<?> episode;
            private List<LeadActorBean> leadActor;
            private List<String> screenWriter;
            private List<String> tags;

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getCompany() {
                return company;
            }

            public void setCompany(String company) {
                this.company = company;
            }

            public String getHot() {
                return hot;
            }

            public void setHot(String hot) {
                this.hot = hot;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getLanguage() {
                return language;
            }

            public void setLanguage(String language) {
                this.language = language;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(String publishTime) {
                this.publishTime = publishTime;
            }

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
            }

            public String getSeason() {
                return season;
            }

            public void setSeason(String season) {
                this.season = season;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public List<ActorBean> getActor() {
                return actor;
            }

            public void setActor(List<ActorBean> actor) {
                this.actor = actor;
            }

            public List<String> getAliasName() {
                return aliasName;
            }

            public void setAliasName(List<String> aliasName) {
                this.aliasName = aliasName;
            }

            public List<String> getArea() {
                return area;
            }

            public void setArea(List<String> area) {
                this.area = area;
            }

            public List<AwardBean> getAward() {
                return award;
            }

            public void setAward(List<AwardBean> award) {
                this.award = award;
            }

            public List<String> getDirector() {
                return director;
            }

            public void setDirector(List<String> director) {
                this.director = director;
            }

            public List<?> getEpisode() {
                return episode;
            }

            public void setEpisode(List<?> episode) {
                this.episode = episode;
            }

            public List<LeadActorBean> getLeadActor() {
                return leadActor;
            }

            public void setLeadActor(List<LeadActorBean> leadActor) {
                this.leadActor = leadActor;
            }

            public List<String> getScreenWriter() {
                return screenWriter;
            }

            public void setScreenWriter(List<String> screenWriter) {
                this.screenWriter = screenWriter;
            }

            public List<String> getTags() {
                return tags;
            }

            public void setTags(List<String> tags) {
                this.tags = tags;
            }

            public static class ActorBean {
                /**
                 * name : 达米安·勃纳尔
                 * roleName :
                 */

                private String name;
                private String roleName;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getRoleName() {
                    return roleName;
                }

                public void setRoleName(String roleName) {
                    this.roleName = roleName;
                }
            }

            public static class AwardBean {
                /**
                 * ceremony : 第69届戛纳电影节
                 * title : 主竞赛单元 金棕榈奖(提名)
                 */

                private String ceremony;
                private String title;

                public String getCeremony() {
                    return ceremony;
                }

                public void setCeremony(String ceremony) {
                    this.ceremony = ceremony;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }

            public static class LeadActorBean {
                /**
                 * name : 达米安·勃纳尔
                 * roleName :
                 */

                private String name;
                private String roleName;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getRoleName() {
                    return roleName;
                }

                public void setRoleName(String roleName) {
                    this.roleName = roleName;
                }
            }
        }
    }
}
