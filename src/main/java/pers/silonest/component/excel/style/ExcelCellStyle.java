package pers.silonest.component.excel.style;

import org.apache.poi.ss.usermodel.CellStyle;

/**
 * 装饰着模式所有类的基类.所有类的基类，用于声明接口。
 * 
 * @author 陈晨
 * @time 2014年12月12日
 * @since v1.0.0
 * @version v0.0.1
 */
public abstract class ExcelCellStyle {
  protected CellStyle cs;

  public abstract CellStyle getStyle();
}
