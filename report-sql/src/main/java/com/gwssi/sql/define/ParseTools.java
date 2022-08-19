package com.gwssi.sql.define;

public class ParseTools {

    /**
     * 判断当前符号是不是除操作符以外的部分
     *
     * @param c 当前的字符c
     */
    public static boolean isIdentifierPart(final int c) {
        return ((c > 96 && c < 123)//小写字母
                //大写字母 数字
                || (c > 64 && c < 91) || (c > 47 && c < 58) || (c == '_') ||
                (c == '(') || (c == ')') || (c == '[') || (c == ']') || (c == ','));
    }

    /**
     * 验证当前公式是否由指定符号组成
     *
     * @param c 当前的字符c
     */
    public static boolean isIdentifier(final int c) {
        return isIdentifierPart(c) ||
                (c == '+') || (c == '-') || (c == '*') || (c == '/') || (c == '%');
    }

    /**
     * 判断当前操作符是空格
     */
    public static boolean isWhitespace(char c) {
        return c < '\u0020' + 1;
    }
}
