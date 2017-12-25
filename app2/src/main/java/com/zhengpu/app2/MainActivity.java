package com.zhengpu.app2;

import android.app.Service;
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

import com.unicool.aidlcallback.server.ITaskBinder;
import com.unicool.aidlcallback.server.ITaskCallback;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Intent intent;
    private Button start, stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Intent intent = new Intent();
//        intent.setComponent(new ComponentName("com.zhengpu.iflytekaiui", "com.zhengpu.iflytekaiui.SpeechRecognizerService"));//设置一个组件名称  同组件名来启动所需要启动Service
        start = (Button) this.findViewById(R.id.start);
        stop = (Button) this.findViewById(R.id.stop);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:

//                Bundle args = new Bundle();
//                Intent intent = new Intent();
//                intent.setAction("com.zhengpu.iflytekaiui.service.SpeechRecognizerService");
//                intent.setPackage(this.getPackageName());
//                intent.putExtras(args);
//                bindService(intent,mConnection,Service.BIND_AUTO_CREATE);

                Bundle args = new Bundle();
                Intent intent = new Intent("com.zhengpu.iflytekaiui.service.SpeechRecognizerService");
                intent.putExtras(args);
                bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

//                Intent mIntent = new Intent();
//                mIntent.setAction("com.zhengpu.iflytekaiui.service.SpeechRecognizerService");//你定义的service的action
//                mIntent.setPackage(getPackageName());//这里你需要设置你应用的包名
//                mIntent.putExtras(args);
//                bindService(mIntent, mConnection, Context.BIND_AUTO_CREATE);

                break;
            case R.id.stop:
//                stopService(intent);

                break;
        }
    }

    private ITaskBinder mService;
    private ServiceConnection mConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName className, IBinder service) {
            Log.e("TAG","FFFFF");

//            mService = ITaskBinder.Stub.asInterface(service);
//            try {
//                mService.registerCallback(mCallback);
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
        }

        public void onServiceDisconnected(ComponentName className) {
//            mService = null;
        }
    };



    private ITaskCallback mCallback = new ITaskCallback.Stub() {

        public void actionPerformed(int id) {
            Log.e("TAG","client：callback by server id=" + id);
        }
    };






}
