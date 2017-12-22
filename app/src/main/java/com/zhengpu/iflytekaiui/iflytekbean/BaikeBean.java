package com.zhengpu.iflytekaiui.iflytekbean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/23 0023.
 */

public class BaikeBean {


    /**
     * data : {"result":[{"category":[],"img":"http://a3.att.hudong.com/36/75/20300542878330141507757086727_140.jpg","sectionList":[],"summary":"《催眠大师》，原名《催眠档案之彻夜未眠》，是由陈正道导演执导，徐峥、莫文蔚、吕中等主演的国产悬疑电影。主要讲述了知名心理治疗师徐瑞宁为古怪的女病人任小妍进行催眠治疗不料却陷入了对方事先设置好的\u201c陷阱\u201d的故事。通过两人多次互相催眠，两个人过去的痛苦经历便完整地呈现在观众面前，影片情节复杂，逻辑缜密，看点颇多。影片由万达影视传媒有限公司出品，于2014年4月29日上映，演员精湛的演技和精彩的剧情获得了观众较高的评价和十分可观的票房收入。","title":"催眠大师","url":"http://www.baike.com/gwiki/催眠大师"}]}
     * rc : 0
     * semantic : [{"intent":"QUERY","slots":[{"name":"keyword","value":"催眠大师"}]}]
     * service : baike
     * state : {"fg::baike::default::default":{"state":"default"}}
     * text : 百科催眠大师
     * uuid : atn00da5a1e@ch74900d722dee6f2601
     * used_state : {"state_key":"fg::baike::default::default","state":"default"}
     * answer : {"text":"《催眠大师》，原名《催眠档案之彻夜未眠》，是由陈正道导演执导，徐峥、莫文蔚、吕中等主演的国产悬疑电影。主要讲述了知名心理治疗师徐瑞宁为古怪的女病人任小妍进行催眠治疗不料却陷入了对方事先设置好的\u201c陷阱\u201d的故事。通过两人多次互相催眠，两个人过去的痛苦经历便完整地呈现在观众面前，影片情节复杂，逻辑缜密，看点颇多。影片由万达影视传媒有限公司出品，于2014年4月29日上映，演员精湛的演技和精彩的剧情获得了观众较高的评价和十分可观的票房收入。"}
     * dialog_stat : dataInvalid
     * save_history : true
     * sid : atn00da5a1e@ch74900d722dee6f2601
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

    private List<SemanticBean> semantic;


    public static class SemanticBean {
        /**
         * semantic : [{"intent":"QUERY","slots":[{"name":"keyword","value":"太阳的后裔"}]}]
         */
        private String intent;
        private SlotsBean slotsBean;

        public String getIntent() {
            return intent;
        }

        public void setIntent(String intent) {
            this.intent = intent;
        }

        public SlotsBean getSlotsBean() {
            return slotsBean;
        }

        public void setSlotsBean(SlotsBean slotsBean) {
            this.slotsBean = slotsBean;
        }

        public static class SlotsBean {

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

    /**
     * data : {"result":[{"category":[],"img":"http://a3.att.hudong.com/36/75/20300542878330141507757086727_140.jpg","sectionList":[],"summary":"《催眠大师》，原名《催眠档案之彻夜未眠》，是由陈正道导演执导，徐峥、莫文蔚、吕中等主演的国产悬疑电影。主要讲述了知名心理治疗师徐瑞宁为古怪的女病人任小妍进行催眠治疗不料却陷入了对方事先设置好的\u201c陷阱\u201d的故事。通过两人多次互相催眠，两个人过去的痛苦经历便完整地呈现在观众面前，影片情节复杂，逻辑缜密，看点颇多。影片由万达影视传媒有限公司出品，于2014年4月29日上映，演员精湛的演技和精彩的剧情获得了观众较高的评价和十分可观的票房收入。","title":"催眠大师","url":"http://www.baike.com/gwiki/催眠大师"}]}
     * semantic : [{"intent":"QUERY","slots":[{"name":"keyword","value":"催眠大师"}]}]
     * state : {"fg::baike::default::default":{"state":"default"}}
     * used_state : {"state_key":"fg::baike::default::default","state":"default"}
     * answer : {"text":"《催眠大师》，原名《催眠档案之彻夜未眠》，是由陈正道导演执导，徐峥、莫文蔚、吕中等主演的国产悬疑电影。主要讲述了知名心理治疗师徐瑞宁为古怪的女病人任小妍进行催眠治疗不料却陷入了对方事先设置好的\u201c陷阱\u201d的故事。通过两人多次互相催眠，两个人过去的痛苦经历便完整地呈现在观众面前，影片情节复杂，逻辑缜密，看点颇多。影片由万达影视传媒有限公司出品，于2014年4月29日上映，演员精湛的演技和精彩的剧情获得了观众较高的评价和十分可观的票房收入。"}
     */


    public static class AnswerBean {
        /**
         * text : 等于91125
         * type : T
         */

        private String text;


        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

    }


    public List<SemanticBean> getSemantic() {
        return semantic;
    }

    public void setSemantic(List<SemanticBean> semantic) {
        this.semantic = semantic;
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
             * category : []
             * img : http://a3.att.hudong.com/36/75/20300542878330141507757086727_140.jpg
             * sectionList : []
             * summary : 《催眠大师》，原名《催眠档案之彻夜未眠》，是由陈正道导演执导，徐峥、莫文蔚、吕中等主演的国产悬疑电影。主要讲述了知名心理治疗师徐瑞宁为古怪的女病人任小妍进行催眠治疗不料却陷入了对方事先设置好的“陷阱”的故事。通过两人多次互相催眠，两个人过去的痛苦经历便完整地呈现在观众面前，影片情节复杂，逻辑缜密，看点颇多。影片由万达影视传媒有限公司出品，于2014年4月29日上映，演员精湛的演技和精彩的剧情获得了观众较高的评价和十分可观的票房收入。
             * title : 催眠大师
             * url : http://www.baike.com/gwiki/催眠大师
             */

            private String img;
            private String summary;
            private String title;
            private String url;
            private List<?> category;
            private List<?> sectionList;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public List<?> getCategory() {
                return category;
            }

            public void setCategory(List<?> category) {
                this.category = category;
            }

            public List<?> getSectionList() {
                return sectionList;
            }

            public void setSectionList(List<?> sectionList) {
                this.sectionList = sectionList;
            }
        }
    }
}
