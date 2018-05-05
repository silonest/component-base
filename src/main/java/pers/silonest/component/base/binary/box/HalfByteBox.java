package pers.silonest.component.base.binary.box;

import pers.silonest.component.base.binary.ByteConvert;
import pers.silonest.component.util.NumberUtils;

public class HalfByteBox extends ByteBox {

  public HalfByteBox(byte[] binary) {
    if (binary == null) {
      super.setBinary(null);
    } else {
      byte[] temp = new byte[binary.length * 2];
      for (int i = 0; i < binary.length; i++) {
        byte b = binary[i];
        temp[i * 2] = (byte) (b & 0xf0);
        temp[(i * 2) + 1] = (byte) (b & 0x0f);
      }
      super.setBinary(temp);
    }
  }

  @Override
  public byte[] readByteArray(int index, int length) {
    index = index - 1;
    if (length <= 0 || index < 0) {
      throw new ArithmeticException("Non-positive length");
    }
    if (length > (this.getBinary().length - index)) {
      length = this.getBinary().length - index;
    }
    byte[] binary = super.getBinary();
    byte[] temp = new byte[NumberUtils.isEvenNumber(length) ? length : length + 1];
    System.arraycopy(binary, index, temp, 0, length);
    byte[] result = new byte[temp.length / 2];
    for (int i = 0; i < temp.length; i += 2) {
      byte hbyte = NumberUtils.isEvenNumber(index) ? temp[i] : (byte) ((temp[i] & 0x0f) << 4);
      byte lbyte = NumberUtils.isEvenNumber(index) ? temp[i + 1] : (byte) ((temp[i + 1] & 0xf0) >>> 4);
      result[i / 2] = (byte) (hbyte | lbyte);
    }
    return result;
  }

  @Override
  public ByteConvert readAll() {
    byte[] result = readByteArray(1, this.getBinary().length);
    return new ByteConvert(result);
  }

  @Override
  public ByteConvert read(int index, int length) {
    byte[] result = readByteArray(index, length);
    return new ByteConvert(result);
  }

  @Override
  public ByteConvert read2Byte() {
    return read2Byte(1);
  }

  @Override
  public ByteConvert read2Byte(int index) {
    return read(index, 4);
  }

  @Override
  public ByteConvert read4Byte() {
    return read4Byte(1);
  }

  @Override
  public ByteConvert read4Byte(int index) {
    return read(index, 8);
  }

  @Override
  public ByteConvert read8Byte() {
    return read8Byte(1);
  }

  @Override
  public ByteConvert read8Byte(int index) {
    return read(index, 16);
  }

  @Override
  public ByteConvert read16Byte() {
    return read16Byte(1);
  }

  @Override
  public ByteConvert read16Byte(int index) {
    return read(index, 32);
  }

  @Override
  public int getLength() {
    if (this.getBinary() == null) {
      return 0;
    } else {
      return this.getBinary().length;
    }
  }
}
