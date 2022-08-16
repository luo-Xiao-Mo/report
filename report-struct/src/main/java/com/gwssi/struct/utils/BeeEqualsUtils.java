package com.gwssi.struct.utils;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BeeEqualsUtils {
	
	
	/**
	 * 
	 * @param map1
	 * @param map2
	 * @throws
	 */
    public static <K, V> boolean equalsMap(Map<K, V> map1, Map<K, V> map2) {
    	//null
    	if(map1 == null || map2 == null){
    		return map1 == null && map2 == null;
    	}
    	
    	//size
    	int size1 = map1.size();
    	int size2 = map2.size();
    	
    	if(size1 != size2){
    		return false;
    	}
    	
    	if(size1 == 0){
    		return true;
    	}
    	
    	//遍历entry
    	Iterator<Entry<K, V>> iter = map1.entrySet().iterator();
		while(iter.hasNext()){
			Entry<K, V> e = iter.next();
			Object key1 = e.getKey();
			Object value1 = e.getValue();
			
			if(!map2.containsKey(key1)){ 
				return false;
			}
			
			Object value2 = map2.get(key1);
			if(!BeeEqualsUtils.deepEquals(value1, value2)){
				return false;
			}
		}
		
		return true;
	}
    
    
    /**
     * 比较两个List对象是否相等。
     * 
     * @Title: listEquals
     * @param <E>
     * @param list1
     * @param list2
     * @return
     * @return: boolean
     * @throws
     */
    public static <E> boolean equalsList(List<E> list1, List<E> list2) {
    	//null
    	if(list1 == null || list2 == null){
    		return list1 == null && list2 == null;
    	}
    	
    	//size
    	int size1 = list1.size();
    	int size2 = list2.size();
    	if(size1 != size2){
    		return false;
    	}
    	
    	//same order, same elements
    	E elem1, elem2;
    	for(int i = 0; i < size1; i++){
    		elem1 = list1.get(i);
    		elem2 = list2.get(i);
    		
    		if(!BeeEqualsUtils.deepEquals(elem1, elem2)){
    			return false;
    		}
    	}
    	
    	return true;
	}
    
	
	/**
	 * 简单对象比较。不处理数组等情形。
	 * 
	 * @param s
	 * @param d
	 * @return
	 * @author xingye 2015
	 */
	public static boolean equals(Object s, Object d) {
        
        if (s == d) {
            return true;
        }
        
        if (s == null) {
            return (d == null);
        }
        
        return s.equals(d);
    }

	
	/**
	 * 比较。处理数组等情形。
	 * 
	 * @param s
	 * @param d
	 * @return
	 * @author xingye 2015
	 */
    public static boolean deepEquals(Object s, Object d) {
        
        if (s == d) {
            return true;
        }
        
        if (s == null) {
            return (d == null);
        }
        
        if (s.getClass().isArray()) {
            
            if (!d.getClass().isArray()) {
                return false;
            }
                
            int len1 = Array.getLength(s);
            int len2 = Array.getLength(d);
            
            if (len1 != len2) {
                return false;
            }
            
            for (int i = 0; i < len1; i++) {
                Object o1 = Array.get(s, i);
                Object o2 = Array.get(d, i);
                if (!deepEquals(o1, o2)) {
                    return false;
                }
            }
            
            return true;
            
        }
        
        return s.equals(d);
        
    }
    
    
}
