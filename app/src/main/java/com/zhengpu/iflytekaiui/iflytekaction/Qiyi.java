package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.view.accessibility.AccessibilityNodeInfo;


import com.orhanobut.logger.Logger;
import com.zhengpu.iflytekaiui.contentprovider.PlayController;
import com.zhengpu.iflytekaiui.utils.PreferUtil;

import java.util.List;

import static android.content.Context.CLIPBOARD_SERVICE;
import static com.zhengpu.iflytekaiui.utils.DeviceUtils.getPlayStart;
import static com.zhengpu.iflytekaiui.utils.DeviceUtils.updatePlayStart;

/**
 * sayid ....
 * Created by wengmf on 2017/11/29.
 */

public class Qiyi {

    private Context context;
    private String videoName;

    public Qiyi(Context context) {
        this.context = context;
        this.videoName= getPlayStart(context, 6).isPlay;

    }

    public void start(AccessibilityNodeInfo info) {
        if (info != null) {
            if (info.getChildCount() == 0) {
                if (FindNodeInfosById(info, "com.qiyi.video.pad:id/main_search_word")) {
                     // 模拟点击搜索button
                    AccessibilityNodeInfo parent = info;
                    while (parent != null) {
                        if (parent.isClickable()) {
                            parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            break;
                        }
                        parent = parent.getParent();
                    }
                } else if (FindNodeInfosById(info, "com.qiyi.video.pad:id/phoneSearchKeyword")) {
                    PlayController playController = getPlayStart(context, 5);

                    if (playController.isPlay.equals("true")) {
                        // 模拟输入歌曲名
                        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
                        ClipData clipData = ClipData.newPlainText("scb", videoName);
                        clipboardManager.setPrimaryClip(clipData);
                        info.performAction(AccessibilityNodeInfo.ACTION_PASTE);
                        updatePlayStart(context, 5, "false");
                    }
//                } else if (info.getText() != null  && "搜索".equals(info.getText().toString()) && FindNodeInfosById(info, "com.qiyi.video:id/txt_action")) {
                }else if(FindNodeInfosById(info,"com.qiyi.video.pad:id/phoneAdapterSearchText")){
                    // 模拟点击 搜索歌曲 button
                    updatePlayStart(context,4,"false");
                    AccessibilityNodeInfo parent = info;
                    while (parent != null) {
                        if (parent.isClickable()) {
                            parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            break;
                        }
                        parent = parent.getParent();
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
