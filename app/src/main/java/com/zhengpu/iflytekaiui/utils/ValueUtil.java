package com.zhengpu.iflytekaiui.utils;

/**
 * Created zhanghuagang on 2017/3/16.
 * byte[] to decimals
 * byte[] to hex
 * hex to byte[]
 * hex to decimal
 */

public class ValueUtil {

    private static ValueUtil instance = null;

    private ValueUtil() {
    }

    public static ValueUtil getInstance() {
        synchronized (ValueUtil.class) {
            if (instance == null) {
                instance = new ValueUtil();
            }
        }
        return instance;
    }

    public static void destroy() {
        if (null != instance) {
            instance = null;
        }
    }

    //byte[] 转十进制
    public int[] bytesToDemicals(byte[] bytes) {
        int[] array = new int[bytes.length];
        int i = 0;
        for (byte b : bytes)
            array[i++] = b & 0xff;
        return array;
    }

    //十六进制转十进制
    public int hexToDecimal(String s) {
        String digits = "0123456789ABCDEF";
        s = s.toUpperCase();
        int val = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int d = digits.indexOf(c);
            val = 16 * val + d;
        }
        return val;
    }

    //十进制转十六进制
    public String decimalToHex(int d) {
        String digits = "0123456789ABCDEF";
        if (d == 0) return "0";
        String hex = "";
        while (d > 0) {
            int digit = d % 16;                // rightmost digit
            hex = digits.charAt(digit) + hex;  // string concatenation
            d = d / 16;
        }
        return hex;
    }

    // byte[]转十六进制("BE B0 BC 92")
    public String bytesToHexStr(byte[] bytes) {

        String stmp;
        StringBuilder sb = new StringBuilder("");
        for (byte aByte : bytes) {

            stmp = Integer.toHexString(aByte & 0xFF);
            sb.append((stmp.length() == 1) ? "0" + stmp : stmp);
            sb.append(" ");

        }
        return sb.toString().toUpperCase().trim();
    }

    // 十六进制字符串("BEBOBC92")转bytes
    public byte[] HexStringToBytes(String hexstr) {
        byte[] b = new byte[hexstr.length() / 2];
        int j = 0;
        for (int i = 0; i < b.length; i++) {
            char c0 = hexstr.charAt(j++);
            char c1 = hexstr.charAt(j++);
            b[i] = (byte) ((parse(c0) << 4) | parse(c1));
        }
        return b;
    }

    private static int parse(char c) {
        if (c >= 'a')
            return (c - 'a' + 10) & 0x0f;
        if (c >= 'A')
            return (c - 'A' + 10) & 0x0f;
        return (c - '0') & 0x0f;
    }

    public static int getLow4(byte data) {//获取低四位
        int low;
        low = (data & 0x0f);
        return low;
    }

    public static int getHeight4(byte data) {//获取高四位
        int height;
        height = ((data & 0xf0) >> 4);
        return height;

    }

    public static final byte MASK_BIT0 = 0x01;
    public static final byte MASK_BIT1 = 0x02;
    public static final byte MASK_BIT2 = 0x04;
    public static final byte MASK_BIT3 = 0x08;
    public static final byte MASK_BIT4 = 0x10;
    public static final byte MASK_BIT5 = 0x20;
    public static final byte MASK_BIT6 = 0x30;
    public static final byte MASK_BIT7 = 0x40;

    public static boolean isBitnTrue(byte data, int bit_n) {
        boolean flag = false;
        switch (bit_n) {
            case 0:
                flag = ((data & MASK_BIT0) > 0);
                break;
            case 1:
                flag = ((data & MASK_BIT1) > 0);
                break;
            case 2:
                flag = ((data & MASK_BIT2) > 0);
                break;
            case 3:
                flag = ((data & MASK_BIT3) > 0);
                break;
            case 4:
                flag = ((data & MASK_BIT4) > 0);
                break;
            case 5:
                flag = ((data & MASK_BIT5) > 0);
                break;
            case 6:
                flag = ((data & MASK_BIT6) > 0);
                break;
            case 7:
                flag = ((data & MASK_BIT7) > 0);
                break;
        }
        return flag;
    }

}
