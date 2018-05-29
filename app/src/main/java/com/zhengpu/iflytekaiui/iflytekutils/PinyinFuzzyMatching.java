package com.zhengpu.iflytekaiui.iflytekutils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.zhengpu.iflytekaiui.R;
import com.zhengpu.iflytekaiui.base.AppController;
import com.zhengpu.iflytekaiui.base.BaseApplication;
import com.zhengpu.iflytekaiui.service.SpeechRecognizerService;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.zhengpu.iflytekaiui.service.SpeechRecognizerService.sendSerialMessageBytes;

/**
 * sayid ....
 * Created by wengmf on 2018/5/14.
 */

public class PinyinFuzzyMatching {

    private Context context;
    private String str;

    public PinyinFuzzyMatching(Context context, String str) {
        this.context = context;
        this.str = str;
    }

    public boolean isMatching() {
        boolean Matching = false;
        try {

            String pinyin = getPinyin(str);
            List<Character> charlist = getCharString(str);


            // 头右转
            if (!Matching) {
                if (pinyin.contains("tao") || pinyin.contains("tou") || pinyin.contains("dou") || pinyin.contains("dao")) {
                    if (pinyin.contains("you") || pinyin.contains("yong")) {
                        if (pinyin.contains("zhuan") || pinyin.contains("zhuang") || pinyin.contains("zuan") || pinyin.contains("cuan") || pinyin.contains("chuan")) {
                            sendSerialMessageBytes(AppController.byteHeadRight);
                            SpeechRecognizerService.startSpeech("OPENAPPTEST.RobotCommandPinYin", "多多头右转", "多多头右转");
                            BaseApplication.MAIN_EXECUTOR.schedule(new Runnable() {
                                @Override
                                public void run() {
                                    sendSerialMessageBytes(new byte[]{0x5A, 0x50, 0x05, 0x02, 0x01, 0x07, 0x00, 0x00, 0x0A, 0x0D, 0x0A});
                                }
                            }, 2, TimeUnit.SECONDS);
                            Matching = true;
                        }
                    }
                }
            }

            // 头左转
            if (!Matching) {
                if (pinyin.contains("tao") || pinyin.contains("tou") || pinyin.contains("dou") || pinyin.contains("dao")) {
                    if (pinyin.contains("zhuo") || pinyin.contains("zuo") || pinyin.contains("chuo") || pinyin.contains("cuo") || pinyin.contains("zhe") || pinyin.contains("ze")) {
                        if (pinyin.contains("zhuan") || pinyin.contains("zhuang") || pinyin.contains("zuan") || pinyin.contains("cuan") || pinyin.contains("chuan")) {
                            sendSerialMessageBytes(AppController.byteHeadLeft);
                            SpeechRecognizerService.startSpeech("OPENAPPTEST.RobotCommandPinYin", "多多头左转", "多多头左转");
                            BaseApplication.MAIN_EXECUTOR.schedule(new Runnable() {
                                @Override
                                public void run() {
                                    sendSerialMessageBytes(new byte[]{0x5A, 0x50, 0x05, 0x02, 0x01, 0x07, 0x00, 0x00, 0x0A, 0x0D, 0x0A});
                                }
                            }, 2, TimeUnit.SECONDS);
                            Matching = true;
                        }
                    }
                }
            }

            // 头复位
            if (!Matching) {
                if (pinyin.contains("tao") || pinyin.contains("tou") || pinyin.contains("dou") || pinyin.contains("dao") || pinyin.contains("ta") || pinyin.contains("bao")) {
                    if (pinyin.contains("fu") || pinyin.contains("hu") || pinyin.contains("bu")) {
                        if (pinyin.contains("wei")) {
                            sendSerialMessageBytes(AppController.byteHeadReset);
                            SpeechRecognizerService.startSpeech("OPENAPPTEST.RobotCommandPinYin", "看多多头复位", "看多多头复位");
                            Matching = true;
                        }
                    }
                }
            }

            //手复位
            if (!Matching) {
                if (pinyin.contains("sho") || pinyin.contains("shou") || pinyin.contains("sou") || pinyin.contains("sou") || pinyin.contains("shao") || pinyin.contains("sao")) {
                    if (pinyin.contains("fu") || pinyin.contains("hu") || pinyin.contains("bu")) {
                        if (pinyin.contains("wei")) {
                            sendSerialMessageBytes(AppController.byteHandReset);
                            SpeechRecognizerService.startSpeech("OPENAPPTEST.RobotCommandPinYin", "看多多手复位", "看多多手复位");
                            Matching = true;
                        }
                    }
                }
            }
            //放右手
            if (!Matching) {
                if (pinyin.contains("fang") || pinyin.contains("fan") || pinyin.contains("fa")) {
                    if (pinyin.contains("you") || pinyin.contains("yong")) {
                        if (pinyin.contains("sho") || pinyin.contains("shou") || pinyin.contains("sou") || pinyin.contains("sou") || pinyin.contains("shao") || pinyin.contains("sao")) {
                            sendSerialMessageBytes(AppController.byteDownRightHand);
                            SpeechRecognizerService.startSpeech("OPENAPPTEST.RobotCommandPinYin", "多多放右手", "多多放右手");
                            BaseApplication.MAIN_EXECUTOR.schedule(new Runnable() {
                                @Override
                                public void run() {
                                    sendSerialMessageBytes(new byte[]{0x5A, 0x50, 0x05, 0x01, 0x01, 0x07, 0x00, 0x00, 0x09, 0x0D, 0x0A});
                                }
                            }, 2, TimeUnit.SECONDS);
                            Matching = true;
                        }
                    }
                }
            }
            //放左手
            if (!Matching) {
                if (pinyin.contains("fang") || pinyin.contains("fan") || pinyin.contains("fa")) {
                    if (pinyin.contains("zhuo") || pinyin.contains("zuo") || pinyin.contains("chuo") || pinyin.contains("cuo") || pinyin.contains("zhe") || pinyin.contains("ze")) {
                        if (pinyin.contains("sho") || pinyin.contains("shou") || pinyin.contains("sou") || pinyin.contains("sou") || pinyin.contains("shao") || pinyin.contains("sao")) {
                            sendSerialMessageBytes(AppController.byteDownLeftHand);
                            SpeechRecognizerService.startSpeech("OPENAPPTEST.RobotCommandPinYin", "多多放左手", "多多放左手");
                            BaseApplication.MAIN_EXECUTOR.schedule(new Runnable() {
                                @Override
                                public void run() {
                                    sendSerialMessageBytes(new byte[]{0x5A, 0x50, 0x05, 0x01, 0x01, 0x07, 0x00, 0x00, 0x09, 0x0D, 0x0A});
                                }
                            }, 2, TimeUnit.SECONDS);
                            Matching = true;
                        }
                    }
                }
            }
            //抬左手
            if (!Matching) {
                if (pinyin.contains("tai") || pinyin.contains("dai") || pinyin.contains("tan") || pinyin.contains("gai") || pinyin.contains("ta") || pinyin.contains("pai")) {
                    if (pinyin.contains("zhuo") || pinyin.contains("zuo") || pinyin.contains("chuo") || pinyin.contains("cuo") || pinyin.contains("zhe") || pinyin.contains("ze")) {
                        if (pinyin.contains("sho") || pinyin.contains("shou") || pinyin.contains("sou") || pinyin.contains("sou") || pinyin.contains("shao") || pinyin.contains("sao")) {
                            sendSerialMessageBytes(AppController.byteUpLeftHand);
                            SpeechRecognizerService.startSpeech("OPENAPPTEST.RobotCommandPinYin", "多多抬左手", "多多抬左手");
                            BaseApplication.MAIN_EXECUTOR.schedule(new Runnable() {
                                @Override
                                public void run() {
                                    sendSerialMessageBytes(new byte[]{0x5A, 0x50, 0x05, 0x01, 0x01, 0x07, 0x00, 0x00, 0x09, 0x0D, 0x0A});
                                }
                            }, 2, TimeUnit.SECONDS);
                            Matching = true;
                        }
                    }
                }
            }
            //抬右手
            if (!Matching ) {
                if (pinyin.contains("tai") || pinyin.contains("tan") || pinyin.contains("dai") || pinyin.contains("gai") || pinyin.contains("ta") || pinyin.contains("pai")) {
                    if (pinyin.contains("you") || pinyin.contains("yon") || pinyin.contains("yong")) {
                        if (pinyin.contains("sho") || pinyin.contains("shou") || pinyin.contains("sou") || pinyin.contains("sou") || pinyin.contains("shao") || pinyin.contains("sao")) {
                            sendSerialMessageBytes(AppController.byteUpRightHand);
                            SpeechRecognizerService.startSpeech("OPENAPPTEST.RobotCommandPinYin", "多多抬右手", "多多抬右手");
                            BaseApplication.MAIN_EXECUTOR.schedule(new Runnable() {
                                @Override
                                public void run() {
                                    sendSerialMessageBytes(new byte[]{0x5A, 0x50, 0x05, 0x01, 0x01, 0x07, 0x00, 0x00, 0x09, 0x0D, 0x0A});
                                }
                            }, 2, TimeUnit.SECONDS);
                            Matching = true;
                        }
                    }
                }
            }
            //前进
            if (!Matching) {
                if (pinyin.contains("qian") || pinyin.contains("tian") || pinyin.contains("lian") || pinyin.contains("nian")) {
                    if (pinyin.contains("jing") || pinyin.contains("jin") || pinyin.contains("qian") || pinyin.contains("qiang")) {
                        sendSerialMessageBytes(AppController.byteWalkForward);
                        SpeechRecognizerService.startSpeech("OPENAPPTEST.RobotCommandPinYin", "嘟嘟嘟，多多出发啦", "嘟嘟嘟，多多出发啦");
                        BaseApplication.MAIN_EXECUTOR.schedule(new Runnable() {
                            @Override
                            public void run() {
                                sendSerialMessageBytes(new byte[]{0x5A, 0x50, 0x05, 0x03, 0x01, 0x06, 0x00, 0x00, 0x0A, 0x0D, 0x0A});
                            }
                        }, 2, TimeUnit.SECONDS);
                        Matching = true;
                    }
                }
            }
            // 后退
            if (!Matching) {
                if (pinyin.contains("hou") || pinyin.contains("hao")) {
                    if (pinyin.contains("tui") || pinyin.contains("dui")) {
                        sendSerialMessageBytes(AppController.byteWalkBack);
                        SpeechRecognizerService.startSpeech("OPENAPPTEST.RobotCommandPinYin", "注意，多多倒车啦", "注意，多多倒车啦");
                        BaseApplication.MAIN_EXECUTOR.schedule(new Runnable() {
                            @Override
                            public void run() {
                                sendSerialMessageBytes(new byte[]{0x5A, 0x50, 0x05, 0x03, 0x01, 0x06, 0x00, 0x00, 0x0A, 0x0D, 0x0A});
                            }
                        }, 2, TimeUnit.SECONDS);
                        Matching = true;
                    }
                }
            }
            // 左转
            if (!Matching) {
                if (pinyin.contains("zhuo") || pinyin.contains("zuo") || pinyin.contains("chuo") || pinyin.contains("cuo") || pinyin.contains("zhe")) {
                    if (pinyin.contains("zhuan") || pinyin.contains("zuan") || pinyin.contains("cuan") || pinyin.contains("chuan")) {
                        sendSerialMessageBytes(AppController.byteWalkRight);
                        SpeechRecognizerService.startSpeech("OPENAPPTEST.RobotCommandPinYin", "向左扭扭", "向左扭扭");
                        BaseApplication.MAIN_EXECUTOR.schedule(new Runnable() {
                            @Override
                            public void run() {
                                sendSerialMessageBytes(new byte[]{0x5A, 0x50, 0x05, 0x03, 0x01, 0x06, 0x00, 0x00, 0x0A, 0x0D, 0x0A});
                            }
                        }, 3, TimeUnit.SECONDS);
                        Matching = true;
                    }
                }
            }

            // 右转
            if (!Matching) {
                if (pinyin.contains("you") || pinyin.contains("yon") || pinyin.contains("yong")) {
                    if (pinyin.contains("zhuan") || pinyin.contains("zuan") || pinyin.contains("cuan") || pinyin.contains("chuan")) {
                        sendSerialMessageBytes(AppController.byteWalkLeft);
                        SpeechRecognizerService.startSpeech("OPENAPPTEST.RobotCommandPinYin", "向右扭扭", "向右扭扭");
                        BaseApplication.MAIN_EXECUTOR.schedule(new Runnable() {
                            @Override
                            public void run() {
                                sendSerialMessageBytes(new byte[]{0x5A, 0x50, 0x05, 0x03, 0x01, 0x06, 0x00, 0x00, 0x0A, 0x0D, 0x0A});
                            }
                        }, 3, TimeUnit.SECONDS);
                        Matching = true;
                    }
                }
            }
            // 后转
            if (!Matching) {
                if (pinyin.contains("hou") || pinyin.contains("hao") || pinyin.contains("ao")) {
                    if (pinyin.contains("zhuan") || pinyin.contains("zuan") || pinyin.contains("cuan") || pinyin.contains("chuan")) {
                        sendSerialMessageBytes(AppController.byteWalkLeft);
                        SpeechRecognizerService.startSpeech("OPENAPPTEST.RobotCommandPinYin", "看多多乾坤大挪移", "看多多乾坤大挪移");
                        BaseApplication.MAIN_EXECUTOR.schedule(new Runnable() {
                            @Override
                            public void run() {
                                sendSerialMessageBytes(new byte[]{0x5A, 0x50, 0x05, 0x03, 0x01, 0x06, 0x00, 0x00, 0x0A, 0x0D, 0x0A});
                            }
                        }, 5, TimeUnit.SECONDS);
                        Matching = true;
                    }
                }
            }
            // 抬头
            if (!Matching) {
                if (pinyin.contains("tai") || pinyin.contains("dai") || pinyin.contains("gai") || pinyin.contains("pai")) {
                    if (pinyin.contains("tao") || pinyin.contains("tou") || pinyin.contains("dou") || pinyin.contains("dao")) {
                        sendSerialMessageBytes(AppController.byteHeadUp);
                        SpeechRecognizerService.startSpeech("OPENAPPTEST.RobotCommandPinYin", "多多抬头", "多多抬头");
                        BaseApplication.MAIN_EXECUTOR.schedule(new Runnable() {
                            @Override
                            public void run() {
                                sendSerialMessageBytes(new byte[]{0x5A, 0x50, 0x05, 0x02, 0x01, 0x07, 0x00, 0x00, 0x0A, 0x0D, 0x0A});
                            }
                        }, 2, TimeUnit.SECONDS);
                        Matching = true;
                    }
                }
            }

            // 低头
            if (!Matching) {
                if (pinyin.contains("di") || pinyin.contains("ti") || pinyin.contains("ting") || pinyin.contains("tin")) {
                    if (pinyin.contains("tao") || pinyin.contains("tou") || pinyin.contains("dou") || pinyin.contains("dao")) {
                        sendSerialMessageBytes(AppController.byteHeadDown);
                        SpeechRecognizerService.startSpeech("OPENAPPTEST.RobotCommandPinYin", "多多低头", "多多低头");
                        BaseApplication.MAIN_EXECUTOR.schedule(new Runnable() {
                            @Override
                            public void run() {
                                sendSerialMessageBytes(new byte[]{0x5A, 0x50, 0x05, 0x02, 0x01, 0x07, 0x00, 0x00, 0x0A, 0x0D, 0x0A});
                            }
                        }, 2, TimeUnit.SECONDS);
                        Matching = true;
                    }
                }
            }

            // 跳舞
            if (!Matching) {
                if (pinyin.contains("tiao") || pinyin.contains("diao")) {
                    if (pinyin.contains("wu")) {
                        Intent gotoDance = new Intent();
                        gotoDance.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        gotoDance.setComponent(new ComponentName("com.zeunpro.dance", "com.zeunpro.dance.ui.DanceAct"));
                        context.startActivity(gotoDance);
                        SpeechRecognizerService.startSpeech("DanceAction", "多多开始摇摆了", "多多开始摇摆了");
                        SpeechRecognizerService.Destroy();
                        Matching = true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return Matching;
    }


    private List<Character>  getCharString(String str) {

        List<Character> chars = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {

            char c = str.charAt(i);
            if (c >= '0' && c <= '9') {
            } else if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
            } else {
                chars.add(c);
            }
        }

        return  chars;
    }

    /**
     * 汉字转全拼
     */
    private String getPinyin(String str) throws Exception {
        if (str == null || str.length() == 0) {
            return "";
        }
        char[] t1;
        t1 = str.toCharArray();
        String[] t2;
        // 设置汉字拼音输出的格式
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 小写
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);

        StringBuilder t4 = new StringBuilder();
        for (char aT1 : t1) {
            // 判断是否为汉字字符
            if (Character.toString(aT1).matches("[\\u4E00-\\u9FA5]+")) {
                // 将汉字的几种全拼都存到t2数组中
                t2 = PinyinHelper.toHanyuPinyinStringArray(aT1, t3);
                t4.append(t2[0]);// 取出该汉字全拼的第一种读音并连接到字符串t4后
            } else {
                // 如果不是汉字字符，直接取出字符并连接到字符串t4后
                t4.append(Character.toString(aT1));
            }
        }
        return t4.toString();
    }

}
