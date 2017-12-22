package com.zhengpu.iflytekaiui.service;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

/**
 * sayid ....
 * Created by wengmf on 2017/11/29.
 */

public class MyAccessibilityService extends AccessibilityService  {

    private static String TAG = "MyAccessibilityService类 ";


    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        //接收事件,如触发了通知栏变化、界面变化等
        String nowPackageName = event.getPackageName().toString();
        Log.e(TAG, nowPackageName);
        AccessibilityNodeInfo rootNode = this.getRootInActiveWindow();
//        switch (nowPackageName) {
//
//            case "com.kugou.android":
//                if (AppController.KuGuoplayClickabl) {
//                    KuGou kuGou = new KuGou(this, this);
//                    kuGou.start(rootNode);
//                }
//                break;
//
//            case "com.qiyi.video":
//
//                if(AppController.AiQiPlayClickabl) {
//                    Qiyi youKu = new Qiyi(this, this);
//                    youKu.start(rootNode);
//                }
//                break;
//
//
//        }
    }

    @Override
    public void onInterrupt() {
        //服务中断，如授权关闭或者将服务杀死
        Log.i(TAG, "授权中断");
    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Log.e(TAG, "AccessibilityService >>>>  授权成功");

    }

}
