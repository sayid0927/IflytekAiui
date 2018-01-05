package com.zhengpu.watch.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhengpu.watch.R;
import com.zhengpu.watch.iflytekbean.BaseBean;
import com.zhengpu.watch.iflytekbean.WeatherBean;
import com.zhengpu.watch.utils.GlideUtils;

import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2017/12/5.
 */

public class TalkApadtep extends BaseMultiItemQuickAdapter<BaseBean, BaseViewHolder> {

    private Context context;

    public TalkApadtep(Context context, List data) {
        super(data);
        this.context = context;
        addItemType(BaseBean.USER_CHAT, R.layout.item_user_chat);
        addItemType(BaseBean.BAIKE, R.layout.item_baike_chat);
        addItemType(BaseBean.CALC, R.layout.item_calc_chat);
        addItemType(BaseBean.DATETIME, R.layout.item_calc_chat);
        addItemType(BaseBean.FLIGHT, R.layout.item_calc_chat);
        addItemType(BaseBean.JOKE, R.layout.item_joke_chat);
        addItemType(BaseBean.MUSICX, R.layout.item_calc_chat);
        addItemType(BaseBean.NEWS, R.layout.item_news_chat);
        addItemType(BaseBean.OPENAPPTEST_APP, R.layout.item_calc_chat);
        addItemType(BaseBean.OPENAPPTEST_SHIPING, R.layout.item_calc_chat);
        addItemType(BaseBean.OPENQA, R.layout.item_calc_chat);
        addItemType(BaseBean.POETRY, R.layout.item_calc_chat);
        addItemType(BaseBean.STORY, R.layout.item_calc_chat);
        addItemType(BaseBean.WEATHER, R.layout.item_weather_chat);
        addItemType(BaseBean.R4, R.layout.item_calc_chat);
        addItemType(BaseBean.POINT, R.layout.item_calc_chat);

    }

    @Override
    protected void convert(BaseViewHolder helper, BaseBean item) {

        switch (helper.getItemViewType()) {

            case BaseBean.USER_CHAT:
                helper.setText(R.id.chatlist_text_me, item.getUserChatBean().getText());
                break;
            case BaseBean.POINT:
                helper.setText(R.id.chatlist_text_other, item.getPointBean().getText());
                break;
            case BaseBean.BAIKE:

                String value;
                if (item.getBaikeBean() != null && item.getBaikeBean().getData() != null &&
                        item.getBaikeBean().getData().getResult() != null && item.getBaikeBean().getData().getResult().get(0).getUrl() != null
                        ) {
                    ImageView iv = helper.getView(R.id.img);
                    Glide.with(context).load(item.getBaikeBean().getData().getResult().get(0).getUrl()).into(iv);
                }

                if (item.getBaikeBean() != null && item.getBaikeBean().getSemantic() != null &&
                        item.getBaikeBean().getSemantic().get(0).getSlotsBean() != null)

                    value = item.getBaikeBean().getSemantic().get(0).getSlotsBean().getValue();
                else
                    value = item.getBaikeBean().getText();

                helper.setText(R.id.tv_title, value);
                helper.setText(R.id.tv_context, item.getBaikeBean().getAnswer().getText());

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.baidu.com/s?wd=" + value + "&tn=SE_PSStatistics_p1d9m0nf"));
                context.startActivity(intent);

                break;

            case BaseBean.CALC:
                helper.setText(R.id.chatlist_text_other, item.getCalcBean().getAnswer().getText());
                break;

            case BaseBean.DATETIME:
                helper.setText(R.id.chatlist_text_other, item.getDatetimeBean().getAnswer().getText());
                break;

            case BaseBean.FLIGHT:
                helper.setText(R.id.chatlist_text_other, item.getFlightBean().getAnswer().getText());
                break;

            case BaseBean.JOKE:

//                helper.setText(R.id.tv_title, item.getTianJokeBean().getNewslist().get(0).getTitle());
//                helper.setText(R.id.tv_content, item.getTianJokeBean().getNewslist().get(0).getContent());

                break;
            case BaseBean.MUSICX:
                helper.setText(R.id.chatlist_text_other, item.getMusicXBean().getAnswer().getText());
                break;

            case BaseBean.NEWS:
//                setNews(helper, item);

                break;
            case BaseBean.OPENAPPTEST_APP:
                helper.setText(R.id.chatlist_text_other, item.getOpenAppBean().getText());
                break;
            case BaseBean.OPENAPPTEST_SHIPING:
                helper.setText(R.id.chatlist_text_other, item.getVideoBean().getText());
                break;
            case BaseBean.OPENQA:
                helper.setText(R.id.chatlist_text_other, item.getOpenQABean().getAnswer().getText());
                break;
            case BaseBean.POETRY:
                helper.setText(R.id.chatlist_text_other, item.getPoetryBean().getAnswer().getText());
                break;
            case BaseBean.STORY:
                helper.setText(R.id.chatlist_text_other, item.getStoryBean().getAnswer().getText());
                break;

            case BaseBean.WEATHER:

                if (item.getWeatherBean() != null && item.getWeatherBean().getData() != null &&
                        item.getWeatherBean().getData().getResult() != null && item.getWeatherBean().getData().getResult().size() != 0) {

                    WeatherBean.DataBean dataBean = item.getWeatherBean().getData();
                    String City = dataBean.getResult().get(0).getCity();
                    String Weather = dataBean.getResult().get(0).getWeather();
                    String airQuality = dataBean.getResult().get(0).getAirQuality();
                    String tempRange = dataBean.getResult().get(0).getTempRange();
                    String prompt = dataBean.getResult().get(0).getExp().getCt().getPrompt();

                    helper.setText(R.id.tv_city, City);
                    helper.setText(R.id.tv_weather, Weather);
                    helper.setText(R.id.tv_airQuality, airQuality);
                    helper.setText(R.id.tv_tempRange, tempRange);
                    helper.setText(R.id.tv_prompt, prompt);

                }

                break;

            case BaseBean.R4:
                helper.setText(R.id.chatlist_text_other, "不好意思，我好像没听懂");
                break;

        }
    }

//    private void setNews(BaseViewHolder helper, final BaseBean item) {
//
//        helper.setText(R.id.tv_title, item.getWxItemBean().getNewslist().get(0).getTitle());
//        helper.setText(R.id.tv_title1, item.getWxItemBean().getNewslist().get(1).getTitle());
//        helper.setText(R.id.tv_title2, item.getWxItemBean().getNewslist().get(2).getTitle());
//        helper.setText(R.id.tv_title3, item.getWxItemBean().getNewslist().get(3).getTitle());
//        helper.setText(R.id.tv_title4, item.getWxItemBean().getNewslist().get(4).getTitle());
//
//        GlideUtils.loadMovieTopImg((ImageView) helper.getView(R.id.iv_picUrl), item.getWxItemBean().getNewslist().get(0).getPicUrl());
//        GlideUtils.loadMovieTopImg((ImageView) helper.getView(R.id.iv_picUrl1), item.getWxItemBean().getNewslist().get(1).getPicUrl());
//        GlideUtils.loadMovieTopImg((ImageView) helper.getView(R.id.iv_picUrl2), item.getWxItemBean().getNewslist().get(2).getPicUrl());
//        GlideUtils.loadMovieTopImg((ImageView) helper.getView(R.id.iv_picUrl3), item.getWxItemBean().getNewslist().get(3).getPicUrl());
//        GlideUtils.loadMovieTopImg((ImageView) helper.getView(R.id.iv_picUrl4), item.getWxItemBean().getNewslist().get(4).getPicUrl());
//
//        helper.getView(R.id.rl_news_item).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(item.getWxItemBean().getNewslist().get(0).getUrl())));
//            }
//        });
//
//        helper.getView(R.id.rl_news_item1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(item.getWxItemBean().getNewslist().get(1).getUrl())));
//            }
//        });
//
//        helper.getView(R.id.rl_news_item2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(item.getWxItemBean().getNewslist().get(2).getUrl())));
//            }
//        });
//
//        helper.getView(R.id.rl_news_item3).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(item.getWxItemBean().getNewslist().get(3).getUrl())));
//            }
//        });
//
//        helper.getView(R.id.rl_news_item4).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(item.getWxItemBean().getNewslist().get(4).getUrl())));
//            }
//        });
//    }
}
