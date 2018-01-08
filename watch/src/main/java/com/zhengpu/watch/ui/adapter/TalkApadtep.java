package com.zhengpu.watch.ui.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zhengpu.watch.R;
import com.zhengpu.watch.base.AppContract;
import com.zhengpu.watch.iflytekbean.BaseBean;
import com.zhengpu.watch.iflytekbean.CalcBean;
import com.zhengpu.watch.iflytekbean.DatetimeBean;
import com.zhengpu.watch.iflytekbean.OpenQABean;
import com.zhengpu.watch.iflytekbean.PoetryBean;
import com.zhengpu.watch.iflytekbean.R4Bean;
import com.zhengpu.watch.iflytekbean.UserChatBean;
import com.zhengpu.watch.iflytekbean.WeatherBean;
import com.zhengpu.watch.iflytekbean.request.RobotCommandRequest;

import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2017/12/5.
 */

public class TalkApadtep extends BaseMultiItemQuickAdapter<MultiItemEntity,  BaseViewHolder> {

    private Context context;

    public TalkApadtep(Context context, List<MultiItemEntity> data) {
        super(data);
        this.context = context;
        addItemType(AppContract.UserChatBean, R.layout.item_user_chat);
        addItemType(AppContract.RobotCommandRequest, R.layout.item_calc_chat);
        addItemType(AppContract.OpenQABean, R.layout.item_calc_chat);
//        addItemType(BaseBean.DATETIME, R.layout.item_calc_chat);
//        addItemType(BaseBean.FLIGHT, R.layout.item_calc_chat);
//        addItemType(BaseBean.JOKE, R.layout.item_joke_chat);
//        addItemType(BaseBean.MUSICX, R.layout.item_calc_chat);
//        addItemType(BaseBean.NEWS, R.layout.item_news_chat);
//        addItemType(BaseBean.OPENAPPTEST_APP, R.layout.item_calc_chat);
//        addItemType(BaseBean.OPENAPPTEST_SHIPING, R.layout.item_calc_chat);
//        addItemType(BaseBean.OPENQA, R.layout.item_calc_chat);
//        addItemType(BaseBean.POETRY, R.layout.item_calc_chat);
//        addItemType(BaseBean.STORY, R.layout.item_calc_chat);
//        addItemType(BaseBean.WEATHER, R.layout.item_weather_chat);
//        addItemType(BaseBean.R4, R.layout.item_calc_chat);
//        addItemType(BaseBean.POINT, R.layout.item_calc_chat);

    }


    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {

        switch (helper.getItemViewType()) {

            case AppContract.UserChatBean:     //用户说话内容
                UserChatBean userChatBean = (UserChatBean) item;
                helper.setText(R.id.chatlist_text_me,userChatBean.getText());
                break;

            case AppContract.RobotCommandRequest:  //自定义机器人回复
                RobotCommandRequest robotCommandRequest = (RobotCommandRequest) item;
                helper.setText(R.id.chatlist_text_other,robotCommandRequest.getText());
                break;

            case AppContract.OpenQABean:    //开放问答
                OpenQABean openQABean = (OpenQABean) item;
                helper.setText(R.id.chatlist_text_other,openQABean.getAnswer().getText());
                break;

            case AppContract.PoetryBean:   //诗词问答
                PoetryBean poetryBean = (PoetryBean) item;
                helper.setText(R.id.chatlist_text_other,poetryBean.getAnswer().getText());
                break;

            case AppContract.DatetimeBean:  //时间问答
                DatetimeBean datetimeBean = (DatetimeBean) item;
                helper.setText(R.id.chatlist_text_other,datetimeBean.getAnswer().getText());
                break;

            case AppContract.CalcBean:  //数值问答
                CalcBean calcBean = (CalcBean) item;
                helper.setText(R.id.chatlist_text_other,calcBean.getAnswer().getText());
                break;

            case AppContract.WeatherBean:  //天气问答
                WeatherBean weatherBean = (WeatherBean) item;
                helper.setText(R.id.chatlist_text_other,weatherBean.getAnswer().getText());
                break;

        }
    }
}
