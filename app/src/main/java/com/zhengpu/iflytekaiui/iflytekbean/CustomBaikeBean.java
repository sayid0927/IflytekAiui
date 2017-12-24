package com.zhengpu.iflytekaiui.iflytekbean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/24 0024.
 */

public class CustomBaikeBean {


    /**
     * category : OPENAPPTEST.custom_baike:3.0
     * intentType : custom
     * query : 周杰伦
     * query_ws : 周杰伦/NPP//
     * rc : 0
     * nlis : true
     * service : OPENAPPTEST.custom_baike
     * uuid : atn002d0a96@ch3ed30d9af5f46f2001
     * vendor : OPENAPPTEST
     * version : 3.0
     * semantic : [{"intent":"baike","score":1,"slots":[{"begin":0,"end":3,"name":"entrypoint:ent","normValue":"周杰伦","value":"周杰伦"},{"begin":0,"end":3,"name":"Director","normValue":"周杰伦","value":"周杰伦"}]}]
     * state : null
     * moreResults : [{"category":"OPENAPPTEST.custom_baike:3.0","intentType":"custom","query":"周杰伦","query_ws":"周杰伦/NPP//","rc":0,"nlis":"true","service":"OPENAPPTEST.custom_baike","uuid":"atn002d0a96@ch3ed30d9af5f46f2001","vendor":"OPENAPPTEST","version":"3.0","semantic":[{"intent":"baike","score":1,"slots":[{"begin":0,"end":3,"name":"entrypoint:ent","normValue":"","value":"周杰伦"},{"begin":0,"end":3,"name":"Artist","normValue":"","value":"周杰伦"}]}],"state":null,"text":"周杰伦"}]
     * sid : atn002d0a96@ch3ed30d9af5f46f2001
     * text : 周杰伦
     */

    private String category;
    private String intentType;
    private String query;
    private String query_ws;
    private int rc;
    private String nlis;
    private String service;
    private String uuid;
    private String vendor;
    private String version;
    private Object state;
    private String sid;
    private String text;
    private List<SemanticBean> semantic;
    private List<MoreResultsBean> moreResults;





    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIntentType() {
        return intentType;
    }

    public void setIntentType(String intentType) {
        this.intentType = intentType;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQuery_ws() {
        return query_ws;
    }

    public void setQuery_ws(String query_ws) {
        this.query_ws = query_ws;
    }

    public int getRc() {
        return rc;
    }

    public void setRc(int rc) {
        this.rc = rc;
    }

    public String getNlis() {
        return nlis;
    }

    public void setNlis(String nlis) {
        this.nlis = nlis;
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

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Object getState() {
        return state;
    }

    public void setState(Object state) {
        this.state = state;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

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

    public List<MoreResultsBean> getMoreResults() {
        return moreResults;
    }

    public void setMoreResults(List<MoreResultsBean> moreResults) {
        this.moreResults = moreResults;
    }

    public static class SemanticBean {
        /**
         * intent : baike
         * score : 1
         * slots : [{"begin":0,"end":3,"name":"entrypoint:ent","normValue":"周杰伦","value":"周杰伦"},{"begin":0,"end":3,"name":"Director","normValue":"周杰伦","value":"周杰伦"}]
         */

        private String intent;
        private int score;
        private List<SlotsBean> slots;

        public String getIntent() {
            return intent;
        }

        public void setIntent(String intent) {
            this.intent = intent;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public List<SlotsBean> getSlots() {
            return slots;
        }

        public void setSlots(List<SlotsBean> slots) {
            this.slots = slots;
        }

        public static class SlotsBean {
            /**
             * begin : 0
             * end : 3
             * name : entrypoint:ent
             * normValue : 周杰伦
             * value : 周杰伦
             */

            private int begin;
            private int end;
            private String name;
            private String normValue;
            private String value;

            public int getBegin() {
                return begin;
            }

            public void setBegin(int begin) {
                this.begin = begin;
            }

            public int getEnd() {
                return end;
            }

            public void setEnd(int end) {
                this.end = end;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNormValue() {
                return normValue;
            }

            public void setNormValue(String normValue) {
                this.normValue = normValue;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }

    public static class MoreResultsBean {
        /**
         * category : OPENAPPTEST.custom_baike:3.0
         * intentType : custom
         * query : 周杰伦
         * query_ws : 周杰伦/NPP//
         * rc : 0
         * nlis : true
         * service : OPENAPPTEST.custom_baike
         * uuid : atn002d0a96@ch3ed30d9af5f46f2001
         * vendor : OPENAPPTEST
         * version : 3.0
         * semantic : [{"intent":"baike","score":1,"slots":[{"begin":0,"end":3,"name":"entrypoint:ent","normValue":"","value":"周杰伦"},{"begin":0,"end":3,"name":"Artist","normValue":"","value":"周杰伦"}]}]
         * state : null
         * text : 周杰伦
         */

        private String category;
        private String intentType;
        private String query;
        private String query_ws;
        private int rc;
        private String nlis;
        private String service;
        private String uuid;
        private String vendor;
        private String version;
        private Object state;
        private String text;
        private List<SemanticBeanX> semantic;

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getIntentType() {
            return intentType;
        }

        public void setIntentType(String intentType) {
            this.intentType = intentType;
        }

        public String getQuery() {
            return query;
        }

        public void setQuery(String query) {
            this.query = query;
        }

        public String getQuery_ws() {
            return query_ws;
        }

        public void setQuery_ws(String query_ws) {
            this.query_ws = query_ws;
        }

        public int getRc() {
            return rc;
        }

        public void setRc(int rc) {
            this.rc = rc;
        }

        public String getNlis() {
            return nlis;
        }

        public void setNlis(String nlis) {
            this.nlis = nlis;
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

        public String getVendor() {
            return vendor;
        }

        public void setVendor(String vendor) {
            this.vendor = vendor;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public Object getState() {
            return state;
        }

        public void setState(Object state) {
            this.state = state;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public List<SemanticBeanX> getSemantic() {
            return semantic;
        }

        public void setSemantic(List<SemanticBeanX> semantic) {
            this.semantic = semantic;
        }

        public static class SemanticBeanX {
            /**
             * intent : baike
             * score : 1
             * slots : [{"begin":0,"end":3,"name":"entrypoint:ent","normValue":"","value":"周杰伦"},{"begin":0,"end":3,"name":"Artist","normValue":"","value":"周杰伦"}]
             */

            private String intent;
            private int score;
            private List<SlotsBeanX> slots;

            public String getIntent() {
                return intent;
            }

            public void setIntent(String intent) {
                this.intent = intent;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public List<SlotsBeanX> getSlots() {
                return slots;
            }

            public void setSlots(List<SlotsBeanX> slots) {
                this.slots = slots;
            }

            public static class SlotsBeanX {
                /**
                 * begin : 0
                 * end : 3
                 * name : entrypoint:ent
                 * normValue :
                 * value : 周杰伦
                 */

                private int begin;
                private int end;
                private String name;
                private String normValue;
                private String value;

                public int getBegin() {
                    return begin;
                }

                public void setBegin(int begin) {
                    this.begin = begin;
                }

                public int getEnd() {
                    return end;
                }

                public void setEnd(int end) {
                    this.end = end;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getNormValue() {
                    return normValue;
                }

                public void setNormValue(String normValue) {
                    this.normValue = normValue;
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
}
