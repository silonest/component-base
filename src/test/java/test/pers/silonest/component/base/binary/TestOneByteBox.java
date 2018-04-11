package test.pers.silonest.component.base.binary;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pers.silonest.component.base.binary.ByteBox;
import pers.silonest.component.base.binary.ByteTransfer;
import pers.silonest.component.base.binary.OneByteBox;

public class TestOneByteBox {
  private static final String MODBUS_FRAME_1 = "01 03 04 06 51 3F 9E 0E 32";
  private byte[] modbusFrame1Binary;
  private ByteBox bbox;

  @BeforeTest
  public void init() {
    modbusFrame1Binary = ByteTransfer.hex2Binary(MODBUS_FRAME_1);
    bbox = new OneByteBox(modbusFrame1Binary);
  }

  @Test
  public void testMODBusFrame1Decoder() {
    String hexResult = bbox.readByteTransfer(1, 1).toHex();
    short shortResult = bbox.readByteTransfer(1, 1).toShort();
    int intResult = bbox.readByteTransfer(1, 1).toInt();
    long longResult = bbox.readByteTransfer(1, 1).toLong();
    String secondHexResult = bbox.readByteTransfer(2, 1).toHex();
    float floatResult = bbox.readByteTransfer(4, 4).toFloat("C3-C4-C1-C2");

    Assert.assertEquals(hexResult, "01");
    Assert.assertEquals(shortResult, 1);
    Assert.assertEquals(intResult, 1);
    Assert.assertEquals(longResult, 1);
    Assert.assertEquals(secondHexResult, "03");
    Assert.assertEquals(floatResult, 1.2345678f);
  }
}
