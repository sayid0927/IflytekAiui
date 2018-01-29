package com.zhengpu.watch.ui.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.AppUtils;
import com.blankj.utilcode.utils.EncodeUtils;
import com.blankj.utilcode.utils.EncryptUtils;
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
import com.zhengpu.watch.iflytekbean.RobotCommandBean;
import com.zhengpu.watch.iflytekbean.StoryBean;
import com.zhengpu.watch.iflytekbean.UserChatBean;
import com.zhengpu.watch.iflytekbean.VideoBean;
import com.zhengpu.watch.iflytekbean.WeatherBean;
import com.zhengpu.watch.iflytekbean.request.AppUpdateModel;
import com.zhengpu.watch.iflytekbean.request.RobotCommandRequest;
import com.zhengpu.watch.presenter.contract.MainContract;
import com.zhengpu.watch.presenter.impl.MainActivityPresenter;
import com.zhengpu.watch.ui.adapter.HelpFragmentAdapter;
import com.zhengpu.watch.ui.adapter.TalkApadtep;
import com.zhengpu.watch.ui.adapter.TalkNewsItemLiserten;
import com.zhengpu.watch.ui.fragment.FragmentHelp_1;
import com.zhengpu.watch.ui.fragment.FragmentHelp_Home_2;
import com.zhengpu.watch.ui.view.HelpViewPager;
import com.zhengpu.watch.utils.DesBase64Util;
import com.zhengpu.watch.utils.JsonParser;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
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
    @BindView(R.id.iv_launcher)
    TextView ivLauncher;


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


    /**
     * 检测更新时，发送的key
     */
    private static final String APPPACKAGENAME = "packagename";
    /**
     * 检测更新时，发送的key
     */
    private static final String APPVERSIONCODE = "versioncode";
    /**
     * 检测更新时，发送的key
     */
    private static final String APPVERSIONNAME = "version";
    private static final String APPTYPE = "apptype";
    private static final String CHANNEL_ID = "channel_id";
    public static final String DES_KEY = "leiyonyj";
    public static final String DATA = "data";


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

        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.zhengpu.iflytekaiui",
                "com.zhengpu.iflytekaiui.service.SpeechRecognizerService"));
        // 绑定服务
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

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

//        HashMap<String, String> map = new HashMap<>();
//        JSONObject json = new JSONObject();
//        try {
//            json.put(APPPACKAGENAME, getPackageName());
//            json.put(APPVERSIONNAME,String.valueOf(AppUtils.getAppVersionCode(this)));
//            json.put(CHANNEL_ID, "");
//            json.put(APPTYPE, "0");
//            map.put(DATA, DesBase64Util.encryptDES(json.toString(), DES_KEY));
//        }catch (Exception e ){
//            e.toString();
//        }
//        mPresenter.Apk_Update(map);

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

            case "wakeup_text":   //唤醒

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

            case "video_1":  // 视频问答
                VideoBean videoBean = JsonParser.parseResultVideoBean(Message);
                if (videoBean != null && videoBean.getText() != null && videoBean.getAnswer() != null) {
                    userChatBean = new UserChatBean();
                    userChatBean.setText(videoBean.getText());
                    mData.add(userChatBean);
                    robotCommandRequest = new RobotCommandRequest();
                    robotCommandRequest.setText(videoBean.getAnswer().getText());
                    mData.add(robotCommandRequest);
                }
                break;

            case "video":  // 视频问答

//                VideoBean videoBean = JsonParser.parseResultVideoBean(Message);
//                if(videoBean!=null && videoBean.getText()!=null && videoBean.getAnswer()!=null){
//                    userChatBean = new UserChatBean();
//                    userChatBean.setText(videoBean.getText());
//                    mData.add(userChatBean);
//                    robotCommandRequest = new RobotCommandRequest();
//                    robotCommandRequest.setText(videoBean.getAnswer().getText());
//                    mData.add(robotCommandRequest);
//                }
                break;

            case "OPENAPPTEST.RobotCommand":  // 机器人指令

                RobotCommandBean robotCommandBean = JsonParser.parseResultRobotCommandBean(Message);
                if (robotCommandBean != null && robotCommandBean.getText() != null) {
                    String value = robotCommandBean.getSemantic().get(0).getSlots().get(0).getNormValue();
                    userChatBean = new UserChatBean();
                    userChatBean.setText(robotCommandBean.getText());
                    mData.add(userChatBean);
                    robotCommandRequest = new RobotCommandRequest();
                    robotCommandRequest.setText("正在" + value);
                    mData.add(robotCommandRequest);
                }
                break;

            case "showLowVoice_text":  //机器人 去休息

                robotCommandRequest = new RobotCommandRequest();
                robotCommandRequest.setText(Message);
                mData.add(robotCommandRequest);

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

    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {

        }

        public void onServiceDisconnected(ComponentName className) {

        }
    };

//
//    @Override
//    public void Apk_Update_Info(AppUpdateModel appUpdateModel) {
//        int versioncode = Integer.parseInt(appUpdateModel.getVersioncode());
//        if (AppUtils.getAppVersionCode(this)!= versioncode) {
//            //升级App
//          mPresenter.Apk_Update_Path(appUpdateModel.getApk_download_path());
//        }
//    }
//
//    @Override
//    public void Apk_Update_Path(File file) {
//        Uri uri = Uri.fromFile(file);
//        Intent install = new Intent(Intent.ACTION_VIEW);
//        install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        install.setDataAndType(uri, "application/vnd.android.package-archive");
//        this.startActivity(install);
//    }

}
