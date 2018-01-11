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
            "        \"aPort\": \"虹桥国际机场\",\n" +
            "        \"airline\": \"四川航空股份有限公司\",\n" +
            "        \"arriveCity\": \"上海\",\n" +
            "        \"arriveTime\": \"2018-01-12 10:20:00\",\n" +
            "        \"arriveTimeStamp\": \"1515723600\",\n" +
            "        \"cabinInfo\": \"经济舱\",\n" +
            "        \"dPort\": \"宝安国际机场\",\n" +
            "        \"departCity\": \"深圳\",\n" +
            "        \"endtime_for_voice\": \"明天10:20:00\",\n" +
            "        \"endtimestamp\": 1515723600,\n" +
            "        \"flight\": \"3U3139\",\n" +
            "        \"price\": \"900\",\n" +
            "        \"quantity\": \"10\",\n" +
            "        \"rate\": \"0.64\",\n" +
            "        \"standardPrice\": \"0\",\n" +
            "        \"starttime_for_voice\": \"明天08:00:00\",\n" +
            "        \"starttimestamp\": 1515715200,\n" +
            "        \"takeOffTime\": \"2018-01-12 08:00:00\",\n" +
            "        \"takeOffTimeStamp\": \"1515715200\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"aPort\": \"虹桥国际机场\",\n" +
            "        \"airline\": \"中国南方航空股份有限公司\",\n" +
            "        \"arriveCity\": \"上海\",\n" +
            "        \"arriveTime\": \"2018-01-12 08:55:00\",\n" +
            "        \"arriveTimeStamp\": \"1515718500\",\n" +
            "        \"cabinInfo\": \"经济舱\",\n" +
            "        \"dPort\": \"宝安国际机场\",\n" +
            "        \"departCity\": \"深圳\",\n" +
            "        \"endtime_for_voice\": \"明天08:55:00\",\n" +
            "        \"endtimestamp\": 1515718500,\n" +
            "        \"flight\": \"CZ3568\",\n" +
            "        \"price\": \"900\",\n" +
            "        \"quantity\": \"6\",\n" +
            "        \"rate\": \"0.64\",\n" +
            "        \"standardPrice\": \"1400\",\n" +
            "        \"starttime_for_voice\": \"明天06:40:00\",\n" +
            "        \"starttimestamp\": 1515710400,\n" +
            "        \"takeOffTime\": \"2018-01-12 06:40:00\",\n" +
            "        \"takeOffTimeStamp\": \"1515710400\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"aPort\": \"虹桥国际机场\",\n" +
            "        \"airline\": \"中国南方航空股份有限公司\",\n" +
            "        \"arriveCity\": \"上海\",\n" +
            "        \"arriveTime\": \"2018-01-12 08:55:00\",\n" +
            "        \"arriveTimeStamp\": \"1515718500\",\n" +
            "        \"cabinInfo\": \"经济舱\",\n" +
            "        \"dPort\": \"宝安国际机场\",\n" +
            "        \"departCity\": \"深圳\",\n" +
            "        \"endtime_for_voice\": \"明天08:55:00\",\n" +
            "        \"endtimestamp\": 1515718500,\n" +
            "        \"flight\": \"CZ3568\",\n" +
            "        \"price\": \"970\",\n" +
            "        \"quantity\": \"7\",\n" +
            "        \"rate\": \"0.69\",\n" +
            "        \"standardPrice\": \"1400\",\n" +
            "        \"starttime_for_voice\": \"明天06:40:00\",\n" +
            "        \"starttimestamp\": 1515710400,\n" +
            "        \"takeOffTime\": \"2018-01-12 06:40:00\",\n" +
            "        \"takeOffTimeStamp\": \"1515710400\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"aPort\": \"虹桥国际机场\",\n" +
            "        \"airline\": \"中国南方航空股份有限公司\",\n" +
            "        \"arriveCity\": \"上海\",\n" +
            "        \"arriveTime\": \"2018-01-12 08:55:00\",\n" +
            "        \"arriveTimeStamp\": \"1515718500\",\n" +
            "        \"cabinInfo\": \"经济舱\",\n" +
            "        \"dPort\": \"宝安国际机场\",\n" +
            "        \"departCity\": \"深圳\",\n" +
            "        \"endtime_for_voice\": \"明天08:55:00\",\n" +
            "        \"endtimestamp\": 1515718500,\n" +
            "        \"flight\": \"CZ3568\",\n" +
            "        \"price\": \"1040\",\n" +
            "        \"quantity\": \"10\",\n" +
            "        \"rate\": \"0.74\",\n" +
            "        \"standardPrice\": \"1400\",\n" +
            "        \"starttime_for_voice\": \"明天06:40:00\",\n" +
            "        \"starttimestamp\": 1515710400,\n" +
            "        \"takeOffTime\": \"2018-01-12 06:40:00\",\n" +
            "        \"takeOffTimeStamp\": \"1515710400\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"aPort\": \"虹桥国际机场\",\n" +
            "        \"airline\": \"中国南方航空股份有限公司\",\n" +
            "        \"arriveCity\": \"上海\",\n" +
            "        \"arriveTime\": \"2018-01-12 08:55:00\",\n" +
            "        \"arriveTimeStamp\": \"1515718500\",\n" +
            "        \"cabinInfo\": \"经济舱\",\n" +
            "        \"dPort\": \"宝安国际机场\",\n" +
            "        \"departCity\": \"深圳\",\n" +
            "        \"endtime_for_voice\": \"明天08:55:00\",\n" +
            "        \"endtimestamp\": 1515718500,\n" +
            "        \"flight\": \"CZ3568\",\n" +
            "        \"price\": \"1110\",\n" +
            "        \"quantity\": \"10\",\n" +
            "        \"rate\": \"0.79\",\n" +
            "        \"standardPrice\": \"1400\",\n" +
            "        \"starttime_for_voice\": \"明天06:40:00\",\n" +
            "        \"starttimestamp\": 1515710400,\n" +
            "        \"takeOffTime\": \"2018-01-12 06:40:00\",\n" +
            "        \"takeOffTimeStamp\": \"1515710400\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"aPort\": \"虹桥国际机场\",\n" +
            "        \"airline\": \"中国南方航空股份有限公司\",\n" +
            "        \"arriveCity\": \"上海\",\n" +
            "        \"arriveTime\": \"2018-01-12 08:55:00\",\n" +
            "        \"arriveTimeStamp\": \"1515718500\",\n" +
            "        \"cabinInfo\": \"经济舱\",\n" +
            "        \"dPort\": \"宝安国际机场\",\n" +
            "        \"departCity\": \"深圳\",\n" +
            "        \"endtime_for_voice\": \"明天08:55:00\",\n" +
            "        \"endtimestamp\": 1515718500,\n" +
            "        \"flight\": \"CZ3568\",\n" +
            "        \"price\": \"1180\",\n" +
            "        \"quantity\": \"10\",\n" +
            "        \"rate\": \"0.84\",\n" +
            "        \"standardPrice\": \"1400\",\n" +
            "        \"starttime_for_voice\": \"明天06:40:00\",\n" +
            "        \"starttimestamp\": 1515710400,\n" +
            "        \"takeOffTime\": \"2018-01-12 06:40:00\",\n" +
            "        \"takeOffTimeStamp\": \"1515710400\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"aPort\": \"虹桥国际机场\",\n" +
            "        \"airline\": \"中国南方航空股份有限公司\",\n" +
            "        \"arriveCity\": \"上海\",\n" +
            "        \"arriveTime\": \"2018-01-12 08:55:00\",\n" +
            "        \"arriveTimeStamp\": \"1515718500\",\n" +
            "        \"cabinInfo\": \"公务舱\",\n" +
            "        \"dPort\": \"宝安国际机场\",\n" +
            "        \"departCity\": \"深圳\",\n" +
            "        \"endtime_for_voice\": \"明天08:55:00\",\n" +
            "        \"endtimestamp\": 1515718500,\n" +
            "        \"flight\": \"CZ3568\",\n" +
            "        \"price\": \"1250\",\n" +
            "        \"quantity\": \"7\",\n" +
            "        \"rate\": \"0.36\",\n" +
            "        \"standardPrice\": \"3500\",\n" +
            "        \"starttime_for_voice\": \"明天06:40:00\",\n" +
            "        \"starttimestamp\": 1515710400,\n" +
            "        \"takeOffTime\": \"2018-01-12 06:40:00\",\n" +
            "        \"takeOffTimeStamp\": \"1515710400\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"aPort\": \"虹桥国际机场\",\n" +
            "        \"airline\": \"中国南方航空股份有限公司\",\n" +
            "        \"arriveCity\": \"上海\",\n" +
            "        \"arriveTime\": \"2018-01-12 08:55:00\",\n" +
            "        \"arriveTimeStamp\": \"1515718500\",\n" +
            "        \"cabinInfo\": \"经济舱\",\n" +
            "        \"dPort\": \"宝安国际机场\",\n" +
            "        \"departCity\": \"深圳\",\n" +
            "        \"endtime_for_voice\": \"明天08:55:00\",\n" +
            "        \"endtimestamp\": 1515718500,\n" +
            "        \"flight\": \"CZ3568\",\n" +
            "        \"price\": \"1320\",\n" +
            "        \"quantity\": \"10\",\n" +
            "        \"rate\": \"0.94\",\n" +
            "        \"standardPrice\": \"1400\",\n" +
            "        \"starttime_for_voice\": \"明天06:40:00\",\n" +
            "        \"starttimestamp\": 1515710400,\n" +
            "        \"takeOffTime\": \"2018-01-12 06:40:00\",\n" +
            "        \"takeOffTimeStamp\": \"1515710400\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"aPort\": \"虹桥国际机场\",\n" +
            "        \"airline\": \"中国南方航空股份有限公司\",\n" +
            "        \"arriveCity\": \"上海\",\n" +
            "        \"arriveTime\": \"2018-01-12 08:55:00\",\n" +
            "        \"arriveTimeStamp\": \"1515718500\",\n" +
            "        \"cabinInfo\": \"经济舱\",\n" +
            "        \"dPort\": \"宝安国际机场\",\n" +
            "        \"departCity\": \"深圳\",\n" +
            "        \"endtime_for_voice\": \"明天08:55:00\",\n" +
            "        \"endtimestamp\": 1515718500,\n" +
            "        \"flight\": \"CZ3568\",\n" +
            "        \"price\": \"1400\",\n" +
            "        \"quantity\": \"10\",\n" +
            "        \"rate\": \"1\",\n" +
            "        \"standardPrice\": \"1400\",\n" +
            "        \"starttime_for_voice\": \"明天06:40:00\",\n" +
            "        \"starttimestamp\": 1515710400,\n" +
            "        \"takeOffTime\": \"2018-01-12 06:40:00\",\n" +
            "        \"takeOffTimeStamp\": \"1515710400\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"aPort\": \"虹桥国际机场\",\n" +
            "        \"airline\": \"中国南方航空股份有限公司\",\n" +
            "        \"arriveCity\": \"上海\",\n" +
            "        \"arriveTime\": \"2018-01-12 08:55:00\",\n" +
            "        \"arriveTimeStamp\": \"1515718500\",\n" +
            "        \"cabinInfo\": \"公务舱\",\n" +
            "        \"dPort\": \"宝安国际机场\",\n" +
            "        \"departCity\": \"深圳\",\n" +
            "        \"endtime_for_voice\": \"明天08:55:00\",\n" +
            "        \"endtimestamp\": 1515718500,\n" +
            "        \"flight\": \"CZ3568\",\n" +
            "        \"price\": \"1470\",\n" +
            "        \"quantity\": \"10\",\n" +
            "        \"rate\": \"0.42\",\n" +
            "        \"standardPrice\": \"3500\",\n" +
            "        \"starttime_for_voice\": \"明天06:40:00\",\n" +
            "        \"starttimestamp\": 1515710400,\n" +
            "        \"takeOffTime\": \"2018-01-12 06:40:00\",\n" +
            "        \"takeOffTimeStamp\": \"1515710400\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"aPort\": \"虹桥国际机场\",\n" +
            "        \"airline\": \"中国南方航空股份有限公司\",\n" +
            "        \"arriveCity\": \"上海\",\n" +
            "        \"arriveTime\": \"2018-01-12 08:55:00\",\n" +
            "        \"arriveTimeStamp\": \"1515718500\",\n" +
            "        \"cabinInfo\": \"公务舱\",\n" +
            "        \"dPort\": \"宝安国际机场\",\n" +
            "        \"departCity\": \"深圳\",\n" +
            "        \"endtime_for_voice\": \"明天08:55:00\",\n" +
            "        \"endtimestamp\": 1515718500,\n" +
            "        \"flight\": \"CZ3568\",\n" +
            "        \"price\": \"1960\",\n" +
            "        \"quantity\": \"10\",\n" +
            "        \"rate\": \"0.56\",\n" +
            "        \"standardPrice\": \"3500\",\n" +
            "        \"starttime_for_voice\": \"明天06:40:00\",\n" +
            "        \"starttimestamp\": 1515710400,\n" +
            "        \"takeOffTime\": \"2018-01-12 06:40:00\",\n" +
            "        \"takeOffTimeStamp\": \"1515710400\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"aPort\": \"虹桥国际机场\",\n" +
            "        \"airline\": \"中国南方航空股份有限公司\",\n" +
            "        \"arriveCity\": \"上海\",\n" +
            "        \"arriveTime\": \"2018-01-12 08:55:00\",\n" +
            "        \"arriveTimeStamp\": \"1515718500\",\n" +
            "        \"cabinInfo\": \"公务舱\",\n" +
            "        \"dPort\": \"宝安国际机场\",\n" +
            "        \"departCity\": \"深圳\",\n" +
            "        \"endtime_for_voice\": \"明天08:55:00\",\n" +
            "        \"endtimestamp\": 1515718500,\n" +
            "        \"flight\": \"CZ3568\",\n" +
            "        \"price\": \"3500\",\n" +
            "        \"quantity\": \"10\",\n" +
            "        \"rate\": \"1\",\n" +
            "        \"standardPrice\": \"3500\",\n" +
            "        \"starttime_for_voice\": \"明天06:40:00\",\n" +
            "        \"starttimestamp\": 1515710400,\n" +
            "        \"takeOffTime\": \"2018-01-12 06:40:00\",\n" +
            "        \"takeOffTimeStamp\": \"1515710400\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"aPort\": \"浦东国际机场\",\n" +
            "        \"airline\": \"海南航空股份有限公司\",\n" +
            "        \"arriveCity\": \"上海\",\n" +
            "        \"arriveTime\": \"2018-01-12 09:25:00\",\n" +
            "        \"arriveTimeStamp\": \"1515720300\",\n" +
            "        \"cabinInfo\": \"经济舱\",\n" +
            "        \"dPort\": \"宝安国际机场\",\n" +
            "        \"departCity\": \"深圳\",\n" +
            "        \"endtime_for_voice\": \"明天09:25:00\",\n" +
            "        \"endtimestamp\": 1515720300,\n" +
            "        \"flight\": \"HU7207\",\n" +
            "        \"price\": \"670\",\n" +
            "        \"quantity\": \"10\",\n" +
            "        \"rate\": \"0.48\",\n" +
            "        \"standardPrice\": \"1400\",\n" +
            "        \"starttime_for_voice\": \"明天07:00:00\",\n" +
            "        \"starttimestamp\": 1515711600,\n" +
            "        \"takeOffTime\": \"2018-01-12 07:00:00\",\n" +
            "        \"takeOffTimeStamp\": \"1515711600\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"aPort\": \"浦东国际机场\",\n" +
            "        \"airline\": \"海南航空股份有限公司\",\n" +
            "        \"arriveCity\": \"上海\",\n" +
            "        \"arriveTime\": \"2018-01-12 09:25:00\",\n" +
            "        \"arriveTimeStamp\": \"1515720300\",\n" +
            "        \"cabinInfo\": \"经济舱\",\n" +
            "        \"dPort\": \"宝安国际机场\",\n" +
            "        \"departCity\": \"深圳\",\n" +
            "        \"endtime_for_voice\": \"明天09:25:00\",\n" +
            "        \"endtimestamp\": 1515720300,\n" +
            "        \"flight\": \"HU7207\",\n" +
            "        \"price\": \"760\",\n" +
            "        \"quantity\": \"10\",\n" +
            "        \"rate\": \"0.54\",\n" +
            "        \"standardPrice\": \"1400\",\n" +
            "        \"starttime_for_voice\": \"明天07:00:00\",\n" +
            "        \"starttimestamp\": 1515711600,\n" +
            "        \"takeOffTime\": \"2018-01-12 07:00:00\",\n" +
            "        \"takeOffTimeStamp\": \"1515711600\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"aPort\": \"浦东国际机场\",\n" +
            "        \"airline\": \"海南航空股份有限公司\",\n" +
            "        \"arriveCity\": \"上海\",\n" +
            "        \"arriveTime\": \"2018-01-12 09:25:00\",\n" +
            "        \"arriveTimeStamp\": \"1515720300\",\n" +
            "        \"cabinInfo\": \"经济舱\",\n" +
            "        \"dPort\": \"宝安国际机场\",\n" +
            "        \"departCity\": \"深圳\",\n" +
            "        \"endtime_for_voice\": \"明天09:25:00\",\n" +
            "        \"endtimestamp\": 1515720300,\n" +
            "        \"flight\": \"HU7207\",\n" +
            "        \"price\": \"840\",\n" +
            "        \"quantity\": \"10\",\n" +
            "        \"rate\": \"0.6\",\n" +
            "        \"standardPrice\": \"1400\",\n" +
            "        \"starttime_for_voice\": \"明天07:00:00\",\n" +
            "        \"starttimestamp\": 1515711600,\n" +
            "        \"takeOffTime\": \"2018-01-12 07:00:00\",\n" +
            "        \"takeOffTimeStamp\": \"1515711600\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"aPort\": \"浦东国际机场\",\n" +
            "        \"airline\": \"海南航空股份有限公司\",\n" +
            "        \"arriveCity\": \"上海\",\n" +
            "        \"arriveTime\": \"2018-01-12 09:25:00\",\n" +
            "        \"arriveTimeStamp\": \"1515720300\",\n" +
            "        \"cabinInfo\": \"经济舱\",\n" +
            "        \"dPort\": \"宝安国际机场\",\n" +
            "        \"departCity\": \"深圳\",\n" +
            "        \"endtime_for_voice\": \"明天09:25:00\",\n" +
            "        \"endtimestamp\": 1515720300,\n" +
            "        \"flight\": \"HU7207\",\n" +
            "        \"price\": \"850\",\n" +
            "        \"quantity\": \"10\",\n" +
            "        \"rate\": \"0.61\",\n" +
            "        \"standardPrice\": \"1400\",\n" +
            "        \"starttime_for_voice\": \"明天07:00:00\",\n" +
            "        \"starttimestamp\": 1515711600,\n" +
            "        \"takeOffTime\": \"2018-01-12 07:00:00\",\n" +
            "        \"takeOffTimeStamp\": \"1515711600\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"aPort\": \"浦东国际机场\",\n" +
            "        \"airline\": \"海南航空股份有限公司\",\n" +
            "        \"arriveCity\": \"上海\",\n" +
            "        \"arriveTime\": \"2018-01-12 09:25:00\",\n" +
            "        \"arriveTimeStamp\": \"1515720300\",\n" +
            "        \"cabinInfo\": \"经济舱\",\n" +
            "        \"dPort\": \"宝安国际机场\",\n" +
            "        \"departCity\": \"深圳\",\n" +
            "        \"endtime_for_voice\": \"明天09:25:00\",\n" +
            "        \"endtimestamp\": 1515720300,\n" +
            "        \"flight\": \"HU7207\",\n" +
            "        \"price\": \"950\",\n" +
            "        \"quantity\": \"10\",\n" +
            "        \"rate\": \"0.68\",\n" +
            "        \"standardPrice\": \"1400\",\n" +
            "        \"starttime_for_voice\": \"明天07:00:00\",\n" +
            "        \"starttimestamp\": 1515711600,\n" +
            "        \"takeOffTime\": \"2018-01-12 07:00:00\",\n" +
            "        \"takeOffTimeStamp\": \"1515711600\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"aPort\": \"浦东国际机场\",\n" +
            "        \"airline\": \"海南航空股份有限公司\",\n" +
            "        \"arriveCity\": \"上海\",\n" +
            "        \"arriveTime\": \"2018-01-12 09:25:00\",\n" +
            "        \"arriveTimeStamp\": \"1515720300\",\n" +
            "        \"cabinInfo\": \"经济舱\",\n" +
            "        \"dPort\": \"宝安国际机场\",\n" +
            "        \"departCity\": \"深圳\",\n" +
            "        \"endtime_for_voice\": \"明天09:25:00\",\n" +
            "        \"endtimestamp\": 1515720300,\n" +
            "        \"flight\": \"HU7207\",\n" +
            "        \"price\": \"1060\",\n" +
            "        \"quantity\": \"10\",\n" +
            "        \"rate\": \"0.76\",\n" +
            "        \"standardPrice\": \"1400\",\n" +
            "        \"starttime_for_voice\": \"明天07:00:00\",\n" +
            "        \"starttimestamp\": 1515711600,\n" +
            "        \"takeOffTime\": \"2018-01-12 07:00:00\",\n" +
            "        \"takeOffTimeStamp\": \"1515711600\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"aPort\": \"浦东国际机场\",\n" +
            "        \"arriveCity\": \"上海\",\n" +
            "        \"arriveTime\": \"2018-01-12 09:25:00\",\n" +
            "        \"arriveTimeStamp\": \"1515720300\",\n" +
            "        \"cabinInfo\": \"经济舱\",\n" +
            "        \"dPort\": \"宝安国际机场\",\n" +
            "        \"departCity\": \"深圳\",\n" +
            "        \"endtime_for_voice\": \"明天09:25:00\",\n" +
            "        \"endtimestamp\": 1515720300,\n" +
            "        \"flight\": \"Y87207\",\n" +
            "        \"price\": \"1060\",\n" +
            "        \"quantity\": \"10\",\n" +
            "        \"rate\": \"0.76\",\n" +
            "        \"standardPrice\": \"0\",\n" +
            "        \"starttime_for_voice\": \"明天07:00:00\",\n" +
            "        \"starttimestamp\": 1515711600,\n" +
            "        \"takeOffTime\": \"2018-01-12 07:00:00\",\n" +
            "        \"takeOffTimeStamp\": \"1515711600\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"aPort\": \"浦东国际机场\",\n" +
            "        \"arriveCity\": \"上海\",\n" +
            "        \"arriveTime\": \"2018-01-12 09:25:00\",\n" +
            "        \"arriveTimeStamp\": \"1515720300\",\n" +
            "        \"cabinInfo\": \"经济舱\",\n" +
            "        \"dPort\": \"宝安国际机场\",\n" +
            "        \"departCity\": \"深圳\",\n" +
            "        \"endtime_for_voice\": \"明天09:25:00\",\n" +
            "        \"endtimestamp\": 1515720300,\n" +
            "        \"flight\": \"Y87207\",\n" +
            "        \"price\": \"1180\",\n" +
            "        \"quantity\": \"10\",\n" +
            "        \"rate\": \"0.84\",\n" +
            "        \"standardPrice\": \"0\",\n" +
            "        \"starttime_for_voice\": \"明天07:00:00\",\n" +
            "        \"starttimestamp\": 1515711600,\n" +
            "        \"takeOffTime\": \"2018-01-12 07:00:00\",\n" +
            "        \"takeOffTimeStamp\": \"1515711600\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"webpage\": \"http://athena.openspeech.cn/athena/iss?method=webpage&wsid=35c1a254dfcc54e6ae5b0bbfe4931325&sid=atn000a3166@ch10200db242176f1d01\"\n" +
            "  },\n" +
            "  \"rc\": 0,\n" +
            "  \"semantic\": [\n" +
            "    {\n" +
            "      \"intent\": \"QUERY\",\n" +
            "      \"slots\": [\n" +
            "        {\n" +
            "          \"name\": \"endLoc.city\",\n" +
            "          \"value\": \"上海市\",\n" +
            "          \"normValue\": \"上海市\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"endLoc.cityAddr\",\n" +
            "          \"value\": \"上海\",\n" +
            "          \"normValue\": \"上海\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"endLoc.type\",\n" +
            "          \"value\": \"LOC_BASIC\",\n" +
            "          \"normValue\": \"LOC_BASIC\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"startDate\",\n" +
            "          \"value\": \"明天\",\n" +
            "          \"normValue\": \"{\\\"datetime\\\":\\\"2018-01-12\\\",\\\"suggestDatetime\\\":\\\"2018-01-12\\\"}\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"startLoc.city\",\n" +
            "          \"value\": \"深圳市\",\n" +
            "          \"normValue\": \"深圳市\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"startLoc.cityAddr\",\n" +
            "          \"value\": \"深圳\",\n" +
            "          \"normValue\": \"深圳\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"startLoc.type\",\n" +
            "          \"value\": \"LOC_BASIC\",\n" +
            "          \"normValue\": \"LOC_BASIC\"\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  ],\n" +
            "  \"service\": \"flight\",\n" +
            "  \"state\": {\n" +
            "    \"fg::flight::default::default\": {\n" +
            "      \"state\": \"default\"\n" +
            "    }\n" +
            "  },\n" +
            "  \"text\": \"明天\",\n" +
            "  \"uuid\": \"atn000a3166@ch10200db242176f1d01\",\n" +
            "  \"answer\": {\n" +
            "    \"text\": \"[n2]为您找到100个班次，推荐明天08:00:00从深圳出发，明天10:20:00到达上海的[n1][h1]3U3139的经济舱[h0][n0]，价格是[h2]900元[h0]，航空公司为四川航空股份有限公司\"\n" +
            "  },\n" +
            "  \"dialog_stat\": \"DataValid\",\n" +
            "  \"save_history\": true,\n" +
            "  \"sid\": \"atn000a3166@ch10200db242176f1d01\"\n" +
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
                    mData.add(userChatBean);
                    mData.add(newsBean);

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
