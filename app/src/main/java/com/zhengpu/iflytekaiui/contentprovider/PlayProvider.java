package com.zhengpu.iflytekaiui.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

/**
 * sayid ....
 * Created by wengmf on 2018/1/2.
 */

public class PlayProvider  extends ContentProvider {

    public static final String AUTHORITY = "com.zhengpu.iflytekaiui.provider"; // 与AndroidManifest保持一致
    public static final Uri PLAY_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/start");

    public static final int PLAY_URI_CODE = 0;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    // 关联Uri和Uri_Code
    static {
        sUriMatcher.addURI(AUTHORITY, "start", PLAY_URI_CODE);

    }

    private Context mContext;
    private SQLiteDatabase mDb;

    @Override
    public boolean onCreate() {
        mContext = getContext();
        initProviderData(); // 初始化Provider数据
        return false;

    }

    private void initProviderData() {
        mDb = new DbOpenHelper(mContext).getWritableDatabase();
        mDb.execSQL("delete from " + DbOpenHelper.PLAY_TABLE_NAME);
        mDb.execSQL("insert into start values(1,'kuguo_play_clickabl','false');");
        mDb.execSQL("insert into start values(2, 'kuguo_play_abj','true');");
        mDb.execSQL("insert into start values(3, 'kuguo_play_b2w','true');");
        mDb.execSQL("insert into start values(4, 'aiqiyi_play_clickabl','false');");
        mDb.execSQL("insert into start values(5, 'aiqiyi_play_searchkeyword','true');");
        mDb.execSQL("insert into start values(6, 'videoName','false');");
        mDb.execSQL("insert into start values(7, 'videoName','false');");
        mDb.execSQL("insert into start values(8, 'qq_find_name','true');");
        mDb.execSQL("insert into start values(9, 'qq_search_edit','true');");
        mDb.execSQL("insert into start values(10, 'qq_search_edit','true');");
        mDb.execSQL("insert into start values(11, 'search_smart_result_view_container','true');");
        mDb.execSQL("insert into start values(12, 'song_album_img','true');");


    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String tableName = getTableName(uri);
        if (TextUtils.isEmpty(tableName)) {
            throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        return mDb.query(tableName, projection, selection, selectionArgs, null, null, sortOrder, null);
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        String table = getTableName(uri);
        if (TextUtils.isEmpty(table)) {
            throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        mDb.insert(table, null, values);

        // 插入数据后通知改变
        mContext.getContentResolver().notifyChange(uri, null);
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        String table = getTableName(uri);
        if (TextUtils.isEmpty(table)) {
            throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        int count = mDb.delete(table, selection, selectionArgs);
        if (count > 0) {
            mContext.getContentResolver().notifyChange(uri, null);
        }

        return count; // 返回删除的函数
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        String table = getTableName(uri);
        if (TextUtils.isEmpty(table)) {
            throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        int row = mDb.update(table, values, selection, selectionArgs);
        if (row > 0) {
            mContext.getContentResolver().notifyChange(uri, null);
        }

        return row; // 返回更新的行数
    }

    private String getTableName(Uri uri) {
        String tableName = null;
        switch (sUriMatcher.match(uri)) {
            case PLAY_URI_CODE:
                tableName = DbOpenHelper.PLAY_TABLE_NAME;
                break;
            default:
                break;
        }
        return tableName;
    }
}
