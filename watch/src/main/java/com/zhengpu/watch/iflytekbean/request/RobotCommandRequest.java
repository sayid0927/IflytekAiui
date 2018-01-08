package com.zhengpu.watch.iflytekbean.request;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zhengpu.watch.base.AppContract;

/**
 * sayid ....
 * Created by wengmf on 2018/1/8.
 */

public class RobotCommandRequest implements MultiItemEntity {


    String text;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int getItemType() {
        return AppContract.RobotCommandRequest;
    }
}
