package com.gwssi.calculate;

import com.gwssi.struct.model.data.ReportData;
import com.gwssi.struct.model.exp.Exp;
import com.gwssi.struct.model.style.Report;

import java.util.*;

public interface Calculator {

    ReportData calculate(Report report, List<String> expressionList);


    ReportData calculateExp(Report report, List<Exp> expressionList);


    Object calculate(String exp);

    /**
     * 当前表达式中提取出对应的汇总公式和运算公式
     */
    default Map<String, List<String>> groupExp(List<Exp> expressionList) {
        if (expressionList == null || expressionList.isEmpty()) return null;
        Map<String, List<String>> map = new HashMap<>();
        expressionList.forEach(item -> {
            if (map.get(item.getType()) != null) {
                map.get(item.getType()).add(item.getExpression());
            } else {
                List<String> list = new ArrayList<>();
                list.add(item.getExpression());
                map.put(item.getType(), list);
            }
        });
        return map;
    }
}
