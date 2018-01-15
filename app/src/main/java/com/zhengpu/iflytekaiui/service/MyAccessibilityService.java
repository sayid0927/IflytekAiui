package com.zhengpu.iflytekaiui.service;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.orhanobut.logger.Logger;
import com.zhengpu.iflytekaiui.contentprovider.PlayController;
import com.zhengpu.iflytekaiui.iflytekaction.KuGou;
import com.zhengpu.iflytekaiui.iflytekaction.Qiyi;

import static com.zhengpu.iflytekaiui.utils.DeviceUtils.getPlayStart;


/**
 * sayid ....
 * Created by wengmf on 2017/11/29.
 */

public class MyAccessibilityService extends AccessibilityService {

    private PlayController playController;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        //接收事件,如触发了通知栏变化、界面变化等
        String nowPackageName = event.getPackageName().toString();
        AccessibilityNodeInfo rootNode = this.getRootInActiveWindow();
        Logger.e("nowPackageName >>   "+nowPackageName);
        switch (nowPackageName) {
            case "com.kugou.android":
                playController = getPlayStart(MyAccessibilityService.this, 1);
                if (playController.isPlay.equals("true")) {
                    KuGou kuGou = new KuGou(this);
                    kuGou.start(rootNode);
                }
                break;

            case "com.qiyi.video":
                playController = getPlayStart(MyAccessibilityService.this, 4);
                if (playController.isPlay.equals("true")) {
                    Qiyi qiyi = new Qiyi(this);
                    qiyi.start(rootNode);
                }
                break;
        }
    }


    @Override
    public void onInterrupt() {
        //服务中断，如授权关闭或者将服务杀死
        Logger.e("授权中断");
    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Logger.e("AccessibilityService >>>>  授权成功");
    }
}
