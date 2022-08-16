package com.gwssi.struct.utils;

import com.gwssi.struct.model.style.Cell;
import com.gwssi.struct.model.style.Column;
import com.gwssi.struct.model.style.Row;
import com.gwssi.struct.model.style.Sheet;

import java.util.List;

/**
 * 从左上角向右下角，遍历迭代器。默认情况下，当前位置索引为-1
 *
 * @version 1.0 2013 xingye
 */
public class SheetWalker {

    public static final int LOCATION_ROW = 1;
    public static final int LOCATION_COLUMN = 2;
    public static final int LOCATION_CELL = 3;

    protected Sheet sheet = null;

    protected List<Row> rows = null;
    protected List<Column> columns = null;

    protected int rowSize = 0;
    protected int columnSize = 0;

    protected int nextRowIndex = 0;
    protected int nextColumnIndex = 0;


    protected int currentRowIndex = -1;
    protected Row currentRow = null;
    protected List<Cell> currentCells = null;

    protected int currentColumnIndex = -1;
    protected Column currentColumn = null;

    protected Cell currentCell = null;


    /**
     * 所处位置：行、列、单元格
     */
    protected int location = 3;


    // ===========

    public SheetWalker(Sheet sheet) {
        setSheet(sheet);
    }

    // =========


    protected void fireChangeSheet() {

        Sheet sheet = this.sheet;

        rows = sheet.getRows();
        columns = sheet.getColumns();

        rowSize = rows.size();
        columnSize = columns.size();

        nextColumnIndex = 0;
        nextRowIndex = 0;

        // 这里设为null，fireChangeRow时修改currentCell;
        currentColumn = null;

        fireChangeRow();
        fireChangeColumn();
    }


    protected void fireChangeRow() {

        int rowIndex = currentRowIndex = nextRowIndex - 1;

        if (rowIndex >= 0 && rowIndex < rowSize) {
            currentRow = rows.get(rowIndex);
            currentCells = currentRow.getCells();
        } else {
            currentRow = null;
            currentCells = null;
        }

        if (currentCells != null && currentColumn != null) {
            currentCell = currentCells.get(currentColumnIndex);
        } else {
            currentCell = null;
        }

    }


    protected void fireChangeColumn() {

        int columnIndex = currentColumnIndex = nextColumnIndex - 1;

        if (columnIndex >= 0 && columnIndex < columnSize) {
            currentColumn = columns.get(columnIndex);
        } else {
            currentColumn = null;
        }

        if (currentCells != null && currentColumn != null) {
            currentCell = currentCells.get(columnIndex);
        } else {
            currentCell = null;
        }
    }


    // =========


    public void setSheet(Sheet t) {
        this.sheet = t;
        fireChangeSheet();
    }

    public Sheet getSheet() {
        return this.sheet;
    }


    public void reset() {
        fireChangeSheet();
    }


    // ========

    public void locateRow() {
        this.location = LOCATION_ROW;
    }

    public void locateColumn() {
        this.location = LOCATION_COLUMN;
    }

    public void locateCell() {
        this.location = LOCATION_CELL;
    }


    // ==========

    public boolean hasNextRow() {
        return nextRowIndex < rowSize;
    }


    public void moveToRowHead() {
        nextColumnIndex = 0;
        fireChangeColumn();
    }


    public Row nextRow() {

        ++nextRowIndex;
        fireChangeRow();

        return currentRow;
    }

    // ========

    public boolean hasNextColumn() {
        return nextColumnIndex < columnSize;
    }

    public Column nextColumn() {

        ++nextColumnIndex;
        fireChangeColumn();

        return currentColumn;
    }


    public boolean hasNextCellInCurrentRow() {
        return nextColumnIndex < columnSize;
    }


    public Cell nextCellInCurrentRow() {

        ++nextColumnIndex;
        fireChangeColumn();

        return currentCell;
    }

    // ===========

    public boolean hasNextCell() {

        if (currentRow != null && hasNextColumn()) {
            // 有当前行
            return true;
        }

        return hasNextRow();
    }


    public Cell nextCell() {

        Cell cell = null;

        if (currentRow != null) {
            // 有当前行
            cell = nextCellInCurrentRow();
        }

        if (cell != null) {
            // 当前行有下一个单元格
            return cell;
        }

        // 有下一行
        if (nextRow() != null) {
            moveToRowHead();
            return nextCellInCurrentRow();
        }

        return null;

    }


    public Cell getLastCell() {
        if (rowSize > 0 && columnSize > 0) {
            return rows.get(rowSize - 1).getCell(columnSize - 1);
        }
        return null;
    }


    public Cell getFirstCell() {
        if (rowSize > 0 && columnSize > 0) {
            return rows.get(0).getCell(0);
        }
        return null;
    }


    // =============


    public List<Row> getRows() {
        return rows;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public int getLocation() {
        return location;
    }


    public List<Cell> getCurrentCells() {
        return currentCells;
    }


    public int getRowSize() {
        return rowSize;
    }


    public int getColumnSize() {
        return columnSize;
    }


    public int getNextRowIndex() {
        return nextRowIndex;
    }


    public int getNextColumnIndex() {
        return nextColumnIndex;
    }


    public Row getCurrentRow() {
        return currentRow;
    }


    public Column getCurrentColumn() {
        return currentColumn;
    }


    public Cell getCurrentCell() {
        return currentCell;
    }

    public int getCurrentRowIndex() {
        return currentRowIndex;
    }

    public int getCurrentColumnIndex() {
        return currentColumnIndex;
    }


}
