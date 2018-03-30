package pers.silonest.component.excel.style;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * 边框宽度和颜色的样式。 装饰模式，基类为ExcelCellStyle，是一个单元格样式生成器。调用该类可以为原始样式提供全文的文本框边框1宽度，黑色的默认格式。
 * 
 * @author 陈晨
 * @time 2014年12月12日
 * @since v1.0.0
 * @version v0.0.1
 */
public class BorderStyle extends CellStyleMaker {
  private ExcelCellStyle ecs = null;

  /**
   * 构造方法.
   * 
   * @param ecs 单元格样式
   */
  public BorderStyle(ExcelCellStyle ecs) {
    this.ecs = ecs;
    if (ecs == null) {
      throw new InstantiationError("Illegal data,Can't to create object.");
    }
  }

  @Override
  public CellStyle getStyle() {
    ecs.getStyle().setBorderBottom(org.apache.poi.ss.usermodel.BorderStyle.THIN);
    ecs.getStyle().setBorderLeft(org.apache.poi.ss.usermodel.BorderStyle.THIN);
    ecs.getStyle().setBorderRight(org.apache.poi.ss.usermodel.BorderStyle.THIN);
    ecs.getStyle().setBorderTop(org.apache.poi.ss.usermodel.BorderStyle.THIN);
    ecs.getStyle().setWrapText(true);
    ecs.getStyle().setLeftBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
    ecs.getStyle().setRightBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
    ecs.getStyle().setTopBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
    ecs.getStyle().setBottomBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
    return ecs.getStyle();
  }
}
