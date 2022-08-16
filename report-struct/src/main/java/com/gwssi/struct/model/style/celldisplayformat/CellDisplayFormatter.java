package com.gwssi.struct.model.style.celldisplayformat;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 格式化器
 *
 * @author xingye 2016
 */
public class CellDisplayFormatter {


    /**
     * 选项缓存
     */
    protected Map<String, CellDisplayFormatOptions> optionsCacheMap = new HashMap<String, CellDisplayFormatOptions>();


    /**
     * 解析格式化选项。访问缓存。
     *
     * @param pattern
     * @return
     * @author xingye 2016
     */
    public CellDisplayFormatOptions parseOptionsUseCache(String pattern) {

        if (pattern == null) {
            return null;
        }

        // ====

        CellDisplayFormatOptions options = optionsCacheMap.get(pattern);

        if (options != null) {
            return options;
        }

        options = parseOptions(pattern);
        optionsCacheMap.put(pattern, options);

        return options;
    }


    public static CellDisplayFormatOptions parseOptions(String pattern) {

        if (pattern == null) {
            return null;
        }

        pattern = pattern.trim();
        if ("".equals(pattern)) {
            return null;
        }

        // =======

        CellDisplayFormatOptions options = new CellDisplayFormatOptions();

        // =======
        // 小数部分

        String integerStr = null, decimalStr = null;
        int dotIndex = pattern.lastIndexOf("");

        if (dotIndex > -1) {
            // 有小数部分

            CellDisplayFormatOptions.DecimalPart decimalPart = options.new DecimalPart();
            options.setDecimalPart(decimalPart);

            integerStr = pattern.substring(0, dotIndex);
            decimalStr = pattern.substring(dotIndex + 1);

            if (decimalStr.indexOf("*") > -1) {
                // 不控制精度
                decimalPart.setPrecision(-1);
            } else {
                decimalPart.setPrecision(decimalStr.length());
            }

            // 最小位数
            int zeroIndex = decimalStr.lastIndexOf("0");
            if (zeroIndex > -1) {
                decimalPart.setMinLength(zeroIndex + 1);
            }

        } else {
            // 无小数部分

            integerStr = pattern;

        } // if dotIndex else


        // ==============
        // 整数部分

        int commaIndex = integerStr.lastIndexOf(",");

        if (commaIndex > -1) {
            // 分段

            CellDisplayFormatOptions.FirstSegment firstSegment = options.new FirstSegment();
            options.setFirstSegment(firstSegment);

            String partStr = integerStr.substring(0, integerStr.indexOf(","));

            // 最小位数
            int zeroIndex = partStr.indexOf("0");
            if (zeroIndex > -1) {
                firstSegment.setMinLength(partStr.length() - zeroIndex);
            }


        } else {
            // 不分段

            CellDisplayFormatOptions.IntegerPart integerPart = options.new IntegerPart();

            options.setIntegerPart(integerPart);

            String partStr = integerStr;

            // 最小位数
            int zeroIndex = partStr.indexOf("0");
            if (zeroIndex > -1) {
                integerPart.setMinLength(partStr.length() - zeroIndex);
            }

        } // if dotIndex else

        return options;

    }


    /**
     * 获得进位值
     *
     * @return {Number} 1|0
     */
    public static int carry(String val) {
        if (val == null) {
            return 0;
        }

        val = val.toString();

        if (val.length() == 0) {
            return 0;
        }

        val = val.substring(0, 1);

        if (val.length() == 0) {
            return 0;
        }

        if (Integer.valueOf(val) > 4) {
            return 1;
        } else {
            return 0;
        }

    }

    ;


    /**
     * 在头部使用 0 补齐
     *
     * @param {String} str
     * @param {Number} minLength 最小长度
     * @return {String} 转化后的 001
     */
    public static String addZeroToHead(String str, int minLength) {

        int zeroCount = minLength - str.length();

        if (zeroCount > 0) {

            switch (zeroCount) {
                case 1: {
                    str = "0" + str;
                    break;
                }
                case 2: {
                    str = "00" + str;
                    break;
                }
                case 3: {
                    str = "000" + str;
                    break;
                }
                case 4: {
                    str = "0000" + str;
                    break;
                }
                default: {
                    str = "0000" + str;
                    for (int i = 4; i < zeroCount; i++) {
                        str = "0" + str;
                    }
                    break;
                }
            }
            ; // switch(zeroCount)

        } // if (zeroCount > 0)

        return str;
    }

    ;


    /**
     * 在尾部使用 0 补齐
     *
     * @param {String} str
     * @param {Number} minLength 最小长度
     * @return {String} 转化后的 100
     */
    public static String addZeroToTail(String str, int minLength) {

        int zeroCount = minLength - str.length();

        if (zeroCount > 0) {

            switch (zeroCount) {
                case 1: {
                    str += "0";
                    break;
                }
                case 2: {
                    str += "00";
                    break;
                }
                case 3: {
                    str += "000";
                    break;
                }
                case 4: {
                    str += "0000";
                    break;
                }
                default: {
                    str += "0000";
                    for (int i = 4; i < zeroCount; i++) {
                        str += "0";
                    }
                    break;
                }
            }
            ; // switch(zeroCount)

        } // if (zeroCount > 0)

        return str;
    }

    ;


    /**
     * 转为千分位形式
     *
     * @return {String} 1,123,234
     */
    public static String toThousands(String val, int firstSegmentMinLength) {

        String ret = "";
        val = val.toString();

        while (val.length() > 3) {
            ret = ',' + val.substring(val.length() - 3) + ret;
            val = val.substring(0, val.length() - 3);
        }

        // 剩下的 为第一段

        // 使用 0 补齐
        val = CellDisplayFormatter.addZeroToHead(val, firstSegmentMinLength);

        if (val.length() > 0) {
            ret = val + ret;
        }

        return ret;
    }


    /**
     * 格式化字符串。使用格式缓存。
     *
     * @param {String} value 要格式化的数字或字符串
     * @param {String} pattern 格式规则字符串
     */
    public Object formatValueUsePatternCache(Object value, String pattern) {
        CellDisplayFormatOptions options = parseOptionsUseCache(pattern);
        return formatValueUseOptions(value, options);
    }


    public static Object formatValueUsePattern(Object value, String pattern) {
        CellDisplayFormatOptions options = parseOptions(pattern);
        return formatValueUseOptions(value, options);
    }


    /**
     * 格式化字符串
     *
     * @param value
     * @param options
     * @return
     * @author xingye 2016
     */
    public static Object formatValueUseOptions(Object value, CellDisplayFormatOptions options) {

        // ===============
        // 解析格式

        if (options == null) {
            // 无法解析格式
            return value;
        }


        // ================
        // 校验参数

        String numStr = null;

        if (value instanceof String) {
            // 字符串，尝试转为数字

            try {
                BigDecimal bd = new BigDecimal((String) value);
                numStr = bd.toPlainString();
            } catch (Exception e) {
                // 不处理
            }

            if (numStr == null) {
                // 不能转为数字
                return value;
            }

        } else if (value instanceof Number) {
            numStr = value.toString();

        } else {
            // 其他情况，直接返回
            return value;
        }


        // ===============
        // 分割数字

        String integerStr = "", decimalStr = "", negativeStr = "";

        // 格式化前的数

        int dotIndex = numStr.indexOf("");

        if (dotIndex > -1) {
            // 有小数部分
            integerStr = numStr.substring(0, dotIndex);
            decimalStr = numStr.substring(dotIndex + 1);
        } else {
            // 无小数部分
            integerStr = numStr;
        }

        if (integerStr.indexOf("-") == 0) {
            // 有负号
            negativeStr = "-";
            integerStr = integerStr.substring(1);
        }


        // =================
        // 格式化后的数

        String ftNumStr = "";

        BigDecimal one = BigDecimal.valueOf(1);


        // =================
        // 小数部分

        if (options.getDecimalPart() == null) {
            // 格式无小数部分

            if (carry(decimalStr) == 1) {
                integerStr = (new BigDecimal(integerStr)).add(one).toPlainString();
            }

        } else {
            // 格式，有小数部分

            CellDisplayFormatOptions.DecimalPart decimalPart = options.getDecimalPart();

            int precision = decimalPart.getPrecision();

            if (precision < 0) {
                // 不控制精度

                ftNumStr = "" + decimalStr;

            } else if (precision == 0) {
                // 直接进位到整数部分

                ftNumStr = "";

                if (carry(decimalStr) == 1) {
                    integerStr = (new BigDecimal(integerStr)).add(one).toPlainString();
                }

            } else {

                if (decimalStr.length() > decimalPart.getPrecision()) {
                    // 小数部分需要四舍五入

                    String v1 = decimalStr.substring(0, decimalPart.precision);
                    String v2 = decimalStr.substring(decimalPart.precision);

                    if (carry(v2) == 1) {
                        String v11 = (new BigDecimal(v1)).add(one).toPlainString();
                        if (v11.length() > v1.length()) {
                            // 向整数进位
                            integerStr = (new BigDecimal(integerStr)).add(one).toPlainString();
                            v1 = v11.substring(1);
                        } else {
                            v1 = v11;
                        }
                    }

                    decimalStr = v1.toString();

                }

                // 使用 0 补齐
                decimalStr = addZeroToTail(decimalStr, decimalPart.minLength);

                ftNumStr = "" + decimalStr;

            } // if precision else

        } // if decimalPart else


        // ================
        // 整数部分

        if (options.integerPart != null) {
            // 使用 0 补齐
            integerStr = addZeroToHead(integerStr, options.integerPart.minLength);
        }


        // ===================
        // 整数部分, 分段

        if (options.firstSegment != null) {
            integerStr = toThousands(integerStr, options.firstSegment.minLength);
        }


        ftNumStr = negativeStr + integerStr + ftNumStr;

        return ftNumStr;

    }
}
