package pers.silonest.component.base.binary;

import pers.silonest.component.util.NumberUtils;

public class HalfByteBox extends ByteBox {

  public HalfByteBox(byte[] binary) {
    this.binary = new byte[binary.length * 2];
    for (int i = 0; i < binary.length; i++) {
      byte b = binary[i];
      this.binary[i * 2] = (byte) (b & 0xf0);
      this.binary[(i * 2) + 1] = (byte) (b & 0x0f);
    }
  }

  // public HalfByteBox(String str, Charset charset) {
  // if (StringUtils.isNotBlank(str)) {
  // new HalfByteBox(strClean(str).getBytes(charset));
  // } else {
  // throw new NullPointerException();
  // }
  // }

  // public HalfByteBox(String str) {
  // if (StringUtils.isNotBlank(str)) {
  // new HalfByteBox(strClean(str), CharsetUtils.DEFAULT_CHARSET);
  // } else {
  // throw new NullPointerException();
  // }
  // }

  @Override
  public String readHex(int index, int length) {
    return readHex(index, length);
  }

  @Override
  public byte[] readBytes(int index, int length) {
    if (length <= 0) {
      throw new ArithmeticException("Non-positive length");
    } else {
      byte[] temp = new byte[NumberUtils.isEvenNumber(length) ? length : length + 1];
      System.arraycopy(this.binary, index, temp, 0, length);
      byte[] result = new byte[temp.length / 2];
      for (int i = 0; i < temp.length; i += 2) {
        byte hbyte = NumberUtils.isEvenNumber(index) ? temp[i] : (byte) (temp[i] << 4);
        byte lbyte = NumberUtils.isEvenNumber(index) ? temp[i + 1] : (byte) (temp[i + 1] >>> 4);
        result[i / 2] = (byte) (hbyte | lbyte);
      }
      return result;
    }
  }

  @Override
  public ByteTransfer readByteTransfer(int index, int length) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ByteTransfer read2b(int index) {
    // TODO Auto-generated method stub
    return super.read2b(index);
  }
}
