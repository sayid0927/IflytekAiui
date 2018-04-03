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

    private int timeoutMilli;
    public boolean isRun;
    private long startTime;
    private  int TimeCount;

    private OnTimeActionListener onTimeActionListener;
    private int actionCode;

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
            if(TimeCount==10){
                if(onTimeActionListener!=null){
                    onTimeActionListener.onActionFinished();
                }else {
                    TimeCount=0;
                }
            }
        }
    }

    /**
     * 关闭计时器
     */
    public void close() {
        isRun = false;
    }

    /***
     *   重开计时器
     */
    public  void onResart(){
        isRun = true;
    }

    /***
     *    设置倒计时值
     * @param TimeCount
     */
    public void setTimeCount(int TimeCount){
       this.TimeCount = TimeCount;
    }

}
