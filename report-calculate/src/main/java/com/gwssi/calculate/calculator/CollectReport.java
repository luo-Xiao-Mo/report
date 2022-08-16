package com.gwssi.calculate.calculator;


import com.gwssi.calculate.type.ExpType;
import com.gwssi.struct.model.data.ReportData;
import com.gwssi.struct.model.exp.Exp;
import com.gwssi.struct.model.style.Report;

import java.util.List;
import java.util.Map;

public class CollectReport extends AbstractCalculator {

    @Override
    public ReportData calculateCollect(Report report, List<Exp> expressionList) {
        Map<String, List<String>> expList = groupExp(expressionList);
        List<String> drawList = expList.get(ExpType.DRAW.getCode());
        if (drawList != null && !drawList.isEmpty()) {
            drawList.forEach(item -> {

            });
        }
        return null;
    }


}
