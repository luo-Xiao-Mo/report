package com.gwssi.struct.model.style.property;



/**
 * 属性常量
 * 
 * @version 1.0 2013 xingye
 */
public class PropertyConstants {
    

	// 属性前缀
    public static final String PREFIX_CT = "_ct_";  // 类型前缀
    public static final String PREFIX_EXP = "_exp_";  // 已运算表达式前缀
    
    public static final String PREFIX_ERRMSG = "_errmsg_"; // 错误消息前缀
    public static final String PREFIX_ERRDETAIL = "_errdetail_"; // 错误消息前缀
    
    // version
    public static final String VERSION = "20151010";

    // cellValue
    public static final String CELLVALUE_ERROR_RESULT_STR = "!!ERROR!!";
    
    //单元格类型
    /**
     * 单元格类型：数值
     */
    public static final int CELL_TYPE_NUMERIC = 0;
    /**
     * 单元格类型：文本
     */
    public static final int CELL_TYPE_STRING = 1;
    /**
     * 单元格类型：公式
     */
    public static final int CELL_TYPE_FORMULA = 2;
    /**
     * 单元格类型：空白
     */
    public static final int CELL_TYPE_BLANK = 3;
    /**
     * 单元格类型：布尔
     */
    public static final int CELL_TYPE_BOOLEAN = 4;
    /**
     * 单元格类型：错误
     */
    public static final int CELL_TYPE_ERROR = 5;
    
    
    // 合并类型
    public static final int MERGE_TYPE_NOT = 0;// 普通格子
    public static final int MERGE_TYPE_MAIN = 1;// 合并区域首格
    public static final int MERGE_TYPE_BLANK = 2;// 合并区域非首格
    
    
    // 扩展类型
    public static final int extendType_COLUMN = 0;// 横向
    public static final int extendType_ROW = 1; // 纵向
    public static final int extendType_NONE = -1; // 无扩展

    // 锁定
    public static final int freeze_yes = 1;
    public static final int freeze_no = 0;
    // 锁定
    public static final int locked_yes = 1;
    public static final int locked_no = 0;

    
    // ==== 填报 ====
    
    // 是否允许填报
    public static final int canFill_yes = 1;  // 可填报
    public static final int canFill_no = 0;   // 不可填报
    
    // 填报类型
    public static final int FILLOPTION_TEXT = 0;    // 文本
    public static final int FILLOPTION_NUMBER = 1;  // 数值
    public static final int FILLOPTION_CODE = 2;    // 代码表
    public static final int FILLOPTION_TIME = 3;    // 时间
    public static final int FILLOPTION_DATE = 4;    // 日期
    public static final int FILLOPTION_PULLTREE = 5;  // 下拉树

    // ==== 数据类型 =====
    
    public static final int DATATYPE_STRING = 1;    // 字符
    public static final int DATATYPE_INTEGER = 2;   // 整型
    public static final int DATATYPE_FLOAT = 3;     // 浮点型
    public static final int DATATYPE_BOOLEAN = 4;   // 布尔型
    public static final int DATATYPE_DATE = 5;      // 日期
    public static final int DATATYPE_DATETIME = 6;  // 日期时间
    public static final int DATATYPE_PERSENT = 7;  // 百分比0.00%


    // background
    /** 背景颜色：#fff*/
    public static final String DEFAULT_BACKGROUND_COLOR = "#fff";
    /** 背景图片：无*/
    public static final String DEFAULT_BACKGROUND_IMAGE = null;
    /** 背景图片重复与否：否*/
    public static final String DEFAULT_BACKGROUND_REPEAT = null;
    /** 背景图片位置：右上*/
    public static final String DEFAULT_BACKGROUND_POSITION = null;
    
    
    // border
    
    public static final String DEFAULT_BORDER_COLOR = "#ccc";
    
    /** 边框样式：无 **/
    public static final int BORDER_STYLE_NONE = 0;
    /** 边框样式：实线 **/
    public static final int BORDER_STYLE_SOLID = 2;
    /** 边框样式：虚线 **/
    public static final int BORDER_STYLE_DASHED = 3;
    /** 边框样式：双线 **/
    public static final int BORDER_STYLE_DOUBLE = 6;
    /** 边框样式：点线  **/
    public static final int BORDER_STYLE_DOTTED = 7;
    
    
    /** 默认边框宽度 **/
    public static final float DEFAULT_BORDER_WIDTH = 0;
    /** 默认宽度倍 基数 **/
    public static final float BORDER_WIDTH_TIMESBASE = 1;
    
    /**边框宽度:0.5倍 **/
    public static final float HALFTIMES_BORDER_WIDTH = (float)0.5*BORDER_WIDTH_TIMESBASE;
    /**边框宽度:1倍 **/
    public static final float ONETIMES_BORDER_WIDTH = 1*BORDER_WIDTH_TIMESBASE;
    /**边框宽度:2倍 **/
    public static final float TWOTIMES_BORDER_WIDTH = 2*BORDER_WIDTH_TIMESBASE;
    /**边框宽度:4倍 **/
    public static final float FOURTIMES_BORDER_WIDTH = 4*BORDER_WIDTH_TIMESBASE;
    
    // align
    
    /** 水平对齐：普通 **/
    public static final short ALIGN_GENERAL = 0;
    /** 水平对齐：左对齐 **/
    public static final short ALIGN_LEFT = 1;
    /** 水平对齐：中对齐 **/
    public static final short ALIGN_CENTER = 2;
    /** 水平对齐：右对齐 **/
    public static final short ALIGN_RIGHT = 3;
    
    
    /** 垂直对齐：顶端对齐 **/
    public static final short VERTICAL_ALIGN_TOP = 0;
    /** 垂直对齐：中间对齐 **/
    public static final short VERTICAL_ALIGN_MIDDLE = 1;
    /** 垂直对齐：底端对齐 **/
    public static final short VERTICAL_ALIGN_BOTTOM = 2;
    
    
    // font
    public static final String DEFAULT_FONT_FAMILY = "宋体";
    public static final String DEFAULT_FONT_COLOR = "#000";
    public static final float DEFAULT_FONT_SIZE = 12f;

    public static final short hidden_yes = 1;
    public static final short hidden_no = 0;
    
    public static final short font_bold_yes = 1;
    public static final short font_bold_no = 0;
    
    public static final short font_italic_yes = 1;
    public static final short font_italic_no = 0;
    
    public static final short font_underline_yes = 1;
    public static final short font_underline_no = 0;
    
    // paragraph
    public static final float DEFAULT_TEXT_INDENT = 0;
    
    
    public static final short TEXT_CONTORL_HIDDEN = 1;//不换行显示
    public static final short TEXT_CONTORL_WRAP = 2;//换行显示
    public static final short TEXT_CONTORL_REDUCE = 3;//缩小显示
    
    // size
    public static final String DEFAULT_LINE_HEIGHT = "auto";
    
    // hidden
    
    // row
    public static final float DEFAULT_ROW_HEIGHT = 20f;
    
    // column
    public static final float DEFAULT_COLUMN_WIDTH = 100f;
    
    //
    public static final int DEFAULT_COLSPAN = 1;
    public static final int DEFAULT_ROWSPAN = 1;
    
    //
    // 浮动类型
    public static final int floatType_Row = 0; // 行浮动
    public static final int floatType_Column = 1; // 列浮动
    
    public static final int DEFAULT_FLOAT_COLSPAN = 1;
    public static final int DEFAULT_FLOAT_ROWSPAN = 1;
    
    
    //
    public static final int SHEET_TYPE_NORMAL = 1;  // 普通表
    public static final int SHEET_TYPE_ANALYSIS = 2;  // 分析表
    
    //
    public static final int SUMMARIZE_TYPE_NONE = 0; // 汇总方式，无
    public static final int SUMMARIZE_TYPE_SUM = 1; // 汇总方式，求和
    public static final int SUMMARIZE_TYPE_AVG = 2; // 汇总方式，平均值
    public static final int SUMMARIZE_TYPE_MAX = 3; // 汇总方式，最大值
    public static final int SUMMARIZE_TYPE_MIN = 4; // 汇总方式，最小值
    public static final int SUMMARIZE_TYPE_COUNT = 5; // 汇总方式，计数
    
}
