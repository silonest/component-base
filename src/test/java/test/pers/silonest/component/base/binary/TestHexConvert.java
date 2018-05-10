package test.pers.silonest.component.base.binary;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pers.silonest.component.base.binary.ByteConvert;
import pers.silonest.component.base.binary.HexConvert;

public class TestHexConvert {

  private short TEST_BYTE_TO_SHORT = 36;
  private int TEST_BYTE_TO_INT = 33626;
  private long TEST_BYTE_TO_LONG = 336666666;
  private float TEST_BYTE_TO_FLOAT = 33.666F;
  private String TEST_BYTE_TO_HEX = "33 66 66";
  private String TEST_HEX_TO_ASCII = "2b494d45493a";
  private String shortHex;
  private String shortHexOverFlow;
  private String intHex;
  private String intHexOverFlow;
  private String longHex;
  private String longHexOverFlow;
  private String floatHex;
  private String floatHexOverFlow;
  private String asciiHex;
  private String byteArrayHex;

  @BeforeTest
  public void init() {
    this.shortHex = HexConvert.short2Hex(TEST_BYTE_TO_SHORT);
    this.shortHexOverFlow = shortHex + "6";
    this.intHex = HexConvert.int2Hex(TEST_BYTE_TO_INT);
    this.intHexOverFlow = intHex + "6";
    this.longHex = HexConvert.long2Hex(TEST_BYTE_TO_LONG);
    this.longHexOverFlow = longHex + "6";
    this.floatHex = HexConvert.float2Hex(TEST_BYTE_TO_FLOAT);
    this.floatHexOverFlow = floatHex + "6";
    this.asciiHex = TEST_HEX_TO_ASCII;
    this.byteArrayHex = HexConvert.byteArray2Hex(ByteConvert.hex2ByteArray(TEST_BYTE_TO_HEX));
  }

  @Test
  public void testToByteArray() {
    HexConvert hc = new HexConvert(this.shortHex);
    byte[] shortByteArray = ByteConvert.hex2ByteArray(this.shortHex);
    Assert.assertEquals(hc.toByteArray(), shortByteArray);
  }

  @Test
  public void testToShort() {
    HexConvert hc = new HexConvert(this.shortHex);
    Assert.assertEquals(hc.toShort(), TEST_BYTE_TO_SHORT);
  }

  @Test
  public void testToShortOverFlow() {
    HexConvert hc = new HexConvert(this.shortHexOverFlow);
    Assert.assertEquals(hc.toShort(), TEST_BYTE_TO_SHORT);
  }

  @Test
  public void testToInt() {
    HexConvert hc = new HexConvert(this.intHex);
    Assert.assertEquals(hc.toInt(), TEST_BYTE_TO_INT);
  }

  @Test
  public void testToIntOverFlow() {
    HexConvert hc = new HexConvert(this.intHexOverFlow);
    Assert.assertEquals(hc.toInt(), TEST_BYTE_TO_INT);
  }

  @Test
  public void testToLong() {
    HexConvert hc = new HexConvert(this.longHex);
    Assert.assertEquals(hc.toLong(), TEST_BYTE_TO_LONG);
  }

  @Test
  public void testToLongOverFlow() {
    HexConvert hc = new HexConvert(this.longHexOverFlow);
    Assert.assertEquals(hc.toLong(), TEST_BYTE_TO_LONG);
  }

  @Test
  public void testToFloat() {
    HexConvert hc = new HexConvert(this.floatHex);
    Assert.assertEquals(hc.toFloat(), TEST_BYTE_TO_FLOAT);
  }

  @Test
  public void testToFloatOverFlow() {
    HexConvert hc = new HexConvert(this.floatHexOverFlow);
    Assert.assertEquals(hc.toFloat(), TEST_BYTE_TO_FLOAT);
  }

  @Test
  public void testToHex() {
    HexConvert hc = new HexConvert(TEST_BYTE_TO_HEX);
    Assert.assertEquals(hc.toHex(), TEST_BYTE_TO_HEX.replaceAll(" ", "").trim().toLowerCase());
  }

  @Test(description = "HexConvert.toASCII测试用例，根据传入的hex字符串，将其转成正确的ASCII字符串。")
  public void testToASCII() {
    HexConvert hc = new HexConvert(asciiHex);
    Assert.assertEquals(hc.toASCII(), "+IMEI:");
  }

  @Test(description = "测试byteArrayToHex方法的用例。")
  public void testByteArrayToHex() {
    HexConvert hc = new HexConvert(byteArrayHex);
    Assert.assertEquals(hc.toHex(), TEST_BYTE_TO_HEX.replaceAll(" ", "").toLowerCase());
  }

}
