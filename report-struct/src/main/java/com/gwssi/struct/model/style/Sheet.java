package com.gwssi.struct.model.style;

import com.alibaba.fastjson.annotation.JSONField;
import com.gwssi.struct.model.style.property.PropertyKey;
import com.gwssi.struct.utils.BeeEqualsUtils;
import com.gwssi.struct.utils.SheetUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Sheet extends PropertyBase implements Serializable {

    protected List<Row> rows = new ArrayList<Row>();

    protected List<Column> columns = new ArrayList<Column>();

    protected List<CellRange> mergedRegions = new ArrayList<CellRange>();

    public Sheet() {
    }


    // ===============================
    // 合并区域

    /**
     * 获取合并区域
     *
     * @param rowIndex
     * @param columnIndex
     * @return
     * @version 1.0 2014 xingye
     */
    public CellRange lookupMergedRegion(int rowIndex, int columnIndex) {
        return SheetUtils.lookupMergedRegion(mergedRegions, rowIndex, columnIndex);
    }


    /**
     * 注意，本方法不会同时修改单元格的属性
     *
     * @param cr
     * @author xingye 2015
     */
    public void addMergeRegion(CellRange cr) {
        this.mergedRegions.add(cr);
    }


    // ===============================
    // 行操作

    public Row getRow(int index) {
        return rows.get(index);
    }

    public void addRow(Row row) {
        rows.add(row);
    }

    /**
     * 注意，本方法不会扩展合并区域
     *
     * @param index
     * @param row
     * @author xingye 2015
     */
    public void addRow(int index, Row row) {
        rows.add(index, row);
    }


    // =============================================
    // 列操作

    //
    public void fixColumn(int newSize) {

        int colSize = this.columns.size();
        int s = newSize - colSize;

        while (s > 0) {
            s--;
            Column col = new Column();
            addColumn(col);
        }

    }


    //
    public void addColumn(Column column) {
        this.columns.add(column);
    }

    public void addColumn(int index, Column column) {
        this.columns.add(index, column);
    }

    public void addColumnWithNewCell(int index, Column column) {

        this.columns.add(index, column);

        for (int i = 0; i < this.rows.size(); i++) {
            Row row = rows.get(i);
            Cell cell = new Cell();
            row.addCell(index, cell);
        }
    }

    public Column getColumn(int index) {
        return columns.get(index);
    }

    public void removeColumn(int index) {
        columns.remove(index);
    }

    public void removeColumnWithCell(int index) {
        columns.remove(index);

        for (int i = 0; i < this.rows.size(); i++) {
            Row row = rows.get(i);
            row.removeCell(index);
        }
    }


    //

    public Sheet cloneWithoutChildren() {

        Sheet sheet = (Sheet) super.clone();

        sheet.setRows(new ArrayList<Row>());
        sheet.setColumns(new ArrayList<Column>());
        sheet.setMergedRegions(new ArrayList<CellRange>());

        return sheet;
    }

    public Sheet clone() {

        Sheet sheet = (Sheet) super.clone();

        List<Row> _rows = this.rows;
        List<Column> _columns = this.columns;
        List<CellRange> _mergedRegions = this.mergedRegions;

        List<Row> rows = new ArrayList<Row>(_rows.size());
        List<Column> columns = new ArrayList<Column>(_columns.size());
        List<CellRange> mergedRegions = new ArrayList<CellRange>(_mergedRegions.size());

        sheet.rows = rows;
        sheet.columns = columns;
        sheet.mergedRegions = mergedRegions;

        for (int i = 0, len = _rows.size(); i < len; i++) {
            rows.add(_rows.get(i).clone());
        }

        for (int i = 0, len = _columns.size(); i < len; i++) {
            columns.add(_columns.get(i).clone());
        }

        for (int i = 0, len = _mergedRegions.size(); i < len; i++) {
            mergedRegions.add(_mergedRegions.get(i).clone());
        }

        return sheet;
    }


    //
    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public List<CellRange> getMergedRegions() {
        return mergedRegions;
    }

    public void setMergedRegions(List<CellRange> cellRanges) {
        this.mergedRegions = cellRanges;
    }

    public String toString() {

        StringBuilder buf = new StringBuilder();
        buf.append("{columnSize:");
        buf.append(columns.size());
        buf.append(", rowSize:");
        buf.append(rows.size());
        buf.append(", mergedRegionSize:");
        buf.append(mergedRegions.size());
        buf.append("}");

        return buf.toString();
    }

    public boolean equals(Object anotherObject) {
        if (this == anotherObject) {
            return true;
        }

        //type
        if (!(anotherObject instanceof Sheet)) {
            return false;
        }

        Sheet anotherSheet = (Sheet) anotherObject;

        //parent
        if (!super.equals(anotherSheet)) {
            return false;
        }

        //field
        List<Row> rows1 = getRows();
        List<Row> rows2 = anotherSheet.getRows();

        List<Column> cols1 = getColumns();
        List<Column> cols2 = anotherSheet.getColumns();

        List<CellRange> cr1 = getMergedRegions();
        List<CellRange> cr2 = anotherSheet.getMergedRegions();

        return BeeEqualsUtils.equalsList(rows1, rows2) &&
                BeeEqualsUtils.equalsList(cols1, cols2) &&
                BeeEqualsUtils.equalsList(cr1, cr2);
    }

    public int hashCode() {
        return super.hashCode();
    }

    /**
     * 删除表内容四周的空行空列
     * <p>
     * 判断是空行空列的标准是：
     * 行列的属性里没有过滤条件、没有公式
     * 且行列内的每一个单元格都没有过滤条件、没有公式
     * 且每一个单元格没有除框线、字体、背景色、加粗等样式外的其他属性
     * 且每一个单元格的值是空（无值或空值）
     */
    public void trim() {
        if (this.rows == null || this.rows.isEmpty()
                || this.columns == null || this.columns.isEmpty()) {
            return;
        }

        List<Row> rows = this.rows;
        List<Column> cols = this.columns;

        int r, c;
        int sr = 0;
        int er = rows.size() - 1;
        int sc = 0;
        int ec = cols.size() - 1;
        // 从上至下删除空行
        for (r = 0; isEmptyRow(r); r++) {
            if (r != rows.size() - 1) {// 留下最后一行
                rows.remove(r);
                sr++;
                r--;
            }
        }

        // 从下至上删除空行
        for (r = rows.size() - 1; isEmptyRow(r); r--) {
            if (r != 0) {// 留下第一行
                rows.remove(r);
                er--;
            }
        }

        // 从左至右删除空列
        for (c = 0; isEmptyCol(c); c++) {
            if (c != cols.size() - 1) {// 留下最后一列
                this.removeColumnWithCell(c);
                sc++;
                c--;
            }
        }

        // 从右至左删除空列
        for (c = cols.size() - 1; isEmptyCol(c); c--) {
            if (c != 0) {// 留下第一列
                this.removeColumnWithCell(c);
                ec--;
            }
        }

        // 修复合并区
        List<CellRange> mergedRegions = this.mergedRegions;
        for (int i = 0; i < mergedRegions.size(); i++) {
            CellRange region = mergedRegions.get(i);

            if (!isRegionHit(region, sr, er, sc, ec)) {
                mergedRegions.remove(i);
                i--;
                continue;
            }

            int rsr = region.getStartRow();
            int rer = region.getEndRow();
            int rsc = region.getStartColumn();
            int rec = region.getEndColumn();

            // 删除合并区超出边界的区域
            if (rsr < sr) {
                rsr = sr;
            }
            if (rer > er) {
                rer = er;
            }
            if (rsc < sc) {
                rsc = sc;
            }
            if (rec > ec) {
                rec = ec;
            }

            // 行列号平移
            region.setStartRow(rsr - sr);
            region.setEndRow(rer - sr);
            region.setStartColumn(rsc - sc);
            region.setEndColumn(rec - sc);
        }

        SheetUtils.setCellSpanFromMergedRegions(this);
        SheetUtils.setCellName(this);
    }

    /**
     * 获取表式内容边界，确定有内容的区域。
     *
     * @return CellRange
     */
    public CellRange lookupContentBorder() {
        if (this.rows == null || this.rows.isEmpty()
                || this.columns == null || this.columns.isEmpty()) {
            return null;
        }

        List<Row> rows = this.rows;
        List<Column> cols = this.columns;

        int r, c;
        int sr = 0;
        int er = rows.size() - 1;
        int sc = 0;
        int ec = cols.size() - 1;
        // 从上至下删除空行
        for (r = 0; isEmptyRow(r); r++) {
            sr++;
        }

        // 从下至上删除空行
        for (r = rows.size() - 1; isEmptyRow(r); r--) {
            er--;
        }

        // 从左至右删除空列
        for (c = 0; isEmptyCol(c); c++) {
            sc++;
        }

        // 从右至左删除空列
        for (c = cols.size() - 1; isEmptyCol(c); c--) {
            ec--;
        }

        // 如果结束行比起始行小，说明整张表都是空的。
        if (er < sr || ec < sc) {
            return null;
        }

        return new CellRange(sr, sc, er, ec);
    }

    /**
     * 1 判断是否超出范围
     * 2 判断是否有公式
     * 3 判断是否有过滤条件
     * 4 判断是否所有单元格为空
     *
     * @param c
     * @return
     */
    @JSONField(serialize = false)
    private boolean isEmptyCol(int c) {

        List<Row> rows = this.rows;
        List<Column> cols = this.columns;

        if (c >= cols.size() || c < 0) {
            return false;
        }

        Column col = cols.get(c);

        if (col.getExprMap() != null && !col.getExprMap().isEmpty()) {
            return false;
        }

        boolean flag = true;

        for (int r = 0; r < rows.size(); r++) {
            if (notEmptyCell(r, c)) {
                flag = false;
                break;
            }
        }

        return flag;
    }

    /**
     * 1 判断是否超出范围
     * 2 判断是否有公式
     * 3 判断是否有过滤条件
     * 3 判断是否有过滤条件
     * 4 判断是否所有单元格为空
     *
     * @param r
     * @return
     */
    @JSONField(serialize = false)
    private boolean isEmptyRow(int r) {

        List<Row> rows = this.rows;

        if (r >= rows.size() || r < 0) {
            return false;
        }

        Row row = rows.get(r);

        if (row.getExprMap() != null && !row.getExprMap().isEmpty()) {
            return false;
        }

        boolean flag = true;

        for (int c = 0; c < row.getCells().size(); c++) {
            if (notEmptyCell(r, c)) {
                flag = false;
                break;
            }
        }

        return flag;

    }

    /**
     * 1 判断是否有公式
     * 2 判断是否有过滤条件
     * 3 判断propertyMap里是否有cellValue且cellValue是否为空
     * 4 判断propertyMap里是否有除cellValue外的其他非css属性
     *
     * @param r
     * @param c
     * @return
     */
    private boolean notEmptyCell(int r, int c) {

        Cell cell = this.rows.get(r).getCell(c);

        if (cell.getExprMap() != null && !cell.getExprMap().isEmpty()) {
            return true;
        }

        boolean flag = false;

        if (cell.getPropertyMap() != null && !cell.getPropertyMap().isEmpty()) {
            Map<Object, Object> cp = cell.getPropertyMap();
            Object cellValue = cell.getCellValue();
            if (cellValue != null && StringUtils.isNotBlank(cellValue.toString())) {
                flag = true;
            }

            for (Object prop : cp.keySet()) {
                if (!PropertyKey.cellValue.equals(prop.toString()) && !isCssProperty(prop.toString())) {
                    flag = true;
                }
            }
        }

        return flag;
    }

    @JSONField(serialize = false)
    private boolean isCssProperty(String prop) {
        return Arrays.asList(PropertyKey.cssProps).contains(prop);
    }

    @JSONField(serialize = false)
    private boolean isRegionHit(CellRange cr, int sr, int er, int sc, int ec) {
        return (cr.getEndRow() >= sr
                && cr.getStartRow() <= er
                && cr.getEndColumn() >= sc
                && cr.getStartColumn() <= ec);
    }

    //----------------------------获取宽和高--------------------------

    @JSONField(serialize = false)
    public float getSheetHeight() {
        List<Row> rows = this.rows;

        if (columns == null) {
            return 0;
        }

        float height = 0f;
        for (Row row : rows) {
            height += (float) row.getProperty(PropertyKey.height);
        }

        return height;
    }

    @JSONField(serialize = false)
    public float getSheetWidth() {
        List<Column> cols = columns;

        if (columns == null) {
            return 0;
        }

        float wdith = 0f;
        for (Column col : cols) {
            wdith += (float) col.getProperty(PropertyKey.width);
        }

        return wdith;
    }
}
