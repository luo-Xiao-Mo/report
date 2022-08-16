package com.gwssi.struct.utils;

/**
 * 单元格名称
 * A1 对应第1列第1行， 列索引 0 行索引 0
 * 
 * @version 1.0 2013 xingye
 */
public class CellName {
	
	
	public static final String[] ROW_NAMES = new String[2000];
	
	public static final String[] COLUMN_NAMES = new String[200];
	
	public static final Object[] CELL_NAMES = new Object[2000];
	
	
	static {
		
		//
		for (int i = 0; i < ROW_NAMES.length; i++) {
			ROW_NAMES[i] = makeRowName(i);
		}
		
		for (int j = 0; j < COLUMN_NAMES.length; j++) {
			COLUMN_NAMES[j] = makeColumnName(j);
		}
		
		for (int i = 0; i < ROW_NAMES.length; i++) {
			
			String[] cellNames = new String[COLUMN_NAMES.length];
			CELL_NAMES[i] = cellNames;
			
			for (int j = 0; j < COLUMN_NAMES.length; j++) {
				cellNames[j] = COLUMN_NAMES[j]+ROW_NAMES[i];
			}
		}
		
	}
	

    private int rowIndex = -1;
    
    private int columnIndex = -1;
    
    private String cellName = null;
    
    private String rowName = null;
    
    private String columnName = null;

    
    public CellName() {}
    
    // =============
    
    
    public static String toRowName(int rowIndex) {
    	
    	if (rowIndex<0) {
			throw new IllegalArgumentException("不是行序号"+rowIndex);
		}
    	
    	if (rowIndex < ROW_NAMES.length) {
			return ROW_NAMES[rowIndex];
		}
    	
    	return makeRowName(rowIndex);
    }
    
    
    public static String toColumnName(int columnIndex) {
    	
    	if (columnIndex<0) {
			throw new IllegalArgumentException("不是列序号"+columnIndex);
		}
    	
    	if (columnIndex < COLUMN_NAMES.length) {
			return COLUMN_NAMES[columnIndex];
		}
    	
    	return makeColumnName(columnIndex);
    }
    
    
    public static String toCellName(int rowIndex, int columnIndex) {
    	
    	if (rowIndex<0) {
			throw new IllegalArgumentException("不是行序号"+rowIndex);
		}
    	
    	if (columnIndex<0) {
			throw new IllegalArgumentException("不是列序号"+columnIndex);
		}
    	
    	if (rowIndex < ROW_NAMES.length
			&& columnIndex < COLUMN_NAMES.length) {
    		return ((String[])CELL_NAMES[rowIndex])[columnIndex];
		}
    	
    	return makeColumnName(columnIndex)+makeRowName(rowIndex);
    	
    }
    
    
    // ===========
    
    /**
     * 
     * @param rowIndex 从0开始
     * @return
     * @author xingye 2015
     */
    protected static String makeRowName(int rowIndex) {
    	return String.valueOf(rowIndex+1);
    }
    
    
    /**
     * 
     * @param columnIndex 从0开始
     * @return
     * @author xingye 2015
     */
    protected static String makeColumnName(int columnIndex) {
    	
    	columnIndex++;
        
        // 解析
        char[] arrayOfChar = new char[8];
        int i = 8;
        
        // 列
        while (columnIndex >= 0) {
            i--;
            arrayOfChar[i] = (char) (65 + (columnIndex - 1) % 26);

            if ((columnIndex = (columnIndex - 1) / 26) == 0) {                
                break;
            }
        }
        
        return new String(arrayOfChar, i, 8 - i);
    	
    }
    
    
    // ===========
    
    
    public static CellName fromIndex(int rowIndex, int columnIndex) {
    	
    	CellName cellName = new CellName();
    	
    	cellName.setRowIndex(rowIndex);
    	cellName.setRowName(toRowName(rowIndex));
    	
    	cellName.setColumnIndex(columnIndex);
    	cellName.setColumnName(toColumnName(columnIndex));
    	
    	cellName.setCellName(toCellName(rowIndex, columnIndex));
    	
    	return cellName;
    	
    }
    
    
    public static CellName fromString(String cellNameStr) {
        
        if(cellNameStr == null || "".equals(cellNameStr)) {
            return null;
        }
        
        
        // 解析
        int rowIndex = 0, columnIndex = 0;
        
        char[] strs = cellNameStr.toCharArray();

        int colNameLen = 0; // 列名称索引结束位置
        int len = strs.length;
        
        int i = 0;  // 索引
        int ch;
        
        //a 97,z 122,A 65, Z 90, 0 48, 9 57;
        
        // 解析列
        while(i<len) {
        	
            ch = strs[i];
            
            // 小写字母转成大写字母进行下面的运算
            if (ch>=97 && ch<=122) {
                ch = ch - 32;
                strs[i] = (char)ch;
            }
            
            // 非列名，退出解析列
            if (ch<65 || ch>90) {
                colNameLen = i;
                break;
            }
            
            // 进行计算
            // 这里临时加1，便于进制运算。因为进制运算第一位不能为0
            columnIndex = (columnIndex*26 + ch - 65 + 1);
            
            //
            i++;
        } // while
        
        // 上面临时加1，这里减去
        columnIndex--;
        
        
        // 数字不能出现在开头
        if (i==0) {
            return null;
        }
        
        
        
        // 只有字母或着后面没有数字
        if (i==len) {
        	
        	CellName cellName = new CellName();
        	
        	cellName.setColumnName(cellNameStr);
        	cellName.setColumnIndex(columnIndex);
        	
        	return cellName;
        }

        
        // 解析行
        while (i<len) {
            ch = strs[i];
            
            // 非数字，退出解析
            if (ch<48 || ch>57) {
                break;
            }
            
            // 第一个数字不能为0
            if (ch==48 && rowIndex==0) {
                return null;
            }
            
            // 十进制运算，实际上第一位是从1开始的
            rowIndex = (rowIndex*10 + ch - 48);
            
            //
            i++;

        } // while
        
        // 上面从1开始，这里减去1
        rowIndex--;
        
        // 必须是完整名称
        if (i != len) {
        	return null;
        }
        
        
        CellName cellName = new CellName();
        
        cellName.setCellName(new String(strs, 0, len));
        
        cellName.setColumnIndex(columnIndex);
    	cellName.setColumnName(new String(strs, 0, colNameLen));
    	
    	cellName.setRowIndex(rowIndex);
    	cellName.setRowName(new String(strs, colNameLen, len-colNameLen));
    	
    	return cellName;

    }

    
    public String toString() {
    	StringBuilder buf = new StringBuilder();
    	
    	buf.append(cellName);
    	buf.append("@").append(columnName).append("@").append(columnIndex);
    	buf.append("@").append(rowName).append("@").append(rowIndex);
    	
    	return buf.toString();
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

	public void setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
	}

	public String getCellName() {
		return cellName;
	}

	public void setCellName(String cellName) {
		this.cellName = cellName;
	}

	public String getRowName() {
		return rowName;
	}

	public void setRowName(String rowName) {
		this.rowName = rowName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	
	public boolean equals(Object obj) {
		
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof CellName)) {
			return false;
		}
		
		CellName o = (CellName)obj;
		
		return rowIndex == o.getRowIndex() 
				&& columnIndex == o.getColumnIndex()
				&& BeeEqualsUtils.equals(rowName, o.getRowName())
				&& BeeEqualsUtils.equals(columnName, o.getColumnName())
				&& BeeEqualsUtils.equals(cellName, o.getCellName());
	}

	public int hashCode() {
		return super.hashCode();
	}
}