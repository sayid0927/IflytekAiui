package com.zhengpu.iflytekaiui;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.zhengpu.iflytekaiui.utils.PreferUtil;


/**
 * Created by Administrator on 2017/12/23 0023.
 */

public class MainActivity extends Activity {


    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PreferUtil.getInstance().init(this);
        button = (Button) this.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.zhengpu.iflytekaiui",
                        "com.zhengpu.iflytekaiui.service.SpeechRecognizerService"));
                // 绑定服务
//                bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
                startService(intent);
            }
        });
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
        }

        public void onServiceDisconnected(ComponentName className) {
        }
    };

}
