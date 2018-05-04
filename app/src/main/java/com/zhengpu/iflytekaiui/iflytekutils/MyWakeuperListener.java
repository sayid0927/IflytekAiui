//package com.zhengpu.iflytekaiui.iflytekutils;
//
//import android.content.Context;
//import android.os.Bundle;
//
//import com.iflytek.cloud.SpeechError;
//import com.iflytek.cloud.WakeuperListener;
//import com.iflytek.cloud.WakeuperResult;
//import com.orhanobut.logger.Logger;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
///**
// * sayid ....
// * Created by wengmf on 2017/11/22.
// */
//
//public class MyWakeuperListener implements WakeuperListener {
//
//
//    private Context context;
//    private WakeUpListener wakeUpListener;
//
//    public MyWakeuperListener(Context context, WakeUpListener wakeUpListener) {
//        this.context = context;
//        this.wakeUpListener = wakeUpListener;
//    }
//
//
//    //开始说话
//    @Override
//    public void onBeginOfSpeech() {
////        Logger.e("讯飞唤醒开始说话");
//
//    }
//
//    //错误码返回
//    @Override
//    public void onError(SpeechError arg0) {
//        Logger.e("讯飞唤醒错误码返回:" + arg0.toString());
//        wakeUpListener.OnWakeUpError();
//    }
//
//    @Override
//    public void onEvent(int arg0, int arg1, int arg2, Bundle arg3) {
//    }
//
//    @Override
//    public void onVolumeChanged(int i) {
//    }
//
//    @Override
//    public void onResult(WakeuperResult result) {
//
//        if (!"1".equalsIgnoreCase("1")) {
//            //setRadioEnable(true);
//        }
//        try {
//            String text = result.getResultString();
//            JSONObject object;
//            object = new JSONObject(text);
//            StringBuffer buffer = new StringBuffer();
//            buffer.append("【RAW】 " + text);
//            buffer.append("\n");
//            buffer.append("【操作类型】" + object.optString("sst"));
//            buffer.append("\n");
//            buffer.append("【唤醒词id】" + object.optString("id"));
//            buffer.append("\n");
//            buffer.append("【得分】" + object.optString("score"));
//            buffer.append("\n");
//            buffer.append("【前端点】" + object.optString("bos"));
//            buffer.append("\n");
//            buffer.append("【尾端点】" + object.optString("eos"));
//            String resultString = buffer.toString();
//            Logger.e("resultString:" + resultString);
//            //讯飞唤醒！！！！！！！！！！！！！！！！！！！！
//            wakeUpListener.OnWakeUpSuccess();
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
//}