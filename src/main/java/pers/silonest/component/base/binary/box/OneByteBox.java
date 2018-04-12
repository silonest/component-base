package pers.silonest.component.base.binary.box;

import pers.silonest.component.base.binary.ByteConvert;

public class OneByteBox extends ByteBox {
  public OneByteBox(byte[] binary) {
    super.setBinary(binary);
  }

  @Override
  public byte[] readByteArray(int index, int length) {
    return super.readByteArray(index, length);
  }

  @Override
  public ByteConvert read(int index, int length) {
    return super.read(index, length);
  }

  @Override
  public ByteConvert read2Byte(int index) {
    return super.read2Byte(index);
  }

  @Override
  public ByteConvert read4Byte(int index) {
    return super.read4Byte(index);
  }

  @Override
  public ByteConvert read8Byte(int index) {
    return super.read8Byte(index);
  }

  @Override
  public ByteConvert read16Byte(int index) {
    return super.read16Byte(index);
  }
}
