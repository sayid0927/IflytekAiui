package com.wengmengfan.javalib;

public class myClass {

    public static void main(String[] args) {

        String ff = ValueUtil.getInstance().decimalToHex(28120);

        byte[] dd = ValueUtil.getInstance().HexStringToBytes("6DD8");

        String value = ValueUtil.getInstance().bytesToHexStr(dd);

        System.out.println(ff);
        System.out.println(value);
    }
}
