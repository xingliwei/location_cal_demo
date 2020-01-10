package com.levyx.location.cal;

import com.levyx.location.entity.DistanceTimeEntity;
import com.levyx.location.entity.TagMsgInfoEntity;

import java.io.*;
import java.math.BigDecimal;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Copyright (C), 2002-2020, 苏宁易购电子商务有限公司
 * FileName: LocationParseProcessImpl
 * Description:
 * Date: 2020/1/6 23:40
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名     修改时间        版本号       描述
 */
public class LocationParseProcessImpl implements LocationParseProcess {


    //硬件的一个时钟对应的时间
    private final BigDecimal i = new BigDecimal(1/(128*499.2*1e6));

    //光速，单位米每秒
    private final long speed_of_light = 299792458;

    private DistanceTimeEntity entity;

    private TagMsgInfoEntity tagMsgInfoEntity;

    @Override
    public void initParam(double x0, double y0, double x1, double y1, double x2, double y2, double x3, double y3, double Sync_x, double Sync_y) {
        System.out.println("====================debug message==================");

        //单位时钟传播的距离
        BigDecimal distance_per_clock = i.multiply(new BigDecimal(speed_of_light));

        System.out.println("distance_per_clock:"+distance_per_clock);

        //计算标签距离基站0的距离
        double dis0 = distance(x0,y0,Sync_x,Sync_y);
        //计算标签传播到基站0花费的时间
        BigDecimal t0 = new BigDecimal(dis0).divide(distance_per_clock,50, BigDecimal.ROUND_HALF_UP);

        //计算标签距离基站1的距离
        double dis1 = distance(x1,y1,Sync_x,Sync_y);
        //计算标签传播到基站0花费的时间
        BigDecimal t1 = new BigDecimal(dis1).divide(distance_per_clock,50, BigDecimal.ROUND_HALF_UP);

        //计算标签距离基站2的距离
        double dis2 = distance(x2,y2,Sync_x,Sync_y);
        //计算标签传播到基站0花费的时间
        BigDecimal t2 = new BigDecimal(dis2).divide(distance_per_clock,50, BigDecimal.ROUND_HALF_UP);

        //计算标签距离基站3的距离
        double dis3 = distance(x3,y3,Sync_x,Sync_y);
        //计算标签传播到基站0花费的时间
        BigDecimal t3 = new BigDecimal(dis3).divide(distance_per_clock,50, BigDecimal.ROUND_HALF_UP);

        entity = new DistanceTimeEntity();
        entity.setDis0(dis0);
        entity.setT0(t0);
        entity.setDis1(dis1);
        entity.setT1(t1);
        entity.setDis2(dis2);
        entity.setT2(t2);
        entity.setDis3(dis3);
        entity.setT3(t3);
        System.out.println(entity);

        System.out.println("=================debug message end==================");
    }

    //三角函数计算距离
    public double distance(double x1,double y1,
                               double x2,double y2){
        return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
    }

    //
    @Override
    public void parseData(String line) {
        if (line==null || line.equals("")){
            return;
        }

    }


    /**
     * 处理协议数据
     **/
    public TagMsgInfoEntity msgAnalyze(String line){
        tagMsgInfoEntity = new TagMsgInfoEntity();
        String msgHeader = "&&&";
        if (line == null || line.indexOf(msgHeader) == -1){
            return tagMsgInfoEntity;
        }

        //"&&&:0001:76$16BD143DB4:D6EEA24C00$177B8206DE:D7AD104C00$T:50:0005$171C4B223E$67"
        String msg = line.split(msgHeader)[1];
        System.out.println(msg);

        String lenght_temp = msg.split("\\$")[0];
        String anthor_id = lenght_temp.split(":")[1];

        String temp_string = msg.split("\\$")[1];
        long sync0rx = Long.parseLong(temp_string.split(":")[0],16);


        long sync0tx = Long.parseLong(temp_string.split(":")[1],16);

        System.out.printf("同步信号时间信息0: %d  %d",sync0rx,sync0tx);
        temp_string = msg.split("\\$")[2];
        long sync1rx = Long.parseLong(temp_string.split(":")[0],16);
        long sync1tx = Long.parseLong(temp_string.split(":")[1],16);

        System.out.printf("同步信号时间信息1: %d  %d",sync1rx,sync1tx);
        System.out.printf("时钟比例:%.10f",((sync1tx-sync0tx)/(double)(sync1rx-sync0rx)));

        temp_string = msg.split("\\$")[3];
        long tag_seq = Long.parseLong(temp_string.split(":")[1],16);

        String tag_id =  temp_string.split(":")[2];

        temp_string = msg.split("\\$")[4];

        long tag_recive = Long.parseLong(temp_string.split(":")[0],16);
        System.out.printf("标签信息：Seq=%d ID=%s 接收时间 t=%d",tag_seq,tag_id,tag_recive);
        long sync_tag_time =((sync1tx-sync0tx)/(sync1rx-sync0rx))*(tag_recive-sync0rx) + sync0tx;
        System.out.printf("同步后时间信息:%d %d %d\n",sync0tx,sync_tag_time,sync1tx);

        long tag_timestamp = sync_tag_time;

        tagMsgInfoEntity.setAuthorId(anthor_id);
        tagMsgInfoEntity.setTagId(tag_id);
        tagMsgInfoEntity.setTagSeq(tag_seq);
        tagMsgInfoEntity.setTag_timestamp(tag_timestamp);

        System.out.println(tagMsgInfoEntity);

        return tagMsgInfoEntity;
    }

    //文件解析
    @Override
    public void readFile(String filePath){
        try {
            FileReader file = new FileReader(filePath);
            BufferedReader bf = new BufferedReader(file);
            String str;
            while ((str = bf.readLine()) != null) {
                parseData(str);
            }
            bf.close();
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("文件不存在！");
        } catch (IOException e1){
            e1.printStackTrace();
        }
    }

    /**
     * hex转byte数组
     * @param hex
     * @return
     */
    public static byte[] hexToByte(String hex){
        int m = 0, n = 0;
        int byteLen = hex.length() / 2; // 每两个字符描述一个字节
        byte[] ret = new byte[byteLen];
        for (int i = 0; i < byteLen; i++) {
            m = i * 2 + 1;
            n = m + 1;
            int intVal = Integer.decode("0x" + hex.substring(i * 2, m) + hex.substring(m, n));
            ret[i] = Byte.valueOf((byte)intVal);
        }
        return ret;
    }



    public void sendMsg(String str){
        try {
            //创建Socket相当于创建码头
            DatagramSocket socket = new DatagramSocket();

            DatagramPacket packet = new DatagramPacket(hexToByte(str), hexToByte(str).length, InetAddress.getByName("127.0.0.1"), 4444);

            socket.send(packet); //发货,将数据发出去

            System.out.println("发送完成");

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
