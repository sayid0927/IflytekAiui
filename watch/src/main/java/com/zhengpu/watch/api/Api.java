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
package com.zhengpu.watch.api;


import com.zhengpu.watch.base.Constant;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class Api {

    public static Api instance;

    private ApiService service;

    public Api(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.API_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 添加Rx适配器
                .addConverterFactory(GsonConverterFactory.create()) // 添加Gson转换器
                .client(okHttpClient)
                .build();
        service = retrofit.create(ApiService.class);
    }

    public static Api getInstance(OkHttpClient okHttpClient) {
        if (instance == null)
            instance = new Api(okHttpClient);
        return instance;
    }

//    public Observable<ZhiHuNewsBean> getDailyNews() {
//        return service.getDailyNews();
//    }
//
//
//    public Observable<WXItemBean> getWXHot(int num, int page) {
//        return service.getWXHot(Constant.WECHAT_KEY_API, num, page);
//    }
//
//    public Observable<KuGouSongBean> getSearchKugouSong(String keyword, String page, String pagesize) {
//        return service.getSearchKugouSong("json", keyword, page, pagesize);
//    }
//
//    public Observable<KuGouSongInfoResult> getKugouSongInfo(String hash) {
//        return service.getKugouSongInfo("playInfo", hash);
//    }
//
//    public Observable<TianJokeBean> getTianJoke() {
//        return service.getJoke(Constant.WECHAT_KEY_API);
//    }
//
//     //http://mobilecdn.kugou.com/new/app/i/krc.php?
//    // keyword=" + keyword + "&timelength=" + duration + "&type=1&client=pc&cmd=200&hash=" + hash
//    public Observable<Response<ResponseBody>> downloadLyric(String keyword,int duration,String hash) {
//        return service.downloadLyric(keyword,duration,1,"pc",200,hash);
//    }


}
