package com.levyx.location.test;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

/**
 * Copyright (C), 2002-2020, 苏宁易购电子商务有限公司
 * FileName: Test
 * Description:
 * Date: 2020/1/7 0:15
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名     修改时间        版本号       描述
 */
public class Test {

    public static void main(String[] args) throws UnsupportedEncodingException {
//        System.out.println(new BigDecimal(1e9).toPlainString());

        String s = ":0001:76$16BD143DB4:D6EEA24C00$177B8206DE:D7AD104C00$T:50:0005$171C4B223E$67";

        String[] split = s.split("\\$");
        System.out.println(split);

        String str = "00 F2 2D 74 1E E3 2D 74 18 2A 2D 77 0F 2E 3D 1F 0E D4 00";

        System.out.print( toBinaryString(str) );

    }

    public static String toBinaryString(String str) {

        if (str == null) return null;

        StringBuffer sb = new StringBuffer();

        byte[] bytes = str.getBytes();
        for (byte aByte : bytes) {
            sb.append(Integer.toBinaryString(aByte) + BIN_SEPARATOR);
        }
        return sb.toString();
    }

    private static final String BIN_SEPARATOR = " ";

}
