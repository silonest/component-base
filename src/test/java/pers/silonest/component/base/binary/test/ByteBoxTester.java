package pers.silonest.component.base.binary.test;

import org.junit.Assert;
import org.junit.Test;

import pers.silonest.component.base.binary.BinaryBox;
import pers.silonest.component.base.binary.HalfByteBox;

public class ByteBoxTester {

  @Test
  public void testByteBox() {
    byte[] binary = new byte[] {26, 19, 26, 58, 23, 69};
    byte[] binaryDouble = new byte[binary.length * 2];
    for (int i = 0; i < binary.length; i++) {
      byte b = binary[i];
      binaryDouble[i * 2] = (byte) (b & 0xF0);
      binaryDouble[(i * 2) + 1] = (byte) (b & 0x0F);
    }
    byte first = (byte) (binaryDouble[0] | binaryDouble[1]);
    Assert.assertEquals(26, first);

    BinaryBox binaryBox = new HalfByteBox(binary);
    byte[] result = binaryBox.readBytes(1, 3);

    Assert.assertEquals(2, result.length);
    Assert.assertEquals(-95, result[0]);
    Assert.assertEquals("11111111111111111111111110100001", Integer.toBinaryString(result[0]));
  }
}
