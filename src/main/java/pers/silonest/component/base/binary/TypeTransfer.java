package pers.silonest.component.base.binary;

/**
 * 类型转换器.将Hex字符串或者byte数组转换成对应的数据格式。
 * 
 * @author 陈晨
 *
 */
public interface TypeTransfer {
  public short toShort();

  public int toInt();

  public long toLong();

  public float toFloat();

  public String toHex();
}
