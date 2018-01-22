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
import com.iflytek.cloud.SpeechError;
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
import com.zhengpu.iflytekaiui.utils.CommDialog;
import com.zhengpu.iflytekaiui.utils.PreferUtil;
import com.zhengpu.iflytekaiui.utils.SpeechDialog;
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
    private SpeechDialog speechDialog;


    private String Message = "{\n" +
            "  \"data\": {\n" +
            "    \"result\": [\n" +
            "      {\n" +
            "        \"actor\": [\n" +
            "          {\n" +
            "            \"name\": \"帕查特·南潘\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"施莉达\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"辛扎伊·本班尼\",\n" +
            "            \"roleName\": \"\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"aliasName\": [\n" +
            "          \"Klin Kaew Klang Jai,\",\n" +
            "          \"\"\n" +
            "        ],\n" +
            "        \"area\": [\n" +
            "          \"泰国\"\n" +
            "        ],\n" +
            "        \"award\": [],\n" +
            "        \"category\": \"电视剧\",\n" +
            "        \"company\": \"\",\n" +
            "        \"director\": [],\n" +
            "        \"episode\": [],\n" +
            "        \"hot\": \"135\",\n" +
            "        \"img\": \"http://kchfpre.openspeech.cn/music_db_file/parastor/data/downdata/pictures/douban_video/4742387/fc2568c06b91d9ea11a8a1bfec08d32d.jpg\",\n" +
            "        \"language\": \"泰语\",\n" +
            "        \"leadActor\": [\n" +
            "          {\n" +
            "            \"name\": \"帕查特·南潘\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"施莉达\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"辛扎伊·本班尼\",\n" +
            "            \"roleName\": \"\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"name\": \"七里香\",\n" +
            "        \"score\": \"7.5\",\n" +
            "        \"screenWriter\": [\n" +
            "          \"泰国电视台三台\"\n" +
            "        ],\n" +
            "        \"season\": \"\",\n" +
            "        \"source\": \"iflytek\",\n" +
            "        \"tags\": [\n" +
            "          \"电视剧\"\n" +
            "        ]\n" +
            "      },\n" +
            "      {\n" +
            "        \"actor\": [\n" +
            "          {\n" +
            "            \"name\": \"杨琪\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"施芳婷\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"周杰伦\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"游安顺\",\n" +
            "            \"roleName\": \"\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"aliasName\": [\n" +
            "          \"人生剧展:百里香煎鱼\"\n" +
            "        ],\n" +
            "        \"area\": [\n" +
            "          \"中国台湾\"\n" +
            "        ],\n" +
            "        \"award\": [],\n" +
            "        \"category\": \"电影\",\n" +
            "        \"company\": \"\",\n" +
            "        \"director\": [\n" +
            "          \"郭珍弟\"\n" +
            "        ],\n" +
            "        \"episode\": [],\n" +
            "        \"hot\": \"0\",\n" +
            "        \"img\": \"http://kchfpre.openspeech.cn/music_db_file/parastor/data/downdata/pictures/douban_video/3546088/fd939f849fd9350bec50051f28a05b40.jpg\",\n" +
            "        \"language\": \"国语\",\n" +
            "        \"leadActor\": [\n" +
            "          {\n" +
            "            \"name\": \"杨琪\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"施芳婷\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"周杰伦\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"游安顺\",\n" +
            "            \"roleName\": \"\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"name\": \"百里香煎鱼\",\n" +
            "        \"publishTime\": \"1998-01-01\",\n" +
            "        \"score\": \"5.8\",\n" +
            "        \"screenWriter\": [],\n" +
            "        \"season\": \"百里香煎鱼\",\n" +
            "        \"source\": \"iflytek\",\n" +
            "        \"tags\": []\n" +
            "      },\n" +
            "      {\n" +
            "        \"actor\": [\n" +
            "          {\n" +
            "            \"name\": \"周迅\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"陈英明\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"黄又南\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"Wei-Men Hu\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"何世文\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"梁仕平\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"谭洁雯\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"温金丽\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"顾君娜\",\n" +
            "            \"roleName\": \"护士\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"方伟鸿\",\n" +
            "            \"roleName\": \"Peter Chau\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"陈伟强\",\n" +
            "            \"roleName\": \"邮差\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"木星\",\n" +
            "            \"roleName\": \"照相佬\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"曾灶才\",\n" +
            "            \"roleName\": \"写字老人\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"黎永昌\",\n" +
            "            \"roleName\": \"警察\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"陈景隆\",\n" +
            "            \"roleName\": \"斩手帮\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"李家栋\",\n" +
            "            \"roleName\": \"斩手帮\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"Karuna Morris\",\n" +
            "            \"roleName\": \"肥富婆\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"魏锦耀\",\n" +
            "            \"roleName\": \"斩手帮\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"吴俊雄\",\n" +
            "            \"roleName\": \"左撇子司机\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王紫发\",\n" +
            "            \"roleName\": \"士多夫妇\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"Chui-Ling Chan\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"King Kwong Lai\",\n" +
            "            \"roleName\": \"Couple at Store\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"谭杰雯\",\n" +
            "            \"roleName\": \"白毛女\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"胡惠文\",\n" +
            "            \"roleName\": \"吕医生\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"Kit Man Tam\",\n" +
            "            \"roleName\": \"Ah Lui\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"Tak Him Wong\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"Lai King Kwong\",\n" +
            "            \"roleName\": \"Couple at Store\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"Kai Hing Wong\",\n" +
            "            \"roleName\": \"Young Gangster\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"Hang Hing Yuen\",\n" +
            "            \"roleName\": \"Young Gangster\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"Lok Man Yu\",\n" +
            "            \"roleName\": \"Young Gangster\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"Sing Muk\",\n" +
            "            \"roleName\": \"Cambodian ATF interpreter\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"aliasName\": [\n" +
            "          \"香港有个好莱坞\",\n" +
            "          \"Hollywood Hong Kong,香港有个荷里活\",\n" +
            "          \"Hollywood Hong-Kong\",\n" +
            "          \"香港有个荷里活\",\n" +
            "          \"Heung gong yau gok hor lei wood\"\n" +
            "        ],\n" +
            "        \"area\": [\n" +
            "          \"日本\",\n" +
            "          \"法国\",\n" +
            "          \"英国\",\n" +
            "          \"中国香港\"\n" +
            "        ],\n" +
            "        \"award\": [\n" +
            "          {\n" +
            "            \"ceremony\": \"第58届威尼斯电影节\",\n" +
            "            \"title\": \"主竞赛单元 金狮奖 (提名)\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"ceremony\": \"第39届台北金马影展\",\n" +
            "            \"title\": \"金马奖 最佳剧情片(提名) ,金马奖 最佳导演 ,金马奖 最佳男主角(提名) ,金马奖 最佳女主角(提名) ,金马奖 最佳女配角(提名) ,金马奖 最佳原著剧本(提名) ,金马奖 最佳新演员(提名) ,金马奖 最佳摄影(提名) ,金马奖 最佳美术设计(提名) ,金马奖 最佳造型设计 ,金马奖 最佳原创配乐(提名) ,金马奖 最佳原创歌曲(提名) ,金马奖 最佳剪辑(提名) ,金马奖 最佳音效\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"ceremony\": \"第22届香港电影金像奖\",\n" +
            "            \"title\": \"最佳电影(提名) ,最佳导演(提名) ,最佳编剧(提名) ,最佳新演员(提名) ,最佳服装造型设计(提名) ,最佳原创电影音乐(提名)\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"ceremony\": \"第58届威尼斯电影节\",\n" +
            "            \"title\": \"Golden Lion(提名)\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"ceremony\": \"第22届香港金像奖\",\n" +
            "            \"title\": \"Best Costumes/Make Up(提名) ,Best Music(提名) ,最佳影片(提名) ,最佳导演(提名) ,最佳编剧(提名) ,最佳新演员(提名)\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"ceremony\": \"第39届台湾金马奖\",\n" +
            "            \"title\": \"最佳导演 ,最佳造型设计 ,最佳音效 ,最佳剧情片(提名) ,最佳男主角(提名) ,最佳女主角(提名) ,最佳女配角(提名) ,最佳新演员(提名) ,最佳原著剧本(提名) ,最佳摄影(提名) ,最佳美术设计(提名) ,最佳原创电影音乐(提名) ,最佳原创电影歌曲(提名) ,最佳剪辑(提名)\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"ceremony\": \"第3届华语电影传媒大奖\",\n" +
            "            \"title\": \"最佳电影(提名) ,最佳导演(提名)\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"ceremony\": \"第9届香港电影评论学会大奖\",\n" +
            "            \"title\": \"Film of Merit ,最佳编剧\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"ceremony\": \"威尼斯电影节\",\n" +
            "            \"title\": \"金狮奖(提名)\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"ceremony\": \"香港电影金像奖\",\n" +
            "            \"title\": \"金像奖-最佳影片(提名) ,金像奖-最佳导演(提名) ,金像奖-最佳编剧(提名) ,金像奖-最佳新演员(提名) ,金像奖-最佳原创电影音乐(提名) ,金像奖-最佳服装造型设计(提名)\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"ceremony\": \"台湾电影金马奖\",\n" +
            "            \"title\": \"金马奖-最佳导演奖 ,金马奖-最佳音效 ,金马奖-最佳造型设计奖 ,金马奖-最佳影片(提名) ,金马奖-最佳男主角奖(提名) ,金马奖-最佳女主角奖(提名) ,金马奖-最佳女配角奖(提名) ,金马奖-最佳原创剧本(提名) ,金马奖-最佳摄影奖(提名) ,金马奖-最佳艺术指导(提名) ,金马奖-最佳剪辑奖(提名) ,金马奖-最佳原创歌曲奖(提名) ,金马奖-最佳原创音乐(提名) ,金马奖-最佳新人奖(提名)\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"ceremony\": \"华语电影传媒大奖\",\n" +
            "            \"title\": \"最佳电影(提名) ,最佳导演(提名)\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"category\": \"电影\",\n" +
            "        \"company\": \"\",\n" +
            "        \"director\": [\n" +
            "          \"陈果\"\n" +
            "        ],\n" +
            "        \"episode\": [],\n" +
            "        \"hot\": \"15592\",\n" +
            "        \"img\": \"http://kchfpre.openspeech.cn/music_db_file/parastor/data/downdata/pictures/douban_video/1304676/86cde5a39d614ba2d505b8aed0726aa6.jpg\",\n" +
            "        \"language\": \"粤语 ,普通话\",\n" +
            "        \"leadActor\": [\n" +
            "          {\n" +
            "            \"name\": \"周迅\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"陈英明\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"黄又南\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"Wei-Men Hu\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"何世文\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"梁仕平\",\n" +
            "            \"roleName\": \"Tiny Chu\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"name\": \"香港有个荷里活\",\n" +
            "        \"publishTime\": \"2001-09-02\",\n" +
            "        \"score\": \"7.0\",\n" +
            "        \"screenWriter\": [\n" +
            "          \"林纪陶\",\n" +
            "          \"陈果\",\n" +
            "          \"陈荣照\",\n" +
            "          \"陈荣照\",\n" +
            "          \"Kee-To Lam\",\n" +
            "          \"Lan Kay Toa\"\n" +
            "        ],\n" +
            "        \"season\": \"\",\n" +
            "        \"source\": \"iflytek\",\n" +
            "        \"tags\": [\n" +
            "          \"剧情\",\n" +
            "          \"喜剧\",\n" +
            "          \"爱情\"\n" +
            "        ]\n" +
            "      },\n" +
            "      {\n" +
            "        \"actor\": [\n" +
            "          {\n" +
            "            \"name\": \"吴中天\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"章小蕙\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"朱芷莹\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"朱芷莹\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"吴中天\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"丁乃筝\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"章小蕙\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"尹昭德\",\n" +
            "            \"roleName\": \"Wang Yi-Hang\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"Alice Tsai-yi Huang\",\n" +
            "            \"roleName\": \"Foreign domestic helper\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"Samantha Shu-Chin Ko\",\n" +
            "            \"roleName\": \"Mrs. Pai\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"Novia Lin\",\n" +
            "            \"roleName\": \"Female client\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"aliasName\": [\n" +
            "          \"这儿是香格里拉,Here's Shangri-la\",\n" +
            "          \"Finding Shangri-la\",\n" +
            "          \"这儿是香格里拉\"\n" +
            "        ],\n" +
            "        \"area\": [\n" +
            "          \"中国台湾\",\n" +
            "          \"中国大陆\"\n" +
            "        ],\n" +
            "        \"award\": [\n" +
            "          {\n" +
            "            \"ceremony\": \"第12届上海国际电影节\",\n" +
            "            \"title\": \"电影频道传媒大奖 最佳影片(提名)\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"category\": \"电影\",\n" +
            "        \"company\": \"表演工作坊\",\n" +
            "        \"director\": [\n" +
            "          \"丁乃筝\"\n" +
            "        ],\n" +
            "        \"episode\": [],\n" +
            "        \"hot\": \"3405\",\n" +
            "        \"img\": \"http://kchfpre.openspeech.cn/music_db_file/parastor/data/downdata/pictures/douban_video/2342688/929e598a1250856b9a2af2a3a20388f6.jpg\",\n" +
            "        \"language\": \"国语\",\n" +
            "        \"leadActor\": [\n" +
            "          {\n" +
            "            \"name\": \"吴中天\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"章小蕙\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"朱芷莹\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"朱芷莹\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"吴中天\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"尹昭德\",\n" +
            "            \"roleName\": \"Wang Yi-Hang\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"Samantha Shu-Chin Ko\",\n" +
            "            \"roleName\": \"Mrs. Pai\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"Alice Tsai-yi Huang\",\n" +
            "            \"roleName\": \"Foreign domestic helper\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"丁乃筝\",\n" +
            "            \"roleName\": \"\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"name\": \"这儿是香格里拉\",\n" +
            "        \"publishTime\": \"2009-02-27\",\n" +
            "        \"score\": \"5.9\",\n" +
            "        \"screenWriter\": [\n" +
            "          \"丁乃筝\"\n" +
            "        ],\n" +
            "        \"season\": \"\",\n" +
            "        \"source\": \"iflytek\",\n" +
            "        \"tags\": [\n" +
            "          \"剧情\",\n" +
            "          \"爱情\"\n" +
            "        ]\n" +
            "      },\n" +
            "      {\n" +
            "        \"actor\": [\n" +
            "          {\n" +
            "            \"name\": \"刘萌萌\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"张子栋\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"艾如\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"刘冠麟\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"大兵\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"马丽\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王宁\",\n" +
            "            \"roleName\": \"\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"aliasName\": [\n" +
            "          \"香瓜七兄弟 第二季\",\n" +
            "          \"香瓜七兄弟2\"\n" +
            "        ],\n" +
            "        \"area\": [\n" +
            "          \"中国大陆\"\n" +
            "        ],\n" +
            "        \"award\": [],\n" +
            "        \"category\": \"电视剧\",\n" +
            "        \"company\": \"\",\n" +
            "        \"director\": [\n" +
            "          \"姬智\"\n" +
            "        ],\n" +
            "        \"episode\": [],\n" +
            "        \"hot\": \"66\",\n" +
            "        \"img\": \"http://kchfpre.openspeech.cn/music_db_file/parastor/data/downdata/pictures/douban_video/25945115/c2c29bf8c39f15382cda3f2cb5ba3e81.jpg\",\n" +
            "        \"language\": \"国语\",\n" +
            "        \"leadActor\": [\n" +
            "          {\n" +
            "            \"name\": \"刘萌萌\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"张子栋\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"艾如\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"刘冠麟\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"大兵\",\n" +
            "            \"roleName\": \"\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"name\": \"香瓜七兄弟2\",\n" +
            "        \"publishTime\": \"2014-08-02\",\n" +
            "        \"score\": \"7.9\",\n" +
            "        \"screenWriter\": [],\n" +
            "        \"season\": \"2\",\n" +
            "        \"source\": \"iflytek\",\n" +
            "        \"tags\": [\n" +
            "          \"喜剧\",\n" +
            "          \"电视剧\"\n" +
            "        ]\n" +
            "      },\n" +
            "      {\n" +
            "        \"actor\": [],\n" +
            "        \"aliasName\": [\n" +
            "          \"The Last Shangri-La\",\n" +
            "          \"最后的香格里拉\"\n" +
            "        ],\n" +
            "        \"area\": [\n" +
            "          \"美国\"\n" +
            "        ],\n" +
            "        \"award\": [],\n" +
            "        \"category\": \"电影\",\n" +
            "        \"company\": \"\",\n" +
            "        \"director\": [],\n" +
            "        \"episode\": [],\n" +
            "        \"hot\": \"1050\",\n" +
            "        \"img\": \"http://kchfpre.openspeech.cn/music_db_file/parastor/data/downdata/pictures/douban_video/4910862/28c5b8bad765edffc6edb826977804c7.jpg\",\n" +
            "        \"language\": \"普通话 ,国语 ,英语\",\n" +
            "        \"leadActor\": [],\n" +
            "        \"name\": \"最后的香格里拉\",\n" +
            "        \"publishTime\": \"2009-01-01\",\n" +
            "        \"score\": \"7.6\",\n" +
            "        \"screenWriter\": [],\n" +
            "        \"season\": \"\",\n" +
            "        \"source\": \"iflytek\",\n" +
            "        \"tags\": []\n" +
            "      },\n" +
            "      {\n" +
            "        \"actor\": [\n" +
            "          {\n" +
            "            \"name\": \"郑义 等\",\n" +
            "            \"roleName\": \"\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"aliasName\": [\n" +
            "          \"香格里拉\",\n" +
            "          \"shangri-la\"\n" +
            "        ],\n" +
            "        \"area\": [\n" +
            "          \"中国大陆\",\n" +
            "          \"美国\"\n" +
            "        ],\n" +
            "        \"award\": [],\n" +
            "        \"category\": \"电影\",\n" +
            "        \"company\": \"\",\n" +
            "        \"director\": [\n" +
            "          \"Naoko saito\",\n" +
            "          \"Masayuki Takase\"\n" +
            "        ],\n" +
            "        \"episode\": [],\n" +
            "        \"hot\": \"927\",\n" +
            "        \"img\": \"http://kchfpre.openspeech.cn/music_db_file/parastor/data/downdata/pictures/douban_video/3732745/6853f520d0061780c1a966b55d8f9d5f.jpg\",\n" +
            "        \"language\": \"普通话 ,国语 ,英语\",\n" +
            "        \"leadActor\": [\n" +
            "          {\n" +
            "            \"name\": \"郑义 等\",\n" +
            "            \"roleName\": \"\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"name\": \"香格里拉\",\n" +
            "        \"publishTime\": \"2008-01-01\",\n" +
            "        \"score\": \"7.3\",\n" +
            "        \"screenWriter\": [],\n" +
            "        \"season\": \"\",\n" +
            "        \"source\": \"iflytek\",\n" +
            "        \"tags\": []\n" +
            "      },\n" +
            "      {\n" +
            "        \"actor\": [\n" +
            "          {\n" +
            "            \"name\": \"胡歌\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王力可\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"金莎\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"扎西顿珠\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"巍子\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"多布杰\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"牟凤彬\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"曹操\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"施宁\",\n" +
            "            \"roleName\": \"格赞\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"山鹰\",\n" +
            "            \"roleName\": \"佩劳尔特\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"张浩\",\n" +
            "            \"roleName\": \"隆多\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"邸南南\",\n" +
            "            \"roleName\": \"和美丽\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"曹姗姗\",\n" +
            "            \"roleName\": \"曲美\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"李婳\",\n" +
            "            \"roleName\": \"三妹\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"黄杨\",\n" +
            "            \"roleName\": \"金宗\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"周彦呈\",\n" +
            "            \"roleName\": \"洛桑\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"aliasName\": [\n" +
            "          \"Shangri-la\",\n" +
            "          \"香格里拉\"\n" +
            "        ],\n" +
            "        \"area\": [\n" +
            "          \"中国大陆\"\n" +
            "        ],\n" +
            "        \"award\": [],\n" +
            "        \"category\": \"电视剧\",\n" +
            "        \"company\": \"霍尔果斯尚映文化传播有限公司\",\n" +
            "        \"director\": [\n" +
            "          \"蒋家骏\",\n" +
            "          \"蒋家俊\"\n" +
            "        ],\n" +
            "        \"episode\": [],\n" +
            "        \"hot\": \"861\",\n" +
            "        \"img\": \"http://kchfpre.openspeech.cn/music_db_file/parastor/data/downdata/pictures/douban_video/5313425/41e69d2359178746b6bf529712fc376c.jpg\",\n" +
            "        \"language\": \"国语\",\n" +
            "        \"leadActor\": [\n" +
            "          {\n" +
            "            \"name\": \"胡歌\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"王力可\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"金莎\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"扎西顿珠\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"巍子\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"多布杰\",\n" +
            "            \"roleName\": \"\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"name\": \"香格里拉\",\n" +
            "        \"publishTime\": \"2006-01-01\",\n" +
            "        \"score\": \"7.4\",\n" +
            "        \"screenWriter\": [\n" +
            "          \"周力军\"\n" +
            "        ],\n" +
            "        \"season\": \"\",\n" +
            "        \"source\": \"iflytek\",\n" +
            "        \"tags\": [\n" +
            "          \"电视剧\",\n" +
            "          \"家庭\",\n" +
            "          \"剧情\",\n" +
            "          \"战争\",\n" +
            "          \"爱情\",\n" +
            "          \"军事\",\n" +
            "          \"励志\"\n" +
            "        ]\n" +
            "      },\n" +
            "      {\n" +
            "        \"actor\": [\n" +
            "          {\n" +
            "            \"name\": \"栗山千明\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"小松彩夏\",\n" +
            "            \"roleName\": \"白石莉沙\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"星野真里\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"菅野莉央\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"小野寺昭\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"小林凉子\",\n" +
            "            \"roleName\": \"长谷川结衣\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"岩田小百合\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"袴田吉彦\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"井出卓也\",\n" +
            "            \"roleName\": \"园田纯平\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"东根作寿英\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"三津谷叶子\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"久世星佳\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"丸山智己\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"栗山千明\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"井出卓也\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"杉本哲太\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"Jun'ya Taniai\",\n" +
            "            \"roleName\": \"Kotaro takahashi\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"Ryusuke Ito\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"洼冢俊介\",\n" +
            "            \"roleName\": \"Shinya\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"松田悟志\",\n" +
            "            \"roleName\": \"Shun hayami\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"aliasName\": [\n" +
            "          \"秘密谍报员 エリカ\",\n" +
            "          \"Himitsu Chhin Erika\",\n" +
            "          \"秘密谍报员エリカ\",\n" +
            "          \"秘密谍报员绘里香\"\n" +
            "        ],\n" +
            "        \"area\": [\n" +
            "          \"日本\"\n" +
            "        ],\n" +
            "        \"award\": [],\n" +
            "        \"category\": \"电视剧\",\n" +
            "        \"company\": \"\",\n" +
            "        \"director\": [\n" +
            "          \"安见悟朗\",\n" +
            "          \"安见悟朗\",\n" +
            "          \"植田尚\"\n" +
            "        ],\n" +
            "        \"episode\": [],\n" +
            "        \"hot\": \"950\",\n" +
            "        \"img\": \"http://kchfpre.openspeech.cn/music_db_file/parastor/data/downdata/pictures/douban_video/6810840/eedcdc454fa687c4e91c232a940c4176.jpg\",\n" +
            "        \"language\": \"日语\",\n" +
            "        \"leadActor\": [\n" +
            "          {\n" +
            "            \"name\": \"栗山千明\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"小松彩夏\",\n" +
            "            \"roleName\": \"白石莉沙\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"星野真里\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"菅野莉央\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"小野寺昭\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"井出卓也\",\n" +
            "            \"roleName\": \"2011\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"杉本哲太\",\n" +
            "            \"roleName\": \"Soichi tozuka\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"Jun'ya Taniai\",\n" +
            "            \"roleName\": \"Kotaro takahashi\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"name\": \"秘密谍报员绘里香\",\n" +
            "        \"publishTime\": \"2011-01-01\",\n" +
            "        \"score\": \"5.9\",\n" +
            "        \"screenWriter\": [\n" +
            "          \"铃木智\",\n" +
            "          \"关绘理香\"\n" +
            "        ],\n" +
            "        \"season\": \"\",\n" +
            "        \"source\": \"iflytek\",\n" +
            "        \"tags\": [\n" +
            "          \"电视剧\",\n" +
            "          \"悬疑\"\n" +
            "        ]\n" +
            "      },\n" +
            "      {\n" +
            "        \"actor\": [\n" +
            "          {\n" +
            "            \"name\": \"耿兴龙\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"曹江\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"格刀\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"曹江河\",\n" +
            "            \"roleName\": \"\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"aliasName\": [\n" +
            "          \"最后的香格里拉\"\n" +
            "        ],\n" +
            "        \"area\": [\n" +
            "          \"中国大陆\",\n" +
            "          \"美国\"\n" +
            "        ],\n" +
            "        \"award\": [],\n" +
            "        \"category\": \"电影\",\n" +
            "        \"company\": \"\",\n" +
            "        \"director\": [\n" +
            "          \"耿兴龙\"\n" +
            "        ],\n" +
            "        \"episode\": [],\n" +
            "        \"hot\": \"309\",\n" +
            "        \"img\": \"http://kchfpre.openspeech.cn/music_db_file/parastor/data/downdata/pictures/douban_video/25844579/342ff0e0de9f3f858869340059a25b15.jpg\",\n" +
            "        \"language\": \"\",\n" +
            "        \"leadActor\": [\n" +
            "          {\n" +
            "            \"name\": \"耿兴龙\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"曹江\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"格刀\",\n" +
            "            \"roleName\": \"\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"name\": \"曹江河\",\n" +
            "            \"roleName\": \"\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"name\": \"最后的香格里拉\",\n" +
            "        \"publishTime\": \"2010-01-01\",\n" +
            "        \"score\": \"6.5\",\n" +
            "        \"screenWriter\": [],\n" +
            "        \"season\": \"\",\n" +
            "        \"source\": \"iflytek\",\n" +
            "        \"tags\": [\n" +
            "          \"剧情\",\n" +
            "          \"短片\",\n" +
            "          \"纪录片\"\n" +
            "        ]\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  \"save_history\": true,\n" +
            "  \"rc\": 0,\n" +
            "  \"semantic\": [\n" +
            "    {\n" +
            "      \"intent\": \"QUERY\",\n" +
            "      \"slots\": [\n" +
            "        {\n" +
            "          \"name\": \"artist\",\n" +
            "          \"value\": \"周杰伦\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"name\",\n" +
            "          \"value\": \"七里香\"\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  ],\n" +
            "  \"service\": \"video\",\n" +
            "  \"state\": {\n" +
            "    \"fg::video::default::default\": {\n" +
            "      \"artist\": \"1\",\n" +
            "      \"name\": \"1\",\n" +
            "      \"reserved_intent\": \"1\"\n" +
            "    }\n" +
            "  },\n" +
            "  \"text\": \"播放周杰伦的七里香\",\n" +
            "  \"uuid\": \"atn0095734d@ch03520dc0d3b26f1d01\",\n" +
            "  \"answer\": {\n" +
            "    \"text\": \"为您找到\\\"七里香\\\"\"\n" +
            "  },\n" +
            "  \"dialog_stat\": \"dataInvalid\",\n" +
            "  \"moreResults\": [\n" +
            "    {\n" +
            "      \"text\": \"播放周杰伦的七里香\",\n" +
            "      \"rc\": 3,\n" +
            "      \"semantic\": [\n" +
            "        {\n" +
            "          \"intent\": \"PLAY\",\n" +
            "          \"slots\": [\n" +
            "            {\n" +
            "              \"name\": \"artist\",\n" +
            "              \"value\": \"周杰伦\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"name\": \"song\",\n" +
            "              \"value\": \"心情\"\n" +
            "            }\n" +
            "          ]\n" +
            "        }\n" +
            "      ],\n" +
            "      \"service\": \"m\",\n" +
            "      \"state\": {\n" +
            "        \"fg::musicX::default::default\": {\n" +
            "          \"artist\": \"1\",\n" +
            "          \"song\": \"1\"\n" +
            "        }\n" +
            "      }\n" +
            "    }\n" +
            "  ],\n" +
            "  \"sid\": \"atn0095734d@ch03520dc0d3b26f1d01\"\n" +
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
        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=5a654648," + SpeechConstant.FORCE_LOGIN + "=true");// 传递科大讯飞appid
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

        speechDialog = new SpeechDialog(this, this.getResources().getString(R.string.no_network_text));

//        byte[] byteAutoReset = new byte[]{0x5A,0x50,0x03,0x02,0x02,0x01,0x05,0x0D,0x0A}; //复位
//        sendSerialMessageBytes(byteAutoReset);

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
            case AppController.LAUNCHER_TEXT:

                VideoBean videoBean = JsonParser.parseResultVideoBean(Message);
                if (videoBean.getMoreResults() != null) {
                    String ser = videoBean.getMoreResults().get(0).getService();
                    if(ser.equals("musicX")){   //如果在更多的选项中有音乐
                        MoreMusicXAction moreMusicXAction = new MoreMusicXAction("",videoBean.getMoreResults().get(0),"",this);
                        moreMusicXAction.start();
                    }else {
                        ShipingAction shipingAction = new ShipingAction("", videoBean, this, "");
                        shipingAction.start();
                    }
                }else {
                    ShipingAction shipingAction = new ShipingAction("", videoBean, this, "");
                    shipingAction.start();
                }
                break;
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
    public void SpeechError(SpeechError error) {
        int code = error.getErrorCode();
        if (code == 20001) {
            if (speechDialog != null && !speechDialog.isShowing()) {
                speechDialog.show();
            }
        }
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
        ReceivedSerialPortDataAction receivedSerialPortDataAction = new ReceivedSerialPortDataAction(value.split(" "), this);
        receivedSerialPortDataAction.start();

    }
}
