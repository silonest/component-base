package pers.silonest.component.base.binary;

public class HexBox implements BinaryBox {
  String hexStr;

  public HexBox(byte[] binary) {
    StringBuilder sb = new StringBuilder();
    for (byte b : binary) {
      String hex = Integer.toHexString(b & 0xff);
      if (hex.length() == 1) {
        hex = '0' + hex;
      }
      sb.append(hex.toUpperCase());
    }
    this.hexStr = sb.toString();
  }

  @Override
  public byte[] readBytes(int index, int length) {
    return null;
  }

  private String readHex(int index, int length) {
    return hexStr.substring(index, index + length);
  }

  @Override
  public HexTransfer readByteTransfer(int index, int length) {
    String str = readHex(index, length);
    HexTransfer result = new HexTransfer(str);
    return result;
  }

}
