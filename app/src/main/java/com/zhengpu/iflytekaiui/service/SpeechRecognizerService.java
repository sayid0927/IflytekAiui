package com.zhengpu.iflytekaiui.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;

import com.blankj.utilcode.utils.ConstUtils;
import com.blankj.utilcode.utils.TimeUtils;
import com.blankj.utilcode.utils.Utils;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.zhengpu.iflytekaiui.R;
import com.zhengpu.iflytekaiui.SerialPort.OpenSerialPortListener;
import com.zhengpu.iflytekaiui.SerialPort.SerialUtils;
import com.zhengpu.iflytekaiui.base.AppController;
import com.zhengpu.iflytekaiui.iflytekaction.MoreMusicXAction;
import com.zhengpu.iflytekaiui.iflytekaction.PlayMusicxAction;
import com.zhengpu.iflytekaiui.iflytekaction.ReceivedSerialPortDataAction;
import com.zhengpu.iflytekaiui.iflytekaction.ShipingAction;
import com.zhengpu.iflytekaiui.iflytekaction.WebSearchAction;
import com.zhengpu.iflytekaiui.iflytekbean.BaseBean;
import com.zhengpu.iflytekaiui.iflytekbean.MusicXBean;
import com.zhengpu.iflytekaiui.iflytekbean.VideoBean;
import com.zhengpu.iflytekaiui.iflytekbean.WebSearchBean;
import com.zhengpu.iflytekaiui.iflytekutils.IGetVoiceToWord;
import com.zhengpu.iflytekaiui.iflytekutils.IGetWordToVoice;
import com.zhengpu.iflytekaiui.iflytekutils.IflytekWakeUp;
import com.zhengpu.iflytekaiui.iflytekutils.JsonParser;
import com.zhengpu.iflytekaiui.iflytekutils.MyWakeuperListener;
import com.zhengpu.iflytekaiui.iflytekutils.VoiceToWords;
import com.zhengpu.iflytekaiui.iflytekutils.WakeUpListener;
import com.zhengpu.iflytekaiui.iflytekutils.WordsToVoice;
import com.zhengpu.iflytekaiui.ipc.entity.RequestMessage;
import com.zhengpu.iflytekaiui.ipc.entity.SendMessage;
import com.zhengpu.iflytekaiui.thread.KuGuoMuiscPlayListener;
import com.zhengpu.iflytekaiui.thread.KuGuoMuiscPlayThread;
import com.zhengpu.iflytekaiui.utils.PreferUtil;
import com.zhengpu.iflytekaiui.utils.ValueUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import xiaofei.library.hermeseventbus.HermesEventBus;

import static com.zhengpu.iflytekaiui.utils.DeviceUtils.isAccessibilitySettingsOn;


/**
 * ....
 * Created by wengmf on 2017/11/21
 */

public class SpeechRecognizerService extends Service implements IGetVoiceToWord, WakeUpListener, IGetWordToVoice, KuGuoMuiscPlayListener, OpenSerialPortListener {

    private IflytekWakeUp iflytekWakeUp;
    private static VoiceToWords voiceToWords;
    private static WordsToVoice wordsToVoice;
    private static SerialUtils serialUtils;
    private KuGuoMuiscPlayThread kuGuoMuiscPlayThread;
    private String message;
    private String SpeechType = "0";
    private  String Message = "{\n" +
            "  \"data\": {\n" +
            "    \"result\": [\n" +
            "      {\n" +
            "        \"img\": \"\",\n" +
            "        \"name\": \"2017年圣诞节礼物推荐 圣诞节送女朋友什么礼物好? 礼意久久送礼网\",\n" +
            "        \"siteName\": \"\",\n" +
            "        \"source\": \"360so\",\n" +
            "        \"summary\": \"今年最流行、时尚、创意的圣诞节礼物,收集最火热的圣诞礼物信息,提供多种送礼方案,礼意久久送礼网都可以找到最适合的礼物!\",\n" +
            "        \"type\": \"kvdb\",\n" +
            "        \"url\": \"http://shengdan.liyi99.com/\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"img\": \"\",\n" +
            "        \"name\": \"圣诞节礼物\",\n" +
            "        \"siteName\": \"\",\n" +
            "        \"source\": \"360so\",\n" +
            "        \"summary\": \"圣诞节礼物,圣诞节(Christmas)又称耶诞节,译名为 基督弥撒 ,西方传统节日,在每年12月25日。弥撒是教会的一种礼拜仪式。圣诞节是一个宗教节,因为把它当作耶稣的诞辰来庆...\",\n" +
            "        \"type\": \"kvdb\",\n" +
            "        \"url\": \"http://m.baike.so.com/doc/3202242-3374797.html\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"img\": \"\",\n" +
            "        \"name\": \"圣诞节礼物送什么好?圣诞节送什么礼物?\",\n" +
            "        \"siteName\": \"\",\n" +
            "        \"source\": \"360so\",\n" +
            "        \"summary\": \"2016年圣诞节送什么礼物好? 适合送女朋友的18种礼物推荐  2016年圣诞节就要到了,圣诞节送什么礼物好?平安夜送女朋友什么好?送什么礼物才能讨女友欢心呢?小编整理了适...\",\n" +
            "        \"type\": \"engine\",\n" +
            "        \"url\": \"http://wenda.so.com/q/1509347835217876?src=140\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"img\": \"http://p2.qhimg.com/t01ecddd3571cd5d4a8.jpg?size=96x72&phash=1402828632112767826\",\n" +
            "        \"name\": \"圣诞节送什么礼物 圣诞礼物送什么好 太平洋亲子网\",\n" +
            "        \"siteName\": \"\",\n" +
            "        \"source\": \"360so\",\n" +
            "        \"summary\": \"浪漫的圣诞节礼物你认为是什么呢?女生追求的浪漫似乎都让男生感觉有些找不到北,感觉总是那么虚无缥缈,所以经常令一些缺乏耐心的男生觉得无所适从。其实,女人是天生爱...\",\n" +
            "        \"type\": \"kvdb\",\n" +
            "        \"url\": \"https://m.so.com/index.php?a=newTranscode&u=http%3A%2F%2Fgoods.pcbaby.com.cn%2Fnf%2Fsdjzt%2F&m=b2a929e314613542cce424705a5ac4f2bd4176c0&q=%25E5%259C%25A3%25E8%25AF%259E%25E8%258A%2582%25E7%25A4%25BC%25E7%2589%25A9\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"img\": \"\",\n" +
            "        \"name\": \"2017圣诞节给女友 男友送什么创意圣诞节礼物最好 猫咪吧\",\n" +
            "        \"siteName\": \"\",\n" +
            "        \"source\": \"360so\",\n" +
            "        \"summary\": \"人工智水晶苹果平安夜创意圣诞节礼物送女生男生男友朋友女友儿童闺蜜实用浪漫创意diy定制生日礼物小号绿色苹果信毅礼品旗舰店商品毛雅奇乐圣诞节创意礼品男生送女生...\",\n" +
            "        \"type\": \"engine\",\n" +
            "        \"url\": \"https://m.so.com/index.php?a=newTranscode&u=http%3A%2F%2Fwww.catmii.com%2F&m=4863bf27544d3852dfac56492b3e19d1b4a691ef&q=%25E5%259C%25A3%25E8%25AF%259E%25E8%258A%2582%25E7%25A4%25BC%25E7%2589%25A9\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"img\": \"\",\n" +
            "        \"name\": \"2015圣诞节礼物送什么好圣诞节礼品圣诞节送什么礼物好圣诞节送...\",\n" +
            "        \"siteName\": \"\",\n" +
            "        \"source\": \"360so\",\n" +
            "        \"summary\": \"圣诞节的礼物,礼仪之邦为您准备了数千款圣诞节礼物,保证让挑选的眼花缭乱。如果您还在为圣诞节送老公什么礼物而烦恼,您可以随时咨询我们的送礼顾问,我们时刻准备着为...\",\n" +
            "        \"type\": \"kvdb\",\n" +
            "        \"url\": \"https://m.so.com/index.php?a=newTranscode&u=http%3A%2F%2Fwww.li63.com%2Fshengdanjielipin%2F&m=d62a99c4384a9a20620669b602bbd5d32f75a49d&q=%25E5%259C%25A3%25E8%25AF%259E%25E8%258A%2582%25E7%25A4%25BC%25E7%2589%25A9\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"img\": \"http://p2.qhimg.com/t01f7fd3e94672590fd.jpg?size=748x306&phash=-1046227030113947056\",\n" +
            "        \"name\": \"圣诞节送什么礼物, 意品网:创意设计、专属定制意想不到的圣诞节礼物\",\n" +
            "        \"siteName\": \"\",\n" +
            "        \"source\": \"360so\",\n" +
            "        \"summary\": \"意品网创意节日礼物定制,送出意想不到的惊喜,圣诞节礼物,圣诞节送什么礼物,创意圣诞节礼物,尽在意品网:一件起订,快速制作发货,全国货到付款\",\n" +
            "        \"type\": \"engine\",\n" +
            "        \"url\": \"https://m.so.com/index.php?a=newTranscode&u=http%3A%2F%2Fwww.diypin.com%2Fdiy-119-b0%2F&m=0bf0f89d982464eed2012532216613716282760d&q=%25E5%259C%25A3%25E8%25AF%259E%25E8%258A%2582%25E7%25A4%25BC%25E7%2589%25A9\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"img\": \"\",\n" +
            "        \"name\": \"圣诞节礼物送什么 太平洋电脑网\",\n" +
            "        \"siteName\": \"\",\n" +
            "        \"source\": \"360so\",\n" +
            "        \"summary\": \"圣诞节礼物  12月25日圣诞节,圣诞节礼物送什么好?打算为Ta送上圣诞节礼物吗?一份有创意的圣诞节礼物,将为圣诞节增添无穷的乐趣;一份充满爱的圣诞礼物,将为节日带来浪...\",\n" +
            "        \"type\": \"engine\",\n" +
            "        \"url\": \"https://m.so.com/index.php?a=newTranscode&u=http%3A%2F%2Fwww.pconline.com.cn%2Fitbbs%2Fchristmas%2Fnr%2Fgift%2F&m=12b41af2f7c6c3c7f1d0faf2414e3a2af51caf65&q=%25E5%259C%25A3%25E8%25AF%259E%25E8%258A%2582%25E7%25A4%25BC%25E7%2589%25A9\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"img\": \"\",\n" +
            "        \"name\": \"圣诞节礼物价格 优质圣诞节礼物批发/采购商机 搜好货网\",\n" +
            "        \"siteName\": \"\",\n" +
            "        \"source\": \"360so\",\n" +
            "        \"summary\": \"搜好货网(www.912688.com)您提供各种圣诞节礼物的供求信息价格,圣诞节礼物资讯,圣诞节礼物图片,圣诞节礼物厂家等,查找更多的圣诞节礼物产品信息和供应商请上搜好货网...\",\n" +
            "        \"type\": \"engine\",\n" +
            "        \"url\": \"http://m.912688.com/chanpin/57238BDE8282793C7269.html\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"img\": \"\",\n" +
            "        \"name\": \"圣诞节礼物送什么好?圣诞节送什么礼物?最好的圣诞礼物,数千款...\",\n" +
            "        \"siteName\": \"\",\n" +
            "        \"source\": \"360so\",\n" +
            "        \"summary\": \"来我们的2012年圣诞礼物专区吧.这里圣诞节礼物应有尽有.为您献策圣诞节送什么礼物好?圣诞节送什么好快来挑选一份浪漫的圣诞礼品吧.数千款时尚新颖圣诞节礼物,免费...\",\n" +
            "        \"type\": \"engine\",\n" +
            "        \"url\": \"https://m.so.com/index.php?a=newTranscode&u=http%3A%2F%2Fwww.liyi99.com%2Fo_xmas%2F&m=26aec65895576760fd663ee855f02f03508b63ea&q=%25E5%259C%25A3%25E8%25AF%259E%25E8%258A%2582%25E7%25A4%25BC%25E7%2589%25A9\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"img\": \"\",\n" +
            "        \"name\": \"拆圣诞节礼物小游戏,4399小游戏www.4399.com\",\n" +
            "        \"siteName\": \"\",\n" +
            "        \"source\": \"360so\",\n" +
            "        \"summary\": \"4399为您提供拆圣诞节礼物在线玩,拆圣诞节礼物下载,拆圣诞节礼物攻略秘籍.更多拆圣诞节礼物游戏尽在4399小游戏,好玩记得告诉你的朋友哦!  登录注册 我玩过的 猜你喜欢 ...\",\n" +
            "        \"type\": \"engine\",\n" +
            "        \"url\": \"http://www.4399.com/flash/76901.htm\"\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  \"rc\": 0,\n" +
            "  \"semantic\": [\n" +
            "    {\n" +
            "      \"intent\": \"QUERY\",\n" +
            "      \"slots\": [\n" +
            "        {\n" +
            "          \"name\": \"channel\",\n" +
            "          \"value\": \"baidu\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"keyword\",\n" +
            "          \"value\": \"圣诞节礼物\"\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  ],\n" +
            "  \"service\": \"websearch\",\n" +
            "  \"state\": {\n" +
            "    \"fg::websearch::default::default\": {\n" +
            "      \"state\": \"default\"\n" +
            "    }\n" +
            "  },\n" +
            "  \"text\": \"淘宝搜索圣诞节礼物\",\n" +
            "  \"uuid\": \"atn003b0a52@ch03520db908df6f1d01\",\n" +
            "  \"answer\": {\n" +
            "    \"text\": \"已为您搜索到2017年圣诞节礼物推荐 圣诞节送女朋友什么礼物好? 礼意久久送礼网\"\n" +
            "  },\n" +
            "  \"dialog_stat\": \"dataInvalid\",\n" +
            "  \"save_history\": true,\n" +
            "  \"sid\": \"atn003b0a52@ch03520db908df6f1d01\"\n" +
            "}";


    @Override
    public void onCreate() {
        super.onCreate();

        if (!isAccessibilitySettingsOn(this)) {
            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        HermesEventBus.getDefault().register(SpeechRecognizerService.this);
//        Utils.init(this);
        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=5a5da161," + SpeechConstant.FORCE_LOGIN + "=true");// 传递科大讯飞appid
//        PreferUtil.getInstance().init(this);

        //初始化讯飞语音识别
        voiceToWords = VoiceToWords.getInstance(this);
        voiceToWords.setmIGetVoiceToWord(this);
        voiceToWords.setkuGuoMuiscPlayListener(this);
        wordsToVoice = WordsToVoice.getInstance(this);
        wordsToVoice.setiGetWordToVoice(this);

        iflytekWakeUp = new IflytekWakeUp(this, new MyWakeuperListener(this, this));
        iflytekWakeUp.startWakeuper();
        kuGuoMuiscPlayThread = KuGuoMuiscPlayThread.getInstance(this);
        startSpeech(AppController.LAUNCHER_TEXT, getResources().getString(R.string.launcher_text), getResources().getString(R.string.launcher_text));

        //初始化串口
        serialUtils = SerialUtils.getInstance(this);
        serialUtils.setSerialPortListener(this);

//        byte[] byteAutoReset = new byte[]{0x5A,0x50,0x03,0x02,0x02,0x01,0x05,0x0D,0x0A}; //复位
//        sendSerialMessageBytes(byteAutoReset);



        WebSearchBean webSearchBean = JsonParser.parseResultWebSearchBean(Message);
        WebSearchAction webSearchAction = new WebSearchAction("",webSearchBean,"",this);
        webSearchAction.start();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    //获取其他进程的消息 让机器人播报其他线程消息内容
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getChildAppEvent(RequestMessage requestMessage) {

        String reqService = requestMessage.getService();
        String reqMessage = requestMessage.getMessage();
        if (reqService.equals("PlayUrl")) {
            voiceToWords.mIatDestroy();
            KuGuoMuiscPlayThread.getInstance(this).playUrl(reqMessage);
        } else if (reqService.equals("SpeechStart")) {
            if (reqMessage.equals("1")) {
                voiceToWords.startRecognizer();
                startSpeech(AppController.LAUNCHER_TEXT, getResources().getString(R.string.launcher_text), getResources().getString(R.string.launcher_text));
            }
        } else {
            startSpeech(reqService, reqMessage, reqMessage);
        }
    }

    /**
     * 语音转文本回调
     */

    @Override
    public void getResult(String service, BaseBean result) {

    }

    /**
     * 用户说话声音太小回调
     */

    @Override
    public void showLowVoice(String result) {

        Long showLowVoiceTime = PreferUtil.getInstance().getShowLowVoiceTime();
        int showLowVoiceCount = PreferUtil.getInstance().getShowLowVoiceCount();

        if (TimeUtils.getTimeSpanByNow(showLowVoiceTime, ConstUtils.TimeUnit.MIN) < 2) {
            if (showLowVoiceCount == 2) {
                showLowVoiceCount = 0;
                startSpeech(AppController.SHOWLOWVOICE_TEXT, getResources().getString(R.string.showLowVoice_text), getResources().getString(R.string.showLowVoice_text));
            } else {
                voiceToWords.startRecognizer();
                showLowVoiceCount++;
                PreferUtil.getInstance().setShowLowVoiceCount(showLowVoiceCount);
            }
        } else {
            voiceToWords.startRecognizer();
            PreferUtil.getInstance().setShowLowVoiceTime(TimeUtils.getNowTimeMills());
            PreferUtil.getInstance().setShowLowVoiceCount(1);
        }
    }

    /**
     * 用户说话结束回调
     */
    @Override
    public void SpeechOver() {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setService(AppController.SPEECH_OVER);
        sendMessage.setMessage(getResources().getString(R.string.user_Speech_Over));
        HermesEventBus.getDefault().post(sendMessage);
    }

    /**
     * 用户开始说话回调
     */
    @Override
    public void SpeechStart() {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setService(AppController.SPEECH_START);
        sendMessage.setMessage(getResources().getString(R.string.user_Speech_Start));
        HermesEventBus.getDefault().post(sendMessage);
    }

    /**
     * 用户说话错误回调
     */
    @Override
    public void SpeechError(String error) {

    }

    /**
     * 机器人唤醒成功
     */
    @Override
    public void OnWakeUpSuccess() {
        if (kuGuoMuiscPlayThread.isPlay())
            kuGuoMuiscPlayThread.pause();
        startSpeech(AppController.WAKEUP_TEXT, getResources().getString(R.string.wakeup_text), getResources().getString(R.string.wakeup_text));
    }

    /**
     * 机器人唤醒失败
     */
    @Override
    public void OnWakeUpError() {

    }

    /***
     *
     * 机器人语音播报结束回调
     */
    @Override
    public void SpeechEnd(String service) {
        switch (service) {
            case AppController.SHOWLOWVOICE_TEXT:
                voiceToWords.mIatDestroy();
                break;
            case AppController.JOKE:
                voiceToWords.startRecognizer();
                break;
            case AppController.MUSICX:
                voiceToWords.mIatDestroy();
//             KuGuoMuiscPlayThread.getInstance(this).playUrl(PreferUtil.getInstance().getPlayMusicUrl());
                break;
            case AppController.NEWS:
                voiceToWords.mIatDestroy();
                KuGuoMuiscPlayThread.getInstance(this).playUrl(PreferUtil.getInstance().getPlayMusicUrl());
                break;
            case AppController.STORY:
                voiceToWords.mIatDestroy();
                KuGuoMuiscPlayThread.getInstance(this).playUrl(PreferUtil.getInstance().getPlayStoryUrl());
                break;
            case AppController.VIDEO:
                voiceToWords.mIatDestroy();
                break;
            default:
                voiceToWords.startRecognizer();
                break;
        }
    }

    /***
     *
     * 机器人语音播报错误回调
     */
    @Override
    public void SpeechError() {

    }

    /***
     *   设置机器人语音播报内容
     * @param service    语义场景
     * @param text         播报内容
     * @param request    发送给其他线程的内容
     */

    public static void startSpeech(String service, String text, String request) {

        voiceToWords.mIatDestroy();
        wordsToVoice.startSynthesizer(service, text);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setService(service);
        sendMessage.setMessage(request);
        HermesEventBus.getDefault().post(sendMessage);

    }

    @Override
    public void KuGuoMuiscPlayPause() {
        setKuGuoMuiscPlayStart(0);
    }

    @Override
    public void KuGuoMuiscPlayReplay() {
        setKuGuoMuiscPlayStart(1);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    // 播放音乐暂停、继续和停止
    private void setKuGuoMuiscPlayStart(int Type) {
        if (Type == 0) {     //暂停
            if (kuGuoMuiscPlayThread.isPlay())
                kuGuoMuiscPlayThread.pause();
        } else {                 // 继续
            kuGuoMuiscPlayThread.start();
        }
    }


    /**
     * 发送串口消息
     */
    public static void sendSerialMessageBytes(byte[] bytes) {
        serialUtils.sendContentBytes(bytes);
    }

    /**
     * 串口打开成功
     */
    @Override
    public void onOPenSerialSuccess() {

    }

    /**
     * 串口打开失败
     */
    @Override
    public void onOPenSerialFail() {

    }

    /**
     * 发送串口消息成功
     */
    @Override
    public void onDataSentSuccess() {

    }

    /**
     * 发送串口消息失败
     */
    @Override
    public void onDataSentFail() {

    }

    /**
     * 接收串口消息成功
     */
    @Override
    public void onDataReceivedSuccess(byte[] bytes) {

        String value = ValueUtil.getInstance().bytesToHexStr(bytes);
        com.orhanobut.logger.Logger.e("心跳       >>>   " + value);
        ReceivedSerialPortDataAction receivedSerialPortDataAction = new ReceivedSerialPortDataAction(value.split(" "), this);
        receivedSerialPortDataAction.start();

    }
}
