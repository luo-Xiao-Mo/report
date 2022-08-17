package com.gwssi.sql.entity;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TableEntity {

    //中括号表达式中的表名
    private String tableName;
    //中括号表达式中的列名
    private String coordinate;
}
