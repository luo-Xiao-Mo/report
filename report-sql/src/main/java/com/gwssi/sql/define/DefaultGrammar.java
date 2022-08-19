package com.gwssi.sql.define;

import com.gwssi.sql.BracketUtil;
import com.gwssi.sql.callback.ReportCallBack;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import static com.gwssi.sql.define.ParseTools.isWhitespace;
import static com.gwssi.sql.define.ParseTools.isIdentifier;

public abstract class DefaultGrammar implements Grammar {

    /**
     * 时期参数
     */
    protected String period;
    /**
     * 当前公式的类型
     */
    protected boolean type;
    /**
     * 回调report的接口,用来从report中提取元素
     */
    protected ReportCallBack reportCallBack;

    /**
     * 当前字符串的结束位置
     */
    protected int end;

    /**
     * 当前字符串的长度
     **/
    protected int length;


    protected String expression;

    /**
     * 当前字符串起始处理点(跳过空格)
     */
    protected int start;

    /**
     * 存储当前表和列值的映射
     */
    protected Map<String, String> tableAndColumnMap;


    /**
     * 当前的操作符栈
     */
    protected Stack<Character> operateSignStack = new Stack<>();


    @Override
    public void setExpType(boolean type) {
        this.type = type;
    }

    @Override
    public void setReportCallBack(ReportCallBack reportCallBack) {
        this.reportCallBack = reportCallBack;
    }

    /**
     * 解析当前表达式
     *
     * @param expression 当前解析的表达式
     */
    public void parse(String expression) {

    }

    /**
     * 默认的验证当前表达式的方式
     */
    public boolean DefaultValidate(String expression) {
//        if (StringUtils.isEmpty(expression)) throw new IllegalArgumentException("表达式不能为空");
//        //检测所有字符,只能出现英文、数字、下划线 + - * / %
//        for (int i = start; i < length; i++) {
//            char c = expr[i];
//            if (!isIdentifier(c)) {
//                throw new IllegalArgumentException(String.format("表达式该%s不支持", c));
//            }
//        }
//        //验证中括号中不能出现操作符运算
//        List<String> BracketList = BracketUtil.matcherBracketValue(expression);
//        if (BracketList != null && !BracketList.isEmpty()) {
//            BracketList.forEach(item -> {
//                if (item.contains("+") || item.contains("-") || item.contains("*") || item.contains("/")
//                        || item.contains("%")) {
//                    throw new IllegalArgumentException(String.format("中括号%s中不支持语法计算", item));
//                }
//            });
//        }
//        //提取操作符的位置
//        List<Integer> indexList = new ArrayList<>();
//        List<String> tokenList = new ArrayList<>();
//        for (int cursor = start; cursor < end; cursor++) {
//            char c = expr[cursor];
//            if ('+' == c || '-' == c || '/' == c || '*' == c || '%' == c) {
//                indexList.add(cursor);
//            }
//        }
//        if (!indexList.isEmpty()) {
//            for (int i = 0; i < indexList.size(); i++) {
//                if (i == 0) {
//                    tokenList.add(exp.substring(0, indexList.get(0)));
//                }
//                if (i > 0 && i < indexList.size() - 1) {
//                    tokenList.add(exp.substring(indexList.get(i - 1) + 1, indexList.get(i)));
//                }
//                if (i == indexList.size() - 1) {
//                    tokenList.add(exp.substring(indexList.get(i) + 1));
//                }
//            }
//        }
        return false;
    }

    /**
     * 去除表达式的首尾空格
     *
     * @param expression 当前待处理的表达式
     */
    private void setExpression(String expression) {
        if (expression != null && expression.length() != 0) {
            char[] chars = expression.toCharArray();
            end = length = chars.length;
            while (start < length && isWhitespace(chars[start])) start++;
            while (length != 0 && isWhitespace(chars[length - 1])) length--;
            //有效的字符串
            this.expression = expression.substring(start, end);
        }
    }

//    private void
}
