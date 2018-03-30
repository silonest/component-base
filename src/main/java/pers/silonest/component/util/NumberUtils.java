package pers.silonest.component.util;

public class NumberUtils {
  public static boolean isEvenNumber(int number) {
    if ((number & 1) != 0) {
      return false;
    } else {
      return true;
    }
  }
}
