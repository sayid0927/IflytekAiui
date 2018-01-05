package com.zhengpu.watch.ui.fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.zhengpu.watch.R;
import com.zhengpu.watch.base.BaseFragment;
import com.zhengpu.watch.component.AppComponent;
import com.zhengpu.watch.ui.activity.MainActivity;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * sayid ....
 * Created by wengmf on 2017/12/8.
 */

public class FragmentHelp_Home_2 extends BaseFragment {


    @BindView(R.id.rl_music)
    RelativeLayout rlMusic;
    @BindView(R.id.rl_weatch)
    RelativeLayout rlWeatch;
    @BindView(R.id.rl_search)
    RelativeLayout rlSearch;
    @BindView(R.id.rl_nearby)
    RelativeLayout rlNearby;
    @BindView(R.id.rl_story)
    RelativeLayout rlStory;

    @BindView(R.id.fragment_help_2)
    LinearLayout fragmentHelp2;

    @BindView(R.id.fragment_help_music)
    RelativeLayout fragmentHelpMusic;
    @BindView(R.id.fragment_help_weatch)
    RelativeLayout fragmentHelpWeatch;
    @BindView(R.id.fragment_help_web_search)
    RelativeLayout fragmentHelpWebSearch;
    @BindView(R.id.fragment_help_nearby)
    RelativeLayout fragmentHelpNearby;
    @BindView(R.id.fragment_help_story)
    RelativeLayout fragmentHelpStory;
    Unbinder unbinder;

    private int visibilityType = 0;
    public static FragmentHelp_Home_2 fragmentHelpHome2;


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_help_home_2;
    }

    @Override
    public void attachView() {
        fragmentHelpHome2 = this;

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @OnClick({R.id.rl_music, R.id.rl_weatch, R.id.rl_search, R.id.rl_nearby, R.id.rl_story})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_music:

                if (fragmentHelp2.getVisibility() == View.VISIBLE && fragmentHelpMusic.getVisibility() == View.GONE) {
                    fragmentHelp2.setVisibility(View.GONE);
                    fragmentHelpMusic.setVisibility(View.VISIBLE);
                    MainActivity.mainActivity.setScanScroll(false);
                }
                visibilityType = 1;
                break;

            case R.id.rl_weatch:

                if (fragmentHelp2.getVisibility() == View.VISIBLE && fragmentHelpWeatch.getVisibility() == View.GONE) {
                    fragmentHelp2.setVisibility(View.GONE);
                    fragmentHelpWeatch.setVisibility(View.VISIBLE);
                    MainActivity.mainActivity.setScanScroll(false);
                }
                visibilityType = 2;
                break;

            case R.id.rl_search:

                if (fragmentHelp2.getVisibility() == View.VISIBLE && fragmentHelpWebSearch.getVisibility() == View.GONE) {
                    fragmentHelp2.setVisibility(View.GONE);
                    fragmentHelpWebSearch.setVisibility(View.VISIBLE);
                    MainActivity.mainActivity.setScanScroll(false);

                }
                visibilityType = 3;
                break;

            case R.id.rl_nearby:

                if (fragmentHelp2.getVisibility() == View.VISIBLE && fragmentHelpNearby.getVisibility() == View.GONE) {
                    fragmentHelp2.setVisibility(View.GONE);
                    fragmentHelpNearby.setVisibility(View.VISIBLE);
                    MainActivity.mainActivity.setScanScroll(false);
                }
                visibilityType = 4;
                break;

            case R.id.rl_story:

                if (fragmentHelp2.getVisibility() == View.VISIBLE && fragmentHelpStory.getVisibility() == View.GONE) {
                    fragmentHelp2.setVisibility(View.GONE);
                    fragmentHelpStory.setVisibility(View.VISIBLE);
                    MainActivity.mainActivity.setScanScroll(false);
                }
                visibilityType = 5;

                break;
        }
    }


    public void setvisibilityStatr() {
        switch (visibilityType) {

            case 1:

                if (fragmentHelp2.getVisibility() == View.GONE && fragmentHelpMusic.getVisibility() == View.VISIBLE) {
                    fragmentHelp2.setVisibility(View.VISIBLE);
                    fragmentHelpMusic.setVisibility(View.GONE);
                    MainActivity.mainActivity.setScanScroll(true);
                }
                visibilityType = 0;

                break;

            case 2:

                if (fragmentHelp2.getVisibility() == View.GONE && fragmentHelpWeatch.getVisibility() == View.VISIBLE) {
                    fragmentHelp2.setVisibility(View.VISIBLE);
                    fragmentHelpWeatch.setVisibility(View.GONE);
                    MainActivity.mainActivity.setScanScroll(true);
                }
                visibilityType = 0;

                break;

            case 3:

                if (fragmentHelp2.getVisibility() == View.GONE && fragmentHelpWebSearch.getVisibility() == View.VISIBLE) {
                    fragmentHelp2.setVisibility(View.VISIBLE);
                    fragmentHelpWebSearch.setVisibility(View.GONE);
                    MainActivity.mainActivity.setScanScroll(true);
                }
                visibilityType = 0;

                break;

            case 4:

                if (fragmentHelp2.getVisibility() == View.GONE && fragmentHelpNearby.getVisibility() == View.VISIBLE) {
                    fragmentHelp2.setVisibility(View.VISIBLE);
                    fragmentHelpNearby.setVisibility(View.GONE);
                    MainActivity.mainActivity.setScanScroll(true);
                }
                visibilityType = 0;

                break;

            case 5:

                if (fragmentHelp2.getVisibility() == View.GONE && fragmentHelpStory.getVisibility() == View.VISIBLE) {
                    fragmentHelp2.setVisibility(View.VISIBLE);
                    fragmentHelpStory.setVisibility(View.GONE);
                    MainActivity.mainActivity.setScanScroll(true);
                }
                visibilityType = 0;

                break;
        }
    }

    public int getvisibilityType() {
        return visibilityType;
    }

    public void setvisibilityType(int visibilityType) {
        this.visibilityType = visibilityType;
    }

}
