package pers.silonest.component.base.binary;

public class HexConvert implements DataConvert {

  private String hexStr = null;

  public HexConvert(String hex) {
    this.hexStr = hex.toLowerCase().replaceAll(" ", "").trim();
    int length = hexStr.length();
    if ((length & 1) == 1) {// 奇数
      this.hexStr = hexStr.substring(0, length - 1);
    }
  }

  @Override
  public byte[] toByteArray() {
    return ByteConvert.hex2ByteArray(hexStr);
  }

  @Override
  public short toShort() {
    short result = Short.parseShort(this.hexStr, 16);
    return result;
  }

  @Override
  public int toInt() {
    int result = Integer.parseInt(this.hexStr, 16);
    return result;
  }

  @Override
  public long toLong() {
    long result = Long.parseLong(this.hexStr, 16);
    return result;
  }

  @Override
  public float toFloat() {
    Float value = Float.intBitsToFloat(Integer.valueOf(this.hexStr, 16));
    return value;
  }

  @Override
  public String toHex() {
    return hexStr;
  }

  @Override
  public String toASCII() {
    byte[] data = ByteConvert.hex2ByteArray(this.hexStr);
    ByteConvert bc = new ByteConvert(data);
    return bc.toASCII();
  }

  public static String short2Hex(short value) {
    String hex = Integer.toHexString(((int) value & 0xffff));
    return hex;
  }

  public static String int2Hex(int value) {
    String hex = Integer.toHexString(value);
    return hex;
  }

  public static String long2Hex(long value) {
    String hex = Long.toHexString(value);
    return hex;
  }

  public static String float2Hex(float value) {
    String hex = Integer.toHexString(Float.floatToIntBits(value));
    return hex;
  }

}
