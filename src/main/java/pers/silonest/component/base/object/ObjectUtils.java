package pers.silonest.component.base.object;

/**
 * ObjectUtils.
 *
 * @author silonest
 * @version v0.0.1
 * @email silonest@icloud.com
 * @time 2018年01月15日 下午5:50
 * @since v1.0.0
 */
public class ObjectUtils {

  private ObjectUtils() {
  }

  /**
   * Checks that the given argument is not null. If it is, throws {@link NullPointerException}.
   * Otherwise, returns the argument.
   */
  public static <T> T checkNotNull(T arg, String text) {
    if (arg == null) {
      throw new NullPointerException(text);
    }
    return arg;
  }

  /**
   * Checks that the given argument is strictly positive. If it is, throws {@link
   * IllegalArgumentException}. Otherwise, returns the argument.
   */
  public static int checkPositive(int i, String name) {
    if (i <= 0) {
      throw new IllegalArgumentException(name + ": " + i + " (expected: > 0)");
    }
    return i;
  }

  /**
   * Checks that the given argument is strictly positive. If it is, throws {@link
   * IllegalArgumentException}. Otherwise, returns the argument.
   */
  public static long checkPositive(long i, String name) {
    if (i <= 0) {
      throw new IllegalArgumentException(name + ": " + i + " (expected: > 0)");
    }
    return i;
  }

  /**
   * Checks that the given argument is positive or zero. If it is, throws {@link
   * IllegalArgumentException}. Otherwise, returns the argument.
   */
  public static int checkPositiveOrZero(int i, String name) {
    if (i < 0) {
      throw new IllegalArgumentException(name + ": " + i + " (expected: >= 0)");
    }
    return i;
  }

  /**
   * Checks that the given argument is positive or zero. If it is, throws {@link
   * IllegalArgumentException}. Otherwise, returns the argument.
   */
  public static long checkPositiveOrZero(long i, String name) {
    if (i < 0) {
      throw new IllegalArgumentException(name + ": " + i + " (expected: >= 0)");
    }
    return i;
  }

  /**
   * Checks that the given argument is neither null nor empty. If it is, throws {@link
   * NullPointerException} or {@link IllegalArgumentException}. Otherwise, returns the argument.
   */
  public static <T> T[] checkNonEmpty(T[] array, String name) {
    checkNotNull(array, name);
    checkPositive(array.length, name + ".length");
    return array;
  }

  /**
   * Resolves a possibly null Integer to a primitive int, using a default value.
   *
   * @param wrapper the wrapper
   * @param defaultValue the default value
   * @return the primitive value
   */
  public static int intValue(Integer wrapper, int defaultValue) {
    return wrapper != null ? wrapper : defaultValue;
  }

  /**
   * Resolves a possibly null Long to a primitive long, using a default value.
   *
   * @param wrapper the wrapper
   * @param defaultValue the default value
   * @return the primitive value
   */
  public static long longValue(Long wrapper, long defaultValue) {
    return wrapper != null ? wrapper : defaultValue;
  }
}
