package com.levyx.location.cal;

/**
 * Copyright (C), 2002-2020, 苏宁易购电子商务有限公司
 * FileName: Main
 * Description:
 * Date: 2020/1/6 23:44
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名     修改时间        版本号       描述
 */
public class Main {

    public static void main(String[] args) {
        LocationParseProcessImpl process = new LocationParseProcessImpl();
//        process.msgAnalyze("&&&:0001:76$16BD143DB4:D6EEA24C00$177B8206DE:D7AD104C00$T:50:0005$171C4B223E$67");

//        process.initParam(0,0,   1.6,0,   1.6,1.6,  0,1.6,  0.8,1.6);
//
//        process.readFile("D:\\test\\duanfei\\TDOA\\0816_20190103.txt");

//        process.sendMsg("00 F2 2D 74 1E E3 2D 74 18 2A 2D 77 0F 2E 3D 1F 0E D4 00");
        process.sendMsg("00F22D741EE32D74182A2D770F2E3D1F0ED400");
    }
}
