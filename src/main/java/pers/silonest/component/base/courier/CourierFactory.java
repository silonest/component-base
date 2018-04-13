package pers.silonest.component.base.courier;

public class CourierFactory {
  public static <T> Courier<T> create(boolean status) {
    return new Courier<T>(status, null, null, null, null);
  }

  public static <T> Courier<T> create(boolean status, T content) {
    return new Courier<T>(status, null, null, null, content);
  }

  public static <T> Courier<T> create(boolean status, String cause, String notice) {
    return new Courier<T>(status, null, cause, notice, null);
  }

  public static <T> Courier<T> create(boolean status, String cause, String notice, T content) {
    return new Courier<T>(status, null, cause, notice, content);
  }

  public static <T> Courier<T> create(boolean status, String code, String cause, String notice, T content) {
    return new Courier<T>(status, code, cause, notice, content);
  }
}
