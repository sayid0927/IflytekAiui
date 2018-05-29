package com.zhengpu.iflytekaiui.service;

import android.app.Application;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.provider.Settings;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUtility;
import com.orhanobut.logger.Logger;
import com.zhengpu.iflytekaiui.R;
import com.zhengpu.iflytekaiui.SerialPort.OpenSerialPortListener;
import com.zhengpu.iflytekaiui.SerialPort.SerialUtils;
import com.zhengpu.iflytekaiui.base.AppController;
import com.zhengpu.iflytekaiui.base.BaseApplication;
import com.zhengpu.iflytekaiui.broadcastReceiver.HumanCheckBroadCast;
import com.zhengpu.iflytekaiui.broadcastReceiver.StratFaceserBroadCast;
import com.zhengpu.iflytekaiui.iflytekbean.BaseBean;
import com.zhengpu.iflytekaiui.iflytekbean.otherbean.HotspotRequest;
import com.zhengpu.iflytekaiui.iflytekbean.otherbean.WifiData;
import com.zhengpu.iflytekaiui.iflytekutils.IGetVoiceToWord;
import com.zhengpu.iflytekaiui.iflytekutils.IGetWordToVoice;
import com.zhengpu.iflytekaiui.iflytekutils.JsonParser;
import com.zhengpu.iflytekaiui.iflytekutils.PinyinFuzzyMatching;
import com.zhengpu.iflytekaiui.iflytekutils.VoiceToWords;
import com.zhengpu.iflytekaiui.iflytekutils.WakeUpListener;
import com.zhengpu.iflytekaiui.iflytekutils.WordsToVoice;
import com.zhengpu.iflytekaiui.ipc.entity.DanceMessage;
import com.zhengpu.iflytekaiui.ipc.entity.RequestMessage;
import com.zhengpu.iflytekaiui.ipc.entity.SendMessage;
import com.zhengpu.iflytekaiui.thread.KuGuoMuiscPlayListener;
import com.zhengpu.iflytekaiui.thread.KuGuoMuiscPlayThread;
import com.zhengpu.iflytekaiui.utils.HotsposListener;
import com.zhengpu.iflytekaiui.utils.OnTimeActionListener;
import com.zhengpu.iflytekaiui.utils.PreferUtil;
import com.zhengpu.iflytekaiui.utils.SpeechDialog;
import com.zhengpu.iflytekaiui.utils.TimeJudge;
import com.zhengpu.iflytekaiui.utils.UDPReceiveListenter;
import com.zhengpu.iflytekaiui.utils.UDPReceiveUtils;
import com.zhengpu.iflytekaiui.utils.UDPSendUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import xiaofei.library.hermeseventbus.HermesEventBus;

import static com.zhengpu.iflytekaiui.utils.DeviceUtils.isAccessibilitySettingsOn;


/**
 * ....
 * Created by wengmf on 2017/11/21
 */

public class SpeechRecognizerService extends Service implements IGetVoiceToWord, WakeUpListener, IGetWordToVoice, KuGuoMuiscPlayListener,
        OpenSerialPortListener, HotsposListener, UDPReceiveListenter, OnTimeActionListener {

    //        private IflytekWakeUp iflytekWakeUp;
    private static VoiceToWords voiceToWords;
    private static WordsToVoice wordsToVoice;
    private static SerialUtils serialUtils;
    public static int touch_V = 0;
    public static boolean isSpeech = false;
    private static KuGuoMuiscPlayThread kuGuoMuiscPlayThread;
    private String message;
    private String SpeechType = "0";
    private SpeechDialog speechDialog;
    private boolean isConnect = true;
    public static boolean FaceServiceState = false;
    private ScheduledFuture scheduledFuture;
    private static boolean isOpenCamera;
    private static TimeJudge timeJudge;
    public static int microPhone = 0;
    public static int pirV = 0;
    public static boolean isStartFacse = false;
    public static boolean isPir = true;
    public static boolean isDance = false;
    public static boolean isbodyinfrared = true;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (!isAccessibilitySettingsOn(this)) {
            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        HermesEventBus.getDefault().register(SpeechRecognizerService.this);
        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=5aeaf87f," + SpeechConstant.FORCE_LOGIN + "=true");// 传递科大讯飞appid

        //初始化讯飞语音识别
        voiceToWords = VoiceToWords.getInstance(this);
        voiceToWords.setmIGetVoiceToWord(this);
        voiceToWords.setkuGuoMuiscPlayListener(this);
        wordsToVoice = WordsToVoice.getInstance(this);
        wordsToVoice.setiGetWordToVoice(this);

//        iflytekWakeUp = new IflytekWakeUp(this, new MyWakeuperListener(this, this));
//        iflytekWakeUp.startWakeuper();

        // 启动定时器
        timeJudge = new TimeJudge();
        timeJudge.setOnTimeActionListener(this);
        timeJudge.start();

        kuGuoMuiscPlayThread = KuGuoMuiscPlayThread.getInstance(this);
        startSpeech(AppController.LAUNCHER_TEXT, getResources().getString(R.string.launcher_text), getResources().getString(R.string.launcher_text));

        //初始化串口
        serialUtils = SerialUtils.getInstance(this);
        serialUtils.setSerialPortListener(this);
        speechDialog = new SpeechDialog(this, this.getResources().getString(R.string.no_network_text));


//        byte [] bytes ={0x5A, 0x50, 0x17, 0x30, 0x04, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01, 0x00,
//                0x00, 0x01, 0x00, 0x00, 0x00, 0x21, 0x00, 0x00, 0x00, 0x00, 0x01, 0x59, 0x0D, 0x0A};

//        String value = ValueUtil.getInstance().bytesToHexStr(bytes);
//        String[] strBytes = value.split(" ");

//        SerialPortUtilsAction serialPortUtilsAction = new SerialPortUtilsAction(this,bytes,strBytes);
//        serialPortUtilsAction.start();

//        创建热点
//        HotspotUtils hotspotUtils = HotspotUtils.getHotspotUtils(this);
//        hotspotUtils.setHotsposListener(this);

        StratFaceserBroadCast stratFaceserBroadCast = new StratFaceserBroadCast();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.zeunpro.login.FaceRecognitionService");
        registerReceiver(stratFaceserBroadCast, intentFilter);

        HumanCheckBroadCast humanCheckBroadCast = new HumanCheckBroadCast();
        IntentFilter humanCheckintentFilter = new IntentFilter();
        humanCheckintentFilter.addAction("com.zp.action.bodyset");
        registerReceiver(humanCheckBroadCast, humanCheckintentFilter);


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    File file = new File("/dev/mtk-kpd");
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String line = null;
                    while ((reader.readLine()) != null) {
                        System.out.println(":::" + line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getDanceAppEvent(DanceMessage danceMessage) {
////                              dance.action
////                         0  没有播放
////                         1   播放中
        String Dancemessage = danceMessage.getMessage();
        String DanceService = danceMessage.getService();
        String DancePag = danceMessage.getPkg();

        Logger.e("获取跳舞进程的消息>>     Service  " + DanceService
                + "   Message >>  " + Dancemessage + "   DancePag >>  " + DancePag);

        if (DancePag.equals("com.zeunpro.dance")) {
            if (DanceService.equals("dance.action")) {
                if (Dancemessage.equals("1")) {
                    isDance = true;
                } else if (Dancemessage.equals("0")) {
                    isDance = false;
                    voiceToWords.startRecognizer();
                }
            }
        }
    }


    //  获取其他进程的消息 让机器人播报其他进程消息内容
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getChildAppEvent(RequestMessage requestMessage) {

        String reqService = requestMessage.getService();
        String reqMessage = requestMessage.getMessage();

        Logger.e("获取其他进程的消息>>     Service  " + reqService + "   Message >>  " + reqMessage);

        switch (reqService) {
            case "PlayUrl":
                voiceToWords.mIatDestroy();
                KuGuoMuiscPlayThread.getInstance(this).playUrl(reqMessage);
                break;
            case "SpeechStart":
                if (reqMessage.equals("1")) {
                    voiceToWords.startRecognizer();
                    startSpeech(AppController.LAUNCHER_TEXT, getResources().getString(R.string.launcher_text), getResources().getString(R.string.launcher_text));
                }
                break;
            case "AAAAA":
                startSpeech("AAAAA", reqMessage, reqMessage);
                break;
            case "state_start":
                FaceServiceState = true;
                break;
            case "state_end":
                FaceServiceState = false;
                break;
            case "ShootAction":
                if (reqMessage.equals("true")) {
                    isOpenCamera = true;
                } else {
                    isOpenCamera = false;
                }
                break;

            case "microphone":
                if (reqMessage.equals("0")) {
                    microPhone = 0;
                } else {
                    microPhone = 1;
                }
                break;

            case "get_battery_info":
                sendSerialMessageBytes(new byte[]{0x5A, 0x50, 0x05, 0x03, 0x02, 0x01, 0x00, 0x00, 0x06, 0x0D, 0x0A});
                break;

            default:
                startSpeech(reqService, reqMessage, reqMessage);
                break;
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

//        if (!wordsToVoice.isSpeaking() && !isKuGuoMuiscPlay() && microPhone == 0 && !isDance) {
//            startSpeech(AppController.SHOWLOWVOICE_TEXT, getResources().getString(R.string.showLowVoice_text), getResources().getString(R.string.showLowVoice_text));
//            sendSerialMessageBytes(new byte[]{0x5A, 0x50, 0x05, 0x02, 0x07, 0x00, 0x00, 0x00, 0x09, 0x0D, 0x0A}); //科大讯飞复位
//        } else
//            voiceToWords.mIatDestroy();

//        timeJudge.close();
//        voiceToWords.startRecognizer();
//        Long showLowVoiceTime = PreferUtil.getInstance().getShowLowVoiceTime();
//        int showLowVoiceCount = PreferUtil.getInstance().getShowLowVoiceCount();
//        if (TimeUtils.getTimeSpanByNow(showLowVoiceTime, ConstUtils.TimeUnit.MIN) < 2) {
//            if (showLowVoiceCount == 4) {
//                showLowVoiceCount = 0;
//                startSpeech(AppController.SHOWLOWVOICE_TEXT, getResources().getString(R.string.showLowVoice_text), getResources().getString(R.string.showLowVoice_text));
//            } else {
//                voiceToWords.startRecognizer();
//                showLowVoiceCount++;
//                PreferUtil.getInstance().setShowLowVoiceCount(showLowVoiceCount);
//            }
//        } else {
//            voiceToWords.startRecognizer();
//            PreferUtil.getInstance().setShowLowVoiceTime(TimeUtils.getNowTimeMills());
//            PreferUtil.getInstance().setShowLowVoiceCount(1);
//        }

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
//        speechDialog.show();
    }

    /**
     * 机器人唤醒成功
     */

    @Override
    public void OnWakeUpSuccess() {
        if (kuGuoMuiscPlayThread.isPlay())
            kuGuoMuiscPlayThread.pause();

//        startSpeech(AppController.WAKEUP_TEXT, getResources().getString(R.string.wakeup_text), getResources().getString(R.string.wakeup_text));
//        stratFaceservice(SpeechRecognizerService.this);

    }

    /**
     * 机器人唤醒失败
     */
    @Override
    public void OnWakeUpError() {

    }

    /**
     * 硬件机器人唤醒成功
     */

    public static void stratWakeUp(Context context) {
        if (microPhone == 0) {
            wordsToVoice.mTtsStop();
            if (kuGuoMuiscPlayThread.isPlay()) {
                kuGuoMuiscPlayThread.stop();
            }
            if (PreferUtil.getInstance().getfaceCheck())
                stratFaceservice(context);
            startSpeech(AppController.WAKEUP_TEXT, "多多在这里", "多多在这里");
            sendSerialMessageBytes(new byte[]{0x5A, 0x50, 0x05, 0x02, 0x07, 0x02, 0x00, 0x00, 0x0B, 0x0D, 0x0A});
//         new OpenAppAction("守护陪伴", context).start();
        }
    }

    public static void stopDanceAction() {

        DanceMessage danceMessage = new DanceMessage();
        danceMessage.setService("dance.action");
        danceMessage.setMessage("0");
        danceMessage.setPkg("com.zhengpu.iflytekaiui");
        HermesEventBus.getDefault().post(danceMessage);

    }


    public static void stratInfrared(final Context context) {
        if (microPhone == 0) {
            wordsToVoice.mTtsStop();
            if (kuGuoMuiscPlayThread.isPlay()) {
                kuGuoMuiscPlayThread.stop();
            }
            startSpeech(AppController.WAKEUP_TEXT, "欢迎光临", "欢迎光临");
//            sendSerialMessageBytes(AppController.byteWalkLeft);
//            BaseApplication.MAIN_EXECUTOR.schedule(new Runnable() {
//                @Override
//                public void run() {
//                    sendSerialMessageBytes(new byte[]{0x5A, 0x50, 0x05, 0x03, 0x01, 0x06, 0x00, 0x00, 0x0A, 0x0D, 0x0A});
//                    if (PreferUtil.getInstance().getfaceCheck())
//                        stratFaceservice(context);
//                }
//            }, 7, TimeUnit.SECONDS);
        }
    }


    /**
     * 启动人脸识别服务
     */
    public static void stratFaceservice(Context context) {
        if (!FaceServiceState && microPhone == 0) {
            try {
                Intent intent = new Intent();
                ComponentName componentName = new ComponentName("com.zeunpro", "com.zeunpro.login.FaceRecognitionService");
                intent.setComponent(componentName);
                context.startService(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isKuGuoMuiscPlay() {
        return kuGuoMuiscPlayThread.isPlay();
    }

    public static void Destroy() {
        voiceToWords.mIatDestroy();
        timeJudge.close();
    }

    /***
     *
     * 机器人语音播报结束回调
     *
     */

    @Override
    public void SpeechEnd(String service) {
        switch (service) {
            case AppController.SHOWLOWVOICE_TEXT:
                voiceToWords.mIatDestroy();
                SendMessage sendMessage = new SendMessage();
                sendMessage.setService(AppController.SPEECH_OVER);
                sendMessage.setMessage(getResources().getString(R.string.user_Speech_Over));
                HermesEventBus.getDefault().post(sendMessage);
                break;
            case AppController.JOKE:
                voiceToWords.startRecognizer();
                break;
            case AppController.LAUNCHER_TEXT:

                voiceToWords.startRecognizer();
//                BaseApplication.MAIN_EXECUTOR.scheduleWithFixedDelay(getBatteryInfo(), 0, 3, TimeUnit.SECONDS);
                break;
            case AppController.MUSICX:
                voiceToWords.mIatDestroy();
                break;
            case AppController.NEWS:
                voiceToWords.mIatDestroy();
                KuGuoMuiscPlayThread.getInstance(this).playUrl(PreferUtil.getInstance().getPlayMusicUrl());
                break;
            case AppController.STORY:
                voiceToWords.mIatDestroy();
                KuGuoMuiscPlayThread.getInstance(this).playUrl(PreferUtil.getInstance().getPlayStoryUrl());
                break;

            case AppController.RADIO:
                voiceToWords.mIatDestroy();
                KuGuoMuiscPlayThread.getInstance(this).playUrl(PreferUtil.getInstance().getPlayRadioUrl());
                break;

            case AppController.VIDEO:
                voiceToWords.mIatDestroy();
                break;

            case AppController.WAKEUP_TEXT:
                voiceToWords.startRecognizer();
//                voiceToWords.mIatDestroy();
                break;

            case AppController.STRATFACESER:
                voiceToWords.startRecognizer();
                break;
            default:
                voiceToWords.startRecognizer();
                break;
        }
        isSpeech = false;
        if (!service.equals(AppController.SHOWLOWVOICE_TEXT)) {
            timeJudge.onResart();
        }
    }

    /***
     *
     * 机器人语音播报错误回调
     */

    @Override
    public void SpeechError(SpeechError error) {
        int code = error.getErrorCode();
        if (code == 20001) {
            if (speechDialog != null && !speechDialog.isShowing()) {
                speechDialog.show();
            }
        }
        if (code == 10114) {
            if (speechDialog != null && !speechDialog.isShowing()) {
                speechDialog.show();
            }
        }
//        sendSerialMessageBytes(new byte[]{0x5A, 0x50, 0x05, 0x02, 0x07, 0x00, 0x00, 0x00, 0x09, 0x0D, 0x0A});
    }

    /***
     * 设置机器人语音播报内容
     * @param service    语义场景
     * @param text         播报内容
     * @param request    发送给其他线程的内容
     */

    public static void startSpeech(String service, String text, String request) {
        if (microPhone == 0) {
            voiceToWords.mIatDestroy();
            timeJudge.close();
            isSpeech = true;
            WordsToVoice.startSynthesizer(service, text);
            sendHermesMessage(service, request);

        }
    }

    public static boolean isIsSpeech() {
        return wordsToVoice.isSpeaking();
    }

    public static void startFaceserSpeech(String service, String text, String request) {

        if (microPhone == 0) {

            voiceToWords.mIatDestroy();
            timeJudge.close();
            isSpeech = true;
            isStartFacse = true;
            WordsToVoice.startSynthesizer(service, text);
            sendHermesMessage(service, request);

        }
    }

    public static void sendHermesMessage(String service, String request) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setService(service);
        sendMessage.setMessage(request);
        HermesEventBus.getDefault().post(sendMessage);
    }

    public static void SendShootAction(String service, String message) {
        if (isOpenCamera) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setService(service);
            sendMessage.setMessage(message);
            HermesEventBus.getDefault().post(sendMessage);
        } else {
            startSpeech("OpenCamera", "相机还没打开", "OpenCamera");
        }
    }

    @Override
    public void KuGuoMuiscPlayPause() {
        setKuGuoMuiscPlayStart(0);
    }

    @Override
    public void KuGuoMuiscPlayReplay() {
        setKuGuoMuiscPlayStart(1);
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
    }

    /***
     * 打开热点成功
     */

    @Override
    public void openHotspotSuccess() {

    }

    /***
     * 打开热点失败
     */
    @Override
    public void openHotspotError() {

    }

    /***
     * 接收到从机器人表情UDP消息
     */

    @Override
    public void UDPReceiveSuccess(String content) {
        final HotspotRequest hotspotRequest = JsonParser.parseHotspotRequest(content);

        if (hotspotRequest.getStatus() == 1) {   // 收到表情命令确认

        } else if (hotspotRequest.getMsg().equals("emoji_connect_success")) {   //双方连接成功
            scheduledFuture.cancel(false);
            PreferUtil.getInstance().setEmojiConnectState(false);

        }
    }

    /***
     *   关闭热点重连Wifi 失败
     */

    @Override
    public void connectWifiError() {

    }

    /***
     *   关闭热点重连Wifi 成功
     */

    @Override
    public void connectWifiSuccess() {

        PreferUtil.getInstance().setEmojiConnectState(true);
        BaseApplication.MAIN_EXECUTOR.schedule(UDPReceive(), 5, TimeUnit.SECONDS);
        scheduledFuture = BaseApplication.MAIN_EXECUTOR.scheduleWithFixedDelay(SendMessage(), 5, 2, TimeUnit.SECONDS);

    }

    /***
     *  注册接收UDP消息线程
     *
     */
    private Runnable UDPReceive() {
        return new Runnable() {
            @Override
            public void run() {

                UDPReceiveUtils udpReceiveUtils = UDPReceiveUtils.getUDPReceiveUtils();
                udpReceiveUtils.setUdpReceiveListenter(SpeechRecognizerService.this);
                udpReceiveUtils.start();

            }
        };
    }

    /***
     *  发送UDP消息
     */

    private Runnable SendMessage() {
        return new Runnable() {
            @Override
            public void run() {
                if (PreferUtil.getInstance().getEmojiConnectState()) {

                    WifiData wifiInfo = new WifiData();
                    wifiInfo.setMsg("pad_connect_success");
                    wifiInfo.setPassword("ZP1");
                    wifiInfo.setSsid("zeunpro123");
                    wifiInfo.setCode(1002);
                    UDPSendUtils.getInstance().sendMessage(WifiData.toJsonStr(wifiInfo));
                }
            }
        };
    }

    private Runnable getBatteryInfo() {
        return new Runnable() {
            @Override
            public void run() {
                sendSerialMessageBytes(new byte[]{0x5A, 0x50, 0x05, 0x03, 0x02, 0x01, 0x00, 0x00, 0x06, 0x0D, 0x0A});
            }
        };
    }

    /**
     * 10秒倒计时到
     */

    @Override
    public void onActionFinished() {
        if (!wordsToVoice.isSpeaking() && !isKuGuoMuiscPlay() && microPhone == 0 && !isDance) {
            startSpeech(AppController.SHOWLOWVOICE_TEXT, getResources().getString(R.string.showLowVoice_text), getResources().getString(R.string.showLowVoice_text));
            sendSerialMessageBytes(new byte[]{0x5A, 0x50, 0x05, 0x02, 0x07, 0x00, 0x00, 0x00, 0x09, 0x0D, 0x0A}); //科大讯飞复位
        } else
            voiceToWords.mIatDestroy();
        timeJudge.close();
    }
}