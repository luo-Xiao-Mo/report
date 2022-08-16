package com.gwssi.struct.model.style.property;

import java.util.HashMap;
import java.util.Map;


/**
 * 属性定义列表
 *
 * @author xingye 2015
 */
public class PropertyDefines {


    public static final Map<Object, PropertyDefine> pdMap = new HashMap<Object, PropertyDefine>();


    private static final Class<?> STRING = String.class;
    private static final Class<?> INTEGER = Integer.class;
    private static final Class<?> FLOAT = Float.class;
    private static final Class<?> SHORT = Short.class;

    static {

        // ======================

        add(PropertyKey.version, PropertyConstants.VERSION, "版本", STRING);

        // ==================

        add(PropertyKey.width, PropertyConstants.DEFAULT_COLUMN_WIDTH, "列宽", FLOAT);
        add(PropertyKey.height, PropertyConstants.DEFAULT_ROW_HEIGHT, "行高", FLOAT);

        // 行、列
        add(PropertyKey.hidden, PropertyConstants.hidden_no, "隐藏", SHORT);

        // =============

        // 值
        add(PropertyKey.cellValue, null, "单元格值", null);

        //背景
        add(PropertyKey.backgroundColor, PropertyConstants.DEFAULT_BACKGROUND_COLOR, "背景色", STRING);
        add(PropertyKey.backgroundImage, PropertyConstants.DEFAULT_BACKGROUND_IMAGE, "背景图片", STRING);
        add(PropertyKey.backgroundPosition, PropertyConstants.DEFAULT_BACKGROUND_POSITION, "背景图片位置", STRING);
        add(PropertyKey.backgroundRepeat, PropertyConstants.DEFAULT_BACKGROUND_REPEAT, "背景图片重复与否", STRING);

        //字体
        add(PropertyKey.fontColor, PropertyConstants.DEFAULT_FONT_COLOR, "字体颜色", STRING);
        add(PropertyKey.fontFamily, PropertyConstants.DEFAULT_FONT_FAMILY, "字体样式", STRING);
        add(PropertyKey.fontSize, PropertyConstants.DEFAULT_FONT_SIZE, "字体大小", FLOAT);

        add(PropertyKey.fontBold, PropertyConstants.font_bold_no, "加粗", SHORT);
        add(PropertyKey.fontItalic, PropertyConstants.font_italic_no, "斜体", SHORT);
        add(PropertyKey.fontUnderline, PropertyConstants.font_underline_no, "下划线", SHORT);
        //
        add(PropertyKey.textIndent, PropertyConstants.DEFAULT_TEXT_INDENT, "缩进", FLOAT);

        add(PropertyKey.textControl, PropertyConstants.TEXT_CONTORL_HIDDEN, "文本显示方式", SHORT);

        //对齐
        add(PropertyKey.align, PropertyConstants.ALIGN_GENERAL, "水平对齐", SHORT);
        add(PropertyKey.verticalAlign, PropertyConstants.VERTICAL_ALIGN_MIDDLE, "垂直对齐", SHORT);

        //边框
        add(PropertyKey.borderRightColor, "#ffffff", "右边框颜色", STRING);
        add(PropertyKey.borderRightStyle, PropertyConstants.BORDER_STYLE_NONE, "右边框样式", INTEGER);
        add(PropertyKey.borderRightWidth, PropertyConstants.DEFAULT_BORDER_WIDTH, "右边框宽度", FLOAT);

        add(PropertyKey.borderBottomColor, "#ffffff", "下边框颜色", STRING);
        add(PropertyKey.borderBottomStyle, PropertyConstants.BORDER_STYLE_NONE, "下边框样式", INTEGER);
        add(PropertyKey.borderBottomWidth, PropertyConstants.DEFAULT_BORDER_WIDTH, "下边框宽度", FLOAT);

        add(PropertyKey.borderLeftColor, "#ffffff", "左边框颜色", STRING);
        add(PropertyKey.borderLeftStyle, PropertyConstants.BORDER_STYLE_NONE, "左边框样式", INTEGER);
        add(PropertyKey.borderLeftWidth, PropertyConstants.DEFAULT_BORDER_WIDTH, "左边框宽度", FLOAT);

        add(PropertyKey.borderTopColor, "#ffffff", "上边框颜色", STRING);
        add(PropertyKey.borderTopStyle, PropertyConstants.BORDER_STYLE_NONE, "上边框样式", INTEGER);
        add(PropertyKey.borderTopWidth, PropertyConstants.DEFAULT_BORDER_WIDTH, "上边框宽度", FLOAT);

        //
        add(PropertyKey.name, null, "名称", STRING);
        add(PropertyKey.originalName, null, "原始名称", STRING);

        //
        add(PropertyKey.cellType, null, "单元格类型", INTEGER);

        // 扩展
        add(PropertyKey.extendType, PropertyConstants.extendType_ROW, "扩展方向", INTEGER);
        // 冻结
        add(PropertyKey.freezeStatus, PropertyConstants.freeze_no, "冻结状态", INTEGER);

        // 锁定
        add(PropertyKey.locked, PropertyConstants.locked_no, "锁定", INTEGER);

        // 浮动
        add(PropertyKey.float_type, null, "浮动方向", INTEGER);
        add(PropertyKey.float_level, null, "浮动层级", INTEGER);
        add(PropertyKey.float_uuid, null, "浮动唯一标示", STRING);
        add(PropertyKey.float_colspan, null, "浮动区域跨列数", INTEGER);
        add(PropertyKey.float_rowspan, null, "浮动区域跨行数", INTEGER);
        add(PropertyKey.float_datasource, null, "浮动数据来源", STRING);
        add(PropertyKey.float_uniqueFloatCodeList, null, "唯一标示列表", STRING);
        add(PropertyKey.float_uniqueFlag, "0", "是否唯一", STRING);
        add(PropertyKey.float_gatherType, "0", "汇总方式", STRING);

        //dataSource目前被urlValue替代 以后要改回来
        add(PropertyKey.fill_urlValue, null, "数据来源", STRING);

        //
        add(PropertyKey.colspan, PropertyConstants.DEFAULT_COLSPAN, "跨列数", INTEGER);
        add(PropertyKey.rowspan, PropertyConstants.DEFAULT_ROWSPAN, "跨行数", INTEGER);

        //fill_fillType
        add(PropertyKey.fill_canFill, PropertyConstants.canFill_no, "是否可填报", INTEGER);
        add(PropertyKey.fill_fillType, PropertyConstants.FILLOPTION_TEXT, "填报类型", INTEGER);
        add(PropertyKey.fill_dataType, PropertyConstants.DATATYPE_STRING, "数据类型", INTEGER);
        add(PropertyKey.BGQ, PropertyKey.BGQ, "数据类型", STRING);
//
//        //元数据
//        add("metaDataPackage", null ,"数据类型", CellMetaDataPackage.class);
    }


    protected static void add(Object key, Object defaultValue, String name, Class<?> clazz) {

        PropertyDefine pd = new PropertyDefine();
        pd.setKey(key);
        pd.setName(name);
        pd.setDefaultValue(defaultValue);
        pd.setClazz(clazz);

        pdMap.put(key, pd);
    }

    public static PropertyDefine getPropertyDefine(Object key) {
        return pdMap.get(key);
    }


}
