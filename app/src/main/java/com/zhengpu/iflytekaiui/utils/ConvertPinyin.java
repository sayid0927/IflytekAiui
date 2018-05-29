package com.zhengpu.iflytekaiui.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * sayid ....
 * Created by wengmf on 2018/5/14.
 */

public class ConvertPinyin {


    /**
     * 汉字转全拼
     *
     * @param str
     * @return
     * @throws Exception
     */
    public static String getPinyin(String str) throws Exception {
        if (str == null || str.length() == 0) {
            return "";
        }
        char[] t1 = null;
        t1 = str.toCharArray();
        String[] t2 = new String[t1.length];
         // 设置汉字拼音输出的格式
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 小写
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);

        String t4 = "";
        int t0 = t1.length;
        try {
            for (int i = 0; i < t0; i++) {
                // 判断是否为汉字字符
                if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
                 // 将汉字的几种全拼都存到t2数组中
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
                    t4 += t2[0];// 取出该汉字全拼的第一种读音并连接到字符串t4后
                } else {
                 // 如果不是汉字字符，直接取出字符并连接到字符串t4后
                    t4 += Character.toString(t1[i]);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            throw e;
        }
        return t4;
    }
}
