package pers.silonest.component.excel.style;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;

/**
 * 设置Title的单元格背景颜色
 * 
 * @author 陈晨
 * @time 2014年10月22日
 * @since v1.0.0
 * @version v0.0.1
 */
public class BackGroundColorStyle2Title extends CellStyleMaker {
  private ExcelCellStyle ecs = null;

  /**
   * 构造方法.
   * 
   * @param ecs 单元格样式
   */
  public BackGroundColorStyle2Title(ExcelCellStyle ecs) {
    this.ecs = ecs;
    if (ecs == null) {
      throw new InstantiationError("Illegal data,Can't to create object.");
    }
  }

  @Override
  public CellStyle getStyle() {
    ecs.getStyle().setFillPattern(FillPatternType.SOLID_FOREGROUND);
    return ecs.getStyle();
  }
}
