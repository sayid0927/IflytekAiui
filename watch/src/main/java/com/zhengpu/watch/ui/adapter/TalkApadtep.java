package com.zhengpu.watch.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.utils.EncodeUtils;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zhengpu.watch.R;
import com.zhengpu.watch.base.AppContract;
import com.zhengpu.watch.iflytekbean.BaikeBean;
import com.zhengpu.watch.iflytekbean.BaseBean;
import com.zhengpu.watch.iflytekbean.CalcBean;
import com.zhengpu.watch.iflytekbean.CustomBaikeBean;
import com.zhengpu.watch.iflytekbean.DatetimeBean;
import com.zhengpu.watch.iflytekbean.NewsBean;
import com.zhengpu.watch.iflytekbean.OpenQABean;
import com.zhengpu.watch.iflytekbean.PoetryBean;
import com.zhengpu.watch.iflytekbean.R4Bean;
import com.zhengpu.watch.iflytekbean.UserChatBean;
import com.zhengpu.watch.iflytekbean.WeatherBean;
import com.zhengpu.watch.iflytekbean.request.RobotCommandRequest;
import com.zhengpu.watch.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2017/12/5.
 */

public class TalkApadtep extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    private Context context;
    private TalkNewsItemLiserten talkNewsItemLiserten;

    public void setTalkNewsItemLiserten(TalkNewsItemLiserten talkNewsItemLiserten) {
        this.talkNewsItemLiserten = talkNewsItemLiserten;
    }


    public TalkApadtep(Context context, List<MultiItemEntity> data) {
        super(data);
        this.context = context;

        addItemType(AppContract.UserChatBean, R.layout.item_user_chat);
        addItemType(AppContract.RobotCommandRequest, R.layout.item_calc_chat);
        addItemType(AppContract.OpenQABean, R.layout.item_calc_chat);
        addItemType(AppContract.WeatherBean, R.layout.item_weather_chat);
        addItemType(AppContract.CalcBean, R.layout.item_calc_chat);
        addItemType(AppContract.PoetryBean, R.layout.item_calc_chat);
        addItemType(AppContract.DatetimeBean, R.layout.item_calc_chat);
        addItemType(AppContract.NewsBean, R.layout.item_news);
        addItemType(AppContract.FlightBean, R.layout.item_filght_chat);
        addItemType(AppContract.BaikeBean, R.layout.item_baike_chat);
        addItemType(AppContract.VideoBean, R.layout.item_calc_chat);

    }


    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {

        switch (helper.getItemViewType()) {

            case AppContract.UserChatBean:     //用户说话内容
                UserChatBean userChatBean = (UserChatBean) item;
                helper.setText(R.id.chatlist_text_me, userChatBean.getText());
                break;

            case AppContract.RobotCommandRequest:  //自定义机器人回复
                RobotCommandRequest robotCommandRequest = (RobotCommandRequest) item;
                helper.setText(R.id.chatlist_text_other, robotCommandRequest.getText());
                break;

            case AppContract.OpenQABean:    //开放问答
                OpenQABean openQABean = (OpenQABean) item;
                helper.setText(R.id.chatlist_text_other, openQABean.getAnswer().getText());
                break;

            case AppContract.PoetryBean:   //诗词问答
                PoetryBean poetryBean = (PoetryBean) item;
                helper.setText(R.id.chatlist_text_other, poetryBean.getAnswer().getText());
                break;

            case AppContract.DatetimeBean:  //时间问答
                DatetimeBean datetimeBean = (DatetimeBean) item;
                helper.setText(R.id.chatlist_text_other, datetimeBean.getAnswer().getText());
                break;

            case AppContract.CalcBean:  //数值问答
                CalcBean calcBean = (CalcBean) item;
                helper.setText(R.id.chatlist_text_other, calcBean.getAnswer().getText());
                break;

            case AppContract.FlightBean:   //订票服务
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      Intent    intent = new Intent(Intent.ACTION_VIEW);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        String  url = "https://m.ctrip.com/html5" ;
                        intent.setData(Uri.parse(url));
                        context.startActivity(intent);
                    }
                });

                break;
            case AppContract.BaikeBean:   //互动百科词条查询

                BaikeBean baikeBean = (BaikeBean) item;
                helper.setText(R.id.chatlist_text_other, baikeBean.getAnswer().getText());

                break;

            case AppContract.CustomBaikeBean:   //自定义百科

                CustomBaikeBean customBaikeBean = (CustomBaikeBean) item;
//            helper.setText(R.id.chatlist_text_other, customBaikeBean.getAnswer().getText());

                break;

            case AppContract.NewsBean:  //新闻问答

                NewsBean newsBean = (NewsBean) item;

                RecyclerView recyclerView = helper.getView(R.id.rv_news);
                NewsAdapter newsAdapter = new NewsAdapter(newsBean.getData().getResult());
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                recyclerView.setAdapter(newsAdapter);
                newsAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
                recyclerView.scrollToPosition(newsAdapter.getItemCount() - 1);

                newsAdapter.setNewsItemLiserten(new NewsItemLiserten() {
                    @Override
                    public void onNewsItemClick(String url) {
                       if(talkNewsItemLiserten!=null)
                           talkNewsItemLiserten.onTalkNewsItemClick(url);
                    }
                });

                break;

            case  AppContract.VideoBean:



                break;

            case AppContract.WeatherBean:  //天气问答
                WeatherBean weatherBean = (WeatherBean) item;
                WeatherBean.DataBean dataBean = weatherBean.getData();

                String City = dataBean.getResult().get(0).getCity();
                String Weather = dataBean.getResult().get(0).getWeather();
                String airQuality = dataBean.getResult().get(0).getAirQuality();
                String tempRange = dataBean.getResult().get(0).getTempRange();
                String prompt = dataBean.getResult().get(0).getExp().getCt().getPrompt();
                int temp = dataBean.getResult().get(0).getTemp();

                helper.setText(R.id.tv_temp, String.valueOf(temp));
                helper.setText(R.id.tv_city, City);
                helper.setText(R.id.tv_weather, Weather);
                helper.setText(R.id.tv_airQuality, airQuality);
                helper.setText(R.id.tv_tempRange, tempRange);
                helper.setText(R.id.tv_prompt, prompt);

                break;

        }
    }
}
