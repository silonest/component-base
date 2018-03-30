package pers.silonest.component.base.binary;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

public class ByteTransfer implements TypeTransfer {
  private static final int SHROT_BYTE_LENGTH = 2;
  private static final int INT_BYTE_LENGTH = 4;
  // private static final int LONG_BYTE_LENGTH = 8;

  private byte[] binary;

  public ByteTransfer(byte[] binary) {
    this.binary = binary;
  }

  public byte[] getBinary() {
    return this.binary;
  }

  @Override
  public short toShort() {
    if (this.binary == null || this.binary.length == 0) {
      throw new NullPointerException();
    } else if (this.binary.length > SHROT_BYTE_LENGTH) {
      byte[] temp = new byte[SHROT_BYTE_LENGTH];
      System.arraycopy(this.binary, 0, temp, 0, SHROT_BYTE_LENGTH);
      ByteBuffer bb = ByteBuffer.wrap(temp);
      bb.order(ByteOrder.nativeOrder());
      ShortBuffer sb = bb.asShortBuffer();
      return sb.get();
    } else {
      ByteBuffer bb = ByteBuffer.wrap(this.binary);
      bb.order(ByteOrder.nativeOrder());
      ShortBuffer sb = bb.asShortBuffer();
      return sb.get();
    }
  }

  @Override
  public int toInt() {
    if (this.binary == null || this.binary.length == 0) {
      throw new NullPointerException();
    } else if (this.binary.length > INT_BYTE_LENGTH) {
      byte[] temp = new byte[INT_BYTE_LENGTH];
      System.arraycopy(this.binary, 0, temp, 0, INT_BYTE_LENGTH);
      ByteBuffer bb = ByteBuffer.wrap(temp);
      bb.order(ByteOrder.nativeOrder());
      IntBuffer fb = bb.asIntBuffer();
      return fb.get();
    } else {
      ByteBuffer bb = ByteBuffer.wrap(this.binary);
      bb.order(ByteOrder.nativeOrder());
      IntBuffer ib = bb.asIntBuffer();
      return ib.get();
    }
  }

  @Override
  public long toLong() {
    if (this.binary == null || this.binary.length == 0) {
      throw new NullPointerException();
    } else if (this.binary.length > INT_BYTE_LENGTH) {
      byte[] temp = new byte[INT_BYTE_LENGTH];
      System.arraycopy(this.binary, 0, temp, 0, INT_BYTE_LENGTH);
      ByteBuffer bb = ByteBuffer.wrap(temp);
      bb.order(ByteOrder.nativeOrder());
      IntBuffer fb = bb.asIntBuffer();
      return fb.get();
    } else {
      ByteBuffer bb = ByteBuffer.wrap(this.binary);
      bb.order(ByteOrder.nativeOrder());
      LongBuffer lb = bb.asLongBuffer();
      return lb.get();
    }
  }

  public static String byte2Hex(byte[] data) {
    StringBuilder hs = new StringBuilder(data.length);
    for (byte b : data) {
      String stmp = Integer.toHexString(b & 0xFF);
      if (stmp.length() == 1)
        hs = hs.append("0").append(stmp);
      else {
        hs = hs.append(stmp);
      }
      hs.append(" ");
    }
    hs.deleteCharAt(hs.length() - 1);
    return String.valueOf(hs);
  }
}
