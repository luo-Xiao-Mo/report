package com.gwssi.calculate.type;

public enum ExpType {


    /**
     * 提取公式
     */
    DRAW("0"),

    /**
     * 运算公式
     */
    OPERATION("1");

    private String code;

    ExpType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
