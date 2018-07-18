package pers.silonest.component.base.binary;

/**
 * 数据类型转换器.
 * 
 * @author 陈晨
 *
 */
public interface DataConvert {

  public byte[] toByteArray();

  public short toShort();

  public int toInt();

  public long toLong();

  public float toFloat();

  public String toHex();

  public String toASCII();

}
