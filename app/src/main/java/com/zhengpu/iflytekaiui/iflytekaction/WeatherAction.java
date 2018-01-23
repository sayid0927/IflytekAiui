package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.Context;

import com.zhengpu.iflytekaiui.iflytekbean.WeatherBean;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;

/**
 * Created by Administrator on 2017/12/27 0027.
 */

public class WeatherAction {


    private String service;
    private  String request;
    private WeatherBean weatherBean;
    private Context context;

    public WeatherAction(String service, WeatherBean weatherBean , String request, Context context) {
        this.service = service;
        this.request = request;
        this.weatherBean = weatherBean;
        this.context = context;
    }

    public void start() {

        if (weatherBean != null && weatherBean.getData() != null &&
                weatherBean.getData().getResult() != null && weatherBean.getData().getResult().size() != 0) {

            StringBuilder stringBuffer = new StringBuilder();
            String humidity = weatherBean.getData().getResult().get(0).getHumidity();  //湿度
            String tempRange = weatherBean.getData().getResult().get(0).getTempRange();   // 温度范围
            String weather = weatherBean.getData().getResult().get(0).getWeather(); //天气情况
            String wind = weatherBean.getData().getResult().get(0).getWind();
            String prompt = weatherBean.getData().getResult().get(0).getExp().getCt().getPrompt();
            String airQuality = weatherBean.getData().getResult().get(0).getAirQuality();
            int temp = weatherBean.getData().getResult().get(0).getTemp();

            stringBuffer.append("室外温度为").append(String.valueOf(temp)).append("摄氏度").
                                   append("温度范围为").append(tempRange).
                                   append("天气情况为").append(weather).
                                   append("空气质量指数为").append(airQuality).
                                   append("温馨提示").append(prompt);

            SpeechRecognizerService.startSpeech(service, stringBuffer.toString(), request);

        }else {
            R4Action r4Action = new R4Action(context,request);
            r4Action.start();

        }
    }
}
