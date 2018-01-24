package com.zhengpu.iflytekaiui.iflytekaction;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.view.accessibility.AccessibilityNodeInfo;

import com.orhanobut.logger.Logger;
import com.zhengpu.iflytekaiui.contentprovider.PlayController;

import java.util.List;

import static android.content.Context.CLIPBOARD_SERVICE;
import static com.zhengpu.iflytekaiui.utils.DeviceUtils.getPlayStart;
import static com.zhengpu.iflytekaiui.utils.DeviceUtils.updatePlayStart;

/**
 * sayid ....
 * Created by wengmf on 2017/11/28.
 */

public class QQ {

    private Context context;
    private String songName;
    private String Findname;
    private  String Searchedit;

    public QQ(Context context) {

        this.context = context;
        this.songName =getPlayStart(context, 7).isPlay;
        this.Findname =  getPlayStart(context, 8).isPlay;
        this.Searchedit =   getPlayStart(context, 9).isPlay;

    }

    public void start(AccessibilityNodeInfo info) {
        if (info != null) {
            if (info.getChildCount() == 0) {
//                if (Findname.equals("true")&& FindNodeInfosById(info, "com.kugou.playerHDyy:id/item_container")) {
                if (Findname.equals("true")&& FindNodeInfosById(info, "com.tencent.qqmusicpad:id/find_name")) {
                    // 模拟点击搜索button
                    AccessibilityNodeInfo parent = info;
                    while (parent != null) {
                        if (parent.isClickable()) {
                            updatePlayStart(context, 8, "false");
                            parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            break;
                        }
                        parent = parent.getParent();
                    }
                } else if (Findname.equals("false") && FindNodeInfosById(info, "com.tencent.qqmusicpad:id/search_edit")) {
                    PlayController playController = getPlayStart(context, 9);
                    if (playController.isPlay.equals("true")) {
                        // 模拟输入歌曲名
                        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("scb", songName);
                        clipboard.setPrimaryClip(clip);
                        info.performAction(AccessibilityNodeInfo.ACTION_FOCUS);
                        info.performAction(AccessibilityNodeInfo.ACTION_PASTE);
                        updatePlayStart(context, 9, "false");
                    }
                }else if(FindNodeInfosById(info, "com.tencent.qqmusicpad:id/search_smart_result_view_container") ){
                    // 模拟点击 搜索歌曲 button
                    PlayController playController = getPlayStart(context, 11);
                    if (playController.isPlay.equals("true")) {
                        AccessibilityNodeInfo parent = info;
                        while (parent != null) {
                            if (parent.isClickable()) {
                                updatePlayStart(context, 11, "false");
                                parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                                break;
                            }
                            parent = parent.getParent();
                        }
                    }
                } else if ( FindNodeInfosById(info, "com.tencent.qqmusicpad:id/song_name")) {
                    PlayController playController = getPlayStart(context, 11);
                    if(playController.isPlay.equals("false")){
                        //  模拟点击播放歌曲 button
                        AccessibilityNodeInfo parent = info;
                        while (parent != null) {
                            if (parent.isClickable()) {
                                updatePlayStart(context,11,"true");
                                updatePlayStart(context,12,"false");
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

    //通过id查找
    public static boolean FindNodeInfosByText(AccessibilityNodeInfo nodeInfo, String resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByViewId(resId);
            if (list != null && !list.isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
