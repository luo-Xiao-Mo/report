package com.gwssi.sql.entity;


import lombok.Data;

import java.util.List;

@Data
public class ExpEntity {
    //主栏条件
    private List<String> main;
    //宾栏条件
    private List<String> slider;
    //汇总条件
    private String collect;
    //聚集方式 sum max min avg
    private String gather;
}
