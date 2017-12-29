package com.zhengpu.app2;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zhengpu.iflytekaiui.ipc.entity.RequestMessage;
import com.zhengpu.iflytekaiui.ipc.entity.SendMessage;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Date;
import java.util.List;

import xiaofei.library.hermeseventbus.HermesEventBus;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Intent intent;
    private Button start, sendMessage;
    private TextView textView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        long curTime = (new Date()).getTime();

        start = (Button) this.findViewById(R.id.start);
        sendMessage = (Button) this.findViewById(R.id.butn_sendMessage);
        textView = (TextView) this.findViewById(R.id.text);
        editText = (EditText) this.findViewById(R.id.edit_sendMessage);
        start.setOnClickListener(this);
        sendMessage.setOnClickListener(this);
        HermesEventBus.getDefault().register(this);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMainAppEvent(SendMessage message) {
        textView.setText(textView.getText() + "\n" + "service ===  " + message.getService() + "\n" + "message ===  " + message.getMessage());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:

                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.zhengpu.iflytekaiui",
                        "com.zhengpu.iflytekaiui.service.SpeechRecognizerService"));
                // 绑定服务
                bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

                break;

            case R.id.butn_sendMessage:




//                String message = editText.getText().toString().trim();
//                RequestMessage requestMessage = new RequestMessage();
//                requestMessage.setMessage(message);
//                requestMessage.setService("AAAAA");
//                HermesEventBus.getDefault().post(requestMessage);
                break;
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {

        }

        public void onServiceDisconnected(ComponentName className) {

        }
    };

}
