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
    //??????
    public static final String textIndent = "textIndent";
    //??????????????????????????????????????????????????????????????????
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

    // ???????????????
    public static final String comment = "comment";

    // =================

    // ???????????????
    public static final String locked = "locked";

    // ===================

    // float ??????
    public static final String float_uuid = "float_uuid";//??????????????????
    public static final String float_type = "float_type";//???????????????????????????????????????????????????
    public static final String float_level = "float_level";//???????????????1?????????2?????????????????????
    public static final String float_rowspan = "float_rowspan";//??????????????????
    public static final String float_colspan = "float_colspan";//??????????????????
    public static final String float_datasource = "float_datasource";//??????????????????
    public static final String float_onchange = "float_onchange";//??????????????????????????????????????????

    //??????????????????????????????
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

    //????????????
    public static final String PARM_LIST = "parmList";

    //??????
    public static final String CONDITTON = "condition";
    //????????????????????????original
    public static final String OR_EXP = "originalExp";
    //??????????????????
    public static final String BGQ = "BGQ";
    //???????????????
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
    //2022-08-16??????
    public static final String CELL_COORDINATE = "coordinate";
    public static final String CELL_MAIN = "main";
    public static final String CELL_SLIDER = "slider";
}
