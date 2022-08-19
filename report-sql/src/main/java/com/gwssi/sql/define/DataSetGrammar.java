package com.gwssi.sql.define;

import lombok.Data;

@Data
public class DataSetGrammar extends DefaultGrammar {

    //表名
    private String tableName;
    //实际的列名称
    private String var;
    //时期参数
    private String period;
    //公式类型
    private boolean type;


    @Override
    public Object operateExp(String exp) {
        return null;
    }

    @Override
    public boolean validateExp(String exp) {
        return false;
    }
}
