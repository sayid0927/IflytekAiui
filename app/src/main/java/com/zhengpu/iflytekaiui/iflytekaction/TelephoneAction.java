package com.zhengpu.iflytekaiui.iflytekaction;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;

import com.orhanobut.logger.Logger;
import com.zhengpu.iflytekaiui.base.AppController;
import com.zhengpu.iflytekaiui.iflytekbean.StoryBean;
import com.zhengpu.iflytekaiui.iflytekbean.TelephoneBean;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;
import com.zhengpu.iflytekaiui.utils.PreferUtil;

/**
 * sayid ....
 * Created by wengmf on 2018/1/29.
 */

public class TelephoneAction {

    private Context context;
    private String service;
    private TelephoneBean telephoneBean;
    private String request;
    private String mPerson;
    private String number;
    private  int teleType=-1;

    public TelephoneAction(String service, TelephoneBean telephoneBean, String request, Context context) {

        this.context = context;
        this.service = service;
        this.telephoneBean = telephoneBean;
        this.request = request;

    }

    public void start() {
        if (telephoneBean != null) {
            if (telephoneBean.getText().equals("打电话")) {
                try {
                    Intent gotoDialer = new Intent();
                    gotoDialer.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    gotoDialer.setComponent(new ComponentName("com.android.dialer", "com.android.dialer.DialtactsActivity"));
                    context.startActivity(gotoDialer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                for (int i = 0; i < telephoneBean.getSemantic().get(0).getSlots().size(); i++) {
                        String name = telephoneBean.getSemantic().get(0).getSlots().get(i).getName();
                        if (name.equals("name")) {

                            mPerson = telephoneBean.getSemantic().get(0).getSlots().get(i).getValue();
                            Cursor cursor = context.getContentResolver().
                                    query(Uri.parse("content://com.zeunpro.dialer.provider.ContactProvider/Contact"),
                                            null, "name = ?", new String[]{mPerson}, null);

                            int number = 0;
                            if (cursor != null) {
                                while (cursor.moveToNext()) {
                                    number = cursor.getInt(6);
                                }
                                SpeechRecognizerService.Destroy();
                                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                                teleType = 0;
                                break;
                            } else {
                                String userId = checkDatabase(mPerson);
                                if(!userId.equals("")) {
                                    SpeechRecognizerService.Destroy();
                                    Intent intent = new Intent(Intent.ACTION_MAIN);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    ComponentName componentName = new ComponentName("com.zeunpro.im", "com.zeunpro.im.SplashActivity");
                                    intent.setComponent(componentName);
                                    intent.putExtra("EXTRA_CALLING_ID", userId);
                                    context.startActivity(intent);
                                    teleType = 1;

                                    break;
                                }else
                                    break;
                            }
                    }
                }
                if(teleType ==-1){
                    String like = "未找到名称为"+ mPerson+"的联系人";
                    SpeechRecognizerService.startSpeech(AppController.OPENAPPTEST_ROBOTCOMMAND,like,like);
                }
            }
        } else {
            R4Action r4Action = new R4Action(context, request);
            r4Action.start();
        }
    }

    private String checkDatabase(String name) {
        String userId = "";
        try {
            String path = Environment.getExternalStorageDirectory() + "/ZPUSER/video/video_db";
            SQLiteDatabase db = SQLiteDatabase.openDatabase(path,
                    null, SQLiteDatabase.OPEN_READWRITE);
//            String sql = "Select userid from contact_db where username like '%laozhang%'  or friendNickName like '%laozhang% ' limit 1";
            String sql = "Select userid from contact_db where username like " + "'%" + name + "%'" + "  or friendNickName like " + "'%" + name + "%'" + " limit 1";
            Cursor cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                userId = cursor.getString(0);
                Logger.e(number);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return  "";
        }
        return userId;
    }
}



