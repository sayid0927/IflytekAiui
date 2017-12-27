package com.zhengpu.iflytekaiui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.utils.ConstUtils;
import com.blankj.utilcode.utils.TimeUtils;
import com.orhanobut.logger.Logger;
import com.zhengpu.iflytekaiui.utils.PreferUtil;


/**
 * Created by Administrator on 2017/12/23 0023.
 */

public class MainActivity extends Activity {

    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PreferUtil.getInstance().init(this);
        button = (Button) this.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                long beTime = PreferUtil.getInstance().getR4SpaceTime(); // 上次的时间戳
                long nowTime = TimeUtils.getNowTimeMills(); //当前的时间戳
                long dd = TimeUtils.getTimeSpanByNow(beTime, ConstUtils.TimeUnit.MIN);
                if(TimeUtils.getTimeSpanByNow(beTime, ConstUtils.TimeUnit.MIN)>1) {
                    int spCount = PreferUtil.getInstance().getR4SpaceCount();
                    switch (spCount) {
                        case 0:
                            Logger.e("0000000");
                            break;
                        case 1:
                            Logger.e("1111111111");
                            break;
                        case 2:
                            Logger.e("2222222");
                            break;
                    }
                    spCount++;
                    if(spCount==3)spCount=0;
                    PreferUtil.getInstance().setR4SpaceTime(nowTime);
                    PreferUtil.getInstance().setR4SpaceCount(spCount);
                }else {
                    Logger.e("0000000");
                    PreferUtil.getInstance().setR4SpaceTime(nowTime);
                    PreferUtil.getInstance().setR4SpaceCount(1);
                }
            }
        });
    }
}
