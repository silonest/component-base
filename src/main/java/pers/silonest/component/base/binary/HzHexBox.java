package pers.silonest.component.base.binary;

public class HzHexBox extends HexBox {

  public HzHexBox(byte[] binary) {
    super(binary);
    this.hexStr = this.hexStr.replaceAll("A", "-");
  }

  @Override
  public byte[] readBytes(int index, int length) {
    return null;
  }

  @Override
  public HexTransfer readByteTransfer(int index, int length) {
    return null;
  }


}
