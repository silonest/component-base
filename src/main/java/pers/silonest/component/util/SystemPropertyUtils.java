package pers.silonest.component.util;

import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * SystemPropertyUtils.
 *
 * @author silonest
 * @version v0.0.1
 * @email silonest@icloud.com
 * @time 2018年01月15日 下午5:44
 * @since v1.0.0
 */
public class SystemPropertyUtils {

  /**
   * Returns {@code true} if and only if the object property with the specified {@code key} exists.
   */
  public static boolean contains(String key) {
    return get(key) != null;
  }

  /**
   * Returns the value of the Java object property with the specified {@code key}, while falling
   * back to {@code null} if the property access fails.
   *
   * @return the property value or {@code null}
   */
  public static String get(String key) {
    return get(key, null);
  }

  /**
   * Returns the value of the Java object property with the specified {@code key}, while falling
   * back to the specified default value if the property access fails.
   *
   * @return the property value. {@code def} if there's no such property or if an access to the
   *         specified property is not allowed.
   */
  public static String get(final String key, String def) {
    if (key == null) {
      throw new NullPointerException("key");
    }
    if (key.isEmpty()) {
      throw new IllegalArgumentException("key must not be empty.");
    }

    String value = null;
    try {
      if (System.getSecurityManager() == null) {
        value = System.getProperty(key);
      } else {
        value = AccessController.doPrivileged(new PrivilegedAction<String>() {
          @Override
          public String run() {
            return System.getProperty(key);
          }
        });
      }
    } catch (SecurityException e) {
    }

    if (value == null) {
      return def;
    }

    return value;
  }

  /**
   * Returns the value of the Java object property with the specified {@code key}, while falling
   * back to the specified default value if the property access fails.
   *
   * @return the property value. {@code def} if there's no such property or if an access to the
   *         specified property is not allowed.
   */
  public static boolean getBoolean(String key, boolean def) {
    String value = get(key);
    if (value == null) {
      return def;
    }

    value = value.trim().toLowerCase();
    if (value.isEmpty()) {
      return true;
    }

    if ("true".equals(value) || "yes".equals(value) || "1".equals(value)) {
      return true;
    }

    if ("false".equals(value) || "no".equals(value) || "0".equals(value)) {
      return false;
    }

    return def;
  }

  /**
   * Returns the value of the Java object property with the specified {@code key}, while falling
   * back to the specified default value if the property access fails.
   *
   * @return the property value. {@code def} if there's no such property or if an access to the
   *         specified property is not allowed.
   */
  public static int getInt(String key, int def) {
    String value = get(key);
    if (value == null) {
      return def;
    }

    value = value.trim();
    try {
      return Integer.parseInt(value);
    } catch (Exception e) {
      // Ignore
    }

    return def;
  }

  /**
   * Returns the value of the Java object property with the specified {@code key}, while falling
   * back to the specified default value if the property access fails.
   *
   * @return the property value. {@code def} if there's no such property or if an access to the
   *         specified property is not allowed.
   */
  public static long getLong(String key, long def) {
    String value = get(key);
    if (value == null) {
      return def;
    }

    value = value.trim();
    try {
      return Long.parseLong(value);
    } catch (Exception e) {
      // Ignore
    }

    return def;
  }

  private SystemPropertyUtils() {
    // Unused
  }
}
