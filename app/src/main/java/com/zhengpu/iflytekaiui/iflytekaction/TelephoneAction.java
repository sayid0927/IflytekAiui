package com.zhengpu.iflytekaiui.iflytekaction;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;

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

                            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);

                        } else {


                        }
                    }
                }
            }
            SpeechRecognizerService.Destroy();

//            //通过名字查找号码
//            number = getNumberByName(mPerson, context);
//            if (number == null) {
//                SpeechRecognizerService.startSpeech(service, "没有在通讯录中找到" + mPerson + "的号码。", request);
//            } else {
//                //e(TAG, "startCall" + "拨打:code:" + number);
////                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
////                if (ActivityCompat.checkSelfPermission( context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
////                    //
////                    //    ActivityCompat#requestPermissions
////                    // here to request the missing permissions, and then overriding
////                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
////                    //                                          int[] grantResults)
////                    // to handle the case where the user grants the permission. See the documentation
////                    // for ActivityCompat#requestPermissions for more details.
////                    return;
////                }
////                context.startActivity(intent);
//
//
//
//
//
//                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE)
//                        != PackageManager.PERMISSION_GRANTED) {
//                    // 应用没有授予拨打电话权限，请求权限
////                    requestCameraPermission();
//                } else {
//                    // 应用被授予拨打电话权限 PackageManager.PERMISSION_GRANTED
////                    makeCall();
//                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
//                    context.startActivity(intent);
//
//                }
//
//
//
//
//


//            }
        } else {
            R4Action r4Action = new R4Action(context, request);
            r4Action.start();
        }
    }

    /**
     * 通过名字在通讯录中查找对应的电话号码
     *
     */

//    public static String getNumberByName(String name, Context context) {
//        Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_FILTER_URI, name);
//        ContentResolver resolver = context.getContentResolver();
//
//        Cursor cursor = resolver.query(uri, new String[]{ContactsContract.Contacts._ID}, null, null, null);
//
//        if ((cursor != null) && (cursor.moveToFirst())) {
//            int idCoulmn = cursor.getColumnIndex(ContactsContract.Contacts._ID);
//            long id = cursor.getLong(idCoulmn);
//            cursor.close();
//            cursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, new String[]{"data1"}, "contact_id = ?", new String[]{Long.toString(id)}, null);
//            if ((cursor != null) && (cursor.moveToFirst())) {
//                //获取电话号码
//                int m = cursor.getColumnIndex("data1");
//                String num = cursor.getString(m);
//                cursor.close();
//                return num;
//            }
//        }
//        return null;
//    }
}
