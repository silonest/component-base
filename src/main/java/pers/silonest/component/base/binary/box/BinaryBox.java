package pers.silonest.component.base.binary.box;

/**
 * 二进制数据盒子.二进制数据盒子是可以用来包装二进制数组的一个工具，从字节数组中读取指定长度的内容，并返回易于使用的转换器。
 * 
 * @author 陈晨
 *
 */
public interface BinaryBox {
  // /**
  // * 从index位置（包含index）开始向后取length长的byte.
  // *
  // * @param index 当前位置（从1开始）
  // * @param length 读取长度
  // * @return 类型转换器
  // */
  // public DataConvert read(int index, int length);

  public byte[] readByteArray(int index, int length);
}
