package pers.silonest.component.base.courier;

public class CourierFactory {
  public static Courier create(boolean status) {
    return new Courier(status, null, null, null);
  }

  public static <T> Courier create(boolean status, T content) {
    return new ContentCourier<T>(status, null, null, null, content);
  }

  public static Courier create(boolean status, String cause, String notice) {
    return new Courier(status, null, cause, notice);
  }

  public static <T> Courier create(boolean status, String cause, String notice, T content) {
    return new ContentCourier<T>(status, null, cause, notice, content);
  }

  public static <T> Courier create(boolean status, String code, String cause, String notice, T content) {
    return new ContentCourier<T>(status, code, cause, notice, content);
  }
}
