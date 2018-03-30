package pers.silonest.component.excel.style;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * body内字体的样式.装饰模式，基类为ExcelCellStyle，是一个单元格样式生成器。调用该类可以为原始样式提供除表头以外的所有文本的字体样式（宋体，10号字）。
 * 
 * @author 陈晨
 * @time 2014年12月12日
 * @since v1.0.0
 * @version v0.0.1
 */
public class FontStyle2Body extends CellStyleMaker {
  private ExcelCellStyle ecs = null;
  private Workbook wb = null;

  /**
   * 构造方法.
   * 
   * @param wb excel文本
   * @param ecs 单元格样式
   */
  public FontStyle2Body(Workbook wb, ExcelCellStyle ecs) {
    this.ecs = ecs;
    this.wb = wb;
    if (ecs == null || wb == null) {
      throw new InstantiationError("无法创建对象，传入数据不合法");
    }
  }

  @Override
  public CellStyle getStyle() {
    Font font = wb.getFontAt(Font.DEFAULT_CHARSET);
    font.setFontHeightInPoints((short) 10);
    font.setFontName("宋体");
    ecs.getStyle().setFont(font);
    ecs.getStyle().setWrapText(true);
    return this.ecs.getStyle();
  }
}
