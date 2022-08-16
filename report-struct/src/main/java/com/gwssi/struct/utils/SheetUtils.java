package com.gwssi.struct.utils;


import com.gwssi.struct.model.style.*;
import com.gwssi.struct.model.style.property.PropertyConstants;
import com.gwssi.struct.model.style.property.PropertyKey;

import java.util.Iterator;
import java.util.List;

/**
 * sheet工具集
 *
 * @version 1.0 2014 xingye
 */
public class SheetUtils {

    /**
     * 返回主体单元格。
     *
     * @param sheet
     * @param rowIndex
     * @param colIndex
     * @return
     * @version 1.0 2014 xingye
     */
    public static Cell getMainCell(Sheet sheet,
                                   int rowIndex, int colIndex) {

        Cell cell = sheet.getRow(rowIndex).getCell(colIndex);

        if (cell.judgeMergeType() == PropertyConstants.MERGE_TYPE_NOT) {
            return cell;
        }

        return getMergeMainCell(sheet, rowIndex, colIndex);
    }

    /**
     * 返回合并区域主体单元格。
     *
     * @param sheet
     * @param rowIndex
     * @param colIndex
     * @return
     * @version 1.0 2014 xingye
     */
    public static Cell getMergeMainCell(Sheet sheet,
                                        int rowIndex, int colIndex) {

        CellRange cr = lookupMergedRegion(sheet.getMergedRegions(), rowIndex, colIndex);

        if (cr == null) {
            throw new ReportException("不在合并区域中");
        }

        return sheet.getRow(cr.getStartRow()).getCell(cr.getStartColumn());
    }

    /**
     * 返回主体单元格。
     * <p>
     * 本方法适用于还未生成合并区域的情形。
     *
     * @param sheet
     * @param rowIndex
     * @param colIndex
     * @return
     * @version 1.0 2014 xingye
     */
    public static CellWrapper getMainCellWrapper(Sheet sheet,
                                                 int rowIndex, int colIndex) {

        Cell cell = sheet.getRow(rowIndex).getCell(colIndex);

        if (cell.judgeMergeType() == PropertyConstants.MERGE_TYPE_NOT) {
            CellWrapper entry = new CellWrapper();
            entry.setCell(cell);
            entry.setRowIndex(rowIndex);
            entry.setColumnIndex(colIndex);
            return entry;
        }

        return getMergeMainCellWrapper(sheet, rowIndex, colIndex);
    }

    /**
     * 返回合并区域主体单元格。
     * <p>
     * 本方法适用于还未生成合并区域的情形。
     *
     * @param sheet
     * @param rowIndex
     * @param colIndex
     * @return
     * @version 1.0 2014 xingye
     */
    public static CellWrapper getMergeMainCellWrapper(Sheet sheet,
                                                      int rowIndex, int colIndex) {

        // 判断当前单元格是否主格
        Cell cell = sheet.getRow(rowIndex).getCell(colIndex);
        int mergeType = cell.judgeMergeType();
        switch (mergeType) {
            case PropertyConstants.MERGE_TYPE_NOT: {
                throw new ReportException("不在合并区域中");
            }
            case PropertyConstants.MERGE_TYPE_MAIN: {
                CellWrapper entry = new CellWrapper();
                entry.setCell(cell);
                entry.setRowIndex(rowIndex);
                entry.setColumnIndex(colIndex);
                return entry;
            }
            default: {
                break;
            }
        } // switch

        // 逐行向上向左找

        Row row;
        int minColIndex = 0;

        for (int ri = rowIndex; ri >= 0; ri--) {

            if (minColIndex > colIndex) {
                throw new ReportException("不在合并区域中");
            }

            // 每一行
            row = sheet.getRow(ri);

            // 找到最左边的非合并空白格
            for (int ci = colIndex; ci >= minColIndex; ci--) {

                cell = row.getCell(ci);
                mergeType = cell.judgeMergeType();
                switch (mergeType) {
                    case PropertyConstants.MERGE_TYPE_NOT: {
                        minColIndex = ci++;
                        break;
                    }
                    case PropertyConstants.MERGE_TYPE_MAIN: {
                        // 判断是否在合并区域中

                        // 不需要判断行位置，因为已经在minColIndex中屏蔽掉这种情况了。
                        //int endRow = ri + cell.getIntValue(PropertyName.rowspan) - 1;
                        //if (rowIndex > endRow) {
                        //    // 继续找
                        //    break;
                        //}
                        int endCol = ci + cell.getIntValue(PropertyKey.colspan) - 1;
                        if (colIndex > endCol) {
                            // 继续找
                            minColIndex = endCol++;
                            break;
                        }

                        CellWrapper entry = new CellWrapper();
                        entry.setCell(cell);
                        entry.setRowIndex(ri);
                        entry.setColumnIndex(ci);
                        return entry;
                    }
                    default: {
                        break;
                    }
                } // switch

            } // for col

        } // for row

        throw new ReportException("不在合并区域中");

    }

    /**
     * 创建合并区域的空白格
     *
     * @return
     * @version 1.0 2014 xingye
     */
    public static Cell createMergeBlankCell() {

        Cell cell = new Cell();
        cell.putProperty(PropertyKey.rowspan, 0);
        cell.putProperty(PropertyKey.colspan, 0);

        return cell;
    }


    /**
     * 获取合并区域
     *
     * @param rowIndex
     * @param columnIndex
     * @return
     * @version 1.0 2014 xingye
     */
    public static CellRange lookupMergedRegion(List<CellRange> mergedRegions, int rowIndex, int columnIndex) {

        CellRange cr;

        for (int i = 0, len = mergedRegions.size(); i < len; i++) {
            cr = mergedRegions.get(i);
            if (cr.isSurround(rowIndex, columnIndex)) {
                return cr;
            }
        }

        return null;
    }


    /**
     * 根据mergedRegion设置colspan和rowspan
     *
     * @version 1.0 2013 xingye
     */
    public static void setCellSpanFromMergedRegions(Sheet paramSheet) {

        if (paramSheet == null) {
            return;
        }

        CellRange region = null;
        Row row = null;
        Cell cell = null;
        int startRow, startCol, endRow, endCol;

        Iterator<CellRange> iterator = paramSheet.getMergedRegions().iterator();

        while (iterator.hasNext()) {

            region = iterator.next();

            startRow = region.getStartRow();
            endRow = region.getEndRow();

            startCol = region.getStartColumn();
            endCol = region.getEndColumn();

            for (int r = startRow; r <= endRow; r++) {

                row = paramSheet.getRow(r);

                for (int c = startCol; c <= endCol; c++) {
                    cell = row.getCell(c);
                    cell.putProperty(PropertyKey.colspan, 0);
                    cell.putProperty(PropertyKey.rowspan, 0);
                }
            }

            // 设置主格
            cell = paramSheet.getRow(startRow).getCell(startCol);

            cell.putProperty(PropertyKey.colspan, (endCol - startCol + 1));
            cell.putProperty(PropertyKey.rowspan, (endRow - startRow + 1));
        }

    }

    /**
     * 根据rowspan和colspan设置mergedRegion
     *
     * @param paramSheet
     * @version 1.0 2013 xingye
     */
    public static void setMergedRegionsFromCellSpan(Sheet paramSheet) {

        if (paramSheet == null) {
            return;
        }

        Cell cell = null;

        List<CellRange> mergedRegions = paramSheet.getMergedRegions();
        mergedRegions.clear();

        List<Row> rows = paramSheet.getRows();
        List<Cell> cells = null;

        int colSize = paramSheet.getColumns().size();
        int rowSize = rows.size();
        int colspan, rowspan;

        for (int r = 0; r < rowSize; r++) {

            cells = rows.get(r).getCells();

            for (int c = 0; c < colSize; c++) {

                cell = cells.get(c);

                colspan = cell.getIntValue(PropertyKey.colspan);
                rowspan = cell.getIntValue(PropertyKey.rowspan);

                if (colspan > 1 || rowspan > 1) {
                    CellRange region = new CellRange();

                    region.setStartRow(r);
                    region.setEndRow(r + rowspan - 1);
                    region.setStartColumn(c);
                    region.setEndColumn(c + colspan - 1);

                    mergedRegions.add(region);
                }

            }
        }

    }

    /**
     * @param paramSheet
     * @version 1.0 2013 xingye
     */
    public static void setCellName(Sheet paramSheet) {

        if (paramSheet == null) {
            return;
        }

        SheetWalker walker = new SheetWalker(paramSheet);

        // 列
        Column col;
        walker.reset();
        while ((col = walker.nextColumn()) != null) {
            col.setName(CellName.toColumnName(walker.getCurrentColumnIndex()));
        }

        // 行
        Row row;
        walker.reset();
        while ((row = walker.nextRow()) != null) {
            row.setName(CellName.toRowName(walker.getCurrentRowIndex()));
        }

        // 单元格
        Cell cell;
        walker.reset();
        while ((cell = walker.nextCell()) != null) {
            cell.setName(CellName.toCellName(walker.getCurrentRowIndex(), walker.getCurrentColumnIndex()));
        }


    }


    public static void setOriginalCellName(Sheet paramSheet) {

        if (paramSheet == null) {
            return;
        }

        SheetWalker walker = new SheetWalker(paramSheet);


        // 列
        Column col;
        walker.reset();
        while ((col = walker.nextColumn()) != null) {
            col.setOriginalName(col.getName());
        }

        // 行
        Row row;
        walker.reset();
        while ((row = walker.nextRow()) != null) {
            row.setOriginalName(row.getName());
        }

        // 单元格
        Cell cell;
        walker.reset();
        while ((cell = walker.nextCell()) != null) {
            cell.setOriginalName(cell.getName());
        }


    }

    /**
     * 根据是否隐藏属性设置行和列的高宽
     *
     * @param sheet
     * @version 1.0 2014 xingye
     */
    public static void setRowAndColSizeByHiddenProperty(Sheet sheet) {

        if (sheet == null) {
            return;
        }

        List<Row> rows = sheet.getRows();

        for (Row row : rows) {
            if (row.getShortValue(PropertyKey.hidden) == PropertyConstants.hidden_yes) {
                row.putProperty(PropertyKey.height, 0f);
            }
        }

        List<Column> columns = sheet.getColumns();

        for (Column column : columns) {
            if (column.getShortValue(PropertyKey.hidden) == PropertyConstants.hidden_yes) {
                column.putProperty(PropertyKey.width, 0f);
            }
        }

    }

    /**
     * 验证合并格的colspan与rowspan属性设置是否正确
     *
     * @param sheet
     * @return
     * @version 1.0 2014 cuiwen
     */
    public static boolean validateCellSpan(Sheet sheet) {

        if (sheet == null) {
            return false;
        }

        List<Row> rows = sheet.getRows();
        List<Column> cols = sheet.getColumns();
        List<Cell> cells = null;

        int rowNum = rows.size();
        int colNum = cols.size();

        Row row = null;
        Cell cell = null;

        int sheetArea = 0;

        for (int r = 0; r < rowNum; r++) {

            row = rows.get(r);
            cells = row.getCells();

            for (int c = 0; c < colNum; c++) {
                cell = cells.get(c);

                int colspan = cell.getIntValue(PropertyKey.colspan);
                int rowspan = cell.getIntValue(PropertyKey.rowspan);

                sheetArea += colspan * rowspan;
            }
        }

        if (sheetArea == colNum * rowNum) {
            return true;
        }

        return false;
    }

    /**
     * 修复某列的右边框
     *
     * @param sheet
     * @param columnIndex
     * @version 1.0 2014 xingye
     */
    public static void fixColumnRightBorderUseNextLeftBorder(Sheet sheet, int columnIndex) {

        int maxColIndex = sheet.getColumns().size() - 1;

        int refColIndex = columnIndex + 1;

        if (refColIndex > maxColIndex) {
            return;
        }

        Cell cell, refCell;

        List<Row> rows = sheet.getRows();
        Row row;

        for (int i = 0, len = rows.size(); i < len; i++) {
            row = rows.get(i);

            cell = row.getCell(columnIndex);

            // 判断单元格类型
            int mergeType = cell.judgeMergeType();
            switch (mergeType) {
                case PropertyConstants.MERGE_TYPE_MAIN: {
                    i = i + cell.getIntValue(PropertyKey.rowspan) - 1;
                    break;
                }
                case PropertyConstants.MERGE_TYPE_BLANK: {
                    cell = getMergeMainCell(sheet, i, columnIndex);
                    i = i + cell.getIntValue(PropertyKey.rowspan) - 1;
                    break;
                }
                case PropertyConstants.MERGE_TYPE_NOT:
                default: {
                    break;
                }
            }

            //
            refCell = getMainCell(sheet, i, refColIndex);

            //
            cell.putProperty(PropertyKey.borderRightStyle,
                    refCell.getProperty(PropertyKey.borderLeftStyle));
            cell.putProperty(PropertyKey.borderRightWidth,
                    refCell.getProperty(PropertyKey.borderLeftWidth));
            cell.putProperty(PropertyKey.borderRightColor,
                    refCell.getProperty(PropertyKey.borderLeftColor));

        }

    }

    /**
     * 修复某行的下边框
     *
     * @param sheet
     * @param rowIndex
     * @version 1.0 2014 xingye
     */
    public static void fixRowBottomBorderUseNextTopBorder(Sheet sheet, int rowIndex) {

        int maxRowIndex = sheet.getRows().size() - 1;

        int refRowIndex = rowIndex + 1;

        if (refRowIndex > maxRowIndex) {
            return;
        }

        Cell cell, refCell;
        Row row;

        row = sheet.getRow(rowIndex);

        for (int i = 0, len = sheet.getColumns().size(); i < len; i++) {

            cell = row.getCell(i);

            // 判断单元格类型
            int mergeType = cell.judgeMergeType();
            switch (mergeType) {
                case PropertyConstants.MERGE_TYPE_MAIN: {
                    i = i + cell.getIntValue(PropertyKey.colspan) - 1;
                    break;
                }
                case PropertyConstants.MERGE_TYPE_BLANK: {
                    cell = getMergeMainCell(sheet, rowIndex, i);
                    i = i + cell.getIntValue(PropertyKey.colspan) - 1;
                    break;
                }
                case PropertyConstants.MERGE_TYPE_NOT:
                default: {
                    break;
                }
            }

            refCell = getMainCell(sheet, refRowIndex, i);

            //
            cell.putProperty(PropertyKey.borderBottomStyle,
                    refCell.getProperty(PropertyKey.borderTopStyle));
            cell.putProperty(PropertyKey.borderBottomWidth,
                    refCell.getProperty(PropertyKey.borderTopWidth));
            cell.putProperty(PropertyKey.borderBottomColor,
                    refCell.getProperty(PropertyKey.borderTopColor));

        }

    }

    /**
     * 清除合并区域的非主格单元格的所有属性。包括上下文、表达式和属性。除了合并属性。
     *
     * @param sheet
     * @version 1.0 2014 xingye
     */
    public static void clearMergeBlankCellsProperties(Sheet sheet) {

        SheetWalker iterator = new SheetWalker(sheet);
        Cell cell;

        while (iterator.hasNextCell()) {

            cell = iterator.nextCell();

            if (cell.judgeMergeType() == PropertyConstants.MERGE_TYPE_BLANK) {
                cell.clear();
                cell.putProperty(PropertyKey.rowspan, 0);
                cell.putProperty(PropertyKey.colspan, 0);
            }
        }
    }

    /**
     * 如果某一行为隐藏行，向上追溯，找到第一个非隐藏行
     *
     * @param sheet
     * @param rowIndex
     * @author cuiwen 2014 version: 1.0
     */
    public static int traceNonhiddenRowIndex(Sheet sheet, int rowIndex) {
        Row row;

        while (rowIndex >= 0) {
            row = sheet.getRow(rowIndex);
            if (row.getShortValue(PropertyKey.hidden) == PropertyConstants.hidden_no) {
                return rowIndex;
            }

            rowIndex--;
        }

        return -1;
    }

    /**
     * 如果某一列为隐藏列，向前追溯，找到第一个非隐藏列
     *
     * @param sheet
     * @param colIndex
     * @return
     * @author cuiwen 2014 version: 1.0
     */
    public static int traceNonhiddenColIndex(Sheet sheet, int colIndex) {
        Column col;
        while (colIndex >= 0) {
            col = sheet.getColumn(colIndex);
            if (col.getShortValue(PropertyKey.hidden) == PropertyConstants.hidden_no) {
                return colIndex;
            }

            colIndex--;
        }

        return -1;
    }

}
