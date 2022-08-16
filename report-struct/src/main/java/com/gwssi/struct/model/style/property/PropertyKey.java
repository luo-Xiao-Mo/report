package com.gwssi.struct.model.style.property;


public class PropertyKey {

    // version
    public static final String version = "version";

    // value

    public static final String cellValue = "cellValue";
    public static final String cellDisplayValue = "cellDisplayValue";
    public static final String cellDisplayFormat = "cellDisplayFormat";

    // cellType

    public static final String cellType = "cellType";

    // extendType

    public static final String extendType = "extendType";

    public static final String freezeStatus = "freezeStatus";


    // cellPosition

    public static final String name = "name";
    public static final String originalName = "originalName";


    // background

    public static final String backgroundImage = "backgroundImage";
    public static final String backgroundRepeat = "backgroundRepeat";
    public static final String backgroundPosition = "backgroundPosition";
    public static final String backgroundSize = "backgroundSize";
    public static final String backgroundColor = "backgroundColor";

    // border

    public static final String borderTopStyle = "borderTopStyle";
    public static final String borderTopWidth = "borderTopWidth";
    public static final String borderTopColor = "borderTopColor";

    public static final String borderLeftStyle = "borderLeftStyle";
    public static final String borderLeftWidth = "borderLeftWidth";
    public static final String borderLeftColor = "borderLeftColor";

    public static final String borderBottomStyle = "borderBottomStyle";
    public static final String borderBottomWidth = "borderBottomWidth";
    public static final String borderBottomColor = "borderBottomColor";

    public static final String borderRightStyle = "borderRightStyle";
    public static final String borderRightWidth = "borderRightWidth";
    public static final String borderRightColor = "borderRightColor";

    // align

    public static final String align = "align";
    public static final String verticalAlign = "verticalAlign";

    // font

    public static final String fontFamily = "fontFamily";
    public static final String fontSize = "fontSize";
    public static final String fontBold = "fontBold";
    public static final String fontItalic = "fontItalic";
    public static final String fontUnderline = "fontUnderline";
    public static final String fontColor = "fontColor";

    // paragraph
    //缩进
    public static final String textIndent = "textIndent";
    //文本显示方式溢出隐藏，溢出折行，自动缩小文本
    public static final String textControl = "textControl";

    public static final String lineHeight = "lineHeight";

    // hidden
    public static final String hidden = "hidden";

    // row
    public static final String height = "height";

    // column
    public static final String width = "width";

    //
    public static final String colspan = "colspan";
    public static final String rowspan = "rowspan";

    // ===========

    // 批注、注释
    public static final String comment = "comment";

    // =================

    // 锁定格属性
    public static final String locked = "locked";

    // ===================

    // float 浮动
    public static final String float_uuid = "float_uuid";//浮动位移编号
    public static final String float_type = "float_type";//浮动类型（不浮动、行浮动、列浮动）
    public static final String float_level = "float_level";//浮动层级（1级浮动2级浮动。。。）
    public static final String float_rowspan = "float_rowspan";//浮动纵跨行数
    public static final String float_colspan = "float_colspan";//浮动横跨列数
    public static final String float_datasource = "float_datasource";//浮动数据来源
    public static final String float_onchange = "float_onchange";//浮动下拉树数据改变时触发事件

    //浮动编码唯一标示列表
    public static final String float_uniqueFloatCodeList = "float_uniqueFloatCodeList";
    public static final String float_uniqueFlag = "float_uniqueFlag";
    public static final String float_gatherType = "float_gatherType";


    // =====================
    // exportOption

    public static final String excel_dataType = "excel_dataType";

    // =====================
    // fillOption

    public static final String fill_canFill = "fill_canFill";

    public static final String fill_fillType = "fill_fillType";
    public static final String fill_dataType = "fill_dataType";

    public static final String fill_urlValue = "fill_urlValue";

    // ==================
    // metaData

    //参数列表
    public static final String PARM_LIST = "parmList";

    //条件
    public static final String CONDITTON = "condition";
    //未改变时候表达式original
    public static final String OR_EXP = "originalExp";
    //默认的简写键
    public static final String BGQ = "BGQ";
    //数据源名称
    public static final String DATA_SOURCE_NAME = "dataSourceName";

    public static final String EXP_VALUE = "_exp_cellValue";

    // cssProps
    public static final String cssProps[] = {
            PropertyKey.fontSize,
            PropertyKey.fontColor,
            PropertyKey.fontFamily,
            PropertyKey.fontBold,
            PropertyKey.fontItalic,
            PropertyKey.fontUnderline,
            PropertyKey.align,
            PropertyKey.verticalAlign,
            PropertyKey.textControl,
            PropertyKey.backgroundColor,
            PropertyKey.borderTopStyle,
            PropertyKey.borderTopWidth,
            PropertyKey.borderTopColor,
            PropertyKey.borderLeftStyle,
            PropertyKey.borderLeftWidth,
            PropertyKey.borderLeftColor,
            PropertyKey.borderBottomStyle,
            PropertyKey.borderBottomWidth,
            PropertyKey.borderBottomColor,
            PropertyKey.borderRightStyle,
            PropertyKey.borderRightWidth,
            PropertyKey.borderRightColor,
            PropertyKey.cellType,
            PropertyKey.name
    };
    //2022-08-16补充
    public static final String CELL_COORDINATE = "coordinate";
    public static final String CELL_MAIN = "main";
    public static final String CELL_SLIDER = "slider";
}
