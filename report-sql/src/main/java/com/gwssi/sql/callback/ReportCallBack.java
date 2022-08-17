package com.gwssi.sql.callback;

import com.gwssi.sql.entity.TableEntity;

import java.util.List;
import java.util.Map;


/**
 * 该接口主要用于回调report层的逻辑实现
 */
public interface ReportCallBack {

    // 根据表名和坐标查询对应的英文名称
    Map<String, String> enByTablesName(TableEntity tableEntity);


    // 根据表名和坐标批量查询信息
    List<Map<String, String>> batchTablesName(List<TableEntity> tableEntities);


    //根据表名查询对应的主线信息
    Map<String, String> mainLineByTable(List<String> tables);


    //根据表名查询对应的关联关系
    String relationByTables(String... tables);

}
