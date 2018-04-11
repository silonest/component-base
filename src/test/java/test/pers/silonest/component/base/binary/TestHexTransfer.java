package test.pers.silonest.component.base.binary;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pers.silonest.component.base.binary.HexTransfer;
import pers.silonest.component.base.binary.TypeTransfer;

public class TestHexTransfer {

  private short TEST_BYTE_TO_SHORT = 36;
  private int TEST_BYTE_TO_INT = 33626;
  private long TEST_BYTE_TO_LONG = 336666666;
  private float TEST_BYTE_TO_FLOAT = 33.666F;
  private String TEST_BYTE_TO_HEX = "33 66 66";
  private String shortHex;
  private String shortHexOverFlow;
  private String intHex;
  private String intHexOverFlow;
  private String longHex;
  private String longHexOverFlow;
  private String floatHex;
  private String floatHexOverFlow;

  @BeforeTest
  public void init() {
    this.shortHex = HexTransfer.short2Hex(TEST_BYTE_TO_SHORT);
    this.shortHexOverFlow = shortHex + "6";
    this.intHex = HexTransfer.int2Hex(TEST_BYTE_TO_INT);
    this.intHexOverFlow = intHex + "6";
    this.longHex = HexTransfer.long2Hex(TEST_BYTE_TO_LONG);
    this.longHexOverFlow = longHex + "6";
    this.floatHex = HexTransfer.float2Hex(TEST_BYTE_TO_FLOAT);
    this.floatHexOverFlow = floatHex + "6";
  }

  @Test
  public void testToShort() {
    TypeTransfer typeTransfer = new HexTransfer(this.shortHex);
    Assert.assertEquals(typeTransfer.toShort(), TEST_BYTE_TO_SHORT);
  }

  @Test
  public void testToShortOverFlow() {
    TypeTransfer typeTransfer = new HexTransfer(this.shortHexOverFlow);
    Assert.assertEquals(typeTransfer.toShort(), TEST_BYTE_TO_SHORT);
  }

  @Test
  public void testToInt() {
    TypeTransfer typeTransfer = new HexTransfer(this.intHex);
    Assert.assertEquals(typeTransfer.toInt(), TEST_BYTE_TO_INT);
  }

  @Test
  public void testToIntOverFlow() {
    TypeTransfer typeTransfer = new HexTransfer(this.intHexOverFlow);
    Assert.assertEquals(typeTransfer.toInt(), TEST_BYTE_TO_INT);
  }

  @Test
  public void testToLong() {
    TypeTransfer typeTransfer = new HexTransfer(this.longHex);
    Assert.assertEquals(typeTransfer.toLong(), TEST_BYTE_TO_LONG);
  }

  @Test
  public void testToLongOverFlow() {
    TypeTransfer typeTransfer = new HexTransfer(this.longHexOverFlow);
    Assert.assertEquals(typeTransfer.toLong(), TEST_BYTE_TO_LONG);
  }

  @Test
  public void testToFloat() {
    TypeTransfer typeTransfer = new HexTransfer(this.floatHex);
    Assert.assertEquals(typeTransfer.toFloat(), TEST_BYTE_TO_FLOAT);
  }

  @Test
  public void testToFloatOverFlow() {
    TypeTransfer typeTransfer = new HexTransfer(this.floatHexOverFlow);
    Assert.assertEquals(typeTransfer.toFloat(), TEST_BYTE_TO_FLOAT);
  }

  @Test
  public void testToHex() {
    TypeTransfer typeTransfer = new HexTransfer(TEST_BYTE_TO_HEX);
    Assert.assertEquals(typeTransfer.toHex(), TEST_BYTE_TO_HEX.replaceAll(" ", "").trim().toLowerCase());
  }
}
