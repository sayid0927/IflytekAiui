package com.zhengpu.iflytekaiui;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;

/**
 * Created by Administrator on 2017/12/23 0023.
 */

public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.zhengpu.iflytekaiui",
                "com.zhengpu.iflytekaiui.service.SpeechRecognizerService"));
        // 绑定服务
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

    }


    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            Log.e("TAG", "CCCC");
        }

        public void onServiceDisconnected(ComponentName className) {
            Log.e("TAG", "CVVVV");
        }
    };


}
