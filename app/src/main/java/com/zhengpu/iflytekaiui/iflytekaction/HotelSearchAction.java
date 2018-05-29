package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.Context;

import com.zhengpu.iflytekaiui.iflytekbean.HotelSearchBean;
import com.zhengpu.iflytekaiui.iflytekbean.StockBean;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;

/**
 * sayid ....
 * Created by wengmf on 2018/5/16.
 */

public class HotelSearchAction {

    private Context context;
    private String service;
    private String strRequest;
    private HotelSearchBean hotelSearchBean;

    public HotelSearchAction(String service, HotelSearchBean hotelSearchBean, String strRequest, Context context) {

        this.hotelSearchBean = hotelSearchBean;
        this.service = service;
        this.context = context;
        this.strRequest = strRequest;

    }

    public void start() {
        StringBuffer stringBuffer = new StringBuffer();
        if (hotelSearchBean != null) {
            if (hotelSearchBean.getData() != null && hotelSearchBean.getData().getResult() != null && hotelSearchBean.getData().getResult().size() != 0) {
                for (int i = 0; i < hotelSearchBean.getData().getResult().size(); i++) {
                    String Hotelname = hotelSearchBean.getData().getResult().get(i).getName();
                    String phone = hotelSearchBean.getData().getResult().get(i).getPhone();
                    String address = hotelSearchBean.getData().getResult().get(i).getAddress();
                    String radius = hotelSearchBean.getData().getResult().get(i).getRadius();
                    if (!Hotelname.equals(""))
                        stringBuffer.append("酒店名称为," + Hotelname);
                    if (!address.equals(""))
                        stringBuffer.append("酒店地址在," + address);
                    if (!phone.equals(""))
                        stringBuffer.append("酒店的电话号码为," + phone);
                    if (!radius.equals(""))
                        stringBuffer.append("距离你现在的位置为," + radius + "米左右.");
                }
                SpeechRecognizerService.startSpeech(service, "为你查找到的酒店有," + stringBuffer.toString(), strRequest);
            } else {
                SpeechRecognizerService.startSpeech(service, hotelSearchBean.getAnswer().getText(), strRequest);
            }
        } else {
            R4Action r4Action = new R4Action(context, strRequest);
            r4Action.start();
        }
    }
}
