package pers.silonest.component.excel;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import pers.silonest.component.excel.exception.ExcelTransferException;

public class Reader {

  /**
   * 读取stream中的内容到二维字符串数组.
   * 
   * @param inputStream 读取流
   * @return 读取结果
   */
  public Map<String, String[][]> read(InputStream inputStream) {
    try {
      Workbook workbook = WorkbookFactory.create(inputStream);
      Map<String, String[][]> result = new HashMap<String, String[][]>();
      for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
        Sheet sheet = workbook.getSheetAt(i);
        String sheetName = sheet.getSheetName();
        String[][] arrayCache = getSheetData(workbook, sheet);
        if (arrayCache == null) {
          continue;
        }
        String[][] arrayTemp = result.get(sheetName);
        if (arrayTemp != null && arrayTemp.length != 0) {
          System.arraycopy(arrayCache, 0, arrayTemp, 0, arrayCache.length);
        } else {
          result.put(sheetName, arrayCache);
        }
      }
      return result;
    } catch (EncryptedDocumentException ex) {
      throw new ExcelTransferException(ex);
    } catch (InvalidFormatException ex) {
      throw new ExcelTransferException(ex);
    } catch (IOException ex) {
      throw new ExcelTransferException(ex);
    }
  }

  /**
   * 读取多个文件的内容到二维字符串数组.
   * 
   * @param file 文件
   * @return 读取结果
   */
  // public Map<String, String[][]> read(MultipartFile file) {
  // try {
  // return read(file.getInputStream());
  // } catch (IOException ex) {
  // throw new ExcelTransferException(ex);
  // }
  // }

  private String[][] getSheetData(Workbook workbook, Sheet sheet) {
    if (sheet.getLastRowNum() < 2) {
      return null;
    }
    Row title = sheet.getRow(1);// 默认第二行是列头。
    short columnNumber = title.getLastCellNum();
    sheet = ExcelUtils.removeEmptyRows(sheet);
    String[][] arrayCache = new String[sheet.getLastRowNum() - 1][columnNumber];
    for (int rowIndex = 2; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
      Row row = sheet.getRow(rowIndex);
      for (Cell cell : row) {
        cell.setCellType(CellType.STRING);
        String value = cell.getStringCellValue();
        arrayCache[rowIndex - 2][cell.getColumnIndex()] = value;
      }
    }
    return arrayCache;
  }
}
