package com.gwssi.sql.define;

import lombok.Data;

import java.util.List;

@Data
public class ReportGrammar {
    //表号
    private String number;
    //报告期
    private String bgq;
    //行坐标
    private String row;
    //纵坐标
    private String column;
    //时期参数
    private String period;
    //行坐标通配
    private List<String> rowList;
    //总坐标通配
    private List<String> columnList;
}
