package pers.silonest.component.base.binary;

public class HzHexBox extends HexBox {

  public HzHexBox(byte[] binary) {
    super(binary);
    this.hexStr.replaceAll("A", "-");
  }

  @Override
  public byte[] readBytes(int index, int length) {
    return null;
  }

  @Override
  public String readHex(int index, int length) {
    return this.hexStr.substring(index, index + length);
  }

  @Override
  public HexTransfer readByteTransfer(int index, int length) {
    return null;
  }


}
