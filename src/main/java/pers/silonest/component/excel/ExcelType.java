package pers.silonest.component.excel;

/**
 * 支持的excel类型.2007以后版本是使用xml的形式存储，所以在灵活性和数据量上有优势。推荐使用2007及后续版本。
 * 
 * @author 陈晨
 * @time 2017年12月6日 上午9:27:27
 * @since v1.0.0
 * @version v0.0.1
 */
public enum ExcelType {
  XLS_2003, XLSX_2007_AND_LATER
}
