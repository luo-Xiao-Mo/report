package com.gwssi.calculate.calculator;

import com.gwssi.calculate.Calculator;
import com.gwssi.struct.model.data.ReportData;
import com.gwssi.struct.model.exp.Exp;
import com.gwssi.struct.model.style.Report;

import java.util.List;
import java.util.Map;

public abstract class AbstractCalculator implements Calculator {
    //存储当前已计算的表达式
    private Map<String, Object> valueMap;

    @Override

    public ReportData calculate(Report report, List<String> expressionList) {
        return null;
    }

    @Override
    public ReportData calculateExp(Report report, List<Exp> expressionList) {
        return calculateCollect(report, expressionList);
    }

    @Override
    public Object calculate(String exp) {
        return null;
    }

    public abstract ReportData calculateCollect(Report report, List<Exp> expressionList);
}
