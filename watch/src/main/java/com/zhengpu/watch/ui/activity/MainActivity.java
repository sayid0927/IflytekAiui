package com.zhengpu.watch.ui.activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.orhanobut.logger.Logger;
import com.skyfishjy.library.RippleBackground;
import com.zhengpu.iflytekaiui.ipc.entity.RequestMessage;
import com.zhengpu.iflytekaiui.ipc.entity.SendMessage;
import com.zhengpu.watch.R;
import com.zhengpu.watch.base.BaseActivity;
import com.zhengpu.watch.component.AppComponent;
import com.zhengpu.watch.component.DaggerMainComponent;
import com.zhengpu.watch.iflytekbean.BaikeBean;
import com.zhengpu.watch.iflytekbean.BaseBean;
import com.zhengpu.watch.iflytekbean.CalcBean;
import com.zhengpu.watch.iflytekbean.CustomBaikeBean;
import com.zhengpu.watch.iflytekbean.DatetimeBean;
import com.zhengpu.watch.iflytekbean.FlightBean;
import com.zhengpu.watch.iflytekbean.JokeBean;
import com.zhengpu.watch.iflytekbean.NewsBean;
import com.zhengpu.watch.iflytekbean.OpenQABean;
import com.zhengpu.watch.iflytekbean.PoetryBean;
import com.zhengpu.watch.iflytekbean.R4Bean;
import com.zhengpu.watch.iflytekbean.StoryBean;
import com.zhengpu.watch.iflytekbean.UserChatBean;
import com.zhengpu.watch.iflytekbean.VideoBean;
import com.zhengpu.watch.iflytekbean.WeatherBean;
import com.zhengpu.watch.iflytekbean.request.RobotCommandRequest;
import com.zhengpu.watch.presenter.contract.MainContract;
import com.zhengpu.watch.presenter.impl.MainActivityPresenter;
import com.zhengpu.watch.ui.adapter.HelpFragmentAdapter;
import com.zhengpu.watch.ui.adapter.TalkApadtep;
import com.zhengpu.watch.ui.adapter.TalkNewsItemLiserten;
import com.zhengpu.watch.ui.fragment.FragmentHelp_1;
import com.zhengpu.watch.ui.fragment.FragmentHelp_Home_2;
import com.zhengpu.watch.ui.view.HelpViewPager;
import com.zhengpu.watch.utils.JsonParser;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import xiaofei.library.hermeseventbus.HermesEventBus;


public class MainActivity extends BaseActivity implements MainContract.View, TalkNewsItemLiserten {

    @Inject
    MainActivityPresenter mPresenter;

    @BindView(R.id.llExit)
    LinearLayout llExit;

    @BindView(R.id.video_n)
    ImageView videoN;

    @BindView(R.id.ripple_voice)
    RippleBackground RippleVoice;

    @BindView(R.id.ripple_voice_n)
    RippleBackground RippleVoice_N;

    @BindView(R.id.voice)
    ImageView voice;
    @BindView(R.id.rv_speech)
    RecyclerView rvSpeech;

    @BindView(R.id.ll_centet)
    LinearLayout llCentet;
    @BindView(R.id.iv_phone)
    ImageView ivPhone;
    @BindView(R.id.iv_help)
    ImageView ivHelp;
    @BindView(R.id.viewpager)
    HelpViewPager viewpager;


    public static MainActivity mainActivity;

    private TalkApadtep mAdapter;
    private BaseBean data;
    private boolean isFist = true;
    private boolean isClickHelp = true;
    private UserChatBean userChatBean;

    private List<Fragment> fragmentList;
    private HelpFragmentAdapter helpFragmentAdapter;

    private List<MultiItemEntity> mData;
    private R4Bean r4Bean;
    private RobotCommandRequest robotCommandRequest;

    private String Message = "{\n" +
            "  \"data\": {\n" +
            "    \"result\": [\n" +
            "      {\n" +
            "        \"actor\": [\n" +
            "          {\n" +
            "            \"name\": \"邓超\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王祖蓝\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王宝强\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"陈赫\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"郑恺\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"杨颖\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"李晨\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王心凌\",\n" +
            "            \"roleName\": \"\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"aliasName\": [\n" +
            "          \"跑男\",\n" +
            "          \"奔跑吧!兄弟\",\n" +
            "          \"跑起来!好兄弟\",\n" +
            "          \"Running Man\"\n" +
            "        ],\n" +
            "        \"area\": [\n" +
            "          \"中国大陆\"\n" +
            "        ],\n" +
            "        \"award\": [\n" +
            "          {\n" +
            "            \"ceremony\": \"第1届豆瓣电影年度榜单\",\n" +
            "            \"title\": \"最受关注的大陆综艺(提名)\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"category\": \"综艺\",\n" +
            "        \"company\": \"\",\n" +
            "        \"director\": [\n" +
            "          \"岑俊义\",\n" +
            "          \"陆浩\"\n" +
            "        ],\n" +
            "        \"episode\": [],\n" +
            "        \"hot\": \"23794\",\n" +
            "        \"img\": \"http://kchfpre.openspeech.cn/music_db_file/parastor/data/downdata/pictures/douban_video/25899362/0f3c259311fb102be3dec50e84302118.jpg\",\n" +
            "        \"language\": \"国语\",\n" +
            "        \"leadActor\": [\n" +
            "          {\n" +
            "            \"name\": \"邓超\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王祖蓝\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王宝强\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"陈赫\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"郑恺\",\n" +
            "            \"roleName\": \"\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"name\": \"奔跑吧兄弟 第一季\",\n" +
            "        \"publishTime\": \"2014-10-10\",\n" +
            "        \"score\": \"8.3\",\n" +
            "        \"screenWriter\": [\n" +
            "          \"俞杭英\"\n" +
            "        ],\n" +
            "        \"season\": \"第一季\",\n" +
            "        \"source\": \"iflytek\",\n" +
            "        \"tags\": [\n" +
            "          \"电视剧\",\n" +
            "          \"真人秀\"\n" +
            "        ]\n" +
            "      },\n" +
            "      {\n" +
            "        \"actor\": [\n" +
            "          {\n" +
            "            \"name\": \"邓超\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"鹿晗\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"杨颖\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"郑恺\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王祖蓝\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"李晨\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"陈赫\",\n" +
            "            \"roleName\": \"\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"aliasName\": [\n" +
            "          \"跑男3\",\n" +
            "          \"Running Man season 3\"\n" +
            "        ],\n" +
            "        \"area\": [\n" +
            "          \"中国大陆\"\n" +
            "        ],\n" +
            "        \"award\": [],\n" +
            "        \"category\": \"综艺\",\n" +
            "        \"company\": \"\",\n" +
            "        \"director\": [\n" +
            "          \"岑俊义\",\n" +
            "          \"陆皓\"\n" +
            "        ],\n" +
            "        \"episode\": [],\n" +
            "        \"hot\": \"11563\",\n" +
            "        \"img\": \"http://kchfpre.openspeech.cn/music_db_file/parastor/data/downdata/pictures/douban_video/26438888/5d263aa80ad468d74f02f2c7abc23905.jpg\",\n" +
            "        \"language\": \"国语\",\n" +
            "        \"leadActor\": [\n" +
            "          {\n" +
            "            \"name\": \"邓超\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"鹿晗\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"杨颖\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"郑恺\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王祖蓝\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"李晨\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"陈赫\",\n" +
            "            \"roleName\": \"\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"name\": \"奔跑吧兄弟 第三季\",\n" +
            "        \"publishTime\": \"2015-10-30\",\n" +
            "        \"score\": \"6.3\",\n" +
            "        \"screenWriter\": [],\n" +
            "        \"season\": \"第三季\",\n" +
            "        \"source\": \"iflytek\",\n" +
            "        \"tags\": [\n" +
            "          \"电视剧\",\n" +
            "          \"真人秀\",\n" +
            "          \"综艺\"\n" +
            "        ]\n" +
            "      },\n" +
            "      {\n" +
            "        \"actor\": [\n" +
            "          {\n" +
            "            \"name\": \"邓超\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"李晨\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"陈赫\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"郑恺\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王祖蓝\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"鹿晗\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"迪丽热巴\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"杨颖\",\n" +
            "            \"roleName\": \"\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"aliasName\": [\n" +
            "          \"奔跑吧兄弟第五季\",\n" +
            "          \"跑男5\",\n" +
            "          \"Keep Running\"\n" +
            "        ],\n" +
            "        \"area\": [\n" +
            "          \"中国大陆\"\n" +
            "        ],\n" +
            "        \"award\": [],\n" +
            "        \"category\": \"综艺\",\n" +
            "        \"company\": \"\",\n" +
            "        \"director\": [],\n" +
            "        \"episode\": [],\n" +
            "        \"hot\": \"10505\",\n" +
            "        \"img\": \"http://kchfpre.openspeech.cn/music_db_file/parastor/data/downdata/pictures/douban_video/26830085/0f923ec3a1d79652be1ff3f266944420.jpg\",\n" +
            "        \"language\": \"国语\",\n" +
            "        \"leadActor\": [\n" +
            "          {\n" +
            "            \"name\": \"邓超\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"李晨\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"陈赫\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"郑恺\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王祖蓝\",\n" +
            "            \"roleName\": \"\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"name\": \"奔跑吧\",\n" +
            "        \"publishTime\": \"2017-04-14\",\n" +
            "        \"score\": \"6.4\",\n" +
            "        \"screenWriter\": [],\n" +
            "        \"season\": \"\",\n" +
            "        \"source\": \"iflytek\",\n" +
            "        \"tags\": [\n" +
            "          \"电视剧\",\n" +
            "          \"真人秀\"\n" +
            "        ]\n" +
            "      },\n" +
            "      {\n" +
            "        \"actor\": [\n" +
            "          {\n" +
            "            \"name\": \"邓超\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"杨颖\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"李晨\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"郑恺\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王祖蓝\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"陈赫\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"鹿晗\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"代乐乐\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"姜超\",\n" +
            "            \"roleName\": \"\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"aliasName\": [\n" +
            "          \"跑男4\",\n" +
            "          \"Running Man Season 4\"\n" +
            "        ],\n" +
            "        \"area\": [\n" +
            "          \"中国大陆\"\n" +
            "        ],\n" +
            "        \"award\": [],\n" +
            "        \"category\": \"综艺\",\n" +
            "        \"company\": \"\",\n" +
            "        \"director\": [\n" +
            "          \"蒋敏昊\"\n" +
            "        ],\n" +
            "        \"episode\": [],\n" +
            "        \"hot\": \"8730\",\n" +
            "        \"img\": \"http://kchfpre.openspeech.cn/music_db_file/parastor/data/downdata/pictures/douban_video/26710402/8dcaccfbea4438bdd19290f57eab09da.jpg\",\n" +
            "        \"language\": \"国语\",\n" +
            "        \"leadActor\": [\n" +
            "          {\n" +
            "            \"name\": \"邓超\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"杨颖\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"李晨\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"郑恺\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王祖蓝\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"陈赫\",\n" +
            "            \"roleName\": \"\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"name\": \"奔跑吧兄弟 第四季\",\n" +
            "        \"publishTime\": \"2016-04-15\",\n" +
            "        \"score\": \"5.8\",\n" +
            "        \"screenWriter\": [],\n" +
            "        \"season\": \"第四季\",\n" +
            "        \"source\": \"iflytek\",\n" +
            "        \"tags\": [\n" +
            "          \"电视剧\",\n" +
            "          \"真人秀\",\n" +
            "          \"综艺\"\n" +
            "        ]\n" +
            "      },\n" +
            "      {\n" +
            "        \"actor\": [\n" +
            "          {\n" +
            "            \"name\": \"邓超\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王祖蓝\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王宝强\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"李晨\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"陈赫\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"郑恺\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"杨颖\",\n" +
            "            \"roleName\": \"\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"aliasName\": [\n" +
            "          \"Chinese Running Man\",\n" +
            "          \"奔跑吧!兄弟\",\n" +
            "          \"奔跑吧\",\n" +
            "          \"兄弟\",\n" +
            "          \"奔跑吧兄弟\"\n" +
            "        ],\n" +
            "        \"area\": [\n" +
            "          \"中国大陆\"\n" +
            "        ],\n" +
            "        \"award\": [],\n" +
            "        \"category\": \"综艺\",\n" +
            "        \"company\": \"\",\n" +
            "        \"director\": [],\n" +
            "        \"episode\": [],\n" +
            "        \"hot\": \"194\",\n" +
            "        \"img\": \"http://kchfpre.openspeech.cn/music_db_file/parastor/data/downdata/pictures/mtime_video/217596/360c8d0d15e08bddfe1ac73daa9a1f5e.jpg\",\n" +
            "        \"language\": \"国语\",\n" +
            "        \"leadActor\": [\n" +
            "          {\n" +
            "            \"name\": \"邓超\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王祖蓝\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王宝强\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"李晨\",\n" +
            "            \"roleName\": \"\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"name\": \"奔跑吧兄弟\",\n" +
            "        \"publishTime\": \"2014-10-10\",\n" +
            "        \"score\": \"7.1\",\n" +
            "        \"screenWriter\": [],\n" +
            "        \"season\": \"\",\n" +
            "        \"source\": \"iflytek\",\n" +
            "        \"tags\": [\n" +
            "          \"电视剧\",\n" +
            "          \"真人秀\",\n" +
            "          \"综艺\"\n" +
            "        ]\n" +
            "      },\n" +
            "      {\n" +
            "        \"actor\": [\n" +
            "          {\n" +
            "            \"name\": \"陈赫\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"郑恺\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"杨颖\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"邓超\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王祖蓝\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王宝强\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"李晨\",\n" +
            "            \"roleName\": \"\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"aliasName\": [\n" +
            "          \"Chinese Running Man\"\n" +
            "        ],\n" +
            "        \"area\": [\n" +
            "          \"中国大陆\"\n" +
            "        ],\n" +
            "        \"award\": [],\n" +
            "        \"category\": \"综艺\",\n" +
            "        \"company\": \"\",\n" +
            "        \"director\": [],\n" +
            "        \"episode\": [],\n" +
            "        \"hot\": \"42\",\n" +
            "        \"img\": \"http://kchfpre.openspeech.cn/music_db_file/parastor/data/downdata/pictures/maoyan_video/368767/2c90c84543fde459015c55e4d5691dbc.jpg\",\n" +
            "        \"language\": \"\",\n" +
            "        \"leadActor\": [\n" +
            "          {\n" +
            "            \"name\": \"陈赫\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"郑恺\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"杨颖\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"邓超\",\n" +
            "            \"roleName\": \"\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"name\": \"奔跑吧兄弟 第一季\",\n" +
            "        \"publishTime\": \"2014-10-10\",\n" +
            "        \"score\": \"6.2\",\n" +
            "        \"screenWriter\": [],\n" +
            "        \"season\": \"第一季\",\n" +
            "        \"source\": \"iflytek\",\n" +
            "        \"tags\": [\n" +
            "          \"综艺\",\n" +
            "          \"真人秀\"\n" +
            "        ]\n" +
            "      },\n" +
            "      {\n" +
            "        \"actor\": [\n" +
            "          {\n" +
            "            \"name\": \"杨颖\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"陈赫\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王宝强\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王祖蓝\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"郑恺\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"李晨\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"熊黛林\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"谢依霖\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"伊一\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"郭京飞\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"张爱钦\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"金钟国\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"邓超\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"包贝尔\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"范冰冰\",\n" +
            "            \"roleName\": \"第一期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"韩庚\",\n" +
            "            \"roleName\": \"第一期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"黄晓明\",\n" +
            "            \"roleName\": \"第二期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"欧弟\",\n" +
            "            \"roleName\": \"第三期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"宋佳\",\n" +
            "            \"roleName\": \"第三期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"林更新\",\n" +
            "            \"roleName\": \"第四期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"张艺兴\",\n" +
            "            \"roleName\": \"第四期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"叶祖新\",\n" +
            "            \"roleName\": \"第四期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"李治廷\",\n" +
            "            \"roleName\": \"第四期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"蒋劲夫\",\n" +
            "            \"roleName\": \"第四期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"吴奇隆\",\n" +
            "            \"roleName\": \"第五期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"陈乔恩\",\n" +
            "            \"roleName\": \"第五期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"杜淳\",\n" +
            "            \"roleName\": \"第五期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"姚晨\",\n" +
            "            \"roleName\": \"第六期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"萧敬腾\",\n" +
            "            \"roleName\": \"第六期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"信\",\n" +
            "            \"roleName\": \"第六期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"吉克隽逸\",\n" +
            "            \"roleName\": \"第六期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"大鹏\",\n" +
            "            \"roleName\": \"第六期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"刘涛\",\n" +
            "            \"roleName\": \"第七期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"蔡少芬\",\n" +
            "            \"roleName\": \"第九期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"霍思燕\",\n" +
            "            \"roleName\": \"第九期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"李彩桦\",\n" +
            "            \"roleName\": \"第九期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"蒋欣\",\n" +
            "            \"roleName\": \"第九期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"江一燕\",\n" +
            "            \"roleName\": \"第九期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"苏见信\",\n" +
            "            \"roleName\": \"第六期嘉宾\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"aliasName\": [\n" +
            "          \"奔跑吧!兄弟\",\n" +
            "          \"Running Man\",\n" +
            "          \"奔跑吧兄弟\",\n" +
            "          \"奔跑吧\",\n" +
            "          \"兄弟\",\n" +
            "          \"跑男\",\n" +
            "          \"奔跑吧兄弟第二季\",\n" +
            "          \"奔跑吧兄弟 第二季\",\n" +
            "          \"跑男2\",\n" +
            "          \"Running Man Season 2\"\n" +
            "        ],\n" +
            "        \"area\": [\n" +
            "          \"中国大陆\"\n" +
            "        ],\n" +
            "        \"award\": [\n" +
            "          {\n" +
            "            \"ceremony\": \"第7届金扫帚奖\",\n" +
            "            \"title\": \"最令人失望中小成本电影\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"ceremony\": \"第6届豆瓣电影鑫像奖\",\n" +
            "            \"title\": \"豆渣单元 最渣影片(华语)\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"ceremony\": \"第2届豆瓣电影年度榜单\",\n" +
            "            \"title\": \"1月最受关注电影(提名) ,评分最低的华语电影(提名)\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"ceremony\": \"第2届豆瓣电影年度榜单\",\n" +
            "            \"title\": \"最受关注的大陆综艺(提名)\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"category\": \"综艺\",\n" +
            "        \"company\": \"万达影视传媒有限公司\",\n" +
            "        \"director\": [\n" +
            "          \"胡笳\",\n" +
            "          \"岑俊义\",\n" +
            "          \"陆浩\"\n" +
            "        ],\n" +
            "        \"episode\": [],\n" +
            "        \"hot\": \"0\",\n" +
            "        \"img\": \"http://kchfpre.openspeech.cn/music_db_file/parastor/data/downdata/pictures/maoyan_video/246316/d24fafbab399042eed3e8f44fcd1b669.jpg\",\n" +
            "        \"language\": \"国语\",\n" +
            "        \"leadActor\": [\n" +
            "          {\n" +
            "            \"name\": \"杨颖\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"陈赫\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王宝强\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王祖蓝\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"李晨\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"郑恺\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"邓超\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"包贝尔\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"叶祖新\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"蒋劲夫\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"信\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"吉克隽逸\",\n" +
            "            \"roleName\": \"\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"name\": \"奔跑吧!兄弟\",\n" +
            "        \"publishTime\": \"2015-01-30\",\n" +
            "        \"score\": \"7.8\",\n" +
            "        \"screenWriter\": [\n" +
            "          \"胡笳\",\n" +
            "          \"岑俊义\",\n" +
            "          \"李雅弢\",\n" +
            "          \"杨琳\",\n" +
            "          \"俞杭英\"\n" +
            "        ],\n" +
            "        \"season\": \"第二季\",\n" +
            "        \"source\": \"iflytek\",\n" +
            "        \"tags\": [\n" +
            "          \"综艺\",\n" +
            "          \"喜剧\",\n" +
            "          \"动作\",\n" +
            "          \"冒险\",\n" +
            "          \"电视剧\",\n" +
            "          \"真人秀\"\n" +
            "        ]\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  \"rc\": 0,\n" +
            "  \"semantic\": [\n" +
            "    {\n" +
            "      \"intent\": \"QUERY\",\n" +
            "      \"slots\": [\n" +
            "        {\n" +
            "          \"name\": \"name\",\n" +
            "          \"value\": \"奔跑吧兄弟\"\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  ],\n" +
            "  \"service\": \"video\",\n" +
            "  \"state\": {\n" +
            "    \"fg::video::default::default\": {\n" +
            "      \"name\": \"1\"\n" +
            "    }\n" +
            "  },\n" +
            "  \"text\": \"奔跑吧兄弟\",\n" +
            "  \"uuid\": \"atn00166083@ch11710db360446f2001\",\n" +
            "  \"answer\": {\n" +
            "    \"text\": \"不好意思，没有为您找到您想看的影片，为您推荐\\\"奔跑吧兄弟 第一季\\\"\"\n" +
            "  },\n" +
            "  \"dialog_stat\": \"dataInvalid\",\n" +
            "  \"save_history\": true,\n" +
            "  \"sid\": \"atn00166083@ch11710db360446f2001\"\n" +
            "}";

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void attachView() {
        mPresenter.attachView(this);
    }

    @Override
    public void detachView() {
        mPresenter.detachView();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void initView() {

        fragmentList = new ArrayList<>();
        fragmentList.add(new FragmentHelp_1());
        fragmentList.add(new FragmentHelp_Home_2());

        helpFragmentAdapter = new HelpFragmentAdapter(fragmentList, getSupportFragmentManager());
        viewpager.setAdapter(helpFragmentAdapter);

        mData = new ArrayList<>();

        mAdapter = new TalkApadtep(this, mData);
        rvSpeech.setLayoutManager(new LinearLayoutManager(this));
        rvSpeech.setAdapter(mAdapter);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        mAdapter.setTalkNewsItemLiserten(this);
//    UmengUtil.onEvent(MainActivity.this, "MainActivity", null);
        mainActivity = this;
        HermesEventBus.getDefault().register(this);

        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setMessage("1");
        requestMessage.setService("SpeechStart");
        HermesEventBus.getDefault().post(requestMessage);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMainAppEvent(SendMessage message) {
        Logger.e(message.getMessage() + " \n" + message.getService());

        String service = message.getService();
        String Message = message.getMessage();


        isFist = false;
        isClickHelp = true;

        if (llCentet.getVisibility() == View.VISIBLE)
            llCentet.setVisibility(View.INVISIBLE);

        if (rvSpeech.getVisibility() == View.GONE)
            rvSpeech.setVisibility(View.VISIBLE);

        if (RippleVoice_N.getVisibility() == View.GONE)
            RippleVoice_N.setVisibility(View.VISIBLE);

        if (viewpager.getVisibility() == View.VISIBLE)
            viewpager.setVisibility(View.GONE);

        switch (message.getService()) {
            case "speech_start":  //用户开始说话
                if (isFist)
                    RippleVoice.startRippleAnimation();
                else
                    RippleVoice_N.startRippleAnimation();

                break;

            case "speech_over": //用户说话结束

                if (RippleVoice.isRippleAnimationRunning())
                    RippleVoice.stopRippleAnimation();
                if (RippleVoice_N.isRippleAnimationRunning())
                    RippleVoice_N.stopRippleAnimation();

                break;

            case "wakeup_text":

                userChatBean = new UserChatBean();
                userChatBean.setText(getResources().getString(R.string.wakeup_text));
                mData.add(userChatBean);

                robotCommandRequest = new RobotCommandRequest();
                robotCommandRequest.setText(Message);
                mData.add(robotCommandRequest);

            case "r4_0":  //听不懂一次
                r4Bean = JsonParser.parseResultR4Bean(Message);
                if (r4Bean != null && r4Bean.getText() != null) {

                    userChatBean = new UserChatBean();
                    userChatBean.setText(r4Bean.getText());
                    mData.add(userChatBean);

                    robotCommandRequest = new RobotCommandRequest();
                    robotCommandRequest.setText(getResources().getString(R.string.r4_0_text));
                    mData.add(robotCommandRequest);

                }
                break;
            case "r4_1":// 听不懂二次
                r4Bean = JsonParser.parseResultR4Bean(Message);
                if (r4Bean != null && r4Bean.getText() != null) {

                    userChatBean = new UserChatBean();
                    userChatBean.setText(r4Bean.getText());
                    mData.add(userChatBean);

                    robotCommandRequest = new RobotCommandRequest();
                    robotCommandRequest.setText(getResources().getString(R.string.r4_1_text));
                    mData.add(robotCommandRequest);

                }
                break;
            case "r4_2": // 听不懂三次
                r4Bean = JsonParser.parseResultR4Bean(Message);
                if (r4Bean != null && r4Bean.getText() != null) {

                    userChatBean = new UserChatBean();
                    userChatBean.setText(r4Bean.getText());
                    mData.add(userChatBean);
                    robotCommandRequest = new RobotCommandRequest();
                    robotCommandRequest.setText(getResources().getString(R.string.r4_2_text));
                    mData.add(robotCommandRequest);

                }
                break;

            case "openQA":  //开放问答
                OpenQABean openQABean = JsonParser.parseResultOpenQABean(Message);
                if (openQABean != null && openQABean.getText() != null) {
                    userChatBean = new UserChatBean();
                    userChatBean.setText(openQABean.getText());
                    mData.add(userChatBean);
                    mData.add(openQABean);
                }
                break;

            case "joke":  //笑话问答
                JokeBean jokeBean = JsonParser.parseResultJokeBean(Message);
                if (jokeBean != null && jokeBean.getText() != null) {
                    userChatBean = new UserChatBean();
                    userChatBean.setText(jokeBean.getText());
                    mData.add(userChatBean);
                    robotCommandRequest = new RobotCommandRequest();
                    robotCommandRequest.setText(getResources().getString(R.string.no_text));
                    mData.add(robotCommandRequest);

                }
                break;

            case "story":  //故事问答
                StoryBean storyBean = JsonParser.parseResultStoryBean(Message);
                if (storyBean != null && storyBean.getText() != null) {
                    userChatBean = new UserChatBean();
                    userChatBean.setText(storyBean.getText());
                    mData.add(userChatBean);
                    robotCommandRequest = new RobotCommandRequest();
                    robotCommandRequest.setText(getResources().getString(R.string.no_text));
                    mData.add(robotCommandRequest);
                }
                break;

            case "poetry"://诗词问答
                PoetryBean poetryBean = JsonParser.parseResultPoetryBean(Message);
                if (poetryBean != null && poetryBean.getText() != null) {
                    userChatBean = new UserChatBean();
                    userChatBean.setText(poetryBean.getText());
                    mData.add(userChatBean);
                    mData.add(poetryBean);
                }
                break;

            case "baike"://百科词条查询
                BaikeBean baikeBean = JsonParser.parseResultBaikeBean(Message);
                if (baikeBean != null && baikeBean.getText() != null) {
                    userChatBean = new UserChatBean();
                    userChatBean.setText(baikeBean.getText());
                    mData.add(userChatBean);
                    mData.add(baikeBean);
                }
                break;

            case "OPENAPPTEST.custom_baike"://自定义百科
                CustomBaikeBean customBaikeBean = JsonParser.parseResultCustomBaikeBean(Message);
                if (customBaikeBean != null && customBaikeBean.getText() != null) {

                    userChatBean = new UserChatBean();
                    userChatBean.setText(customBaikeBean.getText());
                    mData.add(userChatBean);
                    mData.add(customBaikeBean);

                }
                break;

            case "datetime"://时间问答
                DatetimeBean datetimeBean = JsonParser.parseResultDatetimeBean(Message);
                if (datetimeBean != null && datetimeBean.getText() != null) {

                    userChatBean = new UserChatBean();
                    userChatBean.setText(datetimeBean.getText());
                    mData.add(userChatBean);
                    mData.add(datetimeBean);

                }
                break;

            case "calc":// 数值问答
                CalcBean calcBean = JsonParser.parseResultCalcBean(Message);
                if (calcBean != null && calcBean.getText() != null) {
                    userChatBean = new UserChatBean();
                    userChatBean.setText(calcBean.getText());
                    mData.add(userChatBean);
                    mData.add(calcBean);

                }
                break;

            case "weather":   //天气问答
                WeatherBean weatherBean = JsonParser.parseResultWeatherBean(Message);
                if (weatherBean != null && weatherBean.getText() != null) {
                    userChatBean = new UserChatBean();
                    userChatBean.setText(weatherBean.getText());
                    mData.add(userChatBean);
                    mData.add(weatherBean);

                }
                break;

            case "news":   //新闻问答

                NewsBean newsBean = JsonParser.parseResultNewsBean(Message);
                if (newsBean != null && newsBean.getText() != null) {

                    userChatBean = new UserChatBean();
                    userChatBean.setText(newsBean.getText());

                    robotCommandRequest = new RobotCommandRequest();
                    robotCommandRequest.setText(getResources().getString(R.string.news_text));

                    mData.add(userChatBean);
                    mData.add(robotCommandRequest);
                    mData.add(newsBean);

                }

                break;

            case  "video_1":  // 视频问答
                VideoBean videoBean = JsonParser.parseResultVideoBean(Message);
                if(videoBean!=null && videoBean.getText()!=null && videoBean.getAnswer()!=null ){
                    userChatBean = new UserChatBean();
                    userChatBean.setText(videoBean.getText());
                    mData.add(userChatBean);
                    robotCommandRequest = new RobotCommandRequest();
                    robotCommandRequest.setText(videoBean.getAnswer().getText());
                    mData.add(robotCommandRequest);
                }
                break;

            case "flight":   //订票服务

                FlightBean flightBean = JsonParser.parseResultFlightoBean(Message);
                if (flightBean != null && flightBean.getData() != null) {

                    userChatBean = new UserChatBean();
                    userChatBean.setText(flightBean.getText());
                    mData.add(userChatBean);
                    mData.add(flightBean);

                } else {
                    if (flightBean != null && flightBean.getText() != null) {
                        userChatBean = new UserChatBean();
                        userChatBean.setText(flightBean.getText());
                        mData.add(userChatBean);
                        robotCommandRequest = new RobotCommandRequest();
                        robotCommandRequest.setText(flightBean.getAnswer().getText());
                        mData.add(robotCommandRequest);
                    }
                }
                break;
        }

        mAdapter.notifyDataSetChanged();
        rvSpeech.scrollToPosition(mAdapter.getItemCount() - 1);

    }

    @Override
    public void showError(String message) {

    }

    @OnClick({R.id.iv_phone, R.id.iv_help, R.id.llExit, R.id.video_n})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.llExit:
                if (isClickHelp) {
                    this.finish();
                } else {
                    if (FragmentHelp_Home_2.fragmentHelpHome2.getvisibilityType() != 0) {
                        FragmentHelp_Home_2.fragmentHelpHome2.setvisibilityStatr();
                    } else if (!isClickHelp) {
                        isClickHelp = true;
                        if (viewpager.getVisibility() == View.VISIBLE)
                            viewpager.setVisibility(View.GONE);
                        if (mData.size() == 0) {
                            if (rvSpeech.getVisibility() == View.VISIBLE)
                                rvSpeech.setVisibility(View.GONE);
                            if (RippleVoice_N.getVisibility() == View.VISIBLE)
                                RippleVoice_N.setVisibility(View.GONE);
                            if (llCentet.getVisibility() == View.INVISIBLE)
                                llCentet.setVisibility(View.VISIBLE);
                            break;
                        } else {
                            if (rvSpeech.getVisibility() == View.GONE)
                                rvSpeech.setVisibility(View.VISIBLE);
                        }
                    }
                }
                break;

            case R.id.video_n:


                break;

            case R.id.iv_phone:

                break;

            case R.id.iv_help:

                if (isClickHelp) {

                    if (llCentet.getVisibility() == View.VISIBLE)
                        llCentet.setVisibility(View.INVISIBLE);
                    if (rvSpeech.getVisibility() == View.VISIBLE)
                        rvSpeech.setVisibility(View.GONE);
                    if (RippleVoice_N.getVisibility() == View.GONE)
                        RippleVoice_N.setVisibility(View.VISIBLE);
                    if (viewpager.getVisibility() == View.GONE)
                        viewpager.setVisibility(View.VISIBLE);

                    isClickHelp = false;
                    break;

                } else {
                    isClickHelp = true;
                    if (viewpager.getVisibility() == View.VISIBLE)
                        viewpager.setVisibility(View.GONE);
                    if (mData.size() == 0) {
                        if (rvSpeech.getVisibility() == View.VISIBLE)
                            rvSpeech.setVisibility(View.GONE);
                        if (RippleVoice_N.getVisibility() == View.VISIBLE)
                            RippleVoice_N.setVisibility(View.GONE);
                        if (llCentet.getVisibility() == View.INVISIBLE)
                            llCentet.setVisibility(View.VISIBLE);
                        break;

                    } else {
                        if (rvSpeech.getVisibility() == View.GONE)
                            rvSpeech.setVisibility(View.VISIBLE);
                        break;

                    }
                }
        }
    }

    public void setScanScroll(boolean isCanScroll) {
        viewpager.setScanScroll(isCanScroll);
    }

    @Override
    public void onTalkNewsItemClick(String url) {

        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setMessage(url);
        requestMessage.setService("PlayUrl");
        HermesEventBus.getDefault().post(requestMessage);

    }
}
