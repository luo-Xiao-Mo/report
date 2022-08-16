package com.gwssi.calculate.calculator;

import com.gwssi.calculate.Calculator;

public class CalculatorFactory {

    //汇总表工厂方法
    Calculator createCollectReport() {
        return new CollectReport();
    }

    //采集表的工厂方法
    Calculator createGatherReport() {
        return new GatherReport();
    }

    //专题表的工厂方法
    Calculator createSpecialReport() {
        return new SpecialReport();
    }
}
