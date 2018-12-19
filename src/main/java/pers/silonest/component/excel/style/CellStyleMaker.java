package pers.silonest.component.excel.style;

import org.apache.poi.ss.usermodel.CellStyle;

/**
 * 单元格样式生成器.所有装饰模式单元格类的基类。
 * 
 * @author 陈晨
 * @time 2014年12月12日
 * @version v0.0.1
 * @since v1.0.0
 */
public abstract class CellStyleMaker extends ExcelCellStyle {
  @Override
  public abstract CellStyle getStyle();
}
