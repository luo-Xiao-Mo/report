package com.gwssi.struct.utils;


import com.gwssi.struct.model.style.Cell;

/**
 * 单元格封装
 *
 * @version 1.0 2014 xingye
 */
public class CellWrapper {


    protected Cell cell;


    protected int rowIndex;


    protected int columnIndex;


    public CellWrapper() {
    }


    public CellWrapper(Cell cell, int rowIndex, int columnIndex) {
        this.cell = cell;
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }


    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int colIndex) {
        this.columnIndex = colIndex;
    }

}
