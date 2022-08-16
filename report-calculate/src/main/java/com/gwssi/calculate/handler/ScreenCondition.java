package com.gwssi.calculate.handler;

import com.gwssi.calculate.model.Term;
import com.gwssi.calculate.util.BracketUtil;
import com.gwssi.struct.model.style.PropertyBase;
import com.gwssi.struct.model.style.Report;
import com.gwssi.struct.model.style.Sheet;
import com.gwssi.struct.model.style.property.PropertyKey;

import java.util.*;

public class ScreenCondition {

    /**
     * 从当前报表模型中获取主栏筛选条件
     */
    public static Map<String, Term> getCondition(Report report) {
        Map<String, Term> map = new HashMap<>();
        return itemReport(report, map);
    }

    /**
     * 从报表中坐标值和宾栏主栏条件
     *
     * @param report 报表对象
     * @return Map<String, Term>
     */
    private static Map<String, Term> itemReport(Report report, Map<String, Term> map) {
        Sheet sheet = report.getSheet();
        if (sheet != null) sheet.getRows().forEach((row) -> row.getCells().forEach((cell) -> handler(cell, map)));
        return map;
    }


    /**
     * 报表对象中提取参数
     * 目前参数作用域范围为条件和格子表达式
     *
     * @param base    报表对象的父类
     * @param termMap 当前的存储对象信息
     */
    private static void handler(PropertyBase base, Map<String, Term> termMap) {
        conditionsMap(base.getPropertyMap(), termMap);
    }


    /**
     * 从报表条件中提取参数
     *
     * @param map     报表的表达式对象
     * @param termMap 当前的存储对象信息
     */
    @SuppressWarnings("unchecked")
    private static void conditionsMap(final Map<Object, Object> map, Map<String, Term> termMap) {
        if (map != null) {
            String coordinate = null;
            List<String> mainCondition = null;
            List<String> sliderCondition = null;
            if (map.get(PropertyKey.CELL_COORDINATE) != null)
                coordinate = map.get(PropertyKey.CELL_COORDINATE).toString();
            if (map.get(PropertyKey.CELL_MAIN) != null)
                mainCondition = (List<String>) map.get(PropertyKey.CELL_MAIN);
            if (map.get(PropertyKey.CELL_SLIDER) != null)
                sliderCondition = (List<String>) map.get(PropertyKey.CELL_SLIDER);
            termMap.put(coordinate, Term.builder().mainCondition(mainCondition).sliderCondition(sliderCondition).build());
        }
    }


    /**
     * 从当前报表模型中提取出所有坐标
     *
     * @param report 当前报表的report对象
     */
    public static List<String> getCoordinate(Report report) {
        Sheet sheet = report.getSheet();
        List<String> coordinateList = new ArrayList<>();
        if (sheet != null) sheet.getRows().forEach((row) -> row.getCells().forEach((cell) ->
        {
            Map<Object, Object> propertyMap = cell.getPropertyMap();
            coordinateList.add(propertyMap.get("CELL_COORDINATE").toString());
        }));
        return coordinateList;
    }

    /**
     * 返回当前所有坐标的行表示
     *
     * @param list 当前所有的坐标集合
     * @return Map<String, List < String>> key:行号 value[列集合]
     */
    public static Map<String, List<String>> rowMap(List<String> list) {
        if (BracketUtil.isEmpty(list)) return new HashMap<>();
        Map<String, List<String>> map = new HashMap<>();
        list.forEach(item -> {
            item = BracketUtil.replaceSmallBracket(item);
            String[] split = item.split(",");
            if (map.containsKey(split[0])) map.get(split[0]).add(split[1]);
            else {
                List<String> tempList = new ArrayList<>();
                tempList.add(split[1]);
                map.put(split[0], tempList);
            }
        });
        return map;
    }

    /**
     * 返回当前所有坐标的行表示
     *
     * @param list 当前所有的坐标集合
     * @return Map<String, List < String>> key:列号 value[行集合]
     */
    public static Map<String, List<String>> columnMap(List<String> list) {
        if (BracketUtil.isEmpty(list)) return new HashMap<>();
        Map<String, List<String>> map = new HashMap<>();
        list.forEach(item -> {
            item = BracketUtil.replaceSmallBracket(item);
            String[] split = item.split(",");
            if (map.containsKey(split[1])) map.get(split[1]).add(split[0]);
            else {
                List<String> tempList = new ArrayList<>();
                tempList.add(split[0]);
                map.put(split[1], tempList);
            }
        });
        return map;
    }
}
