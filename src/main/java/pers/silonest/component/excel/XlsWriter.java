package pers.silonest.component.excel;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import pers.silonest.component.excel.annotation.ExcelInstruction;
import pers.silonest.component.excel.pojo.Title;

/**
 * 生成xls格式的excel(excel2003的格式).
 * 
 * @author 陈晨
 * @version v0.0.1
 * @since v1.0.0
 */
class XlsWriter extends Writer {
  /**
   * 生成excel2003格式的Workbook类.
   * 
   * @param paramsList 数据对象集合
   * @param sheetName sheet名称
   * @param pageSize 每页的数量
   * @return poi的workbook对象
   * @see Writer#newExcel(List, String, int)
   */
  @Override
  <T> Workbook newExcel(List<T> paramsList, String sheetName, int pageSize) {
    List<Field> fields = eraseField(paramsList);
    HSSFWorkbook hwRs = new HSSFWorkbook();
    List<Title> listTitle = newTitle(fields);
    Collections.sort(listTitle, new Comparator<Title>() {
      public int compare(Title o1, Title o2) {
        Title title1 = o1;
        Title title2 = o2;
        return Integer.valueOf(title1.getOrder()).compareTo(Integer.valueOf(title2.getOrder()));
      }
    });
    Collections.sort(fields, new Comparator<Field>() {
      public int compare(Field o1, Field o2) {
        Field field1 = o1;
        ExcelInstruction inst1 = field1.getAnnotation(ExcelInstruction.class);
        Field field2 = o2;
        ExcelInstruction inst2 = field2.getAnnotation(ExcelInstruction.class);
        return Integer.valueOf(inst1.order()).compareTo(Integer.valueOf(inst2.order()));
      }
    });
    // 按照传入的每张Sheet存入的记录条数（默认65534）判断需要生成几张Sheet
    int sheetCount = sheetCount(paramsList, pageSize);
    for (int sign = 1; sign <= sheetCount; sign++) {
      HSSFSheet hs = (HSSFSheet) newSheet(hwRs, sheetName, sign);// 生成sheet
      writeTitle(hwRs, hs, listTitle);

      try {
        writeBody(hwRs, hs, paramsList, fields, sign, pageSize);
      } catch (IllegalAccessException e) {
        continue;
      } catch (InstantiationException e) {
        continue;
      } catch (NoSuchMethodException e) {
        continue;
      } catch (InvocationTargetException e) {
        continue;
      }

    }
    return hwRs;
  }

  /**
   * 生成excel2003格式的Workbook类.可以生成多个sheet在同一个workbook里。
   * 
   * @param paramsMap 数据对象集合，key为sheet的名称value为list集合
   * @param pageSize 每页生成数据的数量
   * @return poi的workbook对象
   * @see Writer.#newExcel(Map, int)
   */
  @Override
  <T> Workbook newExcel(Map<String, List<T>> paramsMap, int pageSize) {
    HSSFWorkbook hwRs = new HSSFWorkbook();// 生成工作簿
    for (Entry<String, List<T>> entry : paramsMap.entrySet()) {
      List<T> paramsList = entry.getValue();
      String sheetName = entry.getKey();
      List<Field> fields = eraseField(paramsList);
      List<Title> listTitle = newTitle(fields);// 生成title
      Collections.sort(listTitle, new Comparator<Title>() {
        public int compare(Title o1, Title o2) {
          Title title1 = o1;
          Title title2 = o2;
          return new Integer(title1.getOrder()).compareTo(Integer.valueOf(title2.getOrder()));
        }
      });
      Collections.sort(fields, new Comparator<Field>() {
        public int compare(Field o1, Field o2) {
          Field field1 = o1;
          ExcelInstruction inst1 = field1.getAnnotation(ExcelInstruction.class);
          Field field2 = o2;
          ExcelInstruction inst2 = field2.getAnnotation(ExcelInstruction.class);
          return Integer.valueOf(inst1.order()).compareTo(Integer.valueOf(inst2.order()));
        }
      });
      int sheetCount = sheetCount(paramsList, pageSize);// 计算总共需要的sheet
      for (int sign = 1; sign <= sheetCount; sign++) {
        HSSFSheet hs = (HSSFSheet) newSheet(hwRs, sheetName, sign);// 生成sheet
        writeTitle(hwRs, hs, listTitle);// 向sheet中写入title
        try {
          writeBody(hwRs, hs, paramsList, fields, sign, pageSize);// 向title中写入body
        } catch (IllegalAccessException e) {
          continue;
        } catch (InstantiationException e) {
          continue;
        } catch (NoSuchMethodException e) {
          continue;
        } catch (InvocationTargetException e) {
          continue;
        }
      }
    }
    return hwRs;
  }
}
