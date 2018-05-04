package test.pers.silonest.component.base.binary.box;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pers.silonest.component.base.binary.ByteConvert;
import pers.silonest.component.base.binary.box.ByteBox;
import pers.silonest.component.base.binary.box.OneByteBox;

public class TestOneByteBox {
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

  @Test(description = "ByteBox类，测试解析modbus协议是否正确。")
  public void testMODBusFrame1Decoder() {
    ByteBox bbox = new OneByteBox(modbusFrame1Binary);

    String hexResult = bbox.read(1, 1).toHex();
    short shortResult = bbox.read(1, 1).toShort();
    int intResult = bbox.read(1, 1).toInt();
    long longResult = bbox.read(1, 1).toLong();
    String secondHexResult = bbox.read(2, 1).toHex();
    float floatResult = bbox.read(4, 4).toFloat("C2-C1-C4-C3");

    Assert.assertEquals(hexResult, "01");
    Assert.assertEquals(shortResult, 1);
    Assert.assertEquals(intResult, 1);
    Assert.assertEquals(longResult, 1);
    Assert.assertEquals(secondHexResult, "03");
    Assert.assertEquals(floatResult, 1.2345678f);
  }

  @Test(description = "ByteBox.readAll测试，是否能正确取出所有的数据。")
  public void testReadAll() {
    ByteBox bbox = new OneByteBox(modbusFrame1Binary);
    String hexResult = bbox.readAll().toHex();
    Assert.assertEquals(hexResult, MODBUS_FRAME_1.replaceAll(" ", "").toLowerCase());
  }

  @Test(description = "ByteBox.read2Byte测试，是否能从第一个位置取出2个字节的数据。")
  public void testRead2Byte() {
    ByteBox bbox = new OneByteBox(modbusFrame1Binary);
    String hexResult = bbox.read2Byte().toHex();
    Assert.assertEquals(hexResult, "0103");
  }

  @Test(description = "ByteBox.read2Byte测试，是否能从传入的位置取出2个字节的数据。 ")
  public void testRead2ByteWithIndex() {
    ByteBox bbox = new OneByteBox(modbusFrame1Binary);
    String hexResult = bbox.read2Byte(4).toHex();
    Assert.assertEquals(hexResult, "0651".replaceAll(" ", "").toLowerCase());
  }

  @Test(description = "ByteBox.read4Byte测试，是否能正确取出4字节的数据。")
  public void testRead4Byte() {
    ByteBox bbox = new OneByteBox(modbusFrame1Binary);
    String hexResult = bbox.read4Byte().toHex();
    Assert.assertEquals(hexResult, "01030406");
  }

  @Test(description = "ByteBox.read4Byte测试，是否能从传入的位置取出4字节的数据。")
  public void testRead4ByteWithIndex() {
    ByteBox bbox = new OneByteBox(modbusFrame1Binary);
    String hexResult = bbox.read4Byte(4).toHex();
    Assert.assertEquals(hexResult, "06513f9e");
  }

  @Test(description = "ByteBox.read8Byte测试，是否能正确取出8字节的数据。")
  public void testRead8Byte() {
    ByteBox bbox = new OneByteBox(modbusFrame1Binary);
    String hexResult = bbox.read8Byte().toHex();
    Assert.assertEquals(hexResult, "01 03 04 06 51 3F 9E 0E".replaceAll(" ", "").toLowerCase());
  }

  @Test(description = "ByteBox.read8Byte测试，是否能从传入的位置取出8字节的数据。")
  public void testRead8ByteWithIndex() {
    ByteBox bbox = new OneByteBox(modbusFrame2Binary);
    String hexResult = bbox.read8Byte(2).toHex();
    Assert.assertEquals(hexResult, "03 6c 00 ce 42 96 00 00".replaceAll(" ", "").toLowerCase());
  }

  @Test(description = "ByteBox.read16Byte测试，是否能从第一个位置取出16字节的数据。")
  public void testRead16Byte() {
    ByteBox bbox = new OneByteBox(modbusFrame2Binary);
    String hexResult = bbox.read16Byte(2).toHex();
    Assert.assertEquals(hexResult, "03 6c 00 ce 42 96 00 00 00 00 e7 d7 3f 96 73 61".replaceAll(" ", "").toLowerCase());
  }

  @Test(description = "ByteBox.read16Byte测试，是否能从指定位置取出16字节的数据。")
  public void testRead16ByteWithIndex() {
    ByteBox bbox = new OneByteBox(modbusFrame2Binary);
    String hexResult = bbox.read16Byte().toHex();
    Assert.assertEquals(hexResult, "50 03 6c 00 ce 42 96 00 00 00 00 e7 d7 3f 96 73".replaceAll(" ", "").toLowerCase());
  }

  @Test(description = "ByteBox.getLength测试，能否正常取出字节的长度。")
  public void testGetLength() {
    ByteBox bbox = new OneByteBox(modbusFrame1Binary);
    int length = bbox.getLength();
    Assert.assertEquals(length, 9);
  }

  @Test(description = "ByteBox.getLength测试，当传入的对象是null时，取出的length是0。")
  public void testGetLengthWithNullParameter() {
    ByteBox bbox = new OneByteBox(null);
    int length = bbox.getLength();
    Assert.assertEquals(length, 0);
  }

  @Test(description = "ByteBox.read测试，根据传入的index和length读取正确的数据。")
  public void testRead() {
    ByteBox bbox = new OneByteBox(modbusFrame1Binary);
    String hexResult = bbox.read(4, 2).toHex();
    Assert.assertEquals(hexResult, "06 51".replaceAll(" ", ""));
  }

  @Test(description = "ByteBox.read测试，传入错误的index，应抛出异常。", expectedExceptions = ArithmeticException.class)
  public void testReadWithErrorIndex() {
    ByteBox bbox = new OneByteBox(modbusFrame1Binary);
    bbox.read(-1, 2).toHex();
  }

  @Test(description = "ByteBox.read测试，传入错误的length，应抛出异常。", expectedExceptions = ArithmeticException.class)
  public void testReadWithErrorLength() {
    ByteBox bbox = new OneByteBox(modbusFrame1Binary);
    bbox.read(1, 0).toByteArray();
  }

  @Test(description = "ByteBox.read测试，传入超长的length，应返回从index开始的剩余所有数据。")
  public void testReadWithOverlength() {
    ByteBox bbox = new OneByteBox(modbusFrame1Binary);
    String hexResult = bbox.read(6, 336).toHex();
    Assert.assertEquals(hexResult, "3F 9E 0E 32".replaceAll(" ", "").toLowerCase());
  }

  @Test(description = "ByteBox.read测试，传入临界值的length，应返回从index开始所有的数据。")
  public void testReadWithCriticalLength() {
    ByteBox bbox = new OneByteBox(modbusFrame1Binary);
    String hexResult = bbox.read(7, 3).toHex();
    Assert.assertEquals(hexResult, "9E 0E 32".replaceAll(" ", "").toLowerCase());
  }
}
