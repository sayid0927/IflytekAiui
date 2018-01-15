package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.view.accessibility.AccessibilityNodeInfo;


import com.zhengpu.iflytekaiui.contentprovider.PlayController;
import com.zhengpu.iflytekaiui.utils.PreferUtil;

import java.util.List;

import static android.content.Context.CLIPBOARD_SERVICE;
import static com.zhengpu.iflytekaiui.utils.DeviceUtils.getPlayStart;
import static com.zhengpu.iflytekaiui.utils.DeviceUtils.updatePlayStart;

/**
 * sayid ....
 * Created by wengmf on 2017/11/28.
 */

public class KuGou {

    private Context context;
    private String songName;

    public KuGou(Context context) {
        this.context = context;

        this.songName =getPlayStart(context, 7).isPlay;
    }

    public void start(AccessibilityNodeInfo info) {
        if (info != null) {
            if (info.getChildCount() == 0) {
                if (FindNodeInfosById(info, "com.kugou.android:id/y5")) {
                    // 模拟点击搜索button
                    AccessibilityNodeInfo parent = info;
                    while (parent != null) {
                        if (parent.isClickable()) {
                            parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            break;
                        }
                        parent = parent.getParent();
                    }

                } else if (FindNodeInfosById(info, "com.kugou.android:id/abj")) {
                    PlayController playController = getPlayStart(context, 2);
                    if (playController.isPlay.equals("true")) {
                        // 模拟输入歌曲名
                        updatePlayStart(context,2,"false");
                        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
                        ClipData clipData = ClipData.newPlainText("scb", songName);
                        clipboardManager.setPrimaryClip(clipData);
                        info.performAction(AccessibilityNodeInfo.ACTION_PASTE);

                    }
                } else if (FindNodeInfosById(info, "com.kugou.android:id/chy")) {
                    // 模拟点击 搜索歌曲 button

                    AccessibilityNodeInfo parent = info;
                    while (parent != null) {
                        if (parent.isClickable()) {
                            parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            break;
                        }
                        parent = parent.getParent();
                    }
                } else if (FindNodeInfosById(info, "com.kugou.android:id/b2w")) {
                    PlayController playController = getPlayStart(context, 3);
                    if(playController.isPlay.equals("true")){
                        //  模拟点击播放歌曲button
                        updatePlayStart(context,1,"false");
                        updatePlayStart(context,2,"true");
                        updatePlayStart(context,3,"false");

                        AccessibilityNodeInfo parent = info;
                        while (parent != null) {
                            if (parent.isClickable()) {
                                parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                                break;
                            }
                            parent = parent.getParent();
                        }
                    }
                }
            } else {
                for (int i = 0; i < info.getChildCount(); i++) {
                    if (info.getChild(i) != null) {
                        start(info.getChild(i));
                    }
                }
            }
        }
    }

    //通过id查找
    public static boolean FindNodeInfosById(AccessibilityNodeInfo nodeInfo, String resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByViewId(resId);
            if (list != null && !list.isEmpty()) {
                return true;
            }
        }
        return false;
    }

}
