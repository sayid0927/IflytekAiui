package com.zhengpu.iflytekaiui.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.zhengpu.iflytekaiui.MainActivity;

import java.util.Objects;

/**
 * sayid ....
 * Created by wengmf on 2018/5/25.
 */

public class BootReceiver extends BroadcastReceiver {
    static final String action_boot ="android.intent.action.BOOT_COMPLETED";

    @Override
    public void onReceive (Context context, Intent intent) {

        Log.i("charge start", "启动完成");

        if (Objects.equals(intent.getAction(), action_boot)){

//            Intent mBootIntent = new Intent(context, MainActivity.class);
//            // 下面这句话必须加上才能开机自动运行app的界面
//            mBootIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(mBootIntent);

            Intent mBootIntent = new Intent();
            ComponentName componentName = new ComponentName("com.zhengpu.iflytekaiui",
                    "com.zhengpu.iflytekaiui.service.SpeechRecognizerService");
            mBootIntent.setComponent(componentName);
            context.startActivity(mBootIntent);

        }
    }
}
