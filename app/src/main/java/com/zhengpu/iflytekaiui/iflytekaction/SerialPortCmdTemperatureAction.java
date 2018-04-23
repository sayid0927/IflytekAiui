package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.Context;

import com.blankj.utilcode.utils.ConstUtils;
import com.blankj.utilcode.utils.TimeUtils;
import com.zhengpu.iflytekaiui.R;
import com.zhengpu.iflytekaiui.base.AppController;
import com.zhengpu.iflytekaiui.base.BaseApplication;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;
import com.zhengpu.iflytekaiui.utils.PreferUtil;
import com.zhengpu.iflytekaiui.utils.ValueUtil;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/12/27 0027.
 */

public class SerialPortCmdTemperatureAction {

    private Context context;
    private byte[] bytes;
    private String[] strData;
    private long TouchHeadTime;
    private int TouchHeadCount;
    private  int count=0;

    private ScheduledFuture scheduledFuture;

    public SerialPortCmdTemperatureAction(Context context, byte[] bytes, String[] strData) {
        this.context = context;
        this.bytes = bytes;
        this.strData = strData;
    }

    public void start() {
        // 温度,最高位=1表示负
//         bytes[10];
       // 湿度
//        bytes[11];
    }
}
