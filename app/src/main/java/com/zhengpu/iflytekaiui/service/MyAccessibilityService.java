package com.zhengpu.iflytekaiui.service;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.orhanobut.logger.Logger;
import com.zhengpu.iflytekaiui.base.AppController;
import com.zhengpu.iflytekaiui.iflytekaction.KuGou;
import com.zhengpu.iflytekaiui.iflytekaction.Qiyi;
import com.zhengpu.iflytekaiui.ipc.entity.SendMessage;
import com.zhengpu.iflytekaiui.utils.PreferUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import xiaofei.library.hermeseventbus.HermesEventBus;

import static com.zhengpu.iflytekaiui.utils.DeviceUtils.getKUGuoClick;

/**
 * sayid ....
 * Created by wengmf on 2017/11/29.
 */

public class MyAccessibilityService extends AccessibilityService {


    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        //接收事件,如触发了通知栏变化、界面变化等
        String nowPackageName = event.getPackageName().toString();
        AccessibilityNodeInfo rootNode = this.getRootInActiveWindow();

      String gg = getKUGuoClick(MyAccessibilityService.this);
      Logger.e(gg);

        switch (nowPackageName) {
            case "com.kugou.android":

                if (PreferUtil.getInstance().getKuGuoplayClickabl()) {
                    KuGou kuGou = new KuGou(this);
                    kuGou.start(rootNode);
                }
                break;

            case "com.qiyi.video":

                if (PreferUtil.getInstance().getAiQiPlayClickabl()) {
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
