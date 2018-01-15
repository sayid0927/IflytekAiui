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
import com.zhengpu.iflytekaiui.iflytekaction.ReceivedSerialPortDataAction;
import com.zhengpu.iflytekaiui.iflytekaction.ShipingAction;
import com.zhengpu.iflytekaiui.iflytekbean.BaseBean;
import com.zhengpu.iflytekaiui.iflytekbean.VideoBean;
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

    private String Message = "{\n" +
            "  \"data\": {\n" +
            "    \"result\": [\n" +
            "      {\n" +
            "        \"actor\": [\n" +
            "          {\n" +
            "            \"name\": \"邓超\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王祖蓝\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王宝强\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"陈赫\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"郑恺\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"杨颖\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"李晨\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王心凌\",\n" +
            "            \"roleName\": \"\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"aliasName\": [\n" +
            "          \"跑男\",\n" +
            "          \"奔跑吧!兄弟\",\n" +
            "          \"跑起来!好兄弟\",\n" +
            "          \"Running Man\"\n" +
            "        ],\n" +
            "        \"area\": [\n" +
            "          \"中国大陆\"\n" +
            "        ],\n" +
            "        \"award\": [\n" +
            "          {\n" +
            "            \"ceremony\": \"第1届豆瓣电影年度榜单\",\n" +
            "            \"title\": \"最受关注的大陆综艺(提名)\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"category\": \"综艺\",\n" +
            "        \"company\": \"\",\n" +
            "        \"director\": [\n" +
            "          \"岑俊义\",\n" +
            "          \"陆浩\"\n" +
            "        ],\n" +
            "        \"episode\": [],\n" +
            "        \"hot\": \"23794\",\n" +
            "        \"img\": \"http://kchfpre.openspeech.cn/music_db_file/parastor/data/downdata/pictures/douban_video/25899362/0f3c259311fb102be3dec50e84302118.jpg\",\n" +
            "        \"language\": \"国语\",\n" +
            "        \"leadActor\": [\n" +
            "          {\n" +
            "            \"name\": \"邓超\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王祖蓝\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王宝强\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"陈赫\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"郑恺\",\n" +
            "            \"roleName\": \"\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"name\": \"奔跑吧兄弟 第一季\",\n" +
            "        \"publishTime\": \"2014-10-10\",\n" +
            "        \"score\": \"8.3\",\n" +
            "        \"screenWriter\": [\n" +
            "          \"俞杭英\"\n" +
            "        ],\n" +
            "        \"season\": \"第一季\",\n" +
            "        \"source\": \"iflytek\",\n" +
            "        \"tags\": [\n" +
            "          \"电视剧\",\n" +
            "          \"真人秀\"\n" +
            "        ]\n" +
            "      },\n" +
            "      {\n" +
            "        \"actor\": [\n" +
            "          {\n" +
            "            \"name\": \"邓超\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"鹿晗\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"杨颖\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"郑恺\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王祖蓝\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"李晨\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"陈赫\",\n" +
            "            \"roleName\": \"\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"aliasName\": [\n" +
            "          \"跑男3\",\n" +
            "          \"Running Man season 3\"\n" +
            "        ],\n" +
            "        \"area\": [\n" +
            "          \"中国大陆\"\n" +
            "        ],\n" +
            "        \"award\": [],\n" +
            "        \"category\": \"综艺\",\n" +
            "        \"company\": \"\",\n" +
            "        \"director\": [\n" +
            "          \"岑俊义\",\n" +
            "          \"陆皓\"\n" +
            "        ],\n" +
            "        \"episode\": [],\n" +
            "        \"hot\": \"11563\",\n" +
            "        \"img\": \"http://kchfpre.openspeech.cn/music_db_file/parastor/data/downdata/pictures/douban_video/26438888/5d263aa80ad468d74f02f2c7abc23905.jpg\",\n" +
            "        \"language\": \"国语\",\n" +
            "        \"leadActor\": [\n" +
            "          {\n" +
            "            \"name\": \"邓超\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"鹿晗\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"杨颖\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"郑恺\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王祖蓝\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"李晨\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"陈赫\",\n" +
            "            \"roleName\": \"\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"name\": \"奔跑吧兄弟 第三季\",\n" +
            "        \"publishTime\": \"2015-10-30\",\n" +
            "        \"score\": \"6.3\",\n" +
            "        \"screenWriter\": [],\n" +
            "        \"season\": \"第三季\",\n" +
            "        \"source\": \"iflytek\",\n" +
            "        \"tags\": [\n" +
            "          \"电视剧\",\n" +
            "          \"真人秀\",\n" +
            "          \"综艺\"\n" +
            "        ]\n" +
            "      },\n" +
            "      {\n" +
            "        \"actor\": [\n" +
            "          {\n" +
            "            \"name\": \"邓超\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"李晨\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"陈赫\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"郑恺\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王祖蓝\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"鹿晗\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"迪丽热巴\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"杨颖\",\n" +
            "            \"roleName\": \"\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"aliasName\": [\n" +
            "          \"奔跑吧兄弟第五季\",\n" +
            "          \"跑男5\",\n" +
            "          \"Keep Running\"\n" +
            "        ],\n" +
            "        \"area\": [\n" +
            "          \"中国大陆\"\n" +
            "        ],\n" +
            "        \"award\": [],\n" +
            "        \"category\": \"综艺\",\n" +
            "        \"company\": \"\",\n" +
            "        \"director\": [],\n" +
            "        \"episode\": [],\n" +
            "        \"hot\": \"10505\",\n" +
            "        \"img\": \"http://kchfpre.openspeech.cn/music_db_file/parastor/data/downdata/pictures/douban_video/26830085/0f923ec3a1d79652be1ff3f266944420.jpg\",\n" +
            "        \"language\": \"国语\",\n" +
            "        \"leadActor\": [\n" +
            "          {\n" +
            "            \"name\": \"邓超\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"李晨\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"陈赫\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"郑恺\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王祖蓝\",\n" +
            "            \"roleName\": \"\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"name\": \"奔跑吧\",\n" +
            "        \"publishTime\": \"2017-04-14\",\n" +
            "        \"score\": \"6.4\",\n" +
            "        \"screenWriter\": [],\n" +
            "        \"season\": \"\",\n" +
            "        \"source\": \"iflytek\",\n" +
            "        \"tags\": [\n" +
            "          \"电视剧\",\n" +
            "          \"真人秀\"\n" +
            "        ]\n" +
            "      },\n" +
            "      {\n" +
            "        \"actor\": [\n" +
            "          {\n" +
            "            \"name\": \"邓超\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"杨颖\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"李晨\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"郑恺\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王祖蓝\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"陈赫\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"鹿晗\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"代乐乐\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"姜超\",\n" +
            "            \"roleName\": \"\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"aliasName\": [\n" +
            "          \"跑男4\",\n" +
            "          \"Running Man Season 4\"\n" +
            "        ],\n" +
            "        \"area\": [\n" +
            "          \"中国大陆\"\n" +
            "        ],\n" +
            "        \"award\": [],\n" +
            "        \"category\": \"综艺\",\n" +
            "        \"company\": \"\",\n" +
            "        \"director\": [\n" +
            "          \"蒋敏昊\"\n" +
            "        ],\n" +
            "        \"episode\": [],\n" +
            "        \"hot\": \"8730\",\n" +
            "        \"img\": \"http://kchfpre.openspeech.cn/music_db_file/parastor/data/downdata/pictures/douban_video/26710402/8dcaccfbea4438bdd19290f57eab09da.jpg\",\n" +
            "        \"language\": \"国语\",\n" +
            "        \"leadActor\": [\n" +
            "          {\n" +
            "            \"name\": \"邓超\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"杨颖\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"李晨\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"郑恺\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王祖蓝\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"陈赫\",\n" +
            "            \"roleName\": \"\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"name\": \"奔跑吧兄弟 第四季\",\n" +
            "        \"publishTime\": \"2016-04-15\",\n" +
            "        \"score\": \"5.8\",\n" +
            "        \"screenWriter\": [],\n" +
            "        \"season\": \"第四季\",\n" +
            "        \"source\": \"iflytek\",\n" +
            "        \"tags\": [\n" +
            "          \"电视剧\",\n" +
            "          \"真人秀\",\n" +
            "          \"综艺\"\n" +
            "        ]\n" +
            "      },\n" +
            "      {\n" +
            "        \"actor\": [\n" +
            "          {\n" +
            "            \"name\": \"邓超\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王祖蓝\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王宝强\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"李晨\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"陈赫\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"郑恺\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"杨颖\",\n" +
            "            \"roleName\": \"\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"aliasName\": [\n" +
            "          \"Chinese Running Man\",\n" +
            "          \"奔跑吧!兄弟\",\n" +
            "          \"奔跑吧\",\n" +
            "          \"兄弟\",\n" +
            "          \"奔跑吧兄弟\"\n" +
            "        ],\n" +
            "        \"area\": [\n" +
            "          \"中国大陆\"\n" +
            "        ],\n" +
            "        \"award\": [],\n" +
            "        \"category\": \"综艺\",\n" +
            "        \"company\": \"\",\n" +
            "        \"director\": [],\n" +
            "        \"episode\": [],\n" +
            "        \"hot\": \"194\",\n" +
            "        \"img\": \"http://kchfpre.openspeech.cn/music_db_file/parastor/data/downdata/pictures/mtime_video/217596/360c8d0d15e08bddfe1ac73daa9a1f5e.jpg\",\n" +
            "        \"language\": \"国语\",\n" +
            "        \"leadActor\": [\n" +
            "          {\n" +
            "            \"name\": \"邓超\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王祖蓝\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王宝强\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"李晨\",\n" +
            "            \"roleName\": \"\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"name\": \"奔跑吧兄弟\",\n" +
            "        \"publishTime\": \"2014-10-10\",\n" +
            "        \"score\": \"7.1\",\n" +
            "        \"screenWriter\": [],\n" +
            "        \"season\": \"\",\n" +
            "        \"source\": \"iflytek\",\n" +
            "        \"tags\": [\n" +
            "          \"电视剧\",\n" +
            "          \"真人秀\",\n" +
            "          \"综艺\"\n" +
            "        ]\n" +
            "      },\n" +
            "      {\n" +
            "        \"actor\": [\n" +
            "          {\n" +
            "            \"name\": \"陈赫\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"郑恺\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"杨颖\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"邓超\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王祖蓝\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王宝强\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"李晨\",\n" +
            "            \"roleName\": \"\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"aliasName\": [\n" +
            "          \"Chinese Running Man\"\n" +
            "        ],\n" +
            "        \"area\": [\n" +
            "          \"中国大陆\"\n" +
            "        ],\n" +
            "        \"award\": [],\n" +
            "        \"category\": \"综艺\",\n" +
            "        \"company\": \"\",\n" +
            "        \"director\": [],\n" +
            "        \"episode\": [],\n" +
            "        \"hot\": \"42\",\n" +
            "        \"img\": \"http://kchfpre.openspeech.cn/music_db_file/parastor/data/downdata/pictures/maoyan_video/368767/2c90c84543fde459015c55e4d5691dbc.jpg\",\n" +
            "        \"language\": \"\",\n" +
            "        \"leadActor\": [\n" +
            "          {\n" +
            "            \"name\": \"陈赫\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"郑恺\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"杨颖\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"邓超\",\n" +
            "            \"roleName\": \"\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"name\": \"奔跑吧兄弟 第一季\",\n" +
            "        \"publishTime\": \"2014-10-10\",\n" +
            "        \"score\": \"6.2\",\n" +
            "        \"screenWriter\": [],\n" +
            "        \"season\": \"第一季\",\n" +
            "        \"source\": \"iflytek\",\n" +
            "        \"tags\": [\n" +
            "          \"综艺\",\n" +
            "          \"真人秀\"\n" +
            "        ]\n" +
            "      },\n" +
            "      {\n" +
            "        \"actor\": [\n" +
            "          {\n" +
            "            \"name\": \"杨颖\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"陈赫\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王宝强\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王祖蓝\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"郑恺\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"李晨\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"熊黛林\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"谢依霖\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"伊一\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"郭京飞\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"张爱钦\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"金钟国\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"邓超\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"包贝尔\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"范冰冰\",\n" +
            "            \"roleName\": \"第一期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"韩庚\",\n" +
            "            \"roleName\": \"第一期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"黄晓明\",\n" +
            "            \"roleName\": \"第二期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"欧弟\",\n" +
            "            \"roleName\": \"第三期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"宋佳\",\n" +
            "            \"roleName\": \"第三期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"林更新\",\n" +
            "            \"roleName\": \"第四期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"张艺兴\",\n" +
            "            \"roleName\": \"第四期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"叶祖新\",\n" +
            "            \"roleName\": \"第四期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"李治廷\",\n" +
            "            \"roleName\": \"第四期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"蒋劲夫\",\n" +
            "            \"roleName\": \"第四期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"吴奇隆\",\n" +
            "            \"roleName\": \"第五期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"陈乔恩\",\n" +
            "            \"roleName\": \"第五期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"杜淳\",\n" +
            "            \"roleName\": \"第五期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"姚晨\",\n" +
            "            \"roleName\": \"第六期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"萧敬腾\",\n" +
            "            \"roleName\": \"第六期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"信\",\n" +
            "            \"roleName\": \"第六期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"吉克隽逸\",\n" +
            "            \"roleName\": \"第六期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"大鹏\",\n" +
            "            \"roleName\": \"第六期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"刘涛\",\n" +
            "            \"roleName\": \"第七期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"蔡少芬\",\n" +
            "            \"roleName\": \"第九期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"霍思燕\",\n" +
            "            \"roleName\": \"第九期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"李彩桦\",\n" +
            "            \"roleName\": \"第九期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"蒋欣\",\n" +
            "            \"roleName\": \"第九期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"江一燕\",\n" +
            "            \"roleName\": \"第九期嘉宾\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"苏见信\",\n" +
            "            \"roleName\": \"第六期嘉宾\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"aliasName\": [\n" +
            "          \"奔跑吧!兄弟\",\n" +
            "          \"Running Man\",\n" +
            "          \"奔跑吧兄弟\",\n" +
            "          \"奔跑吧\",\n" +
            "          \"兄弟\",\n" +
            "          \"跑男\",\n" +
            "          \"奔跑吧兄弟第二季\",\n" +
            "          \"奔跑吧兄弟 第二季\",\n" +
            "          \"跑男2\",\n" +
            "          \"Running Man Season 2\"\n" +
            "        ],\n" +
            "        \"area\": [\n" +
            "          \"中国大陆\"\n" +
            "        ],\n" +
            "        \"award\": [\n" +
            "          {\n" +
            "            \"ceremony\": \"第7届金扫帚奖\",\n" +
            "            \"title\": \"最令人失望中小成本电影\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"ceremony\": \"第6届豆瓣电影鑫像奖\",\n" +
            "            \"title\": \"豆渣单元 最渣影片(华语)\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"ceremony\": \"第2届豆瓣电影年度榜单\",\n" +
            "            \"title\": \"1月最受关注电影(提名) ,评分最低的华语电影(提名)\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"ceremony\": \"第2届豆瓣电影年度榜单\",\n" +
            "            \"title\": \"最受关注的大陆综艺(提名)\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"category\": \"综艺\",\n" +
            "        \"company\": \"万达影视传媒有限公司\",\n" +
            "        \"director\": [\n" +
            "          \"胡笳\",\n" +
            "          \"岑俊义\",\n" +
            "          \"陆浩\"\n" +
            "        ],\n" +
            "        \"episode\": [],\n" +
            "        \"hot\": \"0\",\n" +
            "        \"img\": \"http://kchfpre.openspeech.cn/music_db_file/parastor/data/downdata/pictures/maoyan_video/246316/d24fafbab399042eed3e8f44fcd1b669.jpg\",\n" +
            "        \"language\": \"国语\",\n" +
            "        \"leadActor\": [\n" +
            "          {\n" +
            "            \"name\": \"杨颖\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"陈赫\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王宝强\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王祖蓝\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"李晨\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"郑恺\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"邓超\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"包贝尔\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"叶祖新\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"蒋劲夫\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"信\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"吉克隽逸\",\n" +
            "            \"roleName\": \"\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"name\": \"奔跑吧!兄弟\",\n" +
            "        \"publishTime\": \"2015-01-30\",\n" +
            "        \"score\": \"7.8\",\n" +
            "        \"screenWriter\": [\n" +
            "          \"胡笳\",\n" +
            "          \"岑俊义\",\n" +
            "          \"李雅弢\",\n" +
            "          \"杨琳\",\n" +
            "          \"俞杭英\"\n" +
            "        ],\n" +
            "        \"season\": \"第二季\",\n" +
            "        \"source\": \"iflytek\",\n" +
            "        \"tags\": [\n" +
            "          \"综艺\",\n" +
            "          \"喜剧\",\n" +
            "          \"动作\",\n" +
            "          \"冒险\",\n" +
            "          \"电视剧\",\n" +
            "          \"真人秀\"\n" +
            "        ]\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  \"rc\": 0,\n" +
            "  \"semantic\": [\n" +
            "    {\n" +
            "      \"intent\": \"QUERY\",\n" +
            "      \"slots\": [\n" +
            "        {\n" +
            "          \"name\": \"name\",\n" +
            "          \"value\": \"奔跑吧兄弟\"\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  ],\n" +
            "  \"service\": \"video\",\n" +
            "  \"state\": {\n" +
            "    \"fg::video::default::default\": {\n" +
            "      \"name\": \"1\"\n" +
            "    }\n" +
            "  },\n" +
            "  \"text\": \"奔跑吧兄弟\",\n" +
            "  \"uuid\": \"atn00166083@ch11710db360446f2001\",\n" +
            "  \"answer\": {\n" +
            "    \"text\": \"不好意思，没有为您找到您想看的影片，为您推荐\\\"奔跑吧兄弟 第一季\\\"\"\n" +
            "  },\n" +
            "  \"dialog_stat\": \"dataInvalid\",\n" +
            "  \"save_history\": true,\n" +
            "  \"sid\": \"atn00166083@ch11710db360446f2001\"\n" +
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
        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=5a541d0c," + SpeechConstant.FORCE_LOGIN + "=true");// 传递科大讯飞appid
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

//        VideoBean videoBean = JsonParser.parseResultVideoBean(Message);

//        ShipingAction shipingAction = new ShipingAction("", videoBean, this, Message);
//        shipingAction.start();

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
                voiceToWords.mIatDestroy();
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
