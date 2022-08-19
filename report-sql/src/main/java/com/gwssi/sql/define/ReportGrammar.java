package com.gwssi.sql.define;


import lombok.Data;

@Data
public class ReportGrammar extends DefaultGrammar {
    //表号
    private String number;
    //报告期
    private String bgq;
    //行坐标
    private String row;
    //纵坐标
    private String column;


    @Override
    public Object operateExp(String exp) {
        return null;
    }

    @Override
    public boolean validateExp(String exp) {
        //验证主线是不是一致
        //验证格子是否存在
        //验证符号是否支持
        //验证函数是否支持
        return false;
    }
}
