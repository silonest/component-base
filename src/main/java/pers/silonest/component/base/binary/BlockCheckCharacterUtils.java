package pers.silonest.component.base.binary;

public class BlockCheckCharacterUtils {
  public static byte getBcc(byte[] bts) {
    return getBcc(bts, 0, bts.length);
  }

  /**
   * 异或校验
   * 
   * @param bts
   * @param startindex
   * @param len
   * @return
   */
  public static byte getBcc(byte[] bts, int startindex, int len) {
    byte re = bts[startindex];
    for (int i = startindex; i < startindex + len - 1; i++) {
      re = (byte) (re ^ bts[i + 1]);
    }
    return re;
  }
}
