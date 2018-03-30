package pers.silonest.component.base.binary;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.charset.Charset;
import org.apache.commons.lang3.StringUtils;
import pers.silonest.component.base.charset.CharsetUtils;

/**
 * ByteBox.
 *
 * @author silonest
 * @version v0.0.1
 * @email silonest@icloud.com
 * @time 2018年01月15日 下午4:06
 * @since v1.0.0
 */
public class ByteBox {

  private byte[] binary;

  public ByteBox(byte[] binary) {
    this.binary = binary;
  }

  public ByteBox(String str, Charset charset) {
    if (StringUtils.isNotBlank(str)) {
      new ByteBox(strClean(str).getBytes(charset));
    } else {
      throw new NullPointerException();
    }
  }

  public ByteBox(String str) {
    if (StringUtils.isNotBlank(str)) {
      new ByteBox(strClean(str), CharsetUtils.DEFALUT_CHARSET);
    } else {
      throw new NullPointerException();
    }
  }

  public ByteTransfer read(int index, int length) {
    if (length == 0) {
      return null;
    } else {
      byte[] result = new byte[length];
      System.arraycopy(this.binary, index, result, 0, length);
      return new ByteTransfer(result);
    }
  }

  public ByteTransfer read2b(int index) {
    return read(index, 2);
  }

  public ByteTransfer read2b() {
    return read(0, 2);
  }

  public ByteTransfer read4b(int index) {
    return read(index, 4);
  }

  public ByteTransfer read4b() {
    return read(0, 4);
  }

  public ByteTransfer read8b(int index) {
    return read(index, 8);
  }

  public ByteTransfer read8b() {
    return read(0, 8);
  }

  public ByteTransfer read16b(int index) {
    return read(index, 16);
  }

  public ByteTransfer read16b() {
    return read(0, 16);
  }

  public static int byteArray2Int(byte[] binary) {
    ByteBuffer bb = ByteBuffer.wrap(binary);
    bb.order(ByteOrder.nativeOrder());
    IntBuffer fb = bb.asIntBuffer();
    return fb.get();
  }

  private String strClean(String str) {
    str.trim(); //去掉所有首尾空格；
    str.replace(" ", ""); //去掉所有空格；
    str.replaceAll(" ", ""); //去掉所有空格；
    str.replaceAll(" +", ""); //去掉所有空格；
    str.replaceAll("\\s*", ""); //替换所有空白字符，不限于空格；
    str.replaceAll("\r|\n|\t", ""); //替换空格、回车、换行符、制表符
    str.replaceAll("\\s*", ""); // * 表示零个或多个
    str.replaceAll("\\s+", ""); // + 表示一个或多个
    return str;
  }
}
