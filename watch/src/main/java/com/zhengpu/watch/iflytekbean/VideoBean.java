package com.zhengpu.watch.iflytekbean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zhengpu.watch.base.AppContract;

import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2017/11/29.
 */

public class VideoBean implements MultiItemEntity {


    /**
     * data : {"result":[{"actor":[{"name":"邓超","roleName":""},{"name":"王祖蓝","roleName":""},{"name":"王宝强","roleName":""},{"name":"陈赫","roleName":""},{"name":"郑恺","roleName":""},{"name":"杨颖","roleName":""},{"name":"李晨","roleName":""},{"name":"王心凌","roleName":""}],"aliasName":["跑男","奔跑吧!兄弟","跑起来!好兄弟","Running Man"],"area":["中国大陆"],"award":[{"ceremony":"第1届豆瓣电影年度榜单","title":"最受关注的大陆综艺(提名)"}],"category":"综艺","company":"","director":["岑俊义","陆浩"],"episode":[],"hot":"23794","img":"http://kchfpre.openspeech.cn/music_db_file/parastor/data/downdata/pictures/douban_video/25899362/0f3c259311fb102be3dec50e84302118.jpg","language":"国语","leadActor":[{"name":"邓超","roleName":""},{"name":"王祖蓝","roleName":""},{"name":"王宝强","roleName":""},{"name":"陈赫","roleName":""},{"name":"郑恺","roleName":""}],"name":"奔跑吧兄弟 第一季","publishTime":"2014-10-10","score":"8.3","screenWriter":["俞杭英"],"season":"第一季","source":"iflytek","tags":["电视剧","真人秀"]},{"actor":[{"name":"邓超","roleName":""},{"name":"鹿晗","roleName":""},{"name":"杨颖","roleName":""},{"name":"郑恺","roleName":""},{"name":"王祖蓝","roleName":""},{"name":"李晨","roleName":""},{"name":"陈赫","roleName":""}],"aliasName":["跑男3","Running Man season 3"],"area":["中国大陆"],"award":[],"category":"综艺","company":"","director":["岑俊义","陆皓"],"episode":[],"hot":"11563","img":"http://kchfpre.openspeech.cn/music_db_file/parastor/data/downdata/pictures/douban_video/26438888/5d263aa80ad468d74f02f2c7abc23905.jpg","language":"国语","leadActor":[{"name":"邓超","roleName":""},{"name":"鹿晗","roleName":""},{"name":"杨颖","roleName":""},{"name":"郑恺","roleName":""},{"name":"王祖蓝","roleName":""},{"name":"李晨","roleName":""},{"name":"陈赫","roleName":""}],"name":"奔跑吧兄弟 第三季","publishTime":"2015-10-30","score":"6.3","screenWriter":[],"season":"第三季","source":"iflytek","tags":["电视剧","真人秀","综艺"]},{"actor":[{"name":"邓超","roleName":""},{"name":"李晨","roleName":""},{"name":"陈赫","roleName":""},{"name":"郑恺","roleName":""},{"name":"王祖蓝","roleName":""},{"name":"鹿晗","roleName":""},{"name":"迪丽热巴","roleName":""},{"name":"杨颖","roleName":""}],"aliasName":["奔跑吧兄弟第五季","跑男5","Keep Running"],"area":["中国大陆"],"award":[],"category":"综艺","company":"","director":[],"episode":[],"hot":"10505","img":"http://kchfpre.openspeech.cn/music_db_file/parastor/data/downdata/pictures/douban_video/26830085/0f923ec3a1d79652be1ff3f266944420.jpg","language":"国语","leadActor":[{"name":"邓超","roleName":""},{"name":"李晨","roleName":""},{"name":"陈赫","roleName":""},{"name":"郑恺","roleName":""},{"name":"王祖蓝","roleName":""}],"name":"奔跑吧","publishTime":"2017-04-14","score":"6.4","screenWriter":[],"season":"","source":"iflytek","tags":["电视剧","真人秀"]},{"actor":[{"name":"邓超","roleName":""},{"name":"杨颖","roleName":""},{"name":"李晨","roleName":""},{"name":"郑恺","roleName":""},{"name":"王祖蓝","roleName":""},{"name":"陈赫","roleName":""},{"name":"鹿晗","roleName":""},{"name":"代乐乐","roleName":""},{"name":"姜超","roleName":""}],"aliasName":["跑男4","Running Man Season 4"],"area":["中国大陆"],"award":[],"category":"综艺","company":"","director":["蒋敏昊"],"episode":[],"hot":"8730","img":"http://kchfpre.openspeech.cn/music_db_file/parastor/data/downdata/pictures/douban_video/26710402/8dcaccfbea4438bdd19290f57eab09da.jpg","language":"国语","leadActor":[{"name":"邓超","roleName":""},{"name":"杨颖","roleName":""},{"name":"李晨","roleName":""},{"name":"郑恺","roleName":""},{"name":"王祖蓝","roleName":""},{"name":"陈赫","roleName":""}],"name":"奔跑吧兄弟 第四季","publishTime":"2016-04-15","score":"5.8","screenWriter":[],"season":"第四季","source":"iflytek","tags":["电视剧","真人秀","综艺"]},{"actor":[{"name":"邓超","roleName":""},{"name":"王祖蓝","roleName":""},{"name":"王宝强","roleName":""},{"name":"李晨","roleName":""},{"name":"陈赫","roleName":""},{"name":"郑恺","roleName":""},{"name":"杨颖","roleName":""}],"aliasName":["Chinese Running Man","奔跑吧!兄弟","奔跑吧","兄弟","奔跑吧兄弟"],"area":["中国大陆"],"award":[],"category":"综艺","company":"","director":[],"episode":[],"hot":"194","img":"http://kchfpre.openspeech.cn/music_db_file/parastor/data/downdata/pictures/mtime_video/217596/360c8d0d15e08bddfe1ac73daa9a1f5e.jpg","language":"国语","leadActor":[{"name":"邓超","roleName":""},{"name":"王祖蓝","roleName":""},{"name":"王宝强","roleName":""},{"name":"李晨","roleName":""}],"name":"奔跑吧兄弟","publishTime":"2014-10-10","score":"7.1","screenWriter":[],"season":"","source":"iflytek","tags":["电视剧","真人秀","综艺"]},{"actor":[{"name":"陈赫","roleName":""},{"name":"郑恺","roleName":""},{"name":"杨颖","roleName":""},{"name":"邓超","roleName":""},{"name":"王祖蓝","roleName":""},{"name":"王宝强","roleName":""},{"name":"李晨","roleName":""}],"aliasName":["Chinese Running Man"],"area":["中国大陆"],"award":[],"category":"综艺","company":"","director":[],"episode":[],"hot":"42","img":"http://kchfpre.openspeech.cn/music_db_file/parastor/data/downdata/pictures/maoyan_video/368767/2c90c84543fde459015c55e4d5691dbc.jpg","language":"","leadActor":[{"name":"陈赫","roleName":""},{"name":"郑恺","roleName":""},{"name":"杨颖","roleName":""},{"name":"邓超","roleName":""}],"name":"奔跑吧兄弟 第一季","publishTime":"2014-10-10","score":"6.2","screenWriter":[],"season":"第一季","source":"iflytek","tags":["综艺","真人秀"]},{"actor":[{"name":"杨颖","roleName":""},{"name":"陈赫","roleName":""},{"name":"王宝强","roleName":""},{"name":"王祖蓝","roleName":""},{"name":"郑恺","roleName":""},{"name":"李晨","roleName":""},{"name":"熊黛林","roleName":""},{"name":"谢依霖","roleName":""},{"name":"伊一","roleName":""},{"name":"郭京飞","roleName":""},{"name":"张爱钦","roleName":""},{"name":"金钟国","roleName":""},{"name":"邓超","roleName":""},{"name":"包贝尔","roleName":""},{"name":"范冰冰","roleName":"第一期嘉宾"},{"name":"韩庚","roleName":"第一期嘉宾"},{"name":"黄晓明","roleName":"第二期嘉宾"},{"name":"欧弟","roleName":"第三期嘉宾"},{"name":"宋佳","roleName":"第三期嘉宾"},{"name":"林更新","roleName":"第四期嘉宾"},{"name":"张艺兴","roleName":"第四期嘉宾"},{"name":"叶祖新","roleName":"第四期嘉宾"},{"name":"李治廷","roleName":"第四期嘉宾"},{"name":"蒋劲夫","roleName":"第四期嘉宾"},{"name":"吴奇隆","roleName":"第五期嘉宾"},{"name":"陈乔恩","roleName":"第五期嘉宾"},{"name":"杜淳","roleName":"第五期嘉宾"},{"name":"姚晨","roleName":"第六期嘉宾"},{"name":"萧敬腾","roleName":"第六期嘉宾"},{"name":"信","roleName":"第六期嘉宾"},{"name":"吉克隽逸","roleName":"第六期嘉宾"},{"name":"大鹏","roleName":"第六期嘉宾"},{"name":"刘涛","roleName":"第七期嘉宾"},{"name":"蔡少芬","roleName":"第九期嘉宾"},{"name":"霍思燕","roleName":"第九期嘉宾"},{"name":"李彩桦","roleName":"第九期嘉宾"},{"name":"蒋欣","roleName":"第九期嘉宾"},{"name":"江一燕","roleName":"第九期嘉宾"},{"name":"苏见信","roleName":"第六期嘉宾"}],"aliasName":["奔跑吧!兄弟","Running Man","奔跑吧兄弟","奔跑吧","兄弟","跑男","奔跑吧兄弟第二季","奔跑吧兄弟 第二季","跑男2","Running Man Season 2"],"area":["中国大陆"],"award":[{"ceremony":"第7届金扫帚奖","title":"最令人失望中小成本电影"},{"ceremony":"第6届豆瓣电影鑫像奖","title":"豆渣单元 最渣影片(华语)"},{"ceremony":"第2届豆瓣电影年度榜单","title":"1月最受关注电影(提名) ,评分最低的华语电影(提名)"},{"ceremony":"第2届豆瓣电影年度榜单","title":"最受关注的大陆综艺(提名)"}],"category":"综艺","company":"万达影视传媒有限公司","director":["胡笳","岑俊义","陆浩"],"episode":[],"hot":"0","img":"http://kchfpre.openspeech.cn/music_db_file/parastor/data/downdata/pictures/maoyan_video/246316/d24fafbab399042eed3e8f44fcd1b669.jpg","language":"国语","leadActor":[{"name":"杨颖","roleName":""},{"name":"陈赫","roleName":""},{"name":"王宝强","roleName":""},{"name":"王祖蓝","roleName":""},{"name":"李晨","roleName":""},{"name":"郑恺","roleName":""},{"name":"邓超","roleName":""},{"name":"包贝尔","roleName":""},{"name":"叶祖新","roleName":""},{"name":"蒋劲夫","roleName":""},{"name":"信","roleName":""},{"name":"吉克隽逸","roleName":""}],"name":"奔跑吧!兄弟","publishTime":"2015-01-30","score":"7.8","screenWriter":["胡笳","岑俊义","李雅弢","杨琳","俞杭英"],"season":"第二季","source":"iflytek","tags":["综艺","喜剧","动作","冒险","电视剧","真人秀"]}]}
     * rc : 0
     * semantic : [{"intent":"QUERY","slots":[{"name":"name","value":"奔跑吧兄弟"}]}]
     * service : video
     * state : {"fg::video::default::default":{"name":"1"}}
     * text : 奔跑吧兄弟
     * uuid : atn00166083@ch11710db360446f2001
     * answer : {"text":"不好意思，没有为您找到您想看的影片，为您推荐\"奔跑吧兄弟 第一季\""}
     * dialog_stat : dataInvalid
     * save_history : true
     * sid : atn00166083@ch11710db360446f2001
     */

    private DataBean data;
    private int rc;
    private String service;
    private String text;
    private String uuid;
    private String dialog_stat;
    private boolean save_history;
    private String sid;
    private AnswerBean answer;

    @Override
    public int getItemType() {
        return AppContract.VideoBean;
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

    public AnswerBean getAnswer() {
        return answer;
    }

    public void setAnswer(AnswerBean answer) {
        this.answer = answer;
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
             * actor : [{"name":"邓超","roleName":""},{"name":"王祖蓝","roleName":""},{"name":"王宝强","roleName":""},{"name":"陈赫","roleName":""},{"name":"郑恺","roleName":""},{"name":"杨颖","roleName":""},{"name":"李晨","roleName":""},{"name":"王心凌","roleName":""}]
             * aliasName : ["跑男","奔跑吧!兄弟","跑起来!好兄弟","Running Man"]
             * area : ["中国大陆"]
             * award : [{"ceremony":"第1届豆瓣电影年度榜单","title":"最受关注的大陆综艺(提名)"}]
             * category : 综艺
             * company :
             * director : ["岑俊义","陆浩"]
             * episode : []
             * hot : 23794
             * img : http://kchfpre.openspeech.cn/music_db_file/parastor/data/downdata/pictures/douban_video/25899362/0f3c259311fb102be3dec50e84302118.jpg
             * language : 国语
             * leadActor : [{"name":"邓超","roleName":""},{"name":"王祖蓝","roleName":""},{"name":"王宝强","roleName":""},{"name":"陈赫","roleName":""},{"name":"郑恺","roleName":""}]
             * name : 奔跑吧兄弟 第一季
             * publishTime : 2014-10-10
             * score : 8.3
             * screenWriter : ["俞杭英"]
             * season : 第一季
             * source : iflytek
             * tags : ["电视剧","真人秀"]
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
                 * name : 邓超
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
                 * ceremony : 第1届豆瓣电影年度榜单
                 * title : 最受关注的大陆综艺(提名)
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
                 * name : 邓超
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
