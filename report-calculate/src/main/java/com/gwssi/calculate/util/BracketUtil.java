package com.gwssi.calculate.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BracketUtil {

    /**
     * 判断表达式中是否有中括号
     *
     * @param originalStr 中括号字符串
     * @return boolean
     */
    public static boolean isContainBrace(String originalStr) {
        return !originalStr.contains("[") && !originalStr.contains("]");
    }

    /**
     * 判断表达式中是否有大括号表达式
     *
     * @param originalStr 大括号字符串
     * @return boolean
     */
    public static boolean isContainBigBrace(String originalStr) {
        return !originalStr.contains("{") && !originalStr.contains("}");
    }

    /**
     * 正则表达式匹配出中括号内容
     *
     * @param originalStr 中括号字符串
     * @return list 中括号的内容
     */
    public static List<String> matcherBracketValue(String originalStr) {
        List<String> result = new ArrayList<>();
        // 查找规则公式中中括号以内的字符
        Pattern p = Pattern.compile("\\[.*?\\]");
        Matcher m = p.matcher(originalStr);
        // 遍历找到的所有中括号
        while (m.find()) {
            String param = m.group().replaceAll("\\[\\]", "");
            result.add(param);
        }
        return result;
    }

    /**
     * 正则表达式匹配出大括号内容
     *
     * @param originalStr 大括号内容
     * @param flag        是否替换大括号
     * @return list 中括号的内容
     */
    public static List<String> matcherBracketValue(String originalStr, boolean flag) {
        if (isContainBigBrace(originalStr)) {
            return null;
        }
        List<String> result = new ArrayList<>();
        // 查找规则公式中大括号以内的字符
        Pattern p = Pattern.compile("\\{.*?\\}");
        Matcher m = p.matcher(originalStr);
        // 遍历找到的所有大括号
        while (m.find()) {
            String param = m.group().replaceAll("\\{\\}", "");
            if (flag) {
                param = replaceBigBracket(param);
            }
            result.add(param);
        }
        return result;
    }

    /**
     * 判断集合是否为空
     *
     * @param collection 集合信息
     * @return boolean
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 替换字符串
     *
     * @param expression 表达式信息
     * @return String 替换后的字符串
     */
    public static String replace(String expression) {
        return expression.replace("[", "").replace("]", "");
    }

    /**
     * 替换字符串
     *
     * @param expression 表达式信息
     * @return String 替换后的字符串
     */
    public static String replaceBigBracket(String expression) {
        return expression.replace("{", "").replace("}", "");
    }

    /**
     * 替换字符串
     *
     * @param expression 表达式信息
     * @return String 替换后的字符串
     */
    public static String replaceSmallBracket(String expression) {
        return expression.replace("(", "").replace(")", "").trim();
    }

    /**
     * 匹配字符串是否为英文和数组组合
     * 弱匹配=>列名出现格子序号匹配时、会出问题
     *
     * @param str 字符串
     * @return boolean 如果是,则为true
     */
    public static boolean isChar(String str) {
        return str != null &&
                Pattern.compile("^[A-Za-z]{1,2}[0-9]{1,4}$").matcher(str).matches();
    }
}
