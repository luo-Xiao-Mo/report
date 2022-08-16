package com.gwssi.struct.model.style.property;

/**
 * 属性定义
 * 
 * @author xingye 2015
 *
 */
public class PropertyDefine implements Cloneable {

	/**
	 * key
	 */
    Object key;
    
    /**
     * 名称
     */
    String name;
    
    /**
     * 默认值
     */
    Object defaultValue;
    
    /**
     * 值类型
     */
    Class<?> clazz;
    
    
    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Object getDefaultValue() {
        return defaultValue;
    }
    
    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }


    public PropertyDefine clone() {
        
        PropertyDefine p = null;
        
        try {
            p = (PropertyDefine)super.clone();
        } catch (CloneNotSupportedException e) {
            // 不会出现
        }
        
        return p;
    }

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

    
}
