package com.gwssi.sql.define;

import lombok.Data;

@Data
public class DataSetGrammar {

    //表名
    private String tableName;
    //实际的列名称
    private String var;
    //时期参数
    private String period;
}
