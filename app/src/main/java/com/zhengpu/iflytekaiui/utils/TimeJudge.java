package com.zhengpu.iflytekaiui.utils;

import com.orhanobut.logger.Logger;

/**
 * 计时器，超时触发指定监听器
 *
 * @author luozhi
 */
public class TimeJudge extends Thread {
    private final static String TAG = "TimeJudge";

    /**
     * 计时单位
     */
    private final static int m_rate = 1000;


    public boolean isRun;
    private  int TimeCount;

    private OnTimeActionListener onTimeActionListener;


    public TimeJudge() {
        isRun = true;
    }

    public  void  setOnTimeActionListener(OnTimeActionListener onTimeActionListener){
        this.onTimeActionListener =  onTimeActionListener;
    }

    public void run() {
        while (isRun) {
            try {
                Thread.sleep(m_rate);
                TimeCount++;
                Logger.e(String.valueOf(TimeCount));
            } catch (InterruptedException ioe) {
                continue;
            }
            if(TimeCount==12){
                if(onTimeActionListener!=null){
                    onTimeActionListener.onActionFinished();
                }
            }
        }
    }

    /**
     * 关闭计时器
     */
    public void close() {
       this.isRun = false;
    }

    /***
     *   重开计时器
     */
    public  void onResart(){
     this.isRun = true;
      this.TimeCount=0;
      this.run();
    }

}
