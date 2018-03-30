package pers.silonest.component.excel;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import pers.silonest.component.excel.pojo.Title;

/**
 * 生成xlsx格式的excel文档（ 2007及以后的格式）。
 * 
 * @time 2014年5月23日 上午9:45:28
 * @author 陈晨
 * @version v0.0.1
 * @since v1.0.0
 */
public class XlsxWriter extends Writer {
  /**
   * 生成excel2007及其以后格式的Workbook类.
   * 
   * @param paramsList 数据对象集合
   * @param sheetName sheet名字
   * @param pageSize 每页的记录数
   * @return poi的workbook对象
   * @see Writer.#newExcel(List, String, int)
   */
  @Override
  <T> Workbook newExcel(List<T> paramsList, String sheetName, int pageSize) {
    List<Field> fields = eraseField(paramsList);
    XSSFWorkbook hwRs = new XSSFWorkbook();
    List<Title> listTitle = newTitle(fields);
    int sheetCount = sheetCount(paramsList, pageSize);
    for (int sign = 1; sign <= sheetCount; sign++) {
      XSSFSheet hs = (XSSFSheet) newSheet(hwRs, sheetName, sign);
      writeTitle(hwRs, hs, listTitle);
      try {
        writeBody(hwRs, hs, paramsList, fields, sign, pageSize);
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
        continue;
      } catch (SecurityException e) {
        e.printStackTrace();
        continue;
      } catch (IllegalAccessException e) {
        e.printStackTrace();
        continue;
      } catch (InstantiationException e) {
        e.printStackTrace();
        continue;
      } catch (NoSuchMethodException e) {
        e.printStackTrace();
        continue;
      } catch (InvocationTargetException e) {
        e.printStackTrace();
        continue;
      }
    }
    return hwRs;
  }

  /**
   * 生成excel2007及其以后格式的Workbook类.可以生成多个sheet在同一个workbook里。
   * 
   * @param paramsMap 数据对象集合，key为sheet的名称value为list集合
   * @param pageSize 每页生成数据的数量
   * @return poi的workbook对象
   * @see Writer#newExcel(Map, int)
   */
  @Override
  <T> Workbook newExcel(Map<String, List<T>> paramsMap, int pageSize) {
    XSSFWorkbook hwRs = new XSSFWorkbook();
    for (Entry<String, List<T>> entry : paramsMap.entrySet()) {
      List<T> paramsList = entry.getValue();
      String sheetName = entry.getKey();
      List<Field> fields = eraseField(paramsList);
      List<Title> listTitle = newTitle(fields);
      int sheetCount = sheetCount(paramsList, pageSize);
      for (int sign = 1; sign <= sheetCount; sign++) {
        XSSFSheet hs = (XSSFSheet) newSheet(hwRs, sheetName, sign);
        writeTitle(hwRs, hs, listTitle);
        try {
          writeBody(hwRs, hs, paramsList, fields, sign, pageSize);
        } catch (IllegalArgumentException ex) {
          ex.printStackTrace();
          continue;
        } catch (InvocationTargetException ex) {
          ex.printStackTrace();
          continue;
        } catch (InstantiationException ex) {
          ex.printStackTrace();
          continue;
        } catch (IllegalAccessException ex) {
          ex.printStackTrace();
          continue;
        } catch (NoSuchMethodException ex) {
          ex.printStackTrace();
          continue;
        } catch (SecurityException ex) {
          ex.printStackTrace();
          continue;
        }
      }
    }
    return hwRs;
  }
}
