package com.gwssi.calculate.blame;

import com.gwssi.sql.SqlEngine;
import com.gwssi.sql.callback.ReportCallBack;
import com.gwssi.sql.entity.ExpEntity;
import com.gwssi.sql.entity.TableEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeneralHandler extends Handler {


    private SqlEngine sqlEngine;

    public GeneralHandler(String expression) {
        super(expression);
    }

    @Override
    public List<String> process() {
        return null;
    }

    public static void main(String[] args) {
        SqlEngine sqlEngine = SqlEngine.builder().expEntity(null)
                .reportCallBack(new ReportCallBack() {
                    @Override
                    public Map<String, String> enByTablesName(TableEntity tableEntity) {
                        return new HashMap<>();
                    }

                    @Override
                    public List<Map<String, String>> batchTablesName(List<TableEntity> tableEntities) {
                        return null;
                    }

                    @Override
                    public Map<String, String> mainLineByTable(List<String> tables) {
                        return null;
                    }

                    @Override
                    public String relationByTables(String... tables) {
                        return null;
                    }
                }).build();
        String message = sqlEngine.callSql();
    }
}
