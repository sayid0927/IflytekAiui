package com.zhengpu.watch.ui.activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.orhanobut.logger.Logger;
import com.skyfishjy.library.RippleBackground;
import com.zhengpu.iflytekaiui.ipc.entity.SendMessage;
import com.zhengpu.watch.R;
import com.zhengpu.watch.base.BaseActivity;
import com.zhengpu.watch.component.AppComponent;
import com.zhengpu.watch.component.DaggerMainComponent;
import com.zhengpu.watch.iflytekbean.BaseBean;
import com.zhengpu.watch.iflytekbean.CalcBean;
import com.zhengpu.watch.iflytekbean.DatetimeBean;
import com.zhengpu.watch.iflytekbean.OpenQABean;
import com.zhengpu.watch.iflytekbean.PoetryBean;
import com.zhengpu.watch.iflytekbean.R4Bean;
import com.zhengpu.watch.iflytekbean.UserChatBean;
import com.zhengpu.watch.iflytekbean.WeatherBean;
import com.zhengpu.watch.iflytekbean.request.RobotCommandRequest;
import com.zhengpu.watch.presenter.contract.MainContract;
import com.zhengpu.watch.presenter.impl.MainActivityPresenter;
import com.zhengpu.watch.ui.adapter.HelpFragmentAdapter;
import com.zhengpu.watch.ui.adapter.TalkApadtep;
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


public class MainActivity extends BaseActivity implements MainContract.View {

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

//    UmengUtil.onEvent(MainActivity.this, "MainActivity", null);
        mainActivity = this;

        HermesEventBus.getDefault().register(this);


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

            case "poetry"://诗词问答
                PoetryBean poetryBean = JsonParser.parseResultPoetryBean(Message);
                if (poetryBean != null && poetryBean.getText() != null) {

                    userChatBean = new UserChatBean();
                    userChatBean.setText(poetryBean.getText());
                    mData.add(userChatBean);
                    mData.add(poetryBean);

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

            case "weather"://天气问答
                WeatherBean weatherBean = JsonParser.parseResultWeatherBean(Message);
                if (weatherBean != null && weatherBean.getText() != null) {
                    userChatBean = new UserChatBean();
                    userChatBean.setText(weatherBean.getText());
                    mData.add(userChatBean);
                    mData.add(weatherBean);

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

}