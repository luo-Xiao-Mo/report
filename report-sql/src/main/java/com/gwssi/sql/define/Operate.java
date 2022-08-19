package com.gwssi.sql.define;

public enum Operate {

    /**
     * ＋运算
     */
    ADD('+', 1) {
        @Override
        String calc(String a, String b) {
            return a + Operate.ADD.getSign() + b;
        }
    },
    /**
     * -运算
     */
    SUB('-', 1) {
        @Override
        String calc(String a, String b) {
            return a + Operate.SUB.getSign() + b;
        }
    },
    /**
     * *运算
     */
    MULT('*', 2) {
        @Override
        String calc(String a, String b) {
            return a + Operate.MULT.getSign() + b;
        }
    },
    /**
     * 除运算
     */
    DIV('/', 2) {
        @Override
        String calc(String a, String b) {
            return a + Operate.DIV.getSign() + b;
        }
    },
    /**
     * 取模运算
     */
    MOD('%', 2) {
        @Override
        String calc(String a, String b) {
            return a + Operate.MOD.getSign() + b;
        }
    };

    //当前操作的符号
    private char sign;
    //当前操作的优先级
    private int level;

    Operate(char sign, int level) {
        this.sign = sign;
        this.level = level;
    }

    public char getSign() {
        return sign;
    }

    public void setSign(char sign) {
        this.sign = sign;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public static Operate getTypeByValue(char sign) {
        for (Operate enums : Operate.values()) {
            if (enums.getSign() == sign) {
                return enums;
            }
        }
        return null;
    }

    abstract String calc(String a, String b);
}
