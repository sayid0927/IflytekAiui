package com.zhengpu.iflytekaiui.base;

/**
 * sayid ....
 * Created by wengmf on 2017/11/22.
 */

public class AppController {


    public static boolean service_flag = false;//表示是否在一项服务中
    public static String SRResult = "";    //识别结果

    public  static  final  String LAUNCHER_TEXT="launcher_text";
    public  static  final  String SHOWLOWVOICE_TEXT="showLowVoice_text";
    public  static  final  String WAKEUP_TEXT="wakeup_text";
    public  static  final  String SPEECH_START="speech_start";
    public  static  final  String SPEECH_OVER="speech_over";

    public static final String BAIKE = "baike";               //互动百科词条查询。
    public static final String CALC = "calc";                   //  数值计算
    public static final String DATETIME = "datetime"; // 时间查询
    public static final String FLIGHT = "flight";           //机票查询
    public static final String JOKE = "joke";                //  笑话查询
    public static final String MUSICX = "musicX";       // 音乐查询
    public static final String NEWS = "news";             // 新闻查询
    public static final String OPENAPPTEST_APP = "OPENAPPTEST.APP";
    public static final String OPENQA = "openQA";    // 打开一个APp
    public static final String POETRY = "poetry";       // 诗词对答
    public static final String STORY = "story";          // 故事
    public static final String OPENAPPTEST_SHIPING = "OPENAPPTEST.shiping";   // 视频
    public static final String OPENAPPTEST_MUSIC_DEMO = "OPENAPPTEST.music_demo";
    public static final String OPENAPPTEST_CUSTOM_BAIKE = "OPENAPPTEST.custom_baike";     //自定义百科
    public static final String OPENAPPTEST_ROBOTCOMMAND = "OPENAPPTEST.RobotCommand";// 机器人指令

    public static final String WEATHER = "weather";
    public static final String POINT = "point";
    public static final String R4_0 = "r4_0";
    public static final String R4_1 = "r4_1";
    public static final String R4_2 = "r4_2";


   //  发送给机器人基本指令
    public static final byte[] byteWalkForward =new byte[]{0x5A,0x50,0x03,0x02,     0x01,0x01,0x4,0x0D,0x0A} ; //前进
    public static final byte[] byteWalkBack =new byte[]{0x5A,0x50,0x03,0x02,     0x02,0x01,0x4,0x0D,0x0A} ; //后退
    public static final byte[] byteWalkLeft =new byte[]{0x5A,0x50,0x03,0x02,     0x03,0x01,0x4,0x0D,0x0A} ; //左转
    public static final byte[] byteWalkRight =new byte[]{0x5A,0x50,0x03,0x02,     0x04,0x01,0x4,0x0D,0x0A} ; //右转
    public static final byte[] byteWalkForwardLeft =new byte[]{0x5A,0x50,0x03,0x02,     0x05,0x01,0x4,0x0D,0x0A} ; //前进左转
    public static final byte[] byteWalkForwardRight =new byte[]{0x5A,0x50,0x03,0x02,     0x06,0x01,0x4,0x0D,0x0A} ; //前进右转
    public static final byte[] byteWalkBackLeft =new byte[]{0x5A,0x50,0x03,0x02,     0x07,0x01,0x4,0x0D,0x0A} ; //后退左转
    public static final byte[] byteWalkBackRight =new byte[]{0x5A,0x50,0x03,0x02,     0x08,0x01,0x4,0x0D,0x0A} ; //后退右转
    public static final byte[] byteWalkSituLeft =new byte[]{0x5A,0x50,0x03,0x02,     0x09,0x01,0x4,0x0D,0x0A} ; //原地左转
    public static final byte[] byteWalkSituRight =new byte[]{0x5A,0x50,0x03,0x02,     0x0A,0x01,0x4,0x0D,0x0A} ; //原地右转
    public static final byte[] byteHandUp =new byte[]{0x5A,0x50,0x03,0x02,     0x0B,0x01,0x4,0x0D,0x0A} ; //抬手
    public static final byte[] byteHandReset =new byte[]{0x5A,0x50,0x03,0x02,     0x0C,0x01,0x4,0x0D,0x0A} ; //手复位
    public static final byte[] byteHeadUp =new byte[]{0x5A,0x50,0x03,0x02,     0x0D,0x01,0x4,0x0D,0x0A} ; //抬头
    public static final byte[] byteHeadReset =new byte[]{0x5A,0x50,0x03,0x02,     0x0E,0x01,0x4,0x0D,0x0A} ; //头复位
    public static final byte[] byteHeadDown =new byte[]{0x5A,0x50,0x03,0x02,     0x0F,0x01,0x4,0x0D,0x0A} ; //低头
    public static final byte[] byteHeadLeft =new byte[]{0x5A,0x50,0x03,0x02,     0x10,0x01,0x4,0x0D,0x0A} ; //头左转
    public static final byte[] byteHeadRight =new byte[]{0x5A,0x50,0x03,0x02,     0x11,0x01,0x4,0x0D,0x0A} ; //头右转
    public static final byte[] byteMotorStop=new byte[]{0x5A,0x50,0x03,0x02,     0x12,0x01,0x4,0x0D,0x0A} ; //机器人停止
    public static final byte[] byteAutoCharge=new byte[]{0x5A,0x50,0x03,0x02,     0x13,0x01,0x4,0x0D,0x0A} ; //充电
    public static final byte[] byteAutoUpdata=new byte[]{0x5A,0x50,0x03,0x02,     0x14,0x01,0x4,0x0D,0x0A} ; //升级
    public static final byte[] byteAutoReset=new byte[]{0x5A,0x50,0x03,0x02,     0x15,0x01,0x4,0x0D,0x0A} ; //复位


//    体感触摸：
    public static final String TouchHead="TouchHead_service" ; //头顶
    public static final String TouchLeftFace="TouchLeftFace_service" ; //左脸
    public static final String TouchRightFace="TouchRightFace_service"; //右脸
    public static final String TouchLeftHand= "TouchLeftHand_service"; //左手
    public static final String TouchRightHand="TouchRightHand_service" ; //右手
    public static final String TouchFront= "TouchFront_service"; //前胸
    public static final String TouchBack="TouchBack_service" ; //前胸


    public static final  String WalkForward ="前进";
    public static final  String WalkBack ="后退";
    public static final  String WalkLeft ="左转";
    public static final  String WalkRight ="右转";
    public static final  String WalkForwardLeft ="前进左转";
    public static final  String WalkForwardRight ="前进右转";
    public static final  String WalkBackLeft ="后退左转";
    public static final  String WalkBackRight ="后退右转";
    public static final  String WalkSituLeft ="原地左转";
    public static final  String WalkSituRight ="原地右转";
    public static final  String HandUp ="抬手";
    public static final  String HandReset ="手复位";
    public static final  String HeadUp ="抬头";
    public static final  String HeadReset ="头复位";
    public static final  String HeadDown ="低头";
    public static final  String HeadLeft ="头左转";
    public static final  String HeadRight ="头右转";
    public static final  String MotorStop ="机器人停止";
    public static final  String AutoCharge ="充电";
    public static final  String AutoUpdata ="升级";
    public static final  String AutoReset ="复位";




}
