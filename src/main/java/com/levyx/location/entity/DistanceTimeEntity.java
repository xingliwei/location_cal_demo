package com.levyx.location.entity;

import java.math.BigDecimal;

/**
 * [功能描述]：基站的距离和时间信息
 */
public class DistanceTimeEntity {

    //标签距离基站0的距离
    private double dis0;
    //标签传播到基站0花费的时间
    private BigDecimal t0;

    //标签距离基站1的距离
    private double dis1;
    //标签传播到基站1花费的时间
    private BigDecimal t1;

    //标签距离基站2的距离
    private double dis2;
    //标签传播到基站2花费的时间
    private BigDecimal t2;

    //标签距离基站3的距离
    private double dis3;
    //标签传播到基站3花费的时间
    private BigDecimal t3;

    public double getDis0() {
        return dis0;
    }

    public void setDis0(double dis0) {
        this.dis0 = dis0;
    }

    public BigDecimal getT0() {
        return t0;
    }

    public void setT0(BigDecimal t0) {
        this.t0 = t0;
    }

    public double getDis1() {
        return dis1;
    }

    public void setDis1(double dis1) {
        this.dis1 = dis1;
    }

    public BigDecimal getT1() {
        return t1;
    }

    public void setT1(BigDecimal t1) {
        this.t1 = t1;
    }

    public double getDis2() {
        return dis2;
    }

    public void setDis2(double dis2) {
        this.dis2 = dis2;
    }

    public BigDecimal getT2() {
        return t2;
    }

    public void setT2(BigDecimal t2) {
        this.t2 = t2;
    }

    public double getDis3() {
        return dis3;
    }

    public void setDis3(double dis3) {
        this.dis3 = dis3;
    }

    public BigDecimal getT3() {
        return t3;
    }

    public void setT3(BigDecimal t3) {
        this.t3 = t3;
    }

    @Override
    public String toString() {
        return "DistanceTimeEntity{" +
                "\ndis0=" + dis0 +
                ", \nt0=" + t0 +
                ", \ndis1=" + dis1 +
                ", \nt1=" + t1 +
                ", \ndis2=" + dis2 +
                ", \nt2=" + t2 +
                ", \ndis3=" + dis3 +
                ", \nt3=" + t3 +
                '}';
    }
}
