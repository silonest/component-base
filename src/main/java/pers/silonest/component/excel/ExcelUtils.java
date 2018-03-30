package pers.silonest.component.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * excel处理的工具类.
 * 
 * @author 陈晨
 * @time 2017年12月6日 上午9:43:57
 * @since v1.0.0
 * @version v0.0.1
 */
public class ExcelUtils {
  /**
   * 删除空行.
   * 
   * @param sheet sheet对象
   * @return 返回删除空行后的sheet对象
   */
  @SuppressWarnings("deprecation")
  public static Sheet removeEmptyRows(Sheet sheet) {
    for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
      Row row = sheet.getRow(rowIndex);
      boolean flag = false;
      if (row != null) {
        short rowSize = row.getLastCellNum();
        for (int i = 0; i < rowSize; i++) {
          Object value = null;
          Cell cell = row.getCell(i);
          if (cell != null) {
            int cellType = cell.getCellType();
            switch (cellType) {
              case Cell.CELL_TYPE_NUMERIC:
                value = cell.getNumericCellValue();
                break;
              case Cell.CELL_TYPE_STRING:
                value = cell.getStringCellValue();
                break;
              case Cell.CELL_TYPE_FORMULA:
                value = cell.getStringCellValue();
                break;
              case Cell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
              default:
                value = null;
                break;
            }
            if (value != null) {
              flag = true;
              break;
            }
          } else {
            break;
          }
        }
      }
      if (!flag) {
        sheet.removeRow(row);
      }
    }
    return sheet;
  }
}
