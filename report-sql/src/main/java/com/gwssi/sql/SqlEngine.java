package com.gwssi.sql;

import com.gwssi.sql.callback.ReportCallBack;
import com.gwssi.sql.define.ReportGrammar;
import com.gwssi.sql.entity.ExpEntity;
import com.gwssi.sql.entity.TableEntity;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class SqlEngine {

    private ExpEntity expEntity;
    private ReportCallBack reportCallBack;
    private ReportGrammar reportGrammar;

    public SqlEngine(ExpEntity expEntity, ReportGrammar reportGrammar) {
        this.expEntity = expEntity;
        this.reportGrammar = reportGrammar;
    }


    /**
     * 根据表达式返回sql
     */
    public String callSql() {
        this.reportGrammar = new ReportGrammar();
        //解析主栏条件和宾栏条件
        //提取中括号表达式 并解析出语法内容
        //解析函数
        //拼接sql语句
        String tableName = "B203-1";
        String zuobioa = "1,2";
        Map<String, String> map =
                reportCallBack.enByTablesName(TableEntity.builder().tableName(tableName).coordinate(zuobioa).build());
        //当前tablename对应的表名
        tableName = map.get(tableName);
        //当前坐标对应的变量名称
        zuobioa = map.get(zuobioa);
        return "value";
    }

    /**
     * 验证表达式是否符合语法要求
     */
    public boolean validataExp() {
        return false;
    }
}
