package pers.silonest.component.base.binary;

public class HexTransfer implements TypeTransfer {

  public String hexStr = null;

  public HexTransfer(String hex) {
    this.hexStr = hex;
  }

  @Override
  public short toShort() {
    short result = Short.valueOf(this.hexStr).shortValue();
    return result;
  }

  public int toInt() {
    int result = Integer.valueOf(this.hexStr).intValue();
    return result;
  }

  @Override
  public long toLong() {
    long result = Long.valueOf(this.hexStr).longValue();
    return result;
  }

  private static byte charToByte(char c) {
    return (byte) "0123456789ABCDEF".indexOf(c);
  }

  public static byte[] hex2Byte(String hexString) {
    if (hexString == null || hexString.equals("")) {
      return null;
    }
    hexString = hexString.toUpperCase();
    int length = hexString.length() / 2;
    char[] hexChars = hexString.toCharArray();
    byte[] d = new byte[length];
    for (int i = 0; i < length; i++) {
      int pos = i * 2;
      d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
    }
    return d;
  }

  public static String byte2Hex(byte[] data) {
    StringBuilder hs = new StringBuilder(data.length);
    for (byte b : data) {
      String stmp = Integer.toHexString(b & 0xFF);
      if (stmp.length() == 1)
        hs = hs.append("0").append(stmp);
      else {
        hs = hs.append(stmp);
      }
      hs.append(" ");
    }
    hs.deleteCharAt(hs.length() - 1);
    return String.valueOf(hs);
  }
}
