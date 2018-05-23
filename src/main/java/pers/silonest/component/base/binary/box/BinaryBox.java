package pers.silonest.component.base.binary.box;

/**
 * 二进制数据盒子.二进制数据盒子是可以用来包装二进制数组的一个工具，从字节数组中读取指定长度的内容，并返回易于使用的转换器。
 * 
 * @author 陈晨
 *
 */
public interface BinaryBox {
  public byte[] readByteArray(int index, int length);
}
