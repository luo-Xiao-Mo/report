package com.gwssi.struct.utils;

/**
 * 强类型转换
 *
 * @author xingye 2015
 */
public class StrongTypeCast {

    @SuppressWarnings("unchecked")
    public static <T> T castToT(Class<T> clazz, Object obj) {
        if (obj == null) {
            return null;
        }

        if (clazz.isInstance(obj)) {
            return (T) obj;
        }

        throw new IllegalArgumentException("类型" + obj.getClass().getSimpleName());
    }

    public static String castToString(Object obj) {
        return castToT(String.class, obj);
    }

    public static Boolean castToBoolean(Object obj) {
        return castToT(Boolean.class, obj);
    }

    public static Number castToNumber(Object obj) {
        return castToT(Number.class, obj);
    }

}
