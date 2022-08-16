package com.gwssi.struct.model.style;


import java.io.Serializable;

public class Report extends PropertyBase implements Serializable {

    /**
     * 报表对应的单元格集合对象
     */
    protected Sheet sheet;

    /**
     * 构造函数。
     */
    public Report() {
    }


    /**
     * 克隆方法
     */
    public Report clone() {

        Report report = (Report) super.clone();

        if (this.sheet != null) {
            report.sheet = this.sheet.clone();
        }

        return report;
    }

    public Report cloneWithoutSheet() {

        Report report = (Report) super.clone();

        return report;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }

    public String toString() {
        return String.valueOf(sheet);
    }

    public boolean equals(Object anotherObject) {
        if (anotherObject == this) {
            return true;
        }

        //type
        if (!(anotherObject instanceof Report)) {
            return false;
        }

        Report anotherReport = (Report) anotherObject;

        //super
        if (!super.equals(anotherReport)) {
            return false;
        }

        //field
        Sheet sheet1 = getSheet();
        Sheet sheet2 = anotherReport.getSheet();
        return sheet1.equals(sheet2);
    }

    public int hashCode() {
        return super.hashCode();
    }
}
