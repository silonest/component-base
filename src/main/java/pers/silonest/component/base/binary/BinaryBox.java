package pers.silonest.component.base.binary;

/**
 * 二进制数据盒子.二进制数据盒子是可以用来包装二进制数组的一个工具，在实例化
 * 
 * @author 陈晨
 *
 */
public interface BinaryBox {
  public byte[] readBytes(int index, int length);

  /**
   * 从index位置（包含index）开始向后取length长的byte.
   * 
   * @param index 从1开始。
   * @param length
   * @return
   */
  public TypeTransfer readByteTransfer(int index, int length);
}
