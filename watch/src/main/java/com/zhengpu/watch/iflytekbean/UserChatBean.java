package com.zhengpu.watch.iflytekbean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zhengpu.watch.base.AppContract;

/**
 * sayid ....
 * Created by wengmf on 2017/12/5.
 */

public class UserChatBean   implements MultiItemEntity {

    String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int getItemType() {
        return AppContract.UserChatBean;
    }
}
