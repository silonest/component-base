package pers.silonest.component.base.binary;

/**
 * 二进制数据盒子.二进制数据盒子是可以用来包装二进制数组的一个工具，在实例化
 * @author 陈晨
 *
 */
public interface BinaryBox {
  public String readHex(int index, int length);

  public byte[] readBytes(int index, int length);

  public TypeTransfer readByteTransfer(int index, int length);
}
