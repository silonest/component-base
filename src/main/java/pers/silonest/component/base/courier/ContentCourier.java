package pers.silonest.component.base.courier;

public class ContentCourier<T> extends Courier {
  private T content;

  public ContentCourier() {}

  public ContentCourier(boolean status, String code, String cause, String notice, T content) {
    this.setStatus(status);
    this.setCode(code);
    this.setCause(cause);
    this.setNotice(notice);
    this.content = content;
  }

  public T getContent() {
    return content;
  }

  public void setContent(T content) {
    this.content = content;
  }
}
