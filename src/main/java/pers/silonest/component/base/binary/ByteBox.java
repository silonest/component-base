package pers.silonest.component.base.binary;

public abstract class ByteBox implements BinaryBox {
  byte[] binary;

  @Override
  public byte[] readBytes(int index, int length) {
    if (length <= 0) {
      throw new ArithmeticException("Non-positive length");
    } else {
      byte[] result = new byte[length];
      System.arraycopy(this.binary, index, result, 0, length);
      return result;
    }
  }

  @Override
  public String readHex(int index, int length) {
    return null;
  }

  @Override
  public ByteTransfer readByteTransfer(int index, int length) {
    byte[] result = readBytes(index, length);
    return new ByteTransfer(result);
  }

  public ByteTransfer read2b(int index) {
    return readByteTransfer(index, 2);
  }
  //
  // public ByteTransfer read2b() {
  // return read(0, 2);
  // }
  //
  // public ByteTransfer read4b(int index) {
  // return read(index, 4);
  // }
  //
  // public ByteTransfer read4b() {
  // return read(0, 4);
  // }
  //
  // public ByteTransfer read8b(int index) {
  // return read(index, 8);
  // }
  //
  // public ByteTransfer read8b() {
  // return read(0, 8);
  // }
  //
  // public ByteTransfer read16b(int index) {
  // return read(index, 16);
  // }
  //
  // public ByteTransfer read16b() {
  // return read(0, 16);
  // }

  // public static int byteArray2Int(byte[] binary) {
  // ByteBuffer bb = ByteBuffer.wrap(binary);
  // bb.order(ByteOrder.nativeOrder());
  // IntBuffer fb = bb.asIntBuffer();
  // return fb.get();
  // }

  String strClean(String str) {
    str.trim(); // 去掉所有首尾空格；
    str.replace(" ", ""); // 去掉所有空格；
    str.replaceAll(" ", ""); // 去掉所有空格；
    str.replaceAll(" +", ""); // 去掉所有空格；
    str.replaceAll("\\s*", ""); // 替换所有空白字符，不限于空格；
    str.replaceAll("\r|\n|\t", ""); // 替换空格、回车、换行符、制表符
    str.replaceAll("\\s*", ""); // * 表示零个或多个
    str.replaceAll("\\s+", ""); // + 表示一个或多个
    return str;
  }

}
