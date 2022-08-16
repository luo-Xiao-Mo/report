package com.gwssi.struct.model.style;


import com.gwssi.struct.utils.BeeEqualsUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Row extends PropertyBase implements Serializable {

    protected List<Cell> cells = new ArrayList<Cell>();

    public Row() {
    }

    // =========


    // ===================

    public void addCell(Cell cell) {
        cells.add(cell);
    }

    public void addCell(int index, Cell cell) {
        cells.add(index, cell);
    }

    public void setCell(int index, Cell cell) {
        cells.set(index, cell);
    }

    public Cell getCell(int index) {
        return cells.get(index);
    }

    public void removeCell(int index) {
        cells.remove(index);
    }

    public void replaceCell(int index, Cell cell) {
        cells.remove(index);
        cells.add(index, cell);
    }

    //
    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }

    public Row clone() {

        Row newRow = (Row) super.clone();

        List<Cell> cells = this.cells;

        List<Cell> newCells = new ArrayList<Cell>(cells.size());
        newRow.setCells(newCells);

        for (int i = 0, len = cells.size(); i < len; i++) {
            newCells.add(cells.get(i).clone());
        }

        return newRow;
    }

    public Row cloneWithoutCells() {

        Row newRow = (Row) super.clone();
        newRow.setCells(new ArrayList<Cell>(cells.size()));

        return newRow;
    }


    public String toString() {
        return cells.toString();
    }

    public boolean equals(Object anotherObject) {
        if (anotherObject == this) {
            return true;
        }

        //type
        if (!(anotherObject instanceof Row)) {
            return false;
        }

        Row anotherRow = (Row) anotherObject;

        if (!super.equals(anotherRow)) {
            return false;
        }

        List<Cell> cells1 = getCells();
        List<Cell> cells2 = anotherRow.getCells();

        return BeeEqualsUtils.equalsList(cells1, cells2);
    }

    public int hashCode() {
        return super.hashCode();
    }
}
