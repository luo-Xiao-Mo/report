package com.gwssi.struct.model.style;

import java.io.Serializable;

public class CellRange implements Cloneable, Serializable {

    protected int startRow;
    protected int startColumn;
    protected int endRow;
    protected int endColumn;

    public CellRange() {

    }

    public CellRange(int startRow, int startColumn, int endRow, int endColumn) {
        this.startRow = startRow;
        this.startColumn = startColumn;
        this.endRow = endRow;
        this.endColumn = endColumn;
    }

    /**
     * 判断参数单元格位置是否在合并区域内。
     *
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    public boolean isSurround(int rowIndex, int columnIndex) {
        return startRow <= rowIndex && rowIndex <= endRow &&
                startColumn <= columnIndex && columnIndex <= endColumn;
    }


    /**
     * 判断参数合并区域是否在区域内
     *
     * @param cr
     * @return
     * @author xingye 2015
     */
    public boolean isSurround(CellRange cr) {
        return startRow <= cr.startRow && cr.endRow <= endRow &&
                startColumn <= cr.startColumn && cr.endColumn <= endColumn;
    }

    public int hashCode() {
        return super.hashCode();
    }

    /**
     * 获取单元格区域的单元格总数
     *
     * @return
     */
    public int getNumberOfCells() {
        return (endRow - startRow + 1) * (endColumn - startColumn + 1);
    }

    //
    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int firstRow) {
        this.startRow = firstRow;
    }

    public int getStartColumn() {
        return startColumn;
    }

    public void setStartColumn(int firstColumn) {
        this.startColumn = firstColumn;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int lastRow) {
        this.endRow = lastRow;
    }

    public int getEndColumn() {
        return endColumn;
    }

    public void setEndColumn(int lastColumn) {
        this.endColumn = lastColumn;
    }

    public CellRange clone() {

        CellRange obj = null;

        try {
            obj = (CellRange) super.clone();
        } catch (CloneNotSupportedException e) {
            // 不会出现
        }

        return obj;
    }

    public boolean equals(Object anotherObject) {
        if (anotherObject == this) {
            return true;
        }

        //type
        if (!(anotherObject instanceof CellRange)) {
            return false;
        }

        CellRange anotherCR = (CellRange) anotherObject;

        //fields
        int startRow1 = getStartRow();
        int startRow2 = anotherCR.getStartRow();

        int endRow1 = getEndRow();
        int endRow2 = anotherCR.getEndRow();

        int startCol1 = getStartColumn();
        int startCol2 = anotherCR.getStartColumn();

        int endCol1 = getEndColumn();
        int endCol2 = anotherCR.getEndColumn();

        return startRow1 == startRow2 &&
                endRow1 == endRow2 &&
                startCol1 == startCol2 &&
                endCol1 == endCol2;
    }


    public String toString() {

        StringBuilder buf = new StringBuilder();
        buf.append("{").append(startRow).append(":").append(endRow);
        buf.append(",").append(startColumn).append(":").append(endColumn).append("}");

        return buf.toString();
    }


}
