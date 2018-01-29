package com.zhengpu.app2;
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
    private final static int m_rate = 300;

    private int timeoutMilli;
    private boolean isRun;
    private long startTime;
    private int actionCode;

    public TimeJudge(int timeoutMilli, int actionCode) {
        this.timeoutMilli = timeoutMilli;
        this.actionCode = actionCode;
        startTime = System.currentTimeMillis();
        isRun = true;
    }

    public void run() {
        while (isRun) {
            try {
                Thread.sleep(m_rate);
                synchronized (this) {
                    if (System.currentTimeMillis() - startTime > timeoutMilli) {
                        isRun = false;
                        break;
                    }
                }
            } catch (Exception ioe) {
                 ioe.toString();
            }
        }
    }

    /**
     * 关闭计时器
     */

    public void close() {
        isRun = false;
    }
}
