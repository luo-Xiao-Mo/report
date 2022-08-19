package com.gwssi.sql;

import com.gwssi.sql.callback.ReportCallBack;
import com.gwssi.sql.define.Grammar;
import com.gwssi.sql.define.GrammarValidate;
import com.gwssi.sql.entity.ExpEntity;
import com.gwssi.sql.entity.TableEntity;
import lombok.Data;

import java.util.Map;

@Data
public class SqlEngine {

    //用于计算的表达式对象
    private ExpEntity expEntity;
    //当前的回调报表状态
    private ReportCallBack reportCallBack;
    //当前的语法对象
    private Grammar grammar;
    //用于验证的表达式
    private String exp;

    public SqlEngine() {

    }

    public SqlEngine(ExpEntity expEntity, ReportCallBack reportCallBack) {
        this.expEntity = expEntity;
        this.reportCallBack = reportCallBack;
        this.grammar = GrammarValidate.validate(expEntity.getCollect(), reportCallBack);
    }

    public SqlEngine(String exp) {
        this.exp = exp;
        this.grammar = GrammarValidate.validate(exp, reportCallBack);
    }

    /**
     * 根据表达式返回sql
     */
    public String callSql() {

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
        return grammar.validateExp(exp);
    }
}
