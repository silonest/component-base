package pers.silonest.component.base.binary;

public abstract class CheckSumUtils {
  /**
   * 获取累加和校验.
   * 
   * @param data 用来计算累加和的hex字符串
   * @return data对应的累计和
   */
  public static String getCs(String data) {
    if (data == null || data.equals("")) {
      return "";
    }
    int total = 0;
    int len = data.length();
    int num = 0;
    while (num < len) {
      String s = data.substring(num, num + 2);
      total += Integer.parseInt(s, 16);
      num = num + 2;
    }
    int mod = total % 256;
    String hex = Integer.toHexString(mod);
    len = hex.length();
    // 如果不够校验位的长度，补0,这里用的是两位校验
    if (len < 2) {
      hex = "0" + hex;
    }
    return hex;
  }

  public static byte getCs(byte[] bts) {
    return getCs(bts, 0, bts.length);
  }

  /**
   * 累积和校验
   * 
   * @param bts 校验数组
   * @param startindex 校验开始索引
   * @param len 校验从开始索引的字节长度
   * @return
   */
  public static byte getCs(byte[] bts, int startindex, int len) {
    byte re = 0;
    for (int i = startindex; i < startindex + len; i++) {
      re += bts[i];
    }
    return re;
  }
}
