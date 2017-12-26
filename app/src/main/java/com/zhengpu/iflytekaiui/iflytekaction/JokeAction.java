package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.Context;

import com.blankj.utilcode.utils.EncodeUtils;
import com.zhengpu.iflytekaiui.R;
import com.zhengpu.iflytekaiui.iflytekbean.otherbean.IfCustomBaikeBean;
import com.zhengpu.iflytekaiui.iflytekbean.otherbean.TianJokeBean;
import com.zhengpu.iflytekaiui.iflytekutils.JsonParser;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * sayid ....
 * Created by wengmf on 2017/11/29.
 */

public class JokeAction {


    private Context context;
    private String service;
    private String strRequest;


    public JokeAction(String service, String strRequest,Context context) {
        this.service = service;
        this.context = context;
        this.strRequest = strRequest;
    }

    public void start() {

        String url = "http://api.tianapi.com/txapi/joke/?key=e6d6ec3ba2f9d7a3051a6c09f0524738";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                SpeechRecognizerService.startSpeech(service, context.getResources().getString(R.string.error_network_text),strRequest);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                TianJokeBean tianJokeBean = JsonParser.parseResultTianJokeBean(res);
                if (tianJokeBean != null && tianJokeBean.getNewslist().size()!=0 && tianJokeBean.getNewslist().get(0).getTitle()!=null && tianJokeBean.getNewslist().get(0).getContent()!=null) {

                    // 去除特殊字符串
                    String content = tianJokeBean.getNewslist().get(0).getContent();
                    content = content.replace("<", " ");
                    content = content.replace("b", " ");
                    content = content.replace("r", " ");
                    content = content.replace("/", " ");
                    content = content.replace(">", " ");
                    tianJokeBean.getNewslist().get(0).setContent(content);

                    SpeechRecognizerService.startSpeech(service,"请欣赏笑话"+ tianJokeBean.getNewslist().get(0).getTitle() +tianJokeBean.getNewslist().get(0).getContent(),strRequest);
                }
            }
        });
    }
}
