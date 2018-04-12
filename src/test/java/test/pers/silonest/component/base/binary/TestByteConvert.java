package test.pers.silonest.component.base.binary;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pers.silonest.component.base.binary.ByteConvert;

public class TestByteConvert {
  private short TEST_BYTE_TO_SHORT = 36;
  private int TEST_BYTE_TO_INT = 33626;
  private long TEST_BYTE_TO_LONG = 336666666;
  private float TEST_BYTE_TO_FLOAT = 1.234567f;
  private String TEST_BYTE_TO_HEX = "33666666";
  // 注意：如果修改了“TEST_BYTE_TO_FLOAT_WITH_PARAMETER”值，需要修改断言中的参照值。
  private String TEST_BYTE_TO_FLOAT_WITH_PARAMETER = "AAA94206";
  private byte[] longBytes;
  private byte[] overFlowLongBytes;
  private byte[] intBytes;
  private byte[] overFlowIntBytes;
  private byte[] shortBytes;
  private byte[] overFlowShortBytes;
  private byte[] floatBytes;
  private byte[] overFlowFloatBytes;
  private byte[] flipFloatBytes;
  private byte[] hexBytes;

  @BeforeTest
  public void init() {
    // 生成short的正常测试数据。
    this.shortBytes = ByteConvert.short2ByteArray((short) TEST_BYTE_TO_SHORT);
    // 生成溢出一位的short数据，用于溢出用例的测试。
    this.overFlowShortBytes = new byte[this.shortBytes.length + 1];
    for (int i = 0; i < shortBytes.length; i++) {
      this.overFlowShortBytes[i] = this.shortBytes[i];
    }
    this.overFlowShortBytes[this.shortBytes.length] = 26;
    // 生成int的正常测试数据。
    this.intBytes = ByteConvert.int2ByteArray(TEST_BYTE_TO_INT);
    // 生成溢出一位的int数据，用于溢出用例的测试。
    this.overFlowIntBytes = new byte[this.intBytes.length + 1];
    for (int i = 0; i < intBytes.length; i++) {
      this.overFlowIntBytes[i] = this.intBytes[i];
    }
    this.overFlowIntBytes[this.intBytes.length] = 26;
    // 生成long的正常测试数据。
    this.longBytes = ByteConvert.long2ByteArray(TEST_BYTE_TO_LONG);
    // 生成溢出一位的long数据，用于溢出用例的测试。
    this.overFlowLongBytes = new byte[this.longBytes.length + 1];
    for (int i = 0; i < longBytes.length; i++) {
      this.overFlowLongBytes[i] = this.longBytes[i];
    }
    this.overFlowLongBytes[this.longBytes.length] = 26;
    // 生成float的正常测试数据。
    this.floatBytes = ByteConvert.float2ByteArray(TEST_BYTE_TO_FLOAT);
    // 生成溢出一位的hex数据，用于溢出用例的测试。
    this.overFlowFloatBytes = new byte[this.floatBytes.length + 1];
    for (int i = 0; i < floatBytes.length; i++) {
      this.overFlowFloatBytes[i] = this.floatBytes[i];
    }
    // 生成高低字节转换的float测试数据。
    this.flipFloatBytes = ByteConvert.hex2ByteArray(TEST_BYTE_TO_FLOAT_WITH_PARAMETER);
    this.overFlowFloatBytes[this.floatBytes.length] = 26;
    // 生成hex的测试数据。
    this.hexBytes = ByteConvert.hex2ByteArray(TEST_BYTE_TO_HEX);
  }

  /**
   * 转换成字节数组的正常用例.
   */
  @Test
  public void testToByteArray() {
    ByteConvert bt = new ByteConvert(this.shortBytes);
    byte[] result = bt.toByteArray();
    Assert.assertEquals(result, this.shortBytes);
  }

  /**
   * 转换成short的正常用例.
   */
  @Test
  public void testToShort() {
    ByteConvert bt = new ByteConvert(this.shortBytes);
    short result = bt.toShort();
    Assert.assertEquals(result, TEST_BYTE_TO_SHORT);
  }

  /**
   * 转换成short的溢出用例.当传入的字节数组大于2时，方法应会自动截取前2位，并将其转换成short型。
   */
  @Test
  public void testToShortOverFlow() {
    ByteConvert bt = new ByteConvert(this.overFlowShortBytes);
    short result = bt.toShort();
    Assert.assertEquals(result, TEST_BYTE_TO_SHORT);
  }

  /**
   * 转换成int的正常用例.测试解析int数据是否正常。
   */
  @Test
  public void testToInt() {
    ByteConvert bt = new ByteConvert(this.intBytes);
    int result = bt.toInt();
    Assert.assertEquals(result, TEST_BYTE_TO_INT);
  }

  /**
   * 转换成int的溢出用例.当传入的字节数组长度大于4时，方法应会自动截取前4位并将其转换成int型。
   */
  @Test
  public void testToIntOverFlow() {
    ByteConvert bt = new ByteConvert(this.overFlowIntBytes);
    int result = bt.toInt();
    Assert.assertEquals(result, TEST_BYTE_TO_INT);
  }

  /**
   * 转换成long的正常用例.
   */
  @Test
  public void testToLong() {
    ByteConvert bt = new ByteConvert(this.longBytes);
    long result = bt.toLong();
    Assert.assertEquals(result, TEST_BYTE_TO_LONG);
  }

  /**
   * 转换成long的溢出用例.当传入的字节数组长度大于8时，方法应会自动截取前8位并将其转换成long型。
   */
  @Test
  public void testToLongOverFlow() {
    ByteConvert bt = new ByteConvert(this.overFlowLongBytes);
    long result = bt.toLong();
    Assert.assertEquals(result, TEST_BYTE_TO_LONG);
  }

  /**
   * 转换成float的正常用例.
   */
  @Test
  public void testToFloat() {
    ByteConvert bt = new ByteConvert(this.floatBytes);
    float result = bt.toFloat();
    Assert.assertEquals(result, TEST_BYTE_TO_FLOAT);
  }

  @Test
  public void testToFloatWithFormat() {
    ByteConvert bt = new ByteConvert(this.flipFloatBytes);
    float result = bt.toFloat("C3-C4-C1-C2");
    Assert.assertEquals(result, 33.66666F);
  }

  /**
   * 转换成float的溢出用例.当传入的字节数组大于4时，方法应会自动截取前4位，并将其转换成float型。
   */
  @Test
  public void testToFloatOverFlow() {
    ByteConvert bt = new ByteConvert(this.overFlowFloatBytes);
    float result = bt.toFloat();
    Assert.assertEquals(result, TEST_BYTE_TO_FLOAT);
  }

  /**
   * 转换成hex的正常用例.
   */
  @Test
  public void testToHex() {
    ByteConvert bt = new ByteConvert(this.hexBytes);
    String result = bt.toHex();
    Assert.assertEquals(result, TEST_BYTE_TO_HEX.toLowerCase());
  }
}
