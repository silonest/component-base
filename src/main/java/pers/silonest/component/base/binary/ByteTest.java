package pers.silonest.component.base.binary;

public class ByteTest {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    byte[] binary = new byte[] {26, 19, 26, 58, 23, 69};
    byte[] binaryDouble = new byte[binary.length * 2];
    for (int i = 0; i < binary.length; i++) {
      byte b = binary[i];
      binaryDouble[i * 2] = (byte) (b & 0xF0);
      binaryDouble[(i * 2) + 1] = (byte) (b & 0x0F);
    }
    byte first = (byte) (binaryDouble[0] | binaryDouble[1]);
    System.out.println(first);

    BinaryBox binaryBox = new HalfByteBox(binary);
    byte[] result = binaryBox.readBytes(1, 3);
    System.out.println("size:" + result.length + "first:" + result[0] + "binaryStr:"
        + Integer.toBinaryString(result[0]));
  }

}
