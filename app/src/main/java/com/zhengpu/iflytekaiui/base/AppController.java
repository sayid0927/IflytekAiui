package com.zhengpu.iflytekaiui.base;

/**
 * sayid ....
 * Created by wengmf on 2017/11/22.
 */

public class AppController {


    public static boolean service_flag = false;//表示是否在一项服务中
    public static String SRResult = "";    //识别结果
    public static boolean  KuGuoplayClickabl = false;
    public  static  boolean AiQiPlayClickabl =false;
    public static boolean abj = true;
    public  static boolean b2w= true;

    public  static  boolean SearchKeyword = true;


    public  static  final  String LAUNCHER_TEXT="launcher_text";
    public  static  final  String SHOWLOWVOICE_TEXT="showLowVoice_text";
    public  static  final  String WAKEUP_TEXT="wakeup_text";
    public  static  final  String SPEECH_START="speech_start";
    public  static  final  String SPEECH_OVER="speech_over";


    public static final String BAIKE = "baike";  //互动百科词条查询。
    public static final String CALC = "calc";     //  数值计算
    public static final String DATETIME = "datetime"; // 时间查询
    public static final String FLIGHT = "flight";   //机票查询
    public static final String JOKE = "joke";         //  笑话查询
    public static final String MUSICX = "musicX";   // 音乐查询
    public static final String NEWS = "news";          // 新闻查询
    public static final String OPENAPPTEST_APP = "OPENAPPTEST.APP";
    public static final String OPENQA = "openQA";  // 打开一个APp
    public static final String POETRY = "poetry";
    public static final String STORY = "story";
    public static final String OPENAPPTEST_SHIPING = "OPENAPPTEST.shiping";
    public static final String OPENAPPTEST_MUSIC_DEMO = "OPENAPPTEST.music_demo";
    public static final String OPENAPPTEST_CUSTOM_BAIKE = "OPENAPPTEST.custom_baike";


    public static final String WEATHER = "weather";
    public static final String POINT = "point";
    public static final String R4_0 = "r4_0";
    public static final String R4_1 = "r4_1";
    public static final String R4_2 = "r4_2";


}
