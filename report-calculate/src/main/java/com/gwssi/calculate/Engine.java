package com.gwssi.calculate;


import com.gwssi.calculate.calculator.DefaultCalculator;
import com.gwssi.struct.model.data.ReportData;
import com.gwssi.struct.model.exp.Exp;
import com.gwssi.struct.model.style.Report;

import java.util.List;


/**
 * 报表引擎
 * 提供给业务接口调用使用
 */
public class Engine {


    /**
     * 报表计算
     *
     * @param report 当前的报表对象
     */
    public static ReportData calculate(Report report, List<String> expressionList, Calculator calculator) {
        return calculator.calculate(report, expressionList);
    }


    public static ReportData calculateExp(Report report, List<Exp> expressionList, Calculator calculator) {
        return calculator.calculateExp(report, expressionList);
    }

    public static Object calculate(String exp) {
        return new DefaultCalculator().calculate(exp);
    }
}

