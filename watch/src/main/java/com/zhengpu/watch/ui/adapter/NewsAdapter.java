package com.zhengpu.watch.ui.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.orhanobut.logger.Logger;
import com.zhengpu.watch.R;
import com.zhengpu.watch.iflytekbean.NewsBean;
import com.zhengpu.watch.utils.GlideUtils;

import java.util.List;


public class NewsAdapter extends BaseQuickAdapter<NewsBean.DataBean.ResultBean, BaseViewHolder> {


    private  NewsItemLiserten  newsItemLiserten;

    public NewsAdapter(List<NewsBean.DataBean.ResultBean> data) {
        super(R.layout.item_news_item, data);

    }
    public void  setNewsItemLiserten( NewsItemLiserten  newsItemLiserten){
        this.newsItemLiserten = newsItemLiserten;
    }


    @Override
    protected void convert(BaseViewHolder helper, final NewsBean.DataBean.ResultBean item) {

        GlideUtils.loadMovieTopImg((ImageView) helper.getView(R.id.iv_url), item.getImgUrl());
        helper.setText(R.id.tv_news,item.getTitle());
         helper.itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 newsItemLiserten.onNewsItemClick(item.getUrl());
             }
         });
    }
}
