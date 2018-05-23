package pers.silonest.component.base.binary;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ByteConvert implements DataConvert {
  private static final int SHORT_BYTE_LENGTH = 2;
  private static final int INT_BYTE_LENGTH = 4;
  private static final int LONG_BYTE_LENGTH = 8;
  private static final int FLOAT_BYTE_LENGTH = 4;
  private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
  private byte[] binary;

  public ByteConvert(byte[] binary) {
    this.binary = binary;
  }

  @Override
  public byte[] toByteArray() {
    return this.binary;
  }

  @Override
  public short toShort() {
    if (this.binary == null || this.binary.length == 0) {
      throw new NullPointerException();
    } else if (this.binary.length > SHORT_BYTE_LENGTH) {
      byte[] temp = new byte[SHORT_BYTE_LENGTH];
      System.arraycopy(this.binary, 0, temp, 0, SHORT_BYTE_LENGTH);
      ByteBuffer bb = ByteBuffer.allocate(SHORT_BYTE_LENGTH);
      bb.put(temp);
      bb.flip();
      return bb.getShort();
    } else {
      this.binary = processBinary(SHORT_BYTE_LENGTH);
      ByteBuffer bb = ByteBuffer.allocate(SHORT_BYTE_LENGTH);
      bb.put(this.binary);
      bb.flip();
      return bb.getShort();
    }
  }

  @Override
  public int toInt() {
    if (this.binary == null || this.binary.length == 0) {
      throw new NullPointerException();
    } else if (this.binary.length > INT_BYTE_LENGTH) {
      byte[] temp = new byte[INT_BYTE_LENGTH];
      System.arraycopy(this.binary, 0, temp, 0, INT_BYTE_LENGTH);
      ByteBuffer bb = ByteBuffer.allocate(INT_BYTE_LENGTH);
      bb.put(temp);
      bb.flip();
      return bb.getInt();
    } else {
      this.binary = processBinary(INT_BYTE_LENGTH);
      ByteBuffer bb = ByteBuffer.allocate(INT_BYTE_LENGTH);
      bb.put(this.binary);
      bb.flip();
      return bb.getInt();
    }
  }

  public int toInt(String format) {
    adjustByteOrder(this.binary.length, format);
    return toInt();
  }

  @Override
  public long toLong() {
    if (this.binary == null || this.binary.length == 0) {
      throw new NullPointerException();
    } else if (this.binary.length > LONG_BYTE_LENGTH) {
      byte[] temp = new byte[LONG_BYTE_LENGTH];
      System.arraycopy(this.binary, 0, temp, 0, LONG_BYTE_LENGTH);
      ByteBuffer bb = ByteBuffer.allocate(LONG_BYTE_LENGTH);
      bb.put(temp);
      bb.flip();
      return bb.getLong();
    } else {
      this.binary = processBinary(LONG_BYTE_LENGTH);
      ByteBuffer bb = ByteBuffer.allocate(LONG_BYTE_LENGTH);
      bb.put(this.binary);
      bb.flip();
      return bb.getLong();
    }
  }

  public long toLong(String format) {
    adjustByteOrder(this.binary.length, format);
    return toLong();
  }

  @Override
  public float toFloat() {
    if (this.binary == null || this.binary.length == 0) {
      throw new NullPointerException();
    } else if (this.binary.length > FLOAT_BYTE_LENGTH) {
      byte[] temp = new byte[FLOAT_BYTE_LENGTH];
      System.arraycopy(this.binary, 0, temp, 0, FLOAT_BYTE_LENGTH);
      ByteBuffer bb = ByteBuffer.wrap(temp);
      bb.order(ByteOrder.LITTLE_ENDIAN);
      return bb.getFloat();
    } else {
      ByteBuffer bb = ByteBuffer.wrap(this.binary);
      bb.order(ByteOrder.LITTLE_ENDIAN);
      return bb.getFloat();
    }
  }

  public float toFloat(String format) {
    adjustByteOrder(FLOAT_BYTE_LENGTH, format);
    return toFloat();
  }

  @Override
  public String toHex() {
    if (this.binary == null || this.binary.length == 0) {
      throw new NullPointerException();
    } else {
      char[] buf = new char[this.binary.length * 2];
      int a = 0;
      int index = 0;
      for (byte b : this.binary) {
        if (b < 0) {
          a = 256 + b;
        } else {
          a = b;
        }
        buf[index++] = HEX_CHAR[a / 16];
        buf[index++] = HEX_CHAR[a % 16];
      }
      return new String(buf);
    }
  }

  public String toHex(String format) {
    adjustByteOrder(this.binary.length, format);
    return toHex();
  }

  @Override
  public String toASCII() {
    if (this.binary == null || this.binary.length == 0) {
      throw new NullPointerException();
    } else {
      try {
        return new String(this.binary, "ISO8859-1");
      } catch (UnsupportedEncodingException e) {
        return null;
      }
    }
  }

  public String toASCII(String format) {
    adjustByteOrder(this.binary.length, format);
    return toASCII();
  }

  private byte[] processBinary(int size) {
    if (this.binary.length < size) {
      byte[] result = new byte[size];
      for (int i = 0; i < this.binary.length; i++) {
        result[size - this.binary.length + i] = this.binary[i];
      }
      return result;
    } else {
      return this.binary;
    }
  }

  private void adjustByteOrder(int size, String format) {
    try {
      String[] formatArry = format.split("-");
      byte[] temp = new byte[size];
      for (int index = 0; index < size; index++) {
        temp[index] = this.binary[Integer.valueOf(formatArry[index].replaceAll("C", "").replaceAll("c", "")) - 1];
      }
      this.binary = temp;
    } catch (Throwable ex) {
      throw new TypeTransferFormatException("Parameter is error!");
    }
  }

  public static byte[] short2ByteArray(short value) {
    byte[] result = new byte[2];
    result[0] = (byte) ((value >> 8) & 0xFF);
    result[1] = (byte) (value & 0xFF);
    return result;
  }

  public static byte[] int2ByteArray(int value) {
    byte[] result = new byte[4];
    // 由高位到低位
    result[0] = (byte) ((value >> 24) & 0xFF);
    result[1] = (byte) ((value >> 16) & 0xFF);
    result[2] = (byte) ((value >> 8) & 0xFF);
    result[3] = (byte) (value & 0xFF);
    return result;
  }

  public static byte[] long2ByteArray(long value) {
    byte[] result = new byte[8];
    // 由高位到低位
    result[0] = (byte) ((value >> 56) & 0xFF);
    result[1] = (byte) ((value >> 48) & 0xFF);
    result[2] = (byte) ((value >> 40) & 0xFF);
    result[3] = (byte) ((value >> 32) & 0xFF);
    result[4] = (byte) ((value >> 24) & 0xFF);
    result[5] = (byte) ((value >> 16) & 0xFF);
    result[6] = (byte) ((value >> 8) & 0xFF);
    result[7] = (byte) (value & 0xFF);
    return result;
  }

  public static byte[] float2ByteArray(float value) {
    // 把float转换为byte[]
    int fbit = Float.floatToIntBits(value);
    byte[] b = new byte[FLOAT_BYTE_LENGTH];
    for (int i = 0; i < FLOAT_BYTE_LENGTH; i++) {
      b[i] = (byte) (fbit >> (24 - i * 8));
    }
    // 翻转数组
    int len = b.length;
    // 建立一个与源数组元素类型相同的数组
    byte[] dest = new byte[FLOAT_BYTE_LENGTH];
    // 为了防止修改源数组，将源数组拷贝一份副本
    System.arraycopy(b, 0, dest, 0, len);
    byte temp;
    // 将顺位第i个与倒数第i个交换
    for (int i = 0; i < len / 2; ++i) {
      temp = dest[i];
      dest[i] = dest[len - i - 1];
      dest[len - i - 1] = temp;
    }
    return dest;
  }

  public static byte[] hex2ByteArray(String hex) {
    String temp = hex.replaceAll(" ", "").toLowerCase();
    byte[] b = new byte[temp.length() / 2];
    for (int i = 0; i < b.length; i++) {
      int index = i * 2;
      int v = Integer.parseInt(temp.substring(index, index + 2), 16);
      b[i] = (byte) v;
    }
    return b;
  }
}
