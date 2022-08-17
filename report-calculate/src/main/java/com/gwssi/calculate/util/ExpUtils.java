package com.gwssi.calculate.util;

import org.apache.commons.lang3.StringUtils;

import java.util.List;


public class ExpUtils {


    public static void getExp(String exp, List<String> coordinate) {
        if (StringUtils.isEmpty(exp)) throw new IllegalArgumentException("表达式不能为空");
        if (!exp.contains("=")) throw new IllegalArgumentException("汇总表达式格式不正确");
        String[] split = exp.split("=");
        if (split.length != 2) throw new IllegalArgumentException("汇总表达式只能出现一个等号");
        //判断左边是否有*号
        String left = split[0];
        String right = split[1];

    }

    /**
     * 等号左边字符串
     * 验证字符串中是否出现*号和范围
     */
    private static void validate(String left) {
        //包含中括号
        char[] array = left.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        char c1 = array[0];
        if (c1 != '[') throw new IllegalArgumentException("提取公式左边格式不正确");
        for (char c : array) {
            stringBuilder.append(c);
            if (c == '*') i++;
            if (c == ']') break;
        }
        //判断是否有大括号范围
        if (left.length() == stringBuilder.length()) return;
        //判断大括号个数是否和星号个数相同
        if (i != BracketUtil.matcherBracketValue(left.substring(stringBuilder.length()), false).size())
            throw new IllegalArgumentException("*号范围和大括号不匹配");
    }
}
