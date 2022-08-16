package com.gwssi.struct.model.data;

import lombok.Data;

@Data
public class BusinessError extends Business {

    //是否计算成功
    private boolean message;
    //错误原因
    private String errorReason;
    //计算状态
    private String status;

}
