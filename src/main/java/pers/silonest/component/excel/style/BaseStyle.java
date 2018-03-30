package pers.silonest.component.excel.style;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * 基本样式。 一个空的excel样式对象。
 * 
 * @author 陈晨
 * @time 2014年12月12日
 * @version v1.0.0
 * @since v0.0.1
 */
public class BaseStyle extends CellStyleMaker {
  private CellStyle cs = null;

  /**
   * 构造方法.
   * 
   * @param wb excel文本对象
   */
  public BaseStyle(Workbook wb) {
    cs = wb.createCellStyle();
    if (cs == null) {
      throw new InstantiationError("Illegal data,Can't to create object.");
    }
  }

  @Override
  public CellStyle getStyle() {
    if (cs != null) {
      return cs;
    } else {
      throw new InstantiationError("Illegal data,Can't to create object.");
    }
  }

}
