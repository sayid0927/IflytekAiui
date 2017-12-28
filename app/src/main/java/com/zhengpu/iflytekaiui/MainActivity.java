package com.zhengpu.iflytekaiui;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.utils.EncodeUtils;
import com.zhengpu.iflytekaiui.iflytekbean.otherbean.IfMusicBean;
import com.zhengpu.iflytekaiui.iflytekbean.otherbean.IfMusicResBean;
import com.zhengpu.iflytekaiui.iflytekutils.JsonParser;


/**
 * Created by Administrator on 2017/12/23 0023.
 */

public class MainActivity extends Activity {

    private Button button;
    private String json;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) this.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                IfMusicBean ifMusicBean = JsonParser.parseResultIfMusicBean(json);
//                String res =   ifMusicBean.getData().getRes();
//                IfMusicResBean ifMusicResBean = JsonParser.parseResultIfMusicResBean(ifMusicBean.getData().getRes());

                String keywords = "安卓酷狗音乐App";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


//               String url ="https://m.baidu.com/from=844b/s?word="+EncodeUtils.urlEncode(keywords)+"&sasub=re_dl_gh_icon20171229&ts=7411662&t_kt=0&ie=utf-8&fm_kl=021394be2f&rsv_iqid=0597588752&rsv_t=8541StZEj%252FbkWLjsANNg2SxJqtmCvhWlFgMuw7tqmBImLS3Ppq%252FPGK%252FtpQ&sa=is_1&ms=1&rsv_pq=0597588752&rsv_sug4=10699&ss=101&inputT=7942&rq=酷&tj=1";


                String url ="http://www.baidu.com/s?wd="+ EncodeUtils.urlEncode(keywords);

                intent.setData(Uri.parse(url));
                startActivity(intent);

//                Intent intent = new Intent();
//                intent.setComponent(new ComponentName("com.zhengpu.iflytekaiui",
//                        "com.zhengpu.iflytekaiui.service.SpeechRecognizerService"));
//                // 绑定服务
//                bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
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
