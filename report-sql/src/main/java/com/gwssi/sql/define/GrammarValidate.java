package com.gwssi.sql.define;

import com.gwssi.sql.BracketUtil;
import com.gwssi.sql.callback.ReportCallBack;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class GrammarValidate {

    /**
     * 判断当前报表采用哪种语法格式
     *
     * @param left 当前的语法表达式
     */
    public static Grammar validate(String left, ReportCallBack reportCallBack) {
//        if (StringUtils.isEmpty(left)) throw new IllegalArgumentException("参数信息不能为空");
//        if (BracketUtil.isContainBrace(left)) throw new IllegalArgumentException("参数信息不为中括号表达式");
//        List<String> list = BracketUtil.matcherBracketValue(left);
//        String oneBrace = BracketUtil.replace(list.get(0));
//        String[] split = oneBrace.split(",");
////        String info = split[0];
        Grammar grammar = new ReportGrammar();
        grammar.setReportCallBack(reportCallBack);
        return grammar;
    }
}
