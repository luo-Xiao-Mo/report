package com.gwssi.sql.define;


import com.gwssi.sql.callback.ReportCallBack;

public interface Grammar {

    //解析当前表达式
    Object operateExp(String exp);

    //验证当前表达式
    boolean validateExp(String exp);

    //设置是提取公式还是筛选公式
    void setExpType(boolean type);

    //设置当前的回调接口
    void setReportCallBack(ReportCallBack reportCallBack);


    void parse(String expression);

}
