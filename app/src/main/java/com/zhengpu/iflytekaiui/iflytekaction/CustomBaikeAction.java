package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.Context;

import com.blankj.utilcode.utils.EncodeUtils;
import com.zhengpu.iflytekaiui.R;
import com.zhengpu.iflytekaiui.iflytekbean.BaikeBean;
import com.zhengpu.iflytekaiui.iflytekbean.CustomBaikeBean;
import com.zhengpu.iflytekaiui.iflytekbean.otherbean.IfCustomBaikeBean;
import com.zhengpu.iflytekaiui.iflytekutils.JsonParser;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;

import java.io.IOException;
import java.util.logging.Logger;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/12/24 0024.
 */

public class CustomBaikeAction {


    private String value;
    private String service;
    private Context context;
    private String strRequest;
    private CustomBaikeBean customBaikeBean;

    public CustomBaikeAction(String service, CustomBaikeBean customBaikeBean, String strRequest, Context context) {
        this.customBaikeBean = customBaikeBean;
        this.service = service;
        this.context = context;
        this.strRequest = strRequest;
    }

    public void start() {

        if (customBaikeBean != null && customBaikeBean.getQuery() != null) {

            String url = "http://aiui.xfyun.cn/taste/getAnswer?text=" + EncodeUtils.urlEncode(customBaikeBean.getQuery() + "百科") + "&appid=all&category=baike&timestamp=1513932137263";
            OkHttpClient okHttpClient = new OkHttpClient();
            final Request request = new Request.Builder()
                    .url(url)
                    .build();
            Call call = okHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                    SpeechRecognizerService.startSpeech(service, context.getResources().getString(R.string.error_network_text), strRequest);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String res = response.body().string();
                    IfCustomBaikeBean ifCustomBaikeBean = JsonParser.parseResultIfCustomBaikeBean(res);
                    if (ifCustomBaikeBean != null && ifCustomBaikeBean.getData() != null && ifCustomBaikeBean.getData().getAnswer() != null) {
                        SpeechRecognizerService.startSpeech(service, ifCustomBaikeBean.getData().getAnswer(), strRequest);
                    } else {
                        R4Action r4Action = new R4Action(context);
                        r4Action.start();
                    }
                }
            });
        } else {
            R4Action r4Action = new R4Action(context);
            r4Action.start();
        }
    }
}
