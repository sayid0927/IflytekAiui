package com.zhengpu.iflytekaiui.iflytekutils;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUnderstander;
import com.iflytek.cloud.SpeechUnderstanderListener;
import com.iflytek.cloud.UnderstanderResult;
import com.orhanobut.logger.Logger;
import com.zhengpu.iflytekaiui.base.AppController;
import com.zhengpu.iflytekaiui.iflytekaction.BaikeAction;
import com.zhengpu.iflytekaiui.iflytekaction.CalcAction;
import com.zhengpu.iflytekaiui.iflytekaction.CustomBaikeAction;
import com.zhengpu.iflytekaiui.iflytekaction.OpenAppAction;
import com.zhengpu.iflytekaiui.iflytekaction.PlayMusicxAction;
import com.zhengpu.iflytekaiui.iflytekbean.BaikeBean;
import com.zhengpu.iflytekaiui.iflytekbean.BaseBean;
import com.zhengpu.iflytekaiui.iflytekbean.CalcBean;
import com.zhengpu.iflytekaiui.iflytekbean.CustomBaikeBean;
import com.zhengpu.iflytekaiui.iflytekbean.DatetimeBean;
import com.zhengpu.iflytekaiui.iflytekbean.FlightBean;
import com.zhengpu.iflytekaiui.iflytekbean.JokeBean;
import com.zhengpu.iflytekaiui.iflytekbean.MusicXBean;
import com.zhengpu.iflytekaiui.iflytekbean.NewsBean;
import com.zhengpu.iflytekaiui.iflytekbean.OpenAppBean;
import com.zhengpu.iflytekaiui.iflytekbean.OpenQABean;
import com.zhengpu.iflytekaiui.iflytekbean.PoetryBean;
import com.zhengpu.iflytekaiui.iflytekbean.R4Bean;
import com.zhengpu.iflytekaiui.iflytekbean.StoryBean;
import com.zhengpu.iflytekaiui.iflytekbean.VideoBean;
import com.zhengpu.iflytekaiui.iflytekbean.WeatherBean;
import com.zhengpu.iflytekaiui.iflytekbean.otherbean.CustomMusicBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;


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
    private String operation;
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
        mIat.setParameter(SpeechConstant.KEY_SPEECH_TIMEOUT, "50000");
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
        //mIat.setParameter(SpeechConstant.DOMAIN, "iat");

        // 传入音频源。
        //录音机的录音方式，默认为MIC(MediaRecorder.AudioSource.MIC)
        // 设置音频来源为外部文件
        //mIat.setParameter(SpeechConstant.AUDIO_SOURCE, "-1");
        // 也可以像以下这样直接设置音频文件路径识别（要求设置文件在sdcard上的全路径）：
        // mIat.setParameter(SpeechConstant.AUDIO_SOURCE, "-2");
        // mIat.setParameter(SpeechConstant.ASR_SOURCE_PATH, "sdcard/XXX/XXX.pcm");

        mIat.setParameter(SpeechConstant.VAD_ENABLE, "1");
        // 设置语音前端点:静音超时时间，即用户多长时间不说话则当做超时处理
        mIat.setParameter(SpeechConstant.VAD_BOS, "200000");

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
                        if (mIGetVoiceToWord != null) {
                            R4Bean r4Bean = JsonParser.parseResultR4Bean(text);
                            BaseBean baseBean = new BaseBean();
                            baseBean.setContext(r4Bean.getText());
                            baseBean.setR4Bean(r4Bean);
                            mIGetVoiceToWord.getResult("r4", baseBean);
                        }
                    }
                } catch (JSONException e) {
                    if (mIGetVoiceToWord != null) {
                        R4Bean r4Bean = JsonParser.parseResultR4Bean(text);
                        BaseBean baseBean = new BaseBean();
                        baseBean.setContext(r4Bean.getText());
                        baseBean.setR4Bean(r4Bean);
                        mIGetVoiceToWord.getResult("r4", baseBean);
                    }
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onVolumeChanged(int volume, byte[] data) {

            //showTip("当前正在说话，音量大小：" + volume);
            //Log.d(TAG, "返回音频数据：" + data.length);
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
                int i = new Random().nextInt(3);
                switch (i) {
                    case 0:
                        lowVoiceTip = "声音太小了，听不清呢";
                        break;
                    case 1:
                        lowVoiceTip = "有在说话吗，没听到啊";
                        break;
                    case 2:
                        lowVoiceTip = "您好像没有说话呢";
                        break;
                }
                if (mIGetVoiceToWord != null)
                    mIGetVoiceToWord.showLowVoice(lowVoiceTip);
            }
        }

        @Override
        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
            // 以下代码用于获取与云端的会话id，当业务出错时将会话id提供给技术支持人员，可用于查询会话日志，定位出错原因
            // 若使用本地能力，会话id为null
            //	if (SpeechEvent.EVENT_SESSION_ID == eventType) {
            //		String sid = obj.getString(SpeechEvent.KEY_EVENT_SESSION_ID);
            //		Log.d(TAG, "session id =" + sid);
            //	}

        }
    };


    //语义场景判断
    private void judgeService(String service, String text) {

        if (!AppController.service_flag) { //如果不在一项服务中才进行服务的判断

            BaseBean baseBean = new BaseBean();
            switch (service) {

                case AppController.BAIKE: {     //互动百科词条查询。

                    BaikeBean baikeBean = JsonParser.parseResultBaikeBean(text);
                    if (baikeBean != null && baikeBean.getAnswer().getText() != null) {

                        baseBean.setContext(baikeBean.getText());
                        baseBean.setBaikeBean(baikeBean);
                        mIGetVoiceToWord.getResult(service, baseBean);

                        BaikeAction baikeAction = new BaikeAction(service, baikeBean.getAnswer().getText());
                        baikeAction.start();

                    }

                    break;
                }
                case AppController.OPENAPPTEST_CUSTOM_BAIKE: // 自定义百科 对人名 地名进行百科


                    CustomBaikeBean customBaikeBean = JsonParser.parseResultCustomBaikeBean(text);
                    if (customBaikeBean != null && customBaikeBean.getSemantic().size() != 0 && customBaikeBean.getSemantic().get(0).getSlots().size() != 0
                            && customBaikeBean.getSemantic().get(0).getSlots().get(0).getValue() != null) {

                        baseBean.setContext(customBaikeBean.getText());
                        baseBean.setCustomBaikeBean(customBaikeBean);
                        mIGetVoiceToWord.getResult(service, baseBean);

                        CustomBaikeAction customBaikeAction = new CustomBaikeAction(service, customBaikeBean.getSemantic().get(0).getSlots().get(0).getValue(), context);
                        customBaikeAction.start();

                    }


                    break;
                case "calc": {    //  数值计算问答
                    CalcBean calcBean = JsonParser.parseResultCalc(text);
                    operation = calcBean.getOperation();
                    switch (operation) {
                        case "ANSWER":
                            if (calcBean.getAnswer() != null) {
                                if (calcBean.getAnswer().getText() != null) {

                                    baseBean.setContext(calcBean.getText());
                                    baseBean.setCalcBean(calcBean);
                                    mIGetVoiceToWord.getResult(service, baseBean);

                                    String str = calcBean.getAnswer().getText();
                                    CalcAction calcAction = new CalcAction(service, str);
                                    calcAction.start();

                                }
                            }
                            break;
                    }
                    break;
                }
                case "datetime": {   //  时间、日期的查询。
                    DatetimeBean datetimeBean = JsonParser.parseResultDatetimeBean(text);
                    operation = datetimeBean.getOperation();
                    switch (operation) {
                        case "ANSWER": {
                            if (datetimeBean.getAnswer() != null) {
                                if (datetimeBean.getAnswer().getText() != null) {

                                    baseBean.setContext(datetimeBean.getText());
                                    baseBean.setDatetimeBean(datetimeBean);
                                    mIGetVoiceToWord.getResult(service, baseBean);

                                    String str = datetimeBean.getAnswer().getText();
                                    CalcAction calcAction = new CalcAction(service, str);
                                    calcAction.start();

                                }
                            }
                        }
                        break;
                    }
                    break;
                }
                case "flight": {  //飞机票、航班信息的查询及订购。

                    FlightBean flightBean = JsonParser.parseResultFlightoBean(text);
                    if (flightBean != null && flightBean.getAnswer() != null) {

                        baseBean.setContext(flightBean.getText());
                        baseBean.setFlightBean(flightBean);
                        mIGetVoiceToWord.getResult(service, baseBean);

                    }
                    break;
                }

                case "joke": {  // 笑话的点播
                    JokeBean jokeBean = JsonParser.parseResultJokeBean(text);
                    if (jokeBean != null) {

                        baseBean.setContext(jokeBean.getText());
                        baseBean.setJokeBean(jokeBean);
                        mIGetVoiceToWord.getResult(service, baseBean);

//                            if (jokeBean.getData().getResult().get(0).getTitle() != null && jokeBean.getData().getResult().get(0).getMp3Url() != null) {
//                                String mp3Url = jokeBean.getData().getResult().get(0).getMp3Url();
//                                JokeAction jokeAction = new JokeAction(mp3Url, context);
//                                jokeAction.start();
//                            } else {
//                                if (jokeBean.getData().getResult().get(0).getTitle() != null &&
//                                        jokeBean.getData().getResult().get(0).getContent() != null) {
//                                    String title = jokeBean.getData().getResult().get(0).getTitle();
//                                    String content = jokeBean.getData().getResult().get(0).getContent();
////                                    CalcAction calcAction = new CalcAction("请欣赏 " + title + content);
////                                    calcAction.start();
//                                }
//                            }
                    }
                    break;
                }
                case AppController.MUSICX: {  //   音乐的搜索和播放

                    MusicXBean musicXBean = JsonParser.parseResultMusicXBean(text);
                    if (musicXBean != null && musicXBean.getText() != null) {

                        baseBean.setContext(musicXBean.getText());
                        baseBean.setMusicXBean(musicXBean);
                        mIGetVoiceToWord.getResult(service, baseBean);

                        PlayMusicxAction playMusicxAction = new PlayMusicxAction(musicXBean.getText(),musicXBean.getText(),context);
                        playMusicxAction.start();
                    }
                    break;
                }
                case "news": {  //  新闻的搜索和点播

                    NewsBean newsBean = JsonParser.parseResultNewsBean(text);
                    if (newsBean != null && newsBean.getText() != null) {

                        baseBean.setContext(newsBean.getText());
                        baseBean.setNewsBean(newsBean);
                        mIGetVoiceToWord.getResult(service, baseBean);
//                        CalcAction calcAction = new CalcAction("为你推荐如下热门新闻");
//                        calcAction.start();

                    }

                    break;
                }
                case "OPENAPPTEST.APP": {   //打开App

                    OpenAppBean openAppBean = JsonParser.parseResultOpenAppBean(text);
                    if (openAppBean != null && openAppBean.getSemantic().size() != 0) {
                        if (openAppBean.getSemantic().get(0).getSlots().size() != 0) {
                            if (openAppBean.getSemantic().get(0).getSlots().get(0).getNormValue() != null) {

                                baseBean.setContext(openAppBean.getText());
                                baseBean.setOpenAppBean(openAppBean);
                                mIGetVoiceToWord.getResult(service, baseBean);

                                String appName = openAppBean.getSemantic().get(0).getSlots().get(0).getNormValue();
                                OpenAppAction openAppAction = new OpenAppAction(appName, context);
                                openAppAction.start();
                            }
                        }
                    }
                    break;
                }
                case "openQA": {  //     开放问答

                    OpenQABean openQABean = JsonParser.parseResultOpenQABean(text);
                    if (openQABean != null && openQABean.getAnswer() != null) {

                        baseBean.setContext(openQABean.getText());
                        baseBean.setOpenQABean(openQABean);
                        mIGetVoiceToWord.getResult(service, baseBean);

//                        String str = openQABean.getAnswer().getText();
//                        CalcAction calcAction = new CalcAction(str);
//                        calcAction.start();

                    }
                    break;
                }
                case "poetry": {  //     诗词查询和诗句对答。
                    PoetryBean poetryBean = JsonParser.parseResultPoetryBean(text);
                    if (poetryBean != null && poetryBean.getData() != null) {
                        if (poetryBean.getData().getResult().size() != 0) {
                            if (poetryBean.getData().getResult().get(0).getTitle() != null && poetryBean.getData().getResult().get(0).getShowContent() != null) {

                                baseBean.setContext(poetryBean.getText());
                                baseBean.setPoetryBean(poetryBean);
                                mIGetVoiceToWord.getResult(service, baseBean);

                            }
                        }
                    }
                    break;
                }
                case "story": { //     故事的点播
                    StoryBean storyBean = JsonParser.parseResultStoryBean(text);
                    if (storyBean != null && storyBean.getAnswer() != null) {

                        baseBean.setContext(storyBean.getText());
                        baseBean.setStoryBean(storyBean);
                        mIGetVoiceToWord.getResult(service, baseBean);

                    }

                    break;
                }
                case "OPENAPPTEST.shiping": {//   视频的搜索和播放

                    VideoBean videoBean = JsonParser.parseResultVideoBean(text);
                    if (videoBean != null && videoBean.getSemantic().size() != 0) {
                        if (videoBean.getSemantic().get(0).getSlots().size() != 0) {
                            if (videoBean.getSemantic().get(0).getSlots().get(0).getValue() != null) {

                                baseBean.setContext(videoBean.getText());
                                baseBean.setVideoBean(videoBean);
                                mIGetVoiceToWord.getResult(service, baseBean);

//                                String videoName = videoBean.getSemantic().get(0).getSlots().get(0).getValue();
//                                String appName = "爱奇艺";
//                                if (isAppInstalled(context, appName)) {
//                                    PlayVideoAction playMusicxAction = new PlayVideoAction(videoName, appName, context);
//                                    playMusicxAction.start();
//                                } else {
//                                    Logger.e("没有安装爱奇艺APP");
//                                }
                            }
                        }
                    }
                    break;
                }
                case "weather": {  //  天气情况的查询。

                    WeatherBean weatherBean = JsonParser.parseResultWeatherBean(text);
                    if (weatherBean.getData().getResult().size() != 0) {

                        baseBean.setContext(weatherBean.getText());
                        baseBean.setWeatherBean(weatherBean);
                        mIGetVoiceToWord.getResult(service, baseBean);

//                        StringBuilder stringBuffer = new StringBuilder();
//                        String humidity = weatherBean.getData().getResult().get(0).getHumidity();  //湿度
//                        String tempRange = weatherBean.getData().getResult().get(0).getTempRange();   // 温度范围
//                        String weather = weatherBean.getData().getResult().get(0).getWeather(); //天气情况
//                        String wind = weatherBean.getData().getResult().get(0).getWind();
//                        String prompt = weatherBean.getData().getResult().get(0).getExp().getCt().getPrompt();
//
//                        String airQuality = weatherBean.getData().getResult().get(0).getAirQuality();
//
//                        stringBuffer.append("空气质量为").append(airQuality)
//                                .append("湿度为").append(humidity).append("温度范围为").append(tempRange)
//                                .append("天气情况为").append(weather).append("风向以及风力情况为").append(wind)
//                                .append("穿衣指数为").append(prompt);

//                        CalcAction calcAction = new CalcAction(stringBuffer.toString());
//                        calcAction.start();

                    } else {
                        if (weatherBean.getAnswer() != null) {
//                            String str = weatherBean.getAnswer().getText();
//                            CalcAction calcAction = new CalcAction(str);
//                            calcAction.start();
                        }
                    }

                    break;
                }
                case "OPENAPPTEST.music_demo": {  //   艺人跟歌曲 搜索和播放
                    CustomMusicBean customMusicBean = JsonParser.parseResultCustomMusicBean(text);
                    if (customMusicBean != null && customMusicBean.getSemantic().size() != 0 && customMusicBean.getSemantic().get(0).getSlots().size() != 0) {

                        baseBean.setContext(customMusicBean.getText());
                        baseBean.setCustomMusicBean(customMusicBean);
                        mIGetVoiceToWord.getResult(service, baseBean);

                    }
                    break;
                }

                default:
                    WordsToVoice.startSynthesizer(AppController.R4, "不好意思，我好像没听懂。");
            }

        }
    }

}
