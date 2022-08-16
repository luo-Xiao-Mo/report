package com.gwssi.calculate.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Term {

    //主栏条件
    private List<String> mainCondition;
    //宾栏条件
    private List<String> sliderCondition;
}
