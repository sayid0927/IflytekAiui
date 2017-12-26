package com.zhengpu.app2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zhengpu.iflytekaiui.ipc.entity.RequestMessage;
import com.zhengpu.iflytekaiui.ipc.entity.SendMessage;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import xiaofei.library.hermeseventbus.HermesEventBus;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Intent intent;
    private Button start, stop , sendMessage;
    private TextView textView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = (Button) this.findViewById(R.id.start);
        stop = (Button) this.findViewById(R.id.stop);
        sendMessage = (Button)this.findViewById(R.id.butn_sendMessage);
        textView= (TextView)this.findViewById(R.id.text);
        editText = (EditText )this.findViewById(R.id.edit_sendMessage);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        sendMessage.setOnClickListener(this);
        HermesEventBus.getDefault().register(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMainAppEvent(SendMessage message) {
        textView.setText( "service ===  " + message.getService() +"\n"+"message ===  "+message.getMessage() );
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:

                Bundle args = new Bundle();
                Intent intent = new Intent("com.zhengpu.iflytekaiui.service.SpeechRecognizerService");
                intent.putExtras(args);
//                intent.setPackage(this.getPackageName());
                bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.stop:
//                stopService(intent);

                break;
            case  R.id.butn_sendMessage:
               String message =  editText.getText().toString().trim();
                RequestMessage requestMessage = new RequestMessage();
                requestMessage.setMessage(message);
                requestMessage.setService("AAAAA");
                HermesEventBus.getDefault().post(requestMessage);
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
