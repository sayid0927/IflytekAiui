package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.nfc.cardemulation.CardEmulation;

import com.blankj.utilcode.utils.EncodeUtils;
import com.zhengpu.iflytekaiui.iflytekbean.WeatherBean;
import com.zhengpu.iflytekaiui.iflytekbean.WebSearchBean;

import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2018/1/16.
 */

public class WebSearchAction {

    private String service;
    private String request;
    private WebSearchBean webSearchBean;
    private Context context;
    private String channel, keyword;
    private Intent intent;
    private String url;


    public WebSearchAction(String service, WebSearchBean webSearchBean, String request, Context context) {
        this.service = service;
        this.request = request;
        this.webSearchBean = webSearchBean;
        this.context = context;

    }


    public void start() {

        if (webSearchBean != null && webSearchBean.getSemantic() != null && webSearchBean.getSemantic().get(0).getSlots() != null) {

            for (int i = 0; i < webSearchBean.getSemantic().get(0).getSlots().size(); i++) {
                String name = webSearchBean.getSemantic().get(0).getSlots().get(i).getName();
                if (name.equals("channel")) {
                    channel = webSearchBean.getSemantic().get(0).getSlots().get(i).getValue();
                } else if (name.equals("keyword")) {
                    keyword = webSearchBean.getSemantic().get(0).getSlots().get(i).getValue();
                }
            }

            switch (channel) {
                case "taobao":

                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    url = "https://s.m.taobao.com/h5?q=" + EncodeUtils.urlEncode(keyword);
                    intent.setData(Uri.parse(url));
                    context.startActivity(intent);
                    break;
                case "baidu":

                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    url = "https://m.baidu.com/from=844b/s?word=" + EncodeUtils.urlEncode(keyword);
                    intent.setData(Uri.parse(url));
                    context.startActivity(intent);
                    break;
                    
                case "google":
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    url = "https://m.baidu.com/from=844b/s?word=" + EncodeUtils.urlEncode(keyword);
                    intent.setData(Uri.parse(url));
                    context.startActivity(intent);
                    break;
                case "default":

                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    url = "https://m.baidu.com/from=844b/s?word=" + EncodeUtils.urlEncode(keyword);
                    intent.setData(Uri.parse(url));
                    context.startActivity(intent);
                    break;
                default:
                    R4Action r4Action = new R4Action(context, request);
                    r4Action.start();
                    break;

            }
        } else {
            R4Action r4Action = new R4Action(context, request);
            r4Action.start();
        }
    }
}
