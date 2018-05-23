package pers.silonest.component.util;

import java.math.BigDecimal;

public abstract class ArithmeticUtils {
  public static final int DIGITS_AFTER_THE_DECIMAL_POINT = 5;

  public static String multiply(String faciend, String multiplicator, int scale) {
    return new BigDecimal(faciend).multiply(new BigDecimal(multiplicator)).setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
  }

  public static String multiply(String faciend, String multiplicator) {
    return multiply(faciend, multiplicator, DIGITS_AFTER_THE_DECIMAL_POINT);
  }

  public static String divide(String dividend, String divisor, int scale) {
    return new BigDecimal(dividend).divide(new BigDecimal(divisor), scale, BigDecimal.ROUND_HALF_UP).toString();
  }

  public static String divide(String dividend, String divisor) {
    return divide(dividend, divisor, DIGITS_AFTER_THE_DECIMAL_POINT);
  }

  public static String scale(String number, int scale) {
    return new BigDecimal(number).setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
  }

  public static String scale(String number) {
    return scale(number, DIGITS_AFTER_THE_DECIMAL_POINT);
  }
}
