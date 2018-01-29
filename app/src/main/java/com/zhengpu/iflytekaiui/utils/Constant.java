package com.zhengpu.iflytekaiui.utils;

/**
 * Created by zhangtx on 2018/1/26.
 */

public class Constant {
    public static final String UTF_8 = "UTF-8";

    public static final String KEY_EXIT = "exit";
    public static final String KEY_IPPORT_INFO = "ipport_info";

    /**
     * 最大尝试次数
     */
    public static final int DEFAULT_TRY_COUNT = 10;

    /**
     * WiFi连接成功时未分配的默认IP地址
     */
    public static final String DEFAULT_UNKNOW_IP = "0.0.0.0";

    /**
     * UDP通信服务端默认端口号
     */
    public static final int DEFAULT_SERVER_UDP_PORT = 8204;

    /**
     * 文件接收端监听默认端口号
     */
    public static final int DEFAULT_FILE_RECEIVE_SERVER_PORT = 8284;

    /**
     * UDP通知：文件接收端初始化
     */
    public static final String MSG_FILE_RECEIVER_INIT = "MSG_FILE_RECEIVER_INIT";

    /**
     * UDP通知：文件接收端初始化完毕
     */
    public static final String MSG_FILE_RECEIVER_INIT_SUCCESS = "MSG_FILE_RECEIVER_INIT_SUCCESS";

    /**
     * UDP通知：开始发送文件
     */
    public static final String MSG_START_SEND = "MSG_START_SEND";


    public static final int POOL_THREADS = 5;
}
