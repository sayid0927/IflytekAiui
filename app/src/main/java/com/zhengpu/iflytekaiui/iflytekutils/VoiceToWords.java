package com.zhengpu.iflytekaiui.iflytekutils;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechEvent;
import com.iflytek.cloud.SpeechUnderstander;
import com.iflytek.cloud.SpeechUnderstanderListener;
import com.iflytek.cloud.UnderstanderResult;
import com.orhanobut.logger.Logger;
import com.zhengpu.iflytekaiui.R;
import com.zhengpu.iflytekaiui.base.AppController;
import com.zhengpu.iflytekaiui.iflytekaction.BaikeAction;
import com.zhengpu.iflytekaiui.iflytekaction.CalcAction;
import com.zhengpu.iflytekaiui.iflytekaction.CustomBaikeAction;
import com.zhengpu.iflytekaiui.iflytekaction.DanceAction;
import com.zhengpu.iflytekaiui.iflytekaction.DateTimeAction;
import com.zhengpu.iflytekaiui.iflytekaction.FaceserviceAction;
import com.zhengpu.iflytekaiui.iflytekaction.FlightAction;
import com.zhengpu.iflytekaiui.iflytekaction.IflyJokeAction;
import com.zhengpu.iflytekaiui.iflytekaction.JokeAction;
import com.zhengpu.iflytekaiui.iflytekaction.MoreMusicXAction;
import com.zhengpu.iflytekaiui.iflytekaction.NewsAction;
import com.zhengpu.iflytekaiui.iflytekaction.OpenAppAction;
import com.zhengpu.iflytekaiui.iflytekaction.OpenCameraAction;
import com.zhengpu.iflytekaiui.iflytekaction.OpenQaAction;
import com.zhengpu.iflytekaiui.iflytekaction.OpenVideoAction;
import com.zhengpu.iflytekaiui.iflytekaction.OpenimAction;
import com.zhengpu.iflytekaiui.iflytekaction.PlayMusicxAction;
import com.zhengpu.iflytekaiui.iflytekaction.PoetryAction;
import com.zhengpu.iflytekaiui.iflytekaction.R4Action;
import com.zhengpu.iflytekaiui.iflytekaction.RobotCommandAction;
import com.zhengpu.iflytekaiui.iflytekaction.ShipingAction;
import com.zhengpu.iflytekaiui.iflytekaction.ShootAction;
import com.zhengpu.iflytekaiui.iflytekaction.StoryAction;
import com.zhengpu.iflytekaiui.iflytekaction.TelephoneAction;
import com.zhengpu.iflytekaiui.iflytekaction.VideoCammandAction;
import com.zhengpu.iflytekaiui.iflytekaction.WeatherAction;
import com.zhengpu.iflytekaiui.iflytekaction.WebSearchAction;
import com.zhengpu.iflytekaiui.iflytekbean.BaikeBean;
import com.zhengpu.iflytekaiui.iflytekbean.BaseBean;
import com.zhengpu.iflytekaiui.iflytekbean.CalcBean;
import com.zhengpu.iflytekaiui.iflytekbean.CustomBaikeBean;
import com.zhengpu.iflytekaiui.iflytekbean.DanceBean;
import com.zhengpu.iflytekaiui.iflytekbean.DatetimeBean;
import com.zhengpu.iflytekaiui.iflytekbean.FaceserviceBean;
import com.zhengpu.iflytekaiui.iflytekbean.FlightBean;
import com.zhengpu.iflytekaiui.iflytekbean.IflyJokeBean;
import com.zhengpu.iflytekaiui.iflytekbean.JokeBean;
import com.zhengpu.iflytekaiui.iflytekbean.MusicXBean;
import com.zhengpu.iflytekaiui.iflytekbean.NewsBean;
import com.zhengpu.iflytekaiui.iflytekbean.OpenAppBean;
import com.zhengpu.iflytekaiui.iflytekbean.OpenCameraBean;
import com.zhengpu.iflytekaiui.iflytekbean.OpenQABean;
import com.zhengpu.iflytekaiui.iflytekbean.OpenVideoBean;
import com.zhengpu.iflytekaiui.iflytekbean.OpenimBean;
import com.zhengpu.iflytekaiui.iflytekbean.PoetryBean;
import com.zhengpu.iflytekaiui.iflytekbean.R4Bean;
import com.zhengpu.iflytekaiui.iflytekbean.RobotCommandBean;
import com.zhengpu.iflytekaiui.iflytekbean.ShootBean;
import com.zhengpu.iflytekaiui.iflytekbean.StoryBean;
import com.zhengpu.iflytekaiui.iflytekbean.TelephoneBean;
import com.zhengpu.iflytekaiui.iflytekbean.VideoBean;
import com.zhengpu.iflytekaiui.iflytekbean.VideoCammandBean;
import com.zhengpu.iflytekaiui.iflytekbean.WeatherBean;
import com.zhengpu.iflytekaiui.iflytekbean.WebSearchBean;
import com.zhengpu.iflytekaiui.iflytekbean.otherbean.CustomMusicBean;
import com.zhengpu.iflytekaiui.ipc.entity.SendMessage;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;
import com.zhengpu.iflytekaiui.thread.KuGuoMuiscPlayListener;
import com.zhengpu.iflytekaiui.utils.PreferUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;

import xiaofei.library.hermeseventbus.HermesEventBus;


/**
 * sayid ....
 * Created by wengmf on 2017/11/22.
 */

public class VoiceToWords {

    // 语音听写对象
//    private SpeechRecognizer mIat;
    public static SpeechUnderstander mIat;

    // 用HashMap存储听写结果
    private HashMap<String, String> mIatResults = new LinkedHashMap<String, String>();

    private Context context;
    // 函数调用返回值,表示返回结果，失败或成功
    //设置回调接口
    private IGetVoiceToWord mIGetVoiceToWord;
    private KuGuoMuiscPlayListener kuGuoMuiscPlayListener;
    public static VoiceToWords voiceToWords;

    /**
     * 构造方法
     */
    public VoiceToWords(Context context) {
        this.context = context;
        mIat = SpeechUnderstander.createUnderstander(context, mInitListener);
        //设置参数
        setParams();

    }

    public static synchronized VoiceToWords getInstance(Context context) {
        if (voiceToWords == null)
            voiceToWords = new VoiceToWords(context);
        return voiceToWords;
    }

    public void setmIGetVoiceToWord(IGetVoiceToWord mIGetVoiceToWord) {
        this.mIGetVoiceToWord = mIGetVoiceToWord;
    }

    public void setkuGuoMuiscPlayListener(KuGuoMuiscPlayListener kuGuoMuiscPlayListener) {
        this.kuGuoMuiscPlayListener = kuGuoMuiscPlayListener;
    }

    /**
     * 初始化监听器
     */
    private static InitListener mInitListener = new InitListener() {

        @Override
        public void onInit(int code) {
            if (code != ErrorCode.SUCCESS) {
                Logger.e("初始化失败，错误码：" + code);
            }
        }
    };

    /**
     * 开始语音听写
     */

    public void startRecognizer() {
        //清空听写结果
        mIatResults.clear();
        if (mIat.startUnderstanding(mSpeechUnderstanderListener) != ErrorCode.SUCCESS)
            Logger.e("听写失败,错误码：" + mIat.startUnderstanding(mSpeechUnderstanderListener));

    }

    /**
     * 为听写对象设置参数
     */
    private void setParams() {
        //默认不设置的语音参数
        //SpeechConstant.NET_TIMEOUT:网络连接超时时间 默认20秒
        //SpeechConstant.KEY_SPEECH_TIMEOUT:语音输入超时时间  默认60秒
        //mIat.setParameter(SpeechConstant.SAMPLE_RATE, "16000") 默认的识别采样率支持16000Hz和8000Hz
        // 清空参数
//    mIat.setParameter(SpeechConstant.NET_TIMEOUT,"30000");
        mIat.setParameter(SpeechConstant.PARAMS, null);
        //设置语音输入超时时间
        mIat.setParameter(SpeechConstant.KEY_SPEECH_TIMEOUT, "10000");
        mIat.setParameter(SpeechConstant.SAMPLE_RATE, "16000");
        // 设置听写引擎
        mIat.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
        // 设置返回结果格式
        mIat.setParameter(SpeechConstant.RESULT_TYPE, "json");
        //获得当前系统的语言
        String language = context.getResources().getConfiguration().locale.getCountry();
        // 设置语言
        if (language.equals("US")) {
            // 设置语言
            mIat.setParameter(SpeechConstant.LANGUAGE, "en_us");
        } else {
            // 设置语言
            mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
            // 设置语言区域
            mIat.setParameter(SpeechConstant.ACCENT, "mandarin");
        }
        //应用领域
//        mIat.setParameter(SpeechConstant.DOMAIN, "iat");

        // 传入音频源。
        //录音机的录音方式，默认为MIC(MediaRecorder.AudioSource.MIC)
        // 设置音频来源为外部文件
        //mIat.setParameter(SpeechConstant.AUDIO_SOURCE, "-1");
        // 也可以像以下这样直接设置音频文件路径识别（要求设置文件在sdcard上的全路径）：
        // mIat.setParameter(SpeechConstant.AUDIO_SOURCE, "-2");
        // mIat.setParameter(SpeechConstant.ASR_SOURCE_PATH, "sdcard/XXX/XXX.pcm");
        mIat.setParameter(SpeechConstant.VAD_ENABLE, "1");
        // 设置语音前端点:静音超时时间，即用户多长时间不说话则当做超时处理
        mIat.setParameter(SpeechConstant.VAD_BOS, "1000");
        // 设置语音后端点:后端点静音检测时间，即用户停止说话多长时间内即认为不再输入， 自动停止录音
        mIat.setParameter(SpeechConstant.VAD_EOS, "1000");
        // 设置标点符号,设置为"0"返回结果无标点,设置为"1"返回结果有标点
        mIat.setParameter(SpeechConstant.ASR_PTT, "0");
        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        // 注：AUDIO_FORMAT参数语记需要更新版本才能生效
        mIat.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");
        mIat.setParameter(SpeechConstant.ASR_AUDIO_PATH, Environment.getExternalStorageDirectory() + "/abcd/iat.wav");
        // 设置返回结果格式
        mIat.setParameter(SpeechConstant.RESULT_TYPE, "json");

    }

    public boolean isIatListening() {
        return mIat.isUnderstanding();
    }


    //停止录音，开始解析
    public static void mTtsStop() {
        // 退出时释放连接
        if (mIat != null && mIat.isUnderstanding()) {
            mIat.stopUnderstanding();
        }
    }

    public void mIatDestroy() {

        // 退出时释放连接
        if (mIat != null) {
            if (mIat.isUnderstanding()) {
                mIat.cancel();
            }
            mIat.destroy();
        }
    }

    /**
     * 语义理解回调。
     */
    private SpeechUnderstanderListener mSpeechUnderstanderListener = new SpeechUnderstanderListener() {

        @Override
        public void onResult(final UnderstanderResult result) {
            if (null != result) {
                Logger.e(result.getResultString());
                String text = result.getResultString();
                JSONObject jsonObject2;
                String service = "";
                try {
                    jsonObject2 = new JSONObject(text);
                    if (jsonObject2.has("service")) {
                        service = jsonObject2.getString("service");
                    }
                    int rc = jsonObject2.getInt("rc");
                    if (service != "" && rc != 4) {
                        judgeService(service, text);
                    } else {
                        R4Action r4Action = new R4Action(context, text);
                        r4Action.start();
                    }
                } catch (JSONException e) {
                    R4Action r4Action = new R4Action(context, text);
                    r4Action.start();
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onVolumeChanged(int volume, byte[] data) {
            //showTip("当前正在说话，音量大小：" + volume);
            //Log.d(TAG, "返回音频数据：" + data.length);
//            Logger.e("返回音频数据：" + data.length);
        }

        @Override
        public void onEndOfSpeech() {
            // 此回调表示：检测到了语音的尾端点，已经进入识别过程，不再接受语音输入
            //showTip("结束说话");
//            Logger.e("结束说话");
            if (mIGetVoiceToWord != null) {
                mIGetVoiceToWord.SpeechOver();
            }
        }

        @Override
        public void onBeginOfSpeech() {
            // 此回调表示：检测到了语音的前端点，已经进入识别
//            Logger.e("开始说话");
            if (mIGetVoiceToWord != null)
                mIGetVoiceToWord.SpeechStart();
        }

        @Override
        public void onError(SpeechError error) {
            // Tips：
            // 错误码：10118(您没有说话)，可能是录音机权限被禁，需要提示用户打开应用的录音权限。
            // 如果使用本地功能（语记）需要提示用户开启语记的录音权限。
            // showTip(error.getPlainDescription(true));
            if ("您好像没有说话哦.(错误码:10118)".equals(error.getPlainDescription(true))) {
                String lowVoiceTip = "";
                if (mIGetVoiceToWord != null)
                    mIGetVoiceToWord.showLowVoice(lowVoiceTip);
            }
        }

        @Override
        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
            // 以下代码用于获取与云端的会话id，当业务出错时将会话id提供给技术支持人员，可用于查询会话日志，定位出错原因
            // 若使用本地能力，会话id为null
//            	if (SpeechEvent.EVENT_SESSION_ID == eventType) {

//            		String sid = obj.getString(SpeechEvent.KEY_EVENT_SESSION_ID);
//            		Logger.e("session id =" + sid);
////            	Log.d(TAG, "session id =" + sid);

//            	}
        }
    };


    //语义场景判断
    private void judgeService(String service, String text) {

        if (!AppController.service_flag) { //如果不在一项服务中才进行服务的判断

            switch (service) {
                case AppController.BAIKE:      //互动百科词条查询。

                    BaikeBean baikeBean = JsonParser.parseResultBaikeBean(text);
                    BaikeAction baikeAction = new BaikeAction(service, baikeBean, text, context);
                    baikeAction.start();

                    break;

//                case AppController.OPENAPPTEST_CUSTOM_BAIKE: // 自定义百科 对人名 地名进行百科
//                    CustomBaikeBean customBaikeBean = JsonParser.parseResultCustomBaikeBean(text);
//                    CustomBaikeAction customBaikeAction = new CustomBaikeAction(service, customBaikeBean, text, context);
//                    customBaikeAction.start();
//                    break;

                case AppController.CALC:     //  数值计算问答

                    CalcBean calcBean = JsonParser.parseResultCalc(text);
                    CalcAction calcAction = new CalcAction(service, calcBean, text, context);
                    calcAction.start();

                    break;

                case AppController.DATETIME:    //  时间、日期的查询。

                    DatetimeBean datetimeBean = JsonParser.parseResultDatetimeBean(text);
                    DateTimeAction dateTimeAction = new DateTimeAction(service, datetimeBean, text, context);
                    dateTimeAction.start();

                    break;

                case AppController.FLIGHT:       //飞机票、航班信息的查询及订购。

                    FlightBean flightBean = JsonParser.parseResultFlightoBean(text);
                    FlightAction flightAction = new FlightAction(service, flightBean, text, context);
                    flightAction.start();

                    break;

                case AppController.JOKE:  // 笑话的点播

                    IflyJokeBean iflyJokeBean = JsonParser.parseResultIflyJokeBean(text);
                    IflyJokeAction iflyJokeAction = new IflyJokeAction(service, iflyJokeBean, text, context);
                    iflyJokeAction.start();

//                    JokeBean jokeBean = JsonParser.parseResultJokeBean(text);
//                    JokeAction jokeAction = new JokeAction(service, jokeBean, text, context);
//                    jokeAction.start();

                    break;

                case AppController.MUSICX:   //   音乐的搜索和播放

                    MusicXBean musicXBean = JsonParser.parseResultMusicXBean(text);
                    PlayMusicxAction playMusicxAction = new PlayMusicxAction(service, musicXBean, text, context);
                    playMusicxAction.start();
                    break;

                case AppController.NEWS:   //  新闻的搜索和点播

                    NewsBean newsBean = JsonParser.parseResultNewsBean(text);
                    NewsAction newsAction = new NewsAction(service, newsBean, text, context);
                    newsAction.start();

                    break;

                case AppController.WEBSEARCH:   //  网络搜索

                    WebSearchBean webSearchBean = JsonParser.parseResultWebSearchBean(text);
                    WebSearchAction webSearchAction = new WebSearchAction(service, webSearchBean, text, context);
                    webSearchAction.start();

                    break;

                case AppController.OPENAPPTEST_APP:    //打开App

                    OpenAppBean openAppBean = JsonParser.parseResultOpenAppBean(text);
                    if (openAppBean != null && openAppBean.getSemantic() != null && openAppBean.getSemantic().size() != 0 &&
                            openAppBean.getSemantic().get(0).getSlots() != null && openAppBean.getSemantic().get(0).getSlots().size() != 0) {

                        String appName = openAppBean.getSemantic().get(0).getSlots().get(0).getNormValue();
                        OpenAppAction openAppAction = new OpenAppAction(appName, context);
                        openAppAction.start();

                    } else {
                        R4Action r4Action = new R4Action(context, text);
                        r4Action.start();
                    }

                    break;

                case AppController.OPENAPPTEST_OPENCAMERA:      //打开相机
                    OpenCameraBean openCameraBean = JsonParser.parseResultOpenCameraBean(text);
                    OpenCameraAction openCameraAction = new OpenCameraAction(service, context);
                    openCameraAction.start();
                    break;

                case AppController.OPENAPPTEST_EXITCAMERA:  // 退出相机

                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setService("exitcamera");
                    sendMessage.setMessage("true");
                    HermesEventBus.getDefault().post(sendMessage);

                    break;

                case AppController.OPENAPPTEST_SHOOT:      //声控拍摄相机
                    ShootBean shootBean = JsonParser.parseResultShootBean(text);
                    ShootAction shootAction = new ShootAction(service, context);
                    shootAction.start();
                    break;

                case AppController.OPENAPPTEST_OPENVIDEO:      //打开录像机
                    OpenVideoBean openVideoBean = JsonParser.parseResultOpenVideoBean(text);
                    OpenVideoAction openVideoAction = new OpenVideoAction(service, context);
                    openVideoAction.start();
                    break;

                case AppController.OPENAPPTEST_VIDEO_ACTION:      //声控录像机
                    VideoCammandBean videoCammandBean = JsonParser.parseResultVideoCammandBean(text);
                    VideoCammandAction videoCammandAction = new VideoCammandAction(videoCammandBean, context);
                    videoCammandAction.start();
                    break;

                case AppController.OPENQA:   //     开放问答

                    OpenQABean openQABean = JsonParser.parseResultOpenQABean(text);
                    OpenQaAction openQaAction = new OpenQaAction(service, openQABean, text, context);
                    openQaAction.start();

                    break;

                case AppController.POETRY:       //诗词查询和诗句对答

                    PoetryBean poetryBean = JsonParser.parseResultPoetryBean(text);
                    PoetryAction poetryAction = new PoetryAction(service, poetryBean, text, context);
                    poetryAction.start();

                    break;

                case AppController.STORY:  //     故事的点播

                    StoryBean storyBean = JsonParser.parseResultStoryBean(text);
                    StoryAction storyAction = new StoryAction(service, storyBean, text, context);
                    storyAction.start();

                    break;

                case AppController.VIDEO: //   视频的搜索和播放

                    VideoBean videoBean = JsonParser.parseResultVideoBean(text);
                    if (videoBean.getMoreResults() != null) {
                        String ser = videoBean.getMoreResults().get(0).getService();
                        if (ser.equals("musicX")) {   //如果在更多的选项中有音乐
                            MoreMusicXAction moreMusicXAction = new MoreMusicXAction("musicX", videoBean.getMoreResults().get(0), text, context);
                            moreMusicXAction.start();
                        } else {
                            ShipingAction shipingAction = new ShipingAction(service, videoBean, context, text);
                            shipingAction.start();
                        }
                    } else {
                        ShipingAction shipingAction = new ShipingAction(service, videoBean, context, text);
                        shipingAction.start();
                    }
                    break;

                case AppController.WEATHER:   //  天气情况的查询。

                    WeatherBean weatherBean = JsonParser.parseResultWeatherBean(text);
                    WeatherAction weatherAction = new WeatherAction(service, weatherBean, text, context);
                    weatherAction.start();

                    break;

                case AppController.OPENAPPTEST_ROBOTCOMMAND:   //  机器人指令

                    RobotCommandBean robotCommandBean = JsonParser.parseResultRobotCommandBean(text);
                    RobotCommandAction robotCommandAction = new RobotCommandAction(service, robotCommandBean, text, context);
                    robotCommandAction.start();

                    break;

                case AppController.OPENAPPTEST_DANCE:      //  跳个舞

                    DanceBean danceBean = JsonParser.parseResultIflyDanceBean(text);
                    DanceAction danceAction = new DanceAction(service, danceBean, text, context);
                    danceAction.start();

                    break;

                case AppController.OPENAPPTEST_OPENIM:   // 打开视频通话

                    OpenimBean openimBean = JsonParser.parseResultIflyOpenimBean(text);
                    OpenimAction openimAction = new OpenimAction(service, openimBean, text, context);
                    openimAction.start();

                    break;

                case AppController.OPENAPPTEST_ELECTRICITY:   // 电量

                    byte[] sendByte = {0x5A, 0x50, 0x05, 0x01, 0x02, 0x01, 0x00, 0x00, 0x04, 0x0D, 0x0A};
                    SpeechRecognizerService.sendSerialMessageBytes(sendByte);
                    PreferUtil.getInstance().setElectricity(1);

                    break;

                case AppController.TELEPHONE: // 打电话

                    TelephoneBean telephoneBean = JsonParser.parseResultTelephoneBean(text);
                    TelephoneAction telephoneAction = new TelephoneAction(service, telephoneBean, text, context);
                    telephoneAction.start();

                    break;

                case AppController.FACESERVICE:   // 启动人脸服务

                    FaceserviceBean faceserviceBean = JsonParser.parseResultFaceserviceBean(text);
                    FaceserviceAction faceserviceAction = new FaceserviceAction(service, faceserviceBean, text, context);
                    faceserviceAction.start();

                    break;

//                case "OPENAPPTEST.music_demo": {  //   艺人跟歌曲 搜索和播放
//                    CustomMusicBean customMusicBean = JsonParser.parseResultCustomMusicBean(text);
//                    if (customMusicBean != null && customMusicBean.getSemantic().size() != 0 && customMusicBean.getSemantic().get(0).getSlots().size() != 0) {
//                        baseBean.setContext(customMusicBean.getText());
//                        baseBean.setCustomMusicBean(customMusicBean);
//                        mIGetVoiceToWord.getResult(service, baseBean);
//                    }
//                    break;
//                }

                default:
                    R4Action r4Action = new R4Action(context, text);
                    r4Action.start();
                    break;
            }
        }
    }
}
