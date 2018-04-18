package com.zhengpu.iflytekaiui.utils;

import com.orhanobut.logger.Logger;

/**
 * 计时器，超时触发指定监听器
 *
 * @author luozhi
 */
public class TimeJudge extends Thread {

    /**
     * 计时单位
     */
    private final static int m_rate = 1000;


    private boolean isRun;
    private int TimeCount;

    private OnTimeActionListener onTimeActionListener;


    public void setOnTimeActionListener(OnTimeActionListener onTimeActionListener) {
        this.onTimeActionListener = onTimeActionListener;
    }

    public void run() {
        super.run();
        while (true) {
            try {
                Thread.sleep(m_rate);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (isRun) {
                try {
                    Thread.sleep(m_rate);
                    TimeCount++;
                    Logger.e(String.valueOf(TimeCount));
                    if (TimeCount == 10) {
                        if (onTimeActionListener != null) {
                            onTimeActionListener.onActionFinished();
                        }
                    }
                } catch (InterruptedException ioe) {
                    return;
                }
            }
        }
    }

    /**
     * 关闭计时器
     */
    public void close() {
        this.isRun = false;
        this.TimeCount = 0;
    }

    /***
     *   重开计时器
     */
    public void onResart() {
        this.isRun = true;
        this.TimeCount = 0;
    }
}
