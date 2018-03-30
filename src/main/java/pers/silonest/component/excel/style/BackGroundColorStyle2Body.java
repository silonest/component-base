package pers.silonest.component.excel.style;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;

/**
 * 背景色的样式. 装饰模式，基类为CellStyleMaker，是一个单元格样式生成器。调用该类可以为原始样式提供全文的白色背景色。
 * 
 * @author 陈晨
 * @time 2014年12月12
 * @version v1.0.0
 * @since v0.0.1
 */
public class BackGroundColorStyle2Body extends CellStyleMaker {
  private ExcelCellStyle ecs = null;

  /**
   * 构造方法.
   * 
   * @param ecs 单元格样式
   */
  public BackGroundColorStyle2Body(ExcelCellStyle ecs) {
    this.ecs = ecs;
    if (ecs == null) {
      throw new InstantiationError("Illegal data,Can't to create object.");
    }
  }

  @Override
  public CellStyle getStyle() {
    ecs.getStyle().setFillBackgroundColor(FillPatternType.NO_FILL.getCode());
    return ecs.getStyle();
  }
}
