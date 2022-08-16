package com.gwssi.sql.entity;


import lombok.Data;

@Data
public class ExpEntity {
    //主栏条件
    private String main;
    //宾栏条件
    private String slider;
    //汇总条件
    private String collect;
    //聚集方式
    private String gather;
}
