package com.gwssi.struct.model.style;

import java.io.Serializable;

public class Column extends PropertyBase implements Serializable {

    public Column() {
    }

    // ==========


    // ===========


    public Column clone() {

        Column column = null;

        column = (Column) super.clone();

        return column;
    }

    public boolean equals(Object anotherObject) {
        if (anotherObject == this) {
            return true;
        }

        if (!(anotherObject instanceof Column)) {
            return false;
        }

        return super.equals((Column) anotherObject);
    }

    public int hashCode() {
        return super.hashCode();
    }
}
