/**
 * Copyright 2016 JustWayward Team
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zhengpu.watch.base;

import java.io.File;

public class Constant {

    //需要APIKEY请去 http://www.tianapi.com/#wxnew 申请,复用会减少访问可用次数。还有很多别的接口大家可以研究。

    public static final String WECHAT_KEY_API = "e6d6ec3ba2f9d7a3051a6c09f0524738";


    public static final String FILEPATH = "temp";

    public static final String API_BASE_URL = "http://tingapi.ting.baidu.com/";
    public static final String ZHIHU_BASE_URL = "http://news-at.zhihu.com/";
    public static final String TIANAPI_BASE_URL = "http://api.tianapi.com/";

    public static final String KUGOU_BASE_URL = "http://mobilecdn.kugou.com/";
    public static final String KUGOU_SONG_INFO_BASE_URL = "http://m.kugou.com/";

    /**
     *  临时目录
     */
    public final static String PATH_TEMP = "zhengpu";

    /**
     * 歌词目录
     */
    public final static String PATH_LYRICS = PATH_TEMP + File.separator + "lyrics";

}
