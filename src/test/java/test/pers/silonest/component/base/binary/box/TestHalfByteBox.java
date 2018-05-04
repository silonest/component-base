package test.pers.silonest.component.base.binary.box;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pers.silonest.component.base.binary.ByteConvert;
import pers.silonest.component.base.binary.box.ByteBox;
import pers.silonest.component.base.binary.box.HalfByteBox;

public class TestHalfByteBox {
  private static final String MODBUS_FRAME_1 = "01 03 04 06 51 3F 9E 0E 32";
  private static final String MODBUS_FRAME_2 = "50 03 6c" + " 00 ce 42 96" // 4 瞬时流量
      + " 00 00 00 00" // 8 瞬时热流量
      + " e7 d7 3f 96" // 12 流体速度
      + " 73 61 44 b6" // 16 测量流体声速
      + " 5c d3 00 03" // 20 正累积流量
      + " cb 61 3e 81" // 24 正累积流量小数部分
      + " eb 42 ff ff" // 28 负累积流量
      + " 92 50 bf 03" // 32 负累积流量小数部分
      + " 00 00 00 00" // 36 正累积热量
      + " 00 00 00 00" // 40 正累积热量小数部分
      + " 00 00 00 00" // 44 负累积热量
      + " 15 53 b9 40" // 48 负累积热量小数部分
      + " 48 14 00 03" // 52 净累积流量
      + " 53 c6 3f 3d" // 56 净累积流量小数部分
      + " 00 00 00 00" // 60
      + " 15 53 39 40" // 64
      + " fc b2 42 32" // 68
      + " 20 3a 42 33" // 72
      + " 8f 00 3d a3" // 76
      + " 74 00 3d 5c" // 80
      + " 19 99 bf bf" // 84
      + " d8 80 3c 82" // 88
      + " 5c 00 3c 30" // 92
      + " 9a 00 3c 99" // 96
      + " 00 00 ff ff" // 100
      + " 00 00" // 104
      + " 00 00 48 51 11 23" // 106
      + " 86 44";
  private byte[] modbusFrame1Binary;
  private byte[] modbusFrame2Binary;

  @BeforeTest
  public void init() {
    modbusFrame1Binary = ByteConvert.hex2ByteArray(MODBUS_FRAME_1);
    modbusFrame2Binary = ByteConvert.hex2ByteArray(MODBUS_FRAME_2);
  }

  @Test(description = "HalfByteBox.readAll测试用例，应能正常读取所有数据。")
  public void testReadAll() {
    ByteBox bbox = new HalfByteBox(modbusFrame1Binary);
    String hexResult = bbox.readAll().toHex();
    Assert.assertEquals(hexResult, MODBUS_FRAME_1.replaceAll(" ", "").toLowerCase());
  }

  @Test(description = "HalfByteBox.read测试用例，应能正常读取选择的数据")
  public void testRead() {
    ByteBox bbox = new HalfByteBox(modbusFrame1Binary);
    String hexResult = bbox.read(8, 4).toHex();
    Assert.assertEquals(hexResult, "6513");
  }

  @Test(description = "HalfByteBox.read测试用例，传入了错误的index，应抛出一个程序异常。", expectedExceptions = ArithmeticException.class)
  public void testReadWithErrorIndex() {
    ByteBox bbox = new HalfByteBox(modbusFrame1Binary);
    bbox.read(0, 1);
  }

  @Test(description = "HalfByteBox.read测试用例，传入了错误的length，应抛出一个程序异常。", expectedExceptions = ArithmeticException.class)
  public void testReadWithErrorLength() {
    ByteBox bbox = new HalfByteBox(modbusFrame1Binary);
    bbox.read(1, 0);
  }

  @Test(description = "HalfByteBox.read测试用例，传入了超长的length，应返回从index位置开始，剩余所有的数据。")
  public void testReadWithOverlength() {
    ByteBox bbox = new HalfByteBox(modbusFrame1Binary);
    String hexResult = bbox.read(1, 366).toHex();
    Assert.assertEquals(hexResult, MODBUS_FRAME_1.replaceAll(" ", "").toLowerCase());
  }

  @Test(description = "HalfByteBox.read2Byte测试用例，从起始位置读取2字节长度的数据。")
  public void testRead2Byte() {
    ByteBox bbox = new HalfByteBox(modbusFrame1Binary);
    String hexResult = bbox.read2Byte().toHex();
    Assert.assertEquals(hexResult, "0103");
  }

  @Test(description = "HalfByteBox.read2Byte测试用例，从指定的半字节index位置读取2字节长度的数据。")
  public void testRead2ByteWithIndexOnHalfByte() {
    ByteBox bbox = new HalfByteBox(modbusFrame1Binary);
    String hexResult = bbox.read2Byte(8).toHex();
    Assert.assertEquals(hexResult, "6513");
  }

  @Test(description = "HalfByteBox.read2Byte测试用例，从指定的字节index位置读取2字节长度的数据。")
  public void testRead2ByteWithIndexOnByte() {
    ByteBox bbox = new HalfByteBox(modbusFrame1Binary);
    String hexResult = bbox.read2Byte(9).toHex();
    Assert.assertEquals(hexResult, "513f");
  }

  @Test(description = "HalfByteBox.read4Byte测试用例，从起始位置读取4字节长度的数据。")
  public void testRead4Byte() {
    ByteBox bbox = new HalfByteBox(modbusFrame2Binary);
    String hexResult = bbox.read4Byte().toHex();
    Assert.assertEquals(hexResult, "50 03 6c 00".replaceAll(" ", "").toLowerCase());
  }

  @Test(description = "HalfByteBox.read4Byte测试用例，从指定的半字节index位置读取4字节长度的数据。")
  public void testRead4ByteWithIndexOnHalfByte() {
    ByteBox bbox = new HalfByteBox(modbusFrame2Binary);
    String hexResult = bbox.read4Byte(6).toHex();
    Assert.assertEquals(hexResult, "c0 0c e4 29".replaceAll(" ", "").toLowerCase());
  }

  @Test(description = "HalfByteBox.read4Byte测试用例，从指定的字节位置读取4字节长度的数据。")
  public void testRead4ByteWithIndexOnByte() {
    ByteBox bbox = new HalfByteBox(modbusFrame2Binary);
    String hexResult = bbox.read4Byte(7).toHex();
    Assert.assertEquals(hexResult, "00 ce 42 96".replaceAll(" ", "").toLowerCase());
  }

  @Test(description = "HalfByteBox.read8Byte测试用例，从起始位置读取8字节长度的数据。")
  public void testRead8Byte() {
    ByteBox bbox = new HalfByteBox(modbusFrame2Binary);
    String hexResult = bbox.read8Byte().toHex();
    Assert.assertEquals(hexResult, "50 03 6c 00 ce 42 96 00".replaceAll(" ", "").toLowerCase());
  }

  @Test(description = "HalfByteBox.read8Byte测试用例，从指定的半字节index位置读取8字节长度的数据。")
  public void testRead8ByteWithIndexOnHalfByte() {
    ByteBox bbox = new HalfByteBox(modbusFrame2Binary);
    String hexResult = bbox.read8Byte(36).toHex();
    Assert.assertEquals(hexResult, "4b 65 cd 30 00 3c b6 13".replaceAll(" ", "").toLowerCase());
  }

  @Test(description = "HalfByteBox.read8Byte测试用例，从指定字节的位置读取8字节长度的数据。")
  public void testRead8ByteWithIndexOnByte() {
    ByteBox bbox = new HalfByteBox(modbusFrame2Binary);
    String hexResult = bbox.read8Byte(63).toHex();
    Assert.assertEquals(hexResult, "92 50 bf 03 00 00 00 00".replaceAll(" ", "").toLowerCase());
  }

  @Test(description = "HalfByteBox.read16Byte测试用例，从起始位置读取16字节长度的数据。")
  public void testRead16Byte() {
    ByteBox bbox = new HalfByteBox(modbusFrame2Binary);
    String hexResult = bbox.read16Byte().toHex();
    Assert.assertEquals(hexResult, "50 03 6c 00 ce 42 96 00 00 00 00 e7 d7 3f 96 73".replaceAll(" ", "").toLowerCase());
  }

  @Test(description = "HalfByteBox.read16Byte测试用例，从指定的半字节位置读取16字节长度的数据。")
  public void testRead16ByteWithIndexOnHalfByte() {
    ByteBox bbox = new HalfByteBox(modbusFrame2Binary);
    String hexResult = bbox.read16Byte(2).toHex();
    Assert.assertEquals(hexResult, "0 03 6c 00 ce 42 96 00 00 00 00 e7 d7 3f 96 73 6".replaceAll(" ", "").toLowerCase());
  }

  @Test(description = "HalfByteBox.read16Byte测试用例，从指定的字节位置读取16字节长度的数据。")
  public void testRead16ByteWithIndexOnByte() {
    ByteBox bbox = new HalfByteBox(modbusFrame2Binary);
    String hexResult = bbox.read16Byte(3).toHex();
    Assert.assertEquals(hexResult, "03 6c 00 ce 42 96 00 00 00 00 e7 d7 3f 96 73 61".replaceAll(" ", "").toLowerCase());
  }

  @Test(description = "HalfByteBox.getLength测试用例，应能返回字节总数。")
  public void testGetLength() {
    ByteBox bbox = new HalfByteBox(modbusFrame1Binary);
    int length = bbox.getLength();
    Assert.assertEquals(length, 18);
  }
}
