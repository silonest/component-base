package pers.silonest.component.base.binary.box;

import pers.silonest.component.base.binary.ByteConvert;

public abstract class ByteBox implements BinaryBox {
  private byte[] binary;

  void setBinary(byte[] binary) {
    this.binary = binary;
  }

  byte[] getBinary() {
    return this.binary;
  }

  /**
   * 从index的位置开始读取length长度的字节数组。
   * 
   * @param index 开始位置，从1开始
   * @param length 读取长度
   * @return
   */
  @Override
  public byte[] readByteArray(int index, int length) {
    index = index - 1;
    if (length <= 0 || index < 0) {
      throw new ArithmeticException("Non-positive length");
    }
    if (length > (this.binary.length - index)) {
      length = this.binary.length - index;
    }
    byte[] result = new byte[length];
    System.arraycopy(this.binary, index, result, 0, length);
    return result;
  }

  public ByteConvert read(int index, int length) {
    byte[] result = readByteArray(index, length);
    return new ByteConvert(result);
  }

  public ByteConvert readAll() {
    return read(1, this.binary.length);
  }

  public ByteConvert read2Byte() {
    return read2Byte(1);
  }

  public ByteConvert read2Byte(int index) {
    return read(index, 2);
  }

  public ByteConvert read4Byte() {
    return read4Byte(1);
  }

  public ByteConvert read4Byte(int index) {
    return read(index, 4);
  }

  public ByteConvert read8Byte() {
    return read8Byte(1);
  }

  public ByteConvert read8Byte(int index) {
    return read(index, 8);
  }

  public ByteConvert read16Byte() {
    return read16Byte(1);
  }

  public ByteConvert read16Byte(int index) {
    return read(index, 16);
  }

  public int getLength() {
    if (this.binary == null) {
      return 0;
    } else {
      return this.binary.length;
    }
  }
}
