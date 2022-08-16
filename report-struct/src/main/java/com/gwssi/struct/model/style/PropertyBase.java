package com.gwssi.struct.model.style;

import com.gwssi.struct.model.style.property.PropertyDefine;
import com.gwssi.struct.model.style.property.PropertyDefines;
import com.gwssi.struct.model.style.property.PropertyKey;
import com.gwssi.struct.utils.BeeEqualsUtils;
import com.gwssi.struct.utils.StrongTypeCast;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public abstract class PropertyBase implements Cloneable {

    /*
     * 属性数据，用于存cellValue等
     */
    protected Map<Object, Object> propertyMap;

    /**
     * 属性表达式，用于存cellValue的表达式等
     */
    protected Map<Object, String> exprMap;

    /*
     * 临时数据
     */
    protected transient Map<Object, Object> tempMap;


    // =============


    public String getName() {
        return getStringValue(PropertyKey.name);
    }


    public void setName(String name) {
        putProperty(PropertyKey.name, name);
    }


    public String getOriginalName() {
        return getStringValue(PropertyKey.originalName);
    }


    public void setOriginalName(String name) {
        putProperty(PropertyKey.originalName, name);
    }


    // ===============

    public Map<Object, Object> getPropertyMap() {
        return propertyMap;
    }

    public void setPropertyMap(Map<Object, Object> propMap) {
        this.propertyMap = propMap;
    }

    public Map<Object, String> getExprMap() {
        return exprMap;
    }

    public void setExprMap(Map<Object, String> expMap) {
        this.exprMap = expMap;
    }

    public Map<Object, Object> getTempMap() {
        return tempMap;
    }

    public void setTempMap(Map<Object, Object> tempMap) {
        this.tempMap = tempMap;
    }

    // =============

    public void ensureHasPropertyMap() {
        if (this.propertyMap == null) {
            this.propertyMap = new HashMap<Object, Object>();
        }
    }

    public void ensureHasExprMap() {
        if (this.exprMap == null) {
            this.exprMap = new HashMap<Object, String>();
        }
    }

    public void ensureHasTempMap() {
        if (this.tempMap == null) {
            this.tempMap = new HashMap<Object, Object>();
        }
    }

    // =================


    /**
     * <p>返回临时数据。</p>
     *
     * @param key
     * @return
     * @version 1.0 2014 xingye
     */
    public Object getTemp(Object key) {

        if (tempMap == null) {
            return null;
        }
        return tempMap.get(key);
    }

    /**
     * <p>设置临时数据。</p>
     *
     * @param key
     * @param value
     * @version 1.0 2014 xingye
     */
    public void putTemp(Object key, Object value) {

        ensureHasTempMap();
        tempMap.put(key, value);
    }

    /**
     * 移除临时数据
     *
     * @param key
     * @return
     * @author xingye 2015
     */
    public Object removeTemp(Object key) {

        if (tempMap == null) {
            return null;
        }
        return tempMap.remove(key);
    }


    // ====================

    /**
     * <p>返回属性值。本方法会寻找默认值。</p>
     *
     * @param key
     * @return
     * @version 1.0 2014 xingye
     */
    public Object getProperty(Object key) {

        Object val = null;

        // 取自有的值
        if (propertyMap != null) {
            val = propertyMap.get(key);
        }

        // 取默认值
        if (val == null) {

            PropertyDefine pd = PropertyDefines.getPropertyDefine(key);
            if (pd != null) {
                return pd.getDefaultValue();
            }
        }

        return val;
    }


    /**
     * 返回自己拥有的属性值，不读取默认值。没有值时返回null;
     *
     * @param key
     * @return
     * @version 1.0 2014 xingye
     */
    public Object getOwnProperty(Object key) {

        if (propertyMap != null) {
            return propertyMap.get(key);
        }

        return null;
    }


    /**
     * <p>设置属性值。</p>
     *
     * @param key
     * @param value
     * @version 1.0 2014 xingye
     */
    public void putProperty(Object key, Object value) {

        removeExpr(key);
        ensureHasPropertyMap();
        this.propertyMap.put(key, value);
    }


    public void removeProperty(Object key) {

        if (this.propertyMap == null) {
            return;
        }
        this.propertyMap.remove(key);
    }


    /**
     * 移除掉值为默认值的属性
     *
     * @author xingye 2015
     */
    public void removeDefaultValues() {

        if (propertyMap == null) {
            return;
        }


        Iterator<Entry<Object, Object>> entryIt = propertyMap.entrySet().iterator();
        Entry<Object, Object> entry;
        Object key, value, defValue;

        while (entryIt.hasNext()) {

            entry = entryIt.next();

            key = entry.getKey();
            value = entry.getValue();

            // 默认定义
            PropertyDefine pd = PropertyDefines.getPropertyDefine(key);

            if (pd != null) {

                defValue = pd.getDefaultValue();

                if (defValue == null) {

                    if (value == null) {
                        entryIt.remove();
                    }

                } else {

                    if (defValue.equals(value)) {
                        entryIt.remove();
                    }

                }

            }

        } // while

    }

    // ==================


    public String getExpr(Object key) {

        if (exprMap == null) {
            return null;
        }
        return this.exprMap.get(key);
    }

    public void putExpr(Object key, String value) {

        removeProperty(key);
        ensureHasExprMap();
        this.exprMap.put(key, value);
    }

    public void removeExpr(Object key) {

        if (this.exprMap == null) {
            return;
        }
        this.exprMap.remove(key);
    }


    // =====================

    public String getStringValue(Object key) {

        return StrongTypeCast.castToString(getProperty(key));
    }

    public boolean getBooleanValue(Object key) {

        Boolean obj = StrongTypeCast.castToBoolean(getProperty(key));
        if (obj == null) {
            throw new IllegalArgumentException("不存在" + key);
        }

        return obj.booleanValue();
    }

    public int getIntValue(Object key) {

        Number num = StrongTypeCast.castToNumber(getProperty(key));
        if (num == null) {
            throw new IllegalArgumentException("不存在" + key);
        }

        return num.intValue();
    }


    public int getIntValue(Object key, int defaultValue) {

        Number num = StrongTypeCast.castToNumber(getProperty(key));
        if (num == null) {
            return defaultValue;
        } else {
            return num.intValue();
        }

    }

    public short getShortValue(Object key) {

        Number num = StrongTypeCast.castToNumber(getProperty(key));
        if (num == null) {
            throw new IllegalArgumentException("不存在" + key);
        }

        return num.shortValue();
    }

    public float getFloatValue(Object key) {

        Number num = StrongTypeCast.castToNumber(getProperty(key));
        if (num == null) {
            throw new IllegalArgumentException("不存在" + key);
        }

        return num.floatValue();
    }


    // ========================

    public void clear() {

        propertyMap = null;
        exprMap = null;
        tempMap = null;

    }

    public String toString() {
        return String.valueOf(propertyMap);
    }

    public boolean equals(Object anotherObject) {

        if (this == anotherObject) {
            return true;
        }

        //type
        if (!(anotherObject instanceof PropertyBase)) {
            return false;
        }

        PropertyBase anotherPB = (PropertyBase) anotherObject;

        //fields
        Map<Object, Object> propMap1 = getPropertyMap();
        Map<Object, Object> propMap2 = anotherPB.getPropertyMap();

        Map<Object, String> expMap1 = getExprMap();
        Map<Object, String> expMap2 = anotherPB.getExprMap();

        Map<Object, Object> ctxMap1 = getTempMap();
        Map<Object, Object> ctxMap2 = anotherPB.getTempMap();

        return BeeEqualsUtils.equalsMap(propMap1, propMap2) && BeeEqualsUtils.equalsMap(expMap1, expMap2)
                && BeeEqualsUtils.equalsMap(ctxMap1, ctxMap2);
    }


    public PropertyBase clone() {

        PropertyBase newObj = null;

        try {
            newObj = (PropertyBase) super.clone();

        } catch (CloneNotSupportedException e) {
            // 不会出现
        }

        if (newObj != null) {
            if (this.propertyMap != null) {
                Map<Object, Object> pMap = new HashMap<Object, Object>(this.propertyMap);
                newObj.propertyMap = pMap;
            }
            if (this.exprMap != null) {
                Map<Object, String> eMap = new HashMap<Object, String>(this.exprMap);
                newObj.exprMap = eMap;
            }
            if (this.tempMap != null) {
                Map<Object, Object> cMap = new HashMap<Object, Object>(this.tempMap);
                newObj.tempMap = cMap;
            }
        }

        return newObj;
    }

    public int hashCode() {
        return super.hashCode();
    }
}
