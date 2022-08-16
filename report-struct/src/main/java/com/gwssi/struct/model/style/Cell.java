package com.gwssi.struct.model.style;


import com.gwssi.struct.model.style.celldisplayformat.CellDisplayFormatter;
import com.gwssi.struct.model.style.property.PropertyConstants;
import com.gwssi.struct.model.style.property.PropertyKey;

import java.io.Serializable;

public class Cell extends PropertyBase implements Serializable {

    public Cell() {
    }


    // ==============

    /**
     * 返回格式化后的单元格显示值。
     * <p>
     * 显示值 -> 显示格式  -> 单元格值
     *
     * @return
     * @author xingye 2016
     */
    public Object getFormattedDisplayValue() {

        Object val = getOwnProperty(PropertyKey.cellDisplayValue);
        if (val != null) {
            return val;
        }

        val = getCellValue();
        if (val == null) {
            return null;
        }

        String f = getStringValue(PropertyKey.cellDisplayFormat);

        if (f == null) {
            return val;
        }

        return CellDisplayFormatter.formatValueUsePattern(val, f);
    }


    /**
     * 类似td rowspan
     *
     * @return
     * @author xingye 2015
     */
    public int getRowspan() {
        return getIntValue(PropertyKey.rowspan);
    }

    public void setRowspan(int i) {
        if (i == 1) {
            removeProperty(PropertyKey.rowspan);
        } else {
            putProperty(PropertyKey.rowspan, i);
        }
    }

    /**
     * 类似 td colspan
     *
     * @return
     * @author xingye 2015
     */
    public int getColspan() {
        return getIntValue(PropertyKey.colspan);
    }

    public void setColspan(int i) {
        if (i == 1) {
            removeProperty(PropertyKey.colspan);
        } else {
            putProperty(PropertyKey.colspan, i);
        }
    }


    public Object getCellValue() {
        return getOwnProperty(PropertyKey.cellValue);
    }

    public void setCellValue(Object obj) {
        putProperty(PropertyKey.cellValue, obj);
    }

    /**
     * 扩展方式
     *
     * @return
     * @author xingye 2015
     */
    public int getExtendType() {
        return getIntValue(PropertyKey.extendType);
    }

    // ===============

    /**
     * 获得格子类型，
     * 0 表示合并区域的非首格，
     * 1 表示显示的单个格子，
     * 2 表示合并区域的首个格子
     *
     * @return
     */
    public int judgeMergeType() {

        int colspan = getIntValue(PropertyKey.colspan);
        int rowspan = getIntValue(PropertyKey.rowspan);

        if (colspan == 0 && rowspan == 0) {
            return PropertyConstants.MERGE_TYPE_BLANK;
        } else if (colspan == 1 && rowspan == 1) {
            return PropertyConstants.MERGE_TYPE_NOT;
        } else {
            return PropertyConstants.MERGE_TYPE_MAIN;
        }
    }


    public Cell clone() {
        Cell cell = (Cell) super.clone();
        return cell;
    }


    public boolean equals(Object anotherObject) {
        if (anotherObject == this) {
            return true;
        }

        //type
        if (!(anotherObject instanceof Cell)) {
            return false;
        }

        Cell anotherCell = (Cell) anotherObject;

        //super
        return super.equals(anotherCell);

    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
