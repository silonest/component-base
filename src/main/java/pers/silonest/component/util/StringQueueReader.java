package pers.silonest.component.util;

/**
 * 该类可以帮助调用者便捷的读取字符串.
 * 
 * @author 陈晨
 * @time 2018年6月7日 下午3:46:07
 * @since v1.0.0
 * @version v0.0.1
 */
public class StringQueueReader {
  private String str;

  public StringQueueReader(String str) {
    this.str = str;
  }

  public String readOne() {
    if (str.length() > 1) {
      String result = str.substring(0, 1);
      str = str.substring(1, str.length());
      return result;
    } else {
      return str;
    }
  }
}
