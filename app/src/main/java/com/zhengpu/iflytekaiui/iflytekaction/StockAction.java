package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.Context;

import com.zhengpu.iflytekaiui.iflytekbean.RadioBean;
import com.zhengpu.iflytekaiui.iflytekbean.StockBean;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;
import com.zhengpu.iflytekaiui.utils.PreferUtil;

/**
 * sayid ....
 * Created by wengmf on 2018/5/10.
 */

public class StockAction {

    private Context context;
    private String service;
    private String strRequest;
    private StockBean stockBean;

    public StockAction(String service, StockBean stockBean, String strRequest, Context context) {

        this.stockBean = stockBean;
        this.service = service;
        this.context = context;
        this.strRequest = strRequest;

    }

    public void start() {
        if(stockBean!=null && stockBean.getAnswer().getText()!=null){
            SpeechRecognizerService.startSpeech(service,stockBean.getAnswer().getText(),strRequest);
        }else {
            R4Action r4Action = new R4Action(context, strRequest);
            r4Action.start();
        }
    }
}
